package com.invilla.acme.controller;

import com.invilla.acme.model.Store;
import com.invilla.acme.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Store addStore(@RequestBody Store newStore) {
        return storeService.addStore(newStore);
    }
}
