package com.company.capi.domain;

import com.company.myloyal.LoyaltyPoints;

import java.util.List;

/**
 * Created by jmbataller on 08/12/14.
 */
public class Points {

    private int totalPoints;
    private List<LoyaltyPoints> loyaltyPoints;


    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<LoyaltyPoints> getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(List<LoyaltyPoints> loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
