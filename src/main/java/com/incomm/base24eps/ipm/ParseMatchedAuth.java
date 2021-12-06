package com.incomm.base24eps.ipm;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ParseMatchedAuth
{

	static Logger logger = Logger.getLogger("ParseMatchedAuth.class");

	//Application properties.
	private static Properties props;
	static Connection conn = null;

	public static void main(String [] args) throws Exception
	{
		logger.info("########################################");
		Date startDate = new Date();
		logger.info("ParseMatchedAuth Started: " + startDate);

		long startMemory = Runtime.getRuntime().totalMemory();
		logger.info("startMemory = " + startMemory/1000+" KB");
		PreparedStatement pStatement = null;
		
		String query = "SELECT Distinct FileId, FileName from IPMFileStatus WITH (NOLOCK) where ReportGeneratedStatusId = 0";

		String reportQuery = "{call usp_IPMAccountLog_sel(?)}";
		ResultSet rs = null;
		
		int FileID = 0;
		String fileName = "";
		try
		{
			PropertyConfigurator.configure("config/log4j.properties");

			init();
			String ipmReportFolderName = props.getProperty("IPM.reports");
			
			String IPMKey = EPSBatchAuthorization.getAttribute("IPMKey", conn);
			pStatement = conn.prepareStatement(query);
			pStatement.executeQuery();
			rs = pStatement.getResultSet();
			
			if (rs != null)
		    {
				while (rs.next()) //for number of files in IPMFileStatus table.
				{
					 FileID = rs.getInt(1);	
					 fileName = rs.getString(2);
					 logger.info(" FileID = "+FileID+" fileName = "+fileName);
					 CallableStatement pStatementReport = null;
					 StringBuffer csvBufferGC = new StringBuffer();
					 StringBuffer csvBufferVMS = new StringBuffer();
					 StringBuffer csvBufferRev = new StringBuffer();
					 ResultSet rsReport = null;
					 String reportString = "";
					 String hostName = "";
					 int countGC = 0;
					 int countVMS = 0;
					 int countRev = 0;
					 int tranType = 0;
					 
					 pStatementReport = conn.prepareCall(reportQuery);	
					 pStatementReport.setInt(1, FileID);
					 pStatementReport.executeQuery();
					 rsReport = pStatementReport.getResultSet();
				 
					 if (rsReport !=null)
				     {
						 while (rsReport.next()) 
						 {
								reportString = rsReport.getString(1);
								hostName = rsReport.getString(2);
								tranType = rsReport.getInt(3);
								if (reportString!=null)
								{								
									reportString = new EncryptDecryptUtil().decrypt(reportString, IPMKey);
									reportString = reportString.substring(0,reportString.lastIndexOf(",")); //to bypass last element - AmountFee
//									logger.info(" reportString = "+reportString);
									logger.info(" hostName = "+hostName);
									if (hostName!=null)
									{
										if (hostName.equals("GreenCard") || hostName.equals("NotDefined"))
										{
											if(countGC==0)
											{
												csvBufferGC.append("MTI,PAN,ProcessCode,Amount,ReconAmount,CardHolderBillingAmount,AmountCurr,ReconAmountCurr,CardHolderBillingAmountCurr,DateTimeLocalTXN,SettlementDate,MCC,MerchantID,TerminalID,BanknetRefNum,MerchantName,POSDataCode,AcqRefNum,AcqInstIDCode,AmountFee");
												csvBufferGC.append("\n");
											}
												csvBufferGC.append(reportString);
												csvBufferGC.append("\n");
												countGC = countGC + 1;
										}
										else if (hostName.equals("VMS"))
										{
											if(countVMS==0)
											{
												csvBufferVMS.append("MTI,PAN,ProcessCode,Amount,ReconAmount,CardHolderBillingAmount,AmountCurr,ReconAmountCurr,CardHolderBillingAmountCurr,DateTimeLocalTXN,SettlementDate,MCC,MerchantID,TerminalID,BanknetRefNum,MerchantName,POSDataCode,AcqRefNum,AcqInstIDCode,AmountFee");
												csvBufferVMS.append("\n");
											}
												csvBufferVMS.append(reportString);
												csvBufferVMS.append("\n");
												countVMS = countVMS + 1;
										} 
									}
									if (tranType == 2) //for reversals
									{									
										if(countRev==0)
										{
											csvBufferRev.append("MTI,PAN,ProcessCode,Amount,ReconAmount,CardHolderBillingAmount,AmountCurr,ReconAmountCurr,CardHolderBillingAmountCurr,DateTimeLocalTXN,SettlementDate,MCC,MerchantID,TerminalID,BanknetRefNum,MerchantName,POSDataCode,AcqRefNum,AcqInstIDCode,AmountFee");
											csvBufferRev.append("\n");
										}
										csvBufferRev.append(reportString);
										csvBufferRev.append("\n");
										countRev = countRev + 1;
									}
								}
						 	} //while (rsReport.next()) 
				     	} //if (rsReport !=null)
				 	
					 	logger.info("countGC:"+countGC);
					 	logger.info("countVMS:"+countVMS);
					 	logger.info("countRev:"+countRev);
					 
					 	//Put below loggers in 'debug' as they have PAN
					 	logger.debug("csvBufferGC::"+csvBufferGC);
					 	logger.debug("csvBufferVMS::"+csvBufferVMS);
						if (csvBufferGC.length() > 1)
			    	 	{
							logger.info("csvBufferGC length::"+csvBufferGC.length());
							logger.info("Creating Report File for GC::"+ipmReportFolderName+"/"+fileName+"_GC.csv with " +countGC+" Records");
					        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ipmReportFolderName+"/"+fileName+"_GC.csv")));
					        bw.write(csvBufferGC.toString());
					        bw.close();
			    	 	}
			    	 	if (csvBufferVMS.length() > 1)
			    	 	{
			    	 		logger.info("csvBufferVMS length ::"+csvBufferVMS.length());
			    	 		logger.info("Creating Report File for VMS::"+ipmReportFolderName+"/"+fileName+"_VMS.csv with " +countVMS+" Records");
				             BufferedWriter bwvms = new BufferedWriter(new FileWriter(new File(ipmReportFolderName+"/"+fileName+"_VMS.csv")));
				             bwvms.write(csvBufferVMS.toString());
				             bwvms.close();
			    	 	}
			    	 	if (csvBufferRev.length() > 1)
			    	 	{
			    	 		logger.info("csvBufferRev length::"+csvBufferRev.length());
			    	 		logger.info("Creating Report File for Reversals::"+ipmReportFolderName+"/"+fileName+"_Rev.csv with " +countRev+" Records");
			    	 		BufferedWriter bwrev = new BufferedWriter(new FileWriter(new File(ipmReportFolderName+"/"+fileName+"_Rev.csv")));
				            bwrev.write(csvBufferRev.toString());
				            bwrev.close();
			    	 	}
		    	 	
			    	 	String updateQuery = "Update IPMFileStatus set ReportGeneratedStatusId =2,ReportGeneratedEndTime=? where FileId =?";

			    	 	java.util.Date today = new java.util.Date();
					    java.sql.Timestamp time =  new java.sql.Timestamp(today.getTime());

					    PreparedStatement pStatementUpd = conn.prepareStatement(updateQuery);
			    	 	pStatementUpd.setTimestamp(1, (java.sql.Timestamp)time);
			    	 	pStatementUpd.setInt(2, FileID);
			    	 	
			    	 	int result = pStatementUpd.executeUpdate();
			    	 	logger.info("UpDate Result::"+result);
		    	 		
				} //while (rs.next())
				 
			} //if (rs != null)	
			else {
				logger.info("No File found for Report creation process " + new Date());
			}
			
			Date endDate = new Date();
			logger.info("ParseMatchedAuth Ended: " + new Date());

			logger.info("Total time taken = " + (endDate.getTime() - startDate.getTime())/1000.0+" Seconds");
			logger.info("########################################");
		
		
		}catch (Exception e)
		{
			logger.error(e.getMessage());
			StringWriter stackTrace = new StringWriter();
			e.printStackTrace(new PrintWriter(stackTrace));
			logger.error(stackTrace.toString());
		}
		finally 
		{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}						
			if(pStatement != null)
				try {
					pStatement.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} 
            if(conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} 
        }	

	} //main(String [] args)
	
	private static void init() throws Exception
	{
		logger.info(">> init method...");
		props = new Properties();
		props.load(new FileInputStream("./config/application.properties"));
		logger.info(props);
		conn = getDBConnection();
		logger.info("<< init method...");
	}
	
	public static Connection getDBConnection()throws IPMGenericException {
    	Connection con = null;
    	try {
	    	DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
	    	con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+props.getProperty("IPM.Server")+"/"+props.getProperty("IPM.databaseName")+";sendStringParametersAsUnicode=false", props.getProperty("IPM.userName"), props.getProperty("IPM.passWord"));
	    	logger.info("Connection Establish"+con);
    	} catch (SQLException sqle) {
    		logger.error("Error connecting to the database.");
			StringWriter stackTrace = new StringWriter();
			sqle.printStackTrace(new PrintWriter(stackTrace));
			logger.error(stackTrace.toString());
    	}
    	return con;
    }
}

