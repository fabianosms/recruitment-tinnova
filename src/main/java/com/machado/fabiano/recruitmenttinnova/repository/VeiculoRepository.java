package com.machado.fabiano.recruitmenttinnova.repository;

import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByCreatedBetween(LocalDateTime primeiraData, LocalDateTime segundaData);

    Long countByVendidoFalse();

    Long countByAnoBetween(Integer primeiraData, Integer segundaData);
}
