package com.example.roboticsapp2;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class DataTransfer extends AsyncTask<byte[], Void, Void> {

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;
    String ipAdd;
    int ipPort;

    DataTransfer(String address, int port) {
        ipPort = port;
        ipAdd = address;
        /*try {
            s = new Socket(ipAdd, ipPort);
        }catch(UnknownHostException e)
        {
            e.printStackTrace();
        } catch(IOException ioe)
        {
            ioe.printStackTrace();
        }*/

    }
    DataTransfer()
    {
        ipPort=4000;
        ipAdd="192.168.43.196";
        /*try {
            s = new Socket(ipAdd, ipPort);
        }catch(UnknownHostException e)
        {
            e.printStackTrace();
        } catch(IOException ioe)
        {
            ioe.printStackTrace();
        }*/
    }
    /*DataTransfer(Socket client)
    {
        s=client;
    }*/
    @Override
    protected Void doInBackground(byte[]... voids) {

        byte[] number = voids[0];
        try{

            s = new Socket(ipAdd, ipPort);

            pw = new PrintWriter(s.getOutputStream());
            pw.write(""+number[0]+" "+number[1]+" "+number[2]);
            pw.flush();
            pw.close();
            s.close();

        }catch(IOException ioe){

            ioe.printStackTrace();
        }
        return null;
    }
}
