package com.douglas.medicontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MedicamentoRequestDTO(

    String nome,
    String dosagem,
    Integer quantidade,
    BigDecimal preco,
    LocalDate dataValidade,
    Long categoriaId,
    Long fabricanteId
        ){
}
