package com.algaworks.goc.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.goc.model.Carta;

public interface Cartas extends JpaRepository<Carta, Long> {
	
}
