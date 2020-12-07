package com.mapsa.marketplace.model;

import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

@Entity
@ToString
public class Orders {
    private long id;
    private String orderNumber;
    private Time orderDate;
    private long orderStatusId;
    private long cartId;
    private long lockVersion;
    private Collection<Bill> billsById;
    private Collection<OrderStatus> orderStatusesById;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORDER_NUMBER")
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Basic
    @Column(name = "ORDER_DATE")
    public Time getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Time orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "ORDER_STATUS_ID")
    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    @Basic
    @Column(name = "CART_ID")
    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
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

        Orders orders = (Orders) o;

        if (id != orders.id) return false;
        if (orderStatusId != orders.orderStatusId) return false;
        if (cartId != orders.cartId) return false;
        if (lockVersion != orders.lockVersion) return false;
        if (orderNumber != null ? !orderNumber.equals(orders.orderNumber) : orders.orderNumber != null) return false;
        if (orderDate != null ? !orderDate.equals(orders.orderDate) : orders.orderDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (int) (orderStatusId ^ (orderStatusId >>> 32));
        result = 31 * result + (int) (cartId ^ (cartId >>> 32));
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "ordersByOrderId")
    public Collection<Bill> getBillsById() {
        return billsById;
    }

    public void setBillsById(Collection<Bill> billsById) {
        this.billsById = billsById;
    }

    @OneToMany(mappedBy = "ordersByOrderId")
    public Collection<OrderStatus> getOrderStatusesById() {
        return orderStatusesById;
    }

    public void setOrderStatusesById(Collection<OrderStatus> orderStatusesById) {
        this.orderStatusesById = orderStatusesById;
    }
}
