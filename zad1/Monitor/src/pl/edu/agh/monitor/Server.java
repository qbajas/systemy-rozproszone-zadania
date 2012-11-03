/* @(#) $Id$
 *
 * Copyright (c) 2000-2012 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.monitor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** @author ja */
public class Server implements Runnable {
    public static final int CLIENT_PORT = 8080;
    public static final int SENSOR_PORT = 8081;
    private static int SUBSCR_COUNT = 0;
    
    private Selector selector;
    private ServerSocketChannel clientChannel;
    private ServerSocketChannel sensorChannel;
    
    private HttpRequestHandler httpHandler;
    private ConnectionHandler connHandler;
    
    Map<Integer, SelectionKey> waitingSubscriptions = new HashMap<Integer, SelectionKey>();
    Map<Integer, Subscription> activeSubscriptions = new HashMap<Integer, Subscription>();
    
    public HttpRequestHandler getHttpHandler() {
        return httpHandler;
    }
    
    public void addActiveSubscription(Subscription s) {
        int id = s.getId();
        activeSubscriptions.put(new Integer(id), s);
    }
    
    public void removeActiveSubscription(Subscription s) {
        System.out.println("Removing Subscription " + s.getId());
        activeSubscriptions.remove(new Integer(s.getId()));
    }
    
    public void removeWaitingSubscription(int id) {
        waitingSubscriptions.remove(new Integer(id));
    }
    
    public Server() {
        try {
            selector = Selector.open();
            clientChannel = ServerSocketChannel.open();
            clientChannel.bind(new InetSocketAddress(CLIENT_PORT));
            clientChannel.configureBlocking(false);
            clientChannel.register(selector, SelectionKey.OP_ACCEPT);
            sensorChannel = ServerSocketChannel.open();
            sensorChannel.bind(new InetSocketAddress(SENSOR_PORT));
            sensorChannel.configureBlocking(false);
            sensorChannel.register(selector, SelectionKey.OP_ACCEPT);
            httpHandler = new HttpRequestHandler(this);
            connHandler = new ConnectionHandler(this);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public int registerSubscription(Filter f) {
        try {
            ServerSocketChannel newSubscription = ServerSocketChannel.open();
            newSubscription.bind(new InetSocketAddress(InetAddress.getLocalHost(), 0));
            newSubscription.configureBlocking(false);
            SUBSCR_COUNT++;
            f.setId(SUBSCR_COUNT);
            SelectionKey nsKey = newSubscription.register(selector, SelectionKey.OP_ACCEPT, f);
            System.out.println("Registered a subscription: " + newSubscription.socket().getInetAddress()
                    + ':' + newSubscription.socket().getLocalPort());
            waitingSubscriptions.put(new Integer(SUBSCR_COUNT), nsKey);
            return SUBSCR_COUNT;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    
    public String getSubscriptionAddress(int subscrId) {
        SelectionKey subscrKey = waitingSubscriptions.get(new Integer(subscrId));
        if (subscrKey == null) {
            return null;
        }
        ServerSocketChannel subscrChannel = (ServerSocketChannel) subscrKey.channel();
        String address =
                "" + subscrChannel.socket().getInetAddress() + ':' + subscrChannel.socket().getLocalPort();
        String dupa = address.substring(1); //bez pierwszego slasha
        return dupa;
    }
    
    public void processMeasurement(Measurement m) {
        List<Integer> toRemove = new ArrayList<Integer>();
        for (Integer i: activeSubscriptions.keySet()) {
            Subscription s = activeSubscriptions.get(i);
            if (s.isAlive()) {
                s.processMeasurement(m);
            } else {
                toRemove.add(i);
            }
        }
        for (Integer i: toRemove) {
            activeSubscriptions.remove(i);
        }
    }
    
    public void registerForWrite(SocketChannel sc, String message) throws ClosedChannelException {
        try {
            sc.configureBlocking(false);
        } catch (IOException exception) {
            // TODO Handle exception using log4j or CLog
            exception.printStackTrace();
        }
        sc.register(selector, SelectionKey.OP_WRITE, message);
    }
    
    public void run() {
        while (true) {
            try {
                int readyChannels = selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    if (key.isAcceptable() && (key.channel() == clientChannel)) {
                        connHandler.handleClientConnection(key);
                    } else if (key.isAcceptable() && (key.channel() == sensorChannel)) {
                        connHandler.handleSensorConnection(key);
                    } else if (key.isAcceptable()) { //waiting subscription
                        Filter f = (Filter) key.attachment();
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel client = channel.accept();
                        channel.close();
                        key.cancel();
                        Subscription s = new Subscription(this, f, client);
                        removeWaitingSubscription(f.getId());
                        addActiveSubscription(s);
                    } else if (key.isWritable()) { //pass measurement to client
                        //TODO
                        SocketChannel client = (SocketChannel) key.channel();
                        key.cancel();
                        System.out.println("Printing to client " + client);
                        ByteBuffer buf = ByteBuffer.allocateDirect(4096);
                        String msg = (String) key.attachment();
                        buf.put(msg.getBytes());
                        buf.flip();
                        try {
                            client.write(buf);
//                            //TEST ONLY
//                            client.close();
                        } catch (IOException e) {
                            System.out.println("Client disconnected");
                            client.close();
                        }
                        
//                        client.configureBlocking(false);
//                        PrintWriter out = new PrintWriter(client.socket().getOutputStream(), true);
//                        String msg = (String) key.attachment();
//                        out.write(msg);
//                        out.close();
                    }
                }
            } catch (IOException exception) {
                // TODO Handle exception using log4j or CLog
                exception.printStackTrace();
            }
            
        }
    }
}
