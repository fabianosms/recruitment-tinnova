package com.machado.fabiano.recruitmenttinnova.repository;

import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Marca findByNome(String nome);

    Long countByNome(String nome);
}
