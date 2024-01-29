package com.oracle.medrec.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = 4907071444700296617L;

    private String city;

    private String country;

    private String state;

    private String street1;

    private String street2;

    private String zip;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;

        if (city != null ? !city.equals(address.getCity())
                : address.getCity() != null) {
            return false;
        }
        if (country != null ? !country.equals(address.getCountry()) : address
                .getCountry() != null) {
            return false;
        }
        if (state != null ? !state.equals(address.getState()) : address
                .getState() != null) {
            return false;
        }
        if (street1 != null ? !street1.equals(address.getStreet1()) : address
                .getStreet1() != null) {
            return false;
        }
        if (street2 != null ? !street2.equals(address.getStreet2()) : address
                .getStreet2() != null) {
            return false;
        }
        if (zip != null ? !zip.equals(address.getZip())
                : address.getZip() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (city != null ? city.hashCode() : 0);
        result = 29 * result + (country != null ? country.hashCode() : 0);
        result = 29 * result + (state != null ? state.hashCode() : 0);
        result = 29 * result + (street1 != null ? street1.hashCode() : 0);
        result = 29 * result + (street2 != null ? street2.hashCode() : 0);
        result = 29 * result + (zip != null ? zip.hashCode() : 0);
        return result;
    }
}
