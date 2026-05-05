package com.douglas.medicontrol.controller;


import com.douglas.medicontrol.dto.FabricanteRequestDTO;
import com.douglas.medicontrol.dto.FabricanteResponseDTO;
import com.douglas.medicontrol.model.Fabricante;
import com.douglas.medicontrol.repository.FabricanteRepository;
import com.douglas.medicontrol.service.FabricanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fabricantes")
public class FabricanteController {

    private final FabricanteService service;
    private final FabricanteRepository fabricanteRepository;

    public FabricanteController(FabricanteService service,
                                FabricanteRepository fabricanteRepository){
        this.service = service;
        this.fabricanteRepository = fabricanteRepository;
    }

    @GetMapping
    public List<FabricanteResponseDTO> listarTodos(){
        return service.listarTodos().stream()
                .map(f -> new FabricanteResponseDTO(f.getId(), f.getNome(),f.getCnpj())).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<FabricanteResponseDTO> salvar(@RequestBody FabricanteRequestDTO dto){
        Fabricante salvo = service.salvar(dto);
        FabricanteResponseDTO response = new FabricanteResponseDTO(salvo.getId(), salvo.getNome(), salvo.getCnpj());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteResponseDTO> buscarPorId(@PathVariable Long id){
        Fabricante obj = service.buscarPorIdFabricante(id);
        return ResponseEntity.ok().body(new FabricanteResponseDTO(obj.getId(), obj.getNome(), obj.getCnpj()));
    }


}

