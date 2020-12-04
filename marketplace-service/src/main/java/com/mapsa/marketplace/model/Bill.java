package com.mapsa.marketplace.model;

import javax.persistence.*;

@Entity
public class Bill {
    private long id;
    private String billNumber;
    private String total;
    private String remarks;
    private long lockVersion;
    private Orders ordersByOrderId;
    private Payment paymentByPaymentId;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "BILL_NUMBER")
    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    @Basic
    @Column(name = "TOTAL")
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Basic
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "LOCK_VERSION")
    public long getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(long lockVersion) {
        this.lockVersion = lockVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (id != bill.id) return false;
        if (lockVersion != bill.lockVersion) return false;
        if (billNumber != null ? !billNumber.equals(bill.billNumber) : bill.billNumber != null) return false;
        if (total != null ? !total.equals(bill.total) : bill.total != null) return false;
        if (remarks != null ? !remarks.equals(bill.remarks) : bill.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (billNumber != null ? billNumber.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false)
    public Orders getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(Orders ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID", nullable = false)
    public Payment getPaymentByPaymentId() {
        return paymentByPaymentId;
    }

    public void setPaymentByPaymentId(Payment paymentByPaymentId) {
        this.paymentByPaymentId = paymentByPaymentId;
    }
}
