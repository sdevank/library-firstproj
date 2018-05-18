package com.sample.library.membership;

public enum Membership {
    BASIC(3,15),SILVER(6,30),GOLD(12,60);
    private int renewalMonths;
    private int planDuration;

     Membership(int renewalMonths,int planDuration){
       this.renewalMonths=renewalMonths;
       this.planDuration=planDuration;
    }

    public int getRenewalMonths() {
        return renewalMonths;
    }

    public int getPlanDuration() {
        return planDuration;
    }
}
