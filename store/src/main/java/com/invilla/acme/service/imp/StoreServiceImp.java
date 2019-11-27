package com.invilla.acme.service.imp;

import com.invilla.acme.model.Store;
import com.invilla.acme.repository.StoreRepository;
import com.invilla.acme.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.invilla.acme.repository.specification.StoreSpecification.addressEquals;
import static com.invilla.acme.repository.specification.StoreSpecification.nameEquals;
import static org.springframework.data.jpa.domain.Specification.where;

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
    public List<Store> findAll(String name, String address) {
        List<Store> stores;
        if (name == null && address == null) {
            stores = storeRepository.findAll();
        } else {
            stores = storeRepository.findAll(where(nameEquals(name))
                    .or(addressEquals(address)));
        }
        return stores;
    }

    @Override
    public Store getById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found."));
    }

    private Store saveStore(Store store) {
        return storeRepository.save(store);
    }
}
