package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.client.Client;
import com.fimeco.fimeco.domain.client.ClientRepository;
import com.fimeco.fimeco.domain.order.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<DataResponseOrder> registerOrder(@RequestBody @Valid DataRegisterOrder dataRegisterOrder){
        Client client = clientRepository.findById(dataRegisterOrder.clienteId()).orElseThrow();
        Order order = new Order(dataRegisterOrder, client);
        orderRepository.save(order);
        return ResponseEntity.ok(new DataResponseOrder(order));
    }

    @GetMapping
    public ResponseEntity<Page<DataListOrder>> listOrder(@PageableDefault(size = 5)Pageable pagination){
        return ResponseEntity.ok(orderRepository.findAll(pagination).map(DataListOrder::new));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseOrder> getOrder(@PathVariable Long id){
        Order order = orderRepository.getReferenceById(id);
        DataResponseOrder dataResponseOrder = new DataResponseOrder(order);
        return ResponseEntity.ok(dataResponseOrder);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Order>> getOrderPerState(@PathVariable State state){
        List<Order> orders = orderRepository.getAllByState(state);
        return ResponseEntity.ok(orders);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseOrder> updateOrder(@RequestBody @Valid DataUpdateOrder dataUpdateOrder){
        Order order = orderRepository.getReferenceById(dataUpdateOrder.id());
        order.updateOrder(dataUpdateOrder);
        return ResponseEntity.ok(new DataResponseOrder(order));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseOrder> deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
