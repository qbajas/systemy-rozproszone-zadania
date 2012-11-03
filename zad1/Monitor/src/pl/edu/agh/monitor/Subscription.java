/* @(#) $Id$
 *
 * Copyright (c) 2000-2012 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.monitor;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;

/** @author ja */
public class Subscription {
    private int id;
    private Filter filter;
    private SocketChannel client;
    private Server server;
    
    public Subscription(Server s, Filter f, SocketChannel client) {
        id = f.getId();
        filter = f;
        this.client = client;
        server = s;
        try {
            System.out.println("New active subscription for parameter: " + f + " with client: "
                    + client.getRemoteAddress());
        } catch (IOException exception) {
            // TODO Handle exception using log4j or CLog
            exception.printStackTrace();
        }
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isAlive() {
        return client.isOpen();
    }
    
    public void processMeasurement(Measurement m) {
        if (filter.match(m)) {
            System.out.println("Filter match for Subscription " + id);
            try {
                server.registerForWrite(client, m.toString());
            } catch (ClosedChannelException exception) { //klient
                // TODO Handle exception using log4j or CLog
                try {
                    client.close();
                } catch (IOException exception1) {
                    // TODO Handle exception1 using log4j or CLog
                    System.out.println("Cannot close client channel " + client);
                    exception1.printStackTrace();
                }
                
            }
            
        }
    }
}
