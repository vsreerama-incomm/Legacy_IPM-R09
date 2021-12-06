package com.incomm.base24eps.ipm;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ReportByBinNew {

	static Logger logger = Logger.getLogger("ReportByBin.class");

	//Application properties.
	private static Properties props;
	static Connection conn = null;

	static LinkedHashMap<String, Integer> lhm = null;
	private static Properties IPMRoutingProps;

	private static String [] BINList = null;
	private static String [] PCList = null;
	private static String [] EPList = null;
	
	public static void main(String [] args) throws Exception 
	{
		logger.info("########################################");
		Date startDate = new Date();
		logger.info("ReportByBin Started: " + startDate);

		long startMemory = Runtime.getRuntime().totalMemory();
		logger.info("startMemory = " + startMemory/1000+" KB");
		
		PreparedStatement pStatement = null;
		
		String query = "SELECT Distinct FileId, FileName from IPMFileStatus WITH (NOLOCK) where BinReportGeneratedStatusId = 0";

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
			logger.debug("rs = " + rs);

			while ( rs != null && rs.next()) //for each ipm file.
			{
				FileID = rs.getInt(1);	
				fileName = rs.getString(2);
				logger.info(" FileID = "+FileID+" fileName = "+fileName);

				initDynamicVariables(fileName); //build lhm with 0 data.
				StringBuffer GCReportData = new StringBuffer();
				StringBuffer VMSReportData = new StringBuffer();
				
				 
				CallableStatement pStatementReport = null;

				ResultSet rsReport = null;
				String reportString = "";

				pStatementReport = conn.prepareCall(reportQuery);	
				pStatementReport.setInt(1, FileID);
				pStatementReport.executeQuery();
				rsReport = pStatementReport.getResultSet();
				 
				logger.info("MTI,PAN,ProcessCode,Amount,ReconAmount,CardHolderBillingAmount,AmountCurr,ReconAmountCurr,CardHolderBillingAmountCurr,DateTimeLocalTXN,SettlementDate,MCC,MerchantID,TerminalID,BanknetRefNum");
			 
				while (rsReport != null && rsReport.next()) //for each IPM record 
				{
					reportString = rsReport.getString(1);
					reportString = new EncryptDecryptUtil().decrypt(reportString, IPMKey);

					if (reportString != null)
					{
						logger.debug(" reportString = "+reportString);
						String PAN = null;
						String BIN = "";
						String ProcCode = null;
						String ReconAmount = null;
						int iReconAmount = 0;
						String CBAmount = null;
						String DateTimeLocalTXN = null;
						String SettlementDate = null;
						String MCC = null;
						String MerchantID = null;
						String TerminalID = null;
						String INTCHFee = null;
						int feeAmount = 0;
						int reconFeeAmount = 0;
						int iCBAmount = 0;
							
						StringTokenizer reportStr = new StringTokenizer(reportString, ",");
						logger.debug(" reportStr = "+reportStr);
						while (reportStr.hasMoreElements()) //for every data element in the IPM Record
						{
							reportStr.nextElement(); // to skip MTI - 1220
							
							PAN = reportStr.nextElement().toString();
							//logger.info(" PAN = "+IPMUtil.maskText(PAN, 6, 12, "*")); 
							BIN = PAN.substring(1, 7);
							logger.info("BIN = "+BIN);
							
							ProcCode = reportStr.nextElement().toString();
							ProcCode = ProcCode.substring(1,ProcCode.length()-1);
							logger.info(" ProcCode = "+ProcCode);								
							
							reportStr.nextElement();
							ReconAmount = reportStr.nextElement().toString();
							logger.info(" ReconAmount = "+ReconAmount);
							iReconAmount = new Integer(ReconAmount).intValue();
							
							CBAmount = reportStr.nextElement().toString();
							logger.info(" CBAmount = "+CBAmount);
							iCBAmount = new Integer(CBAmount).intValue();
							logger.info(" iCBAmount = "+iCBAmount);
							
							reportStr.nextElement();reportStr.nextElement();reportStr.nextElement();
							
							DateTimeLocalTXN = reportStr.nextElement().toString();
							logger.info(" DateTimeLocalTXN = "+DateTimeLocalTXN);
							
							SettlementDate = reportStr.nextElement().toString();
							logger.info(" SettlementDate = "+SettlementDate);
							
							MCC = reportStr.nextElement().toString();
							logger.info(" MCC = "+MCC);
							
							MerchantID = reportStr.nextElement().toString();
							logger.info(" MerchantID = "+MerchantID);
								
							TerminalID = reportStr.nextElement().toString();
							logger.info(" TerminalID = "+TerminalID);
							
							reportStr.nextElement(); //skip transport data
							reportStr.nextElement(); //skip card acceptor name location
							reportStr.nextElement(); //skip POSDataCode - added on 02/02/2015
							reportStr.nextElement(); //skip AcqRefData - added on 02/02/2015
							reportStr.nextElement(); //skip AcqInstIDCode - added on 02/02/2015
							reportStr.nextElement(); //skip AmountFee_SF5 - added on 02/06/2015
							
							INTCHFee = reportStr.nextElement().toString();
							logger.info(" INTCHFee = "+INTCHFee);
							
							feeAmount = new Integer(INTCHFee.substring(6, 14)).intValue();
							logger.info(" feeAmount = "+feeAmount);
							
							reconFeeAmount = new Integer(INTCHFee.substring(23, 31)).intValue();
							logger.info(" reconFeeAmount = "+reconFeeAmount);
						
							
							//Build report data dynamically
							lhm.put("Count_"+BIN, lhm.get("Count_"+BIN)+1); //incrementing BIN Count.
							
							//if ProcCode starts with a 2 then subtract amount otherwise add
							//eg; 280000, 200000
							
							if(ProcCode.startsWith("2")) { //subtract
								
								lhm.put("ReconAmount_"+BIN, lhm.get("ReconAmount_"+BIN)-iReconAmount); 
								lhm.put("CBAmount_"+BIN, lhm.get("CBAmount_"+BIN)-iCBAmount); 
								lhm.put("FeeAmountTotal_"+BIN, lhm.get("FeeAmountTotal_"+BIN)-feeAmount); 
								lhm.put("ReconFeeAmountTotal_"+BIN, lhm.get("ReconFeeAmountTotal_"+BIN)-reconFeeAmount); 
							}
							else { //add
								
								lhm.put("ReconAmount_"+BIN, lhm.get("ReconAmount_"+BIN)+iReconAmount); 
								lhm.put("CBAmount_"+BIN, lhm.get("CBAmount_"+BIN)+iCBAmount); 
								lhm.put("FeeAmountTotal_"+BIN, lhm.get("FeeAmountTotal_"+BIN)+feeAmount); 
								lhm.put("ReconFeeAmountTotal_"+BIN, lhm.get("ReconFeeAmountTotal_"+BIN)+reconFeeAmount); 
							}
							
							lhm.put("Amount_"+BIN+"_"+ProcCode, lhm.get("Amount_"+BIN+"_"+ProcCode)+iCBAmount); 
							lhm.put("ReconAmount_"+BIN+"_"+ProcCode, lhm.get("ReconAmount_"+BIN+"_"+ProcCode)+iReconAmount); 
							lhm.put("FeeAmount_"+BIN+"_"+ProcCode, lhm.get("FeeAmount_"+BIN+"_"+ProcCode)+feeAmount); 
							lhm.put("ReconFeeAmount_"+BIN+"_"+ProcCode, lhm.get("ReconFeeAmount_"+BIN+"_"+ProcCode)+reconFeeAmount); 
							
							
						} //while for every data element in an IPM Record.

					} //if reportString

				} //while for each IPM Record.
			 
			logger.info("lhm with data::"+lhm);
			
			
			buildReportData(lhm, GCReportData, VMSReportData);
			
			logger.debug("GCReportData::"+GCReportData);
			logger.debug("VMSReportData::"+VMSReportData);
				 
			StringBuffer reportHeaderSB = new StringBuffer();
//			reportHeaderSB.append("BIN,TXNCount,CBAmount,ReconAmount,CCA(ReconAmount-CBAmount),");
			reportHeaderSB.append("BIN,TXNCount,CBAmount,ReconAmount,CCA(ReconAmount-CBAmount),FeeAmountTotal,ReconFeeAmountTotal,");
			
			for(String PC : PCList) {
				
				reportHeaderSB.append("CBAmount("+PC+"),ReconAmount("+PC+"),FeeAmount("+PC+"),ReconFeeAmount("+PC+"),");
			}
			
			reportHeaderSB.setLength(reportHeaderSB.length()-1);
			reportHeaderSB.append("\n");
			logger.debug("reportHeaderSB::"+reportHeaderSB);

			generateReports(reportHeaderSB.toString(), GCReportData, VMSReportData, ipmReportFolderName, fileName);
			
			String updateQuery = "Update IPMFileStatus set BinReportGeneratedStatusId =1,BinReportGeneratedEndTime=? where FileId =?";
		 	PreparedStatement pStatementUpd = null;
		 	int result = 0 ;
		 	logger.info("UpDate FileID::"+FileID);
		 	java.util.Date today = new java.util.Date();
		    java.sql.Timestamp time =  new java.sql.Timestamp(today.getTime());
		 	
		    pStatementUpd = conn.prepareStatement(updateQuery);
		 	pStatementUpd.setTimestamp(1, (java.sql.Timestamp)time);
		 	pStatementUpd.setInt(2, FileID);
		 	
		 	result = pStatementUpd.executeUpdate();
	 		logger.info("UpDate Result::"+result);
		
	 		System.runFinalization(); //for clean up after generating report for each IPM File.
	 		
		} // While for each ipm file
    
		Date endDate = new Date();
		logger.info("ReportByBin Ended: " + new Date());

		logger.info("Total time taken = " + (endDate.getTime() - startDate.getTime())/1000.0+" Seconds");
		logger.info("########################################");
	
	}
	catch (Exception e)
	{
		StringWriter stackTrace = new StringWriter();
		e.printStackTrace(new PrintWriter(stackTrace));
		logger.error(stackTrace.toString());
	}
	finally 
	{
		if( rs != null )
			try {
				rs.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}						
		if( pStatement != null )
			try {
				pStatement.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} 
        if( conn != null )
			try {
				conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} 
    } //finally

} // main method.

			
	private static void init() throws Exception
	{

		logger.info(">>> init method...");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			props = new Properties();
			props.load(new FileInputStream("./config/application.properties"));
			logger.info(props);
			conn = getDBConnection();

			PCList = props.getProperty("IPM.PCList").split(",");
			EPList = props.getProperty("IPM.EPList").split(",");
			
			//load data from IPMRouting table.
			String query = "select * from IPMRouting with (nolock)";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			IPMRoutingProps = new Properties();
			
			while(rs != null && rs.next()) {
				
				IPMRoutingProps.put(rs.getString(1), rs.getString(2));
			}
			
			logger.info("IPMRoutingProps: "+IPMRoutingProps);
	
			logger.info("<< init method...");
		}
		finally {
			
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
		}
	}
	
	public static Connection getDBConnection()throws IPMGenericException{
    	Connection con = null;
    	try {
    	 DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
    	 con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+props.getProperty("IPM.Server")+"/"+props.getProperty("IPM.databaseName")+";sendStringParametersAsUnicode=false", props.getProperty("IPM.userName"), props.getProperty("IPM.passWord"));
    	 logger.info("Connection Establish"+con);
    	} catch (SQLException sqle) {
    		logger.info("Not Able to make connection to Database: "+props.getProperty("IPM.databaseName")+": "+sqle.getMessage());
			logger.fatal("Not Able to make connection to: Database"+sqle.getMessage());
			sqle.printStackTrace();
    	}
    	return con;
    }

	private static void initDynamicVariables(String fileName) throws Exception { //called for every IPM File.

		logger.info(">>> initDynamicVariables");
		lhm = new LinkedHashMap<String, Integer>(); //For each file it will be instantiated.

		String BINs = "";
		String GCBINs = "";
		String VMSBINs = "";

		for(String EP : EPList) {
			if(fileName.indexOf(EP) != -1) { 
				
				GCBINs = props.getProperty("IPM.BINList.GC."+EP);
				VMSBINs = props.getProperty("IPM.BINList.VMS."+EP);

				if(GCBINs != null) {
					
					BINs = GCBINs;
				}
				if(VMSBINs != null) {
					
					if(BINs.length() > 0) {
						
						BINs = BINs + "," + VMSBINs;
					}
					else {
						
						BINs = VMSBINs;
					}
				}

				logger.info("GCBINs::"+GCBINs);
				logger.info("VMSBINs::"+VMSBINs);
				logger.info("BINs::"+BINs);
				
				BINList = BINs.split(",");

				break;
			}
		}
		
		for(String BIN : BINList) {
			lhm.put("Count_"+BIN, new Integer(0));
			lhm.put("ReconAmount_"+BIN, new Integer(0));
			lhm.put("CBAmount_"+BIN, new Integer(0));
			lhm.put("FeeAmountTotal_"+BIN, new Integer(0));
			lhm.put("ReconFeeAmountTotal_"+BIN, new Integer(0));
			for(String PC : PCList) {
				
				lhm.put("Amount_"+BIN+"_"+PC, new Integer(0));
				lhm.put("ReconAmount_"+BIN+"_"+PC, new Integer(0));
				lhm.put("FeeAmount_"+BIN+"_"+PC, new Integer(0));
				lhm.put("ReconFeeAmount_"+BIN+"_"+PC, new Integer(0));
			}
		}
		
		logger.info(lhm);
		logger.info("<<< initDynamicVariables");
	}
	
	private static void buildReportData(LinkedHashMap<String, Integer> lhm, StringBuffer GCReportData, StringBuffer VMSReportData) throws Exception {
		
		logger.info(">>> buildReportData");

			int binCount = 0;
			int CBAmount = 0;
			int reconAmount = 0;
			int CCA = 0;
			int feeAmountTotal = 0;
			int reconFeeAmountTotal = 0;
			
			int Amount_By_PC = 0;
			int ReconAmount_By_PC = 0;
			int FeeAmount_By_PC = 0;
			int ReconFeeAmount_By_PC = 0;
			
			for(String BIN : BINList) {
				logger.info("BIN::"+BIN);
				binCount = (Integer)lhm.get("Count_"+BIN);
				CBAmount = (Integer)lhm.get("CBAmount_"+BIN);
				reconAmount = (Integer)lhm.get("ReconAmount_"+BIN);
				CCA = reconAmount - CBAmount;

				feeAmountTotal = (Integer)lhm.get("FeeAmountTotal_"+BIN);
				reconFeeAmountTotal = (Integer)lhm.get("ReconFeeAmountTotal_"+BIN);
				
				String hostSystem = (String)IPMRoutingProps.get(BIN);
				logger.info("hostSystem::"+hostSystem);
				if(hostSystem != null && hostSystem.equalsIgnoreCase("Greencard")) {
//					GCReportData.append(BIN).append(",").append(binCount).append(",").append(CBAmount).append(",").append(reconAmount).append(",").append(CCA).append(",");
					GCReportData.append(BIN).append(",").append(binCount).append(",").append(CBAmount).append(",").append(reconAmount).append(",").append(CCA).append(",").append(feeAmountTotal).append(",").append(reconFeeAmountTotal).append(",");
				}
				else if(hostSystem != null && hostSystem.equalsIgnoreCase("VMS")) {
//					VMSReportData.append(BIN).append(",").append(binCount).append(",").append(CBAmount).append(",").append(reconAmount).append(",").append(CCA).append(",");
					VMSReportData.append(BIN).append(",").append(binCount).append(",").append(CBAmount).append(",").append(reconAmount).append(",").append(CCA).append(",").append(feeAmountTotal).append(",").append(reconFeeAmountTotal).append(",");
				}

				for(String PC : PCList) {
					
					Amount_By_PC = (Integer)lhm.get("Amount_"+BIN+"_"+PC);
					ReconAmount_By_PC = (Integer)lhm.get("ReconAmount_"+BIN+"_"+PC);
					FeeAmount_By_PC = (Integer)lhm.get("FeeAmount_"+BIN+"_"+PC);
					ReconFeeAmount_By_PC = (Integer)lhm.get("ReconFeeAmount_"+BIN+"_"+PC);

					if(hostSystem != null && hostSystem.equalsIgnoreCase("Greencard")) {

						if(PC.startsWith("2"))
							GCReportData.append(-Amount_By_PC).append(",").append(-ReconAmount_By_PC).append(",").append(-FeeAmount_By_PC).append(",").append(-ReconFeeAmount_By_PC).append(",");
						else 
							GCReportData.append(Amount_By_PC).append(",").append(ReconAmount_By_PC).append(",").append(FeeAmount_By_PC).append(",").append(ReconFeeAmount_By_PC).append(",");
					}
					else if(hostSystem != null && hostSystem.equalsIgnoreCase("VMS")) {
						if(PC.startsWith("2"))
							VMSReportData.append(-Amount_By_PC).append(",").append(-ReconAmount_By_PC).append(",").append(-FeeAmount_By_PC).append(",").append(-ReconFeeAmount_By_PC).append(",");
						else
							VMSReportData.append(Amount_By_PC).append(",").append(ReconAmount_By_PC).append(",").append(FeeAmount_By_PC).append(",").append(ReconFeeAmount_By_PC).append(",");
					}
					
				}
				if(hostSystem != null && hostSystem.equalsIgnoreCase("Greencard")) {
					GCReportData.setLength(GCReportData.length()-1);
					GCReportData.append("\n");
					logger.debug(GCReportData.toString());
				}
				else if(hostSystem != null && hostSystem.equalsIgnoreCase("VMS")) {
					VMSReportData.setLength(VMSReportData.length()-1);
					VMSReportData.append("\n");
					logger.debug(VMSReportData.toString());
				}
				
			} //for BIN in BINList
			
		logger.info("<<< buildReportData");
	}

	private static void generateReports(String reportHeader, StringBuffer GCReportData, StringBuffer VMSReportData, String reportFolder, String reportFile) throws Exception {
		
		logger.info(">>> generateReports");
		
		BufferedWriter bw1 = null;
		BufferedWriter bw2 = null;
		
		try {
			
			if(GCReportData.length() > 0) {
				bw1 = new BufferedWriter(new FileWriter(new File(reportFolder+"/"+reportFile+"_ByBIN_GC.csv")));
				bw1.write(reportHeader);
				bw1.write(GCReportData.toString());
				bw1.flush();
				bw1.close();
			}
			if(VMSReportData.length() > 0) {
				bw2 = new BufferedWriter(new FileWriter(new File(reportFolder+"/"+reportFile+"_ByBIN_VMS.csv")));
				bw2.write(reportHeader);
				bw2.write(VMSReportData.toString());
				bw2.flush();
				bw2.close();
			}
		}
		finally {
			
			if(bw1 != null)
				bw1.close();
			if(bw2 != null)
				bw2.close();
		}
		logger.info("<<< generateReports");

	} //generateReports

} // class



