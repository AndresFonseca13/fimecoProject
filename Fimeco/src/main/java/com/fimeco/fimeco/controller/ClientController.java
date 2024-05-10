package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.client.*;
import com.fimeco.fimeco.domain.address.DataAddress;
import com.fimeco.fimeco.domain.address.Country;
import com.fimeco.fimeco.domain.user.UserEntity;
import com.fimeco.fimeco.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    public ClientController(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<DataResponseClient> registerClient(@RequestBody @Valid DataRegisterClient dataRegisterClient){
        try{
            if (clientRepository.existsByEmail(dataRegisterClient.email())){
                throw new IllegalArgumentException("El email ya se encuentra registrado");
            } else if (clientRepository.existsByPhone(dataRegisterClient.phone())){
                throw new IllegalArgumentException("El phone ya se encuentra registrado");
            }
            UserEntity userEntity = userRepository.findById(dataRegisterClient.user_id()).orElseThrow();
            System.out.println(userEntity.getUsername());
            System.out.println(userEntity.getUserId());
            Client client = clientRepository.save(new Client(dataRegisterClient, userEntity));
            DataResponseClient dataResponseClient = new DataResponseClient(client.getId(), client.getName(), client.getEmail(),
                    client.getPhone(), client.getNamePerson(),
                    new DataAddress(client.getAddress().getStreet(), client.getAddress().getRace(), client.getAddress().getNumber(),
                            client.getAddress().getDepartment(), client.getAddress().getCity(), client.getAddress().getComplement(), client.getAddress().getCountry(),
                            client.getAddress().getAddressComplete()));
            return ResponseEntity.ok(dataResponseClient);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<DataListClients>> listClients(@PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(clientRepository.findAll(pagination).map(DataListClients::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseClient> getClient(@PathVariable Long id){
        Client client = clientRepository.getReferenceById(id);
        DataResponseClient dataResponseClient = new DataResponseClient(client);
        return ResponseEntity.ok(dataResponseClient);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Client>> getClientsByCountry(@PathVariable Country country){
        List<Client> clients = clientRepository.getAllByCountry(country);
        return ResponseEntity.ok(clients);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseClient> updateClient(@RequestBody @Valid DataUpdateClient dataUpdateClient){
        Client client = clientRepository.getReferenceById(dataUpdateClient.id());
        client.dataUpdate(dataUpdateClient);
        return ResponseEntity.ok(new DataResponseClient(client));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseClient> deleteClient(@PathVariable Long id){
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
