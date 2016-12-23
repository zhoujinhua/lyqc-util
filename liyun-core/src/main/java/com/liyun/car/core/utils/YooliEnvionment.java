/*
 * 创建日期 2007-3-23
 */
package com.liyun.car.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author yaogz
 * @version $Revision: 1.6 $
 * 建立日期  2014-12-25
 */
public final class YooliEnvionment
{
    /**
	 * 
	 */
	private static final String	IBAS_ENV_PROPERTIES	= "YooliEnvionment.properties";
	/**
     * yoolicar环境实例 
     */
    private static  YooliEnvionment INSTANCE = null;

    /**
     * @return 获取实例
     */
    public static synchronized YooliEnvionment getInstance()
	{
    	if (INSTANCE==null)
    	{
    		try
			{
				INSTANCE = new YooliEnvionment();
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
    	}
		return INSTANCE;
	}
	private final Properties p;
    
    private YooliEnvionment() throws IOException
    {
        p = new Properties();
        init();
    }
    
    private InputStream getEtcInputStream() throws FileNotFoundException
    {
        Properties sPs = System.getProperties();
        
        List list = new ArrayList();

        list.add(getConfigFileName("."));
        
        list.add(getConfigFileName("/"));
        
        list.add(getConfigFileName("/etc/yoolicar/"));

        list.add(getConfigFileName(sPs.getProperty("user.home")));
        
        for (int i = 0, iSize = list.size(); i < iSize; i++)
		{
			File file = ((File) list.get(i)).getAbsoluteFile();
			if(file.exists())
	        {
	            System.out.println("get environment From " + file.getAbsolutePath()+"/"+file.getPath());
	            return new FileInputStream(file);
	        }
			
		}

        final InputStream resourceAsStream = YooliEnvionment.class.getResourceAsStream(IBAS_ENV_PROPERTIES);
        System.out.println("get environment From ClassPath: " + IBAS_ENV_PROPERTIES);
        if (resourceAsStream!=null)
        	return resourceAsStream;
        System.err.println( "can not get properties file configration");
        return null ;
    }

	private File getConfigFileName(final String basePath)
	{
		File envFile;
		envFile = new File(basePath , IBAS_ENV_PROPERTIES);
		return envFile;
	}
	
	/**
	 * @return 获取属性
	 */
	public static Properties getProperties()
	{
		return (Properties) getInstance().p.clone() ;
	}
    
    void init() throws IOException
    {
        
        InputStream in =null ;
        try
        {
        	in = getEtcInputStream();
        	if (in!=null)
        	{
                p.load(in);
        	}
        	else
        	{
        		System.out.println( "environment configration is Empty");
        	}
        }
         finally
        {
            if(in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                }    
            }
        }
    }

	/**
     * 根据键获得值
     * @param key
     * @return 环境值
     */
    public static String getStringValue(String key)
    {
        return getInstance().p.getProperty(key);
    }

	/**
     * 根据键获得整型值 
     * @param key
     * @return 环境值
     */
    public static int getIntValue(String key)
    {
        return Integer.parseInt(getInstance().p.getProperty(key));
    }

    public static void main(String[] args) {
		System.out.println(YooliEnvionment.getStringValue("ServerJobfilePath"));
		System.out.println(YooliEnvionment.getStringValue("JDKey"));
	}
}
