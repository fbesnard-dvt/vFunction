package com.oracle.medrec.common.core;

import junit.framework.JUnit4TestAdapter;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.Test;

import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

/**
 * {@link MethodParameterValidatingInterceptor} test case.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class MethodParameterValidatingInterceptorTestCase {

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(
                MethodParameterValidatingInterceptorTestCase.class);
    }

    @Test
    public void testValidateParameters() throws Exception {
        MethodParameterValidator mpv = createMock(MethodParameterValidator.class);
        InvocationContext ctx = createMock(InvocationContext.class);

        mpv.validateParameters((Method) anyObject(), (Object[]) anyObject());
        expectLastCall().once();
        ctx.getMethod();
        expectLastCall().andReturn(null);
        ctx.getParameters();
        expectLastCall().andReturn(new Object[0]);
        Object obj = new Object();
        ctx.proceed();
        expectLastCall().andReturn(obj);

        replay(mpv);
        replay(ctx);
        MethodParameterValidatingInterceptor impl = new MethodParameterValidatingInterceptor();
        impl.setMethodParameterValidator(mpv);
        impl.validateParameters(ctx);
        verify(mpv);
        verify(ctx);
    }
}
