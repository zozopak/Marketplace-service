package com.mapsa.marketplace.model;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_STATUS", schema = "MARKETPLACE")
public class OrderStatus {
    private long id;
    private long shiped;
    private long delivered;
    private long closed;
    private long lockVersion;
    private Orders ordersByOrderId;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SHIPED")
    public long getShiped() {
        return shiped;
    }

    public void setShiped(long shiped) {
        this.shiped = shiped;
    }

    @Basic
    @Column(name = "DELIVERED")
    public long getDelivered() {
        return delivered;
    }

    public void setDelivered(long delivered) {
        this.delivered = delivered;
    }

    @Basic
    @Column(name = "CLOSED")
    public long getClosed() {
        return closed;
    }

    public void setClosed(long closed) {
        this.closed = closed;
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

        OrderStatus that = (OrderStatus) o;

        if (id != that.id) return false;
        if (shiped != that.shiped) return false;
        if (delivered != that.delivered) return false;
        if (closed != that.closed) return false;
        if (lockVersion != that.lockVersion) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (shiped ^ (shiped >>> 32));
        result = 31 * result + (int) (delivered ^ (delivered >>> 32));
        result = 31 * result + (int) (closed ^ (closed >>> 32));
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
}
