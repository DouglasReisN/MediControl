package com.douglas.medicontrol.service;


import com.douglas.medicontrol.dto.MedicamentoRequestDTO;
import com.douglas.medicontrol.model.Categoria;
import com.douglas.medicontrol.model.Fabricante;
import com.douglas.medicontrol.model.Medicamento;
import com.douglas.medicontrol.repository.MedicamentoRepository;
import com.douglas.medicontrol.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepository repository;
    private final CategoriaService categoriaService;
    private final FabricanteService fabricanteService;

    public MedicamentoService(MedicamentoRepository repository, CategoriaService categoriaService, FabricanteService fabricanteService) {
        this.repository = repository;
        this.categoriaService = categoriaService;
        this.fabricanteService = fabricanteService;
    }

    public List<Medicamento> listarTodos() {
        return repository.findAll();
    }

    public Optional<Medicamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Medicamento salvar(MedicamentoRequestDTO dto) {
        boolean nomeJaExiste = repository.findAll().stream().anyMatch(m -> m.getNome().equalsIgnoreCase(dto.nome()));

        if (nomeJaExiste) {
            throw new RuntimeException("Erro: Já existe um medicamento cadastrado com o nome:" + dto.nome());
        }
        Categoria categoria = categoriaService.buscarPorIdCategoria(dto.categoriaId());

        Fabricante fabricante = fabricanteService.buscarPorIdFabricante(dto.fabricanteId());


        Medicamento medicamento = new Medicamento();
        medicamento.setNome(dto.nome());
        medicamento.setDosagem(dto.dosagem());
        medicamento.setPreco(dto.preco());
        medicamento.setEstoque(dto.estoque());
        medicamento.setDataValidade(dto.dataValidade());

        medicamento.setCategoria(categoria);
        medicamento.setFabricante(fabricante);

        return repository.save(medicamento);

    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Medicamento buscarPorIdMedicamento(Long id) {
        Optional<Medicamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}