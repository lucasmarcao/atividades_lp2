/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Entidade.Entidade_Nacionalidade;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author Usuario
 */
public class Controle_nacionalidade {

private List<Entidade_Nacionalidade> lista = new ArrayList<>();
    
public void adicionar(Entidade_Nacionalidade entidade_Nacionalidade){
    lista.add(entidade_Nacionalidade);
}


public Entidade_Nacionalidade buscar (String Sigla){
    for (int i = 0; i < lista.size(); i++) {
        if (Sigla.equals(lista.get(i).getSigla())) {
            return lista.get(i);
        }
    }
    return null;
} 

public void alterar(Entidade_Nacionalidade entidade_Nacionalidade, Entidade_Nacionalidade antigo){
    lista.set(lista.indexOf(antigo),entidade_Nacionalidade);
}

public void excluir(Entidade_Nacionalidade entidade_Nacionalidade){
    lista.remove(entidade_Nacionalidade);
}

public List<Entidade_Nacionalidade> listar(){
    return lista;
}

public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Entidade_Nacionalidade entidade_Nacionalidade : lista) {
            listaDeString.add(entidade_Nacionalidade.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Trabalhador
        Entidade_Nacionalidade entidade_Nacionalidade;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            entidade_Nacionalidade = new Entidade_Nacionalidade(aux[0], aux[1]);
            lista.add(entidade_Nacionalidade);
        }
    }


}
