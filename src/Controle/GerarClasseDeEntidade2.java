/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;
/**
 *
 * @author Usuario
 */
public class GerarClasseDeEntidade2 {
    
    public GerarClasseDeEntidade2(String nomeDaClasse2, List<String> atributo2,String caminho){
        StringTools st2 = new StringTools();
        List<String> cg = new ArrayList();//código gerado
        cg.add("package Entidades;");
        cg.add("/**\n"
                + " *\n"
                + " * @author LucasMarcão\n"
                + " */");
        cg.add("public class " + nomeDaClasse2 + " {\n");
        String[] aux;
        for (int i = 0; i < atributo2.size(); i++) {
            aux = atributo2.get(i).split(";");
            cg.add("private " + aux[0] + " " + aux[1] + ";");
        }
        cg.add("public " + nomeDaClasse2 + "() {\n"
                + "    }");

        String s = "";
        for (int i = 0; i < atributo2.size(); i++) {
            aux = atributo2.get(i).split(";");
            s = s + aux[0] + " " + aux[1] + ",";
        }
        s = s.substring(0, s.length() - 1);
        cg.add("public " + nomeDaClasse2 + "(" + s + ") {\n");
        for (int i = 0; i < atributo2.size(); i++) {
            aux = atributo2.get(i).split(";");
            cg.add("this." + aux[1] + "=" + aux[1] + ";\n");
        }
        cg.add("}");

        cg.add("\n\n //gets e sets\n");

        for (int i = 0; i < atributo2.size(); i++) {
            aux = atributo2.get(i).split(";");
            cg.add("public " + aux[0] + " get" + st2.plMaiusc(aux[1]) + "() {\n"
                    + "        return " + aux[1] + ";\n"
                    + "    }\n\n");
        }
        
        for (int i = 0; i < atributo2.size(); i++) {
            aux = atributo2.get(i).split(";");
            cg.add("public void" + " set" + st2.plMaiusc(aux[1])
                    + "(" + aux[0] + " " + aux[1] + ") {\n"
                    + "this." + aux[1] + "=" + aux[1] + ";\n"
                    + "    }\n\n");
        }

        cg.add(" @Override\n"
                + "    public String toString() {\n return");

        s = "";
        for (int i = 0; i < atributo2.size(); i++) {
            aux = atributo2.get(i).split(";");
            s = s + " " + aux[1] + "+ \";\"+";
        }
        s = s.substring(0, s.length() - 6);
        cg.add(s + ";");
        cg.add("\n}");

        cg.add("}//fim da classe\n\n");

//        for (String linha : cg) {
//            System.out.println(linha);
//        }
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho+"/src/Entidades/" + nomeDaClasse2 + ".java", cg);
    }
    
}
