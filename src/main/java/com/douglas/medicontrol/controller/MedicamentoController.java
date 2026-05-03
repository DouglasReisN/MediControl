package com.douglas.medicontrol.controller;


import com.douglas.medicontrol.dto.MedicamentoRequestDTO;
import com.douglas.medicontrol.dto.MedicamentoResponseDTO;
import com.douglas.medicontrol.model.Medicamento;
import com.douglas.medicontrol.service.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private final MedicamentoService service;

    public MedicamentoController(MedicamentoService service){
        this.service = service;
    }
    @GetMapping
    public List<MedicamentoResponseDTO> listarTodos(){
        //busca as entidades e transforma em DTOs de resposta
        return service.listarTodos().stream()
                .map(m -> new MedicamentoResponseDTO(
                 m.getId(),
                 m.getNome(),
                 m.getDosagem(),
                 m.getQuantidade(),
                 m.getPreco(),
                 m.getDataValidade(),
                 m.getCategoria().getNome(),
                 m.getFabricante().getNome())).collect(Collectors.toList());

    }

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> salvar(@RequestBody MedicamentoRequestDTO dto){
        // Aqui a Service recebe o DTO , valida e salva a entidade/entity
        Medicamento salvo = service.salvar(dto);

        // Transformamos a Entity salva de volta em um DTO para o usuário ver o resultado
        MedicamentoResponseDTO response = new MedicamentoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDosagem(),
                salvo.getQuantidade(),
                salvo.getPreco(),
                salvo.getDataValidade(),
                salvo.getCategoria().getNome(),
                salvo.getFabricante().getNome()
        );
        //Retorno status 201(CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
