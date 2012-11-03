/* @(#) $Id$
 *
 * Copyright (c) 2000-2012 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.monitor;

/** @author ja */
public class Measurement {
    private String resId, metric, timestamp, value;
    
    public Measurement(String resId, String metric, String timestamp, String value) {
        this.resId = resId;
        this.metric = metric;
        this.timestamp = timestamp;
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getMetric() {
        return metric;
    }
    
    public void setMetric(String metric) {
        this.metric = metric;
    }
    
    public String getResId() {
        return resId;
    }
    
    public void setResId(String resId) {
        this.resId = resId;
    }
    
    @Override
    public String toString() {
        return resId + '|' + metric + '|' + value + '|' + timestamp;
    }
}
