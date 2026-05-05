package com.douglas.medicontrol.controller;


import com.douglas.medicontrol.dto.MedicamentoRequestDTO;
import com.douglas.medicontrol.dto.MedicamentoResponseDTO;
import com.douglas.medicontrol.model.Medicamento;
import com.douglas.medicontrol.repository.MedicamentoRepository;
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
    private final MedicamentoRepository repository;

    public MedicamentoController(MedicamentoService service, MedicamentoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<MedicamentoResponseDTO> listarTodos() {
        //busca as entidades e transforma em DTOs de resposta
        return service.listarTodos().stream()
                .map(m -> new MedicamentoResponseDTO(
                        m.getId(),
                        m.getNome(),
                        m.getDosagem(),
                        m.getEstoque(),
                        m.getPreco(),
                        m.getDataValidade(),
                        m.getCategoria().getNome(),
                        m.getFabricante().getNome())).collect(Collectors.toList());

    }

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> salvar(@RequestBody MedicamentoRequestDTO dto) {
        // Aqui a Service recebe o DTO , valida e salva a entidade/entity
        Medicamento salvo = service.salvar(dto);

        // Transforma a Entity salva de volta em um DTO para o usuário ver o resultado
        MedicamentoResponseDTO response = new MedicamentoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDosagem(),
                salvo.getEstoque(),
                salvo.getPreco(),
                salvo.getDataValidade(),
                salvo.getCategoria().getNome(),
                salvo.getFabricante().getNome()
        );
        //Retorno status 201(CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        Medicamento obj = service.buscarPorIdMedicamento(id);
        return ResponseEntity.ok().body(new MedicamentoResponseDTO(obj.getId(), obj.getNome(), obj.getDosagem(), obj.getEstoque(),
                obj.getPreco(), obj.getDataValidade(), obj.getCategoria().getNome(), obj.getFabricante().getNome()));
    }

}