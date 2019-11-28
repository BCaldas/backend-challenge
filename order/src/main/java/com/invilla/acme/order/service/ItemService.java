package com.invilla.acme.order.service;


import com.invilla.acme.commons.model.Item;

public interface ItemService {
    Item refund(Long itemId);

    Item getById(Long id);
}
