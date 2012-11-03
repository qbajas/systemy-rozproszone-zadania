/* @(#) $Id$
 *
 * Copyright (c) 2000-2012 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.monitor;

/** @author ja */
public class HttpRequestHandler {
    //TODO
    public static final int UNRECOGNIZED = -1;
    public static final int SUBSCR_CREATE = 0;
    public static final int SUBSCR_GETADDR = 1;
    public static final int SUBSCR_CANCEL = 2;
    public static final int MEASUREMENT = 3;
    
    private Server server;
    
    public HttpRequestHandler(Server s) {
        server = s;
    }
    
    public String handleSensorRequest(String request) {
        //TODO
        String result = "I DUNNO MAN, I'M TRIPPING BALLS HERE!";
        String[] tab1 = request.split(" ");
        if (tab1.length < 2) {
            return result;
        }
        String[] tab2 = tab1[1].split("/"); //  /measurement/resource/metric/value/timestamp
        if (tab2.length != 6) {
            return result;
        }
        String resId = tab2[2], metric = tab2[3], value = tab2[4], timestamp = tab2[5];
        Measurement m = new Measurement(resId, metric, timestamp, value);
        System.out.println("Measurement received: " + m);
        server.processMeasurement(m);
        result = "200 OK";
        return result;
    }
    
    public String handleClientRequest(String request) {
        String result = "404 NOT FOUND";
        int reqType = identifyRequestType(request);
        if (reqType == SUBSCR_CREATE) {
            Filter f = extractSubscrParams(request);
            if (f == null) {
                result = "ERROR unrecognized request";
            } else {
                int id = server.registerSubscription(f);
                result = "201 CREATED /subscriptions/" + id;
            }
        } else if (reqType == SUBSCR_GETADDR) {
            String addr = getSubscrAddress(request);
            if (addr == null) {
                result = "404 NOT FOUND";
            } else {
                result = "200 OK " + addr;
            }
        }
        return result;
    }
    
    private int identifyRequestType(String request) {
        if (request == null) {
            return UNRECOGNIZED;
        }
        String[] tab1 = request.split(" ");
        if (tab1.length < 2) {
            return UNRECOGNIZED;
        }
        String[] tab2 = tab1[1].split("/");
        if (tab2.length < 2) {
            return UNRECOGNIZED;
        }
        String reqType = tab2[1].toLowerCase();
        if (reqType.equals("subscribe")) {
            return SUBSCR_CREATE;
        } else if (reqType.equals("unsubscribe")) {
            return SUBSCR_CANCEL;
        } else if (reqType.equals("subscriptions")) {
            return SUBSCR_GETADDR;
        } else {
            return UNRECOGNIZED;
        }
    }
    
    private Filter extractSubscrParams(String request) {
        //TODO
        String[] tab1 = request.split(" "); //POST_subscr_http version;
        if (tab1.length < 2) {
            return null;
        }
        String[] tab2 = tab1[1].split("/"); //subscribe/resource/metric
        if (tab2.length != 4) {
            return null;
        }
        String resource = tab2[2];
        String metric = tab2[3];
        System.out.println("resource: " + resource + ", metric: " + metric);
        
        return new Filter(resource, metric);
    }
    
    private String getSubscrAddress(String request) {
        String[] tab1 = request.split(" "); //POST_subscr_http version;
        if (tab1.length < 2) {
            return null;
        }
        String[] tab2 = tab1[1].split("/"); //subscriptions/id
        if (tab2.length != 3) {
            return null;
        }
        String subscrIdStr = tab2[2];
        int subscrId = Integer.parseInt(subscrIdStr);
        return server.getSubscriptionAddress(subscrId);
    }
}
