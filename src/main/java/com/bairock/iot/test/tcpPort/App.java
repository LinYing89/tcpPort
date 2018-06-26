package com.bairock.iot.test.tcpPort;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
    		new ServerOne().run();
    		new ServerTwo().run();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
