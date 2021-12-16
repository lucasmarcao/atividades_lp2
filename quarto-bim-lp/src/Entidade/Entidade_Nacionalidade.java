/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import java.util.Date;



/**
 *
 * @author Usuario
 */
public class Entidade_Nacionalidade {
    private String Sigla;
    private String paizes;

    public Entidade_Nacionalidade() {
    }

    public Entidade_Nacionalidade(String Sigla, String paizes) {
        this.Sigla = Sigla;
        this.paizes = paizes;
    }

    public String getSigla() {
        return Sigla;
    }

    public void setSigla(String Sigla) {
        this.Sigla = Sigla;
    }

    public String getPaizes() {
        return paizes;
    }

    public void setPaizes(String paizes) {
        this.paizes = paizes;
    }

    @Override
    public String toString() {
        return Sigla + ";" + paizes;
    }
    
    
    
    
    
}
