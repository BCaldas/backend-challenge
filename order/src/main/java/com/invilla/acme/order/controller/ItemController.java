package com.invilla.acme.order.controller;

import com.invilla.acme.commons.model.Item;
import com.invilla.acme.order.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PutMapping("/{id}/refund")
    public Item refundItem(@PathVariable Long id) {
        return itemService.refund(id);
    }
}
