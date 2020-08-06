package com.example.restwithdata.service;

import com.example.restwithdata.model.Model;

import javax.ws.rs.core.Response;

public interface Service<R extends Response,M extends Model,K> {
    R get(K in);

    R create(M in);

    R delete(K in);

    R update(M in);
}
