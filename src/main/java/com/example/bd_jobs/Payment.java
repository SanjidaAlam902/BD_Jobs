package com.example.bd_jobs;

public class Payment {
    private String packageName;
    private String billingName;
    private String paymentMethod;
    private double amount;

    public Payment(String packageName, String billingName, String paymentMethod, double amount) {
        this.packageName = packageName;
        this.billingName = billingName;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }

    public String getBillingName() { return billingName; }
    public void setBillingName(String billingName) { this.billingName = billingName; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
