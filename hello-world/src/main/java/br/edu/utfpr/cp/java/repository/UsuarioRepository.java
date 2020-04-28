package br.edu.utfpr.cp.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.cp.java.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNome(String nome);
}