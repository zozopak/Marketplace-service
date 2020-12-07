package com.mapsa.marketplace.model;

import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;

@Entity
@ToString
public class Cart {
    private long id;
    private Time cartdate;
    private String remarks;
    private long lockVersion;
    private Customer customerByCustomerId;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CARTDATE")
    public Time getCartdate() {
        return cartdate;
    }

    public void setCartdate(Time cartdate) {
        this.cartdate = cartdate;
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

        Cart cart = (Cart) o;

        if (id != cart.id) return false;
        if (lockVersion != cart.lockVersion) return false;
        if (cartdate != null ? !cartdate.equals(cart.cartdate) : cart.cartdate != null) return false;
        if (remarks != null ? !remarks.equals(cart.remarks) : cart.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (cartdate != null ? cartdate.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }


}
