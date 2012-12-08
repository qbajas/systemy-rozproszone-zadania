// **********************************************************************
//
// Copyright (c) 2003-2011 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.4.2
//
// <auto-generated>
//
// Generated from file `_EventManagerOperations.java'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package generated;

public interface _EventManagerOperations
{
    String createEvent(String eventName, String eventDesc, int daysFromNow, Ice.Current __current);

    boolean subscribe(String eventName, Ice.Current __current);

    java.util.List<Event> listEvents(Ice.Current __current);

    String modify(String eventName, String eventDesc, int daysFromNow, Ice.Current __current);
}