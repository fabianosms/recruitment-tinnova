package com.machado.fabiano.recruitmenttinnova.dto;

import com.machado.fabiano.recruitmenttinnova.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MarcaDto {

    @Autowired
    private MarcaRepository marcaRepository;

    private String nome;

    private Long contagem;

}
