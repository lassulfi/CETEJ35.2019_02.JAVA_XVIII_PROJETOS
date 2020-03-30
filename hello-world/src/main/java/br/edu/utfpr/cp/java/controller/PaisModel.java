package br.edu.utfpr.cp.java.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PaisModel {

    private Long id;
    private String nome;
    private String sigla;
}