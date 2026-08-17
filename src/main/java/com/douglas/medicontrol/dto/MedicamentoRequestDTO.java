package com.douglas.medicontrol.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MedicamentoRequestDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "A dosagem é obrigatória")
        String dosagem,

        @NotNull(message = "O estoque não pode ser nulo")
        @PositiveOrZero(message = "O estoque não pode ser negativo")
        Integer estoque,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "A data de validade é obrigatória")
        @FutureOrPresent(message = "Não é permitido cadastrar medicamentos vencidos")
        LocalDate dataValidade,

        @NotNull(message = "O ID da categoria é obrigatório")
        @Positive
        Long categoriaId,

        @NotNull(message = "O ID do fabricante é obrigatório")
        @Positive
        Long fabricanteId
) {}

