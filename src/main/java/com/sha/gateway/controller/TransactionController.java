package com.sha.gateway.controller;

import com.google.gson.JsonElement;
import com.sha.gateway.model.service.AbstractTransactionService;
import com.sha.gateway.security.model.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("gateway/transaction")
@RestController
public class TransactionController
{
    @Autowired
    private AbstractTransactionService transactionService;

    /* Oturum açan kullanıcının tüm işlemlerini (transactions) listelemek için
    * filtreler isteklerden önce calisir
    *
    * @AuthenticationPrincipal ile oturum acan kullaniciya Controller'dan kolayca erisilir.
    * */
    @GetMapping
    public ResponseEntity<?> getTransactionOfAuthorizedUser(@AuthenticationPrincipal UserPrinciple userPrinciple)
    {
        return ResponseEntity.ok(transactionService.findByUserID(userPrinciple.getId()));
    }

    // api/transaction
    @GetMapping("all")
    public ResponseEntity<?> getAllTransaction()
    {
        return ResponseEntity.ok(transactionService.findAll());
    }

    // api/transaction/transactionID
    @DeleteMapping("{transactionID}")
    public ResponseEntity<?> deleteTransactionByID(@PathVariable Integer transactionID)
    {
        transactionService.deleteByID(transactionID);

        return new ResponseEntity(HttpStatus.OK);
    }

    // api/transaction
    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody JsonElement transaction,@AuthenticationPrincipal UserPrinciple userPrinciple)
    {
        transaction.getAsJsonObject().addProperty("userID",userPrinciple.getId());
        JsonElement savedJsonElement = transactionService.save(transaction);
        System.out.println(transaction.toString());
        return new ResponseEntity<>(savedJsonElement, HttpStatus.CREATED);
    }
}
