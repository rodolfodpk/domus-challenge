package com.domus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiConfig {

    @Value("${api.movies.scheme}")
    public String scheme = "http";

    @Value("${api.movies.host}")
    public String host;

    @Value("${api.movies.port}")
    public Integer port;

    @Value("${api.movies.path}")
    public String path;

}
