package Controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

/**
 *
 * @author lucasmarcao
 */
public class GerarClasseDeControle2 {

    public GerarClasseDeControle2(String nomeDaClasse2, List<String> atributo2, String caminho) {
        StringTools st2 = new StringTools();
        String[] aux;
        List<String> cg = new ArrayList();//código gerado
        cg.add("package Controles;");

        cg.add("import Entidades." + nomeDaClasse2 + ";\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                +"import java.text.SimpleDateFormat;"
                +"import java.sql.Date;"
                + "import tools.ManipulaArquivo;");

        cg.add("/**\n"
                + " *\n"
                + " * @author Lucasmarcão\n" + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date())
                + " */");
        cg.add("public class " + nomeDaClasse2 + "Controle {\n");
        cg.add(" private List<" + nomeDaClasse2 + "> lista = new ArrayList<>();");
        cg.add(" public " + nomeDaClasse2 + "Controle() {\n"
                + "    }");

        cg.add("public void limparLista() {\n"
                + "        lista.clear();//zera a lista\n"
                + "    }");
        cg.add(" public void adicionar(" + nomeDaClasse2 + " " + st2.plMinus(nomeDaClasse2) + ") {\n"
                + "        lista.add(" + st2.plMinus(nomeDaClasse2) + ");\n"
                + "    } \n"
        +"SimpleDateFormat simpleDateFormat = new SimpleDateFormat(\"dd/MM/yyyy\");");
        cg.add("public List<" + nomeDaClasse2 + "> listar() {\n"
                + "        return lista;\n"
                + "    }");

        aux = atributo2.get(0).split(";");
        cg.add(" public " + nomeDaClasse2 + " buscar(" + aux[0] + " " + aux[1] + ") {\n"
                + "        for (int i = 0; i < lista.size(); i++) {\n");
        String s = "";
        switch (aux[0]) {
            case "int":
                s = "if (lista.get(i).get" + st2.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "short":
                s = "if (lista.get(i).get" + st2.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "double":
                s = "if (lista.get(i).get" + st2.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n";
                break;
            case "String":
                s = "if (lista.get(i).get" + st2.plMaiusc(aux[1]) + "().equals(" + aux[1] + ")) {\n";
                break;
            default:
                throw new AssertionError();
        }
        cg.add(s);

        cg.add("                return lista.get(i);\n"
                + "            }\n"
                + "        }\n"
                + "        return null;\n"
                + "    }");
        cg.add("public void alterar(" + nomeDaClasse2 + " " + st2.plMinus(nomeDaClasse2) + ", " + nomeDaClasse2 + " " + st2.plMinus(nomeDaClasse2) + "Antigo) {\n"
                + "        lista.set(lista.indexOf(" + st2.plMinus(nomeDaClasse2) + "Antigo), " + st2.plMinus(nomeDaClasse2) + ");\n"
                + "\n"
                + "    }");
        cg.add("public void excluir(" + nomeDaClasse2 + " " + st2.plMinus(nomeDaClasse2) + ") {\n"
                + "        lista.remove(" + st2.plMinus(nomeDaClasse2) + ");\n"
                + "    }");
        cg.add(" public void gravarLista(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        List<String> listaDeString = new ArrayList<>();\n"
                + "        for (" + nomeDaClasse2 + " " + st2.plMinus(nomeDaClasse2) + " : lista) {\n"
                + "            listaDeString.add(" + st2.plMinus(nomeDaClasse2) + ".toString()+System.lineSeparator());\n"
                + "        }\n"
                + "        manipulaArquivo.salvarArquivo(caminho, listaDeString);\n"
                + "    }");

        s = "";
        for (int i = 0; i < atributo2.size(); i++) {

            aux = atributo2.get(i).split(";");
            //   aux[i]=aux[i].trim();            
            if (aux[0].equals("String")) {
                s = s + "aux[" + i + "], ";
            } else if (aux[0].equals("int")) {
                s = s + "Integer.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("double")) {
                s = s + "Double.valueOf(aux[" + i + "]), ";
            }else if (aux[0].equals("Date")) {
                s = s + "simpleDateFormat.parse(aux[" + i + "]), ";
            }
        }
        s = s.substring(0, s.length() - 2);
        cg.add("public void carregarDados(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        if (!manipulaArquivo.existeOArquivo(caminho)) {\n"
                + "            manipulaArquivo.criarArquivoVazio(caminho);\n"
                + "        }\n"
                + "\n"
                + "        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);\n"
                + "        //converter de CSV para " + nomeDaClasse2 + "\n"
                + "        " + nomeDaClasse2 + " " + st2.plMinus(nomeDaClasse2) + ";\n"
                + "        for (String string : listaDeString) {\n"
                + "            String aux[] = string.split(\";\");\n"
                + "            " + st2.plMinus(nomeDaClasse2) + " = new " + nomeDaClasse2 + "(" + s + ");\n"
                + "            lista.add(" + st2.plMinus(nomeDaClasse2) + ");\n"
                + "        }\n"
                + "    }");
        cg.add("");
        cg.add("");

        cg.add("} //fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho + "/src/Controles/" + nomeDaClasse2 + "Controle.java", cg);

    }

}

