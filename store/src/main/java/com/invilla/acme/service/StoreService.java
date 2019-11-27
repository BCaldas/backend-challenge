package com.invilla.acme.service;

import com.invilla.acme.model.Store;

public interface StoreService {
    Store addStore(Store newStore);

    Store updateStore(Integer storeId, Store updatedStore);
}
