package com.invilla.acme.order.service.imp;

import com.invilla.acme.order.enums.EItemStatus;
import com.invilla.acme.order.model.Item;
import com.invilla.acme.order.repository.ItemRepository;
import com.invilla.acme.order.service.ItemService;
import com.invilla.acme.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ItemServiceImp implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PaymentService paymentService;

    @Override
    public Item refund(Long itemId) {
        Item item = getById(itemId);
        paymentService.validateRefund(item.getOrder());
        item.setStatus(EItemStatus.REFUNDED);
        return saveItem(item);
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found."));
    }

    private Item saveItem(Item item) {
        return itemRepository.save(item);
    }
}
