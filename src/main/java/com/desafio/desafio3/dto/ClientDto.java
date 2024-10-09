package com.desafio.desafio3.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos")
    @CPF(message = "CPF Inválido.")
    private String cpf;

    @NotNull(message = "A renda é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "A renda deve ser maior que 0")
    @Positive
    private Double income;

    @NotNull(message = "A data de nascimento é obrigatória")
    @PastOrPresent(message = "A data de nascimento deve ser uma data no passado")
    private LocalDate birthDate;

    @NotNull(message = "O número de filhos é obrigatório")
    @Min(value = 0, message = "O número de filhos deve ser maior ou igual a 0")
    private Integer children;


}
