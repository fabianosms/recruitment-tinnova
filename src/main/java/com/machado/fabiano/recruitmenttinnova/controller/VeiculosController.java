package com.machado.fabiano.recruitmenttinnova.controller;

import com.machado.fabiano.recruitmenttinnova.dto.*;
import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

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

    @GetMapping("/marcas")
    public Map<String, Long> contarVeiculosPorMarca() {

        List<Marca> listaMarcas = marcaRepository.findAll();
        HashMap<String, Long> map = new HashMap<>();

        for (Marca marca : listaMarcas) {
            String marcaNome = marca.getNome();
            map.put(marcaNome, veiculoRepository.countByMarca(marca));
        }

        return map;
    }

    @PostMapping
    public ResponseEntity<VeiculoCadastroDto> cadastrarVeiculo(@RequestBody VeiculoCadastroForm form) {

        Marca marca = form.toMarca();

        if (marcaRepository.findByNome(marca.getNome()) == null) {
            marcaRepository.save(marca);
        }

        Veiculo veiculo = form.toVeiculo();
        veiculo.setMarca(marcaRepository.findByNome(marca.getNome()));
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

    @PatchMapping ("/{id}")
    @Transactional
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculoParte(@PathVariable Long id, @RequestBody VeiculoAtualizacaoForm form) {

        Veiculo veiculo = form.atualizar(id, veiculoRepository);

        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
    }

//    @PatchMapping("{id}")
////    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculoParte(@PathVariable Long id, @RequestBody VeiculoAtualizacaoParcialForm form) {
//
//        Veiculo veiculo = veiculoRepository.findById(id)
//                .orElseThrow(EntityNotFoundException::new);
//
//        Marca marca = form.toMarca();
//
//        if (marcaRepository.findByNome(marca.getNome()) == null) {
//            marcaRepository.save(marca);
//        }
//
//        Veiculo novosDados = form.toVeiculo();
//
//        marcaRepository.findByNome(form.getMarca());
//
//        if (novosDados.getVeiculo() != null) {
//            veiculo.setVeiculo(novosDados.getVeiculo());
//        }
//
//        if (novosDados.getMarca() != null) {
//            veiculo.setMarca(novosDados.getMarca());
//        }
//
//        if (novosDados.getAno() != null) {
//            veiculo.setAno(novosDados.getAno());
//        }
//
//        if (novosDados.getDescricao() != null) {
//            veiculo.setDescricao(novosDados.getDescricao());
//        }
//
//        if (novosDados.getVendido() != null) {
//            veiculo.setVendido(novosDados.getVendido());
//        }
//
//        veiculo.setUpdated(LocalDateTime.now());
//
//        veiculoRepository.save(veiculo);
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
