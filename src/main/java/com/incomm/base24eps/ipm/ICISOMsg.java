package com.incomm.base24eps.ipm;

// jdk
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;

// jakarta
import org.apache.log4j.Logger;

// jpos
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;


/**
 * ICISOMsg.java
 *
 *
 * Created: Thu Mar 11 13:50:40 2004
 *
 * @author <a href="mailto:gstewart@us-south.net">george stewart</a>
 */
public class ICISOMsg {

    private final static Logger logger = Logger.getLogger(ICISOMsg.class);

    /**
     * Class for ISO packager
     *
     */
    private Class packager;

    public ICISOMsg(Class packager) {
        this.packager = packager;
    }

    public ISOMsg create() throws Exception
    {
        ISOMsg m = new ISOMsg();

        // My tests show that jpos packager isn't thread-safe
        m.setPackager ((org.jpos.iso.ISOPackager)
                       packager.newInstance());
        return m;
    }

    /**
     * Use the given Map to provide values for an jbps ISO message.
     *
     * @param m an <code>ISOMsg</code> value
     * @param map a <code>Map</code> value
     * @exception Exception if an error occurs
     */
    public static void setFields(ISOMsg m, Map map)
        throws Exception
    {
        Iterator it = map.keySet().iterator();
        // for each field
        while (it.hasNext()) {
            Object key = it.next();
            m.set(Integer.parseInt((String)key),
                  String.valueOf(map.get(key)));
        }
    }

    public static void getFields(ISOMsg m, Map map)
        throws Exception
    {
        map.putAll(m.getChildren());
    }

    /**
     * Use the given map to create an ISO 8583 message.
     *
     * @param map a <code>Map</code> value
     * @return a <code>byte[]</code> value
     */
    public byte [] pack(Map map) throws Exception
    {

        ISOMsg m = create();
        setFields(m, map);
        byte[] b = m.pack();

        if (logger.isInfoEnabled()) {
            logger.info(dump(m));
        }

        return b;
    }

    /**
     * Unpack the ISO message.  Field values are added to the given
     * Collection.
     *
     * @param is an <code>InputStream</code> value
     * @param flds an <code>int[]</code> value
     * @param cln a <code>Collection</code> value
     */
    public void unpack(java.io.InputStream is, Map map)
        throws Exception
    {
        ISOMsg m = create();

        m.unpack(is);

        if (logger.isInfoEnabled()) {
            logger.debug(dump(m));
        }

        java.util.Iterator it = m.getChildren().keySet().iterator();
        while (it.hasNext()) {
            Integer idx = (Integer)it.next();
            Object val = m.getString(idx.intValue());
            if (val != null) {
                map.put(String.valueOf(idx), val);
            }
        }
    }

    /**
     * Dump the given ISOMsg to a String.
     *
     * @param m an <code>ISOMsg</code> value
     * @return a <code>String</code> value
     */
    public String dump(ISOMsg m) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        m.dump(new java.io.PrintStream(baos), "");
        return baos.toString();
    }


}
