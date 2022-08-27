package br.com.DevLopes.domain.service;

import br.com.DevLopes.domain.model.entity.Entrega;
import br.com.DevLopes.domain.model.entity.Ocorrencia;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoOcorrenciaService {

    private CatalogoEntregraService catalogoEntregraServic;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {

        Entrega entrega = catalogoEntregraServic.buscarEntregaPorId(entregaId);

        return entrega.registrarOcorrencia(descricao);
    }

}
