package com.invilla.acme.controller;

import com.invilla.acme.model.Store;
import com.invilla.acme.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Store addStore(@RequestBody @Valid Store newStore) {
        return storeService.addStore(newStore);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody @Valid Store updatedStore) {
        return storeService.updateStore(id, updatedStore);
    }

    @GetMapping
    public List<Store> getAll(@RequestParam Map<String, String> parameters) {
        String name = parameters.get("name");
        String address = parameters.get("address");
        return storeService.findAll(name, address);
    }

    @GetMapping("/{id}")
    public Store getById(@PathVariable Long id) {
        return storeService.getById(id);
    }
}
