package com.mapsa.marketplace.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class LineitemOrderPK implements Serializable {
    private long lineitemId;
    private long orderId;

    @Column(name = "LINEITEM_ID")
    @Id
    public long getLineitemId() {
        return lineitemId;
    }

    public void setLineitemId(long lineitemId) {
        this.lineitemId = lineitemId;
    }

    @Column(name = "ORDER_ID")
    @Id
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineitemOrderPK that = (LineitemOrderPK) o;

        if (lineitemId != that.lineitemId) return false;
        if (orderId != that.orderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (lineitemId ^ (lineitemId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        return result;
    }
}
