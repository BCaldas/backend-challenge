package com.invilla.acme.order.service;

import com.invilla.acme.order.model.Item;

public interface ItemService {
    Item refund(Long itemId);

    Item getById(Long id);
}
