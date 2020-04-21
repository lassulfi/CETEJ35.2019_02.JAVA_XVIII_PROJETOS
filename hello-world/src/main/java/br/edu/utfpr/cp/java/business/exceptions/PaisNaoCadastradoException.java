package br.edu.utfpr.cp.java.business.exceptions;

public class PaisNaoCadastradoException extends Exception {
    private static final long serialVersionUID = 1L;

    public PaisNaoCadastradoException() {
        super("País não cadastrado.");
    }
}