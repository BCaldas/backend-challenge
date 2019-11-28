package com.invilla.acme.store.repository.specification;

import com.invilla.acme.commons.model.Store;
import org.springframework.data.jpa.domain.Specification;

public class StoreSpecification {

    public static Specification<Store> nameEquals(String name) {
        return (store, cq, cb) -> cb.equal(store.get("name"), name);
    }

    public static Specification<Store> addressEquals(String address) {
        return (store, cq, cb) -> cb.equal(store.get("address"), address);
    }
}
