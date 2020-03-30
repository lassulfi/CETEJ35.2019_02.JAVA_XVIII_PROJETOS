package br.edu.utfpr.cp.java.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaisController {

    private List<PaisModel> paises;

    public PaisController() {
        paises = Stream.of(
            PaisModel.builder().id(1L).nome("Brasil").sigla("BR").build(),
            PaisModel.builder().id(2L).nome("Estados Unidos").sigla("EUA").build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/list")
    public String listarPaises(Model memoria) {
        memoria.addAttribute("listaPaises", this.paises);

        return "pais-page";
    }
}