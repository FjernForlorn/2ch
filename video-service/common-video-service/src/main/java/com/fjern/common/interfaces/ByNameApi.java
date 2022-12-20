package com.fjern.common.interfaces;

public interface ByNameApi<T extends WithName> {
    T findByName(final String name);
}
