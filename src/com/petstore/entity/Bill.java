package com.petstore.entity;

import java.sql.Timestamp;

public class Bill {
    private String billId;
    private String consignee;
    private String consigneeAddress;
    private String consigneePhone;
    private double money;
    private Timestamp createTime;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (Double.compare(bill.money, money) != 0) return false;
        if (billId != null ? !billId.equals(bill.billId) : bill.billId != null) return false;
        if (consignee != null ? !consignee.equals(bill.consignee) : bill.consignee != null) return false;
        if (consigneeAddress != null ? !consigneeAddress.equals(bill.consigneeAddress) : bill.consigneeAddress != null)
            return false;
        if (consigneePhone != null ? !consigneePhone.equals(bill.consigneePhone) : bill.consigneePhone != null)
            return false;
        if (createTime != null ? !createTime.equals(bill.createTime) : bill.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = billId != null ? billId.hashCode() : 0;
        result = 31 * result + (consignee != null ? consignee.hashCode() : 0);
        result = 31 * result + (consigneeAddress != null ? consigneeAddress.hashCode() : 0);
        result = 31 * result + (consigneePhone != null ? consigneePhone.hashCode() : 0);
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
