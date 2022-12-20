package com.fjern.app.web.util;

import com.fjern.common.web.client.CommonPaths;
import com.fjern.common.web.util.UriMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UtilPaths {

    @Value("${http.sec.path}")
    private String secPath;

    @Autowired
    private CommonPaths commonPaths;

    @Autowired
    private UriMapper uriMapper;

    public final String getContext() {
        return commonPaths.getServerRoot()+secPath;
    }

    public final String getRootUri() {
        return getContext();
    }

}
