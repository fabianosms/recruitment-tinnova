package com.machado.fabiano.recruitmenttinnova.service;

import com.machado.fabiano.recruitmenttinnova.dto.*;
import com.machado.fabiano.recruitmenttinnova.dto.form.VeiculoAtualizacaoForm;
import com.machado.fabiano.recruitmenttinnova.dto.form.VeiculoAtualizacaoParcialForm;
import com.machado.fabiano.recruitmenttinnova.dto.form.VeiculoCadastroForm;
import com.machado.fabiano.recruitmenttinnova.model.Marca;
import com.machado.fabiano.recruitmenttinnova.model.Veiculo;
import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;
import com.machado.fabiano.recruitmenttinnova.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    /**
     * Lista todos os veículos ou retorna conforme os parâmetros inseridos na url
     * @param marca nome da marca do veículo a ser listado
     * @param ano ano do veículo a ser listado
     * @return Lista de veículos conforme os critérios selecionados
     */
    public List<Veiculo> listarVeiculos (String marca, Integer ano) {
        if (marca == null && ano == null) {
            return veiculoRepository.findAll();
        }

        if (marca == null) {
            return veiculoRepository.findByAno(ano);
        }

        if (ano == null) {
            return veiculoRepository.findByMarcaNome(marca);
        }

        return veiculoRepository.findByMarcaNomeAndAno(marca, ano);
    }

    /**
     * Gera a descrição de um veículo conforme o id
     * @param id id do veículo, conforme o banco de dados
     * @return Informação detalhada do veículo selecionado
     */
    public VeiculoCompletoDto buscarUmVeiculo(Long id) {

        Veiculo veiculo = veiculoRepository.getReferenceById(id);

        return new VeiculoCompletoDto(veiculo);
    }

    /**
     * Lista todos os veículos criados na última semana
     * @return Lista dos veículos
     */
    public List<Veiculo> buscarVeiculosUltimaSemana() {
        return veiculoRepository.findByCreatedBetween(LocalDateTime.now().minusWeeks(1), LocalDateTime.now());
    }

    /**
     * Realiza a contagem dos veículos não vendidos
     * @return Valor da soma dos veículos
     */
    public Long contarVeiculosNaoVendidos() {
        return veiculoRepository.countByVendidoFalse();
    }

    /**
     * Exibe a distribuição dos veículos conforme a década de fabricação
     * @return Lista com a década de fabricação, seguida da correspondente contagem de veículos
     */
    public Map<String, String> contarVeiculosPorDecada() {

        List<Veiculo> listaTodos = veiculoRepository.findAll();
        List<Integer> listaDecadas = new ArrayList<>();
        int tamanho = listaTodos.size();
        Integer decada = 0;

        for (Veiculo veiculo : listaTodos) {
            Integer ano = veiculo.getAno();
            char[] charsDecada = ano.toString().toCharArray();
            int digitoDecada = Character.getNumericValue(charsDecada[2]);

            if (ano < 1999) {
                decada = Integer.parseInt("19" + digitoDecada + "0");
                listaDecadas.add(decada);
            } else {
                decada = Integer.parseInt("20" + digitoDecada + "0");
                listaDecadas.add(decada);
            }
        }

        Set<Integer> decadasUnicasUnsorted = new HashSet<Integer>(listaDecadas);

        List<Integer> decadasUnicas = new ArrayList<>(decadasUnicasUnsorted);

        decadasUnicas.sort(Comparator.naturalOrder());

        TreeMap<String, String> map = new TreeMap<>();

        for (Integer valor : decadasUnicas) {
            Integer fim = valor + 9;
            Long contagem = veiculoRepository.countByAnoBetween(decada, fim);
            String decadaDe = "Década de " + valor + ":";
            String veiculos = contagem + " veículos";
            map.put(decadaDe, veiculos);
        }

        return map;
    }

    /**
     * Exibe a distribuição dos veículos conforme os fabricantes
     * @return Lista com o nome de cada marca, seguida da correspondente contagem de veículos
     */
    public Map<String, Long> contarVeiculosPorMarca() {

        List<Marca> listaMarcas = marcaRepository.findAll();
        HashMap<String, Long> map = new HashMap<>();

        for (Marca marca : listaMarcas) {
            String marcaNome = marca.getNome();
            if (veiculoRepository.countByMarca(marca) != 0) {
                map.put(marcaNome, veiculoRepository.countByMarca(marca));
            }
        }

        return map;
    }

    /**
     * Cadastra um veículo no banco de dados
     * @param form formulário em JSON com os dados a ser cadastrados
     * @return Informação detalhada do veículo cadastrado
     */
    @Transactional
    public ResponseEntity<VeiculoCadastroDto> cadastrarVeiculo(VeiculoCadastroForm form) {

        Veiculo veiculo = form.toVeiculo(marcaRepository);
        veiculoRepository.save(veiculo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new VeiculoCadastroDto(veiculo));
    }

    /**
     * Atualiza os dados de um veículo cadastrado
     * @param id id do veículo a ser atualizado
     * @param form formulário em JSON com todos os dados do veículo
     * @return Informação detalhada do veículo atualizado
     */
    @Transactional
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculo(Long id, VeiculoAtualizacaoForm form) {

        Veiculo veiculo = form.atualizar(id, veiculoRepository, marcaRepository);

        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
    }

    /**
     * Atualiza apenas alguns dados de um veículo cadastrado
     * @param id id do veículo a ser atualizado
     * @param form formulário em JSON, somente com os dados a ser atualizados
     * @return Informação detalhada do veículo atualizado
     */
    @Transactional
    public ResponseEntity<VeiculoCompletoDto> atualizarVeiculoParte(Long id, VeiculoAtualizacaoParcialForm form) {

        Veiculo veiculo = form.atualizar(id, veiculoRepository, marcaRepository);

        return ResponseEntity.ok(new VeiculoCompletoDto(veiculo));
    }

    /**
     * Remove um veículo do banco de dados
     * @param id id do veículo a ser removido
     * @return Mensagem de confirmação da exclusão
     */
    @Transactional
    public ResponseEntity<String> removerVeiculo(Long id) {
        veiculoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Veículo removido com sucesso");
    }
}