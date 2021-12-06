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
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ReportByBin {

	static Logger logger = Logger.getLogger("ReportByBin.class");

	//Application properties.
	private static Properties props;
	static Connection conn = null;

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
			
			if (rs != null)
		    {
				while (rs.next()) //for each ipm file.
				{
					 FileID = rs.getInt(1);	
					 fileName = rs.getString(2);
					 logger.info(" FileID = "+FileID+" fileName = "+fileName);
					 
					 CallableStatement pStatementReport = null;
					 StringBuffer csvBuffer = new StringBuffer();
					 StringBuffer csvBufferVMS = new StringBuffer();
					 StringBuffer targetBuffer = new StringBuffer();
					 
					 ResultSet rsReport = null;
					 String reportString = "";

					int count_534771 = 0;
					int count_515462 = 0;
					int count_521444 = 0;
					int count_515480 = 0;
					int count_511340 = 0;
					int count_531150 = 0;
					int count_533313 = 0;
					int count_526293 = 0;
					int count_511479 = 0; //added on 07/24/2013
					int count_516488 = 0; //added on 07/24/2013
					int count_526249 = 0;
					int count_530688 = 0;
					int count_534785 = 0;
					int count_528627 = 0;
					int count_538933 = 0;
					int count_526291 = 0;
					int count_511096 = 0;
					int count_511186 = 0; //added on 07/24/2013
					int count_521412 = 0; //added on 07/24/2013
					int count_525698 = 0; //added on 07/24/2013
					int count_510842 = 0;
					int count_511233 = 0;
					int count_512856 = 0;
					int count_512864 = 0;
					int count_516612 = 0;
					int count_517628 = 0;
					int count_519106 = 0;
					int count_521961 = 0;
					int count_524432 = 0;
					int count_531688 = 0;
					int count_542850 = 0;
					int count_516511 = 0; //added on 05/12/2015
					int count_531244 = 0; //added on 07/31/2015. Galileo to GC. CAN
					int count_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//amount is calculated per process code.
					int amount_534771_160100 = 0;
					int amount_515462_160100 = 0;
					int amount_521444_160100 = 0;
					int amount_515480_160100 = 0;
					int amount_511340_160100 = 0;
					int amount_531150_160100 = 0;
					int amount_533313_160100 = 0;
					int amount_526293_160100 = 0;
					int amount_511479_160100 = 0; //added on 07/24/2013
					int amount_516488_160100 = 0; //added on 07/24/2013
					int amount_526249_160100 = 0;
					int amount_530688_160100 = 0;
					int amount_534785_160100 = 0;
					int amount_528627_160100 = 0;
					int amount_538933_160100 = 0;
					int amount_526291_160100 = 0;
					int amount_511096_160100 = 0;
					int amount_511186_160100 = 0; //added on 07/24/2013
					int amount_521412_160100 = 0; //added on 07/24/2013
					int amount_525698_160100 = 0; //added on 07/24/2013
					int amount_510842_160100 = 0;
					int amount_511233_160100 = 0;
					int amount_512856_160100 = 0;
					int amount_512864_160100 = 0;
					int amount_516612_160100 = 0;
					int amount_517628_160100 = 0;
					int amount_519106_160100 = 0;
					int amount_521961_160100 = 0;
					int amount_524432_160100 = 0;
					int amount_531688_160100 = 0;
					int amount_542850_160100 = 0;
					int amount_516511_160100 = 0; //added on 05/12/2015
					int amount_531244_160100 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_160100 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int amount_534771_200000 = 0;
					int amount_515462_200000 = 0;
					int amount_521444_200000 = 0;
					int amount_515480_200000 = 0;
					int amount_511340_200000 = 0;
					int amount_531150_200000 = 0;
					int amount_533313_200000 = 0;
					int amount_526293_200000 = 0;
					int amount_511479_200000 = 0; //added on 07/24/2013
					int amount_516488_200000 = 0; //added on 07/24/2013
					int amount_526249_200000 = 0;
					int amount_530688_200000 = 0;
					int amount_534785_200000 = 0;
					int amount_528627_200000 = 0;
					int amount_538933_200000 = 0;
					int amount_526291_200000 = 0;
					int amount_511096_200000 = 0;
					int amount_511186_200000 = 0;
					int amount_521412_200000 = 0;
					int amount_525698_200000 = 0;
					int amount_510842_200000 = 0;
					int amount_511233_200000 = 0;
					int amount_512856_200000 = 0;
					int amount_512864_200000 = 0;
					int amount_516612_200000 = 0;
					int amount_517628_200000 = 0;
					int amount_519106_200000 = 0;
					int amount_521961_200000 = 0;
					int amount_524432_200000 = 0;
					int amount_531688_200000 = 0;
					int amount_542850_200000 = 0;
					int amount_516511_200000 = 0; //added on 05/12/2015
					int amount_531244_200000 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_200000 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int amount_534771_003000 = 0;
					int amount_515462_003000 = 0;
					int amount_521444_003000 = 0;
					int amount_515480_003000 = 0;
					int amount_511340_003000 = 0;
					int amount_531150_003000 = 0;
					int amount_533313_003000 = 0;
					int amount_526293_003000 = 0;
					int amount_511479_003000 = 0; //added on 07/24/2013
					int amount_516488_003000 = 0; //added on 07/24/2013
					int amount_526249_003000 = 0;
					int amount_530688_003000 = 0;
					int amount_534785_003000 = 0;
					int amount_528627_003000 = 0;
					int amount_538933_003000 = 0;
					int amount_526291_003000 = 0;
					int amount_511096_003000 = 0;
					int amount_511186_003000 = 0;
					int amount_521412_003000 = 0;
					int amount_525698_003000 = 0;
					int amount_510842_003000 = 0;
					int amount_511233_003000 = 0;
					int amount_512856_003000 = 0;
					int amount_512864_003000 = 0;
					int amount_516612_003000 = 0;
					int amount_517628_003000 = 0;
					int amount_519106_003000 = 0;
					int amount_521961_003000 = 0;
					int amount_524432_003000 = 0;
					int amount_531688_003000 = 0;
					int amount_542850_003000 = 0;
					int amount_516511_003000 = 0; //added on 05/12/2015
					int amount_531244_003000 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_003000 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int amount_534771_013000 = 0;
					int amount_515462_013000 = 0;
					int amount_521444_013000 = 0;
					int amount_515480_013000 = 0;
					int amount_511340_013000 = 0;
					int amount_531150_013000 = 0;
					int amount_533313_013000 = 0;
					int amount_526293_013000 = 0;
					int amount_511479_013000 = 0; 
					int amount_516488_013000 = 0; 
					int amount_526249_013000 = 0;
					int amount_530688_013000 = 0;
					int amount_534785_013000 = 0;
					int amount_528627_013000 = 0;
					int amount_538933_013000 = 0;
					int amount_526291_013000 = 0;
					int amount_511096_013000 = 0;
					int amount_511186_013000 = 0;
					int amount_521412_013000 = 0;
					int amount_525698_013000 = 0;
					int amount_510842_013000 = 0;
					int amount_511233_013000 = 0;
					int amount_512856_013000 = 0;
					int amount_512864_013000 = 0;
					int amount_516612_013000 = 0;
					int amount_517628_013000 = 0;
					int amount_519106_013000 = 0;
					int amount_521961_013000 = 0;
					int amount_524432_013000 = 0;
					int amount_531688_013000 = 0;
					int amount_542850_013000 = 0;
					int amount_516511_013000 = 0; 
					int amount_531244_013000 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_013000 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int amount_534771_120000 = 0;
					int amount_515462_120000 = 0;
					int amount_521444_120000 = 0;
					int amount_515480_120000 = 0;
					int amount_511340_120000 = 0;
					int amount_531150_120000 = 0;
					int amount_533313_120000 = 0;
					int amount_526293_120000 = 0;
					int amount_511479_120000 = 0; 
					int amount_516488_120000 = 0; 
					int amount_526249_120000 = 0;
					int amount_530688_120000 = 0;
					int amount_534785_120000 = 0;
					int amount_528627_120000 = 0;
					int amount_538933_120000 = 0;
					int amount_526291_120000 = 0;
					int amount_511096_120000 = 0;
					int amount_511186_120000 = 0;
					int amount_521412_120000 = 0;
					int amount_525698_120000 = 0;
					int amount_510842_120000 = 0;
					int amount_511233_120000 = 0;
					int amount_512856_120000 = 0;
					int amount_512864_120000 = 0;
					int amount_516612_120000 = 0;
					int amount_517628_120000 = 0;
					int amount_519106_120000 = 0;
					int amount_521961_120000 = 0;
					int amount_524432_120000 = 0;
					int amount_531688_120000 = 0;
					int amount_542850_120000 = 0;
					int amount_516511_120000 = 0; 
					int amount_531244_120000 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_120000 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/14/2015 for VMS.
					int amount_534771_010000 = 0;
					int amount_515462_010000 = 0;
					int amount_521444_010000 = 0;
					int amount_515480_010000 = 0;
					int amount_511340_010000 = 0;
					int amount_531150_010000 = 0;
					int amount_533313_010000 = 0;
					int amount_526293_010000 = 0;
					int amount_511479_010000 = 0; 
					int amount_516488_010000 = 0; 
					int amount_526249_010000 = 0;
					int amount_530688_010000 = 0;
					int amount_534785_010000 = 0;
					int amount_528627_010000 = 0;
					int amount_538933_010000 = 0;
					int amount_526291_010000 = 0;
					int amount_511096_010000 = 0;
					int amount_511186_010000 = 0;
					int amount_521412_010000 = 0;
					int amount_525698_010000 = 0;
					int amount_510842_010000 = 0;
					int amount_511233_010000 = 0;
					int amount_512856_010000 = 0;
					int amount_512864_010000 = 0;
					int amount_516612_010000 = 0;
					int amount_517628_010000 = 0;
					int amount_519106_010000 = 0;
					int amount_521961_010000 = 0;
					int amount_524432_010000 = 0;
					int amount_531688_010000 = 0;
					int amount_542850_010000 = 0;
					int amount_516511_010000 = 0; 
					int amount_531244_010000 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_010000 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int amount_534771_012000 = 0;
					int amount_515462_012000 = 0;
					int amount_521444_012000 = 0;
					int amount_515480_012000 = 0;
					int amount_511340_012000 = 0;
					int amount_531150_012000 = 0;
					int amount_533313_012000 = 0;
					int amount_526293_012000 = 0;
					int amount_511479_012000 = 0; 
					int amount_516488_012000 = 0; 
					int amount_526249_012000 = 0;
					int amount_530688_012000 = 0;
					int amount_534785_012000 = 0;
					int amount_528627_012000 = 0;
					int amount_538933_012000 = 0;
					int amount_526291_012000 = 0;
					int amount_511096_012000 = 0;
					int amount_511186_012000 = 0;
					int amount_521412_012000 = 0;
					int amount_525698_012000 = 0;
					int amount_510842_012000 = 0;
					int amount_511233_012000 = 0;
					int amount_512856_012000 = 0;
					int amount_512864_012000 = 0;
					int amount_516612_012000 = 0;
					int amount_517628_012000 = 0;
					int amount_519106_012000 = 0;
					int amount_521961_012000 = 0;
					int amount_524432_012000 = 0;
					int amount_531688_012000 = 0;
					int amount_542850_012000 = 0;
					int amount_516511_012000 = 0; 
					int amount_531244_012000 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_012000 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 07/31/2015 for new process code - 011000
					int amount_534771_011000 = 0;
					int amount_515462_011000 = 0;
					int amount_521444_011000 = 0;
					int amount_515480_011000 = 0;
					int amount_511340_011000 = 0;
					int amount_531150_011000 = 0;
					int amount_533313_011000 = 0;
					int amount_526293_011000 = 0;
					int amount_511479_011000 = 0; 
					int amount_516488_011000 = 0; 
					int amount_526249_011000 = 0;
					int amount_530688_011000 = 0;
					int amount_534785_011000 = 0;
					int amount_528627_011000 = 0;
					int amount_538933_011000 = 0;
					int amount_526291_011000 = 0;
					int amount_511096_011000 = 0;
					int amount_511186_011000 = 0;
					int amount_521412_011000 = 0;
					int amount_525698_011000 = 0;
					int amount_510842_011000 = 0;
					int amount_511233_011000 = 0;
					int amount_512856_011000 = 0;
					int amount_512864_011000 = 0;
					int amount_516612_011000 = 0;
					int amount_517628_011000 = 0;
					int amount_519106_011000 = 0;
					int amount_521961_011000 = 0;
					int amount_524432_011000 = 0;
					int amount_531688_011000 = 0;
					int amount_542850_011000 = 0;
					int amount_516511_011000 = 0; 
					int amount_531244_011000 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_011000 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 07/31/2015 for new process code - 280000
					int amount_534771_280000 = 0;
					int amount_515462_280000 = 0;
					int amount_521444_280000 = 0;
					int amount_515480_280000 = 0;
					int amount_511340_280000 = 0;
					int amount_531150_280000 = 0;
					int amount_533313_280000 = 0;
					int amount_526293_280000 = 0;
					int amount_511479_280000 = 0; 
					int amount_516488_280000 = 0; 
					int amount_526249_280000 = 0;
					int amount_530688_280000 = 0;
					int amount_534785_280000 = 0;
					int amount_528627_280000 = 0;
					int amount_538933_280000 = 0;
					int amount_526291_280000 = 0;
					int amount_511096_280000 = 0;
					int amount_511186_280000 = 0;
					int amount_521412_280000 = 0;
					int amount_525698_280000 = 0;
					int amount_510842_280000 = 0;
					int amount_511233_280000 = 0;
					int amount_512856_280000 = 0;
					int amount_512864_280000 = 0;
					int amount_516612_280000 = 0;
					int amount_517628_280000 = 0;
					int amount_519106_280000 = 0;
					int amount_521961_280000 = 0;
					int amount_524432_280000 = 0;
					int amount_531688_280000 = 0;
					int amount_542850_280000 = 0;
					int amount_516511_280000 = 0; 
					int amount_531244_280000 = 0; //added on 07/31/2015. Galileo to GC
					int amount_543276_280000 = 0; //added on 08/05/2015. One vanilla MC. US
					
					
					//CCA = Recon_amount - CB_amount
					//CCA is not calculated per process code but at BIN level.
					int CCA_amount_534771 = 0;
					int CCA_amount_515462 = 0;
					int CCA_amount_521444 = 0;
					int CCA_amount_515480 = 0;
					int CCA_amount_511340 = 0;
					int CCA_amount_531150 = 0;
					int CCA_amount_533313 = 0;
					int CCA_amount_526293 = 0;
					int CCA_amount_511479 = 0; //added on 07/24/2013
					int CCA_amount_516488 = 0; //added on 07/24/2013
					int CCA_amount_526249 = 0;
					int CCA_amount_530688 = 0;
					int CCA_amount_534785 = 0;
					int CCA_amount_528627 = 0;
					int CCA_amount_538933 = 0;
					int CCA_amount_526291 = 0;
					int CCA_amount_511096 = 0;
					int CCA_amount_511186 = 0;
					int CCA_amount_521412 = 0;
					int CCA_amount_525698 = 0;
					int CCA_amount_510842 = 0;
					int CCA_amount_511233 = 0;
					int CCA_amount_512856 = 0;
					int CCA_amount_512864 = 0;
					int CCA_amount_516612 = 0;
					int CCA_amount_517628 = 0;
					int CCA_amount_519106 = 0;
					int CCA_amount_521961 = 0;
					int CCA_amount_524432 = 0;
					int CCA_amount_531688 = 0;
					int CCA_amount_542850 = 0;
					int CCA_amount_516511 = 0; //added on 05/12/2015
					int CCA_amount_531244 = 0; //added on 07/31/2015. Galileo to GC
					int CCA_amount_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int CB_amount_534771 = 0;
					int CB_amount_515462 = 0;
					int CB_amount_521444 = 0;
					int CB_amount_515480 = 0;
					int CB_amount_511340 = 0;
					int CB_amount_531150 = 0;
					int CB_amount_533313 = 0;
					int CB_amount_526293 = 0;
					int CB_amount_511479 = 0; //added on 07/24/2013
					int CB_amount_516488 = 0; //added on 07/24/2013
					int CB_amount_526249 = 0;
					int CB_amount_530688 = 0;
					int CB_amount_534785 = 0;
					int CB_amount_528627 = 0;
					int CB_amount_538933 = 0;
					int CB_amount_526291 = 0;
					int CB_amount_511096 = 0;
					int CB_amount_511186 = 0;
					int CB_amount_521412 = 0;
					int CB_amount_525698 = 0;
					int CB_amount_510842 = 0;
					int CB_amount_511233 = 0;
					int CB_amount_512856 = 0;
					int CB_amount_512864 = 0;
					int CB_amount_516612 = 0;
					int CB_amount_517628 = 0;
					int CB_amount_519106 = 0;
					int CB_amount_521961 = 0;
					int CB_amount_524432 = 0;
					int CB_amount_531688 = 0;
					int CB_amount_542850 = 0;
					int CB_amount_516511 = 0; //added on 05/12/2015
					int CB_amount_531244 = 0; //added on 07/31/2015. Galileo to GC
					int CB_amount_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int Recon_amount_534771 = 0;
					int Recon_amount_515462 = 0;
					int Recon_amount_521444 = 0;
					int Recon_amount_515480 = 0;
					int Recon_amount_511340 = 0;
					int Recon_amount_531150 = 0;
					int Recon_amount_533313 = 0;
					int Recon_amount_526293 = 0;
					int Recon_amount_511479 = 0; //added on 07/24/2013
					int Recon_amount_516488 = 0; //added on 07/24/2013
					int Recon_amount_526249 = 0;
					int Recon_amount_530688 = 0;
					int Recon_amount_534785 = 0;
					int Recon_amount_528627 = 0;
					int Recon_amount_538933 = 0;
					int Recon_amount_526291 = 0;
					int Recon_amount_511096 = 0;
					int Recon_amount_511186 = 0;
					int Recon_amount_521412 = 0;
					int Recon_amount_525698 = 0;
					int Recon_amount_510842 = 0;
					int Recon_amount_511233 = 0;
					int Recon_amount_512856 = 0;
					int Recon_amount_512864 = 0;
					int Recon_amount_516612 = 0;
					int Recon_amount_517628 = 0;
					int Recon_amount_519106 = 0;
					int Recon_amount_521961 = 0;
					int Recon_amount_524432 = 0;
					int Recon_amount_531688 = 0;
					int Recon_amount_542850 = 0;
					int Recon_amount_516511 = 0; //added on 05/12/2015
					int Recon_amount_531244 = 0; //added on 07/31/2015. Galileo to GC
					int Recon_amount_543276 = 0; //added on 08/05/2015. One vanilla MC. US
					
					
					//Fee_amount and Recon_Fee_amount is calculated per Process Code.
					int Fee_amount_160100_534771 = 0;
					int Fee_amount_160100_515462 = 0;
					int Fee_amount_160100_521444 = 0;
					int Fee_amount_160100_515480 = 0;
					int Fee_amount_160100_511340 = 0;
					int Fee_amount_160100_531150 = 0;
					int Fee_amount_160100_533313 = 0;
					int Fee_amount_160100_526293 = 0;
					int Fee_amount_160100_511479 = 0; //added on 07/24/2013
					int Fee_amount_160100_516488 = 0; //added on 07/24/2013
					int Fee_amount_160100_526249 = 0;
					int Fee_amount_160100_530688 = 0;
					int Fee_amount_160100_534785 = 0;
					int Fee_amount_160100_528627 = 0;
					int Fee_amount_160100_538933 = 0;
					int Fee_amount_160100_526291 = 0;
					int Fee_amount_160100_511096 = 0;
					int Fee_amount_160100_511186 = 0;
					int Fee_amount_160100_521412 = 0;
					int Fee_amount_160100_525698 = 0;
					int Fee_amount_160100_510842 = 0;
					int Fee_amount_160100_511233 = 0;
					int Fee_amount_160100_512856 = 0;
					int Fee_amount_160100_512864 = 0;
					int Fee_amount_160100_516612 = 0;
					int Fee_amount_160100_517628 = 0;
					int Fee_amount_160100_519106 = 0;
					int Fee_amount_160100_521961 = 0;
					int Fee_amount_160100_524432 = 0;
					int Fee_amount_160100_531688 = 0;
					int Fee_amount_160100_542850 = 0;
					int Fee_amount_160100_516511 = 0; //added on 05/12/2015
					int Fee_amount_160100_531244 = 0; //added on 07/31/2015. Galileo to GC
					int Fee_amount_160100_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int Recon_Fee_amount_160100_534771 = 0;
					int Recon_Fee_amount_160100_515462 = 0;
					int Recon_Fee_amount_160100_521444 = 0;
					int Recon_Fee_amount_160100_515480 = 0;
					int Recon_Fee_amount_160100_511340 = 0;
					int Recon_Fee_amount_160100_531150 = 0;
					int Recon_Fee_amount_160100_533313 = 0;
					int Recon_Fee_amount_160100_526293 = 0;
					int Recon_Fee_amount_160100_511479 = 0; //added on 07/24/2013
					int Recon_Fee_amount_160100_516488 = 0; //added on 07/24/2013
					int Recon_Fee_amount_160100_526249 = 0;
					int Recon_Fee_amount_160100_530688 = 0;
					int Recon_Fee_amount_160100_534785 = 0;
					int Recon_Fee_amount_160100_528627 = 0;
					int Recon_Fee_amount_160100_538933 = 0;
					int Recon_Fee_amount_160100_526291 = 0;
					int Recon_Fee_amount_160100_511096 = 0;
					int Recon_Fee_amount_160100_511186 = 0;
					int Recon_Fee_amount_160100_521412 = 0;
					int Recon_Fee_amount_160100_525698 = 0;
					int Recon_Fee_amount_160100_510842 = 0;
					int Recon_Fee_amount_160100_511233 = 0;
					int Recon_Fee_amount_160100_512856 = 0;
					int Recon_Fee_amount_160100_512864 = 0;
					int Recon_Fee_amount_160100_516612 = 0;
					int Recon_Fee_amount_160100_517628 = 0;
					int Recon_Fee_amount_160100_519106 = 0;
					int Recon_Fee_amount_160100_521961 = 0;
					int Recon_Fee_amount_160100_524432 = 0;
					int Recon_Fee_amount_160100_531688 = 0;
					int Recon_Fee_amount_160100_542850 = 0;
					int Recon_Fee_amount_160100_516511 = 0; //added on 05/12/2015
					int Recon_Fee_amount_160100_531244 = 0; //added on 07/31/2015. Galileo to GC
					int Recon_Fee_amount_160100_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int Fee_amount_200000_534771 = 0;
					int Fee_amount_200000_515462 = 0;
					int Fee_amount_200000_521444 = 0;
					int Fee_amount_200000_515480 = 0;
					int Fee_amount_200000_511340 = 0;
					int Fee_amount_200000_531150 = 0;
					int Fee_amount_200000_533313 = 0;
					int Fee_amount_200000_526293 = 0;
					int Fee_amount_200000_511479 = 0; //added on 07/24/2013
					int Fee_amount_200000_516488 = 0; //added on 07/24/2013
					int Fee_amount_200000_526249 = 0;
					int Fee_amount_200000_530688 = 0;
					int Fee_amount_200000_534785 = 0;
					int Fee_amount_200000_528627 = 0;
					int Fee_amount_200000_538933 = 0;
					int Fee_amount_200000_526291 = 0;
					int Fee_amount_200000_511096 = 0;
					int Fee_amount_200000_511186 = 0;
					int Fee_amount_200000_521412 = 0;
					int Fee_amount_200000_525698 = 0;
					int Fee_amount_200000_510842 = 0;
					int Fee_amount_200000_511233 = 0;
					int Fee_amount_200000_512856 = 0;
					int Fee_amount_200000_512864 = 0;
					int Fee_amount_200000_516612 = 0;
					int Fee_amount_200000_517628 = 0;
					int Fee_amount_200000_519106 = 0;
					int Fee_amount_200000_521961 = 0;
					int Fee_amount_200000_524432 = 0;
					int Fee_amount_200000_531688 = 0;
					int Fee_amount_200000_542850 = 0;
					int Fee_amount_200000_516511 = 0; //added on 05/12/2015
					int Fee_amount_200000_531244 = 0; //added on 07/31/2015. Galileo to GC
					int Fee_amount_200000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int Recon_Fee_amount_200000_534771 = 0;
					int Recon_Fee_amount_200000_515462 = 0;
					int Recon_Fee_amount_200000_521444 = 0;
					int Recon_Fee_amount_200000_515480 = 0;
					int Recon_Fee_amount_200000_511340 = 0;
					int Recon_Fee_amount_200000_531150 = 0;
					int Recon_Fee_amount_200000_533313 = 0;
					int Recon_Fee_amount_200000_526293 = 0;
					int Recon_Fee_amount_200000_511479 = 0; //added on 07/24/2013
					int Recon_Fee_amount_200000_516488 = 0; //added on 07/24/2013
					int Recon_Fee_amount_200000_526249 = 0;
					int Recon_Fee_amount_200000_530688 = 0;
					int Recon_Fee_amount_200000_534785 = 0;
					int Recon_Fee_amount_200000_528627 = 0;
					int Recon_Fee_amount_200000_538933 = 0;
					int Recon_Fee_amount_200000_526291 = 0;
					int Recon_Fee_amount_200000_511096 = 0;
					int Recon_Fee_amount_200000_511186 = 0;
					int Recon_Fee_amount_200000_521412 = 0;
					int Recon_Fee_amount_200000_525698 = 0;
					int Recon_Fee_amount_200000_510842 = 0;
					int Recon_Fee_amount_200000_511233 = 0;
					int Recon_Fee_amount_200000_512856 = 0;
					int Recon_Fee_amount_200000_512864 = 0;
					int Recon_Fee_amount_200000_516612 = 0;
					int Recon_Fee_amount_200000_517628 = 0;
					int Recon_Fee_amount_200000_519106 = 0;
					int Recon_Fee_amount_200000_521961 = 0;
					int Recon_Fee_amount_200000_524432 = 0;
					int Recon_Fee_amount_200000_531688 = 0;
					int Recon_Fee_amount_200000_542850 = 0;
					int Recon_Fee_amount_200000_516511 = 0; //added on 05/12/2015
					int Recon_Fee_amount_200000_531244 = 0; //added on 07/31/2015. Galileo to GC
					int Recon_Fee_amount_200000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int Fee_amount_003000_534771 = 0;
					int Fee_amount_003000_515462 = 0;
					int Fee_amount_003000_521444 = 0;
					int Fee_amount_003000_515480 = 0;
					int Fee_amount_003000_511340 = 0;
					int Fee_amount_003000_531150 = 0;
					int Fee_amount_003000_533313 = 0;
					int Fee_amount_003000_526293 = 0;
					int Fee_amount_003000_511479 = 0; //added on 07/24/2013
					int Fee_amount_003000_516488 = 0; //added on 07/24/2013
					int Fee_amount_003000_526249 = 0;
					int Fee_amount_003000_530688 = 0;
					int Fee_amount_003000_534785 = 0;
					int Fee_amount_003000_528627 = 0;
					int Fee_amount_003000_538933 = 0;
					int Fee_amount_003000_526291 = 0;
					int Fee_amount_003000_511096 = 0;
					int Fee_amount_003000_511186 = 0;
					int Fee_amount_003000_521412 = 0;
					int Fee_amount_003000_525698 = 0;
					int Fee_amount_003000_510842 = 0;
					int Fee_amount_003000_511233 = 0;
					int Fee_amount_003000_512856 = 0;
					int Fee_amount_003000_512864 = 0;
					int Fee_amount_003000_516612 = 0;
					int Fee_amount_003000_517628 = 0;
					int Fee_amount_003000_519106 = 0;
					int Fee_amount_003000_521961 = 0;
					int Fee_amount_003000_524432 = 0;
					int Fee_amount_003000_531688 = 0;
					int Fee_amount_003000_542850 = 0;
					int Fee_amount_003000_516511 = 0; //added on 05/12/2015
					int Fee_amount_003000_531244 = 0; //added on 05/12/2015
					int Fee_amount_003000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					int Recon_Fee_amount_003000_534771 = 0;
					int Recon_Fee_amount_003000_515462 = 0;
					int Recon_Fee_amount_003000_521444 = 0;
					int Recon_Fee_amount_003000_515480 = 0;
					int Recon_Fee_amount_003000_511340 = 0;
					int Recon_Fee_amount_003000_531150 = 0;
					int Recon_Fee_amount_003000_533313 = 0;
					int Recon_Fee_amount_003000_526293 = 0;
					int Recon_Fee_amount_003000_511479 = 0; //added on 07/24/2013
					int Recon_Fee_amount_003000_516488 = 0; //added on 07/24/2013
					int Recon_Fee_amount_003000_526249 = 0;
					int Recon_Fee_amount_003000_530688 = 0;
					int Recon_Fee_amount_003000_534785 = 0;
					int Recon_Fee_amount_003000_528627 = 0;
					int Recon_Fee_amount_003000_538933 = 0;
					int Recon_Fee_amount_003000_526291 = 0;
					int Recon_Fee_amount_003000_511096 = 0;
					int Recon_Fee_amount_003000_511186 = 0;
					int Recon_Fee_amount_003000_521412 = 0;
					int Recon_Fee_amount_003000_525698 = 0;
					int Recon_Fee_amount_003000_510842 = 0;
					int Recon_Fee_amount_003000_511233 = 0;
					int Recon_Fee_amount_003000_512856 = 0;
					int Recon_Fee_amount_003000_512864 = 0;
					int Recon_Fee_amount_003000_516612 = 0;
					int Recon_Fee_amount_003000_517628 = 0;
					int Recon_Fee_amount_003000_519106 = 0;
					int Recon_Fee_amount_003000_521961 = 0;
					int Recon_Fee_amount_003000_524432 = 0;
					int Recon_Fee_amount_003000_531688 = 0;
					int Recon_Fee_amount_003000_542850 = 0;
					int Recon_Fee_amount_003000_516511 = 0; //added on 05/12/2015
					int Recon_Fee_amount_003000_531244 = 0; //added on 05/12/2015
					int Recon_Fee_amount_003000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int Fee_amount_013000_534771 = 0;
					int Fee_amount_013000_515462 = 0;
					int Fee_amount_013000_521444 = 0;
					int Fee_amount_013000_515480 = 0;
					int Fee_amount_013000_511340 = 0;
					int Fee_amount_013000_531150 = 0;
					int Fee_amount_013000_533313 = 0;
					int Fee_amount_013000_526293 = 0;
					int Fee_amount_013000_511479 = 0; 
					int Fee_amount_013000_516488 = 0; 
					int Fee_amount_013000_526249 = 0;
					int Fee_amount_013000_530688 = 0;
					int Fee_amount_013000_534785 = 0;
					int Fee_amount_013000_528627 = 0;
					int Fee_amount_013000_538933 = 0;
					int Fee_amount_013000_526291 = 0;
					int Fee_amount_013000_511096 = 0;
					int Fee_amount_013000_511186 = 0;
					int Fee_amount_013000_521412 = 0;
					int Fee_amount_013000_525698 = 0;
					int Fee_amount_013000_510842 = 0;
					int Fee_amount_013000_511233 = 0;
					int Fee_amount_013000_512856 = 0;
					int Fee_amount_013000_512864 = 0;
					int Fee_amount_013000_516612 = 0;
					int Fee_amount_013000_517628 = 0;
					int Fee_amount_013000_519106 = 0;
					int Fee_amount_013000_521961 = 0;
					int Fee_amount_013000_524432 = 0;
					int Fee_amount_013000_531688 = 0;
					int Fee_amount_013000_542850 = 0;
					int Fee_amount_013000_516511 = 0; 
					int Fee_amount_013000_531244 = 0; 
					int Fee_amount_013000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int Recon_Fee_amount_013000_534771 = 0;
					int Recon_Fee_amount_013000_515462 = 0;
					int Recon_Fee_amount_013000_521444 = 0;
					int Recon_Fee_amount_013000_515480 = 0;
					int Recon_Fee_amount_013000_511340 = 0;
					int Recon_Fee_amount_013000_531150 = 0;
					int Recon_Fee_amount_013000_533313 = 0;
					int Recon_Fee_amount_013000_526293 = 0;
					int Recon_Fee_amount_013000_511479 = 0; 
					int Recon_Fee_amount_013000_516488 = 0; 
					int Recon_Fee_amount_013000_526249 = 0;
					int Recon_Fee_amount_013000_530688 = 0;
					int Recon_Fee_amount_013000_534785 = 0;
					int Recon_Fee_amount_013000_528627 = 0;
					int Recon_Fee_amount_013000_538933 = 0;
					int Recon_Fee_amount_013000_526291 = 0;
					int Recon_Fee_amount_013000_511096 = 0;
					int Recon_Fee_amount_013000_511186 = 0;
					int Recon_Fee_amount_013000_521412 = 0;
					int Recon_Fee_amount_013000_525698 = 0;
					int Recon_Fee_amount_013000_510842 = 0;
					int Recon_Fee_amount_013000_511233 = 0;
					int Recon_Fee_amount_013000_512856 = 0;
					int Recon_Fee_amount_013000_512864 = 0;
					int Recon_Fee_amount_013000_516612 = 0;
					int Recon_Fee_amount_013000_517628 = 0;
					int Recon_Fee_amount_013000_519106 = 0;
					int Recon_Fee_amount_013000_521961 = 0;
					int Recon_Fee_amount_013000_524432 = 0;
					int Recon_Fee_amount_013000_531688 = 0;
					int Recon_Fee_amount_013000_542850 = 0;
					int Recon_Fee_amount_013000_516511 = 0; 
					int Recon_Fee_amount_013000_531244 = 0; 
					int Recon_Fee_amount_013000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int Fee_amount_120000_534771 = 0;
					int Fee_amount_120000_515462 = 0;
					int Fee_amount_120000_521444 = 0;
					int Fee_amount_120000_515480 = 0;
					int Fee_amount_120000_511340 = 0;
					int Fee_amount_120000_531150 = 0;
					int Fee_amount_120000_533313 = 0;
					int Fee_amount_120000_526293 = 0;
					int Fee_amount_120000_511479 = 0; 
					int Fee_amount_120000_516488 = 0; 
					int Fee_amount_120000_526249 = 0;
					int Fee_amount_120000_530688 = 0;
					int Fee_amount_120000_534785 = 0;
					int Fee_amount_120000_528627 = 0;
					int Fee_amount_120000_538933 = 0;
					int Fee_amount_120000_526291 = 0;
					int Fee_amount_120000_511096 = 0;
					int Fee_amount_120000_511186 = 0;
					int Fee_amount_120000_521412 = 0;
					int Fee_amount_120000_525698 = 0;
					int Fee_amount_120000_510842 = 0;
					int Fee_amount_120000_511233 = 0;
					int Fee_amount_120000_512856 = 0;
					int Fee_amount_120000_512864 = 0;
					int Fee_amount_120000_516612 = 0;
					int Fee_amount_120000_517628 = 0;
					int Fee_amount_120000_519106 = 0;
					int Fee_amount_120000_521961 = 0;
					int Fee_amount_120000_524432 = 0;
					int Fee_amount_120000_531688 = 0;
					int Fee_amount_120000_542850 = 0;
					int Fee_amount_120000_516511 = 0; 
					int Fee_amount_120000_531244 = 0; 
					int Fee_amount_120000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int Recon_Fee_amount_120000_534771 = 0;
					int Recon_Fee_amount_120000_515462 = 0;
					int Recon_Fee_amount_120000_521444 = 0;
					int Recon_Fee_amount_120000_515480 = 0;
					int Recon_Fee_amount_120000_511340 = 0;
					int Recon_Fee_amount_120000_531150 = 0;
					int Recon_Fee_amount_120000_533313 = 0;
					int Recon_Fee_amount_120000_526293 = 0;
					int Recon_Fee_amount_120000_511479 = 0; 
					int Recon_Fee_amount_120000_516488 = 0; 
					int Recon_Fee_amount_120000_526249 = 0;
					int Recon_Fee_amount_120000_530688 = 0;
					int Recon_Fee_amount_120000_534785 = 0;
					int Recon_Fee_amount_120000_528627 = 0;
					int Recon_Fee_amount_120000_538933 = 0;
					int Recon_Fee_amount_120000_526291 = 0;
					int Recon_Fee_amount_120000_511096 = 0;
					int Recon_Fee_amount_120000_511186 = 0;
					int Recon_Fee_amount_120000_521412 = 0;
					int Recon_Fee_amount_120000_525698 = 0;
					int Recon_Fee_amount_120000_510842 = 0;
					int Recon_Fee_amount_120000_511233 = 0;
					int Recon_Fee_amount_120000_512856 = 0;
					int Recon_Fee_amount_120000_512864 = 0;
					int Recon_Fee_amount_120000_516612 = 0;
					int Recon_Fee_amount_120000_517628 = 0;
					int Recon_Fee_amount_120000_519106 = 0;
					int Recon_Fee_amount_120000_521961 = 0;
					int Recon_Fee_amount_120000_524432 = 0;
					int Recon_Fee_amount_120000_531688 = 0;
					int Recon_Fee_amount_120000_542850 = 0;
					int Recon_Fee_amount_120000_516511 = 0; 
					int Recon_Fee_amount_120000_531244 = 0; 
					int Recon_Fee_amount_120000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int Fee_amount_010000_534771 = 0;
					int Fee_amount_010000_515462 = 0;
					int Fee_amount_010000_521444 = 0;
					int Fee_amount_010000_515480 = 0;
					int Fee_amount_010000_511340 = 0;
					int Fee_amount_010000_531150 = 0;
					int Fee_amount_010000_533313 = 0;
					int Fee_amount_010000_526293 = 0;
					int Fee_amount_010000_511479 = 0; 
					int Fee_amount_010000_516488 = 0; 
					int Fee_amount_010000_526249 = 0;
					int Fee_amount_010000_530688 = 0;
					int Fee_amount_010000_534785 = 0;
					int Fee_amount_010000_528627 = 0;
					int Fee_amount_010000_538933 = 0;
					int Fee_amount_010000_526291 = 0;
					int Fee_amount_010000_511096 = 0;
					int Fee_amount_010000_511186 = 0;
					int Fee_amount_010000_521412 = 0;
					int Fee_amount_010000_525698 = 0;
					int Fee_amount_010000_510842 = 0;
					int Fee_amount_010000_511233 = 0;
					int Fee_amount_010000_512856 = 0;
					int Fee_amount_010000_512864 = 0;
					int Fee_amount_010000_516612 = 0;
					int Fee_amount_010000_517628 = 0;
					int Fee_amount_010000_519106 = 0;
					int Fee_amount_010000_521961 = 0;
					int Fee_amount_010000_524432 = 0;
					int Fee_amount_010000_531688 = 0;
					int Fee_amount_010000_542850 = 0;
					int Fee_amount_010000_516511 = 0; 
					int Fee_amount_010000_531244 = 0; 
					int Fee_amount_010000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int Recon_Fee_amount_010000_534771 = 0;
					int Recon_Fee_amount_010000_515462 = 0;
					int Recon_Fee_amount_010000_521444 = 0;
					int Recon_Fee_amount_010000_515480 = 0;
					int Recon_Fee_amount_010000_511340 = 0;
					int Recon_Fee_amount_010000_531150 = 0;
					int Recon_Fee_amount_010000_533313 = 0;
					int Recon_Fee_amount_010000_526293 = 0;
					int Recon_Fee_amount_010000_511479 = 0; 
					int Recon_Fee_amount_010000_516488 = 0; 
					int Recon_Fee_amount_010000_526249 = 0;
					int Recon_Fee_amount_010000_530688 = 0;
					int Recon_Fee_amount_010000_534785 = 0;
					int Recon_Fee_amount_010000_528627 = 0;
					int Recon_Fee_amount_010000_538933 = 0;
					int Recon_Fee_amount_010000_526291 = 0;
					int Recon_Fee_amount_010000_511096 = 0;
					int Recon_Fee_amount_010000_511186 = 0;
					int Recon_Fee_amount_010000_521412 = 0;
					int Recon_Fee_amount_010000_525698 = 0;
					int Recon_Fee_amount_010000_510842 = 0;
					int Recon_Fee_amount_010000_511233 = 0;
					int Recon_Fee_amount_010000_512856 = 0;
					int Recon_Fee_amount_010000_512864 = 0;
					int Recon_Fee_amount_010000_516612 = 0;
					int Recon_Fee_amount_010000_517628 = 0;
					int Recon_Fee_amount_010000_519106 = 0;
					int Recon_Fee_amount_010000_521961 = 0;
					int Recon_Fee_amount_010000_524432 = 0;
					int Recon_Fee_amount_010000_531688 = 0;
					int Recon_Fee_amount_010000_542850 = 0;
					int Recon_Fee_amount_010000_516511 = 0; 
					int Recon_Fee_amount_010000_531244 = 0; 
					int Recon_Fee_amount_010000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int Fee_amount_012000_534771 = 0;
					int Fee_amount_012000_515462 = 0;
					int Fee_amount_012000_521444 = 0;
					int Fee_amount_012000_515480 = 0;
					int Fee_amount_012000_511340 = 0;
					int Fee_amount_012000_531150 = 0;
					int Fee_amount_012000_533313 = 0;
					int Fee_amount_012000_526293 = 0;
					int Fee_amount_012000_511479 = 0; 
					int Fee_amount_012000_516488 = 0; 
					int Fee_amount_012000_526249 = 0;
					int Fee_amount_012000_530688 = 0;
					int Fee_amount_012000_534785 = 0;
					int Fee_amount_012000_528627 = 0;
					int Fee_amount_012000_538933 = 0;
					int Fee_amount_012000_526291 = 0;
					int Fee_amount_012000_511096 = 0;
					int Fee_amount_012000_511186 = 0;
					int Fee_amount_012000_521412 = 0;
					int Fee_amount_012000_525698 = 0;
					int Fee_amount_012000_510842 = 0;
					int Fee_amount_012000_511233 = 0;
					int Fee_amount_012000_512856 = 0;
					int Fee_amount_012000_512864 = 0;
					int Fee_amount_012000_516612 = 0;
					int Fee_amount_012000_517628 = 0;
					int Fee_amount_012000_519106 = 0;
					int Fee_amount_012000_521961 = 0;
					int Fee_amount_012000_524432 = 0;
					int Fee_amount_012000_531688 = 0;
					int Fee_amount_012000_542850 = 0;
					int Fee_amount_012000_516511 = 0; 
					int Fee_amount_012000_531244 = 0; 
					int Fee_amount_012000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added this section on 05/13/2015 for VMS.
					int Recon_Fee_amount_012000_534771 = 0;
					int Recon_Fee_amount_012000_515462 = 0;
					int Recon_Fee_amount_012000_521444 = 0;
					int Recon_Fee_amount_012000_515480 = 0;
					int Recon_Fee_amount_012000_511340 = 0;
					int Recon_Fee_amount_012000_531150 = 0;
					int Recon_Fee_amount_012000_533313 = 0;
					int Recon_Fee_amount_012000_526293 = 0;
					int Recon_Fee_amount_012000_511479 = 0; 
					int Recon_Fee_amount_012000_516488 = 0; 
					int Recon_Fee_amount_012000_526249 = 0;
					int Recon_Fee_amount_012000_530688 = 0;
					int Recon_Fee_amount_012000_534785 = 0;
					int Recon_Fee_amount_012000_528627 = 0;
					int Recon_Fee_amount_012000_538933 = 0;
					int Recon_Fee_amount_012000_526291 = 0;
					int Recon_Fee_amount_012000_511096 = 0;
					int Recon_Fee_amount_012000_511186 = 0;
					int Recon_Fee_amount_012000_521412 = 0;
					int Recon_Fee_amount_012000_525698 = 0;
					int Recon_Fee_amount_012000_510842 = 0;
					int Recon_Fee_amount_012000_511233 = 0;
					int Recon_Fee_amount_012000_512856 = 0;
					int Recon_Fee_amount_012000_512864 = 0;
					int Recon_Fee_amount_012000_516612 = 0;
					int Recon_Fee_amount_012000_517628 = 0;
					int Recon_Fee_amount_012000_519106 = 0;
					int Recon_Fee_amount_012000_521961 = 0;
					int Recon_Fee_amount_012000_524432 = 0;
					int Recon_Fee_amount_012000_531688 = 0;
					int Recon_Fee_amount_012000_542850 = 0;
					int Recon_Fee_amount_012000_516511 = 0; 
					int Recon_Fee_amount_012000_531244 = 0; 
					int Recon_Fee_amount_012000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added on 07/31/2015 for process code - 011000
					int Fee_amount_011000_534771 = 0;
					int Fee_amount_011000_515462 = 0;
					int Fee_amount_011000_521444 = 0;
					int Fee_amount_011000_515480 = 0;
					int Fee_amount_011000_511340 = 0;
					int Fee_amount_011000_531150 = 0;
					int Fee_amount_011000_533313 = 0;
					int Fee_amount_011000_526293 = 0;
					int Fee_amount_011000_511479 = 0; 
					int Fee_amount_011000_516488 = 0; 
					int Fee_amount_011000_526249 = 0;
					int Fee_amount_011000_530688 = 0;
					int Fee_amount_011000_534785 = 0;
					int Fee_amount_011000_528627 = 0;
					int Fee_amount_011000_538933 = 0;
					int Fee_amount_011000_526291 = 0;
					int Fee_amount_011000_511096 = 0;
					int Fee_amount_011000_511186 = 0;
					int Fee_amount_011000_521412 = 0;
					int Fee_amount_011000_525698 = 0;
					int Fee_amount_011000_510842 = 0;
					int Fee_amount_011000_511233 = 0;
					int Fee_amount_011000_512856 = 0;
					int Fee_amount_011000_512864 = 0;
					int Fee_amount_011000_516612 = 0;
					int Fee_amount_011000_517628 = 0;
					int Fee_amount_011000_519106 = 0;
					int Fee_amount_011000_521961 = 0;
					int Fee_amount_011000_524432 = 0;
					int Fee_amount_011000_531688 = 0;
					int Fee_amount_011000_542850 = 0;
					int Fee_amount_011000_516511 = 0; 
					int Fee_amount_011000_531244 = 0; 
					int Fee_amount_011000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added on 07/31/2015 for process code - 011000
					int Recon_Fee_amount_011000_534771 = 0;
					int Recon_Fee_amount_011000_515462 = 0;
					int Recon_Fee_amount_011000_521444 = 0;
					int Recon_Fee_amount_011000_515480 = 0;
					int Recon_Fee_amount_011000_511340 = 0;
					int Recon_Fee_amount_011000_531150 = 0;
					int Recon_Fee_amount_011000_533313 = 0;
					int Recon_Fee_amount_011000_526293 = 0;
					int Recon_Fee_amount_011000_511479 = 0; 
					int Recon_Fee_amount_011000_516488 = 0; 
					int Recon_Fee_amount_011000_526249 = 0;
					int Recon_Fee_amount_011000_530688 = 0;
					int Recon_Fee_amount_011000_534785 = 0;
					int Recon_Fee_amount_011000_528627 = 0;
					int Recon_Fee_amount_011000_538933 = 0;
					int Recon_Fee_amount_011000_526291 = 0;
					int Recon_Fee_amount_011000_511096 = 0;
					int Recon_Fee_amount_011000_511186 = 0;
					int Recon_Fee_amount_011000_521412 = 0;
					int Recon_Fee_amount_011000_525698 = 0;
					int Recon_Fee_amount_011000_510842 = 0;
					int Recon_Fee_amount_011000_511233 = 0;
					int Recon_Fee_amount_011000_512856 = 0;
					int Recon_Fee_amount_011000_512864 = 0;
					int Recon_Fee_amount_011000_516612 = 0;
					int Recon_Fee_amount_011000_517628 = 0;
					int Recon_Fee_amount_011000_519106 = 0;
					int Recon_Fee_amount_011000_521961 = 0;
					int Recon_Fee_amount_011000_524432 = 0;
					int Recon_Fee_amount_011000_531688 = 0;
					int Recon_Fee_amount_011000_542850 = 0;
					int Recon_Fee_amount_011000_516511 = 0; 
					int Recon_Fee_amount_011000_531244 = 0; 
					int Recon_Fee_amount_011000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added on 07/31/2015 for process code - 280000
					int Fee_amount_280000_534771 = 0;
					int Fee_amount_280000_515462 = 0;
					int Fee_amount_280000_521444 = 0;
					int Fee_amount_280000_515480 = 0;
					int Fee_amount_280000_511340 = 0;
					int Fee_amount_280000_531150 = 0;
					int Fee_amount_280000_533313 = 0;
					int Fee_amount_280000_526293 = 0;
					int Fee_amount_280000_511479 = 0; 
					int Fee_amount_280000_516488 = 0; 
					int Fee_amount_280000_526249 = 0;
					int Fee_amount_280000_530688 = 0;
					int Fee_amount_280000_534785 = 0;
					int Fee_amount_280000_528627 = 0;
					int Fee_amount_280000_538933 = 0;
					int Fee_amount_280000_526291 = 0;
					int Fee_amount_280000_511096 = 0;
					int Fee_amount_280000_511186 = 0;
					int Fee_amount_280000_521412 = 0;
					int Fee_amount_280000_525698 = 0;
					int Fee_amount_280000_510842 = 0;
					int Fee_amount_280000_511233 = 0;
					int Fee_amount_280000_512856 = 0;
					int Fee_amount_280000_512864 = 0;
					int Fee_amount_280000_516612 = 0;
					int Fee_amount_280000_517628 = 0;
					int Fee_amount_280000_519106 = 0;
					int Fee_amount_280000_521961 = 0;
					int Fee_amount_280000_524432 = 0;
					int Fee_amount_280000_531688 = 0;
					int Fee_amount_280000_542850 = 0;
					int Fee_amount_280000_516511 = 0; 
					int Fee_amount_280000_531244 = 0; 
					int Fee_amount_280000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					//Added on 07/31/2015 for process code - 011000
					int Recon_Fee_amount_280000_534771 = 0;
					int Recon_Fee_amount_280000_515462 = 0;
					int Recon_Fee_amount_280000_521444 = 0;
					int Recon_Fee_amount_280000_515480 = 0;
					int Recon_Fee_amount_280000_511340 = 0;
					int Recon_Fee_amount_280000_531150 = 0;
					int Recon_Fee_amount_280000_533313 = 0;
					int Recon_Fee_amount_280000_526293 = 0;
					int Recon_Fee_amount_280000_511479 = 0; 
					int Recon_Fee_amount_280000_516488 = 0; 
					int Recon_Fee_amount_280000_526249 = 0;
					int Recon_Fee_amount_280000_530688 = 0;
					int Recon_Fee_amount_280000_534785 = 0;
					int Recon_Fee_amount_280000_528627 = 0;
					int Recon_Fee_amount_280000_538933 = 0;
					int Recon_Fee_amount_280000_526291 = 0;
					int Recon_Fee_amount_280000_511096 = 0;
					int Recon_Fee_amount_280000_511186 = 0;
					int Recon_Fee_amount_280000_521412 = 0;
					int Recon_Fee_amount_280000_525698 = 0;
					int Recon_Fee_amount_280000_510842 = 0;
					int Recon_Fee_amount_280000_511233 = 0;
					int Recon_Fee_amount_280000_512856 = 0;
					int Recon_Fee_amount_280000_512864 = 0;
					int Recon_Fee_amount_280000_516612 = 0;
					int Recon_Fee_amount_280000_517628 = 0;
					int Recon_Fee_amount_280000_519106 = 0;
					int Recon_Fee_amount_280000_521961 = 0;
					int Recon_Fee_amount_280000_524432 = 0;
					int Recon_Fee_amount_280000_531688 = 0;
					int Recon_Fee_amount_280000_542850 = 0;
					int Recon_Fee_amount_280000_516511 = 0; 
					int Recon_Fee_amount_280000_531244 = 0; 
					int Recon_Fee_amount_280000_543276 = 0; //added on 08/05/2015. One vanilla MC. US

					
					 pStatementReport = conn.prepareCall(reportQuery);	
					 pStatementReport.setInt(1, FileID);
					 pStatementReport.executeQuery();
					 rsReport = pStatementReport.getResultSet();
				 
					 logger.debug("MTI,PAN,ProcessCode,Amount,ReconAmount,CardHolderBillingAmount,AmountCurr,ReconAmountCurr,CardHolderBillingAmountCurr,DateTimeLocalTXN,SettlementDate,MCC,MerchantID,TerminalID,BanknetRefNum");
				 
					 if (rsReport != null)
				     {
						while (rsReport.next()) 
						{
							reportString = rsReport.getString(1);
							reportString = new EncryptDecryptUtil().decrypt(reportString, IPMKey);

							//hostName = rsReport.getString(2);
							//reportString = reportString.substring(0,reportString.lastIndexOf(","));
							
							if (reportString != null)
							{
								logger.debug(" reportString = "+reportString);
								String PAN = null;
								int iBIN = 0;
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
								while (reportStr.hasMoreElements()) 
								{
									reportStr.nextElement(); // to skip MTI - 1220
									
									PAN = reportStr.nextElement().toString();
									//logger.info("PAN : " + IPMUtil.maskText(PAN, 6, 12, "*")); 
									iBIN = new Integer(PAN.substring(1, 7)).intValue();
									logger.info(" iBIN = "+iBIN);
									
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
								}
	
	
							//"MTI,PAN,ProcessCode,Amount,ReconAmount,CardHolderBillingAmount,AmountCurr,ReconAmountCurr,CardHolderBillingAmountCurr,DateTimeLocalTXN,SettlementDate,MCC,MerchantID,TerminalID,BanknetRefNum"
							if(ProcCode.equals("000000")) 
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_160100 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_160100_534771 += feeAmount; Recon_Fee_amount_160100_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_160100 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_160100_515462 += feeAmount; Recon_Fee_amount_160100_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_160100 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_160100_521444 += feeAmount; Recon_Fee_amount_160100_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_160100 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_160100_515480 += feeAmount; Recon_Fee_amount_160100_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_160100 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_160100_511340 += feeAmount; Recon_Fee_amount_160100_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_160100 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_160100_531150 += feeAmount; Recon_Fee_amount_160100_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_160100 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_160100_533313 += feeAmount; Recon_Fee_amount_160100_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_160100 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_160100_526293 += feeAmount; Recon_Fee_amount_160100_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_160100 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_160100_511479 += feeAmount; Recon_Fee_amount_160100_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_160100 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_160100_516488 += feeAmount; Recon_Fee_amount_160100_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_160100 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_160100_526249 += feeAmount; Recon_Fee_amount_160100_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_160100 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_160100_530688 += feeAmount; Recon_Fee_amount_160100_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_160100 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_160100_534785 += feeAmount; Recon_Fee_amount_160100_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_160100 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_160100_528627 += feeAmount; Recon_Fee_amount_160100_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_160100 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_160100_538933 += feeAmount; Recon_Fee_amount_160100_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_160100 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_160100_526291 += feeAmount; Recon_Fee_amount_160100_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_160100 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_160100_511096 += feeAmount; Recon_Fee_amount_160100_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_160100 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_160100_511186 += feeAmount; Recon_Fee_amount_160100_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_160100 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_160100_521412 += feeAmount; Recon_Fee_amount_160100_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_160100 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_160100_525698 += feeAmount; Recon_Fee_amount_160100_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_160100 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_160100_510842 += feeAmount; Recon_Fee_amount_160100_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_160100 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_160100_511233 += feeAmount; Recon_Fee_amount_160100_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_160100 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_160100_512856 += feeAmount; Recon_Fee_amount_160100_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_160100 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_160100_512864 += feeAmount; Recon_Fee_amount_160100_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_160100 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_160100_516612 += feeAmount; Recon_Fee_amount_160100_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_160100 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_160100_517628 += feeAmount; Recon_Fee_amount_160100_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_160100 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_160100_519106 += feeAmount; Recon_Fee_amount_160100_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_160100 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_160100_521961 += feeAmount; Recon_Fee_amount_160100_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_160100 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_160100_524432 += feeAmount; Recon_Fee_amount_160100_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_160100 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_160100_531688 += feeAmount; Recon_Fee_amount_160100_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_160100 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_160100_542850 += feeAmount; Recon_Fee_amount_160100_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_160100 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_160100_516511 += feeAmount; Recon_Fee_amount_160100_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_160100 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_160100_531244 += feeAmount; Recon_Fee_amount_160100_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_160100 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_160100_543276 += feeAmount; Recon_Fee_amount_160100_543276 += reconFeeAmount; break;
								}
							}
							else if(ProcCode.equals("200000")) 
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_200000 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_200000_534771 += feeAmount; Recon_Fee_amount_200000_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_200000 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_200000_515462 += feeAmount; Recon_Fee_amount_200000_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_200000 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_200000_521444 += feeAmount; Recon_Fee_amount_200000_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_200000 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_200000_515480 += feeAmount; Recon_Fee_amount_200000_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_200000 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_200000_511340 += feeAmount; Recon_Fee_amount_200000_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_200000 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_200000_531150 += feeAmount; Recon_Fee_amount_200000_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_200000 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_200000_533313 += feeAmount; Recon_Fee_amount_200000_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_200000 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_200000_526293 += feeAmount; Recon_Fee_amount_200000_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_200000 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_200000_511479 += feeAmount; Recon_Fee_amount_200000_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_200000 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_200000_516488 += feeAmount; Recon_Fee_amount_200000_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_200000 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_200000_526249 += feeAmount; Recon_Fee_amount_200000_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_200000 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_200000_530688 += feeAmount; Recon_Fee_amount_200000_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_200000 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_200000_534785 += feeAmount; Recon_Fee_amount_200000_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_200000 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_200000_528627 += feeAmount; Recon_Fee_amount_200000_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_200000 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_200000_538933 += feeAmount; Recon_Fee_amount_200000_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_200000 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_200000_526291 += feeAmount; Recon_Fee_amount_200000_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_200000 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_200000_511096 += feeAmount; Recon_Fee_amount_200000_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_200000 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_200000_511186 += feeAmount; Recon_Fee_amount_200000_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_200000 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_200000_521412 += feeAmount; Recon_Fee_amount_200000_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_200000 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_200000_525698 += feeAmount; Recon_Fee_amount_200000_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_200000 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_200000_510842 += feeAmount; Recon_Fee_amount_200000_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_200000 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_200000_511233 += feeAmount; Recon_Fee_amount_200000_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_200000 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_200000_512856 += feeAmount; Recon_Fee_amount_200000_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_200000 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_200000_512864 += feeAmount; Recon_Fee_amount_200000_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_200000 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_200000_516612 += feeAmount; Recon_Fee_amount_200000_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_200000 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_200000_517628 += feeAmount; Recon_Fee_amount_200000_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_200000 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_200000_519106 += feeAmount; Recon_Fee_amount_200000_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_200000 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_200000_521961 += feeAmount; Recon_Fee_amount_200000_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_200000 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_200000_524432 += feeAmount; Recon_Fee_amount_200000_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_200000 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_200000_531688 += feeAmount; Recon_Fee_amount_200000_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_200000 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_200000_542850 += feeAmount; Recon_Fee_amount_200000_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_200000 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_200000_516511 += feeAmount; Recon_Fee_amount_200000_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_200000 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_200000_531244 += feeAmount; Recon_Fee_amount_200000_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_200000 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_200000_543276 += feeAmount; Recon_Fee_amount_200000_543276 += reconFeeAmount; break;
								}
							}
							else if(ProcCode.equals("003000")) 
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_003000 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_003000_534771 += feeAmount; Recon_Fee_amount_003000_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_003000 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_003000_515462 += feeAmount; Recon_Fee_amount_003000_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_003000 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_003000_521444 += feeAmount; Recon_Fee_amount_003000_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_003000 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_003000_515480 += feeAmount; Recon_Fee_amount_003000_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_003000 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_003000_511340 += feeAmount; Recon_Fee_amount_003000_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_003000 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_003000_531150 += feeAmount; Recon_Fee_amount_003000_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_003000 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_003000_533313 += feeAmount; Recon_Fee_amount_003000_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_003000 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_003000_526293 += feeAmount; Recon_Fee_amount_003000_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_003000 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_003000_511479 += feeAmount; Recon_Fee_amount_003000_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_003000 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_003000_516488 += feeAmount; Recon_Fee_amount_003000_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_003000 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_003000_526249 += feeAmount; Recon_Fee_amount_003000_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_003000 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_003000_530688 += feeAmount; Recon_Fee_amount_003000_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_003000 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_003000_534785 += feeAmount; Recon_Fee_amount_003000_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_003000 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_003000_528627 += feeAmount; Recon_Fee_amount_003000_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_003000 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_003000_538933 += feeAmount; Recon_Fee_amount_003000_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_003000 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_003000_526291 += feeAmount; Recon_Fee_amount_003000_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_003000 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_003000_511096 += feeAmount; Recon_Fee_amount_003000_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_003000 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_003000_511186 += feeAmount; Recon_Fee_amount_003000_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_003000 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_003000_521412 += feeAmount; Recon_Fee_amount_003000_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_003000 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_003000_525698 += feeAmount; Recon_Fee_amount_003000_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_003000 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_003000_510842 += feeAmount; Recon_Fee_amount_003000_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_003000 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_003000_511233 += feeAmount; Recon_Fee_amount_003000_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_003000 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_003000_512856 += feeAmount; Recon_Fee_amount_003000_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_003000 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_003000_512864 += feeAmount; Recon_Fee_amount_003000_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_003000 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_003000_516612 += feeAmount; Recon_Fee_amount_003000_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_003000 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_003000_517628 += feeAmount; Recon_Fee_amount_003000_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_003000 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_003000_519106 += feeAmount; Recon_Fee_amount_003000_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_003000 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_003000_521961 += feeAmount; Recon_Fee_amount_003000_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_003000 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_003000_524432 += feeAmount; Recon_Fee_amount_003000_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_003000 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_003000_531688 += feeAmount; Recon_Fee_amount_003000_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_003000 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_003000_542850 += feeAmount; Recon_Fee_amount_003000_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_003000 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_003000_516511 += feeAmount; Recon_Fee_amount_003000_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_003000 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_003000_531244 += feeAmount; Recon_Fee_amount_003000_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_003000 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_003000_543276 += feeAmount; Recon_Fee_amount_003000_543276 += reconFeeAmount; break;
								}
							}
							else if(ProcCode.equals("013000")) 
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_013000 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_013000_534771 += feeAmount; Recon_Fee_amount_013000_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_013000 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_013000_515462 += feeAmount; Recon_Fee_amount_013000_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_013000 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_013000_521444 += feeAmount; Recon_Fee_amount_013000_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_013000 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_013000_515480 += feeAmount; Recon_Fee_amount_013000_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_013000 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_013000_511340 += feeAmount; Recon_Fee_amount_013000_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_013000 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_013000_531150 += feeAmount; Recon_Fee_amount_013000_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_013000 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_013000_533313 += feeAmount; Recon_Fee_amount_013000_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_013000 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_013000_526293 += feeAmount; Recon_Fee_amount_013000_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_013000 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_013000_511479 += feeAmount; Recon_Fee_amount_013000_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_013000 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_013000_516488 += feeAmount; Recon_Fee_amount_013000_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_013000 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_013000_526249 += feeAmount; Recon_Fee_amount_013000_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_013000 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_013000_530688 += feeAmount; Recon_Fee_amount_013000_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_013000 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_013000_534785 += feeAmount; Recon_Fee_amount_013000_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_013000 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_013000_528627 += feeAmount; Recon_Fee_amount_013000_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_013000 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_013000_538933 += feeAmount; Recon_Fee_amount_013000_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_013000 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_013000_526291 += feeAmount; Recon_Fee_amount_013000_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_013000 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_013000_511096 += feeAmount; Recon_Fee_amount_013000_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_013000 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_013000_511186 += feeAmount; Recon_Fee_amount_013000_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_013000 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_013000_521412 += feeAmount; Recon_Fee_amount_013000_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_013000 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_013000_525698 += feeAmount; Recon_Fee_amount_013000_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_013000 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_013000_510842 += feeAmount; Recon_Fee_amount_013000_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_013000 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_013000_511233 += feeAmount; Recon_Fee_amount_013000_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_013000 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_013000_512856 += feeAmount; Recon_Fee_amount_013000_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_013000 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_013000_512864 += feeAmount; Recon_Fee_amount_013000_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_013000 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_013000_516612 += feeAmount; Recon_Fee_amount_013000_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_013000 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_013000_517628 += feeAmount; Recon_Fee_amount_013000_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_013000 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_013000_519106 += feeAmount; Recon_Fee_amount_013000_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_013000 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_013000_521961 += feeAmount; Recon_Fee_amount_013000_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_013000 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_013000_524432 += feeAmount; Recon_Fee_amount_013000_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_013000 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_013000_531688 += feeAmount; Recon_Fee_amount_013000_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_013000 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_013000_542850 += feeAmount; Recon_Fee_amount_013000_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_013000 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_013000_516511 += feeAmount; Recon_Fee_amount_013000_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_013000 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_013000_531244 += feeAmount; Recon_Fee_amount_013000_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_013000 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_013000_543276 += feeAmount; Recon_Fee_amount_013000_543276 += reconFeeAmount; break;
								}
							}
							else if(ProcCode.equals("120000")) 
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_120000 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_120000_534771 += feeAmount; Recon_Fee_amount_120000_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_120000 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_120000_515462 += feeAmount; Recon_Fee_amount_120000_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_120000 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_120000_521444 += feeAmount; Recon_Fee_amount_120000_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_120000 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_120000_515480 += feeAmount; Recon_Fee_amount_120000_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_120000 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_120000_511340 += feeAmount; Recon_Fee_amount_120000_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_120000 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_120000_531150 += feeAmount; Recon_Fee_amount_120000_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_120000 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_120000_533313 += feeAmount; Recon_Fee_amount_120000_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_120000 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_120000_526293 += feeAmount; Recon_Fee_amount_120000_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_120000 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_120000_511479 += feeAmount; Recon_Fee_amount_120000_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_120000 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_120000_516488 += feeAmount; Recon_Fee_amount_120000_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_120000 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_120000_526249 += feeAmount; Recon_Fee_amount_120000_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_120000 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_120000_530688 += feeAmount; Recon_Fee_amount_120000_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_120000 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_120000_534785 += feeAmount; Recon_Fee_amount_120000_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_120000 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_120000_528627 += feeAmount; Recon_Fee_amount_120000_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_120000 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_120000_538933 += feeAmount; Recon_Fee_amount_120000_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_120000 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_120000_526291 += feeAmount; Recon_Fee_amount_120000_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_120000 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_120000_511096 += feeAmount; Recon_Fee_amount_120000_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_120000 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_120000_511186 += feeAmount; Recon_Fee_amount_120000_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_120000 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_120000_521412 += feeAmount; Recon_Fee_amount_120000_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_120000 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_120000_525698 += feeAmount; Recon_Fee_amount_120000_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_120000 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_120000_510842 += feeAmount; Recon_Fee_amount_120000_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_120000 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_120000_511233 += feeAmount; Recon_Fee_amount_120000_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_120000 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_120000_512856 += feeAmount; Recon_Fee_amount_120000_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_120000 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_120000_512864 += feeAmount; Recon_Fee_amount_120000_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_120000 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_120000_516612 += feeAmount; Recon_Fee_amount_120000_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_120000 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_120000_517628 += feeAmount; Recon_Fee_amount_120000_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_120000 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_120000_519106 += feeAmount; Recon_Fee_amount_120000_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_120000 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_120000_521961 += feeAmount; Recon_Fee_amount_120000_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_120000 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_120000_524432 += feeAmount; Recon_Fee_amount_120000_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_120000 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_120000_531688 += feeAmount; Recon_Fee_amount_120000_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_120000 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_120000_542850 += feeAmount; Recon_Fee_amount_120000_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_120000 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_120000_516511 += feeAmount; Recon_Fee_amount_120000_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_120000 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_120000_531244 += feeAmount; Recon_Fee_amount_120000_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_120000 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_120000_543276 += feeAmount; Recon_Fee_amount_120000_543276 += reconFeeAmount; break;
								}
							}
							else if(ProcCode.equals("010000")) 
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_010000 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_010000_534771 += feeAmount; Recon_Fee_amount_010000_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_010000 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_010000_515462 += feeAmount; Recon_Fee_amount_010000_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_010000 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_010000_521444 += feeAmount; Recon_Fee_amount_010000_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_010000 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_010000_515480 += feeAmount; Recon_Fee_amount_010000_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_010000 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_010000_511340 += feeAmount; Recon_Fee_amount_010000_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_010000 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_010000_531150 += feeAmount; Recon_Fee_amount_010000_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_010000 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_010000_533313 += feeAmount; Recon_Fee_amount_010000_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_010000 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_010000_526293 += feeAmount; Recon_Fee_amount_010000_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_010000 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_010000_511479 += feeAmount; Recon_Fee_amount_010000_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_010000 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_010000_516488 += feeAmount; Recon_Fee_amount_010000_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_010000 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_010000_526249 += feeAmount; Recon_Fee_amount_010000_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_010000 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_010000_530688 += feeAmount; Recon_Fee_amount_010000_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_010000 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_010000_534785 += feeAmount; Recon_Fee_amount_010000_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_010000 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_010000_528627 += feeAmount; Recon_Fee_amount_010000_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_010000 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_010000_538933 += feeAmount; Recon_Fee_amount_010000_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_010000 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_010000_526291 += feeAmount; Recon_Fee_amount_010000_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_010000 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_010000_511096 += feeAmount; Recon_Fee_amount_010000_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_010000 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_010000_511186 += feeAmount; Recon_Fee_amount_010000_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_010000 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_010000_521412 += feeAmount; Recon_Fee_amount_010000_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_010000 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_010000_525698 += feeAmount; Recon_Fee_amount_010000_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_010000 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_010000_510842 += feeAmount; Recon_Fee_amount_010000_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_010000 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_010000_511233 += feeAmount; Recon_Fee_amount_010000_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_010000 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_010000_512856 += feeAmount; Recon_Fee_amount_010000_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_010000 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_010000_512864 += feeAmount; Recon_Fee_amount_010000_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_010000 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_010000_516612 += feeAmount; Recon_Fee_amount_010000_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_010000 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_010000_517628 += feeAmount; Recon_Fee_amount_010000_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_010000 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_010000_519106 += feeAmount; Recon_Fee_amount_010000_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_010000 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_010000_521961 += feeAmount; Recon_Fee_amount_010000_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_010000 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_010000_524432 += feeAmount; Recon_Fee_amount_010000_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_010000 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_010000_531688 += feeAmount; Recon_Fee_amount_010000_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_010000 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_010000_542850 += feeAmount; Recon_Fee_amount_010000_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_010000 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_010000_516511 += feeAmount; Recon_Fee_amount_010000_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_010000 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_010000_531244 += feeAmount; Recon_Fee_amount_010000_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_010000 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_010000_543276 += feeAmount; Recon_Fee_amount_010000_543276 += reconFeeAmount; break;
								}
							}
							else if(ProcCode.equals("012000")) 
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_012000 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_012000_534771 += feeAmount; Recon_Fee_amount_012000_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_012000 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_012000_515462 += feeAmount; Recon_Fee_amount_012000_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_012000 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_012000_521444 += feeAmount; Recon_Fee_amount_012000_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_012000 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_012000_515480 += feeAmount; Recon_Fee_amount_012000_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_012000 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_012000_511340 += feeAmount; Recon_Fee_amount_012000_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_012000 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_012000_531150 += feeAmount; Recon_Fee_amount_012000_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_012000 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_012000_533313 += feeAmount; Recon_Fee_amount_012000_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_012000 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_012000_526293 += feeAmount; Recon_Fee_amount_012000_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_012000 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_012000_511479 += feeAmount; Recon_Fee_amount_012000_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_012000 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_012000_516488 += feeAmount; Recon_Fee_amount_012000_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_012000 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_012000_526249 += feeAmount; Recon_Fee_amount_012000_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_012000 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_012000_530688 += feeAmount; Recon_Fee_amount_012000_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_012000 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_012000_534785 += feeAmount; Recon_Fee_amount_012000_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_012000 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_012000_528627 += feeAmount; Recon_Fee_amount_012000_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_012000 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_012000_538933 += feeAmount; Recon_Fee_amount_012000_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_012000 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_012000_526291 += feeAmount; Recon_Fee_amount_012000_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_012000 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_012000_511096 += feeAmount; Recon_Fee_amount_012000_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_012000 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_012000_511186 += feeAmount; Recon_Fee_amount_012000_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_012000 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_012000_521412 += feeAmount; Recon_Fee_amount_012000_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_012000 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_012000_525698 += feeAmount; Recon_Fee_amount_012000_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_012000 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_012000_510842 += feeAmount; Recon_Fee_amount_012000_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_012000 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_012000_511233 += feeAmount; Recon_Fee_amount_012000_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_012000 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_012000_512856 += feeAmount; Recon_Fee_amount_012000_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_012000 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_012000_512864 += feeAmount; Recon_Fee_amount_012000_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_012000 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_012000_516612 += feeAmount; Recon_Fee_amount_012000_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_012000 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_012000_517628 += feeAmount; Recon_Fee_amount_012000_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_012000 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_012000_519106 += feeAmount; Recon_Fee_amount_012000_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_012000 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_012000_521961 += feeAmount; Recon_Fee_amount_012000_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_012000 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_012000_524432 += feeAmount; Recon_Fee_amount_012000_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_012000 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_012000_531688 += feeAmount; Recon_Fee_amount_012000_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_012000 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_012000_542850 += feeAmount; Recon_Fee_amount_012000_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_012000 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_012000_516511 += feeAmount; Recon_Fee_amount_012000_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_012000 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_012000_531244 += feeAmount; Recon_Fee_amount_012000_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_012000 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_012000_543276 += feeAmount; Recon_Fee_amount_012000_543276 += reconFeeAmount; break;
								}
							}
							else if(ProcCode.equals("011000")) //added on 07/31/2015
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_011000 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_011000_534771 += feeAmount; Recon_Fee_amount_011000_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_011000 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_011000_515462 += feeAmount; Recon_Fee_amount_011000_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_011000 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_011000_521444 += feeAmount; Recon_Fee_amount_011000_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_011000 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_011000_515480 += feeAmount; Recon_Fee_amount_011000_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_011000 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_011000_511340 += feeAmount; Recon_Fee_amount_011000_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_011000 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_011000_531150 += feeAmount; Recon_Fee_amount_011000_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_011000 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_011000_533313 += feeAmount; Recon_Fee_amount_011000_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_011000 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_011000_526293 += feeAmount; Recon_Fee_amount_011000_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_011000 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_011000_511479 += feeAmount; Recon_Fee_amount_011000_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_011000 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_011000_516488 += feeAmount; Recon_Fee_amount_011000_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_011000 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_011000_526249 += feeAmount; Recon_Fee_amount_011000_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_011000 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_011000_530688 += feeAmount; Recon_Fee_amount_011000_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_011000 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_011000_534785 += feeAmount; Recon_Fee_amount_011000_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_011000 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_011000_528627 += feeAmount; Recon_Fee_amount_011000_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_011000 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_011000_538933 += feeAmount; Recon_Fee_amount_011000_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_011000 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_011000_526291 += feeAmount; Recon_Fee_amount_011000_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_011000 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_011000_511096 += feeAmount; Recon_Fee_amount_011000_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_011000 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_011000_511186 += feeAmount; Recon_Fee_amount_011000_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_011000 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_011000_521412 += feeAmount; Recon_Fee_amount_011000_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_011000 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_011000_525698 += feeAmount; Recon_Fee_amount_011000_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_011000 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_011000_510842 += feeAmount; Recon_Fee_amount_011000_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_011000 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_011000_511233 += feeAmount; Recon_Fee_amount_011000_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_011000 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_011000_512856 += feeAmount; Recon_Fee_amount_011000_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_011000 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_011000_512864 += feeAmount; Recon_Fee_amount_011000_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_011000 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_011000_516612 += feeAmount; Recon_Fee_amount_011000_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_011000 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_011000_517628 += feeAmount; Recon_Fee_amount_011000_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_011000 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_011000_519106 += feeAmount; Recon_Fee_amount_011000_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_011000 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_011000_521961 += feeAmount; Recon_Fee_amount_011000_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_011000 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_011000_524432 += feeAmount; Recon_Fee_amount_011000_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_011000 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_011000_531688 += feeAmount; Recon_Fee_amount_011000_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_011000 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_011000_542850 += feeAmount; Recon_Fee_amount_011000_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_011000 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_011000_516511 += feeAmount; Recon_Fee_amount_011000_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_011000 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_011000_531244 += feeAmount; Recon_Fee_amount_011000_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_011000 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_011000_543276 += feeAmount; Recon_Fee_amount_011000_543276 += reconFeeAmount; break;
								}
							}
							else if(ProcCode.equals("280000")) //added on 07/31/2015 
							{
								switch(iBIN) 
								{
									case 534771 : count_534771++; amount_534771_280000 += iCBAmount; Recon_amount_534771 += iReconAmount; CB_amount_534771 += iCBAmount; Fee_amount_280000_534771 += feeAmount; Recon_Fee_amount_280000_534771 += reconFeeAmount; break;
									case 515462 : count_515462++; amount_515462_280000 += iCBAmount; Recon_amount_515462 += iReconAmount; CB_amount_515462 += iCBAmount; Fee_amount_280000_515462 += feeAmount; Recon_Fee_amount_280000_515462 += reconFeeAmount; break;
									case 521444 : count_521444++; amount_521444_280000 += iCBAmount; Recon_amount_521444 += iReconAmount; CB_amount_521444 += iCBAmount; Fee_amount_280000_521444 += feeAmount; Recon_Fee_amount_280000_521444 += reconFeeAmount; break;
									case 515480 : count_515480++; amount_515480_280000 += iCBAmount; Recon_amount_515480 += iReconAmount; CB_amount_515480 += iCBAmount; Fee_amount_280000_515480 += feeAmount; Recon_Fee_amount_280000_515480 += reconFeeAmount; break;
									case 511340 : count_511340++; amount_511340_280000 += iCBAmount; Recon_amount_511340 += iReconAmount; CB_amount_511340 += iCBAmount; Fee_amount_280000_511340 += feeAmount; Recon_Fee_amount_280000_511340 += reconFeeAmount; break;
									case 531150 : count_531150++; amount_531150_280000 += iCBAmount; Recon_amount_531150 += iReconAmount; CB_amount_531150 += iCBAmount; Fee_amount_280000_531150 += feeAmount; Recon_Fee_amount_280000_531150 += reconFeeAmount; break;
									case 533313 : count_533313++; amount_533313_280000 += iCBAmount; Recon_amount_533313 += iReconAmount; CB_amount_533313 += iCBAmount; Fee_amount_280000_533313 += feeAmount; Recon_Fee_amount_280000_533313 += reconFeeAmount; break;
									case 526293 : count_526293++; amount_526293_280000 += iCBAmount; Recon_amount_526293 += iReconAmount; CB_amount_526293 += iCBAmount; Fee_amount_280000_526293 += feeAmount; Recon_Fee_amount_280000_526293 += reconFeeAmount; break;
									case 511479 : count_511479++; amount_511479_280000 += iCBAmount; Recon_amount_511479 += iReconAmount; CB_amount_511479 += iCBAmount; Fee_amount_280000_511479 += feeAmount; Recon_Fee_amount_280000_511479 += reconFeeAmount; break; //added on 07/24/2013
									case 516488 : count_516488++; amount_516488_280000 += iCBAmount; Recon_amount_516488 += iReconAmount; CB_amount_516488 += iCBAmount; Fee_amount_280000_516488 += feeAmount; Recon_Fee_amount_280000_516488 += reconFeeAmount; break; //added on 07/24/2013
									case 526249 : count_526249++; amount_526249_280000 += iCBAmount; Recon_amount_526249 += iReconAmount; CB_amount_526249 += iCBAmount; Fee_amount_280000_526249 += feeAmount; Recon_Fee_amount_280000_526249 += reconFeeAmount; break;
									case 530688 : count_530688++; amount_530688_280000 += iCBAmount; Recon_amount_530688 += iReconAmount; CB_amount_530688 += iCBAmount; Fee_amount_280000_530688 += feeAmount; Recon_Fee_amount_280000_530688 += reconFeeAmount; break;
									case 534785 : count_534785++; amount_534785_280000 += iCBAmount; Recon_amount_534785 += iReconAmount; CB_amount_534785 += iCBAmount; Fee_amount_280000_534785 += feeAmount; Recon_Fee_amount_280000_534785 += reconFeeAmount; break;
									case 528627 : count_528627++; amount_528627_280000 += iCBAmount; Recon_amount_528627 += iReconAmount; CB_amount_528627 += iCBAmount; Fee_amount_280000_528627 += feeAmount; Recon_Fee_amount_280000_528627 += reconFeeAmount; break;
									case 538933 : count_538933++; amount_538933_280000 += iCBAmount; Recon_amount_538933 += iReconAmount; CB_amount_538933 += iCBAmount; Fee_amount_280000_538933 += feeAmount; Recon_Fee_amount_280000_538933 += reconFeeAmount; break;
									case 526291 : count_526291++; amount_526291_280000 += iCBAmount; Recon_amount_526291 += iReconAmount; CB_amount_526291 += iCBAmount; Fee_amount_280000_526291 += feeAmount; Recon_Fee_amount_280000_526291 += reconFeeAmount; break;
									case 511096 : count_511096++; amount_511096_280000 += iCBAmount; Recon_amount_511096 += iReconAmount; CB_amount_511096 += iCBAmount; Fee_amount_280000_511096 += feeAmount; Recon_Fee_amount_280000_511096 += reconFeeAmount; break;
									case 511186 : count_511186++; amount_511186_280000 += iCBAmount; Recon_amount_511186 += iReconAmount; CB_amount_511186 += iCBAmount; Fee_amount_280000_511186 += feeAmount; Recon_Fee_amount_280000_511186 += reconFeeAmount; break;
									case 521412 : count_521412++; amount_521412_280000 += iCBAmount; Recon_amount_521412 += iReconAmount; CB_amount_521412 += iCBAmount; Fee_amount_280000_521412 += feeAmount; Recon_Fee_amount_280000_521412 += reconFeeAmount; break;
									case 525698 : count_525698++; amount_525698_280000 += iCBAmount; Recon_amount_525698 += iReconAmount; CB_amount_525698 += iCBAmount; Fee_amount_280000_525698 += feeAmount; Recon_Fee_amount_280000_525698 += reconFeeAmount; break;
									case 510842 : count_510842++; amount_510842_280000 += iCBAmount; Recon_amount_510842 += iReconAmount; CB_amount_510842 += iCBAmount; Fee_amount_280000_510842 += feeAmount; Recon_Fee_amount_280000_510842 += reconFeeAmount; break;
									case 511233 : count_511233++; amount_511233_280000 += iCBAmount; Recon_amount_511233 += iReconAmount; CB_amount_511233 += iCBAmount; Fee_amount_280000_511233 += feeAmount; Recon_Fee_amount_280000_511233 += reconFeeAmount; break;
									case 512856 : count_512856++; amount_512856_280000 += iCBAmount; Recon_amount_512856 += iReconAmount; CB_amount_512856 += iCBAmount; Fee_amount_280000_512856 += feeAmount; Recon_Fee_amount_280000_512856 += reconFeeAmount; break;
									case 512864 : count_512864++; amount_512864_280000 += iCBAmount; Recon_amount_512864 += iReconAmount; CB_amount_512864 += iCBAmount; Fee_amount_280000_512864 += feeAmount; Recon_Fee_amount_280000_512864 += reconFeeAmount; break;
									case 516612 : count_516612++; amount_516612_280000 += iCBAmount; Recon_amount_516612 += iReconAmount; CB_amount_516612 += iCBAmount; Fee_amount_280000_516612 += feeAmount; Recon_Fee_amount_280000_516612 += reconFeeAmount; break;
									case 517628 : count_517628++; amount_517628_280000 += iCBAmount; Recon_amount_517628 += iReconAmount; CB_amount_517628 += iCBAmount; Fee_amount_280000_517628 += feeAmount; Recon_Fee_amount_280000_517628 += reconFeeAmount; break;
									case 519106 : count_519106++; amount_519106_280000 += iCBAmount; Recon_amount_519106 += iReconAmount; CB_amount_519106 += iCBAmount; Fee_amount_280000_519106 += feeAmount; Recon_Fee_amount_280000_519106 += reconFeeAmount; break;
									case 521961 : count_521961++; amount_521961_280000 += iCBAmount; Recon_amount_521961 += iReconAmount; CB_amount_521961 += iCBAmount; Fee_amount_280000_521961 += feeAmount; Recon_Fee_amount_280000_521961 += reconFeeAmount; break;
									case 524432 : count_524432++; amount_524432_280000 += iCBAmount; Recon_amount_524432 += iReconAmount; CB_amount_524432 += iCBAmount; Fee_amount_280000_524432 += feeAmount; Recon_Fee_amount_280000_524432 += reconFeeAmount; break;
									case 531688 : count_531688++; amount_531688_280000 += iCBAmount; Recon_amount_531688 += iReconAmount; CB_amount_531688 += iCBAmount; Fee_amount_280000_531688 += feeAmount; Recon_Fee_amount_280000_531688 += reconFeeAmount; break;
									case 542850 : count_542850++; amount_542850_280000 += iCBAmount; Recon_amount_542850 += iReconAmount; CB_amount_542850 += iCBAmount; Fee_amount_280000_542850 += feeAmount; Recon_Fee_amount_280000_542850 += reconFeeAmount; break;
									case 516511 : count_516511++; amount_516511_280000 += iCBAmount; Recon_amount_516511 += iReconAmount; CB_amount_516511 += iCBAmount; Fee_amount_280000_516511 += feeAmount; Recon_Fee_amount_280000_516511 += reconFeeAmount; break;
									case 531244 : count_531244++; amount_531244_280000 += iCBAmount; Recon_amount_531244 += iReconAmount; CB_amount_531244 += iCBAmount; Fee_amount_280000_531244 += feeAmount; Recon_Fee_amount_280000_531244 += reconFeeAmount; break;
									case 543276 : count_543276++; amount_543276_280000 += iCBAmount; Recon_amount_543276 += iReconAmount; CB_amount_543276 += iCBAmount; Fee_amount_280000_543276 += feeAmount; Recon_Fee_amount_280000_543276 += reconFeeAmount; break;
								}
							}
						}

					} //End of while loop.
				 }//End If for File

				CCA_amount_534771 = Recon_amount_534771 - CB_amount_534771;
				CCA_amount_515462 = Recon_amount_515462 - CB_amount_515462;
				CCA_amount_521444 = Recon_amount_521444 - CB_amount_521444;
				CCA_amount_515480 = Recon_amount_515480 - CB_amount_515480;
				CCA_amount_511340 = Recon_amount_511340 - CB_amount_511340;
				CCA_amount_531150 = Recon_amount_531150 - CB_amount_531150;
				CCA_amount_533313 = Recon_amount_533313 - CB_amount_533313;
				CCA_amount_526293 = Recon_amount_526293 - CB_amount_526293;
				CCA_amount_511479 = Recon_amount_511479 - CB_amount_511479; //added on 07/24/2013
				CCA_amount_516488 = Recon_amount_516488 - CB_amount_516488; //added on 07/24/2013
				CCA_amount_526249 = Recon_amount_526249 - CB_amount_526249;
				CCA_amount_530688 = Recon_amount_530688 - CB_amount_530688;
				CCA_amount_534785 = Recon_amount_534785 - CB_amount_534785;
				CCA_amount_528627 = Recon_amount_528627 - CB_amount_528627;
				CCA_amount_538933 = Recon_amount_538933 - CB_amount_538933;
				CCA_amount_526291 = Recon_amount_526291 - CB_amount_526291;
				CCA_amount_511096 = Recon_amount_511096 - CB_amount_511096;
				CCA_amount_511186 = Recon_amount_511186 - CB_amount_511186;
				CCA_amount_521412 = Recon_amount_521412 - CB_amount_521412;
				CCA_amount_525698 = Recon_amount_525698 - CB_amount_525698;
				CCA_amount_510842 = Recon_amount_510842 - CB_amount_510842;
				CCA_amount_511233 = Recon_amount_511233 - CB_amount_511233;
				CCA_amount_512856 = Recon_amount_512856 - CB_amount_512856;
				CCA_amount_512864 = Recon_amount_512864 - CB_amount_512864;
				CCA_amount_516612 = Recon_amount_516612 - CB_amount_516612;
				CCA_amount_517628 = Recon_amount_517628 - CB_amount_517628;
				CCA_amount_519106 = Recon_amount_519106 - CB_amount_519106;
				CCA_amount_521961 = Recon_amount_521961 - CB_amount_521961;
				CCA_amount_524432 = Recon_amount_524432 - CB_amount_524432;
				CCA_amount_531688 = Recon_amount_531688 - CB_amount_531688;
				CCA_amount_542850 = Recon_amount_542850 - CB_amount_542850;
				CCA_amount_516511 = Recon_amount_516511 - CB_amount_516511; //added on 05/13/2015
				CCA_amount_531244 = Recon_amount_531244 - CB_amount_531244; //added on 08/03/2015
				CCA_amount_543276 = Recon_amount_543276 - CB_amount_543276; //added on 08/05/2015


				String csvHeader = "BIN,TXNCount,Amount(160100),Amount(200000),Amount(003000),Amount(013000),Amount(120000),Amount(010000),Amount(012000),Amount(011000),Amount(280000),CCA(RAmount-CBAmount), FeeAmount(160100), ReconFeeAmount(160100), FeeAmount(200000), ReconFeeAmount(200000), FeeAmount(003000), ReconFeeAmount(003000),FeeAmount(013000), ReconFeeAmount(013000),FeeAmount(120000), ReconFeeAmount(120000),FeeAmount(010000), ReconFeeAmount(010000),FeeAmount(012000), ReconFeeAmount(012000),FeeAmount(011000), ReconFeeAmount(011000),FeeAmount(280000), ReconFeeAmount(280000)"; 

				if(fileName.indexOf("80990") != -1) 
				{
					csvBuffer.append(csvHeader).append("\n");
					csvBufferVMS.append(csvHeader).append("\n");

					//Canadian BINs on GC
					csvBuffer.append("511340").append(",").append(count_511340).append(",").append(amount_511340_160100).append(",").append(amount_511340_200000).append(",").append(amount_511340_003000).append(",").append(amount_511340_013000).append(",").append(amount_511340_120000).append(",").append(amount_511340_010000).append(",").append(amount_511340_012000).append(",").append(amount_511340_011000).append(",").append(amount_511340_280000).append(",").append(CCA_amount_511340).append(",").append(Fee_amount_160100_511340).append(",").append(Recon_Fee_amount_160100_511340).append(",").append(Fee_amount_200000_511340).append(",").append(Recon_Fee_amount_200000_511340).append(",").append(Fee_amount_003000_511340).append(",").append(Recon_Fee_amount_003000_511340).append(",").append(Fee_amount_013000_511340).append(",").append(Recon_Fee_amount_013000_511340).append(",").append(Fee_amount_120000_511340).append(",").append(Recon_Fee_amount_120000_511340).append(",").append(Fee_amount_010000_511340).append(",").append(Recon_Fee_amount_010000_511340).append(",").append(Fee_amount_012000_511340).append(",").append(Recon_Fee_amount_012000_511340).append(",").append(Fee_amount_011000_511340).append(",").append(Recon_Fee_amount_011000_511340).append(",").append(Fee_amount_280000_511340).append(",").append(Recon_Fee_amount_280000_511340).append("\n");
					csvBuffer.append("531150").append(",").append(count_531150).append(",").append(amount_531150_160100).append(",").append(amount_531150_200000).append(",").append(amount_531150_003000).append(",").append(amount_531150_013000).append(",").append(amount_531150_120000).append(",").append(amount_531150_010000).append(",").append(amount_531150_012000).append(",").append(amount_531150_011000).append(",").append(amount_531150_280000).append(",").append(CCA_amount_531150).append(",").append(Fee_amount_160100_531150).append(",").append(Recon_Fee_amount_160100_531150).append(",").append(Fee_amount_200000_531150).append(",").append(Recon_Fee_amount_200000_531150).append(",").append(Fee_amount_003000_531150).append(",").append(Recon_Fee_amount_003000_531150).append(",").append(Fee_amount_013000_531150).append(",").append(Recon_Fee_amount_013000_531150).append(",").append(Fee_amount_120000_531150).append(",").append(Recon_Fee_amount_120000_531150).append(",").append(Fee_amount_010000_531150).append(",").append(Recon_Fee_amount_010000_531150).append(",").append(Fee_amount_012000_531150).append(",").append(Recon_Fee_amount_012000_531150).append(",").append(Fee_amount_011000_531150).append(",").append(Recon_Fee_amount_011000_531150).append(",").append(Fee_amount_280000_531150).append(",").append(Recon_Fee_amount_280000_531150).append("\n");
					csvBuffer.append("533313").append(",").append(count_533313).append(",").append(amount_533313_160100).append(",").append(amount_533313_200000).append(",").append(amount_533313_003000).append(",").append(amount_533313_013000).append(",").append(amount_533313_120000).append(",").append(amount_533313_010000).append(",").append(amount_533313_012000).append(",").append(amount_533313_011000).append(",").append(amount_533313_280000).append(",").append(CCA_amount_533313).append(",").append(Fee_amount_160100_533313).append(",").append(Recon_Fee_amount_160100_533313).append(",").append(Fee_amount_200000_533313).append(",").append(Recon_Fee_amount_200000_533313).append(",").append(Fee_amount_003000_533313).append(",").append(Recon_Fee_amount_003000_533313).append(",").append(Fee_amount_013000_533313).append(",").append(Recon_Fee_amount_013000_533313).append(",").append(Fee_amount_120000_533313).append(",").append(Recon_Fee_amount_120000_533313).append(",").append(Fee_amount_010000_533313).append(",").append(Recon_Fee_amount_010000_533313).append(",").append(Fee_amount_012000_533313).append(",").append(Recon_Fee_amount_012000_533313).append(",").append(Fee_amount_011000_533313).append(",").append(Recon_Fee_amount_011000_533313).append(",").append(Fee_amount_280000_533313).append(",").append(Recon_Fee_amount_280000_533313).append("\n");
					csvBuffer.append("531244").append(",").append(count_531244).append(",").append(amount_531244_160100).append(",").append(amount_531244_200000).append(",").append(amount_531244_003000).append(",").append(amount_531244_013000).append(",").append(amount_531244_120000).append(",").append(amount_531244_010000).append(",").append(amount_531244_012000).append(",").append(amount_531244_011000).append(",").append(amount_531244_280000).append(",").append(CCA_amount_531244).append(",").append(Fee_amount_160100_531244).append(",").append(Recon_Fee_amount_160100_531244).append(",").append(Fee_amount_200000_531244).append(",").append(Recon_Fee_amount_200000_531244).append(",").append(Fee_amount_003000_531244).append(",").append(Recon_Fee_amount_003000_531244).append(",").append(Fee_amount_013000_531244).append(",").append(Recon_Fee_amount_013000_531244).append(",").append(Fee_amount_120000_531244).append(",").append(Recon_Fee_amount_120000_531244).append(",").append(Fee_amount_010000_531244).append(",").append(Recon_Fee_amount_010000_531244).append(",").append(Fee_amount_012000_531244).append(",").append(Recon_Fee_amount_012000_531244).append(",").append(Fee_amount_011000_531244).append(",").append(Recon_Fee_amount_011000_531244).append(",").append(Fee_amount_280000_531244).append(",").append(Recon_Fee_amount_280000_531244).append("\n");

					//Canadian BINs on VMS
					csvBufferVMS.append("521961").append(",").append(count_521961).append(",").append(amount_521961_160100).append(",").append(amount_521961_200000).append(",").append(amount_521961_003000).append(",").append(amount_521961_013000).append(",").append(amount_521961_120000).append(",").append(amount_521961_010000).append(",").append(amount_521961_012000).append(",").append(amount_521961_011000).append(",").append(amount_521961_280000).append(",").append(CCA_amount_521961).append(",").append(Fee_amount_160100_521961).append(",").append(Recon_Fee_amount_160100_521961).append(",").append(Fee_amount_200000_521961).append(",").append(Recon_Fee_amount_200000_521961).append(",").append(Fee_amount_003000_521961).append(",").append(Recon_Fee_amount_003000_521961).append(",").append(Fee_amount_013000_521961).append(",").append(Recon_Fee_amount_013000_521961).append(",").append(Fee_amount_120000_521961).append(",").append(Recon_Fee_amount_120000_521961).append(",").append(Fee_amount_010000_521961).append(",").append(Recon_Fee_amount_010000_521961).append(",").append(Fee_amount_012000_521961).append(",").append(Recon_Fee_amount_012000_521961).append(",").append(Fee_amount_011000_521961).append(",").append(Recon_Fee_amount_011000_521961).append(",").append(Fee_amount_280000_521961).append(",").append(Recon_Fee_amount_280000_521961).append("\n");
					csvBufferVMS.append("516511").append(",").append(count_516511).append(",").append(amount_516511_160100).append(",").append(amount_516511_200000).append(",").append(amount_516511_003000).append(",").append(amount_516511_013000).append(",").append(amount_516511_120000).append(",").append(amount_516511_010000).append(",").append(amount_516511_012000).append(",").append(amount_516511_011000).append(",").append(amount_516511_280000).append(",").append(CCA_amount_516511).append(",").append(Fee_amount_160100_516511).append(",").append(Recon_Fee_amount_160100_516511).append(",").append(Fee_amount_200000_516511).append(",").append(Recon_Fee_amount_200000_516511).append(",").append(Fee_amount_003000_516511).append(",").append(Recon_Fee_amount_003000_516511).append(",").append(Fee_amount_013000_516511).append(",").append(Recon_Fee_amount_013000_516511).append(",").append(Fee_amount_120000_516511).append(",").append(Recon_Fee_amount_120000_516511).append(",").append(Fee_amount_010000_516511).append(",").append(Recon_Fee_amount_010000_516511).append(",").append(Fee_amount_012000_516511).append(",").append(Recon_Fee_amount_012000_516511).append(",").append(Fee_amount_011000_516511).append(",").append(Recon_Fee_amount_011000_516511).append(",").append(Fee_amount_280000_516511).append(",").append(Recon_Fee_amount_280000_516511).append("\n");
				} 
				else if(fileName.indexOf("80266") != -1) //80266 
				{
					csvBuffer.append(csvHeader).append("\n");
					csvBufferVMS.append(csvHeader).append("\n");

					//US BINs on GC
					csvBuffer.append("534771").append(",").append(count_534771).append(",").append(amount_534771_160100).append(",").append(amount_534771_200000).append(",").append(amount_534771_003000).append(",").append(amount_534771_013000).append(",").append(amount_534771_120000).append(",").append(amount_534771_010000).append(",").append(amount_534771_012000).append(",").append(amount_534771_011000).append(",").append(amount_534771_280000).append(",").append(CCA_amount_534771).append(",").append(Fee_amount_160100_534771).append(",").append(Recon_Fee_amount_160100_534771).append(",").append(Fee_amount_200000_534771).append(",").append(Recon_Fee_amount_200000_534771).append(",").append(Fee_amount_003000_534771).append(",").append(Recon_Fee_amount_003000_534771).append(",").append(Fee_amount_013000_534771).append(",").append(Recon_Fee_amount_013000_534771).append(",").append(Fee_amount_120000_534771).append(",").append(Recon_Fee_amount_120000_534771).append(",").append(Fee_amount_010000_534771).append(",").append(Recon_Fee_amount_010000_534771).append(",").append(Fee_amount_012000_534771).append(",").append(Recon_Fee_amount_012000_534771).append(",").append(Fee_amount_011000_534771).append(",").append(Recon_Fee_amount_011000_534771).append(",").append(Fee_amount_280000_534771).append(",").append(Recon_Fee_amount_280000_534771).append("\n");
					csvBuffer.append("515462").append(",").append(count_515462).append(",").append(amount_515462_160100).append(",").append(amount_515462_200000).append(",").append(amount_515462_003000).append(",").append(amount_515462_013000).append(",").append(amount_515462_120000).append(",").append(amount_515462_010000).append(",").append(amount_515462_012000).append(",").append(amount_515462_011000).append(",").append(amount_515462_280000).append(",").append(CCA_amount_515462).append(",").append(Fee_amount_160100_515462).append(",").append(Recon_Fee_amount_160100_515462).append(",").append(Fee_amount_200000_515462).append(",").append(Recon_Fee_amount_200000_515462).append(",").append(Fee_amount_003000_515462).append(",").append(Recon_Fee_amount_003000_515462).append(",").append(Fee_amount_013000_515462).append(",").append(Recon_Fee_amount_013000_515462).append(",").append(Fee_amount_120000_515462).append(",").append(Recon_Fee_amount_120000_515462).append(",").append(Fee_amount_010000_515462).append(",").append(Recon_Fee_amount_010000_515462).append(",").append(Fee_amount_012000_515462).append(",").append(Recon_Fee_amount_012000_515462).append(",").append(Fee_amount_011000_515462).append(",").append(Recon_Fee_amount_011000_515462).append(",").append(Fee_amount_280000_515462).append(",").append(Recon_Fee_amount_280000_515462).append("\n");
					csvBuffer.append("521444").append(",").append(count_521444).append(",").append(amount_521444_160100).append(",").append(amount_521444_200000).append(",").append(amount_521444_003000).append(",").append(amount_521444_013000).append(",").append(amount_521444_120000).append(",").append(amount_521444_010000).append(",").append(amount_521444_012000).append(",").append(amount_521444_011000).append(",").append(amount_521444_280000).append(",").append(CCA_amount_521444).append(",").append(Fee_amount_160100_521444).append(",").append(Recon_Fee_amount_160100_521444).append(",").append(Fee_amount_200000_521444).append(",").append(Recon_Fee_amount_200000_521444).append(",").append(Fee_amount_003000_521444).append(",").append(Recon_Fee_amount_003000_521444).append(",").append(Fee_amount_013000_521444).append(",").append(Recon_Fee_amount_013000_521444).append(",").append(Fee_amount_120000_521444).append(",").append(Recon_Fee_amount_120000_521444).append(",").append(Fee_amount_010000_521444).append(",").append(Recon_Fee_amount_010000_521444).append(",").append(Fee_amount_012000_521444).append(",").append(Recon_Fee_amount_012000_521444).append(",").append(Fee_amount_011000_521444).append(",").append(Recon_Fee_amount_011000_521444).append(",").append(Fee_amount_280000_521444).append(",").append(Recon_Fee_amount_280000_521444).append("\n");
					csvBuffer.append("515480").append(",").append(count_515480).append(",").append(amount_515480_160100).append(",").append(amount_515480_200000).append(",").append(amount_515480_003000).append(",").append(amount_515480_013000).append(",").append(amount_515480_120000).append(",").append(amount_515480_010000).append(",").append(amount_515480_012000).append(",").append(amount_515480_011000).append(",").append(amount_515480_280000).append(",").append(CCA_amount_515480).append(",").append(Fee_amount_160100_515480).append(",").append(Recon_Fee_amount_160100_515480).append(",").append(Fee_amount_200000_515480).append(",").append(Recon_Fee_amount_200000_515480).append(",").append(Fee_amount_003000_515480).append(",").append(Recon_Fee_amount_003000_515480).append(",").append(Fee_amount_013000_515480).append(",").append(Recon_Fee_amount_013000_515480).append(",").append(Fee_amount_120000_515480).append(",").append(Recon_Fee_amount_120000_515480).append(",").append(Fee_amount_010000_515480).append(",").append(Recon_Fee_amount_010000_515480).append(",").append(Fee_amount_012000_515480).append(",").append(Recon_Fee_amount_012000_515480).append(",").append(Fee_amount_011000_515480).append(",").append(Recon_Fee_amount_011000_515480).append(",").append(Fee_amount_280000_515480).append(",").append(Recon_Fee_amount_280000_515480).append("\n");
					csvBuffer.append("526293").append(",").append(count_526293).append(",").append(amount_526293_160100).append(",").append(amount_526293_200000).append(",").append(amount_526293_003000).append(",").append(amount_526293_013000).append(",").append(amount_526293_120000).append(",").append(amount_526293_010000).append(",").append(amount_526293_012000).append(",").append(amount_526293_011000).append(",").append(amount_526293_280000).append(",").append(CCA_amount_526293).append(",").append(Fee_amount_160100_526293).append(",").append(Recon_Fee_amount_160100_526293).append(",").append(Fee_amount_200000_526293).append(",").append(Recon_Fee_amount_200000_526293).append(",").append(Fee_amount_003000_526293).append(",").append(Recon_Fee_amount_003000_526293).append(",").append(Fee_amount_013000_526293).append(",").append(Recon_Fee_amount_013000_526293).append(",").append(Fee_amount_120000_526293).append(",").append(Recon_Fee_amount_120000_526293).append(",").append(Fee_amount_010000_526293).append(",").append(Recon_Fee_amount_010000_526293).append(",").append(Fee_amount_012000_526293).append(",").append(Recon_Fee_amount_012000_526293).append(",").append(Fee_amount_011000_526293).append(",").append(Recon_Fee_amount_011000_526293).append(",").append(Fee_amount_280000_526293).append(",").append(Recon_Fee_amount_280000_526293).append("\n");
					csvBuffer.append("511479").append(",").append(count_511479).append(",").append(amount_511479_160100).append(",").append(amount_511479_200000).append(",").append(amount_511479_003000).append(",").append(amount_511479_013000).append(",").append(amount_511479_120000).append(",").append(amount_511479_010000).append(",").append(amount_511479_012000).append(",").append(amount_511479_011000).append(",").append(amount_511479_280000).append(",").append(CCA_amount_511479).append(",").append(Fee_amount_160100_511479).append(",").append(Recon_Fee_amount_160100_511479).append(",").append(Fee_amount_200000_511479).append(",").append(Recon_Fee_amount_200000_511479).append(",").append(Fee_amount_003000_511479).append(",").append(Recon_Fee_amount_003000_511479).append(",").append(Fee_amount_013000_511479).append(",").append(Recon_Fee_amount_013000_511479).append(",").append(Fee_amount_120000_511479).append(",").append(Recon_Fee_amount_120000_511479).append(",").append(Fee_amount_010000_511479).append(",").append(Recon_Fee_amount_010000_511479).append(",").append(Fee_amount_012000_511479).append(",").append(Recon_Fee_amount_012000_511479).append(",").append(Fee_amount_011000_511479).append(",").append(Recon_Fee_amount_011000_511479).append(",").append(Fee_amount_280000_511479).append(",").append(Recon_Fee_amount_280000_511479).append("\n");
					csvBuffer.append("516488").append(",").append(count_516488).append(",").append(amount_516488_160100).append(",").append(amount_516488_200000).append(",").append(amount_516488_003000).append(",").append(amount_516488_013000).append(",").append(amount_516488_120000).append(",").append(amount_516488_010000).append(",").append(amount_516488_012000).append(",").append(amount_516488_011000).append(",").append(amount_516488_280000).append(",").append(CCA_amount_516488).append(",").append(Fee_amount_160100_516488).append(",").append(Recon_Fee_amount_160100_516488).append(",").append(Fee_amount_200000_516488).append(",").append(Recon_Fee_amount_200000_516488).append(",").append(Fee_amount_003000_516488).append(",").append(Recon_Fee_amount_003000_516488).append(",").append(Fee_amount_013000_516488).append(",").append(Recon_Fee_amount_013000_516488).append(",").append(Fee_amount_120000_516488).append(",").append(Recon_Fee_amount_120000_516488).append(",").append(Fee_amount_010000_516488).append(",").append(Recon_Fee_amount_010000_516488).append(",").append(Fee_amount_012000_516488).append(",").append(Recon_Fee_amount_012000_516488).append(",").append(Fee_amount_011000_516488).append(",").append(Recon_Fee_amount_011000_516488).append(",").append(Fee_amount_280000_516488).append(",").append(Recon_Fee_amount_280000_516488).append("\n");
					csvBuffer.append("526249").append(",").append(count_526249).append(",").append(amount_526249_160100).append(",").append(amount_526249_200000).append(",").append(amount_526249_003000).append(",").append(amount_526249_013000).append(",").append(amount_526249_120000).append(",").append(amount_526249_010000).append(",").append(amount_526249_012000).append(",").append(amount_526249_011000).append(",").append(amount_526249_280000).append(",").append(CCA_amount_526249).append(",").append(Fee_amount_160100_526249).append(",").append(Recon_Fee_amount_160100_526249).append(",").append(Fee_amount_200000_526249).append(",").append(Recon_Fee_amount_200000_526249).append(",").append(Fee_amount_003000_526249).append(",").append(Recon_Fee_amount_003000_526249).append(",").append(Fee_amount_013000_526249).append(",").append(Recon_Fee_amount_013000_526249).append(",").append(Fee_amount_120000_526249).append(",").append(Recon_Fee_amount_120000_526249).append(",").append(Fee_amount_010000_526249).append(",").append(Recon_Fee_amount_010000_526249).append(",").append(Fee_amount_012000_526249).append(",").append(Recon_Fee_amount_012000_526249).append(",").append(Fee_amount_011000_526249).append(",").append(Recon_Fee_amount_011000_526249).append(",").append(Fee_amount_280000_526249).append(",").append(Recon_Fee_amount_280000_526249).append("\n");
					csvBuffer.append("534785").append(",").append(count_534785).append(",").append(amount_534785_160100).append(",").append(amount_534785_200000).append(",").append(amount_534785_003000).append(",").append(amount_534785_013000).append(",").append(amount_534785_120000).append(",").append(amount_534785_010000).append(",").append(amount_534785_012000).append(",").append(amount_534785_011000).append(",").append(amount_534785_280000).append(",").append(CCA_amount_534785).append(",").append(Fee_amount_160100_534785).append(",").append(Recon_Fee_amount_160100_534785).append(",").append(Fee_amount_200000_534785).append(",").append(Recon_Fee_amount_200000_534785).append(",").append(Fee_amount_003000_534785).append(",").append(Recon_Fee_amount_003000_534785).append(",").append(Fee_amount_013000_534785).append(",").append(Recon_Fee_amount_013000_534785).append(",").append(Fee_amount_120000_534785).append(",").append(Recon_Fee_amount_120000_534785).append(",").append(Fee_amount_010000_534785).append(",").append(Recon_Fee_amount_010000_534785).append(",").append(Fee_amount_012000_534785).append(",").append(Recon_Fee_amount_012000_534785).append(",").append(Fee_amount_011000_534785).append(",").append(Recon_Fee_amount_011000_534785).append(",").append(Fee_amount_280000_534785).append(",").append(Recon_Fee_amount_280000_534785).append("\n");
					csvBuffer.append("525698").append(",").append(count_525698).append(",").append(amount_525698_160100).append(",").append(amount_525698_200000).append(",").append(amount_525698_003000).append(",").append(amount_525698_013000).append(",").append(amount_525698_120000).append(",").append(amount_525698_010000).append(",").append(amount_525698_012000).append(",").append(amount_525698_011000).append(",").append(amount_525698_280000).append(",").append(CCA_amount_525698).append(",").append(Fee_amount_160100_525698).append(",").append(Recon_Fee_amount_160100_525698).append(",").append(Fee_amount_200000_525698).append(",").append(Recon_Fee_amount_200000_525698).append(",").append(Fee_amount_003000_525698).append(",").append(Recon_Fee_amount_003000_525698).append(",").append(Fee_amount_013000_525698).append(",").append(Recon_Fee_amount_013000_525698).append(",").append(Fee_amount_120000_525698).append(",").append(Recon_Fee_amount_120000_525698).append(",").append(Fee_amount_010000_525698).append(",").append(Recon_Fee_amount_010000_525698).append(",").append(Fee_amount_012000_525698).append(",").append(Recon_Fee_amount_012000_525698).append(",").append(Fee_amount_011000_525698).append(",").append(Recon_Fee_amount_011000_525698).append(",").append(Fee_amount_280000_525698).append(",").append(Recon_Fee_amount_280000_525698).append("\n");
					csvBuffer.append("511233").append(",").append(count_511233).append(",").append(amount_511233_160100).append(",").append(amount_511233_200000).append(",").append(amount_511233_003000).append(",").append(amount_511233_013000).append(",").append(amount_511233_120000).append(",").append(amount_511233_010000).append(",").append(amount_511233_012000).append(",").append(amount_511233_011000).append(",").append(amount_511233_280000).append(",").append(CCA_amount_511233).append(",").append(Fee_amount_160100_511233).append(",").append(Recon_Fee_amount_160100_511233).append(",").append(Fee_amount_200000_511233).append(",").append(Recon_Fee_amount_200000_511233).append(",").append(Fee_amount_003000_511233).append(",").append(Recon_Fee_amount_003000_511233).append(",").append(Fee_amount_013000_511233).append(",").append(Recon_Fee_amount_013000_511233).append(",").append(Fee_amount_120000_511233).append(",").append(Recon_Fee_amount_120000_511233).append(",").append(Fee_amount_010000_511233).append(",").append(Recon_Fee_amount_010000_511233).append(",").append(Fee_amount_012000_511233).append(",").append(Recon_Fee_amount_012000_511233).append(",").append(Fee_amount_011000_511233).append(",").append(Recon_Fee_amount_011000_511233).append(",").append(Fee_amount_280000_511233).append(",").append(Recon_Fee_amount_280000_511233).append("\n");
					csvBuffer.append("512856").append(",").append(count_512856).append(",").append(amount_512856_160100).append(",").append(amount_512856_200000).append(",").append(amount_512856_003000).append(",").append(amount_512856_013000).append(",").append(amount_512856_120000).append(",").append(amount_512856_010000).append(",").append(amount_512856_012000).append(",").append(amount_512856_011000).append(",").append(amount_512856_280000).append(",").append(CCA_amount_512856).append(",").append(Fee_amount_160100_512856).append(",").append(Recon_Fee_amount_160100_512856).append(",").append(Fee_amount_200000_512856).append(",").append(Recon_Fee_amount_200000_512856).append(",").append(Fee_amount_003000_512856).append(",").append(Recon_Fee_amount_003000_512856).append(",").append(Fee_amount_013000_512856).append(",").append(Recon_Fee_amount_013000_512856).append(",").append(Fee_amount_120000_512856).append(",").append(Recon_Fee_amount_120000_512856).append(",").append(Fee_amount_010000_512856).append(",").append(Recon_Fee_amount_010000_512856).append(",").append(Fee_amount_012000_512856).append(",").append(Recon_Fee_amount_012000_512856).append(",").append(Fee_amount_011000_512856).append(",").append(Recon_Fee_amount_011000_512856).append(",").append(Fee_amount_280000_512856).append(",").append(Recon_Fee_amount_280000_512856).append("\n");
					csvBuffer.append("512864").append(",").append(count_512864).append(",").append(amount_512864_160100).append(",").append(amount_512864_200000).append(",").append(amount_512864_003000).append(",").append(amount_512864_013000).append(",").append(amount_512864_120000).append(",").append(amount_512864_010000).append(",").append(amount_512864_012000).append(",").append(amount_512864_011000).append(",").append(amount_512864_280000).append(",").append(CCA_amount_512864).append(",").append(Fee_amount_160100_512864).append(",").append(Recon_Fee_amount_160100_512864).append(",").append(Fee_amount_200000_512864).append(",").append(Recon_Fee_amount_200000_512864).append(",").append(Fee_amount_003000_512864).append(",").append(Recon_Fee_amount_003000_512864).append(",").append(Fee_amount_013000_512864).append(",").append(Recon_Fee_amount_013000_512864).append(",").append(Fee_amount_120000_512864).append(",").append(Recon_Fee_amount_120000_512864).append(",").append(Fee_amount_010000_512864).append(",").append(Recon_Fee_amount_010000_512864).append(",").append(Fee_amount_012000_512864).append(",").append(Recon_Fee_amount_012000_512864).append(",").append(Fee_amount_011000_512864).append(",").append(Recon_Fee_amount_011000_512864).append(",").append(Fee_amount_280000_512864).append(",").append(Recon_Fee_amount_280000_512864).append("\n");
					csvBuffer.append("543276").append(",").append(count_543276).append(",").append(amount_543276_160100).append(",").append(amount_543276_200000).append(",").append(amount_543276_003000).append(",").append(amount_543276_013000).append(",").append(amount_543276_120000).append(",").append(amount_543276_010000).append(",").append(amount_543276_012000).append(",").append(amount_543276_011000).append(",").append(amount_543276_280000).append(",").append(CCA_amount_543276).append(",").append(Fee_amount_160100_543276).append(",").append(Recon_Fee_amount_160100_543276).append(",").append(Fee_amount_200000_543276).append(",").append(Recon_Fee_amount_200000_543276).append(",").append(Fee_amount_003000_543276).append(",").append(Recon_Fee_amount_003000_543276).append(",").append(Fee_amount_013000_543276).append(",").append(Recon_Fee_amount_013000_543276).append(",").append(Fee_amount_120000_543276).append(",").append(Recon_Fee_amount_120000_543276).append(",").append(Fee_amount_010000_543276).append(",").append(Recon_Fee_amount_010000_543276).append(",").append(Fee_amount_012000_543276).append(",").append(Recon_Fee_amount_012000_543276).append(",").append(Fee_amount_011000_543276).append(",").append(Recon_Fee_amount_011000_543276).append(",").append(Fee_amount_280000_543276).append(",").append(Recon_Fee_amount_280000_543276).append("\n");

					//US BINs on VMS
					csvBufferVMS.append("528627").append(",").append(count_528627).append(",").append(amount_528627_160100).append(",").append(amount_528627_200000).append(",").append(amount_528627_003000).append(",").append(amount_528627_013000).append(",").append(amount_528627_120000).append(",").append(amount_528627_010000).append(",").append(amount_528627_012000).append(",").append(amount_528627_011000).append(",").append(amount_528627_280000).append(",").append(CCA_amount_528627).append(",").append(Fee_amount_160100_528627).append(",").append(Recon_Fee_amount_160100_528627).append(",").append(Fee_amount_200000_528627).append(",").append(Recon_Fee_amount_200000_528627).append(",").append(Fee_amount_003000_528627).append(",").append(Recon_Fee_amount_003000_528627).append(",").append(Fee_amount_013000_528627).append(",").append(Recon_Fee_amount_013000_528627).append(",").append(Fee_amount_120000_528627).append(",").append(Recon_Fee_amount_120000_528627).append(",").append(Fee_amount_010000_528627).append(",").append(Recon_Fee_amount_010000_528627).append(",").append(Fee_amount_012000_528627).append(",").append(Recon_Fee_amount_012000_528627).append(",").append(Fee_amount_011000_528627).append(",").append(Recon_Fee_amount_011000_528627).append(",").append(Fee_amount_280000_528627).append(",").append(Recon_Fee_amount_280000_528627).append("\n");
					csvBufferVMS.append("538933").append(",").append(count_538933).append(",").append(amount_538933_160100).append(",").append(amount_538933_200000).append(",").append(amount_538933_003000).append(",").append(amount_538933_013000).append(",").append(amount_538933_120000).append(",").append(amount_538933_010000).append(",").append(amount_538933_012000).append(",").append(amount_538933_011000).append(",").append(amount_538933_280000).append(",").append(CCA_amount_538933).append(",").append(Fee_amount_160100_538933).append(",").append(Recon_Fee_amount_160100_538933).append(",").append(Fee_amount_200000_538933).append(",").append(Recon_Fee_amount_200000_538933).append(",").append(Fee_amount_003000_538933).append(",").append(Recon_Fee_amount_003000_538933).append(",").append(Fee_amount_013000_538933).append(",").append(Recon_Fee_amount_013000_538933).append(",").append(Fee_amount_120000_538933).append(",").append(Recon_Fee_amount_120000_538933).append(",").append(Fee_amount_010000_538933).append(",").append(Recon_Fee_amount_010000_538933).append(",").append(Fee_amount_012000_538933).append(",").append(Recon_Fee_amount_012000_538933).append(",").append(Fee_amount_011000_538933).append(",").append(Recon_Fee_amount_011000_538933).append(",").append(Fee_amount_280000_538933).append(",").append(Recon_Fee_amount_280000_538933).append("\n");
					csvBufferVMS.append("526291").append(",").append(count_526291).append(",").append(amount_526291_160100).append(",").append(amount_526291_200000).append(",").append(amount_526291_003000).append(",").append(amount_526291_013000).append(",").append(amount_526291_120000).append(",").append(amount_526291_010000).append(",").append(amount_526291_012000).append(",").append(amount_526291_011000).append(",").append(amount_526291_280000).append(",").append(CCA_amount_526291).append(",").append(Fee_amount_160100_526291).append(",").append(Recon_Fee_amount_160100_526291).append(",").append(Fee_amount_200000_526291).append(",").append(Recon_Fee_amount_200000_526291).append(",").append(Fee_amount_003000_526291).append(",").append(Recon_Fee_amount_003000_526291).append(",").append(Fee_amount_013000_526291).append(",").append(Recon_Fee_amount_013000_526291).append(",").append(Fee_amount_120000_526291).append(",").append(Recon_Fee_amount_120000_526291).append(",").append(Fee_amount_010000_526291).append(",").append(Recon_Fee_amount_010000_526291).append(",").append(Fee_amount_012000_526291).append(",").append(Recon_Fee_amount_012000_526291).append(",").append(Fee_amount_011000_526291).append(",").append(Recon_Fee_amount_011000_526291).append(",").append(Fee_amount_280000_526291).append(",").append(Recon_Fee_amount_280000_526291).append("\n");
					csvBufferVMS.append("511096").append(",").append(count_511096).append(",").append(amount_511096_160100).append(",").append(amount_511096_200000).append(",").append(amount_511096_003000).append(",").append(amount_511096_013000).append(",").append(amount_511096_120000).append(",").append(amount_511096_010000).append(",").append(amount_511096_012000).append(",").append(amount_511096_011000).append(",").append(amount_511096_280000).append(",").append(CCA_amount_511096).append(",").append(Fee_amount_160100_511096).append(",").append(Recon_Fee_amount_160100_511096).append(",").append(Fee_amount_200000_511096).append(",").append(Recon_Fee_amount_200000_511096).append(",").append(Fee_amount_003000_511096).append(",").append(Recon_Fee_amount_003000_511096).append(",").append(Fee_amount_013000_511096).append(",").append(Recon_Fee_amount_013000_511096).append(",").append(Fee_amount_120000_511096).append(",").append(Recon_Fee_amount_120000_511096).append(",").append(Fee_amount_010000_511096).append(",").append(Recon_Fee_amount_010000_511096).append(",").append(Fee_amount_012000_511096).append(",").append(Recon_Fee_amount_012000_511096).append(",").append(Fee_amount_011000_511096).append(",").append(Recon_Fee_amount_011000_511096).append(",").append(Fee_amount_280000_511096).append(",").append(Recon_Fee_amount_280000_511096).append("\n");
					csvBufferVMS.append("511186").append(",").append(count_511186).append(",").append(amount_511186_160100).append(",").append(amount_511186_200000).append(",").append(amount_511186_003000).append(",").append(amount_511186_013000).append(",").append(amount_511186_120000).append(",").append(amount_511186_010000).append(",").append(amount_511186_012000).append(",").append(amount_511186_011000).append(",").append(amount_511186_280000).append(",").append(CCA_amount_511186).append(",").append(Fee_amount_160100_511186).append(",").append(Recon_Fee_amount_160100_511186).append(",").append(Fee_amount_200000_511186).append(",").append(Recon_Fee_amount_200000_511186).append(",").append(Fee_amount_003000_511186).append(",").append(Recon_Fee_amount_003000_511186).append(",").append(Fee_amount_013000_511186).append(",").append(Recon_Fee_amount_013000_511186).append(",").append(Fee_amount_120000_511186).append(",").append(Recon_Fee_amount_120000_511186).append(",").append(Fee_amount_010000_511186).append(",").append(Recon_Fee_amount_010000_511186).append(",").append(Fee_amount_012000_511186).append(",").append(Recon_Fee_amount_012000_511186).append(",").append(Fee_amount_011000_511186).append(",").append(Recon_Fee_amount_011000_511186).append(",").append(Fee_amount_280000_511186).append(",").append(Recon_Fee_amount_280000_511186).append("\n");
					csvBufferVMS.append("521412").append(",").append(count_521412).append(",").append(amount_521412_160100).append(",").append(amount_521412_200000).append(",").append(amount_521412_003000).append(",").append(amount_521412_013000).append(",").append(amount_521412_120000).append(",").append(amount_521412_010000).append(",").append(amount_521412_012000).append(",").append(amount_521412_011000).append(",").append(amount_521412_280000).append(",").append(CCA_amount_521412).append(",").append(Fee_amount_160100_521412).append(",").append(Recon_Fee_amount_160100_521412).append(",").append(Fee_amount_200000_521412).append(",").append(Recon_Fee_amount_200000_521412).append(",").append(Fee_amount_003000_521412).append(",").append(Recon_Fee_amount_003000_521412).append(",").append(Fee_amount_013000_521412).append(",").append(Recon_Fee_amount_013000_521412).append(",").append(Fee_amount_120000_521412).append(",").append(Recon_Fee_amount_120000_521412).append(",").append(Fee_amount_010000_521412).append(",").append(Recon_Fee_amount_010000_521412).append(",").append(Fee_amount_012000_521412).append(",").append(Recon_Fee_amount_012000_521412).append(",").append(Fee_amount_011000_521412).append(",").append(Recon_Fee_amount_011000_521412).append(",").append(Fee_amount_280000_521412).append(",").append(Recon_Fee_amount_280000_521412).append("\n");
					csvBufferVMS.append("510842").append(",").append(count_510842).append(",").append(amount_510842_160100).append(",").append(amount_510842_200000).append(",").append(amount_510842_003000).append(",").append(amount_510842_013000).append(",").append(amount_510842_120000).append(",").append(amount_510842_010000).append(",").append(amount_510842_012000).append(",").append(amount_510842_011000).append(",").append(amount_510842_280000).append(",").append(CCA_amount_510842).append(",").append(Fee_amount_160100_510842).append(",").append(Recon_Fee_amount_160100_510842).append(",").append(Fee_amount_200000_510842).append(",").append(Recon_Fee_amount_200000_510842).append(",").append(Fee_amount_003000_510842).append(",").append(Recon_Fee_amount_003000_510842).append(",").append(Fee_amount_013000_510842).append(",").append(Recon_Fee_amount_013000_510842).append(",").append(Fee_amount_120000_510842).append(",").append(Recon_Fee_amount_120000_510842).append(",").append(Fee_amount_010000_510842).append(",").append(Recon_Fee_amount_010000_510842).append(",").append(Fee_amount_012000_510842).append(",").append(Recon_Fee_amount_012000_510842).append(",").append(Fee_amount_011000_510842).append(",").append(Recon_Fee_amount_011000_510842).append(",").append(Fee_amount_280000_510842).append(",").append(Recon_Fee_amount_280000_510842).append("\n");
					csvBufferVMS.append("517628").append(",").append(count_517628).append(",").append(amount_517628_160100).append(",").append(amount_517628_200000).append(",").append(amount_517628_003000).append(",").append(amount_517628_013000).append(",").append(amount_517628_120000).append(",").append(amount_517628_010000).append(",").append(amount_517628_012000).append(",").append(amount_517628_011000).append(",").append(amount_517628_280000).append(",").append(CCA_amount_517628).append(",").append(Fee_amount_160100_517628).append(",").append(Recon_Fee_amount_160100_517628).append(",").append(Fee_amount_200000_517628).append(",").append(Recon_Fee_amount_200000_517628).append(",").append(Fee_amount_003000_517628).append(",").append(Recon_Fee_amount_003000_517628).append(",").append(Fee_amount_013000_517628).append(",").append(Recon_Fee_amount_013000_517628).append(",").append(Fee_amount_120000_517628).append(",").append(Recon_Fee_amount_120000_517628).append(",").append(Fee_amount_010000_517628).append(",").append(Recon_Fee_amount_010000_517628).append(",").append(Fee_amount_012000_517628).append(",").append(Recon_Fee_amount_012000_517628).append(",").append(Fee_amount_011000_517628).append(",").append(Recon_Fee_amount_011000_517628).append(",").append(Fee_amount_280000_517628).append(",").append(Recon_Fee_amount_280000_517628).append("\n");
					csvBufferVMS.append("519106").append(",").append(count_519106).append(",").append(amount_519106_160100).append(",").append(amount_519106_200000).append(",").append(amount_519106_003000).append(",").append(amount_519106_013000).append(",").append(amount_519106_120000).append(",").append(amount_519106_010000).append(",").append(amount_519106_012000).append(",").append(amount_519106_011000).append(",").append(amount_519106_280000).append(",").append(CCA_amount_519106).append(",").append(Fee_amount_160100_519106).append(",").append(Recon_Fee_amount_160100_519106).append(",").append(Fee_amount_200000_519106).append(",").append(Recon_Fee_amount_200000_519106).append(",").append(Fee_amount_003000_519106).append(",").append(Recon_Fee_amount_003000_519106).append(",").append(Fee_amount_013000_519106).append(",").append(Recon_Fee_amount_013000_519106).append(",").append(Fee_amount_120000_519106).append(",").append(Recon_Fee_amount_120000_519106).append(",").append(Fee_amount_010000_519106).append(",").append(Recon_Fee_amount_010000_519106).append(",").append(Fee_amount_012000_519106).append(",").append(Recon_Fee_amount_012000_519106).append(",").append(Fee_amount_011000_519106).append(",").append(Recon_Fee_amount_011000_519106).append(",").append(Fee_amount_280000_519106).append(",").append(Recon_Fee_amount_280000_519106).append("\n");
					csvBufferVMS.append("524432").append(",").append(count_524432).append(",").append(amount_524432_160100).append(",").append(amount_524432_200000).append(",").append(amount_524432_003000).append(",").append(amount_524432_013000).append(",").append(amount_524432_120000).append(",").append(amount_524432_010000).append(",").append(amount_524432_012000).append(",").append(amount_524432_011000).append(",").append(amount_524432_280000).append(",").append(CCA_amount_524432).append(",").append(Fee_amount_160100_524432).append(",").append(Recon_Fee_amount_160100_524432).append(",").append(Fee_amount_200000_524432).append(",").append(Recon_Fee_amount_200000_524432).append(",").append(Fee_amount_003000_524432).append(",").append(Recon_Fee_amount_003000_524432).append(",").append(Fee_amount_013000_524432).append(",").append(Recon_Fee_amount_013000_524432).append(",").append(Fee_amount_120000_524432).append(",").append(Recon_Fee_amount_120000_524432).append(",").append(Fee_amount_010000_524432).append(",").append(Recon_Fee_amount_010000_524432).append(",").append(Fee_amount_012000_524432).append(",").append(Recon_Fee_amount_012000_524432).append(",").append(Fee_amount_011000_524432).append(",").append(Recon_Fee_amount_011000_524432).append(",").append(Fee_amount_280000_524432).append(",").append(Recon_Fee_amount_280000_524432).append("\n");
					csvBufferVMS.append("531688").append(",").append(count_531688).append(",").append(amount_531688_160100).append(",").append(amount_531688_200000).append(",").append(amount_531688_003000).append(",").append(amount_531688_013000).append(",").append(amount_531688_120000).append(",").append(amount_531688_010000).append(",").append(amount_531688_012000).append(",").append(amount_531688_011000).append(",").append(amount_531688_280000).append(",").append(CCA_amount_531688).append(",").append(Fee_amount_160100_531688).append(",").append(Recon_Fee_amount_160100_531688).append(",").append(Fee_amount_200000_531688).append(",").append(Recon_Fee_amount_200000_531688).append(",").append(Fee_amount_003000_531688).append(",").append(Recon_Fee_amount_003000_531688).append(",").append(Fee_amount_013000_531688).append(",").append(Recon_Fee_amount_013000_531688).append(",").append(Fee_amount_120000_531688).append(",").append(Recon_Fee_amount_120000_531688).append(",").append(Fee_amount_010000_531688).append(",").append(Recon_Fee_amount_010000_531688).append(",").append(Fee_amount_012000_531688).append(",").append(Recon_Fee_amount_012000_531688).append(",").append(Fee_amount_011000_531688).append(",").append(Recon_Fee_amount_011000_531688).append(",").append(Fee_amount_280000_531688).append(",").append(Recon_Fee_amount_280000_531688).append("\n");
					csvBufferVMS.append("542850").append(",").append(count_542850).append(",").append(amount_542850_160100).append(",").append(amount_542850_200000).append(",").append(amount_542850_003000).append(",").append(amount_542850_013000).append(",").append(amount_542850_120000).append(",").append(amount_542850_010000).append(",").append(amount_542850_012000).append(",").append(amount_542850_011000).append(",").append(amount_542850_280000).append(",").append(CCA_amount_542850).append(",").append(Fee_amount_160100_542850).append(",").append(Recon_Fee_amount_160100_542850).append(",").append(Fee_amount_200000_542850).append(",").append(Recon_Fee_amount_200000_542850).append(",").append(Fee_amount_003000_542850).append(",").append(Recon_Fee_amount_003000_542850).append(",").append(Fee_amount_013000_542850).append(",").append(Recon_Fee_amount_013000_542850).append(",").append(Fee_amount_120000_542850).append(",").append(Recon_Fee_amount_120000_542850).append(",").append(Fee_amount_010000_542850).append(",").append(Recon_Fee_amount_010000_542850).append(",").append(Fee_amount_012000_542850).append(",").append(Recon_Fee_amount_012000_542850).append(",").append(Fee_amount_011000_542850).append(",").append(Recon_Fee_amount_011000_542850).append(",").append(Fee_amount_280000_542850).append(",").append(Recon_Fee_amount_280000_542850).append("\n");
					csvBufferVMS.append("530688").append(",").append(count_530688).append(",").append(amount_530688_160100).append(",").append(amount_530688_200000).append(",").append(amount_530688_003000).append(",").append(amount_530688_013000).append(",").append(amount_530688_120000).append(",").append(amount_530688_010000).append(",").append(amount_530688_012000).append(",").append(amount_530688_011000).append(",").append(amount_530688_280000).append(",").append(CCA_amount_530688).append(",").append(Fee_amount_160100_530688).append(",").append(Recon_Fee_amount_160100_530688).append(",").append(Fee_amount_200000_530688).append(",").append(Recon_Fee_amount_200000_530688).append(",").append(Fee_amount_003000_530688).append(",").append(Recon_Fee_amount_003000_530688).append(",").append(Fee_amount_013000_530688).append(",").append(Recon_Fee_amount_013000_530688).append(",").append(Fee_amount_120000_530688).append(",").append(Recon_Fee_amount_120000_530688).append(",").append(Fee_amount_010000_530688).append(",").append(Recon_Fee_amount_010000_530688).append(",").append(Fee_amount_012000_530688).append(",").append(Recon_Fee_amount_012000_530688).append(",").append(Fee_amount_011000_530688).append(",").append(Recon_Fee_amount_011000_530688).append(",").append(Fee_amount_280000_530688).append(",").append(Recon_Fee_amount_280000_530688).append("\n");
				}
				else if(fileName.indexOf("83872") != -1) //83872. Code changed on 08/03/2015 
				{
					targetBuffer.append(csvHeader).append("\n");
					targetBuffer.append("516612").append(",").append(count_516612).append(",").append(amount_516612_160100).append(",").append(amount_516612_200000).append(",").append(amount_516612_003000).append(",").append(amount_516612_013000).append(",").append(amount_516612_120000).append(",").append(amount_516612_010000).append(",").append(amount_516612_012000).append(",").append(amount_516612_011000).append(",").append(amount_516612_280000).append(",").append(CCA_amount_516612).append(",").append(Fee_amount_160100_516612).append(",").append(Recon_Fee_amount_160100_516612).append(",").append(Fee_amount_200000_516612).append(",").append(Recon_Fee_amount_200000_516612).append(",").append(Fee_amount_003000_516612).append(",").append(Recon_Fee_amount_003000_516612).append(",").append(Fee_amount_013000_516612).append(",").append(Recon_Fee_amount_013000_516612).append(",").append(Fee_amount_120000_516612).append(",").append(Recon_Fee_amount_120000_516612).append(",").append(Fee_amount_010000_516612).append(",").append(Recon_Fee_amount_010000_516612).append(",").append(Fee_amount_012000_516612).append(",").append(Recon_Fee_amount_012000_516612).append(",").append(Fee_amount_011000_516612).append(",").append(Recon_Fee_amount_011000_516612).append(",").append(Fee_amount_280000_516612).append(",").append(Recon_Fee_amount_280000_516612).append("\n");
				}

				if (csvBuffer.length() > 0 && (count_511340 > 0 || count_531150 > 0 || count_533313 > 0 || count_531244 > 0 || count_534771 > 0
						 || count_515462 > 0 || count_521444 > 0 || count_515480 > 0 || count_526293 > 0 || count_511479 > 0 || count_516488 > 0
						 || count_526249 > 0 || count_534785 > 0 || count_525698 > 0 || count_511233 > 0 || count_512856 > 0 || count_512864 > 0 || count_543276 > 0))
				{
					
					logger.info("csvBuffer::"+csvBuffer.toString());
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ipmReportFolderName+"/"+fileName+"_ByBIN_GC.csv")));
					bw.write(csvBuffer.toString());
					bw.flush();
					bw.close();
				}
				
				if (csvBufferVMS.length() > 0 && (count_521961 > 0 || count_516511 > 0 || count_528627 > 0 || count_538933 > 0 || count_526291 > 0
						 || count_511096 > 0 || count_511186 > 0 || count_521412 > 0 || count_510842 > 0 || count_517628 > 0
						 || count_519106 > 0 || count_524432 > 0 || count_531688 > 0 || count_542850 > 0 || count_530688 > 0))
				{
					logger.info("csvBufferVMS::"+csvBufferVMS.toString());

					BufferedWriter bw1 = new BufferedWriter(new FileWriter(new File(ipmReportFolderName+"/"+fileName+"_ByBIN_VMS.csv")));
					bw1.write(csvBufferVMS.toString());
					bw1.flush();
					bw1.close();
				}

				//Added on 08/03/2015
				if (targetBuffer.length() > 0)
				{
					
					logger.info("targetBuffer::"+targetBuffer.toString());
					
					BufferedWriter bw2 = new BufferedWriter(new FileWriter(new File(ipmReportFolderName+"/"+fileName+"_ByBIN_GC.csv")));
					bw2.write(targetBuffer.toString());
					bw2.flush();
					bw2.close();
				}
				
				
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
			
			} // While
	    
		} // if
		else {
			logger.info("No File found for Bin Report creation process " + new Date());
		}
			Date endDate = new Date();
			logger.info("ReportByBin Ended: " + new Date());

			logger.info("Total time taken = " + (endDate.getTime() - startDate.getTime())/1000.0+" Seconds");
			logger.info("########################################");
	
		}
		catch (Exception e)
		{
			logger.fatal("Error while Creating Report Files: "+e.getMessage());
			e.printStackTrace();
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
		logger.info(">> init method...");
		props = new Properties();
		props.load(new FileInputStream("./config/application.properties"));
		logger.info(props);
		conn = getDBConnection();
		logger.info("<< init method...");
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

} // class



