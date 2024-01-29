package com.oracle.physician;

import com.oracle.medrec.common.util.ServerPropertiesUtils;

/**
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class JaxRsProperties {

    public final static String RSURL = "http://"
            + ServerPropertiesUtils.getServerProperty("medrecAppServerAddress",
                    "localhost")
            + ":"
            + ServerPropertiesUtils.getServerProperty("medrecAppServerPort",
                    "7001") + "/medrec-jaxrs-services/resources/";
    
    public final static String RECORD_URI = RSURL+ "records";
    
    public final static String PHYSICIAN_URI = RSURL+ "physician";
}
