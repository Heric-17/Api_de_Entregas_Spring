package br.com.DevLopes.domain.service;

import br.com.DevLopes.domain.exception.EntidadeNaoEncontradaException;
import br.com.DevLopes.domain.model.entity.Cliente;
import br.com.DevLopes.domain.model.entity.Entrega;
import br.com.DevLopes.domain.model.entity.StatusEntrega;
import br.com.DevLopes.domain.exception.NegocioException;
import br.com.DevLopes.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CatalogoEntregraService {

    private EntregaRepository entregaRepository;
    private CatalogoClienteService catalogoClienteService;


    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscarClientePorId(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }

    @Transactional
    public List<Entrega> buscarEntregas() {
        return (List<Entrega>) entregaRepository.findAll();
    }

    @Transactional
    public Entrega buscarEntregaPorId(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

    @Transactional
    public Entrega finalizar(Long entregaId) {
        Entrega entrega = buscarEntregaPorId(entregaId);

        entrega.finalizar();

        return entregaRepository.save(entrega);
    }
    @Transactional
    public Entrega cancelar(Long entregaId) {
        Entrega entrega = buscarEntregaPorId(entregaId);

        entrega.cancelar();

        return entregaRepository.save(entrega);
    }




}
