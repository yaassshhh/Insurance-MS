package com.Yash.entity;

public class Policy {
    private int policyId;
    private String policyName;
    private double coverageAmount;
    private String terms;

    public Policy() {}

    public Policy(int policyId, String policyName, double coverageAmount, String terms) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.coverageAmount = coverageAmount;
        this.terms = terms;
    }

    public int getPolicyId() { return policyId; }
    public void setPolicyId(int policyId) { this.policyId = policyId; }

    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }

    public double getCoverageAmount() { return coverageAmount; }
    public void setCoverageAmount(double coverageAmount) { this.coverageAmount = coverageAmount; }

    public String getTerms() { return terms; }
    public void setTerms(String terms) { this.terms = terms; }

    // toString Method
    @Override
    public String toString() {
        return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", coverageAmount=" + coverageAmount + 
               ", terms=" + terms + "]";
    }
}

