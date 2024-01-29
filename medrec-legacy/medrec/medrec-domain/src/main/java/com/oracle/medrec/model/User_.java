package com.oracle.medrec.model;

import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SingularAttribute;

/**
 * User entity's MetaModel.
 * 
 * @author Xiaojun Wu. <br>
 *         Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@StaticMetamodel(User.class)
public class User_ {

    public static volatile SingularAttribute<User, String> username;

    public static volatile SingularAttribute<User, String> password;

    public static volatile SingularAttribute<User, String> email;

}
