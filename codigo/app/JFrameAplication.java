import javax.swing.*;

import ElementosJFrame.ElementosJFrame;
import business.*;
import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.veiculos.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.time.LocalDate;

public class JFrameAplication extends JFrame {
    private static Map<String, String[]> tiposCombustiveis = new HashMap<String, String[]>();

    private static void carregarTiposCombustivelPorVeiculos() {
        String combustivelCarro[] = { "Gasolina", "Etanol" },
                combustivelVan[] = { "Gasolina", "Diesel" },
                combustivelCaminhao[] = { "Diesel" },
                combustivelFurgao[] = { "Gasolina" };

        tiposCombustiveis.put("Carro", combustivelCarro);
        tiposCombustiveis.put("Van", combustivelVan);
        tiposCombustiveis.put("Furgão", combustivelFurgao);
        tiposCombustiveis.put("Caminhão", combustivelCaminhao);
    }

    private static String[] carregarTiposCombustivel(String selectVeiculo) {
        List<String> listaTiposCombustivel = new ArrayList<String>();

        for (String tipo : tiposCombustiveis.get(selectVeiculo)) {
            listaTiposCombustivel.add(tipo);
        }
        
        String[] arrayListCombustiveis = new String[listaTiposCombustivel.size()];

        return listaTiposCombustivel.toArray(arrayListCombustiveis);
    }

    private static final long serialVersionUID = 2L;

    public static Frota frota = new Frota();

    // Variavéis

    // private static JLabel label2 = new JLabel("Outra coisa!");

    // Componentes Menu
    private static JButton buttonAddRota = new JButton("Incluir rota");
    private static JButton buttonAddLocalizar = new JButton("Localizar veículo");
    private static JButton buttonSalvarArquivo = new JButton("Salvar no arquivo");
    private static JButton buttonCarregarArquivo = new JButton("Carregar arquivo");
    private static JButton buttonOrdenarCustos = new JButton("Ordenar veículos por custo decrescente");
    private static JButton buttonListarVeiculosComMaisRotas = new JButton("Veiculos com mais rotas");
    private static JButton buttonQuilometragemMediaRotas = new JButton("Media da quilometragem das rotas");

    // Componentes tela inserir veículo
    private static JButton buttonEnviaVeiculoNovo = new JButton("Salvar");
    private static JTextField entradaPlaca = new JTextField(30),
        entradaVenda = new JTextField(30);
    private static String[] tiposVeiculo = { "Carro", "Caminhão", "Van", "Furgão" };
    private static JComboBox<String> selectVeiculo = new JComboBox<String>(tiposVeiculo),
        selectCombustivel;

    // Componentes tela localizar veículo
    private static JTextField entradaPlacaLocalizar = new JTextField(30);
    private static JButton buttonLocalizaVeiculo = new JButton("Localizar");

    // Componentes tela carregar arquivo
    private static JButton buttonEnviaArquivoCarregar = new JButton("Carregar");
    private static JTextField entradaCarregarArquivo = new JTextField(30);

    // Componentes tela salvar arquivo
    private static JButton buttonEnviaArquivoSalvar = new JButton("Salvar");
    private static JTextField entradaSalvarArquivo = new JTextField(30);

    // Componentes tela procurarRota
    private static JTextField entradaProcurarRota = new JTextField(10);

    // Componente tela addCustoImprevisto
    private static JTextField entradaAddCustoImprevisto = new JTextField(30);

    public static void main(String[] args) {
        // Config janela
        JFrameAplication window = new JFrameAplication();
        window.setSize(1000, 500);
        window.setVisible(true);
        window.setTitle("Frota de veículos");
        carregarTiposCombustivelPorVeiculos();

        window.setLayout(new FlowLayout());

        // Botões do Menu
        window.getContentPane().add(ElementosJFrame.button("Inserir veículo", (e) -> formVeiculo()));
        window.getContentPane().add(buttonAddLocalizar);
        window.getContentPane().add(buttonAddRota);
        window.getContentPane().add(ElementosJFrame.button("Listar veículos", (e) -> listarVeiculos()));
        window.getContentPane().add(buttonSalvarArquivo);
        window.getContentPane().add(buttonCarregarArquivo);
        window.getContentPane().add(buttonOrdenarCustos);
        window.getContentPane().add(buttonListarVeiculosComMaisRotas);
        window.getContentPane().add(buttonQuilometragemMediaRotas);
        window.getContentPane().add(ElementosJFrame.button("Procurar rotas", (e) -> formLocalizarRotasPorData()));
        window.getContentPane().add(ElementosJFrame.button("Adicionar custo", (e) -> formCustoInprevisto()));

        // Funções Menu
        buttonAddLocalizar.addActionListener((e) -> formLocalizar());
        buttonAddRota.addActionListener((e) -> formRota());
        buttonSalvarArquivo.addActionListener((e) -> formSalvarArquivo());
        buttonCarregarArquivo.addActionListener((e) -> formCarregarArquivo());
        buttonOrdenarCustos.addActionListener((e) -> ordenarCustosDecrescente() );
        buttonListarVeiculosComMaisRotas.addActionListener((e) -> listarVeiculosComMaisRotas());
        buttonQuilometragemMediaRotas.addActionListener((e) -> mediaQuilometragemRotas());

        // Objetos swing
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.revalidate();
        window.repaint();
    }// Fim da main

    // Funções de formulário

    public static void formVeiculo() {
        // JOptionPane.showMessageDialog(null, "Botão funciona", "teste",
        // JOptionPane.INFORMATION_MESSAGE);
        JFrameAplication AddVeiculoPage = new JFrameAplication();
        AddVeiculoPage.setSize(500, 500);
        AddVeiculoPage.setVisible(true);
        AddVeiculoPage.setTitle("Adicionar Veículo");

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.add(ElementosJFrame.label("Placa:"));
        formulario.add(entradaPlaca);
        formulario.add(selectVeiculo);

        selectCombustivel = new JComboBox<String>(carregarTiposCombustivel(selectVeiculo.getSelectedItem().toString()));
        selectVeiculo.addActionListener((e) -> {
            selectCombustivel.removeAllItems();

            for (String combustivel : carregarTiposCombustivel(selectVeiculo.getSelectedItem().toString())) {
                selectCombustivel.addItem(combustivel);
            }

            selectCombustivel.repaint();
        });

        formulario.add(selectCombustivel);
        formulario.add(ElementosJFrame.label("Valor de venda:"));
        formulario.add(entradaVenda);
        formulario.add(buttonEnviaVeiculoNovo);

        AddVeiculoPage.add(formulario);

        AddVeiculoPage.pack();
        AddVeiculoPage.setLocationRelativeTo(null);
        AddVeiculoPage.setVisible(true);

        buttonEnviaVeiculoNovo.addActionListener((e) -> {
            criarVeiculo();
            AddVeiculoPage.dispose();
            entradaPlaca.setText("");
            entradaVenda.setText("");
        });
    }

    public static void formRota() {
        JFrameAplication AddRotaPage = new JFrameAplication();
        AddRotaPage.setSize(500, 500);
        AddRotaPage.setVisible(true);
        AddRotaPage.setLocationRelativeTo(null);
        AddRotaPage.setTitle("Incluir Rota");

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.add(ElementosJFrame.label("Digite o número da placa:"));
        formulario.add(entradaPlaca);
        formulario.add(ElementosJFrame.button("Adicionar rota", (ev) -> {
            try {
                Veiculo found = frota.localizar(entradaPlaca.getText());

                if (found != null) {

                    String placa = entradaPlaca.getText();
                    AddRotaPage.remove(formulario);
                    JTextField entradaData = new JTextField(30);
                    JTextField entradaDistancia = new JTextField(30);
                    JPanel formulario1 = new JPanel();
                    formulario1.setLayout(new BoxLayout(formulario1, BoxLayout.Y_AXIS));
                    formulario1.add(ElementosJFrame.label("Data"));
                    formulario1.add(entradaData);
                    formulario1.add(ElementosJFrame.label("Distância"));
                    formulario1.add(entradaDistancia);
                    formulario1.add(ElementosJFrame.button("Confirmar", (e) -> {
                        String entrada = entradaData.getText();
                        int dia = Integer.parseInt(entrada.split("/")[0]),
                            mes = Integer.parseInt(entrada.split("/")[1]),
                            ano = Integer.parseInt(entrada.split("/")[2]);
                            
                        Rota rota = new Rota(placa,
                                LocalDate.of(ano, mes, dia),
                                Integer.parseInt(entradaDistancia.getText()));
                        
                        found.setRota(rota);
                        AddRotaPage.dispose();
                    }));

                    AddRotaPage.add(formulario1);
                    AddRotaPage.pack();
                }
            } catch (Exception e) {
                JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
                error.setVisible(true);
            }
        }));

        AddRotaPage.add(formulario);

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
        formulario.add(ElementosJFrame.button("Procurar rotas:", (e) -> ListarRotasPorData()));

        AddLocalizarRotaPage.add(formulario);

        AddLocalizarRotaPage.pack();
        AddLocalizarRotaPage.setLocationRelativeTo(null);
        AddLocalizarRotaPage.setVisible(true);
    }

    public static void formLocalizar() {
        JFrameAplication AddLocalizarPage = new JFrameAplication();
        AddLocalizarPage.setSize(500, 500);
        AddLocalizarPage.setVisible(true);
        AddLocalizarPage.setTitle("Localizar Veículo");

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.add(ElementosJFrame.label("Digite a placa do veículo a ser localizado: "));
        formulario.add(entradaPlacaLocalizar);
        formulario.add(buttonLocalizaVeiculo);

        AddLocalizarPage.add(formulario);

        AddLocalizarPage.pack();
        AddLocalizarPage.setLocationRelativeTo(null);
        AddLocalizarPage.setVisible(true);

        buttonLocalizaVeiculo.addActionListener((e) -> localizarVeiculo());
    }

    public static void formCarregarArquivo() {
        JFrameAplication AddCarregarArquivo = new JFrameAplication();
        AddCarregarArquivo.setSize(500, 500);
        AddCarregarArquivo.setVisible(true);
        AddCarregarArquivo.setTitle("Carregar frota de arquivo");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(ElementosJFrame.label("Digite o nome do arquivo para carregar os dados: "));
        panel.add(entradaCarregarArquivo);
        panel.add(buttonEnviaArquivoCarregar);

        AddCarregarArquivo.add(panel);

        AddCarregarArquivo.pack();
        AddCarregarArquivo.setLocationRelativeTo(null);
        AddCarregarArquivo.setVisible(true);

        buttonEnviaArquivoCarregar.addActionListener((e) -> {
            carregarArquivo();
            AddCarregarArquivo.dispose();
        });
    }

    public static void formSalvarArquivo() {
        JFrameAplication AddSalvarArquivo = new JFrameAplication();
        AddSalvarArquivo.setSize(500, 500);
        AddSalvarArquivo.setVisible(true);
        AddSalvarArquivo.setTitle("Salvar frota em arquivo");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(ElementosJFrame.label("Digite o nome do arquivo para salvar os dados: "));
        panel.add(entradaSalvarArquivo);
        panel.add(buttonEnviaArquivoSalvar);

        AddSalvarArquivo.add(panel);

        AddSalvarArquivo.pack();
        AddSalvarArquivo.setLocationRelativeTo(null);
        AddSalvarArquivo.setVisible(true);

        buttonEnviaArquivoSalvar.addActionListener((e) -> { 
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
        formulario.add(ElementosJFrame.label("Digite o valor:"));
        formulario.add(entradaAddCustoImprevisto);
        
        formulario.add(ElementosJFrame.button("Adicionar", (e) -> {
            try {
                entradaPlacaLocalizar.repaint();
                Veiculo veiculo = frota.localizar(entradaPlacaLocalizar.getText());
                veiculo.addNovoCustoVariavel(Float.parseFloat(entradaAddCustoImprevisto.getText()));
                AddCustoInprevisto.dispose();
            } catch (Exception errFormCustoImprevisto) {
                JFrame error = ElementosJFrame.errorWindow("Error", errFormCustoImprevisto.getMessage());
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
        
        String placa = entradaPlaca.getText(),
            veiculoSelecionado = selectVeiculo.getSelectedItem().toString(),
            combustivelSelecionado = selectCombustivel.getSelectedItem().toString();
        
        Float valor_venda = Float.parseFloat(entradaVenda.getText());

        Combustivel selecionado = Combustivel.GASOLINA;
        
        if (combustivelSelecionado.equals(Combustivel.ETANOL.toString())) {
            selecionado = Combustivel.ETANOL;
        } else if (combustivelSelecionado.equals(Combustivel.DIESEL.toString())) {
            selecionado = Combustivel.DIESEL;
        }
        
        if(valor_venda > 0) {
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
                        minhaCapacidade = Capacidades.FURGAO;
                        Utilitario furgao = new Utilitario(placa, minhaCapacidade, selecionado, valor_venda);
                        frota.inserirVeiculo(furgao);

                        break;
                }
            } catch (Exception e) {
                String message = "O combustivel não pode ser negativo";

                JFrame error = ElementosJFrame.errorWindow("Error", message);
                error.setVisible(true);
            }
        } else {
            String message = "O valor de venda não pode ser negativo";

            JFrame error = ElementosJFrame.errorWindow("Error", message);
            error.setVisible(true);
        }
        
    }

    public static void localizarVeiculo() {
        String placa = entradaPlacaLocalizar.getText();
        try {
            Veiculo procurado = frota.localizar(placa);
            JLabel veiculoEncontrado = new JLabel(procurado.toString());
            JFrameAplication FrameVeiculoLocalizado = new JFrameAplication();
            JPanel panelVeiculoEncontrado = new JPanel();
            FrameVeiculoLocalizado.setSize(200, 200);
            FrameVeiculoLocalizado.setVisible(true);
            FrameVeiculoLocalizado.setTitle("Veículo encontrado");
            panelVeiculoEncontrado.add(veiculoEncontrado);

            FrameVeiculoLocalizado.add(panelVeiculoEncontrado);
            FrameVeiculoLocalizado.pack();
        } catch (Exception e) {
            JFrame error = ElementosJFrame.errorWindow("Error", e.getMessage());
            error.setVisible(true);
        }
    }

    public static void listarVeiculos() {
        JFrameAplication ListagemVeiculos = new JFrameAplication();
        ListagemVeiculos.setSize(500, 500);
        ListagemVeiculos.setVisible(true);
        ListagemVeiculos.setTitle("Listagem de veículos");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        int i = 1;
        for (Veiculo selecionado : frota.toArray()) {
            JLabel label = new JLabel(i++ + ". " + selecionado.toString());
            panel.add(label);
        }

        ListagemVeiculos.add(panel);
        ListagemVeiculos.pack();
        ListagemVeiculos.setLocationRelativeTo(null);
        ListagemVeiculos.setVisible(true);
    }

    public static void ListarRotasPorData() {
        JFrameAplication ListagemRotas = new JFrameAplication();
        ListagemRotas.setSize(500, 500);
        ListagemRotas.setVisible(true);
        ListagemRotas.setTitle("Listagem de rotas");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        int i = 1;
        LocalDate data = LocalDate.of(Integer.parseInt(entradaProcurarRota.getText().split("/")[2]),
                Integer.parseInt(entradaProcurarRota.getText().split("/")[1]),
                Integer.parseInt(entradaProcurarRota.getText().split("/")[0]));
        ArrayList<Rota> datasLocalizadas = frota.localizarRotasPorData(data);
        for (Rota selecionado : datasLocalizadas) {
            JLabel label = new JLabel(i++ + ". " + selecionado.toString());
            panel.add(label);
        }

        ListagemRotas.add(panel);
        ListagemRotas.pack();
    }

    public static void carregarArquivo() {
        String entradaNomeArquivo = entradaCarregarArquivo.getText();

        try {
            frota = carregar_arquivo(entradaNomeArquivo);
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

        int i = 1;
        for (Veiculo veiculo : veiculos) {
            JLabel label = new JLabel(i++ + ". " + veiculo.toString());
            panel.add(label);
        }

        ListagemVeiculos.add(panel);
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
        for (Veiculo veiculo : veiculos) {
            JLabel label = new JLabel(i++ + ". " + veiculo.toString());
            panel.add(label);
        }

        ListagemVeiculos.add(panel);
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
        JLabel label = new JLabel("A quilometragem média das rotas é: " + mediaQuilometragem + " km");
        panel.add(label);

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

            return  (Frota) inputFile.readObject();
        } catch (Exception e) {
            throw new Exception("Erro ao carregar \"" + filename + "\" do disco! Arquivo não encontrado");
        }
    }
}