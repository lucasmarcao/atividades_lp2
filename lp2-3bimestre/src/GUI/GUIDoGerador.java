/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controle.GerarClasseDeControle;
import Controle.GerarClasseDeControle2;
import Controle.GerarClasseDeEntidade;
import Controle.GerarClasseDeEntidade2;
import Controle.GerarClasseGUI;
import Controle.GerarClasseGUI2;
import Controle.GerarClasseMain;
import Controle.GerarMenuGUI;
import Main.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import tools.CopiarArquivos;
import tools.ManipulaArquivo;

public class GUIDoGerador extends JFrame {

    private Container cp;
    private final JPanel pnNorte = new JPanel();
    private final JPanel pnCentro = new JPanel();
    private final JPanel pnCentroN = new JPanel();
    private final JPanel pnCentroC = new JPanel();
    
    private final JPanel pnSul = new JPanel();
    private final JLabel lbDestino = new JLabel("Projeto destino");
    private final JTextField tfDestino = new JTextField(50);
    private final JButton btEscolherDestino = new JButton("Escolher");
    private final JButton btGerarEstrutura = new JButton("Gerar Estrutura");
    private final JLabel lbNomeClasse = new JLabel("Classe Entidade");
    private final JTextField tfNomeClasse = new JTextField(25);
    
    private final JLabel lbNomeClassepk = new JLabel("Chave Estrangeira");
    private final JTextField tfNomeClassefk = new JTextField(25);
    
    private final JTextArea txtArea = new JTextArea();
    private final JScrollPane barraDeRolagem = new JScrollPane(txtArea);

    private final JTextArea txtArea2 = new JTextArea();
    private final JScrollPane barraDeRolagem2 = new JScrollPane(txtArea2);
    
    private final JButton btGerarEntidade = new JButton("Entidade");
    private final JButton btGerarMenu = new JButton("MENU");
    private final JButton btGerarControle = new JButton("Controle");
    private final JButton btGerarGUI = new JButton("GUI");
    private final JButton btGerarMain = new JButton("Main");
    private final JButton btGerarTodos = new JButton("GERAR TUDO");
    private final JButton btAbrirEntidade = new JButton("Abrir");
    private final JButton btSalvarEntidade = new JButton("Salvar");
    private JFileChooser caixaDeDialogo = new JFileChooser();
    private String caminho = "";
    private ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    private List<String> texto = new ArrayList();
    private List<String> texto2 = new ArrayList();

    public GUIDoGerador() {
        
        
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        lbDestino.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pnNorte.setBackground(new Color(64, 237, 26));
        pnSul.setBackground(Color.gray);

        pnNorte.add(lbDestino);
        pnNorte.add(tfDestino);
        pnNorte.add(btEscolherDestino);
        pnNorte.add(btGerarEstrutura);

        pnCentro.setLayout(new BorderLayout());

        pnCentro.add(pnCentroN, BorderLayout.NORTH);
        pnCentro.add(pnCentroC, BorderLayout.CENTER);
        pnCentroN.setBackground(Color.black);
      
        
        pnCentroN.add(lbNomeClasse);
        pnCentroN.add(tfNomeClasse);
        
        pnCentroN.add(btAbrirEntidade);
        pnCentroN.add(btSalvarEntidade);
        
        pnCentroN.add(lbNomeClassepk);
        pnCentroN.add(tfNomeClassefk);
        
        btAbrirEntidade.setBackground(new Color(255, 167, 25));
        btSalvarEntidade.setBackground(new Color(255, 167, 25));

        pnCentroC.setLayout(new GridLayout(1, 2));//força o textArea a ocupar todo o espaço

        txtArea.setForeground(Color.BLUE);
        txtArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
        barraDeRolagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.txtArea.setEditable(true);
        pnCentroC.add(barraDeRolagem); //É aqui que coloco no meu Painel
        
        txtArea2.setForeground(Color.BLUE);
        txtArea2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        barraDeRolagem2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.txtArea2.setEditable(true);
        pnCentroC.add(barraDeRolagem2); //É aqui que coloco no meu Painel
        
        
        lbNomeClasse.setFont(new Font("Verdana", Font.BOLD, 15));
        lbNomeClassepk.setFont(new Font("Verdana", Font.BOLD, 15));
        
        txtArea.setBorder(BorderFactory.createLineBorder(Color.black));
        txtArea2.setBorder(BorderFactory.createLineBorder(Color.black));
        tfNomeClassefk.setBorder(BorderFactory.createLineBorder(Color.blue));
        tfNomeClasse.setBorder(BorderFactory.createLineBorder(Color.blue));
        tfDestino.setBorder(BorderFactory.createLineBorder(Color.black));
        txtArea.setBackground(new Color(239, 242, 150));
        txtArea2.setBackground(new Color(179, 249, 241));
        
        lbNomeClasse.setForeground(new Color(239, 242, 150));
        lbNomeClassepk.setForeground(new Color(179, 249, 241));
        
        
        pnSul.setLayout(new GridLayout(1, 6));
        
        pnSul.add(btGerarEntidade);
        pnSul.add(btGerarControle);
        pnSul.add(btGerarGUI);
        pnSul.add(btGerarMain);
        pnSul.add(btGerarMenu);
        pnSul.add(btGerarTodos);

        btGerarControle.setBackground(new Color(164, 153, 199));
        btGerarEntidade.setBackground(new Color(164, 153, 199));
        btGerarGUI.setBackground(new Color(164, 153, 199));
        btGerarMain.setBackground(new Color(164, 153, 199));
        btGerarTodos.setBackground(Color.GREEN);
        btGerarTodos.setForeground(Color.white);
        btGerarTodos.setFont(new Font("Algerian", Font.CENTER_BASELINE, 20));
        
        btGerarEstrutura.setBackground(Color.cyan);
        btEscolherDestino.setBackground(Color.WHITE);
        
        texto = manipulaArquivo.abrirArquivo("ProjetoEscolhido.txt");
        if (texto.size() > 0) {
            caminho = texto.get(0);
            tfDestino.setText(caminho);
        }
        
        btAbrirEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("src/txts/" + tfNomeClasse.getText() + ".txt");
                File file2 = new File("src/txts/" + tfNomeClassefk.getText() + ".txt");
                texto.clear();
                texto2.clear();
                txtArea.setText("");
                txtArea2.setText("");
                if (file.exists()) {
                    texto = manipulaArquivo.abrirArquivo("src/txts/" + tfNomeClasse.getText() + ".txt");
                    for (String string : texto) {
                        txtArea.append(string + System.lineSeparator());
                        
                    }
                                  
                }
                if (file2.exists()) {
                    texto2 = manipulaArquivo.abrirArquivo("src/txts/" + tfNomeClassefk.getText() + ".txt");
                    for (String string : texto2) {
                        txtArea2.append(string + System.lineSeparator());
                        
                    }     
                }
                
            }

        });
        
        btEscolherDestino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("DIRETÓRIO", "..", "..");
                caixaDeDialogo.setFileFilter(filter);
                caixaDeDialogo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                File file = new File(caminho);
                if (file.exists()) {
                    caixaDeDialogo.setCurrentDirectory(file);
                } else {
                    file = new File("/Users/Usuario/Documents/NetBeansProjects");
                    if (file.exists()) {
                        caixaDeDialogo.setCurrentDirectory(file);
                    } else {
                        caixaDeDialogo.setCurrentDirectory(null);
                    }

                }
                if (caixaDeDialogo.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                    caminho = caixaDeDialogo.getSelectedFile().getAbsolutePath();
                    tfDestino.setText(caminho);
                    texto.clear();
                    texto.add(caminho);
                    manipulaArquivo.salvarArquivo("ProjetoEscolhido.txt", texto);
                }
            }

        });

         
        btGerarEstrutura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String src = tfDestino.getText();
                List<String> listaString = new ArrayList<>();
                listaString.clear();
                listaString.add("/src/" + "Entidades");
                listaString.add("/src/" + "Controles");
                listaString.add("/src/" + "GUIs");
                listaString.add("/src/" + "tools");
                
                listaString.add("/src/" + "Main");
                for (String pacote : listaString) {
                    File pac = new File(src + pacote);
                    if (!pac.exists()) {
                        new File(src + pacote).mkdir();//cria as pastas
                    }
                }

              
                

     

                //copiar pacote de ferramentas
                File listaFerramentas = new File("src/tools");
                if (listaFerramentas.exists()) {
                    File[] arqs = listaFerramentas.listFiles();
                    CopiarArquivos copiarArquivos = new CopiarArquivos();
                    for (int i = 0; i < arqs.length; i++) {
                        copiarArquivos.copiar(arqs[i].getAbsolutePath(),
                                caminho + "/src" + "/tools/" + arqs[i].getName());
                    }

                }

            }
        });
        
        tfNomeClasse.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfNomeClasse.setBackground(new Color(197, 214, 249));
            }

            @Override
            public void focusLost(FocusEvent fe) {
                String s = tfNomeClasse.getText();
                if (!s.trim().equals("")) {
                    String nomeAjustado = String.valueOf(s.charAt(0)).toUpperCase()
                            + s.substring(1, s.length());
                    tfNomeClasse.setText(nomeAjustado);
                }
                tfNomeClasse.setBackground(Color.white);
            }
        });
        
        btSalvarEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = txtArea.getText();
                StringTokenizer st = new StringTokenizer(s, System.lineSeparator());
                texto.clear();
                String s2 = txtArea2.getText();
                StringTokenizer st2 = new StringTokenizer(s2, System.lineSeparator());
                texto2.clear();
                while (st.hasMoreTokens()) {
                    String line = st.nextToken();
                    line = line.trim();
                    if (!line.equals("")) {//para evitar que sejam gravadas linhas vazias
                        texto.add(line + System.lineSeparator());
                    }

                }
                while (st2.hasMoreTokens()) {
                    String line2 = st2.nextToken();
                    line2 = line2.trim();
                    if (!line2.equals("")) {//para evitar que sejam gravadas linhas vazias
                        texto2.add(line2 + System.lineSeparator());
                    }

                }
                
                manipulaArquivo.salvarArquivo("src/txts/" + tfNomeClasse.getText() + ".txt", texto);
                manipulaArquivo.salvarArquivo("src/txts/" + tfNomeClassefk.getText() + ".txt", texto2);
            }
        });
        
        tfNomeClassefk.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfNomeClassefk.setBackground(new Color(197, 214, 249));
            }

            @Override
            public void focusLost(FocusEvent fe) {
                String s = tfNomeClassefk.getText();
                if (!s.trim().equals("")) {
                    String nomeAjustado = String.valueOf(s.charAt(0)).toUpperCase()
                            + s.substring(1, s.length());
                    tfNomeClassefk.setText(nomeAjustado);
                }
                tfNomeClassefk.setBackground(Color.white);
            }
        });
        
        btGerarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btGerarEntidade.doClick();
                btGerarControle.doClick();
                btGerarGUI.doClick();
                btGerarMain.doClick();  
                btGerarMenu.doClick();
            }
        });
        
        btGerarEntidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseDeEntidade gerarClasseDeEntidade = new GerarClasseDeEntidade(nomeDaClasse, atributo, caminho);
                }
                String nomeDaClasse2 = tfNomeClassefk.getText();
                
                caminho = tfDestino.getText();
                String oArquivo2 = "src/txts/" + tfNomeClassefk.getText() + ".txt";
                File file2 = new File(oArquivo2);
                if (file2.exists()) {
                    List<String> atributo2 = manipulaArquivo.abrirArquivo(oArquivo2);
                    GerarClasseDeEntidade2 gerarClasseDeEntidade2 = new GerarClasseDeEntidade2(nomeDaClasse2, atributo2, caminho);
                }
                
                

            }
        });
        btGerarControle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseDeControle gerarClasseDeEntidade = new GerarClasseDeControle(nomeDaClasse, atributo, caminho);
                }
                String nomeDaClasse2 = tfNomeClassefk.getText();
                
                caminho = tfDestino.getText();
                String oArquivo2 = "src/txts/" + tfNomeClassefk.getText() + ".txt";
                File file2 = new File(oArquivo2);
                if (file2.exists()) {
                    List<String> atributo2 = manipulaArquivo.abrirArquivo(oArquivo2);
                    GerarClasseDeControle2 gerarClasseDeEntidade2 = new GerarClasseDeControle2(nomeDaClasse2, atributo2, caminho);
                }

            }
        });
        btGerarGUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                File file = new File(oArquivo);
                String nomeDaClasse2 = tfNomeClassefk.getText();
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseGUI gerarClasseDeEntidade = new GerarClasseGUI(nomeDaClasse, atributo, caminho, nomeDaClasse2);
                }
                
               
                caminho = tfDestino.getText();
                String oArquivo2 = "src/txts/" + tfNomeClassefk.getText() + ".txt";
                File file2 = new File(oArquivo2);
                if (file2.exists()) {
                    List<String> atributo2 = manipulaArquivo.abrirArquivo(oArquivo2);
                    GerarClasseGUI2 gerarClasseDeEntidade2 = new GerarClasseGUI2(nomeDaClasse2, atributo2, caminho);
                }

            }
        });
        btGerarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                String nomeDaClasse2 = tfNomeClassefk.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                String oArquivo2 = "src/txts/" + tfNomeClassefk.getText() + ".txt";
                File file = new File(oArquivo);
                File file2 = new File(oArquivo2);
                if (file.exists() && file2.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    List<String> atributo2 = manipulaArquivo.abrirArquivo(oArquivo2);
                    GerarMenuGUI gerarMenuGUI = new GerarMenuGUI(nomeDaClasse,nomeDaClasse2,atributo2, atributo, caminho);
                }
                
                
            }
        });
        btGerarMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDaClasse = tfNomeClasse.getText();
                btSalvarEntidade.doClick();
                caminho = tfDestino.getText();
                String oArquivo = "src/txts/" + tfNomeClasse.getText() + ".txt";
                File file = new File(oArquivo);
                if (file.exists()) {
                    List<String> atributo = manipulaArquivo.abrirArquivo(oArquivo);
                    GerarClasseMain gerarClasseDeEntidade = new GerarClasseMain(nomeDaClasse, atributo, caminho);
                }
            }
        });

        btGerarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btGerarEntidade.doClick();
                btGerarControle.doClick();
                btGerarGUI.doClick();
                btGerarMain.doClick();            
            }
        });
        
        setResizable(false);
        setTitle("   FAZEDOR DE CRUD DO MARCÃO.");
        setSize(1110, 670);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

}
