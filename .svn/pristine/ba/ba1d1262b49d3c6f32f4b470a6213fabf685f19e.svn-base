package com.incomm.base24eps.ipm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import com.incomm.base24eps.ipm.IPMGenericException;

import org.apache.log4j.Logger;

/**
 * 
 * @author schahanapally
 * @author sduggirala
 * 02-23-2016: Added DE-61 for Bridge2PRM Change. Sending this tag, GC will respond with CardID.
 * 08-01-2017: Modified Code to read IDS 0159, 0146, 0025 from DE48 using fieldLength and tagID logic to parse tokens.
 *
 */
public class EPSBatchAuthorization {

	SimpleDateFormat sdt 	= new SimpleDateFormat("yyyyMMddHHmm");
	String strdate			= sdt.format(Calendar.getInstance().getTime());
	
	static Logger logger = Logger.getLogger(EPSBatchAuthorization.class);
	
	int hh=10, mm=10, ss=10;

	public  int executeFileProcessStartIns(String fileName,String SourceDesc,String FileStatusDesc,String FileHeader,String FileParseError,String ReportGeneratedStatus,String BinReportGeneratedStatus,int recordCount,long lastModifiedTime,int fileId, Connection conn,String fileHash)
	throws IPMGenericException 
	{
		
		CallableStatement cs = null;
		int FileID = 0;
		logger.info(">>> usp_IPMFileStatus_ins Started");
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		//logger.info("formatter new Date(lastModifiedTime) = "+formatter.format(new Date(lastModifiedTime)));
		ResultSet rs = null;
		try 
		{
			cs = conn.prepareCall(
            "{call usp_IPMFileStatus_ins(?,?,?,?,?,?,?,?,?,?,?)}");		
			cs.setString(1,SourceDesc);
			cs.setString(2,fileName);
			cs.setString(3,FileStatusDesc);
			cs.setString(4,FileHeader);	
			cs.setString(5,FileParseError);
			cs.setString(6,ReportGeneratedStatus);
			cs.setString(7,BinReportGeneratedStatus);
			cs.setInt(8,recordCount);
			cs.setString(9, formatter.format(new Date(lastModifiedTime)));
			cs.setInt(10, fileId);
			cs.setString(11, fileHash);
			logger.info("calling usp_IPMFileStatus_ins");			 
			cs.executeQuery();	
			rs = cs.getResultSet();
			if (rs == null)
				logger.info(" rs is null");
			if (rs != null && rs.next()) {
				FileID = rs.getInt(1);				
				 logger.info(" FileID = "+FileID);
			}					
		} 
		catch (SQLException sqle)
		{
			IPMGenericException oe = new IPMGenericException("Error in executing the Stored Procedure gcsp_IPMFileStatus_ins: "+sqle.getMessage());
			
			StringWriter stackTrace = new StringWriter();
			sqle.printStackTrace(new PrintWriter(stackTrace));
			logger.fatal(stackTrace.toString());
			
			throw oe;
		}
		finally 
		{
            if( cs != null )
				try {
					cs.close();
				} catch (SQLException sqle) {
					IPMGenericException oe = new IPMGenericException("Error in Closing the CS in Stored Procedure gcsp_IPMFileStatus_ins: "+sqle.getMessage());
					
					StringWriter stackTrace = new StringWriter();
					sqle.printStackTrace(new PrintWriter(stackTrace));
					logger.fatal(stackTrace.toString());

					throw oe;
				}            
        }	
		logger.info("<<< usp_IPMFileStatus_ins Completed");
		return FileID;
	}
	
	public static String getHostName(String bin, Connection conn)
	throws IPMGenericException 
	{
		
		CallableStatement cs = null;
		String hostName = "";
		logger.info(">>> usp_IPMHostName Started::"+bin);
		ResultSet rs = null;
		try 
		{
			cs = conn.prepareCall(
            "{call usp_IPMHostName(?)}");		
			cs.setString(1,bin);
			cs.executeQuery();	
			rs = cs.getResultSet();
			if (rs == null)
				logger.info(" rs is null=");
			if (rs != null && (rs).next()) {
				hostName = rs.getString(1);				
				 logger.info(" hostName = "+hostName);
			}					
		} 
		catch (SQLException sqle)
		{
			IPMGenericException oe = new IPMGenericException("Error in executing the Stored Procedure gcsp_IPMHostName: "+sqle.getMessage());
			logger.fatal("Error in executing the Stored Procedure gcsp_IPMHostName:"+sqle.getMessage());
			throw oe;
		}
		finally 
		{
            if( cs != null )
				try {
					cs.close();
				} catch (SQLException sqle) {
					IPMGenericException oe = new IPMGenericException("Error in Closing the CS in Stored Procedure gcsp_IPMHostName: "+sqle.getMessage());
					logger.fatal("Error in Closing the CS in Stored Procedure gcsp_IPMHostName:"+sqle.getMessage());
					throw oe;
				}            
        }	
		logger.info(">>> usp_IPMHostName Completed");
		return hostName;
	}
	
	public static String getAttribute(String attributeKey, Connection conn)
	throws IPMGenericException 
	{
		
		CallableStatement cs = null;
		String attributeValue = "";
		logger.info(">>> usp_IPMAttributeValue Started::"+attributeKey);
		ResultSet rs = null;
		try 
		{
			cs = conn.prepareCall(
            "{call usp_IPMAttributeValue(?)}");		
			cs.setString(1,attributeKey);
			cs.executeQuery();	
			rs = cs.getResultSet();
			if (rs == null)
				logger.info(" rs is null");
			if (rs != null && rs.next()) {
				attributeValue = rs.getString(1);				
				 logger.info(" attributeValue = "+attributeValue);
			}					
		} 
		catch (SQLException sqle)
		{
			IPMGenericException oe = new IPMGenericException("Error in executing the Stored Procedure usp_IPMAttributeValue: "+sqle.getMessage());
			logger.fatal("Error in executing the Stored Procedure usp_IPMAttributeValue:"+sqle.getMessage());
			throw oe;
		}
		finally 
		{
            if( cs != null )
				try {
					cs.close();
				} catch (SQLException sqle) {
					IPMGenericException oe = new IPMGenericException("Error in Closing the CS in Stored Procedure usp_IPMAttributeValue: "+sqle.getMessage());
					logger.fatal("Error in Closing the CS in Stored Procedure usp_IPMAttributeValue:"+sqle.getMessage());
					throw oe;
				}            
        }	
		logger.info("<<< usp_IPMAttributeValue Completed");
		return attributeValue;
	}
	
	public String generateHisoRecord(Map isoRespMap, String dummyNumber) throws IPMGenericException
	{
		String MTI = "1220"; //CSV
		StringBuffer sb = new StringBuffer();
		StringBuffer csvReportBuffer = new StringBuffer();
		
		Random random = new Random();

		/**
		 * BITMAP: DE1,2,3,4,5,6,12,15,17,23,24,26,32,33,37,39,40,41,42,43,46,49,50,51,54,57
		 * 			59,62,93,94,100,102,123
		 */
		//String BITMAP	= "FC1283418BE4E4A40000000C14000020"; //turned 46 on.
//		String BITMAP = "FE72A7418BE4E4240000000C14000020";
		String BITMAP = "FE72A7418BE4E42C0000000C14000020"; //DE61 is turned on for Bridge2PRM
		try{											 


			sb.append(MTI);
			sb.append(BITMAP);
			
			//DE2
			String PAN = "";
			String PANLen = "16";
			PAN		= isoRespMap.get("2").toString();
			if (dummyNumber!=null)
			{
				if (dummyNumber.length()==16)
				{
					PAN = dummyNumber;
				}
			}
			//NJoshi - Mask PAN
			//logger.info("PAN : " + IPMUtil.maskText(PAN, 6, 12, "*")); //CSV
			sb.append(PANLen).append(PAN);

			//DE3
			String ProcessingCode = "";
			ProcessingCode	= isoRespMap.get("3").toString();
			logger.info("ProcessingCode : " + ProcessingCode);
			if(ProcessingCode.equals("180000")) {
				ProcessingCode = "000000";
				logger.info("New Processing Code: " + ProcessingCode);
			} 
			sb.append(ProcessingCode); //CSV

			//DE4
			String Amount = "";
			Amount			= isoRespMap.get("4").toString();
			logger.info("Amount : "+Amount);
			sb.append(Amount); //CSV
			
			//DE5
			String AmountReconciliation = "000000000000"; //N12
			if(isoRespMap.get("5") != null) {
				AmountReconciliation = isoRespMap.get("5").toString();
			}
			logger.info("AmountReconciliation : "+AmountReconciliation);
			sb.append(AmountReconciliation); //CSV
			
			//DE6
			String AmountCardholderBilling = "000000000000";
			if(isoRespMap.get("6") != null) {
				AmountCardholderBilling = isoRespMap.get("6").toString();
			}
			logger.info("AmountCardholderBilling : "+AmountCardholderBilling);
			sb.append(AmountCardholderBilling); //CSV
			
			
			//DE12
			String DateTimeLocalTXN = "";
			DateTimeLocalTXN = isoRespMap.get("12").toString();
			logger.info("DateTimeLocalTXN : "+DateTimeLocalTXN);
			
			//If time part empty
			StringBuffer timeString = new StringBuffer();
			if(DateTimeLocalTXN.substring(6, DateTimeLocalTXN.length()).equals("000000")) {
				if(ss > 59) {
					mm++;
					ss = 10;
				}
				if(mm > 59) {
					hh++;
					mm = 10;
				}
				if(hh > 23) {
					hh = 10;
					mm = 10;
					ss = 10;
				}
				//timeString = ""+hh+""+mm+""+ss;
				timeString.append(hh).append(mm).append(ss);
				ss++;
				
				DateTimeLocalTXN = DateTimeLocalTXN.substring(0, 6)+timeString.toString();
				logger.info("DateTimeLocalTXN : "+DateTimeLocalTXN);
			}
			
			//DE-7:Transmission Date and Time
			//Format: N 10 (MMDDhhmmss)
			sb.append(DateTimeLocalTXN.substring(2, DateTimeLocalTXN.length()));
			
			//De-10 Conversion Rate, Cardholder Billing - Hardcoding as of now. will change if needed
			String conversionRate = "61000000";
			sb.append(conversionRate);
			
			//DE-11: Systems Trace Audit Number
			//Format: N 6
			String traceNumber = "";
			String auditTraceNum = "";
			int randomTraceNumber = random.nextInt(999999);
			if(randomTraceNumber < 0) {
				randomTraceNumber = Math.abs(randomTraceNumber);
			}
			int randomTraceNumberLen = new Integer(randomTraceNumber).toString().length();
			if(randomTraceNumberLen < 6) {
				for(int j=0; j<6-randomTraceNumberLen; j++) {
					traceNumber += "0";
				}
			}
			auditTraceNum = traceNumber + new Integer(randomTraceNumber).toString();

			logger.info("auditTraceNum : " + auditTraceNum);
			sb.append(auditTraceNum);
			
			
			//DE-12: Date and Time, Local Transaction
			//Format: N 12 (YYMMDDhhmmss)
			sb.append(DateTimeLocalTXN); //CSV
			
			//DE15 and Cycle Number getting from DE48 - Private Data.
			String settlementDate = "      ";//6 spaces.
			String cycleNumber = "";
			logger.info("DE48 : " + isoRespMap.get("48").toString());
			String parseDE48String = isoRespMap.get("48").toString();
			//Mithra Release 17.07 GC-5567
			SortedMap<String, String> dataElement48Map = IPMUtil.parseTokens(parseDE48String, 3,4);
			if(dataElement48Map.containsKey("0159") && dataElement48Map.get("0159")!=null){
				    logger.info("IDS159="+ dataElement48Map.get("0159").toString());
					cycleNumber = dataElement48Map.get("0159").substring(57, 59);
					settlementDate = dataElement48Map.get("0159").substring(59, 65);
					logger.info("settlement date : " + settlementDate);
					logger.info("cycle number : " + cycleNumber);
					sb.append(settlementDate); //CSV
						
					//DE17. Date, Capture N 4 (MMDD) This is required to satisfy EPS.
					String dateCapture = "    "; //Required to satisfy EPS.				
					dateCapture = settlementDate.substring(2);
					logger.info("dateCapture : " + dateCapture);
					sb.append(dateCapture);	
			}//end tag 0159 from DE48 Mithra 
			//commented the below code. Sreedhar Chahanapally - 01-12-2015
/*			
			//DE-19 Country Code, Acquiring Institution N3
			int pds146Indexs = isoRespMap.get("48").toString().indexOf("0146");
			String countryCode ="000"; //modified by Shree on 12/19/2014. previous value was 840.
			
			if(pds146Indexs != -1)	{
				String AmountFee = isoRespMap.get("48").toString().substring(pds146Indexs+4+3, pds146Indexs+4+3+36); //last number hard coded to 36 because 
				countryCode = AmountFee.substring(6, 9);
				logger.info("countryCode : " + countryCode);				
			}
*/			
			//DE19 is being set to DE-49 if it is not present. Sreedhar Chahanapally - 01-12-2015
			String countryCode = "";
			if(isoRespMap.get("19") != null) {
				countryCode = isoRespMap.get("19").toString();
				logger.info("country code present: "+countryCode);
			}
			else {
				countryCode = isoRespMap.get("49").toString();
			}
			sb.append(countryCode);
			
			//DE22.Point of Service Data Code AN12  - Not required. EPS will populate this element.
			//Sreedhar Chahanapally - 01-12-2015. Reading it from the IPM File. If it is not present then send the default value.
			String PointOfServiceDataCode = "000101000001";
			if(isoRespMap.get("22") != null) {
				PointOfServiceDataCode = isoRespMap.get("22").toString();
			}
			sb.append(PointOfServiceDataCode);//DE22

			//DE23. Card Sequence Number N3 This is required to satisfy EPS.
			String cardSeqNum = "000"; sb.append(cardSeqNum);
			
			//DE24 Function Code, 201 = Previously approved authorization – amount same
			String FunctionType		= "201";
			sb.append(FunctionType);
			
			//DE26 Card Acceptor Business Code N4 --CSV MCC
			String CardAcceptorBusinessCode		= "";
			CardAcceptorBusinessCode		= isoRespMap.get("26").toString();
			logger.info("CardAcceptorBusinessCode : " + CardAcceptorBusinessCode);
			sb.append(CardAcceptorBusinessCode);

			//DE31 from IPM. Will get appended to Transport Data (DE 59) of EPS
			String AcqRefData = isoRespMap.get("31").toString();
			logger.info("AcqRefData : " + AcqRefData);
			
			//DE32(Acquirer Institution ID Code) IS OPTIONAL SO READ IT FROM DE94 WHICH IS ALWAYS PRESENT.
			//DE94 WILL ALWAYS MATCH DE32.
			String AcqInstIDCode = "           ";//11 spaces.
			String AcqInstIDCodeLen = "11";

			if(isoRespMap.get("94") != null) {
				AcqInstIDCode		= isoRespMap.get("94").toString();
				int len = AcqInstIDCode.length();
				if( len < 10) {
					AcqInstIDCodeLen = "0"+len;
				}
				else {
					AcqInstIDCodeLen = ""+len;
				}
			}
			logger.info("AcqInstIDCode : " + AcqInstIDCode);
			sb.append(AcqInstIDCodeLen).append(AcqInstIDCode);
			
			//DE33 Forwarding Institution ID Code LLVAR N ..11
			String ForwardingInstIDCode = "           ";//11 spaces.
			String ForwardingInstIDCodeLen = "11";
			if(isoRespMap.get("33") != null) {
				ForwardingInstIDCode = isoRespMap.get("33").toString();
				int len = ForwardingInstIDCode.length();
				if(len < 10) {
					ForwardingInstIDCodeLen = "0"+len;
				}
				else {
					ForwardingInstIDCodeLen = ""+len;
				}
			}
			logger.info("ForwardingInstIDCode : " + ForwardingInstIDCode);
			sb.append(ForwardingInstIDCodeLen).append(ForwardingInstIDCode);
			
			//DE37 Retrieval Reference Number ANP 12
			String rrnPrefix = "";
			String RetrievalRefNum = "";

			int randomNumber = random.nextInt();
			if(randomNumber < 0) {
				randomNumber = Math.abs(randomNumber);
			}
			int randomNumberLen = new Integer(randomNumber).toString().length();
			if(randomNumberLen < 12) {
				for(int j=0; j<12-randomNumberLen; j++) {
					rrnPrefix += "0";
				}
			}
			RetrievalRefNum = rrnPrefix + new Integer(randomNumber).toString();

			logger.info("Random RRN : " + RetrievalRefNum);
			sb.append(RetrievalRefNum);
			
			//DE39. Action Code N3 This is to satisfy EPS.
			String actionCode = "000"; sb.append(actionCode);
			
			//DE40 Service Code N3
			String ServiceCode = "000";
			if(isoRespMap.get("40") != null) {
				ServiceCode = isoRespMap.get("40").toString();
			}
			logger.info("ServiceCode : " + ServiceCode);
			sb.append(ServiceCode);
			
			//DE41 Card Acceptor Terminal Identification ANS16 //CSV
			String CardAccptTermID = "                ";//16 spaces
			if(isoRespMap.get("41") != null) {
				CardAccptTermID = isoRespMap.get("41").toString();
				CardAccptTermID = "00000000"+CardAccptTermID;
			} else {
				CardAccptTermID = "FSIT"+RetrievalRefNum;
			}
			logger.info("CardAccptTermID : " + CardAccptTermID);
			sb.append(CardAccptTermID);

			//DE42 Card Acceptor Identification Code ANS15 --Merchant ID CSV
			String CardAccptIDCode = "               ";//15 spaces
			if(isoRespMap.get("42") != null) {
				CardAccptIDCode = isoRespMap.get("42").toString();
				//to fix comma issue in merchant id - 07/31/2015
				CardAccptIDCode = CardAccptIDCode.replaceAll(",", " ");
			}
			logger.info("CardAccptIDCode : " + CardAccptIDCode);
			sb.append(CardAccptIDCode);

			//DE43 Card Acceptor Name/Location LLVAR ANS ..99
			String CardAccptNameLoc = "";
			String CardAccptNameLocLen = "";

			CardAccptNameLoc = isoRespMap.get("43").toString();
			
			
			//replace " with a hyphen(-)
			CardAccptNameLoc = CardAccptNameLoc.replace('"', '-');
			//replace ' with a hyphen(-)
			CardAccptNameLoc = CardAccptNameLoc.replace("'", "-");
			//replace / with a hyphen(-)
			CardAccptNameLoc = CardAccptNameLoc.replace('/', '-');
			//replace , with a hyphen(-)
			CardAccptNameLoc = CardAccptNameLoc.replace(',', '-');
			
			//replace \\ with \\ \\ and increase the length by 1
			
			if(CardAccptNameLoc.indexOf("\\\\") > 0) {
				CardAccptNameLoc = CardAccptNameLoc.replace("\\\\", "\\ \\");
				//++len;
				//CardAccptNameLocLen = ""+len;
			}
			
			//replace \ with \\ We need to confirm this with Production
			//CardAccptNameLoc = CardAccptNameLoc.replace("\\", "\\\\");
			
			int len = CardAccptNameLoc.length();
			if(len < 10) {
				CardAccptNameLocLen = "0"+len;
			}
			else {
				CardAccptNameLocLen = ""+len;
			}
			
			logger.info("CardAccptNameLoc : " + CardAccptNameLoc);
			sb.append(CardAccptNameLocLen).append(CardAccptNameLoc);


			//DS-46 Amount, Fees LLLVAR ANS ..204 PDS0146 taken from DE48.
			String AmountFees = "";
			String AmountFeesLen = "000";
			
			String AmountFees_SF1 = "";
			String AmountFees_SF2 = "";
			String AmountFees_SF3 = "";
			String AmountFees_SF4 = "";
			String AmountFees_SF5 = "";
			String AmountFees_SF6 = "";
			String AmountFees_SF7 = "";
			//Mithra Release 17.07 GC-5567
			if(dataElement48Map.containsKey("0146") && dataElement48Map.get("0146")!=null){
				logger.info("IDS146="+ dataElement48Map.get("0146"));
				AmountFees= dataElement48Map.get("0146");
				
					//But AmountFeesLen is being hard coded to 034 because we need only first Fees which is 34 long.
					AmountFeesLen = "034"; //Base24-EPS Amount Fees length.
					//AmountFees = isoRespMap.get("48").toString().substring(pds146Index+4+3, pds146Index+4+3+36); //last number hard coded to 36 because 
																												// there was only one fees all the time.
					//Now read all the sub fields.
					AmountFees_SF1 = AmountFees.substring(0, 2);
					AmountFees_SF2 = AmountFees.substring(2, 4);
					AmountFees_SF3 = AmountFees.substring(4, 6);
					AmountFees_SF4 = AmountFees.substring(6, 9);
					AmountFees_SF5 = AmountFees.substring(9, 21);
					AmountFees_SF6 = AmountFees.substring(21, 24);
					AmountFees_SF7 = AmountFees.substring(24, 36);
					
					logger.info("AmountFees_SF1 = " + AmountFees_SF1);
					logger.info("AmountFees_SF2 = " + AmountFees_SF2);
					logger.info("AmountFees_SF3 = " + AmountFees_SF3);
					logger.info("AmountFees_SF4 = " + AmountFees_SF4);
					logger.info("AmountFees_SF5 = " + AmountFees_SF5);
					logger.info("AmountFees_SF6 = " + AmountFees_SF6);
					logger.info("AmountFees_SF7 = " + AmountFees_SF7);
					
					String CreditDebit = "";
					//Populate Batch Amount Fee sub fields.
					String BatchFee_SF1 = AmountFees_SF1;
					String BatchFee_SF2 = AmountFees_SF4; //From SF4
					String BatchFee_SF3 = "";
					
					if(AmountFees_SF2.equals("19"))
						CreditDebit = "D";
					else //equals 29
						CreditDebit = "C";
	
					BatchFee_SF3 = CreditDebit + AmountFees_SF5.substring(4, AmountFees_SF5.length()); //From SF5
					String BatchFee_SF4 = AmountFees_SF5.substring(4, AmountFees_SF5.length());
					String BatchFee_SF5 = CreditDebit + AmountFees_SF7.substring(4, AmountFees_SF7.length());
					String BatchFee_SF6 = AmountFees_SF6;
	
					//New Batch style amount fees
					AmountFees = BatchFee_SF1+BatchFee_SF2+BatchFee_SF3+BatchFee_SF4+BatchFee_SF5+BatchFee_SF6;
					logger.info("Batch Amount Fees = " + AmountFees);
					
				//}
				sb.append(AmountFeesLen).append(AmountFees); 
			}//end if DataElement 48 tag 0146 	
			//DE49 Currency Code,Transaction A 3 or N 3 //CSV
			String CurrCodeTxn = "";
			CurrCodeTxn = isoRespMap.get("49").toString();
			logger.info("CurrCodeTxn : " + CurrCodeTxn);
			sb.append(CurrCodeTxn);
			
			//DE50 Currency Code, Reconciliation A 3 or N 3 //CSV
			String CurrCodeRecon = "000";
			if(isoRespMap.get("50") != null) {
				CurrCodeRecon = isoRespMap.get("50").toString();
			}
			logger.info("CurrCodeRecon : " + CurrCodeRecon);
			sb.append(CurrCodeRecon);

			//DE51 Currency Code, Cardholder Billing A 3 or N 3 //CSV
			String CurrCodeCardholderBilling = "000";
			if(isoRespMap.get("51") != null) {
				CurrCodeCardholderBilling = isoRespMap.get("51").toString();
			}
			logger.info("CurrCodeCardholderBilling : " + CurrCodeCardholderBilling);
			sb.append(CurrCodeCardholderBilling);

			//DE54 Amounts, Additional LLLVAR ANS ..120
			String AmountsAdditional = "";
			String AmountsAdditionalLen = "000";
			if(isoRespMap.get("54") != null) {
				AmountsAdditional = isoRespMap.get("54").toString();
				len = AmountsAdditional.length();
				if(len < 10) {
					AmountsAdditionalLen = "00"+len;
				}
				else {
					AmountsAdditionalLen = "0"+len;
				}
			}
			logger.info("AmountsAdditional : " + AmountsAdditional);
			sb.append(AmountsAdditionalLen).append(AmountsAdditional);

			//To check if this is a reversal TXN.
			boolean isReversal = false;
		    //Mithra Release 17.07 GC-5567
			logger.info("Reached 0025 if check in Data element 48");
			//int pdsIndex25 = isoRespMap.get("48").toString().lastIndexOf("002500");
			if((dataElement48Map.containsKey("0025") && dataElement48Map.get("0025")!=null)) {
				try {
					logger.info("0025 tag is not empty");
					String reversalIndicator =  dataElement48Map.get("0025").substring(0, 1);
					logger.info("reversalIndicator = " + reversalIndicator);
					if(reversalIndicator.equalsIgnoreCase("R")) {
						isReversal = true;
					}
				} catch(StringIndexOutOfBoundsException SOBException) {
					logger.warn(SOBException.getMessage());
				}
			}

			//DE59(Transport Data) will be populated by DE63 -- TXN Life Cycle ID also called Banknet Ref Num.
			//Appending ACQ Ref Data also to this field.
			String TransportDataLen = "037";
			String TransportData = "             ";//13 spaces.
			if(isoRespMap.get("63") != null) {
				TransportData = isoRespMap.get("63").toString().trim();
				int TDLen = TransportData.length();
				if(TDLen < 13) {
					String TDPrefix = "";
					for(int j=0; j<(13-TDLen); j++){
						TDPrefix += "0";
					}
					TransportData = TDPrefix + TransportData;
				} else if(TDLen > 13) {
					TransportData = TransportData.substring(0, 13);
				}
			}
			//CSV
			String TransportDataO=TransportData;
			if(isReversal) {
				TransportData = new StringBuffer(TransportData).append(AcqRefData).toString()+"R";
			} else {
				TransportData = new StringBuffer(TransportData).append(AcqRefData).toString()+"S";
			}
			
			logger.info("TransportData plus AcqRefData plus reversal indicator: " + TransportDataLen+TransportData);
			
			sb.append(TransportDataLen).append(TransportData);
			
			//DE-61. IFA_LLLCHAR(999, "Reserved for national use"),
			//Acquirer FIID. HardCoded to a value of PRO1. GC will now respond back with TAG-049 (CardID) which can be sent to PRM.
			//Adding tag 008 - cvv as 000 just to satisfy GreenCard. CVV validation is never performed on 1220s.
			//Tag008 was added to fix the pulse CVV validation issue happened on 04/04/2016.
			sb.append("019005004PRO1008003111");
			
			//DE-62 (Primary Reserved Private) This is required to satisfy EPS. DE62
			//String reservedDataPrivate = "006230013";
			String reservedDataPrivate = "10907001208002MD1600600000017016INTFBAUT        20024INTFBAUT            PRO121024PT01                PRO1230013";
			sb.append(reservedDataPrivate);//DE62
			logger.info("reservedDataPrivate62 : " + reservedDataPrivate);
			//DE93 (Transaction Destination Institution	Identification Code)
			String TxnDestInstIDCode = "";
			String TxnDestInstIDCodeLen = "";
			TxnDestInstIDCode = isoRespMap.get("93").toString();
			len = TxnDestInstIDCode.length();
			if(len < 10) {
				TxnDestInstIDCodeLen = "0"+len;
			}
			else {
				TxnDestInstIDCodeLen = ""+len;
			}
			logger.info("TxnDestInstIDCode : " + TxnDestInstIDCode);
			sb.append(TxnDestInstIDCodeLen).append(TxnDestInstIDCode);

			//DE94
			String TxnOrigInstIDCode = "";
			String TxnOrigInstIDCodeLen = "";
			TxnOrigInstIDCode = isoRespMap.get("94").toString();
			len = TxnOrigInstIDCode.length();
			if(len < 10) {
				TxnOrigInstIDCodeLen = "0"+len;
			}
			else {
				TxnOrigInstIDCodeLen = ""+len;
			}
			logger.info("TxnOrigInstIDCode : " + TxnOrigInstIDCode);
			sb.append(TxnOrigInstIDCodeLen).append(TxnOrigInstIDCode);

			//DE100 (Receiving Institution Identification Code)
			String ReceivingInstIDCode = "";
			String ReceivingInstIDCodeLen = "";
			ReceivingInstIDCode = isoRespMap.get("100").toString();
			len = ReceivingInstIDCode.length();
			if(len < 10) {
				ReceivingInstIDCodeLen = "0"+len;
			}
			else {
				ReceivingInstIDCodeLen = ""+len;
			}
			logger.info("ReceivingInstIDCode : " + ReceivingInstIDCode);
			sb.append(ReceivingInstIDCodeLen).append(ReceivingInstIDCode);

			//DE102 (Account Identification 1). This is required to satisfy EPS.
			String accountID1 = "16"+PAN; sb.append(accountID1);

			
			if(cycleNumber.equals("")) {
				cycleNumber = "000"; //This is an error condition.
			} else {
				cycleNumber = "0" + cycleNumber; //make it 3 digit
			}
			
			//DE123 (Reserved for Private Use). This is required to satisfy EPS.
			String DE123 = "00803003"+cycleNumber; sb.append(DE123);

			if(isReversal){
				sb.append("R");
			}else { 
				sb.append("S");
			}

			//Add in Buffer to create the csv report
//			csvReportBuffer.append(MTI).append(",").append("'").append(PAN).append("'").append(",").append("'").append(ProcessingCode).append("'").append(",").append(Amount).append(",").append(AmountReconciliation).append(",").append(AmountCardholderBilling).append(",").append(CurrCodeTxn).append(",").append(CurrCodeRecon).append(",").append(CurrCodeCardholderBilling).append(",").append("'").append(DateTimeLocalTXN).append("'").append(",").append(settlementDate).append(",").append(CardAcceptorBusinessCode).append(",").append("'").append(CardAccptIDCode).append("'").append(",").append("'").append(CardAccptTermID).append("'").append(",").append("'").append(TransportDataO).append("'").append(",").append("'").append(CardAccptNameLoc).append("'").append(",").append(AmountFees).append("\n");
			
			//appended POSDataCode, AcqRefData, AcqInstIDCode before AmountFees. - 02/02/2015
			csvReportBuffer.append(MTI).append(",").append("'").append(PAN).append("'").append(",").append("'").append(ProcessingCode).append("'").append(",").append(Amount).append(",").append(AmountReconciliation).append(",").append(AmountCardholderBilling).append(",").append(CurrCodeTxn).append(",").append(CurrCodeRecon).append(",").append(CurrCodeCardholderBilling).append(",").append("'").append(DateTimeLocalTXN).append("'").append(",").append(settlementDate).append(",").append(CardAcceptorBusinessCode).append(",").append("'").append(CardAccptIDCode).append("'").append(",").append("'").append(CardAccptTermID).append("'").append(",").append("'").append(TransportDataO).append("'").append(",").append("'").append(CardAccptNameLoc).append("'").append(",").append("'").append(PointOfServiceDataCode).append("'").append(",").append("'").append(AcqRefData).append("'").append(",").append("'").append(AcqInstIDCode).append("'").append(",").append("'").append(AmountFees_SF5).append("'").append(",").append(AmountFees).append("\n");
			
			sb.append(";");
			sb.append(csvReportBuffer);
			
		}		
		catch(Exception e ) {
			IPMGenericException oe = new IPMGenericException("Exception in generating the HISO message: "+e.getMessage());
//			logger.error("Error in Closing the CS in Stored Procedure gcsp_IPMFileStatus_ins:"+e.getMessage());

			StringWriter stackTrace = new StringWriter();
			e.printStackTrace(new PrintWriter(stackTrace));
			logger.fatal(stackTrace.toString());
			
			throw oe;
		}
		return sb.toString();

	} //generateHisoRecord
	
} //class
