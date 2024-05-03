package com.fimeco.fimeco.domain.product;


import com.fimeco.fimeco.domain.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<DataResponseProduct> createProduct(DataRegisterProduct dataRegisterProduct){
        System.out.println(dataRegisterProduct.order_id());
        if(!orderRepository.findById(dataRegisterProduct.order_id()).isPresent()){
            throw new IllegalArgumentException("El pedido no existe");
        }
        var order = orderRepository.findById(dataRegisterProduct.order_id()).get();

        var product = new Product(dataRegisterProduct, order);
        productRepository.save(product);
        return ResponseEntity.ok(new DataResponseProduct(product));

    }

}
