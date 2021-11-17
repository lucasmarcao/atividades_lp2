/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

public class GerarMenuGUI {
    
    public GerarMenuGUI (String nomeDaClasse,String nomeDaClasse2,List<String> atributo2, List<String> atributo, String caminho){
        StringTools st = new StringTools();
        StringTools st2 = new StringTools();
        String[] aux;
        List<String> cg = new ArrayList();//código gerado
        
        cg.add("package GUIs;");
        
        cg.add("import java.awt.BorderLayout;\n" +
"import java.awt.Color;\n" +
"import java.awt.Font;"+
"import java.awt.Container;\n" +
"import java.awt.GridLayout;\n" +
"import java.awt.event.ActionEvent;\n" +
"import java.awt.event.ActionListener;\n" +
"import javax.swing.ImageIcon;\n" +
"import javax.swing.JButton;\n" +
"import javax.swing.JFrame;\n" +
"import static javax.swing.JFrame.EXIT_ON_CLOSE;\n" +
"import javax.swing.JLabel;\n" +
"import javax.swing.JPanel;\n" +
"\n" +
"/**\n" +
" *\n" +
" * @author Lucas\n" +
" */\n" +
"public class Menu_do_projeto extends JFrame {\n" +
"    private Container cp;\n" +
"    private JPanel painelnorte = new JPanel();\n" +
"    private JPanel painelcentro = new JPanel();\n" +
"    private JPanel painelsul = new JPanel();\n" +
"    private JPanel painelleste = new JPanel();\n" +
"    private JPanel paineloeste = new JPanel();\n" +
"    private JButton crudprincipal = new JButton(\" CRUD PRINCIPAL. \");\n" +
"    private JButton crudfk = new JButton(\" CRUD COM FK. \");\n" +
"    private JLabel imagem = new JLabel(\"<html><img src=\\\"https://cdn-icons-png.flaticon.com/512/1760/1760560.png\\\" alt=\\\"some text\\\" width=160 height=160></html>\");\n" +
"    \n" +
"\n" +
"    \n" +
"    public Menu_do_projeto() {\n" +
"        setDefaultCloseOperation(EXIT_ON_CLOSE);\n" +
"        cp = getContentPane();\n" +
"        cp.setLayout(new BorderLayout());\n" +
"        setTitle(\"      MENU PRINCIPAL. \");\n" +
"        \n" +
"        cp.add(painelnorte, BorderLayout.NORTH);\n" +
"        cp.add(painelcentro, BorderLayout.CENTER);\n" +
"        cp.add(painelsul, BorderLayout.SOUTH);\n" +
"        cp.add(painelleste, BorderLayout.EAST);\n" +
"        cp.add(paineloeste, BorderLayout.WEST);\n" +
"        \n" +
"        painelleste.setBackground(new Color (50,205,50));\n" +
"        paineloeste.setBackground(new Color (50,205,50));\n" +
"        painelnorte.setBackground(new Color (50,205,50));\n" +
"        painelcentro.setBackground(new Color (255,100,227));\n" +
"        painelsul.setBackground(new Color (50,205,50));\n" +
"        \n" +
"        painelcentro.setLayout(new GridLayout(3,1));\n" +
"        painelcentro.add(crudprincipal);\n" +
"        painelcentro.add(imagem);" +
"        painelcentro.add(crudfk);\n" +
"        imagem.setHorizontalAlignment((int) CENTER_ALIGNMENT); \n"+
"        crudprincipal.setFont(new Font(\"Algerian\", Font.CENTER_BASELINE, 30));\n" +
"        crudfk.setFont(new Font(\"Algerian\", Font.CENTER_BASELINE, 30));"+
"        \n" +
"        \n" +
"        \n" +
"        crudfk.setBackground(new Color (255,105,97));\n" +
"        crudprincipal.setBackground(Color.CYAN);\n" +
"        \n" +
"        crudprincipal.addActionListener(new ActionListener() {\n" +
"            @Override\n" +
"            public void actionPerformed(ActionEvent ae) {\n" +
"        "+nomeDaClasse+"GUI guiDoGerador = new "+nomeDaClasse+"GUI();\n"+
"            }\n" +
"        });\n" +
"        \n" +
"        \n" +
"        crudfk.addActionListener(new ActionListener() {\n" +
"            @Override\n" +
"            public void actionPerformed(ActionEvent ae) {\n" +
"        "+nomeDaClasse2+"GUI guiDoGerador2 = new "+nomeDaClasse2+"GUI();\n"+
"            }\n" +
"        });\n" +
"        setSize(600,650);        \n" +
"        setLocationRelativeTo(null);\n" +
"        setVisible(true);\n" +
"        \n" +
"        \n" +
"        \n" +
"    } \n"+
"}"
        );
  
        
        
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo(caminho+"/src/GUIs/" + "Menu_do_projeto" + ".java", cg);
    }
    
}
