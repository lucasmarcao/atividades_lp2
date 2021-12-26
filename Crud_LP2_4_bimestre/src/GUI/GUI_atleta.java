/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controle.Controle_atleta;
import Entidade.Atleta;
import com.itextpdf.text.BaseColor;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import tools.ManipulaArquivo;
import tools.CopiarArquivos;
import tools.DiretorioDaAplicacao;
import tools.ImagemAjustada;




public class GUI_atleta extends JDialog{
    Container cp;
    int ciclo = 1;
      
    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel(new BorderLayout());
    JPanel paineloeste = new JPanel();
    JLabel lnome = new JLabel(" NOME : ");
    JTextField digitanome = new JTextField(22);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");
    JButton btAdicionarFoto = new JButton("Adicionar/alterar foto");
    JButton btRemoverFoto = new JButton("Remover foto");
    JButton btcriarpdf = new JButton(
    "<html><img src=\"https://icones.pro/wp-content/uploads/2021/03/icone-pdf-symbole-png-rouge.png\" width=30 height=30></html>");
    
    JLabel lmodalidade = new JLabel(" Tipo de alien : ");
    JTextField digitaTipoAlien = new JTextField(30);
    
    JLabel laltura = new JLabel(" Altura : ");
    JTextField digitaaltura = new JTextField(30);
    
    JLabel lpeso = new JLabel(" Peso : ");
    JTextField digitapeso = new JTextField(30);
    
    JLabel lnacionalidade = new JLabel(" Planeta Natal : ");
    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
    JComboBox planetaDoAlien = new JComboBox(cbm);
    
    JLabel lrecordes = new JLabel("Primeiro contato na terra:");
    JTextField digitadata = new JTextField(30);
    
    
    Controle_atleta controle = new Controle_atleta();
    Atleta atleta = new Atleta();
    JPanel bordalinda1 = new JPanel();
    JPanel bordalinda2 = new JPanel();
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    String acao = "";
    ///////////////////////////////////
    String [] colunas = new String[] {" NOME"+" MODALIDADE"+"ALTURA"+"PESO"+"NACIONALIDADE"+"FOTOS"+"NASCIMENTO"};
    String [][] dados = new String[0][6];
    
    DefaultTableModel model = new DefaultTableModel(dados,colunas);
    JTable tabela =  new JTable(model);
    
    int cont = 0;
    JButton tirasom = new JButton("Tirar som");
    JButton botasom = new JButton("Botar som");
    
    
    private JScrollPane scrolltabela = new JScrollPane();
    
    private JPanel pnavisos = new JPanel(new GridLayout(1,1));
    private JPanel pnlistagem = new JPanel(new GridLayout(1,1));
    private JPanel pnvazio = new JPanel(new GridLayout(6,1));
    
    private CardLayout cardLayout;
    
    //aqui fica as coisa da imagem
    ImagemAjustada imagemAjustada = new ImagemAjustada();
    DiretorioDaAplicacao diretorioDaAplicacao = new DiretorioDaAplicacao();
    String dirApp = diretorioDaAplicacao.getDiretorioDaAplicacao();
    String origem = dirApp + "/src/fotos/simbolo_lp_4_bim.png";
    int tamX = 300;
    int tamY = 300;
    String temFoto = "";
    
    JLabel lbCaminhoFoto = new JLabel("");
    JLabel lbFoto = new JLabel();
    
    JPanel pnLesteA = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel pnLesteB = new JPanel(new GridLayout(1, 1));
    
    
    
    public GUI_atleta(){
        
        try {
            
            MaskFormatter formatter = new MaskFormatter("##/##/####");
            this.digitadata = new JFormattedTextField(formatter);     
            formatter.setPlaceholderCharacter('0');
   
        } catch (ParseException ex) {
            System.out.println("deu erro na mascara data");
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle(" Aliens Ben 10 - CRUD");
        
        
        lbFoto.setIcon(imagemAjustada.getImagemAjustada(origem, tamX, tamY));
        
        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        
        cp.add(painelleste, BorderLayout.EAST);
        painelleste.add(pnLesteA, BorderLayout.NORTH);
        painelleste.add(pnLesteB, BorderLayout.CENTER);
        pnLesteA.add(btAdicionarFoto);
        pnLesteA.add(btRemoverFoto);
        pnLesteB.add(lbFoto);
        painelleste.setBackground(Color.PINK);
        pnLesteA.setBackground(new Color(45,87,44));
        pnLesteB.setBackground(new Color(45,87,44));
        btAdicionarFoto.setVisible(false);
        btRemoverFoto.setVisible(false);
        cp.add(paineloeste, BorderLayout.WEST);
        
        btAdicionarFoto.setBackground(new Color(119,221,119));
        btRemoverFoto.setBackground(new Color(196,2,51));
        btRemoverFoto.setForeground(Color.WHITE);
        
        painelleste.setBackground(new Color(45,87,44));
        paineloeste.setBackground(new Color(45,87,44));
        painelnorte.setBackground(new Color(45,87,44));
        painelcentro.setBackground(Color.black);
        painelsul.setBackground(new Color(45,87,44));
        
        painelsul.setForeground(Color.PINK);
        
        painelnorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelnorte.add(lnome);
        painelnorte.add(digitanome);
        painelnorte.add(botaobuscar);
        painelnorte.add(botaosalvar);
        painelnorte.add(botaolistar);
        painelnorte.add(botaoadicionar);
        painelnorte.add(botaocancelar);
        painelnorte.add(botaoalterar);
        painelnorte.add(botaoexcluir);
        painelnorte.add(tirasom);
        painelnorte.add(botasom);
        painelnorte.add(btcriarpdf);
        
        btcriarpdf.setBackground(Color.WHITE);
        
        btcriarpdf.setVisible(false);
        
        lnome.setForeground(Color.white);
        lnome.setFont(new Font("Verdana", Font.BOLD, 18));
        
        lmodalidade.setForeground(Color.GREEN);
        laltura.setForeground(Color.MAGENTA);
        lpeso.setForeground(Color.WHITE);
        lnacionalidade.setForeground(Color.ORANGE);
        lrecordes.setForeground(Color.CYAN);
        
        lmodalidade.setFont(new Font("Algerian", Font.BOLD, 18));
        laltura.setFont(new Font("Algerian", Font.BOLD, 18));
        lpeso.setFont(new Font("Algerian", Font.BOLD, 18));
        lnacionalidade.setFont(new Font("Algerian", Font.BOLD, 18));
        lrecordes.setFont(new Font("Algerian", Font.BOLD, 18));
        
        digitanome.setForeground(Color.GREEN);
        digitanome.setFont(new Font("Verdana", Font.BOLD, 18));
        
        planetaDoAlien.setBackground(Color.cyan);
        
        tirasom.setBackground(Color.PINK);
        botasom.setBackground(Color.PINK);
        botasom.setVisible(false);
        
        
        digitanome.setBackground(Color.DARK_GRAY);
        
        digitaTipoAlien.setFont(new Font("Impact", Font.PLAIN, 22));
        digitaTipoAlien.setForeground(new Color(0,96,55));
        digitaaltura.setFont(new Font("Impact", Font.PLAIN, 22));
        digitaaltura.setForeground(new Color(0,96,55));
        digitapeso.setFont(new Font("Impact", Font.PLAIN, 22));
        digitapeso.setForeground(new Color(0,96,55));
        digitadata.setFont(new Font("Impact", Font.PLAIN, 22));
        digitadata.setForeground(new Color(0,96,55));
        planetaDoAlien.setFont(new Font("Impact", Font.PLAIN, 22));
        planetaDoAlien.setForeground(new Color(0,96,55));
        
        botaoadicionar.setVisible(false);
        botaosalvar.setVisible(false);
        botaoalterar.setVisible(false);
        botaocancelar.setVisible(false);
        botaoexcluir.setVisible(false);
        
        
        botaobuscar.setBackground(Color.GREEN);
        botaoadicionar.setBackground(Color.WHITE);
        botaosalvar.setBackground(Color.cyan);
        botaoalterar.setBackground(Color.ORANGE);
        botaoexcluir.setBackground(Color.blue);
        botaocancelar.setBackground(Color.red);
        botaolistar.setBackground(Color.LIGHT_GRAY);
      
        painelcentro.setLayout(new GridLayout(6,2));
  
        
        painelcentro.add(lmodalidade);
        painelcentro.add(digitaTipoAlien);
        
        painelcentro.add(laltura);
        painelcentro.add(digitaaltura);
        
        painelcentro.add(lpeso);
        painelcentro.add(digitapeso);
        
        painelcentro.add(lnacionalidade);
        painelcentro.add(planetaDoAlien);
        
        painelcentro.add(lrecordes);
        painelcentro.add(digitadata);
        
        painelcentro.add(bordalinda1);
        painelcentro.add(bordalinda2);

        
        pnavisos.setBackground(new Color(45,87,44));
        pnlistagem.setBackground(new Color(45,87,44));
        pnvazio.setBackground(new Color(45,87,44));
        
        
        bordalinda1.setBackground(new Color(45,87,44));
        bordalinda2.setBackground(new Color(45,87,44));
        lmodalidade.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        laltura.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        lpeso.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        lnacionalidade.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        lrecordes.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        digitaTipoAlien.setBorder(BorderFactory.createLineBorder(Color.black));
        digitaaltura.setBorder(BorderFactory.createLineBorder(Color.black));
        digitapeso.setBorder(BorderFactory.createLineBorder(Color.black));
        planetaDoAlien.setBorder(BorderFactory.createLineBorder(Color.black));
        digitadata.setBorder(BorderFactory.createLineBorder(Color.black));
        
        painelleste.setBorder(BorderFactory.createLineBorder(Color.black));
        
        cardLayout = new CardLayout();
        painelsul.setLayout(cardLayout);
        
        for (int i = 0; i < 5; i++) {
            pnvazio.add(new JLabel(" "));
        }
        painelsul.add(pnvazio,"vazio");
        painelsul.add(pnlistagem,"listagem");        
        painelsul.add(pnavisos,"");
        tabela.setEnabled(false);

        
        pnavisos.add(new JLabel("avisos"));
        //pnlistagem.add(new JLabel(""));
        
        
        
        String caminho = "Aliens.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);
        
        List<String> umCSV = new ManipulaArquivo().abrirArquivo("Entidade_Nacionalidades.csv");
        for (String string : umCSV ) {
            String[] aux = string.split(";");
            cbm.addElement(string.replace(";", "-"));
            
        }
        
        digitaTipoAlien.setEditable(false);
        digitaaltura.setEditable(false);
        digitapeso.setEditable(false);
        planetaDoAlien.setEnabled(false);
        digitadata.setEditable(false);        
        
        btRemoverFoto.setFont(new Font("Impact", Font.PLAIN, 15));
        btAdicionarFoto.setFont(new Font("Impact", Font.PLAIN, 15));
        
        digitanome.setBorder(BorderFactory.createLineBorder(Color.black));
        
        tirasom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                botasom.setVisible(true);
                tirasom.setVisible(false);
                cont = 1;

            }
        });
        
        botasom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                botasom.setVisible(false);
                tirasom.setVisible(true);
                cont = 0;

            }
        });
        
        botaobuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {            
                if (cont==0) {
                    play("rapaz");
                }
                cardLayout.show(painelsul,"avisos");
                digitanome.requestFocus();
                atleta = controle.buscar(digitanome.getText());
                if (atleta!=null) {
                    btcriarpdf.setBackground(Color.WHITE);
                    ciclo = 1;
                    botaoalterar.setVisible(true);
                    botaoexcluir.setVisible(true);
                    botaoadicionar.setVisible(false);
                    digitaTipoAlien.setText(atleta.getModalidade());
                    digitaaltura.setText(String.valueOf(atleta.getAltura() + "  centimetros."));
                    digitapeso.setText(String.valueOf(atleta.getPeso() + "  quilos. "));
                    int j = 0;
                    for (j = 0; j < umCSV.size(); j++) {
                        if (umCSV.get(j).split(";")[0].equals(atleta.getSigla())) {
                            break;
                        }
                        
                    }
                    if (j< umCSV.size()) {
                        planetaDoAlien.setSelectedIndex(j);
                        
                    }
                    digitadata.setText(simpleDateFormat.format(atleta.getNascimento()));
                    
                    // isso é da imagem
                     String c = dirApp + "/src/fotos/" + atleta.getNome()+ ".png";

                    if (atleta.getFoto().equals("Sim")) { //tem foto
                        ImageIcon ii = imagemAjustada.getImagemAjustada(c, tamY, tamY);
                        lbFoto.setIcon(ii);
                        lbCaminhoFoto.setText(dirApp + "/src/fotos/" + atleta.getNome() + ".png");
                    } else {
                        c = dirApp + "/src/fotos/simbolo_lp_4_bim.png";
                        ImageIcon ii = imagemAjustada.getImagemAjustada(c, tamY, tamY);
                        lbFoto.setIcon(ii);
                    }
                    lbCaminhoFoto.setText(c);
                    // acabou o imagem
                    digitaTipoAlien.setEditable(false);
                    digitaaltura.setEditable(false);
                    digitapeso.setEditable(false);
                    planetaDoAlien.setEnabled(false);
                    digitadata.setEditable(false);
                    botaosalvar.setVisible(false);
                    btcriarpdf.setVisible(true);
                }else{
                    digitaTipoAlien.setText("");
                    digitaaltura.setText("");
                    digitapeso.setText("");
                    btcriarpdf.setVisible(false);
                    digitadata.setText("");
                    botaoadicionar.setVisible(true);
                    digitaTipoAlien.setEditable(false);
                    digitaaltura.setEditable(false);
                    digitapeso.setEditable(false);
                    planetaDoAlien.setEnabled(false);
                    digitadata.setEditable(false);
                    botaosalvar.setVisible(false);
                    botaoalterar.setVisible(false);
                    botaoexcluir.setVisible(false);
                    ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/simbolo_lp_4_bim.png", tamX, tamY);
                    lbFoto.setIcon(ii);
                    lbCaminhoFoto.setText(dirApp + "/src/fotos/simbolo_lp_4_bim.png");

                }
                
            }
        });
        
        botaoadicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (cont==0) {
                    play("rapaz");
                }
                btcriarpdf.setVisible(false);
                digitanome.setEditable(false);
                digitaTipoAlien.setEditable(true);
                digitaTipoAlien.requestFocus();
                digitaaltura.setEditable(true);
                digitapeso.setEditable(true);
                planetaDoAlien.setEnabled(true);
                digitadata.setEditable(true);
                botaoadicionar.setVisible(false);
                botaosalvar.setVisible(true);
                botaobuscar.setVisible(false);
                botaocancelar.setVisible(true); 
                botaolistar.setVisible(false);
                acao = "adicionar";
                btAdicionarFoto.setVisible(true);
                btRemoverFoto.setVisible(true);
                temFoto = "Não";

            }
        });
        
        botaosalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);
                botaocancelar.setVisible(false);
                botaolistar.setVisible(true);
                try {
                    CopiarArquivos copiarArquivos = new CopiarArquivos();
                //salvar a imagem
                //copiar a foto da origem para a pasta destino no projeto
                
                    if (acao.equals("adicionar")) {
                        atleta = new Atleta();
                    }
                    Atleta antigo = atleta;
                    
                    atleta.setNome(digitanome.getText());
                    atleta.setModalidade(digitaTipoAlien.getText());
                    atleta.setAltura(Double.valueOf(digitaaltura.getText()));
                    atleta.setPeso(Double.valueOf(digitapeso.getText()));
                    atleta.setSigla(String.valueOf(planetaDoAlien.getSelectedItem()).split("-")[0]);
                    atleta.setNascimento(simpleDateFormat.parse(digitadata.getText()));
                    atleta.setFoto(temFoto);
                    String destino = dirApp + "/src/fotos/";
                    destino = destino + atleta.getNome()+ ".png";
                    System.out.println("origem =>" + origem);
                    System.out.println("destino =>" + destino);

                    copiarArquivos.copiar(lbCaminhoFoto.getText(), destino);
                    if (acao.equals("adicionar")) {
                        controle.adicionar(atleta);
                    
                    }else{
                        atleta.setFoto(temFoto);
                        controle.alterar(atleta, antigo);
                    }
                    
                    
                    botaosalvar.setVisible(false);
                    digitanome.setEditable(true);
                    digitanome.requestFocus();
                    digitanome.setText("");
                    digitaTipoAlien.setText("");
                    digitaaltura.setText("");
                    digitapeso.setText("");
                    
                    digitadata.setText("");
                    digitaTipoAlien.setEditable(false);
                    digitaaltura.setEditable(false);
                    digitapeso.setEditable(false);
                    planetaDoAlien.setEnabled(false);
                    digitadata.setEditable(false);
                    botaobuscar.setVisible(true);
                    btAdicionarFoto.setVisible(false);
                    btRemoverFoto.setVisible(false);
                    ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/simbolo_lp_4_bim.png", tamX, tamY);
                    lbFoto.setIcon(ii);
                    lbCaminhoFoto.setText(dirApp + "/src/fotos/simbolo_lp_4_bim.png");
                    if (cont==0) {
                        play("zsalvar");
                    }
                    
                } catch (Exception ii) {
                    if (cont==0) {
                        play("zerro");
                    }
                    
                    btAdicionarFoto.setVisible(true);
                    btRemoverFoto.setVisible(true);
                    JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                    botaocancelar.setVisible(true);                   
                    digitaaltura.setText("");
                    digitapeso.setText("");
                    digitadata.setText("");
                    botaolistar.setVisible(false);
                }
                

                }   
        });
                
        botaoalterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (cont==0) {
                    play("zaaa");
                }
                btcriarpdf.setVisible(false);
                btAdicionarFoto.setVisible(true);
                btRemoverFoto.setVisible(true);
                digitaaltura.setText(String.valueOf(atleta.getAltura()));
                digitapeso.setText(String.valueOf(atleta.getPeso()));
                botaolistar.setVisible(false);                
                botaoexcluir.setVisible(false);
                botaobuscar.setVisible(false);
                botaoalterar.setVisible(false);
                digitanome.setEditable(false);
                digitaTipoAlien.setEditable(true);
                digitaTipoAlien.requestFocus();
                digitaaltura.setEditable(true);
                digitapeso.setEditable(true);
                planetaDoAlien.setEnabled(true);
                digitadata.setEditable(true);
                botaosalvar.setVisible(true);
                botaocancelar.setVisible(true);
                acao = "alterar";

            }
        });        
        
        botaoexcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (cont==0) {
                    play("zaaa");
                }
                btcriarpdf.setVisible(false);
                int resposta = JOptionPane.showConfirmDialog (cp, "Deseja mesmo excluir?" , "Confirmar" ,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                botaoexcluir.setVisible(false);
                botaoalterar.setVisible(false);
                digitanome.setEditable(true);
                digitanome.requestFocus();
                digitanome.setText("");
                digitaTipoAlien.setText("");
                digitaaltura.setText("");
                digitapeso.setText("");
                digitadata.setText("");
                digitaTipoAlien.setEditable(false);
                digitaaltura.setEditable(false);
                digitapeso.setEditable(false);
                planetaDoAlien.setEnabled(false);
                digitadata.setEditable(false);
                botaobuscar.setVisible(true);
                
                String cc = lbCaminhoFoto.getText();
                //  System.out.println("arq > " +cc );
                File oArquivo = new File(cc.trim());
                if (oArquivo.exists()) {
                   // System.out.println(oArquivo.getAbsolutePath());
                    oArquivo.delete();//exclui a foto
                    origem = dirApp + "/src/fotos/simbolo_lp_4_bim.png";
                    ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/simbolo_lp_4_bim.png", tamX, tamY);
                    lbFoto.setIcon(ii);
                } else {
                    System.out.println("não achou");
                }
                
                if (resposta == JOptionPane.YES_OPTION) {                                                 
                    controle.excluir(atleta);
                }else{
                    System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");
                    
                }
                
            }
        });
        
        
        botaolistar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                List<Atleta> listaalteta = controle.listar();
                
                String [] colunas = new String[] {" NOME"," TIPO","ALTURA","PESO","PLANETA","FOTOS","APARIÇÃO"};
                Object[][] dados = new Object[listaalteta.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaalteta.size(); i++) {
                    aux = listaalteta.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(painelsul, "listagem");
                scrolltabela.setPreferredSize(tabela.getPreferredSize());
                pnlistagem.add(scrolltabela);
                scrolltabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);
                
                
                
                
            }
        });
        
        botaocancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (cont==0) {
                    play("zerro");
                }
                btAdicionarFoto.setVisible(false);
                btRemoverFoto.setVisible(false);
                botaocancelar.setVisible(false);
                digitanome.setEditable(true);
                digitanome.requestFocus();
                digitanome.setText("");
                digitaTipoAlien.setText("");
                digitaaltura.setText("");
                digitapeso.setText("");
                digitadata.setText("");
                digitaTipoAlien.setEditable(false);
                digitaaltura.setEditable(false);
                digitapeso.setEditable(false);
                planetaDoAlien.setEnabled(false);
                digitadata.setEditable(false);
                botaobuscar.setVisible(true);
                botaolistar.setVisible(true);
                botaosalvar.setVisible(false);
                origem = dirApp + "/src/fotos/simbolo_lp_4_bim.png";
                ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/simbolo_lp_4_bim.png", tamX, tamY);
                lbFoto.setIcon(ii);

            }
        });        
        
        // sobre os listeners do botão
        btAdicionarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                    File img = fc.getSelectedFile();
                    origem = fc.getSelectedFile().getAbsolutePath();

                    try {
                        lbFoto.setIcon(imagemAjustada.getImagemAjustada(origem, tamX, tamY));
                        lbCaminhoFoto.setText(origem);
                        temFoto = "Sim";
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(cp, "Erro ao carregar a imagem");
                    }
                }
            }
        });

        btRemoverFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirma a remoção da foto?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    //excluir a foto
                    System.out.println("arq > " + lbCaminhoFoto.getText());
                    File oArquivo = new File(lbCaminhoFoto.getText().trim());
                    if (oArquivo.exists()) {
                        new File(lbCaminhoFoto.getText()).delete();//exclui a foto
                        origem = dirApp + "/src/fotos/simbolo_lp_4_bim.png";
                        ImageIcon ii = imagemAjustada.getImagemAjustada(dirApp + "/src/fotos/simbolo_lp_4_bim.png", tamX, tamY);
                        lbFoto.setIcon(ii);
                        temFoto = "Não";
                    }
                }
            }
        });
        
        btcriarpdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (cont==0) {
                    play("rapaz");
                }
                ciclo = ciclo+1;
                System.out.println("aumenta"+ ciclo);
                btcriarpdf.setBackground(Color.BLACK);
                if (ciclo%2==0) {
                    btcriarpdf.setBackground(Color.BLACK);
                } else {
                    btcriarpdf.setBackground(Color.WHITE);
                }
                
                //pro pdf
                String pdfNome = digitanome.getText();
                String pdfTipo = digitaTipoAlien.getText();
                String pdfAltura= digitaaltura.getText();
                String pdfPeso = digitapeso.getText();
                String pdfPlaneta = String.valueOf(planetaDoAlien.getSelectedItem());                       
                String pdfAparicao = digitadata.getText();
                System.out.println(pdfNome+"aaaaaa");
                //fim dos dados
                try {
                    if (ciclo == 2 ) {
                        
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(
                        "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\trabalho_crud_lp2_quarto_bimestre\\src\\armario_de_PDF\\"+pdfNome+".pdf"));
                        document.open(); // adicionando um parágrafo no documento 

                        document.setPageSize(PageSize.A4);

                    
                        com.itextpdf.text.Font f = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16, com.itextpdf.text.Font.BOLD);
                        Paragraph p1 = new Paragraph(
                        "Dados do Alien : "+pdfNome+" , do CRUD do Marcão.", f);

                        p1.setAlignment(Element.ALIGN_CENTER);
                        p1.setSpacingAfter(20);
                        document.add(new Paragraph(p1));
                  
                        document.add(new Paragraph("  "));

                
                        Image figura = Image.getInstance(
                        "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\trabalho_crud_lp2_quarto_bimestre\\src\\fotos\\"+pdfNome+".png");
                        figura.setAlignment(Element.ALIGN_CENTER);
                        figura.scaleAbsolute(300, 300);
                        figura.setSpacingBefore(20);
                        document.add(figura);
                        PdfPTable table = new PdfPTable(new float[]{0.6f, 0.4f}); // colunas com larguras diferentes em %

                        table.setWidthPercentage(100.0f);
                        table.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.setSpacingBefore(20);
                        PdfPCell header = new PdfPCell();

                        header.setColspan(2);
                        com.itextpdf.text.Font hfonte = new com.itextpdf.text.Font(FontFamily.HELVETICA, 16, com.itextpdf.text.Font.BOLD);
                        Paragraph h = new Paragraph("       Dados do Alien.",hfonte);
                        h.setAlignment(Element.ALIGN_CENTER);
                        header.addElement(h);

                        table.addCell(header);           
                        table.addCell(" NOME ");
                        table.addCell(pdfNome);
                        table.addCell(" TIPO");
                        table.addCell(pdfTipo);
                        table.addCell(" ALTURA");
                        table.addCell(pdfAltura);
                        table.addCell(" PESO");
                        table.addCell(pdfPeso);
                        table.addCell(" Planeta Natal");
                        table.addCell(pdfPlaneta);
                        table.addCell(" Primeiro contato com a terra");
                        table.addCell(pdfAparicao);
                        document.add(table);
                        document.close();
                        JOptionPane.showMessageDialog(rootPane, "O PDF foi criado");
                        
                    } 
                    if(ciclo > 2) {
                        int resposta = JOptionPane.showConfirmDialog (cp, "criar mais um?" , "Você já tem um pdf assim" ,
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        
                        if (resposta == JOptionPane.YES_OPTION) {   
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(
                        "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\trabalho_crud_lp2_quarto_bimestre\\src\\armario_de_PDF\\"
                        +pdfNome
                        +"_"
                        +String.valueOf(ciclo-1)
                        +".pdf"));
                        document.open(); // adicionando um parágrafo no documento 

                        document.setPageSize(PageSize.A4);

                    
                        com.itextpdf.text.Font hfonte = new com.itextpdf.text.Font(FontFamily.HELVETICA, 16, com.itextpdf.text.Font.BOLD);
                        com.itextpdf.text.Font f = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 16, com.itextpdf.text.Font.BOLD);
                        Paragraph p1 = new Paragraph(
                        "Dados do Alien : "+pdfNome+" , do CRUD do Marcão.", f);

                        p1.setAlignment(Element.ALIGN_CENTER);
                        p1.setSpacingAfter(20);
                        document.add(new Paragraph(p1));
                  
                        document.add(new Paragraph("  "));

                        
                        Image figura = Image.getInstance(
                        "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\trabalho_crud_lp2_quarto_bimestre\\src\\fotos\\" 
                        +pdfNome
                         
                        +".png");
                        figura.scaleAbsolute(300, 300);
                        figura.setAlignment(Element.ALIGN_CENTER);  
                        
                        document.add(figura);
                        PdfPTable table = new PdfPTable(new float[]{0.6f, 0.4f}); // colunas com larguras diferentes em %

                        table.setWidthPercentage(100.0f);
                        table.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.setSpacingBefore(20);
                        PdfPCell header = new PdfPCell();

                        header.setColspan(2);
                        Paragraph h = new Paragraph("       Dados do Alien.",hfonte);
                        h.setAlignment(Element.ALIGN_CENTER);
                        header.addElement(h);

                        table.addCell(header);           
                        table.addCell(" NOME ");
                        table.addCell(pdfNome);
                        table.addCell(" TIPO");
                        table.addCell(pdfTipo);
                        table.addCell(" ALTURA");
                        table.addCell(pdfAltura);
                        table.addCell(" PESO");
                        table.addCell(pdfPeso);
                        table.addCell(" Planeta Natal");
                        table.addCell(pdfPlaneta);
                        table.addCell(" Primeiro contato com a terra");
                        table.addCell(pdfAparicao);
                        document.add(table);
                        document.close();
                        
                        JOptionPane.showMessageDialog(rootPane, "Você criou o PDF :)");
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Você não criou o PDF :(");
                            ciclo = ciclo - 1;
                        }
                    }                 
                } catch (Exception errou) {
                    JOptionPane.showMessageDialog(rootPane, "ERRO: PDF não criado");
                    System.out.println(errou);
                }
                
                

            }
        });        
        
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void  windowClosing(WindowEvent e){
                controle.gravarLista(caminho);
                dispose();
            }        
        });

        setModal(true);
        setSize(1220,640);        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void play(String nomemusica){
        URL url = getClass().getResource(nomemusica+".wav");
        AudioClip audio = Applet.newAudioClip(url);
        
        audio.play();
    }
}
