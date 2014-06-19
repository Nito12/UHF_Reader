/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.h_da.fbi.rfid.uhfreader;

/**
 *
 * @author Home
 */
/*
 serieller anschluß ->	RXTX - http://rxtx.qbang.org/wiki/index.php/Main_Page

*/


import de.h_da.fbi.rfid.gui.GUI;
import de.h_da.fbi.rfid.middleware.LogRFIDManagement;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.*;
import java.io.*;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class SerialReadByEvents 
{

    
    public SerialReadByEvents()
    {

        super();
System.setProperty("java.library.path",".");
    }


    public void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(38400,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                //serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                (new Thread(new SerialReader(in))).start();
                (new Thread(new SerialWriter(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    /** */
    public  class SerialReader implements Runnable
    {
        InputStream in;

        public SerialReader ( InputStream in )
        {
            this.in = in;
        }

        public void run ()
        {
            byte[] buffer = new byte[1024];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                	String x = (new String(buffer,0,len));
                        
                	try
                	{
                		Thread.sleep(500);
                	} catch( Exception e)
                	{
                		System.out.println("Sleep error");
                	}
                	System.out.print(x);
                	if( x.contains("E004556315010000" ))
                	{
                        System.out.print("Gelesene Daten:\n");
                        System.out.print(x);
                        System.out.print("\n\n");
                		System.out.print("Willkommen\n");
                                GUI.logRFIDManagement.convertForMap(x);
                                x = "";
                	} else if ( !x.contains("E004556315010000" ) && len != 0 )
                	{
                        System.out.print("Gelesene Daten:\n");
                        System.out.print(x);
                        
                        //Map<String,String> actualPersonRoom = GUI.logRFIDManagement.getPersonRoom();
                        
                        GUI.logRFIDManagement.convertForMap(x);
                        x = "";
                        System.out.print("\n\n");
                		System.out.print("Zugang verweigert !!!\n");
                	}
                	try
                	{
                		Thread.sleep(500);
                	} catch( Exception e)
                	{
                		System.out.println("Sleep error");
                	}
                        
                        //for(int i = 0; i < GUI.logRFIDManagement.getPersonRoom().keySet().size(); i++)
                        //{
                            System.out.println(GUI.logRFIDManagement.getPersonRoom().keySet().size());
                            //System.out.println("}Cw,d:MCW,b:20100,f:1!^}Cw,d:MSW,b:81808180,f:1!");
                            
                        //}
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    /** */
    public class SerialWriter implements Runnable
    {
        OutputStream out;

        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }

        public void run ()
        {
            try
            {
                int c = 0;
                while ( ( c = System.in.read()) > -1 )
                {
                    this.out.write(c);
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

     
   






//    public static void main ( String[] args )
//    {
//        try
//        {                       
//            //(new SerialReadByEvents()).connect("COM1");
//        }
//        catch ( Exception e )
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    
}
