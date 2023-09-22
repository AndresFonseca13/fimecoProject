package com.fimeco.fimeco.domain.producto;


import com.fimeco.fimeco.domain.pedido.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public ResponseEntity<DatosRespuestaProducto> crearProducto(DatosRegistroProducto datos){
        System.out.println(datos.pedido_id());
        if(!pedidoRepository.findById(datos.pedido_id()).isPresent()){
            throw new IllegalArgumentException("El pedido no existe");
        }
        var pedido = pedidoRepository.findById(datos.pedido_id()).get();

        var producto = new Producto(datos, pedido);
        productoRepository.save(producto);
        return ResponseEntity.ok(new DatosRespuestaProducto(producto));

    }

}
