package com.vysotski.funpay.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
//    realizovat' equals, hashCode

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
