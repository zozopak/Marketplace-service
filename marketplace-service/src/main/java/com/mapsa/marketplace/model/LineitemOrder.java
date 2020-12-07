package com.mapsa.marketplace.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Table(name = "LINEITEM_ORDER", schema = "MARKETPLACE")
@IdClass(LineitemOrderPK.class)
public class LineitemOrder {
    private long lineitemId;
    private long orderId;
    private String remarks;
    private long lockVersion;

    @Id
    @Column(name = "LINEITEM_ID")
    public long getLineitemId() {
        return lineitemId;
    }

    public void setLineitemId(long lineitemId) {
        this.lineitemId = lineitemId;
    }

    @Id
    @Column(name = "ORDER_ID")
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

        LineitemOrder that = (LineitemOrder) o;

        if (lineitemId != that.lineitemId) return false;
        if (orderId != that.orderId) return false;
        if (lockVersion != that.lockVersion) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (lineitemId ^ (lineitemId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }
}
