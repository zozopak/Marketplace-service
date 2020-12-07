package com.mapsa.marketplace.model;

import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
public class Lineitem {
    private long id;
    private String firstname;
    private String lastname;
    private String remarks;
    private long lockVersion;
    private TicketStatus ticketStatusByTicketStatusId;
    private Vendor vendorByVendorId;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

        Lineitem lineitem = (Lineitem) o;

        if (id != lineitem.id) return false;
        if (lockVersion != lineitem.lockVersion) return false;
        if (firstname != null ? !firstname.equals(lineitem.firstname) : lineitem.firstname != null) return false;
        if (lastname != null ? !lastname.equals(lineitem.lastname) : lineitem.lastname != null) return false;
        if (remarks != null ? !remarks.equals(lineitem.remarks) : lineitem.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "TICKET_STATUS_ID", referencedColumnName = "ID", nullable = false)
    public TicketStatus getTicketStatusByTicketStatusId() {
        return ticketStatusByTicketStatusId;
    }

    public void setTicketStatusByTicketStatusId(TicketStatus ticketStatusByTicketStatusId) {
        this.ticketStatusByTicketStatusId = ticketStatusByTicketStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "VENDOR_ID", referencedColumnName = "ID", nullable = false)
    public Vendor getVendorByVendorId() {
        return vendorByVendorId;
    }

    public void setVendorByVendorId(Vendor vendorByVendorId) {
        this.vendorByVendorId = vendorByVendorId;
    }
}
