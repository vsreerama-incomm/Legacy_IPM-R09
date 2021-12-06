package com.incomm.base24eps.ipm;

import java.io.*;

import org.jpos.iso.ISOException;

import com.incomm.base24eps.ipm.EPSBatchAuthorization;
import com.incomm.base24eps.ipm.ICISOMsg;
import com.incomm.base24eps.ipm.IPMISOPackager;
import com.incomm.base24eps.ipm.IPMGenericException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ProcessParsedIPM {

	static Logger logger = Logger.getLogger("ProcessParsedIPM.class");

	//Application properties.
	private static Properties props;
	static Connection conn = null;

	private static Properties IPMRoutingProps;
	
	public static void main(String[] args) {
		
		logger.info("########################################");
		Date startDate = new Date();
		logger.info("Process Started: " + startDate);

		long startMemory = Runtime.getRuntime().totalMemory();
		logger.info("startMemory = " + startMemory/1000+" KB");
		
			
		try	{
			
			PropertyConfigurator.configure("config/log4j.properties");

			/**
			 * Method to load application.properties
			 */
			init();
			
			String ipmFolderName = props.getProperty("IPM.unprocessed");
			String ipmReportFolderName = props.getProperty("IPM.reports");
			String ipmParsedFolderName = props.getProperty("IPM.parsed");
			int batchSize =  Integer.parseInt(props.getProperty("IPM.batchSize"));
			String dummyNumber = props.getProperty("IPM.dummyNumber");
			//int PreEditFileSkipChar =  Integer.parseInt(props.getProperty("IPM.PreEditFileSkipChar"));
			logger.info("dummyNumber:" +dummyNumber);
			ProcessParsedIPM prsIPM = new ProcessParsedIPM();
			String ipmFile = "";
			ArrayList ipmFileList = getFileNames(ipmParsedFolderName, props.getProperty("IPM.fileNameStartWith"));
			
			for (Iterator<String> iterfi = ipmFileList.iterator(); iterfi.hasNext(); ) 
			{
				ipmFile = iterfi.next();
				logger.info("ipmFile : "+ipmFile);
				String fileHash = null;
				String ipmFileName = ipmParsedFolderName+"/"+ipmFile;
				logger.info("ipmFileName = " + ipmFileName);
				try {
						logger.info("Found file, waiting for "+new Integer(props.getProperty("IPM.waitTime"))/1000+" seconds");
						Thread.sleep(new Long(props.getProperty("IPM.waitTime")));
						int fileAlreadyParsed = new Integer(props.getProperty("IPM.fileAlreadyParsed"));
						logger.info("FileAlreadyParsed::"+fileAlreadyParsed);
						//Njalagam:01-31-2019:GCP-177 IPM poller - file hash to prevent duplicate data loads
						fileHash = IPMUtil.getSHA512Checksum(ipmFileName);
						logger.info(">>> fileHash is : " + fileHash);
						if(!StringUtils.isBlank(fileHash) && IPMUtil.isDuplicateFile(fileHash,conn)){
							logger.info("This is duplicate file : " + ipmFile);
							movefile("duplicate",ipmFileName);
							continue;
						}
						/*if(fileAlreadyParsed == 0) {
							prsIPM.parse(ipmFileName); //to remove @@ from the file.
							logger.info("IPM File parsed successfully to remove @@");
						}
						else {
							logger.info("Received IPM File with @@ already removed.");
						}*/
							
						prsIPM.processFile(ipmFileName, ipmFile, batchSize,dummyNumber,ipmReportFolderName,fileHash);	
				} catch(Exception e) {
					StringWriter stackTrace = new StringWriter();
					e.printStackTrace(new PrintWriter(stackTrace));
					logger.fatal(stackTrace.toString());
				}
			}
			
		} catch(Exception e) {
			StringWriter stackTrace = new StringWriter();
			e.printStackTrace(new PrintWriter(stackTrace));
			logger.error(stackTrace.toString());
		}
		finally 
		{
			try 
			{
				if(conn != null) {
					conn.close();
				}
			} 
			catch (SQLException e) {
				logger.fatal("Exception: " + e.getMessage());
			} catch(Exception ee) {
				logger.error("Error while moving file to error folder: "+ee.getMessage());		
			}			
		}	
		
		Date endDate = new Date();
		logger.info("Process Ended: " + new Date());

		logger.info("Total time taken = " + (endDate.getTime() - startDate.getTime())/1000.0+" Seconds");
		logger.info("########################################");
	}
	
	
	static int convertByteToInt(byte[] bRDW) {
		String result = "";
		int decimal = 0;
		for(int i=0; i<bRDW.length; i++) {
			result += Integer.toString((bRDW[i] & 0xff) + 0x100, 16).substring(1);
		}
		decimal = Integer.parseInt(result, 16);
		
		return decimal;
	}
	
	private void processFile(String ipmFileName, String ipmFile, int batchSize, String dummyNumber, String ipmReportFolderName,String fileHash) throws IPMGenericException {
		
		logger.info(">>> processFile.");
		
		byte bRDW [] 		= new byte[4];
		byte bIPMRecord[] 	= null;
		int iBytesRead 		= 0;
		int iRecordLength 	= 0;
		int count = 0;
		int countGC = 0;
		int countVMS = 0;
		//PreparedStatement cs = null;
		CallableStatement cs = null;
		long startTime = 0;
		int result = 0;
		String hostSystem = null;
		logger.info("batchSize = " + batchSize);
		long lastModifiedTime = 0;

		Map dataElementMap 				= new HashMap();
		List<Map> firstPresentmentList 	= new ArrayList<Map>();

		String sHeaderRecord 		= "";
		
		SimpleDateFormat sdt 		= new SimpleDateFormat("yyyyMMddHHmm");
		String sFileDate			= sdt.format(Calendar.getInstance().getTime());

		IPMISOPackager packager 	= null;
		ICISOMsg icisomsg 			= null;
		StringBuffer csvBufferGC = new StringBuffer();
		StringBuffer csvBufferVMS = new StringBuffer();
		
		EPSBatchAuthorization epsBatchAuth = null;

		BufferedInputStream bis = null;
		DataInputStream dis = null;
		epsBatchAuth = new EPSBatchAuthorization();
		
		try	{
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(ipmFileName)));
			lastModifiedTime = new File(ipmFileName).lastModified();

			logger.info("lastModifiedTime = " + lastModifiedTime);
			packager 	= new IPMISOPackager();
			icisomsg	= new ICISOMsg(packager.getClass());
			String query = "insert into IPMAccountLog(FileId,SourceDesc,HisoRequest,StatusId,CreateDt,TranTypeId,HostName,ReportData,carNumber) values (?,?,?,?,?,?,?,?,?)";
			cs = conn.prepareCall("{call usp_IPMAccountLog_ins(?,?,?,?,?,?,?,?,?)}");
			//cs = conn.prepareStatement(query);				
			
//			String IPMKey = epsBatchAuth.getAttribute("IPMKey", conn);
			String IPMKey = EPSBatchAuthorization.getAttribute("IPMKey", conn);
			String reportString = "";
			conn.setAutoCommit(false);

			//First 4 bytes gives the length of the IPM Record.
			while( ( iBytesRead = dis.read(bRDW, 0, 4) ) != -1) 
			{
				String IPMRecord 	= null;
				String IPMRecordS = null;
				String tranType=null;
				iRecordLength = convertByteToInt(bRDW);
				logger.info("iRecordLength = " + iRecordLength);
				
				if(iRecordLength == 0) break;
				
				bIPMRecord = new byte[iRecordLength];
				iBytesRead = dis.read(bIPMRecord, 0, iRecordLength);
				logger.info("bytes read:"+iBytesRead);
				logger.debug(new String(bIPMRecord));
				icisomsg.unpack(new ByteArrayInputStream(bIPMRecord), dataElementMap);
				logger.info("dataElementMap = " + IPMUtil.maskCardNumberTrackData((HashMap<String, String>) dataElementMap));
				
				String MTI = dataElementMap.get("0").toString();
				String FuncCode = dataElementMap.get("24").toString();
				logger.info("FuncCode = " + FuncCode+"  MTI = "+MTI);
				if(MTI.equals("1644") && FuncCode.equals("697")) { //Header Record
					logger.info("Header FuncCode = " + FuncCode+"  MTI = "+MTI);
					String DE48 	= dataElementMap.get("48").toString();
					int iFileIDLen 	= 0;
					String sFileID 	= "";
					
					if(DE48.indexOf("0105") != -1)	{
						iFileIDLen = new Integer(DE48.substring(DE48.indexOf("0105")+4, DE48.indexOf("0105")+7)).intValue();

						sFileID = DE48.substring(DE48.indexOf("0105")+7, DE48.indexOf("0105")+7+iFileIDLen);
						logger.info("Header FileID = " + sFileID);
						
						//sHeaderRecord = epsBatchAuth.createHeader(sFileID);
						//logger.info("sHeaderRecord = " + sHeaderRecord);
						
						result = epsBatchAuth.executeFileProcessStartIns(ipmFile,"MasterCard","queued",sHeaderRecord,null,null,null,count,lastModifiedTime,0, conn,fileHash);
						logger.info("FileProcessStartIns result = " + result);
						if(result==0) 
						{
							logger.info("IPM Already Processed.");
							//move IPM File to error folder.
							movefile("error",ipmFileName);
//							IPMGenericException oe = new IPMGenericException("IPM Already Processed: "+ipmFileName);
//							throw oe;
							
							return; //no need of above exception throw.
						} 

					}
					else {
						//move IPM File to error folder.
						movefile("error",ipmFileName);
						sHeaderRecord =  "Invalid File: p0105(File ID) Missing In The FileHeader";
						epsBatchAuth.executeFileProcessStartIns(ipmFile,"MasterCard","suspanded",sHeaderRecord,null,"suspanded","suspanded",count,lastModifiedTime,result, conn,fileHash);
						//logger.info("FileProcessStartIns result = " + result);
					}

					
				}
				else if(MTI.equals("1644") && FuncCode.equals("685")) { //Trailer
					logger.info("File Completed = " + ipmFile);
					 epsBatchAuth.executeFileProcessStartIns(ipmFile,"MasterCard","complete",null,null,"queued","queued",count,lastModifiedTime,result, conn,fileHash);
				}
				else if(MTI.equals("1240") && FuncCode.equals("200")) {
					try{
						//logger.info("dataElementMap = " + dataElementMap);
						
						//HashMap dataElementMaps = new HashMap();
//						logger.info("Record FuncCode = " + FuncCode+"  MTI = "+MTI);
						
						IPMRecord = epsBatchAuth.generateHisoRecord(dataElementMap, dummyNumber);
						//Njoshi - Commented below, masked PAN and other data is being shown under 'generateHisoRecord'
						//logger.info("IPMRecord & reportString: "+IPMRecord);
						String[] IPMRecords= IPMRecord.split(";");
						
						if (IPMRecords.length==2)
						{
							IPMRecord = IPMRecords[0];
							reportString = IPMRecords[1];
						}
						
						logger.info("BIN : "+IPMRecord.substring(38,44));
//						hostSystem = epsBatchAuth.getHostName(IPMRecord.substring(38,44), conn);
						hostSystem = IPMRoutingProps.getProperty(IPMRecord.substring(38,44));
						if(hostSystem == null) {
							hostSystem = "NotDefined";
						}
						logger.info("hostSystem : "+hostSystem);
						tranType = IPMRecord.substring(IPMRecord.length()-1);
						logger.info("tranType : "+tranType);
						IPMRecordS = IPMRecord.substring(0,IPMRecord.length()-1);
						logger.debug("IPMRecordS : "+IPMRecordS);
						logger.debug("Report String : "+reportString);
					}catch (Exception e)
					{
						logger.error("Error in processing Record = " + IPMUtil.maskCardNumberTrackData((HashMap<String, String>) dataElementMap));
						
					}
					
					 count = count+1;					     
					 logger.info("RecordCount = " + count);
				     startTime = System.currentTimeMillis();
				     java.util.Date today = new java.util.Date();
				     java.sql.Timestamp time =  new java.sql.Timestamp(today.getTime());
//				     logger.info("time = " + time);
				     	
				     cs.setInt(1, result);
				     logger.info("FileID = " + result);
				     cs.setString(2, "MasterCard");
/*				     
				     pStatement.setString(3, encDecUtil.encrypt(IPMRecordS.toString()));
				     pStatement.setString(3, encDecUtil.encrypt(IPMRecord.toString()));
				     cs.setString(3, IPMRecordS);
				     new EncryptDecryptUtil().encrypt("11007E36A541A8E4F0081660114065395030890000000000000001500000000002000000000002000820145902032177130820105902200713082008218405003010640011005999040000040000326011406539503089=200752110000155201059001823000000000000000 00000000000000043ABCD1234 A StreetAtlanta0000000000GA USA03400840D0000005000000000C00000000840840124124D4CBCDC5FC3D3E89121001008014 0 02003040PRO1      DSCV      DS        DS        005004PULS018006L30RET0190011020005PULSE022003PUL023006INTL11", passPhrase);
*/				     
				     cs.setString(3, new EncryptDecryptUtil().encrypt(IPMRecordS, IPMKey));
//				     logger.info("IPMRecordS = " + IPMRecordS);
				     cs.setInt(4, 0);
				     cs.setTimestamp(5, (java.sql.Timestamp)time);
				         if(tranType.equals("R"))
				        	 cs.setInt(6, 2);
				         else 
				        	 cs.setInt(6, 1);
				         
				         cs.setString(7, hostSystem);
//				         logger.info("hostSystem = " + hostSystem);
				         cs.setString(8, new EncryptDecryptUtil().encrypt(reportString, IPMKey));
//				         logger.info("reportString = " + reportString);
				         cs.setString(9, IPMRecord.substring(38,44));
				         cs.addBatch();
				         logger.info("Record "+count+" added to Batch in File"+ipmFile);
				         if (count % batchSize == 0) {
				        	 logger.info("DB Tran Started: " + new Date());
				        	 logger.info("Commiting Tran:"+count);
				        	 cs.executeBatch();
				        	 conn.commit();
				        	 logger.info("DB Tran Ended: " + new Date());
				        	 cs.clearBatch();

				         }

					
					//firstPresentmentList.add(dataElementMap);
				
				} //1240-200 record
				
				dataElementMap = new HashMap();
				
			} //while. for every IPM Record.
			
			logger.info("executeBatch:"+count);   
			logger.info("DB Tran Started: " + new Date());
			cs.executeBatch() ;//for remaining batch queries if total record is odd no.
			 logger.info("Commiting Tran:"+count);   
		     conn.commit();
		     logger.info("DB Tran Ended: " + new Date());
		     
		     
		     logger.info("Record Processed:"+count+" in File"+ipmFile);
		     logger.info("Commiting Tran at EOF");
		  
    		DatabaseMetaData meta = conn.getMetaData();
            logger.info("supportsBatchUpdates "+meta.supportsBatchUpdates());

			logger.info("Move IPM File to processed folder..."+ipmFileName);
			//move incoming IPM File to processed folder because we could successfully create a batch file out of it.
			movefile("processed",ipmFileName);			
		}
		catch(ISOException isoException) {
			
			StringWriter sw = new StringWriter();
			isoException.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
			
//			logger.fatal("Record Processed:"+count+" in File"+ipmFile);
			logger.error("isoException: " + isoException.getMessage());
			IPMGenericException oe = new IPMGenericException("isoException while Processing the File: "+ipmFileName);
			try {
			sHeaderRecord =  "ISO Exception on Record :"+count;
			epsBatchAuth.executeFileProcessStartIns(ipmFile,"MasterCard","suspanded",sHeaderRecord,null,"suspanded","suspanded",count,lastModifiedTime,result, conn,fileHash);			
			movefile("error",ipmFileName);
			}
			catch(Exception e) {
				logger.error("Error while moving file to error folder: "+e.getMessage());
				IPMGenericException oe1 = new IPMGenericException("Error while moving file to error folder: "+ipmFileName);
				throw oe1;
			}
			throw oe;
		}
		catch (SQLException e) {
			logger.error("Record Processed:"+count+" in File"+ipmFile);
				logger.error("SQLException: " + e.getMessage());
				IPMGenericException oe = new IPMGenericException("SQLException while Processing the File: "+ipmFileName);
				try {
				sHeaderRecord =  "ISO Exception on Record :"+count;
				epsBatchAuth.executeFileProcessStartIns(ipmFile,"MasterCard","suspanded",sHeaderRecord,null,"suspanded","suspanded",count,lastModifiedTime,result, conn,fileHash);
				movefile("error",ipmFileName);
				}
				catch(Exception ee) {
					logger.error("Error while moving file to error folder: "+ee.getMessage());
					IPMGenericException oe1 = new IPMGenericException("Error while moving file to error folder: "+ipmFileName);
					throw oe1;
				}
				throw oe;			
		}
		catch(EOFException eofException) {
			StringWriter sw = new StringWriter();
			eofException.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());

			logger.error("Record Processed:"+count+" in File"+ipmFile);
			logger.error("EOFException: " + eofException.getMessage());
			IPMGenericException oe = new IPMGenericException("EOFException while Processing the File: "+ipmFileName);
			try {
				movefile("error",ipmFileName);
			}
			catch(Exception ee) {
				logger.error("Error while moving file to error folder: "+ee.getMessage());
				IPMGenericException oe1 = new IPMGenericException("Error while moving file to error folder: "+ipmFileName);
				throw oe1;
			}
			throw oe;
		}
		catch(IOException ioe) {
			logger.error("Record Processed:"+count+" in File"+ipmFile);
			logger.error("IOException: " + ioe.getMessage());
			IPMGenericException oe = new IPMGenericException("IOException while Processing the File: "+ipmFileName);
			try {
				movefile("error",ipmFileName);
			}
			catch(Exception ee) {
				logger.error("Error while moving file to error folder: "+ee.getMessage());
				IPMGenericException oe1 = new IPMGenericException("Error while moving file to error folder: "+ipmFileName);
				throw oe1;
			}
			throw oe;
		}
		catch(Exception e) {
			logger.error("Record Processed:"+count+" in File"+ipmFile);
			logger.error("Exception: " + e.getMessage());
			IPMGenericException oe = new IPMGenericException("Exception while Processing the File: "+ipmFileName);
			try {
				movefile("error",ipmFileName);
			}
			catch(Exception ee) {
				logger.error("Error while moving file to error folder: "+ee.getMessage());
				IPMGenericException oe1 = new IPMGenericException("Error while moving file to error folder: "+ipmFileName);
				throw oe1;
			}
			throw oe;
		}
		finally {	
			if(bis != null) {
				try {
					bis.close();
				} catch(Exception e) {
					logger.error("Exception: " + e.getMessage());
					IPMGenericException oe = new IPMGenericException("Exception while bis.close(): "+ipmFileName);
					try {
						movefile("error",ipmFileName);
					}
					catch(Exception ee) {
						logger.error("Error while moving file to error folder: "+ee.getMessage());
						IPMGenericException oe1 = new IPMGenericException("Error while moving file to error folder: "+ipmFileName);
						throw oe1;
					}
					throw oe;					
				}
			}
			if(dis != null) {
				try {
					dis.close();
				} catch(Exception e) {
					logger.error("Exception: " + e.getMessage());
					IPMGenericException oe = new IPMGenericException("Exception while dis.close(): "+ipmFileName);
					try {
						movefile("error",ipmFileName);
					}
					catch(Exception ee) {
						logger.error("Error while moving file to error folder: "+ee.getMessage());
						IPMGenericException oe1 = new IPMGenericException("Error while moving file to error folder: "+ipmFileName);
						throw oe1;
					}
					throw oe;									
				}
			}
			
			try {
				if(cs != null){
					cs.close();
				}
			} catch (SQLException e) {
				logger.error("Exception: " + e.getMessage());
				IPMGenericException oe = new IPMGenericException("Exception while pStatement.close(): "+ipmFileName);
				try {
					movefile("error",ipmFileName);
				}
				catch(Exception ee) {
					logger.error("Error while moving file to error folder: "+ee.getMessage());
					IPMGenericException oe1 = new IPMGenericException("Error while moving file to error folder: "+ipmFileName);
					throw oe1;
				}
				throw oe;
			}
			
			packager 	= null;
			icisomsg 	= null;
			epsBatchAuth = null;
		}
		
		logger.info("<<< processFile.");

	}

	
	private static void init() throws Exception
	{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			logger.info(">>> init method");
			props = new Properties();
			props.load(new FileInputStream("./config/application.properties"));
			logger.info(props);
			conn = getDBConnection();
	
			//load data from IPMRouting table.
			
			String query = "select * from IPMRouting with (nolock)";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			IPMRoutingProps = new Properties();
			
			while(rs != null && rs.next()) {
				
				IPMRoutingProps.put(rs.getString(1), rs.getString(2));
			}
			
			logger.info("IPMRoutingProps: "+IPMRoutingProps);
			
			logger.info("<<< init method");
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
		}
	}
	
	public static void movefile(String location, String filename) throws IPMGenericException, IOException
    {
    	try 
        { 
	    	File f = new File(props.getProperty("IPM.parsed"));
	    	String sourceFile = f.getAbsolutePath()+"\\"+filename.substring(filename.lastIndexOf("/")+1);
	    	logger.info("file to move: "+ sourceFile);

	    	File f2 = null;
	    	
	        if(location.equals("error"))
		       	 f2 = new File(props.getProperty("IPM.error"));
	        else if(location.equals("duplicate"))
	        	 f2 = new File(props.getProperty("IPM.duplicate"));
	        else
		       	 f2 = new File(props.getProperty("IPM.processed"));

	    	String destinationFolder = f2.getAbsolutePath();
	    	logger.info("destination folder: "+ destinationFolder);
	
	    	String moveCommand = "cmd /c move "+sourceFile+" "+destinationFolder;
	    	logger.info(moveCommand);
	    	
	    	Runtime.getRuntime().exec(moveCommand);
	    	logger.info("File moved successfully.");
        }
    	catch(Exception e)
        {
        	IPMGenericException oe = new IPMGenericException("Error in moving the file :"+filename+": "+e.getMessage());
        	logger.error("Error in moving the file :"+e.getMessage());
			throw oe;
        }
    	
    }

/*	
	public static void movefile_backup(String location, String filename) throws IPMGenericException
    {
    	// File (or directory) to be moved
    	File dir = null;
    	File file = null;
    	logger.info("Moving File "+filename+" to folder "+location);
        try 
        { 
        	file = new File(filename);
          if(location.equals("error"))
	        	 dir = new File(props.getProperty("IPM.error"));
          else
	        	 dir = new File(props.getProperty("IPM.processed"));
        // Move file to new directory
          logger.info("Moving File dir:"+dir);
          logger.info("file.getName():"+file.getName());
        boolean success = file.renameTo(new File(dir, file.getName()));
        if (success) {
        	logger.info(">>> File: "+filename+" Moved Sucessfully to "+dir);
        }else {
        	logger.info(">>> Error in moving File: "+
        			filename+"  "+dir);
        }
        }catch(Exception e)
        {
        	IPMGenericException oe = new IPMGenericException("Error in moving the file :"+filename+": "+e.getMessage());
        	logger.info("Error in moving the file :"+e.getMessage());
			throw oe;
        }
    	
    }
*/		

	public static Connection getDBConnection()throws IPMGenericException{
    	Connection con = null;
    	try {
    	 DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
    	 con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+props.getProperty("IPM.Server")+"/"+props.getProperty("IPM.databaseName")+";sendStringParametersAsUnicode=false", props.getProperty("IPM.userName"), props.getProperty("IPM.passWord"));
    	 logger.info("Connection Establish"+con);
    	} catch (SQLException sqle) {
    		IPMGenericException oe = new IPMGenericException("Not Able to make connection to Database: "+props.getProperty("IPM.databaseName")+": "+sqle.getMessage());
			logger.error("Not Able to make connection to: Database"+sqle.getMessage());
			throw oe;
    	}
    	return con;
    }
	
	public static ArrayList getFileNames(String Directory, String fileNameStartWith) throws IPMGenericException{
    	logger.info(" Directory : "+ Directory+" fileNameStartWith:"+fileNameStartWith);
    	File dir = new File(Directory);
    	String[] files = dir.list();
    	ArrayList orderFileName = new ArrayList();
    	if (files == null) {
    		logger.info(" Directory does not exist : "+ Directory);    	    
    	} else {
    	    for (int i=0; i<files.length; i++) {
    	        // Get filename of file or directory
    	        String filename = files[i];
    	        if (filename.startsWith(fileNameStartWith) && (!filename.endsWith(".PGP")))
    	        {
    	        	logger.info("File Name in : "+ Directory +" is "+ filename); 
    	        	orderFileName.add(filename);
    	        }
    	    }
    	}
    	logger.info("shipOrderFileName  "+orderFileName);
    	if(orderFileName.isEmpty())
//    		throw new IPMGenericException("No File Found with Name : "+fileNameStartWith);
    		logger.info("No files found with name: "+ fileNameStartWith);
    	return orderFileName;
    }

	private void parse(String ipmFile) throws Exception {
		
		logger.info(">>> parse");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			logger.info("bytes to read in one swoop:"+props.getProperty("IPM.bytesToRead"));
			byte [] arr = new byte[new Integer(props.getProperty("IPM.bytesToRead"))];
			
			File sourceFile = new File(ipmFile);
			logger.info("source file:"+sourceFile);
			File parsedFile = new File(ipmFile.substring(0, ipmFile.lastIndexOf("."))+"_parsed"+ipmFile.substring(ipmFile.lastIndexOf(".")));
			logger.info("parsed file:"+parsedFile);
			
			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			bos = new BufferedOutputStream(new FileOutputStream(parsedFile, true));
			int bytesRead = 0;
			
			//Commented the code to remove all @@ from file
			/*while((bytesRead=bis.read(arr, 0, 10000)) != -1) {
				String str = new String(arr, 0, bytesRead, "ISO-8859-1");
				bos.write(str.replaceAll("@@", "").getBytes("ISO-8859-1"));
			}*/
			
			//code to remove '@@' at every 1013 and 1014 positions
			while((bytesRead=bis.read(arr, 0, 1014)) != -1) {
				StringBuffer bStr = new StringBuffer(new String(arr, 0, bytesRead, "ISO-8859-1"));
				logger.debug("Str before removal of @@-->"+bStr);
				if(bStr.length()<1014){
					logger.debug("str of end of file--->"+bStr);
					bos.write(bStr.substring(0,bStr.length()).getBytes("ISO-8859-1"));
				}else{
					logger.debug("Characters at 1013 and 1014 positions"+ bStr.substring(1012,1014));
					//if(bStr.substring(1012,1014).equals("@@")){
					bos.write(bStr.substring(0,1012).getBytes("ISO-8859-1"));
					//}else{
					//bos.write(bStr.substring(0,1014).getBytes("ISO-8859-1"));
					//}
				}
			}
			logger.info("parsing completed.");
			
			bis.close();
			bos.close();
			
			if(sourceFile.exists()) {
				logger.info("source file exists");
				boolean bool = sourceFile.delete();
				logger.info("source file deleted: "+bool);
				parsedFile.renameTo(new File(ipmFile));
				logger.info("parsed file renamed to the original");
			}
			else {
				logger.info("source file does NOT exist");
			}

		}
		catch(Exception e) {
			throw e;
		}
		finally {
			try {
				if(bis != null)
					bis.close();
				if(bos != null) 
					bos.close();
			}
			catch(Exception e) {
				
			}
		}
		
		logger.info("<<< parse");
	} //parse
}
