package com.invilla.acme.service;

import com.invilla.acme.model.Store;

public interface StoreService {

    Store addStore(Store newStore);
    Store updateStore(Long storeId, Store updatedStore);
    Store findByNameOrAddress(String name, String address);

    Store getById(Long id);
}
