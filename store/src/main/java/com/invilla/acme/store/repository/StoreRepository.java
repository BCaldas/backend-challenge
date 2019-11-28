package com.invilla.acme.store.repository;

import com.invilla.acme.commons.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StoreRepository extends
        JpaRepository<Store, Long>, JpaSpecificationExecutor<Store> {

    Store findByNameOrAddress(String name, String address);
}
