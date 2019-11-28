package com.invilla.acme.order.repository;

import com.invilla.acme.commons.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
