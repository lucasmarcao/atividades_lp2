/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author Usuario
 */
public class Atleta {
    private String Nome;
    private String modalidade;
    private double altura;
    private double peso;   
    private String Sigla;
    private Date nascimento;
    private String foto;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
    
    public Atleta() {
    }
    
    public Atleta(String Nome, String modalidade, double altura, double peso, String Sigla, String foto, Date nascimento) {
        this.Nome = Nome;
        this.modalidade = modalidade;
        this.altura = altura;
        this.peso = peso;
        this.Sigla = Sigla;
        this.foto = foto;
        this.nascimento = nascimento;
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getSigla() {
        return Sigla;
    }

    public void setSigla(String Sigla) {
        this.Sigla = Sigla;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getFoto() {
       return foto;
    }
     
    public void setFoto(String foto) {
       this.foto = foto;
    }

    @Override
    public String toString() {
        return  Nome + ";" + modalidade + ";" + altura + ";" + peso + ";" + Sigla + ";" + foto+ ";" +simpleDateFormat.format(nascimento);
    }

    
    
}
