package com.oracle.medrec.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class from which every concrete entity inherits.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@MappedSuperclass
public abstract class BaseEntity extends DomainModel {

    private static final long serialVersionUID = 1L;

    /**
     * Field corresponding to database primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    // TODO better to be protected
    public void setId(Long id) {
        this.id = id;
    }
}
