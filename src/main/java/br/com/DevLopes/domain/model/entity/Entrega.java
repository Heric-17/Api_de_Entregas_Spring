package br.com.DevLopes.domain.model.entity;

import br.com.DevLopes.domain.ValidationGroups;
import br.com.DevLopes.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.data.Offset;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias;

    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;

    public Ocorrencia registrarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();

        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);
        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if (naoPodeSerAlterada()) {
            throw new NegocioException("Entrega não pode ser finalizada");
        }

        this.setStatus(StatusEntrega.FINALIZADA);
        this.setDataFinalizacao(OffsetDateTime.now());
    }

    public void cancelar() {
        if (naoPodeSerAlterada()) {
            throw new NegocioException("Entrega não pode ser cancelada");
        }

        this.setStatus(StatusEntrega.CANCELADA);
        this.setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerAlterada() {
        return this.getStatus().equals(StatusEntrega.PENDENTE);
    }

    public boolean naoPodeSerAlterada() {
        return !this.podeSerAlterada();
    }
}
