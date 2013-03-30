package com.bekk.Faggruppe.domain;

import com.google.inject.Inject;

public class Result {
    private String number;

    @Inject
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
