package com.oracle.medrec.common.core;

import junit.framework.JUnit4TestAdapter;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

/**
 * {@link MethodInvocationCachingInterceptor} test case.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class MethodInvocationCachingInterceptorTestCase {

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(
                MethodInvocationCachingInterceptorTestCase.class);
    }

    private MethodInvocationCache cache;
    private InvocationContext ctx;
    private MethodInvocationCachingInterceptor interceptor;

    @Before
    public void setUp() {
        cache = createMock(MethodInvocationCache.class);
        ctx = createMock(InvocationContext.class);
        interceptor = new MethodInvocationCachingInterceptor();
        interceptor.setMethodReturnValueCache(cache);
    }

    @Test
    public void checkInCacheWithResult() throws Exception {
        Object result = new Object();
        ctx.getMethod();
        expectLastCall().andReturn(null);
        ctx.getParameters();
        expectLastCall().andReturn(new Object[0]);
        cache.findResult((Method) anyObject(), (Object[]) anyObject());
        expectLastCall().andReturn(result);

        replay(cache);
        replay(ctx);
        assertSame(result, interceptor.checkInCache(ctx));
        verify(cache);
        verify(ctx);
    }

    @Test
    public void checkInCacheWithoutResult() throws Exception {
        Object result = new Object();
        ctx.getMethod();
        expectLastCall().andReturn(null);
        ctx.getParameters();
        expectLastCall().andReturn(new Object[0]);
        cache.findResult((Method) anyObject(), (Object[]) anyObject());
        expectLastCall().andThrow(new ResultNotCachedException());
        ctx.proceed();
        expectLastCall().andReturn(result);
        ctx.getMethod();
        expectLastCall().andReturn(null);
        ctx.getParameters();
        expectLastCall().andReturn(new Object[0]);
        cache.addResult((Method) anyObject(), eq(result),
                (Object[]) anyObject());
        expectLastCall().once();

        replay(cache);
        replay(ctx);
        assertSame(result, interceptor.checkInCache(ctx));
        verify(cache);
        verify(ctx);
    }
}
