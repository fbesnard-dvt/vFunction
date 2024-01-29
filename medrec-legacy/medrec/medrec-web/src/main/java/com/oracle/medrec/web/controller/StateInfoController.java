package com.oracle.medrec.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 * @anthor Copyright (c) 2012, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * 
 */
@ManagedBean
public class StateInfoController {

    private static final String[] STATE_NAMES = { "Alabama", "Alaska",
            "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
            "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois",
            "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",
            "Maryland", "Massachusetts", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada",
            "New Hampshire", "New Jersey", "New Mexico", "New York",
            "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
            "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
            "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
            "West Virginia", "Wisconsin", "Wyoming" };

    public List<SelectItem> getStates() {
        List<SelectItem> states = new ArrayList<SelectItem>();
        states.add(new SelectItem("", "--- Select State ---"));
        for (String name : STATE_NAMES) {
            states.add(new SelectItem(name));
        }
        return (states);
    }
}
