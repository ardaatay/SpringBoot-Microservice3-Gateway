package com.sha.gateway.model.service;

import com.google.gson.JsonElement;
import com.sha.gateway.channel.utility.RetrofitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService extends AbstractProductService
{
    @Override
    public List<JsonElement> findAll()
    {
        return RetrofitUtil.callGenericBlock(productServiceCallable.getAll());
    }

    @Override
    public JsonElement save(JsonElement entity)
    {
        return RetrofitUtil.callGenericBlock(productServiceCallable.save(entity));
    }

    @Override
    public void deleteByID(Integer id)
    {
        RetrofitUtil.callGenericBlock(productServiceCallable.deleteByID(id));
    }
}
