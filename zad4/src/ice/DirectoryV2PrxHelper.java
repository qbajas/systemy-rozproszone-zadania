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
// Generated from file `DirectoryV2PrxHelper.java'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package ice;

public final class DirectoryV2PrxHelper extends Ice.ObjectPrxHelperBase implements DirectoryV2Prx
{
    public String[]
    listFiles(String dirPath)
        throws DirectoryNotExistsException
    {
        return listFiles(dirPath, null, false);
    }

    public String[]
    listFiles(String dirPath, java.util.Map<String, String> __ctx)
        throws DirectoryNotExistsException
    {
        return listFiles(dirPath, __ctx, true);
    }

    private String[]
    listFiles(String dirPath, java.util.Map<String, String> __ctx, boolean __explicitCtx)
        throws DirectoryNotExistsException
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        int __cnt = 0;
        while(true)
        {
            Ice._ObjectDel __delBase = null;
            try
            {
                __checkTwowayOnly("listFiles");
                __delBase = __getDelegate(false);
                _DirectoryV2Del __del = (_DirectoryV2Del)__delBase;
                return __del.listFiles(dirPath, __ctx);
            }
            catch(IceInternal.LocalExceptionWrapper __ex)
            {
                __cnt = __handleExceptionWrapperRelaxed(__delBase, __ex, null, __cnt);
            }
            catch(Ice.LocalException __ex)
            {
                __cnt = __handleException(__delBase, __ex, null, __cnt);
            }
        }
    }

    private static final String __listFiles_name = "listFiles";

    public Ice.AsyncResult begin_listFiles(String dirPath)
    {
        return begin_listFiles(dirPath, null, false, null);
    }

    public Ice.AsyncResult begin_listFiles(String dirPath, java.util.Map<String, String> __ctx)
    {
        return begin_listFiles(dirPath, __ctx, true, null);
    }

    public Ice.AsyncResult begin_listFiles(String dirPath, Ice.Callback __cb)
    {
        return begin_listFiles(dirPath, null, false, __cb);
    }

    public Ice.AsyncResult begin_listFiles(String dirPath, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_listFiles(dirPath, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_listFiles(String dirPath, Callback_DirectoryV2_listFiles __cb)
    {
        return begin_listFiles(dirPath, null, false, __cb);
    }

    public Ice.AsyncResult begin_listFiles(String dirPath, java.util.Map<String, String> __ctx, Callback_DirectoryV2_listFiles __cb)
    {
        return begin_listFiles(dirPath, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_listFiles(String dirPath, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__listFiles_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __listFiles_name, __cb);
        try
        {
            __result.__prepare(__listFiles_name, Ice.OperationMode.Idempotent, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__os();
            __os.writeString(dirPath);
            __os.endWriteEncaps();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public String[] end_listFiles(Ice.AsyncResult __result)
        throws DirectoryNotExistsException
    {
        Ice.AsyncResult.__check(__result, this, __listFiles_name);
        if(!__result.__wait())
        {
            try
            {
                __result.__throwUserException();
            }
            catch(DirectoryNotExistsException __ex)
            {
                throw __ex;
            }
            catch(Ice.UserException __ex)
            {
                throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
            }
        }
        String[] __ret;
        IceInternal.BasicStream __is = __result.__is();
        __is.startReadEncaps();
        __ret = Ice.StringSeqHelper.read(__is);
        __is.endReadEncaps();
        return __ret;
    }

    public static DirectoryV2Prx
    checkedCast(Ice.ObjectPrx __obj)
    {
        DirectoryV2Prx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (DirectoryV2Prx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA(ice_staticId()))
                {
                    DirectoryV2PrxHelper __h = new DirectoryV2PrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static DirectoryV2Prx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        DirectoryV2Prx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (DirectoryV2Prx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA(ice_staticId(), __ctx))
                {
                    DirectoryV2PrxHelper __h = new DirectoryV2PrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static DirectoryV2Prx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        DirectoryV2Prx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId()))
                {
                    DirectoryV2PrxHelper __h = new DirectoryV2PrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static DirectoryV2Prx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        DirectoryV2Prx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId(), __ctx))
                {
                    DirectoryV2PrxHelper __h = new DirectoryV2PrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static DirectoryV2Prx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        DirectoryV2Prx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (DirectoryV2Prx)__obj;
            }
            catch(ClassCastException ex)
            {
                DirectoryV2PrxHelper __h = new DirectoryV2PrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static DirectoryV2Prx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        DirectoryV2Prx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            DirectoryV2PrxHelper __h = new DirectoryV2PrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    public static final String[] __ids =
    {
        "::FileSystem::DirectoryV2",
        "::Ice::Object"
    };

    public static String
    ice_staticId()
    {
        return __ids[0];
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _DirectoryV2DelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _DirectoryV2DelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, DirectoryV2Prx v)
    {
        __os.writeProxy(v);
    }

    public static DirectoryV2Prx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            DirectoryV2PrxHelper result = new DirectoryV2PrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}