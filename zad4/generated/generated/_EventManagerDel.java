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
// Generated from file `_EventManagerDel.java'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package generated;

public interface _EventManagerDel extends Ice._ObjectDel
{
    String createEvent(String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    String subscribe(int eventId, User u, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    java.util.Map<java.lang.Integer, Event> listEvents(java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    String modify(int eventId, String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;

    String delete(int eventId, User u, java.util.Map<String, String> __ctx)
        throws IceInternal.LocalExceptionWrapper;
}
