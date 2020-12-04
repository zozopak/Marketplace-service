package com.mapsa.marketplace.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Wallet {
    private long id;
    private long balance;
    private long store;
    private long customerId;
    private String remarks;
    private long lockVersion;
    private Collection<Customer> customersById;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "BALANCE")
    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "STORE")
    public long getStore() {
        return store;
    }

    public void setStore(long store) {
        this.store = store;
    }

    @Basic
    @Column(name = "CUSTOMER_ID")
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

        Wallet wallet = (Wallet) o;

        if (id != wallet.id) return false;
        if (balance != wallet.balance) return false;
        if (store != wallet.store) return false;
        if (customerId != wallet.customerId) return false;
        if (lockVersion != wallet.lockVersion) return false;
        if (remarks != null ? !remarks.equals(wallet.remarks) : wallet.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (balance ^ (balance >>> 32));
        result = 31 * result + (int) (store ^ (store >>> 32));
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "walletByWalletId")
    public Collection<Customer> getCustomersById() {
        return customersById;
    }

    public void setCustomersById(Collection<Customer> customersById) {
        this.customersById = customersById;
    }
}
