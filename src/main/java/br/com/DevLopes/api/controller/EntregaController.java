package br.com.DevLopes.api.controller;

import br.com.DevLopes.api.assembler.EntregaAssembler;
import br.com.DevLopes.api.model.entity.EntregaModel;
import br.com.DevLopes.api.model.input.EntregaInput;
import br.com.DevLopes.domain.model.entity.Entrega;
import br.com.DevLopes.domain.service.CatalogoEntregraService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/entregas")
public class EntregaController {

    private CatalogoEntregraService catalogoEntregraService;
    private EntregaAssembler entregaAssembler;


    @GetMapping
    public List<EntregaModel> consultarEntregas() {
        return entregaAssembler.toListModel(catalogoEntregraService.buscarEntregas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> consultarEntregaPorId(@PathVariable Long id) {
        Entrega entrega = catalogoEntregraService.buscarEntregaPorId(id);
        return ResponseEntity.ok(entregaAssembler.toModel(entrega));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitarEntrega(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega entrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = catalogoEntregraService.solicitar(entrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @PutMapping(path = "/{id}/finalizar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id) {
        catalogoEntregraService.finalizar(id);
    }

}
