package com.douglas.medicontrol.controller;


import com.douglas.medicontrol.dto.CategoriaRequestDTO;
import com.douglas.medicontrol.dto.CategoriaResponseDTO;
import com.douglas.medicontrol.model.Categoria;
import com.douglas.medicontrol.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service;
    }

    @GetMapping
    public List<CategoriaResponseDTO> listarTodas(){
        return service.listarTodas().stream()
                .map(c -> new CategoriaResponseDTO(c.getId(),c.getNome())).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@RequestBody CategoriaRequestDTO dto){
       Categoria salva = service.salvar(dto);
       CategoriaResponseDTO response = new CategoriaResponseDTO(salva.getId(),salva.getNome());
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ativa o tratamento de erro
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        Categoria obj = service.buscarPorIdCategoria(id);
        return ResponseEntity.ok().body(new CategoriaResponseDTO(obj.getId(), obj.getNome()));
    }
}
