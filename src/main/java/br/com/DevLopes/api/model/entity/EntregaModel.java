package br.com.DevLopes.api.model.entity;

import br.com.DevLopes.domain.model.entity.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaModel {

    private Long id;
    private ClienteResumoModel cliente;
    private StatusEntrega statusEntrega;
    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
    private DestinatarioModel destinatario;

}
