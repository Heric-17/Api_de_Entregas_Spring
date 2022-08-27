package br.com.DevLopes.domain.service;

import br.com.DevLopes.domain.model.entity.Cliente;
import br.com.DevLopes.domain.exception.NegocioException;
import br.com.DevLopes.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogoClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public List<Cliente> buscarClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Transactional
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {

        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Não podem haver E-mails repetidos");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }


}
