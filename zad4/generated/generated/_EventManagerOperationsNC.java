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
// Generated from file `_EventManagerOperationsNC.java'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package generated;

public interface _EventManagerOperationsNC
{
    String createEvent(String eventName, String eventDesc, User u);

    String subscribe(int eventId, User u);

    java.util.Map<java.lang.Integer, Event> listEvents();

    String modify(int eventId, String eventName, String eventDesc, User u);
}
