package com.invilla.acme.store.service;

import com.invilla.acme.commons.model.Store;

import java.util.List;

public interface StoreService {

    Store addStore(Store newStore);
    Store updateStore(Long storeId, Store updatedStore);
    List<Store> findAll(String name, String address);

    Store getById(Long id);
}
