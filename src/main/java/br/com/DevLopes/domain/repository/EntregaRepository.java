package br.com.DevLopes.domain.repository;

import br.com.DevLopes.domain.model.entity.Entrega;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends CrudRepository<Entrega, Long> {


}
