package br.com.DevLopes.domain.repository;

import br.com.DevLopes.domain.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    //Pesquisa exata
    public List<Cliente> findByNome(String nome);

    //Pesquisa o que contem (like)
    public List<Cliente> findByNomeContaining(String nome);

    //Pesquisa exata
    public Optional<Cliente> findByEmail(String email);
}
