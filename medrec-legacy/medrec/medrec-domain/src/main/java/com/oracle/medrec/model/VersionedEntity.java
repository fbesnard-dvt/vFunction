package com.oracle.medrec.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base class from which every concrete entity requiring version for optimistic
 * concurrency control inherits.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@MappedSuperclass
public abstract class VersionedEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Field used by JPA's optimistic concurrency control
     */
    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    // TODO better to be protected
    public void setVersion(Integer version) {
        this.version = version;
    }
}
