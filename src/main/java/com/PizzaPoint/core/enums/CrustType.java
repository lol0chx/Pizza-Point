package com.PizzaPoint.core.enums;

public enum CrustType {
    REGULAR (0.0),
    THIN (0.0),
    THICK (0.0),
    PAN (0.0),
    STUFFED (2.0);
    private final double extraCost;

    CrustType(double extraCost) {

        this.extraCost = extraCost;
    }

    public double getExtraCost() {

        return extraCost;
    }
}
