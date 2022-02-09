package com.sha.gateway.controller;

import com.google.gson.JsonElement;
import com.sha.gateway.model.service.AbstractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("gateway/product")
@RestController
public class ProductController
{
    @Autowired
    AbstractProductService productService;

    // api/product
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody JsonElement product)
    {
        JsonElement savedJsonElement = productService.save(product);

        return new ResponseEntity<>(savedJsonElement, HttpStatus.CREATED);
    }

    @DeleteMapping("{productID}") // api/product/productID
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productID)
    {
        productService.deleteByID(productID);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // api/product
    @GetMapping
    public ResponseEntity<?> getAllProducts()
    {
        List<JsonElement> productList = productService.findAll();

        return ResponseEntity.ok(productList); // cevabın döndürülmesi
    }

}
