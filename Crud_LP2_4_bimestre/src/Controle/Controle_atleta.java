/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Entidade.Atleta;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

/**
 *
 * @author Usuario
 */
public class Controle_atleta {

private List<Atleta> lista = new ArrayList<>();

public Controle_atleta() {
    }

    public void limparLista() {
        lista.clear();//zera a lista
    }
    
public void adicionar(Atleta atleta){
    lista.add(atleta);
}
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    

public Atleta buscar (String Nome){
    for (int i = 0; i < lista.size(); i++) {
        if (Nome.equals(lista.get(i).getNome())) {
            return lista.get(i);
        }
    }
    return null;
} 

public void alterar(Atleta atleta, Atleta antigo){
    lista.set(lista.indexOf(antigo),atleta);
}

public void excluir(Atleta atleta){
    lista.remove(atleta);
}

public List<Atleta> listar(){
    return lista;
}

public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Atleta atleta : lista) {
            listaDeString.add(atleta.toString());
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
        Atleta atleta;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            try {
                atleta = new Atleta(aux[0], aux[1], Double.valueOf(aux[2]), Double.valueOf(aux[3]), aux[4], aux[5], simpleDateFormat.parse(aux[6]));
                lista.add(atleta);
            } catch (Exception e) {
                System.out.println("A lista do atleta não foi salva");
            }
            
        }
    }


}
