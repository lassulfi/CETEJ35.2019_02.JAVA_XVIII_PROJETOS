package br.edu.utfpr.cp.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.cp.java.entity.PaisModel;

public interface PaisRepository extends JpaRepository<PaisModel, Long> {

    public Optional<PaisModel> findByNome(String nome);
}