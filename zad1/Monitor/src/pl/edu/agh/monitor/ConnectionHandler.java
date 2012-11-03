/* @(#) $Id$
 *
 * Copyright (c) 2000-2012 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/** @author ja */
public class ConnectionHandler {
    private Server server;
    
    public ConnectionHandler(Server s) {
        server = s;
    }
    
    public void handleClientConnection(SelectionKey clientConn) {
        ServerSocketChannel serverChannel = (ServerSocketChannel) clientConn.channel();
        try {
            SocketChannel clientChannel = serverChannel.accept();
            BufferedReader clientIn =
                    new BufferedReader(new InputStreamReader(clientChannel.socket().getInputStream()));
            PrintWriter clientOut = new PrintWriter(clientChannel.socket().getOutputStream(), true);
            
            //only the first line!!!
            String request = clientIn.readLine();
            System.out.println("Http request from client: " + clientChannel.getRemoteAddress());
            System.out.println("request body: " + request);
            String response = server.getHttpHandler().handleClientRequest(request);
            clientOut.write(response);
            clientOut.close();
            clientChannel.close();
        } catch (IOException exception) {
            // TODO Handle exception using log4j or CLog
            exception.printStackTrace();
        }
    }
    
    public void handleSensorConnection(SelectionKey sensorConn) {
        ServerSocketChannel channel = (ServerSocketChannel) sensorConn.channel();
        //TODO
        try {
            SocketChannel sensorChannel = channel.accept();
            BufferedReader sensorIn =
                    new BufferedReader(new InputStreamReader(sensorChannel.socket().getInputStream()));
            PrintWriter sensorOut = new PrintWriter(sensorChannel.socket().getOutputStream(), true);
            
            //only the first line!!!
            String request = sensorIn.readLine();
            
            String response = server.getHttpHandler().handleSensorRequest(request);
            sensorOut.write(response);
            sensorOut.close();
            sensorChannel.close();
        } catch (IOException exception) {
            // TODO Handle exception using log4j or CLog
            exception.printStackTrace();
        }
        
    }
}
