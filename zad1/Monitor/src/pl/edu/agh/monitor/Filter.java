/* @(#) $Id$
 *
 * Copyright (c) 2000-2012 ComArch S.A. All Rights Reserved.
 * Any usage, duplication or redistribution of this software
 * is allowed only according to separate agreement prepared
 * in written between ComArch and authorized party.
 */
package pl.edu.agh.monitor;

/** @author ja */
public class Filter {
    private String resId, metric;
    private int id;
    
    public Filter(String resId, String metric) {
        this.resId = resId;
        this.metric = metric;
    }
    
    public String getResId() {
        return resId;
    }
    
    public void setResId(String resId) {
        this.resId = resId;
    }
    
    public String getMetric() {
        return metric;
    }
    
    public void setMetric(String metric) {
        this.metric = metric;
    }
    
    public boolean match(Measurement m) {
        return ((getResId().equals(m.getResId())) && (getMetric().equals(m.getMetric())));
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return resId + ':' + metric;
    }
}
