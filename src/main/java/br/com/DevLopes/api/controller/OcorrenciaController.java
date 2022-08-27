package br.com.DevLopes.api.controller;

import br.com.DevLopes.api.assembler.OcorrenciaAssembler;
import br.com.DevLopes.api.model.entity.OcorrenciaModel;
import br.com.DevLopes.api.model.input.OcorrenciaInput;
import br.com.DevLopes.domain.model.entity.Entrega;
import br.com.DevLopes.domain.model.entity.Ocorrencia;
import br.com.DevLopes.domain.service.CatalogoEntregraService;
import br.com.DevLopes.domain.service.CatalogoOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private CatalogoOcorrenciaService catalogoOcorrenciaService;
    private CatalogoEntregraService catalogoEntregraService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId, @RequestBody @Valid OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrenciaRegistrada = catalogoOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
        Entrega entrega = catalogoEntregraService.buscarEntregaPorId(entregaId);

        return ocorrenciaAssembler.toListModel(entrega.getOcorrencias());
    }

}
