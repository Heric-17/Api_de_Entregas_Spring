package br.com.DevLopes.api.assembler;

import br.com.DevLopes.api.model.entity.ClienteModel;
import br.com.DevLopes.api.model.input.ClienteInput;
import br.com.DevLopes.domain.model.entity.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteAssembler {

    private ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    public List<ClienteModel> toListModel(List<Cliente> clientes) {
        return clientes.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }

}
