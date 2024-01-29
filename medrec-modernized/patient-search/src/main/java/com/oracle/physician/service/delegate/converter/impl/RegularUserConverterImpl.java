package com.oracle.physician.service.delegate.converter.impl;

import com.oracle.medrec.model.PersonName;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
abstract class RegularUserConverterImpl {

    public PersonName fromJaxbPersonName(
            com.oracle.physician.service.delegate.stub.jaxws.PersonName jaxbPersonName) {
        PersonName personName = new PersonName();
        personName.setFirstName(jaxbPersonName.getFirstName());
        personName.setMiddleName(jaxbPersonName.getMiddleName());
        personName.setLastName(jaxbPersonName.getLastName());
        return personName;
    }
}
