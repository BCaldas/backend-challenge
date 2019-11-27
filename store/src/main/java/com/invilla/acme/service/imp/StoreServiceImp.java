package com.invilla.acme.service.imp;

import com.invilla.acme.model.Store;
import com.invilla.acme.repository.StoreRepository;
import com.invilla.acme.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class StoreServiceImp implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store addStore(Store newStore) {
        return saveStore(newStore);
    }

    @Override
    public Store updateStore(Long storeId, Store updatedStore) {
        Store storeToUpdate = getById(storeId);

        storeToUpdate.setAddress(updatedStore.getAddress());
        storeToUpdate.setName(updatedStore.getName());
        return saveStore(storeToUpdate);
    }

    @Override
    public Store findByNameOrAddress(String name, String address) {
        return storeRepository.findByNameOrAddress(name, address);
    }

    @Override
    public Store getById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));
    }

    private Store saveStore(Store store) {
        return storeRepository.save(store);
    }
}
