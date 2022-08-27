package br.com.DevLopes.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClienteInput {

    @NotBlank
    @Size(max = 60, min = 3)
    private String nome;

    @Email
    @NotBlank
    @Size(max = 255, min = 6)
    private String email;

    @NotBlank
    @Size(max = 20, min = 9)
    private String telefone;

}
