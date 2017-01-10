package com.akshayraval.client;

import java.io.*;
import java.net.*;

/**
 *
 * @author Akshay
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Client Signing ON");
            
            Socket soc = new Socket("127.0.0.1",9081);
            Reader r = new Reader(soc);
            r.start();
            System.out.println("Cliend says connection established");
            
            PrintWriter nos = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    soc.getOutputStream()
                            )
                    ), true
            );
            BufferedReader kin = new BufferedReader(new InputStreamReader(System.in));
            
            String n = kin.readLine();
            while(!n.equals("End"))
            {
                nos.println(n);
                n = kin.readLine();
            }
            nos.println("End");
            
            System.out.println("Client Signing OFF");
        } catch (IOException ex) {
            System.out.println("IOexception occurred");
        }
    }
    
}


class Reader extends Thread{

    Socket soc; 
    
    Reader(Socket soc) {
        this.soc = soc;
    }
    
    @Override
    public void run() {
        try {
            BufferedReader nis = new BufferedReader(
                    new InputStreamReader(
                            soc.getInputStream()
                    )
            );
            
            String str = nis.readLine();
            while(!str.equals("End")) {
                System.out.println("Message from Server >> " +str );
                str = nis.readLine();
            }
            
        } catch (IOException ex) {
            System.out.println("IOException Occurred");
        }
    }
}
