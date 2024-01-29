package com.oracle.medrec.model;

import java.io.Serializable;

/**
 * All the domain models should be serializable so that they can be safely
 * stored in HTTP Session and so on, but it doesn't always make sense to
 * directly pass them in remote calls.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class DomainModel implements Serializable {

    private static final long serialVersionUID = 1L;

}
