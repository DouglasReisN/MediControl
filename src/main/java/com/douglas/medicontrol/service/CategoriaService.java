package com.douglas.medicontrol.service;


import com.douglas.medicontrol.dto.CategoriaRequestDTO;
import com.douglas.medicontrol.model.Categoria;
import com.douglas.medicontrol.repository.CategoriaRepository;
import com.douglas.medicontrol.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository){
        this.repository = repository;
    }

    public List<Categoria> listarTodas(){
        return repository.findAll();
    }

    public Categoria buscarPorIdCategoria(Long id){
        Optional<Categoria> obj = repository.findById(id);
        //Se o objeto nao existir ,dispara a exceção!
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));

    }

    @Transactional
    public Categoria salvar(CategoriaRequestDTO dto) {

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new RuntimeException("O nome da categoria não pode estar vazio");
        }

        //Validação Evitar categorias duplicadas (Stream API)
        boolean nomeJaExiste = repository.findAll().stream()
                .anyMatch(c -> c.getNome().equalsIgnoreCase(dto.nome()));

        if (nomeJaExiste) {
            throw new RuntimeException("Erro: Já existe uma categoria cadastrada com o nome: " + dto.nome());
        }

        // Mapeamento: Transformando o Record (DTO) em Entity (Model)
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());

        return repository.save(categoria);
    }

    @Transactional
    public void deletarPorId(Long id){
        repository.deleteById(id);
    }
}
