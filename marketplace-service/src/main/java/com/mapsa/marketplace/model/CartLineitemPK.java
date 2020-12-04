package com.mapsa.marketplace.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CartLineitemPK implements Serializable {
    private long cartId;
    private long lineitemId;

    @Column(name = "CART_ID")
    @Id
    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    @Column(name = "LINEITEM_ID")
    @Id
    public long getLineitemId() {
        return lineitemId;
    }

    public void setLineitemId(long lineitemId) {
        this.lineitemId = lineitemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartLineitemPK that = (CartLineitemPK) o;

        if (cartId != that.cartId) return false;
        if (lineitemId != that.lineitemId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cartId ^ (cartId >>> 32));
        result = 31 * result + (int) (lineitemId ^ (lineitemId >>> 32));
        return result;
    }
}
