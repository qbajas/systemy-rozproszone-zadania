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
// Generated from file `EventManagerPrx.java'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package generated;

public interface EventManagerPrx extends Ice.ObjectPrx
{
    public String createEvent(String eventName, String eventDesc, User u);

    public String createEvent(String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_createEvent(String eventName, String eventDesc, User u);

    public Ice.AsyncResult begin_createEvent(String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_createEvent(String eventName, String eventDesc, User u, Ice.Callback __cb);

    public Ice.AsyncResult begin_createEvent(String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_createEvent(String eventName, String eventDesc, User u, Callback_EventManager_createEvent __cb);

    public Ice.AsyncResult begin_createEvent(String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx, Callback_EventManager_createEvent __cb);

    public String end_createEvent(Ice.AsyncResult __result);

    public String subscribe(int eventId, User u);

    public String subscribe(int eventId, User u, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_subscribe(int eventId, User u);

    public Ice.AsyncResult begin_subscribe(int eventId, User u, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_subscribe(int eventId, User u, Ice.Callback __cb);

    public Ice.AsyncResult begin_subscribe(int eventId, User u, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_subscribe(int eventId, User u, Callback_EventManager_subscribe __cb);

    public Ice.AsyncResult begin_subscribe(int eventId, User u, java.util.Map<String, String> __ctx, Callback_EventManager_subscribe __cb);

    public String end_subscribe(Ice.AsyncResult __result);

    public java.util.Map<java.lang.Integer, Event> listEvents();

    public java.util.Map<java.lang.Integer, Event> listEvents(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_listEvents();

    public Ice.AsyncResult begin_listEvents(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_listEvents(Ice.Callback __cb);

    public Ice.AsyncResult begin_listEvents(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_listEvents(Callback_EventManager_listEvents __cb);

    public Ice.AsyncResult begin_listEvents(java.util.Map<String, String> __ctx, Callback_EventManager_listEvents __cb);

    public java.util.Map<java.lang.Integer, Event> end_listEvents(Ice.AsyncResult __result);

    public String modify(int eventId, String eventName, String eventDesc, User u);

    public String modify(int eventId, String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_modify(int eventId, String eventName, String eventDesc, User u);

    public Ice.AsyncResult begin_modify(int eventId, String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_modify(int eventId, String eventName, String eventDesc, User u, Ice.Callback __cb);

    public Ice.AsyncResult begin_modify(int eventId, String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_modify(int eventId, String eventName, String eventDesc, User u, Callback_EventManager_modify __cb);

    public Ice.AsyncResult begin_modify(int eventId, String eventName, String eventDesc, User u, java.util.Map<String, String> __ctx, Callback_EventManager_modify __cb);

    public String end_modify(Ice.AsyncResult __result);

    public String delete(int eventId, User u);

    public String delete(int eventId, User u, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_delete(int eventId, User u);

    public Ice.AsyncResult begin_delete(int eventId, User u, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_delete(int eventId, User u, Ice.Callback __cb);

    public Ice.AsyncResult begin_delete(int eventId, User u, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_delete(int eventId, User u, Callback_EventManager_delete __cb);

    public Ice.AsyncResult begin_delete(int eventId, User u, java.util.Map<String, String> __ctx, Callback_EventManager_delete __cb);

    public String end_delete(Ice.AsyncResult __result);
}
