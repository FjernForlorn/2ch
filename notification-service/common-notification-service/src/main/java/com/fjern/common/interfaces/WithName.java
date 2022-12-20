package com.fjern.common.interfaces;

import java.io.Serializable;

public interface WithName extends Serializable {
    String getName();
    void setName(String name);
}
