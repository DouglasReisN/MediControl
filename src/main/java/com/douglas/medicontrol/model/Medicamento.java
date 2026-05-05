package com.douglas.medicontrol.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_medicamentos")
public class Medicamento {

    public Medicamento(){}

    public Medicamento(Long id, String nome, String dosagem, BigDecimal preco, LocalDate dataValidade, Integer estoque, Categoria categoria, Fabricante fabricante) {
        this.id = id;
        this.nome = nome;
        this.dosagem = dosagem;
        this.preco = preco;
        this.dataValidade = dataValidade;
        this.estoque = estoque;
        this.categoria = categoria;
        this.fabricante = fabricante;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    private String dosagem;

    private BigDecimal preco;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    private Integer estoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

    public Long getId() {
        return id;
    }

    public void setPreco(BigDecimal preco){
        this.preco = preco;
    }
    public BigDecimal getPreco(){
        return preco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer quantidade) {
        this.estoque = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Medicamento that = (Medicamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
