package com.douglas.medicontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MedicamentoResponseDTO(
        Long id,
        String nome,
        String dosagem,
        Integer quantidade,
        BigDecimal preco,
        LocalDate dataValidade,
        String nomeCategoria,
        String nomeFabricante
) {
}
