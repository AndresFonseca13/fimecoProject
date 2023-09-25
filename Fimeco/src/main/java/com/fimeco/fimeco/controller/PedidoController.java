package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.cliente.Cliente;
import com.fimeco.fimeco.domain.cliente.ClienteRepository;
import com.fimeco.fimeco.domain.pedido.*;
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
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPedido> registrarPedido(@RequestBody @Valid DatosRegistroPedido datosRegistroPedido){
        Cliente cliente = clienteRepository.findById(datosRegistroPedido.clienteId()).orElseThrow();
        Pedido pedido = new Pedido(datosRegistroPedido, cliente);
        pedidoRepository.save(pedido);
        return ResponseEntity.ok(new DatosRespuestaPedido(pedido.getId(), pedido.getFechaPedido(), pedido.getFechaEntrega(),
                pedido.getEstado(), pedido.getDescripcion(), pedido.getCantidad(), pedido.getPrecio(), pedido.getFormaPago(),
                pedido.getCliente().getId(), pedido.getCliente().getNombre()));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarPedido>> listarPedidos(@PageableDefault(size = 5)Pageable paginacion){
        return ResponseEntity.ok(pedidoRepository.findAll(paginacion).map(DatosListarPedido::new));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPedido> obtenerPedido(@PathVariable Long id){
        Pedido pedido = pedidoRepository.getReferenceById(id);
        DatosRespuestaPedido datosRespuestaPedido = new DatosRespuestaPedido(pedido.getId(), pedido.getFechaPedido(), pedido.getFechaEntrega(),
                pedido.getEstado(), pedido.getDescripcion(), pedido.getCantidad(), pedido.getPrecio(), pedido.getFormaPago(),
                pedido.getCliente().getId(), pedido.getCliente().getNombre());
        return ResponseEntity.ok(datosRespuestaPedido);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> obtenerPedidoPorEstado(@PathVariable Estado estado){
        List<Pedido> pedidos = pedidoRepository.getAllByEstado(estado);
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPedido> actualizarPedido(@RequestBody @Valid DatosActualizarPedido datosActualizarPedido){
        Pedido pedido = pedidoRepository.getReferenceById(datosActualizarPedido.id());
        pedido.actualizarPedido(datosActualizarPedido);
        return ResponseEntity.ok(new DatosRespuestaPedido(pedido.getId(), pedido.getFechaPedido(), pedido.getFechaEntrega(),
                pedido.getEstado(), pedido.getDescripcion(), pedido.getCantidad(), pedido.getPrecio(), pedido.getFormaPago(),
                pedido.getCliente().getId(), pedido.getCliente().getNombre()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaPedido> eliminarPedido(@PathVariable Long id){
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
