package br.com.DevLopes.api.controller;

import br.com.DevLopes.api.assembler.ClienteAssembler;
import br.com.DevLopes.api.model.entity.ClienteModel;
import br.com.DevLopes.api.model.input.ClienteInput;
import br.com.DevLopes.domain.model.entity.Cliente;
import br.com.DevLopes.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/clientes")
public class ClienteCotroller {

    private CatalogoClienteService catalogoClienteService;
    private ClienteAssembler clienteAssembler;

    @GetMapping
    public List<ClienteModel> listar() {
        return clienteAssembler.toListModel(catalogoClienteService.buscarClientes());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long id) {
        ClienteModel clienteModel = clienteAssembler.toModel(catalogoClienteService.buscarClientePorId(id));
        return ResponseEntity.ok(clienteModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel inserir(@Valid @RequestBody ClienteInput clienteInput) {
        Cliente cliente = clienteAssembler.toEntity(clienteInput);
        Cliente clienteCadastrado = catalogoClienteService.salvarCliente(cliente);
        return clienteAssembler.toModel(clienteCadastrado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClienteModel> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteInput clienteInput) {

        catalogoClienteService.buscarClientePorId(id);

        Cliente cliente = clienteAssembler.toEntity(clienteInput);
        cliente.setId(id);
        cliente = catalogoClienteService.salvarCliente(cliente);

        return ResponseEntity.ok(clienteAssembler.toModel(cliente));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Cliente> excluirById(@PathVariable Long id) {

        catalogoClienteService.buscarClientePorId(id);

        catalogoClienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }


}
