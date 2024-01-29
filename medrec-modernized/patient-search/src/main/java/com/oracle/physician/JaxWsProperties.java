package com.oracle.physician;

import com.oracle.medrec.common.util.ServerPropertiesUtils;

/**
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class JaxWsProperties {

    public final static String NAMESPACEURL = "http://www.oracle.com/medrec";

    public final static String WSURL = "http://"
            + ServerPropertiesUtils.getServerProperty("medrecAppServerAddress",
                    "localhost")
            + ":"
            + ServerPropertiesUtils.getServerProperty("medrecAppServerPort",
                    "7001") + "/medrec-jaxws-services/";
}
