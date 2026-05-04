package com.douglas.medicontrol.service;


import com.douglas.medicontrol.dto.FabricanteRequestDTO;
import com.douglas.medicontrol.model.Fabricante;
import com.douglas.medicontrol.repository.FabricanteRepository;
import com.douglas.medicontrol.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    private final FabricanteRepository repository;

    public FabricanteService(FabricanteRepository repository){
        this.repository = repository;
    }

    public List<Fabricante> listarTodos(){
        return repository.findAll();
    }

    @Transactional
    public Fabricante salvar(FabricanteRequestDTO dto){
       if (dto.nome() == null || dto.nome().trim().isBlank()){
           throw new RuntimeException("O nome do fabricante não pode estar vazio");
       }
       if (dto.cnpj() == null || dto.cnpj().isBlank()){
           throw new RuntimeException("O CNPJ do fabricante não pode estar vazio");
       }

       boolean nomeJaExiste = repository.findAll().stream().anyMatch(f -> f.getNome().equalsIgnoreCase(dto.nome()));

       if (nomeJaExiste){
           throw new RuntimeException("Já existe um fabricante cadastrado com este nome!");
       }

       //validacao para evitar CNPJs duplicados
        boolean cnpjJaExiste = repository.findAll().stream().anyMatch(f -> f.getCnpj().equals(dto.cnpj()));

       if (cnpjJaExiste){
           throw new RuntimeException("Erro: Já existe um fabricante cadastrado com este CNPJ" +dto.cnpj());
       }

       //Mapeamento : DTO,entidade/entity
         Fabricante fabricante = new Fabricante();
         fabricante.setNome(dto.nome());
         fabricante.setCnpj(dto.cnpj());

        return repository.save(fabricante);
    }

    public Fabricante buscarPorIdFabricante(Long id){
        Optional<Fabricante> obj = repository.findById(id); //Se o objeto nao existir ,dispara a exceção!
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void deletar(Long id){
        repository.deleteById(id);
    }
}
