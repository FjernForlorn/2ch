package com.fjern.common.client;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CommonPaths implements InitializingBean {


    @Autowired
    private Environment env;

    @Value(value = "${http.protocol}")
    private String protocol;

    @Value(value = "${http.host}")
    private String host;

    @Value(value = "${server.port}")
    private int port;

    public String getServerRoot(){
        return protocol + "://" + host + ":" + port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (protocol == null || protocol.equals("${http.protocol}")) {
            protocol = Preconditions.checkNotNull(env.getProperty("http.protocol"));
        }
        if (host == null || host.equals("${http.host}")) {
            host = Preconditions.checkNotNull(env.getProperty("http.host"));
        }
        port = Preconditions.checkNotNull(env.getProperty("server.port", Integer.class));
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
