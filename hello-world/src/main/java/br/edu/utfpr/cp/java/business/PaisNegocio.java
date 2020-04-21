package br.edu.utfpr.cp.java.business;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.utfpr.cp.java.business.exceptions.PaisJaExisteException;
import br.edu.utfpr.cp.java.business.exceptions.PaisNaoCadastradoException;
import br.edu.utfpr.cp.java.entity.PaisModel;
import br.edu.utfpr.cp.java.repository.PaisRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaisNegocio {

    private final PaisRepository paisRepository;

    public List<PaisModel> listarTodos() {
        return this.paisRepository.findAll();
    }

    public void criar(PaisModel pais) throws PaisJaExisteException {

        if(this.paisRepository.findByNome(pais.getNome()).isPresent()) {
            throw new PaisJaExisteException();
        } else {
            this.paisRepository.save(pais);
        }
    }

    public PaisModel buscarPorId(Long id) throws PaisNaoCadastradoException {
        return this.paisRepository.findById(id).orElseThrow(() -> new PaisNaoCadastradoException());
    }

    public void apagar(Long id) {
        this.paisRepository.deleteById(id);
    }

    public void alterar(PaisModel paisNovo) throws PaisNaoCadastradoException {
        var pais = this.paisRepository.findById(paisNovo.getId());
        if(pais.isPresent()) {
            var paisModel = pais.get();
            paisModel.setNome(paisNovo.getNome());
            paisModel.setSigla(paisNovo.getSigla());

            this.paisRepository.saveAndFlush(paisModel);
        } else {
            throw new PaisNaoCadastradoException(); 
        }
    }
}