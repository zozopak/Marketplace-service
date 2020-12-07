package com.mapsa.marketplace.model;

import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@ToString
public class Vendor {
    private long id;
    private long vendorNumber;
    private String firstname;
    private String lastname;
    private String phone;
    private String nationalId;
    private String email;
    private String remarks;
    private long lockVersion;
    private Collection<Address> adressesById;
    private Collection<Lineitem> lineitemsById;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "VENDOR_NUMBER")
    public long getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(long vendorNumber) {
        this.vendorNumber = vendorNumber;
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

        Vendor vendor = (Vendor) o;

        if (id != vendor.id) return false;
        if (vendorNumber != vendor.vendorNumber) return false;
        if (lockVersion != vendor.lockVersion) return false;
        if (firstname != null ? !firstname.equals(vendor.firstname) : vendor.firstname != null) return false;
        if (lastname != null ? !lastname.equals(vendor.lastname) : vendor.lastname != null) return false;
        if (phone != null ? !phone.equals(vendor.phone) : vendor.phone != null) return false;
        if (nationalId != null ? !nationalId.equals(vendor.nationalId) : vendor.nationalId != null) return false;
        if (email != null ? !email.equals(vendor.email) : vendor.email != null) return false;
        if (remarks != null ? !remarks.equals(vendor.remarks) : vendor.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (vendorNumber ^ (vendorNumber >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (nationalId != null ? nationalId.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "vendorByVendorId")
    public Collection<Address> getAdressesById() {
        return adressesById;
    }

    public void setAdressesById(Collection<Address> adressesById) {
        this.adressesById = adressesById;
    }

    @OneToMany(mappedBy = "vendorByVendorId")
    public Collection<Lineitem> getLineitemsById() {
        return lineitemsById;
    }

    public void setLineitemsById(Collection<Lineitem> lineitemsById) {
        this.lineitemsById = lineitemsById;
    }
}
