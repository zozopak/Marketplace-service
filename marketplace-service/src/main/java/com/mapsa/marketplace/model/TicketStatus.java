package com.mapsa.marketplace.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TICKET_STATUS", schema = "MARKETPLACE")
public class TicketStatus {
    private long id;
    private String status;
    private String remarks;
    private long lockVersion;
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
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        TicketStatus that = (TicketStatus) o;

        if (id != that.id) return false;
        if (lockVersion != that.lockVersion) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "ticketStatusByTicketStatusId")
    public Collection<Lineitem> getLineitemsById() {
        return lineitemsById;
    }

    public void setLineitemsById(Collection<Lineitem> lineitemsById) {
        this.lineitemsById = lineitemsById;
    }
}
