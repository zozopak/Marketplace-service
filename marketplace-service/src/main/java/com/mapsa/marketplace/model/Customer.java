package com.mapsa.marketplace.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Customer {
    private long id;
    private String customerNumber;
    private String firstname;
    private String lastname;
    private String phone;
    private String nationalId;
    private String email;
    private String remarks;
    private long lockVersion;
    private Collection<Address> adressesById;
    private Collection<Cart> cartsById;
    private Wallet walletByWalletId;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CUSTOMER_NUMBER")
    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Basic
    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "NATIONAL_ID")
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (lockVersion != customer.lockVersion) return false;
        if (customerNumber != null ? !customerNumber.equals(customer.customerNumber) : customer.customerNumber != null)
            return false;
        if (firstname != null ? !firstname.equals(customer.firstname) : customer.firstname != null) return false;
        if (lastname != null ? !lastname.equals(customer.lastname) : customer.lastname != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        if (nationalId != null ? !nationalId.equals(customer.nationalId) : customer.nationalId != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (remarks != null ? !remarks.equals(customer.remarks) : customer.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (customerNumber != null ? customerNumber.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (nationalId != null ? nationalId.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<Address> getAdressesById() {
        return adressesById;
    }

    public void setAdressesById(Collection<Address> adressesById) {
        this.adressesById = adressesById;
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<Cart> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<Cart> cartsById) {
        this.cartsById = cartsById;
    }

    @ManyToOne
    @JoinColumn(name = "WALLET_ID", referencedColumnName = "ID", nullable = false)
    public Wallet getWalletByWalletId() {
        return walletByWalletId;
    }

    public void setWalletByWalletId(Wallet walletByWalletId) {
        this.walletByWalletId = walletByWalletId;
    }
}
