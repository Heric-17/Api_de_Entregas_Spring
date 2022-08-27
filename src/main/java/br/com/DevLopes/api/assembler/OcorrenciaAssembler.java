package br.com.DevLopes.api.assembler;

import br.com.DevLopes.api.model.entity.OcorrenciaModel;
import br.com.DevLopes.domain.model.entity.Entrega;
import br.com.DevLopes.domain.model.entity.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {
    private ModelMapper modelMapper;

    public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaModel.class);
    }

    public List<OcorrenciaModel> toListModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }


}
