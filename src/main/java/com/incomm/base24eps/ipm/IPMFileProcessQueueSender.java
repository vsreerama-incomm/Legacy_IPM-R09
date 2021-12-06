package com.incomm.base24eps.ipm;
import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.jms.QueueSession;
import javax.jms.QueueSender;

import com.incomm.base24eps.ipm.IPMGenericException;

import net.ussouth.application.resource.Configuration;

import org.apache.log4j.Logger;
/**
 * 
 * @author syeramsetty
 *
 */


public class IPMFileProcessQueueSender{
	
	private final static Logger logger = Logger.getLogger(IPMFileProcessQueueSender.class);

	public static void send(String request, String jndiName, int priority, String ipmQueueHost) throws IPMGenericException {
		logger.debug("<< IPMFileProcessQueueSender.send() ");

		
		QueueConnection queueConnection = null;
		QueueSession queueSession = null;
		QueueSender queueSender = null;
		QueueConnectionFactory queueConnectionFactory = null;

		try {
			
			
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");	
			//String ipmQueueHost = (String)Configuration.getProperty("application","ipmprocessfilehosturl");
			
			env.put(Context.PROVIDER_URL, ipmQueueHost);
			
			Context remoteCtx = new InitialContext(env);
			Queue queue = (Queue) remoteCtx.lookup(jndiName);
			
			queueConnectionFactory = (QueueConnectionFactory) remoteCtx.lookup("ConnectionFactory");

			queueConnection = queueConnectionFactory.createQueueConnection();
			logger.info("queueConnection = " + queueConnection);
			 queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			queueSender = queueSession.createSender(queue);

			TextMessage message = queueSession.createTextMessage();
			logger.info("message = " + message);

			message.setJMSPriority(priority);
			message.setText(request);

			queueConnection.start();
			queueSender.send(message);
		
		} catch (Exception e) {
			IPMGenericException oe = new IPMGenericException("Error in sending the record to JMS: "+request);
			logger.fatal("Error in sending the record to JMS: "+request);
			e.printStackTrace();
			throw oe;
		} finally {
			try {
				if (queueConnection != null) {
					queueConnection.close();
				}
			} catch (Exception e1) { 
				IPMGenericException oe = new IPMGenericException("Error in sending the record to JMS: "+request);
				logger.fatal("Error in Reading the file: "+request);
				e1.printStackTrace();
				throw oe;// Eat the exception. No recovery.
			}
			try {
				if (queueSender != null) {
					queueSender.close();
				}
			} catch (Exception e) { // Eat the exception. No recovery.
			}
			try {
				if (queueSession != null) {
					queueSession.close();
				}
			} catch (Exception e1) { // Eat the exception. No recovery.
			}

		}

	}
	
	public static void main(String[] args) {
		
		String request = "1100F236A76108E080040000000000000020164315682493436072000000000000000200022015353617700313022010375113091302210220840200101700000000100599961112345678901305115001074TERMID01        CARD ACCEPTOR  41ACQUIRER NAME"+File.separator+File.separator+"CITY NAME"+File.separator+"             USA84033207001208002VD09202                                                                            0002                                                                                                                          1600600000017016VISANET         20024VISANET             LGNT21024TEST                    230012320111234567890108401032555 State Street    00U30075    05031        313051076711075        41006130221";
		String jndiName = "queue/ipmfileprocessqueue";
		String ipmQueueHost = "atldevaps01gc.privatecloud.local:8280";
		int priority = 0;
		try {
			send(request, jndiName, priority,ipmQueueHost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
