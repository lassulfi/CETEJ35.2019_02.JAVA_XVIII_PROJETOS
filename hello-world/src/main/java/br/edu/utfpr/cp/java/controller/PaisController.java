package br.edu.utfpr.cp.java.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.java.Log;

@Controller
@Log
public class PaisController {

    private List<PaisModel> paises;

    public PaisController() {
        paises = Stream.of(
            PaisModel.builder().id(1L).nome("Brasil").sigla("BR").build(),
            PaisModel.builder().id(2L).nome("Estados Unidos").sigla("EUA").build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/paises")
    public String listarPaises(Model memoria) {
        this.adicionaListaDePaisesNaMemoria(memoria);

        return "pais-page";
    }

    @GetMapping("/paises/apagar")
    public String apagar(@RequestParam Long id) {
        paises.removeIf(paisAtual -> paisAtual.getId().equals(id));

        return "redirect:/paises";
    }

    @PostMapping("/paises/criar")
    public String criar(@Valid PaisModel pais, BindingResult result, Model memoria) {
        if(result.hasErrors()) {
            result.getFieldErrors()
                .forEach(err -> {
                    log.info(err.getField() + " - " +  
                        err.getDefaultMessage()
                    );
                    memoria.addAttribute(err.getField(), err.getDefaultMessage());
                });
            
            this.adicionaListaDePaisesNaMemoria(memoria);
            this.adicionaPaisNaMemoria(memoria, pais);

            return "pais-page";
        }
        
        Long id = Long.valueOf(paises.size() + 1);
        pais.setId(id);
        paises.add(pais);

        return "redirect:/paises";
    }

    @GetMapping("/paises/preparaAlterar")
    public String preparaAlterar(@RequestParam Long id, Model memoria) {
        var pais = paises.stream()
            .filter(paisAtual -> paisAtual.getId().equals(id))
            .findAny()
            .get();
        
        this.adicionaListaDePaisesNaMemoria(memoria);
        this.adicionaPaisNaMemoria(memoria, pais);
        memoria.addAttribute("alterar", true);

        return "pais-page";
    }

    @PostMapping("/paises/alterar")
    public String alterar(PaisModel paisNovo) {
        var pais = paises.stream().filter(paisAtual -> paisAtual.getId().equals(paisNovo.getId())).findAny().get();
    
        pais.setNome(paisNovo.getNome());
        pais.setSigla(paisNovo.getSigla());

        return "redirect:/paises";
    }

    private void adicionaListaDePaisesNaMemoria(Model memoria) {
        memoria.addAttribute("listaPaises", this.paises);
    }

    private void adicionaPaisNaMemoria(Model memoria, PaisModel pais) {
        memoria.addAttribute("paisAtual", pais);
    }
}