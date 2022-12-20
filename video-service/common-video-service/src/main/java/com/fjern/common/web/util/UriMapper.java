package com.fjern.common.web.util;

public interface UriMapper {
   <T> String getUriBase(final Class<T> clazz);
}
