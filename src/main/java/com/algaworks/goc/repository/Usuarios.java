package com.algaworks.goc.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.goc.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {
	
}