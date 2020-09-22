package br.com.web.model;

import javax.persistence.Entity;

@Entity
public class Aluno extends AbstractEntity {

    private String nome;
    private String curso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
