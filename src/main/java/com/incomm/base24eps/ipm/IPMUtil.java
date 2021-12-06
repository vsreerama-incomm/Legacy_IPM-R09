package com.incomm.base24eps.ipm;

import java.util.Map;
import java.util.SortedMap;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jpos.iso.ISOUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * * @author sduggirala
 * 08-01-2017: Modified Code to read IDS 0159, 0146, 0025 from DE48 using fieldLength and tagID logic to parse tokens.
 * GC-5567
 */
public class IPMUtil {
	
	private static final String ACCOUNTNUMBER = "2";
	private static final String TRACK1 = "45";
	private static final String TRACK2 = "35";
	private static final String TRACK3 = "36";
	private static final String RESERVED_61 = "61";
	private static final String OLS_CVV_TAG = "008";
	static Logger logger = Logger.getLogger("IPMUtil.class");
	private static final String SQL_DuplicateFIle_Chk = "select FileHash from IPMFileStatus where FileHash = ?";
	
	public static SortedMap<String, String> parseTokens(String parseStr, int fieldLength, int tagLength) {
        SortedMap<String, String> tagMap = new TreeMap<String, String>();
        int startIndex = tagLength;
        int i = 0;
        for (i = 0; i < parseStr.length(); i++) {
            int curTagLength = Integer.parseInt(parseStr.substring(startIndex,
                        startIndex + fieldLength));
            tagMap.put(parseStr.substring(startIndex - tagLength, startIndex),
                parseStr.substring(startIndex + fieldLength,
                    startIndex + fieldLength + curTagLength));
           startIndex = startIndex + fieldLength + curTagLength + tagLength;
            i = startIndex;
        }

        return tagMap;
    }

	public static String maskCardNumberTrackData(Map message) {
    	
		String maskedMessage = null;
       	String primaryAcctNum=null;
    	String maskedPAN=null;
    	SortedMap<String,String> DE_61_TAG_INFO = null;
    	String cvv2 = null;
    	try{
	        	if(message!=null && !message.isEmpty()){
	        		
	        		maskedMessage = message.toString();
	        		
	        		if(message.containsKey(RESERVED_61)){
	        			DE_61_TAG_INFO = parseTokens(String.valueOf(message.get(RESERVED_61)), 3, 3);
	        	  	  }
	        		if(message.containsKey(TRACK1)){
	        			maskedMessage = StringUtils.replace(maskedMessage,message.get(TRACK1).toString(),"*****");
	        		}
	        		if(!StringUtils.isEmpty(maskedMessage) && message.containsKey(TRACK2)){
	        			maskedMessage = StringUtils.replace(maskedMessage, message.get(TRACK2).toString(),"*****");
	        		}
	        		if(!StringUtils.isEmpty(maskedMessage) && message.containsKey(TRACK3)){
	        			maskedMessage = StringUtils.replace(maskedMessage, message.get(TRACK3).toString(),"*****");
	        		}
	        		if(DE_61_TAG_INFO!=null && !DE_61_TAG_INFO.isEmpty()){
		 	        	   // Check if CVV2 tag is present
		 	        	   if (DE_61_TAG_INFO.containsKey(OLS_CVV_TAG)){
		 	        		   cvv2=DE_61_TAG_INFO.get(OLS_CVV_TAG).toString();
		 	        		   maskedMessage = StringUtils.replace(maskedMessage, "008003"+cvv2,"008003***");
		 	        	   }
	        		}
	        		if(!StringUtils.isEmpty(maskedMessage) && message.containsKey(ACCOUNTNUMBER)){
	        			primaryAcctNum = message.get(ACCOUNTNUMBER).toString();
	        			//maskedPAN = maskText(primaryAcctNum, 6, 12, "*");
	        			//maskedMessage = maskedMessage.replaceAll(primaryAcctNum, maskedPAN);
	        		}
	     
	        	}
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	     return maskedMessage;
	    }
	    
	
	public static String maskText(String message, int startIndex, int endIndex,
	        String maskStr) {
	        String replacementStr = "";

	        for (int i = startIndex; i < endIndex; i++) {
	            replacementStr += maskStr;
	        }

	        return StringUtils.replace(message,
	            message.substring(startIndex, endIndex), replacementStr);
	    }
	//Njalagam:01-31-2019:GCP-177 IPM poller - file hash to prevent duplicate data loads
	public static String getSHA512Checksum(String filename) throws Exception {
    	logger.info(">>> getSHA512Checksum");
        byte[] buffer = new byte[512];

        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));) {
            int count;
            while ((count = in.read(buffer)) >= 0) {
                digest.update(buffer, 0, count);
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
          //  throw (ex);
        }
        logger.info("<<< getSHA512Checksum");
        return ISOUtil.hexString(digest.digest());
    }
	//Njalagam:01-31-2019:GCP-177 IPM poller - file hash to prevent duplicate data loads
    public static boolean isDuplicateFile (String filaHash, Connection conn) {
    	logger.info(">>> isDuplicateFile");
		ResultSet record = null;
		PreparedStatement ps = null;
		boolean isDuplicateFile = false;
		
		try {
			//conn = getConnection();
			ps = conn.prepareStatement(SQL_DuplicateFIle_Chk);
			ps.setString(1, filaHash);
			record = ps.executeQuery();
			if (record.next()) {

				isDuplicateFile = true;
			}
			record.close();
			ps.close();

		} catch (SQLException e) {

			logger.error("Error while executing fileHash check SQL ", e);

		} finally {
			try{
			if(record!=null){record.close();}
			if(ps!=null){ps.close();}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("<<< isDuplicateFile");
		return isDuplicateFile;
		
	}
	
}
