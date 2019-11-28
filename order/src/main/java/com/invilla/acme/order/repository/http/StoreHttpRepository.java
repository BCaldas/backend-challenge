package com.invilla.acme.order.repository.http;

import com.invilla.acme.commons.model.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "store", url = "${store.url}")
@Repository
public interface StoreHttpRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/{storeId}", produces = "application/json")
    ResponseEntity<Store> getStoreById(@PathVariable Long storeId);

}
