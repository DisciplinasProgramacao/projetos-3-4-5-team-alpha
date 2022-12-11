import javax.swing.*;

import business.*;
import business.Enuns.*;
import business.veiculos.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.time.LocalDate;

public class JFrameAplication extends JFrame {

    private static final long serialVersionUID = 2L;
    
    // Variavéis
    public static Frota frota = new Frota();

    // Janela principal
    public static JFrameAplication window;

    // Componentes tela inserir veículo
    private static JTextField entradaPlaca = new JTextField(30),
            entradaVenda = new JTextField(30);
    private static String[] tiposVeiculo = { "Carro", "Caminhão", "Van", "Furgão" };
    private static JComboBox<String> selectVeiculo = new JComboBox<String>(tiposVeiculo),
            selectCombustivel;

    // Componentes tela localizar veículo
    private static JTextField entradaPlacaLocalizar = new JTextField(30);

    // Componentes tela carregar arquivo
    private static JTextField entradaCarregarArquivo = new JTextField(30);

    // Componentes tela salvar arquivo
    private static JTextField entradaSalvarArquivo = new JTextField(30);

    // Componentes tela procurarRota
    private static JTextField entradaProcurarRota = new JTextField(10);

    // Componente tela addCustoImprevisto
    private static JTextField entradaAddCustoImprevisto = new JTextField(30);
    private static JTextField entradaAddDescricaoCustoImprevisto = new JTextField(30);

    public static void main(String[] args) {
        // Config janela
        window = new JFrameAplication();
        window.setSize(1000, 500);
        window.setTitle("Frota de veículos");
        window.setLayout(new FlowLayout());

        // Botões do Menu
        
        // button add Veiculo
        window.getContentPane().add(ElementosJFrame.button("Inserir veículo", (e) -> formVeiculo()));

        // button Localizar veículo
        window.getContentPane().add(ElementosJFrame.button("Localizar veículo", (e) -> formLocalizar()));

        // button Incluir Rota
        window.getContentPane().add(ElementosJFrame.button("Incluir rota", (e) -> formRota()));
        
        // button listar veículos
        window.getContentPane().add(ElementosJFrame.button("Listar veículos", (e) -> listarVeiculos()));
        
        // button Salvar no aquivo
        window.getContentPane().add(ElementosJFrame.button("Salvar no arquivo", (e) -> formSalvarArquivo()));

        // button Carregar arquivo
        window.getContentPane().add(ElementosJFrame.button("Carregar arquivo", (e) -> formCarregarArquivo()));

        // button Ordernar veículos por custo decrescente
        window.getContentPane().add(ElementosJFrame.button("Ordenar veículos por custo decrescente", (e) -> ordenarCustosDecrescente()));

        // button Veiculos com mais rotas
        window.getContentPane().add(ElementosJFrame.button("Veiculos com mais rotas", (e) -> listarVeiculosComMaisRotas()));

        // button Media da quilometragem das rotas
        window.getContentPane().add(ElementosJFrame.button("Media da quilometragem das rotas", (e) -> mediaQuilometragemRotas()));

        // button Procurar Rotas pela Data
        window.getContentPane().add(ElementosJFrame.button("Procurar rotas", (e) -> formLocalizarRotasPorData()));

        // button Adicionar custo adicional
        window.getContentPane().add(ElementosJFrame.button("Adicionar custo adicional", (e) -> formCustoInprevisto()));
        

        // Objetos swing
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.revalidate();
        window.repaint();
    }// Fim da main

    // Funções de formulário

    public static void formVeiculo() {
        JButton button = new JButton("Salvar");

        JFrameAplication AddVeiculoPage = new JFrameAplication();
        AddVeiculoPage.setSize(500, 500);
        AddVeiculoPage.setVisible(true);
        AddVeiculoPage.setTitle("Adicionar Veículo");

        entradaPlaca.setText("");
        entradaVenda.setText("");

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.add(ElementosJFrame.label("Placa:"));
        formulario.add(entradaPlaca);
        formulario.add(selectVeiculo);

        Capacidades capacidadeSelecionada = Capacidades.valueOf(selectVeiculo.getSelectedItem().toString().toUpperCase());

        selectCombustivel = new JComboBox<String>(capacidadeSelecionada.getCombustiveisStringArray());
        selectVeiculo.addActionListener((e) -> {
            selectCombustivel.removeAllItems();

            String veiculoSelecionadoString = selectVeiculo.getSelectedItem().toString();
            Capacidades capacidadeSelecionada2 = Capacidades.valueOf(veiculoSelecionadoString.toUpperCase());

            for (String combustivel : capacidadeSelecionada2.getCombustiveisStringArray()) {
                if(combustivel != null) {
                    selectCombustivel.addItem(combustivel);
                }
            }

            selectCombustivel.repaint();
        });

        formulario.add(selectCombustivel);
        formulario.add(ElementosJFrame.label("Valor de venda:"));
        formulario.add(entradaVenda);
        formulario.add(button);

        AddVeiculoPage.add(formulario);

        AddVeiculoPage.pack();
        AddVeiculoPage.setLocationRelativeTo(null);
        AddVeiculoPage.setVisible(true);

        button.addActionListener((e) -> {
            criarVeiculo();
            AddVeiculoPage.dispose();
            entradaPlaca.setText("");
            entradaVenda.setText("");
        });
    }

    public static void formRota() {
        JFrameAplication AddRotaPage = new JFrameAplication();
        AddRotaPage.setSize(500, 500);
        AddRotaPage.setLocationRelativeTo(null);
        AddRotaPage.setTitle("Incluir Rota");

        entradaPlaca.setText("");

        JPanel formularioPlaca = new JPanel();
        formularioPlaca.setLayout(new BoxLayout(formularioPlaca, BoxLayout.Y_AXIS));
        formularioPlaca.add(ElementosJFrame.label("Digite o número da placa:"));
        formularioPlaca.add(entradaPlaca);
        formularioPlaca.add(ElementosJFrame.button("Adicionar rota", (ev) -> {
            try {
                String placa = entradaPlaca.getText();
                Veiculo found = frota.localizar(placa);

                if (found != null) {
                    AddRotaPage.remove(formularioPlaca);

                    Date data = new Date();
                    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                    String dataAtualString = formatador.format(data);

                    JTextField entradaData = new JTextField(30);
                    JTextField entradaDistancia = new JTextField(30);

                    entradaData.setText(dataAtualString);
                    entradaDistancia.setText("0");

                    JPanel formularioRota = new JPanel();
                    formularioRota.setLayout(new BoxLayout(formularioRota, BoxLayout.Y_AXIS));

                    formularioRota.add(new JLabel("Autononomia do(a) " + found.tipoVeiculo() + " (" + placa + "): " +
                        String.format("%.02f", found.getAutonomiaMaxima()) + "km"));

                    formularioRota.add(new JLabel(" "));
                    formularioRota.add(ElementosJFrame.label("Data"));
                    formularioRota.add(entradaData);
                    formularioRota.add(ElementosJFrame.label("Distância"));
                    formularioRota.add(entradaDistancia);
                    formularioRota.add(ElementosJFrame.button("Confirmar", (e) -> {
                        AddRotaPage.dispose();

                        String entrada = entradaData.getText();
                        int dia = Integer.parseInt(entrada.split("/")[0]),
                                mes = Integer.parseInt(entrada.split("/")[1]),
                                ano = Integer.parseInt(entrada.split("/")[2]);

                        try {
                            Rota rota = new Rota(placa,
                                LocalDate.of(ano, mes, dia),
                                Integer.parseInt(entradaDistancia.getText()));

                            found.addRota(rota);

                        } catch (ArithmeticException f) {
                            JFrame error = ElementosJFrame.errorWindow("Error", f.getMessage());
                            error.setVisible(true);

                        }catch(NumberFormatException e1){
                            JFrame error = ElementosJFrame.errorWindow("Error", "Distância é irreal");
                            error.setVisible(true);
                        }
                    }));

                    AddRotaPage.add(formularioRota);
                    AddRotaPage.pack();
                }
            } catch (NoSuchFieldException e) {
                JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
                error.setVisible(true);
            }
        }));

        AddRotaPage.add(formularioPlaca);
        AddRotaPage.setVisible(true);
        AddRotaPage.pack();
    }

    public static void formLocalizarRotasPorData() {
        JFrameAplication AddLocalizarRotaPage = new JFrameAplication();
        AddLocalizarRotaPage.setSize(500, 500);
        AddLocalizarRotaPage.setVisible(true);
        AddLocalizarRotaPage.setTitle("Procurar rota");

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.add(ElementosJFrame.label("Digite a data:"));
        formulario.add(entradaProcurarRota);
        formulario.add(ElementosJFrame.button("Procurar rotas:", (e) ->  {
            ListarRotasPorData();
            AddLocalizarRotaPage.dispose();
        }));

        AddLocalizarRotaPage.add(formulario);

        AddLocalizarRotaPage.pack();
        AddLocalizarRotaPage.setLocationRelativeTo(null);
        AddLocalizarRotaPage.setVisible(true);
    }

    public static void formLocalizar() {
        JButton button = new JButton("Localizar");
        entradaPlacaLocalizar.setText("");

        JFrameAplication AddLocalizarPage = new JFrameAplication();
        AddLocalizarPage.setSize(500, 500);
        AddLocalizarPage.setVisible(true);
        AddLocalizarPage.setTitle("Localizar Veículo");

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.add(ElementosJFrame.label("Digite a placa do veículo a ser localizado: "));
        formulario.add(entradaPlacaLocalizar);
        formulario.add(button);

        AddLocalizarPage.add(formulario);

        AddLocalizarPage.pack();
        AddLocalizarPage.setLocationRelativeTo(null);
        AddLocalizarPage.setVisible(true);

        button.addActionListener((e) -> {
            localizarVeiculo();
            AddLocalizarPage.dispose();
            entradaPlacaLocalizar.setText("");
        });
    }

    public static void formCarregarArquivo() {
        JButton button = new JButton("Carregar");

        entradaCarregarArquivo.setText("");

        JFrameAplication AddCarregarArquivo = new JFrameAplication();
        AddCarregarArquivo.setSize(500, 500);
        AddCarregarArquivo.setVisible(true);
        AddCarregarArquivo.setTitle("Carregar frota de arquivo");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(ElementosJFrame.label("Digite o nome do arquivo para carregar os dados: "));
        panel.add(entradaCarregarArquivo);
        panel.add(button);

        AddCarregarArquivo.add(panel);

        AddCarregarArquivo.pack();
        AddCarregarArquivo.setLocationRelativeTo(null);
        AddCarregarArquivo.setVisible(true);

        button.addActionListener((e) -> {
            carregarArquivo();
            AddCarregarArquivo.dispose();
            System.out.println(frota.toArray().length);
            window.repaint();
        });
    }

    public static void formSalvarArquivo() {
        JButton button = new JButton("Salvar");
    
        JFrameAplication AddSalvarArquivo = new JFrameAplication();
        AddSalvarArquivo.setSize(500, 500);
        AddSalvarArquivo.setVisible(true);
        AddSalvarArquivo.setTitle("Salvar frota em arquivo");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(ElementosJFrame.label("Digite o nome do arquivo para salvar os dados: "));
        panel.add(entradaSalvarArquivo);
        panel.add(button);

        AddSalvarArquivo.add(panel);

        AddSalvarArquivo.pack();
        AddSalvarArquivo.setLocationRelativeTo(null);
        AddSalvarArquivo.setVisible(true);

        button.addActionListener((e) -> {
            salvarArquivo();
            AddSalvarArquivo.dispose();
        });
    }

    // Form para adição de custo não previsto
    public static void formCustoInprevisto() {
        JFrameAplication AddCustoInprevisto = new JFrameAplication();
        AddCustoInprevisto.setSize(500, 500);
        AddCustoInprevisto.setTitle("Adicionar custo imprevisto");

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));

        formulario.add(ElementosJFrame.label("Digite a placa do veículo:"));
        formulario.add(entradaPlacaLocalizar);
        formulario.add(ElementosJFrame.label("Digite uma descrição:"));
        formulario.add(entradaAddDescricaoCustoImprevisto);
        formulario.add(ElementosJFrame.label("Digite o valor:"));
        formulario.add(entradaAddCustoImprevisto);

        formulario.add(ElementosJFrame.button("Adicionar", (e) -> {
            try {
                entradaPlacaLocalizar.repaint();
                Veiculo veiculo = frota.localizar(entradaPlacaLocalizar.getText());
                String descricao = entradaAddDescricaoCustoImprevisto.getText();
                veiculo.addNovoCustoAdicional(descricao, Float.parseFloat(entradaAddCustoImprevisto.getText()));
                
                entradaPlacaLocalizar.setText("");
                entradaAddDescricaoCustoImprevisto.setText("");
                entradaAddCustoImprevisto.setText("");

                AddCustoInprevisto.dispose();

            } catch (NoSuchFieldException f) {
                JFrame error = ElementosJFrame.errorWindow("Error", f.getMessage());
                error.setVisible(true);
            }
        }));

        AddCustoInprevisto.add(formulario);

        AddCustoInprevisto.pack();
        AddCustoInprevisto.setLocationRelativeTo(null);
        AddCustoInprevisto.setVisible(true);
    }

    // Funções de instanciação

    public static void criarVeiculo() {
        if (entradaPlaca.getText().compareTo("") != 0 && Float.parseFloat(entradaVenda.getText()) != 0) {
            String placa = entradaPlaca.getText();
            Float valor_venda = Float.parseFloat(entradaVenda.getText());
            String veiculoSelecionado = selectVeiculo.getSelectedItem().toString(),
                    combustivelSelecionado = selectCombustivel.getSelectedItem().toString();
            Combustivel selecionado = Combustivel.GASOLINA;

            if (combustivelSelecionado.equals(Combustivel.ETANOL.toString())) {
                selecionado = Combustivel.ETANOL;
            } else if (combustivelSelecionado.equals(Combustivel.DIESEL.toString())) {
                selecionado = Combustivel.DIESEL;
            }

            try {
                Capacidades minhaCapacidade;

                switch (veiculoSelecionado) {
                    case "Carro":
                        Carro carro;
                        carro = new Carro(placa, selecionado, valor_venda);
                        frota.inserirVeiculo(carro);

                        break;

                    case "Caminhão":
                        Caminhao caminhao;
                        caminhao = new Caminhao(placa, selecionado, valor_venda);
                        frota.inserirVeiculo(caminhao);

                        break;

                    case "Van":
                        minhaCapacidade = Capacidades.VAN;
                        Utilitario van = new Utilitario(placa, minhaCapacidade, selecionado, valor_venda);
                        frota.inserirVeiculo(van);

                        break;

                    case "Furgão":
                        minhaCapacidade = Capacidades.FURGÃO;
                        Utilitario furgao = new Utilitario(placa, minhaCapacidade, selecionado, valor_venda);
                        frota.inserirVeiculo(furgao);

                        break;
                }
            } catch (NoSuchFieldException e) {
                JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
                error.setVisible(true);
            } catch (ArithmeticException e) {
                JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
                error.setVisible(true);
            } catch (IllegalArgumentException e) {
                JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
                error.setVisible(true);
            }
        }
    }

    public static void localizarVeiculo() {
        String placa = entradaPlacaLocalizar.getText();
        try {
            Veiculo procurado = frota.localizar(placa);
            JPanel veiculoLabel = ElementosJFrame.listarVeiculos(procurado.toString());
            JFrameAplication FrameVeiculoLocalizado = new JFrameAplication();

            JPanel panelVeiculoEncontrado = new JPanel();
            FrameVeiculoLocalizado.setSize(200, 200);
            FrameVeiculoLocalizado.setVisible(true);
            FrameVeiculoLocalizado.setTitle("Veículo encontrado");
            panelVeiculoEncontrado.add(veiculoLabel);

            FrameVeiculoLocalizado.add(panelVeiculoEncontrado);
            FrameVeiculoLocalizado.setLocationRelativeTo(null);
            FrameVeiculoLocalizado.pack();
        } catch (NoSuchFieldException e) {
            JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
            error.setVisible(true);
        }
    }

    public static void listarVeiculos() {
        JFrameAplication ListagemVeiculos = new JFrameAplication();
        ListagemVeiculos.setTitle("Listagem de veículos");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel veiculosLabel;
        int i = 1;
        for (Veiculo selecionado : frota.toArray()) {
            veiculosLabel = ElementosJFrame.listarVeiculos(i + ". " + selecionado.toString());
            panel.add(veiculosLabel);
            
            if(i++ < frota.toArray().length) {
                JLabel separador = new JLabel("--------------------");
                panel.add(separador);
            }
        }
        
        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(400, 300));
        
        ListagemVeiculos.add(scrollPanel);
        ListagemVeiculos.pack();
        ListagemVeiculos.setLocationRelativeTo(null);
        ListagemVeiculos.setVisible(true);
    }

    public static void ListarRotasPorData() {
        try {
            LocalDate data = LocalDate.of(Integer.parseInt(entradaProcurarRota.getText().split("/")[2]),
                Integer.parseInt(entradaProcurarRota.getText().split("/")[1]),
                Integer.parseInt(entradaProcurarRota.getText().split("/")[0]));
    
            List<Rota> datasLocalizadas = frota.localizarRotasPorData(data);
    
            JFrameAplication ListagemRotas = new JFrameAplication();
            ListagemRotas.setSize(500, 500);
            ListagemRotas.setTitle("Listagem de rotas");

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            
            JPanel rotaSelecionada;
            int i = 1;
            for (Rota selecionado : datasLocalizadas) {
                rotaSelecionada = ElementosJFrame.listarVeiculos(i + ". " + selecionado.toString());
                panel.add(rotaSelecionada);
    
                if(i++ < datasLocalizadas.size()) {
                    JLabel separador = new JLabel("--------------------");
                    panel.add(separador);
                }
            }
    
            JScrollPane scrollPanel = new JScrollPane(panel);
            scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPanel.setPreferredSize(new Dimension(400, 300));
    
            ListagemRotas.add(scrollPanel);
            ListagemRotas.setLocationRelativeTo(null);
            ListagemRotas.pack();
            ListagemRotas.setVisible(true);
            entradaProcurarRota.setText("");

        } catch(NoSuchFieldException e) {
            JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
            error.setVisible(true);
            entradaProcurarRota.setText("");
        }
    }

    public static void carregarArquivo() {
        String entradaNomeArquivo = entradaCarregarArquivo.getText();

        try {
            frota = carregar_arquivo(entradaNomeArquivo);
            frota.ordernarVeiculosComMaisRotas();
        } catch (Exception e) {
            JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
            error.setVisible(true);
        }
    }

    public static void salvarArquivo() {
        String entradaNomeArquivo = entradaSalvarArquivo.getText();

        try {
            salvar_arquivo(entradaNomeArquivo);
        } catch (Exception e) {
            JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
            error.setVisible(true);
        }
    }

    public static void ordenarCustosDecrescente() {
        JFrameAplication ListagemVeiculos = new JFrameAplication();
        ListagemVeiculos.setSize(500, 500);
        ListagemVeiculos.setVisible(true);
        ListagemVeiculos.setTitle("Veículos em ordem decrescente de custo");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos = frota.ordenarCustosDecrescentes();

        JPanel veiculosLabel;
        int i = 1;
        for (Veiculo veiculo : veiculos) {
            veiculosLabel = ElementosJFrame.listarVeiculos(i + ". " + veiculo.toString());
            panel.add(veiculosLabel);

            if(i++ < veiculos.size()) {
                JLabel separador = new JLabel("--------------------");
                panel.add(separador);
            }
        }

        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(400, 300));

        ListagemVeiculos.add(scrollPanel);
        ListagemVeiculos.pack();
        ListagemVeiculos.setLocationRelativeTo(null);
        ListagemVeiculos.setVisible(true);
    }

    public static void listarVeiculosComMaisRotas() {
        JFrameAplication ListagemVeiculos = new JFrameAplication();
        ListagemVeiculos.setSize(500, 500);
        ListagemVeiculos.setVisible(true);
        ListagemVeiculos.setTitle("Veículos em ordem decrescente de custo");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        int i = 1;
        veiculos = frota.veiculosComMaisRotas();

        JPanel veiculosLabel;
        for (Veiculo veiculo : veiculos) {
            veiculosLabel = ElementosJFrame.listarVeiculos(i + ". " + veiculo.toString());
            panel.add(veiculosLabel);

            if(i++ < veiculos.size()) {
                JLabel separador = new JLabel("--------------------");
                panel.add(separador);
            }
        }

        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(400, 300));

        ListagemVeiculos.add(scrollPanel);
        ListagemVeiculos.pack();
        ListagemVeiculos.setLocationRelativeTo(null);
        ListagemVeiculos.setVisible(true);
    }

    public static void mediaQuilometragemRotas() {
        JFrameAplication ListagemVeiculos = new JFrameAplication();
        ListagemVeiculos.setSize(500, 500);
        ListagemVeiculos.setVisible(true);
        ListagemVeiculos.setTitle("Veículos em ordem decrescente de custo");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        double mediaQuilometragem = frota.quilometragemMedia();
        if (!Double.isNaN(mediaQuilometragem)) {
            JLabel label = new JLabel("A quilometragem média das rotas é: " + mediaQuilometragem + " km");
            panel.add(label);
        } else {
            JLabel label = new JLabel("A quilometragem média das rotas é: " + 0 + " km");
            panel.add(label);
        }

        ListagemVeiculos.add(panel);
        ListagemVeiculos.pack();
        ListagemVeiculos.setLocationRelativeTo(null);
        ListagemVeiculos.setVisible(true);
    }

    public static void salvar_arquivo(String filename) throws Exception {
        String path = "app/arquivos/";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (ObjectOutputStream saidaVeiculos = new ObjectOutputStream(
                new FileOutputStream(path + filename + ".bin", false))) {

            saidaVeiculos.writeObject(frota);

            saidaVeiculos.flush();
        } catch (Exception e) {
            throw new Exception("ERRO ao salvar \"" + filename + "\" dados no disco!");
        }
    }

    public static Frota carregar_arquivo(String filename) throws Exception {
        String path = "app/arquivos/";
        File directory = new File(path);
        if (!directory.exists()) {
            throw new Exception("Arquivo não encontrado");
        }

        try (FileInputStream fis = new FileInputStream(path + filename + ".bin");
                ObjectInputStream inputFile = new ObjectInputStream(fis)) {

            return (Frota) inputFile.readObject();
        } catch (Exception e) {
            throw new Exception("Erro ao carregar \"" + filename + "\" do disco! Arquivo não encontrado");
        }
    }

    private static String[] ordernarVetorString(String[] vetor) {
        int i;
        String key;

        for (int j = 1; j < vetor.length; j++) {
            key = vetor[j];

            for (i = j - 1; (i >= 0) && (vetor[i].compareTo(key) > 0); i--) {
                vetor[i + 1] = vetor[i];
            }

            vetor[i + 1] = key;
        }

        return vetor;
    }
}