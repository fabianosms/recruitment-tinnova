package com.machado.fabiano.recruitmenttinnova.controller;

import com.machado.fabiano.recruitmenttinnova.dto.VeiculoAtualizacaoForm;
import com.machado.fabiano.recruitmenttinnova.dto.VeiculoCadastroDto;
import com.machado.fabiano.recruitmenttinnova.dto.VeiculoCadastroForm;
import com.machado.fabiano.recruitmenttinnova.dto.VeiculoCompletoDto;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listarVeiculos () {
        return veiculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public VeiculoCompletoDto buscarUmVeiculo(@PathVariable Long id) {

        Veiculo veiculo = veiculoRepository.getReferenceById(id);
        return new VeiculoCompletoDto(veiculo);
    }

    @GetMapping("/ultimasemana")
    public List<Veiculo> buscarVeiculosUltimaSemana() {
        return veiculoRepository.findByCreatedBetween(LocalDateTime.now().minusWeeks(1), LocalDateTime.now());
    }

    @GetMapping("/naovendidos")
    public Long contarVeiculosNaoVendidos() {
        return veiculoRepository.countByVendidoFalse();
    }

    @GetMapping("/decada/{decada}")
    public Long contarVeiculosPorDecada(Integer decada) {

        Integer inicio = decada;
        Integer fim = decada + 10;

        return veiculoRepository.countByAnoBetween(inicio, fim);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VeiculoCadastroDto> cadastrarVeiculo(@RequestBody VeiculoCadastroForm form) {
        Veiculo veiculo = form.toVeiculo();
        veiculoRepository.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new VeiculoCadastroDto(veiculo));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoAtualizacaoForm form) {

        Veiculo veiculo = form.atualizar(id, veiculoRepository);

        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoAtualizacaoForm form) {
//        Veiculo veiculo = form.toVeiculo();
//        veiculoRepository.save(veiculo);
//        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
//    }

//    @PatchMapping("{id}")
//    @Transactional
//    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculoParte(@PathVariable Long id, @RequestBody VeiculoAtualizacaoForm form) {
//
//        Veiculo veiculo = form.atualizar(id, veiculoRepository);
//
//        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
//    }


    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> removerVeiculo(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
        return ResponseEntity.ok().build();

        // verificar se o id existe?
    }
}
