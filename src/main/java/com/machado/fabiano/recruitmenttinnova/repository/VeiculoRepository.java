package com.machado.fabiano.recruitmenttinnova.repository;

import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByCreatedBetween(LocalDateTime primeiraData, LocalDateTime segundaData);

    Optional<Veiculo> findById(Long id);

    List<Veiculo> findByAno(Integer ano);

    List<Veiculo> findByMarcaNome(String marca);

    List<Veiculo> findByMarcaNomeAndAno(String marca, Integer ano);

    Long countByVendidoFalse();

    Long countByAnoBetween(Integer primeiraData, Integer segundaData);

    Long countByMarca(Marca marca);
}
