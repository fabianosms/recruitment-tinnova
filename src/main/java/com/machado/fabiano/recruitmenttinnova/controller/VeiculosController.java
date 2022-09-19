package com.machado.fabiano.recruitmenttinnova.controller;

import com.machado.fabiano.recruitmenttinnova.dto.*;
import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;
import com.machado.fabiano.recruitmenttinnova.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> listarVeiculos () {
        return veiculoService.listarVeiculos();
    }

    @GetMapping("/{id}")
    public VeiculoCompletoDto buscarUmVeiculo(@PathVariable Long id) {
        return veiculoService.buscarUmVeiculo(id);
    }

    @GetMapping("/ultimasemana")
    public List<Veiculo> buscarVeiculosUltimaSemana() {
        return veiculoService.buscarVeiculosUltimaSemana();
    }

    @GetMapping("/naovendidos")
    public Long contarVeiculosNaoVendidos() {
        return veiculoService.contarVeiculosNaoVendidos();
    }

    @GetMapping("/decada/{decada}")
    public Long contarVeiculosPorDecada(@PathVariable Integer decada) {
        return veiculoService.contarVeiculosPorDecada(decada);
    }

    @GetMapping("/marcas")
    public Map<String, Long> contarVeiculosPorMarca() {
        return veiculoService.contarVeiculosPorMarca();
    }

    @PostMapping
    public ResponseEntity<VeiculoCadastroDto> cadastrarVeiculo(@RequestBody VeiculoCadastroForm form) {
        return veiculoService.cadastrarVeiculo(form);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoAtualizacaoForm form) {
        return veiculoService.atualizarVeiculo(id, form);
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculoParte(@PathVariable Long id, @RequestBody VeiculoAtualizacaoParcialForm form) {
        return veiculoService.atualizarVeiculoParte(id, form);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removerVeiculo(@PathVariable Long id) {
        return veiculoService.removerVeiculo(id);
    }
}
