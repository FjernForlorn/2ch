package com.fjern.common.web.util;

import org.springframework.stereotype.Component;

@Component
public class UriMapperImpl implements UriMapper{

    public UriMapperImpl(){
        super();
    }

    @Override
    public <T> String getUriBase(Class<T> clazz) {
        String simpleName = clazz.getSimpleName().toString().toLowerCase();
        if (simpleName.endsWith("dto")) {
            simpleName= simpleName.substring(0,simpleName.length()-3);
        }
        return simpleName+"s";
    }
}
