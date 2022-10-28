import javax.swing.*;

import ElementosJFrame.ElementosJFrame;
import business.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class JFrameAplication extends JFrame {

    public static Frota frota = new Frota();

    // Variavéis
    private static final int TANQUE_VAN = 60;
    private static final int TANQUE_FURGAO = 80;

    // private static JLabel label2 = new JLabel("Outra coisa!");

    // Componentes Menu
    private static JButton buttonAddRota = new JButton("Incluir rota");
    private static JButton buttonAddLocalizar = new JButton("Localizar veículo");

    // Componentes tela inserir veículo
    private static JButton buttonEnviaVeiculoNovo = new JButton("Salvar");
    static JButton teste = ElementosJFrame.button("Inserir veículo", new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            formVeiculo(e);
        }
    });
    private static JTextField entradaPlaca = new JTextField(30);
    private static JTextField entradaAutonomia = new JTextField(30);
    private static JTextField entradaVenda = new JTextField(30);
    private static String[] tiposVeiculo = { "Carro", "Caminhão", "Van", "Furgão" };
    private static JComboBox tipoVeiculo = new JComboBox<String>(tiposVeiculo);

    // Componentes tela localizar veículo
    private static JTextField entradaPlacaLocalizar = new JTextField(30);
    private static JButton buttonLocalizaVeiculo = new JButton("Localizar");

    public static void main(String[] args) {

        // Config janela
        JFrameAplication window = new JFrameAplication();
        window.setSize(500, 500);
        window.setVisible(true);
        window.setTitle("Frota de veículos");

        window.setLayout(new FlowLayout());

        // Botões do Menu
        window.getContentPane().add(ElementosJFrame.button("Inserir veículo", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formVeiculo(e);
            }
        }));
        window.getContentPane().add(buttonAddLocalizar);
        window.getContentPane().add(buttonAddRota);
        window.getContentPane().add(ElementosJFrame.button("Listar veículos", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarVeiculos(e);
            }
        }));

        // Funções Menu

        buttonAddLocalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formLocalizar(e);
            }
        });

        buttonAddRota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formRota(e);
            }
        });

        // Objetos swing

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }// Fim da main

    // Funções de formulário

    public static void formVeiculo(ActionEvent e) {
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
        formulario.add(tipoVeiculo);
        formulario.add(ElementosJFrame.label("Autonomia(km/l):"));
        formulario.add(entradaAutonomia);
        formulario.add(ElementosJFrame.label("Valor de venda:"));
        formulario.add(entradaVenda);
        formulario.add(buttonEnviaVeiculoNovo);

        AddVeiculoPage.add(formulario);

        AddVeiculoPage.pack();

        buttonEnviaVeiculoNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarVeiculo();
                AddVeiculoPage.dispose();
            }
        });

    }

    public static void formRota(ActionEvent e) {
        JFrameAplication AddRotaPage = new JFrameAplication();
        AddRotaPage.setSize(500, 500);
        AddRotaPage.setVisible(true);
        AddRotaPage.setTitle("Incluir Rota");

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.add(ElementosJFrame.label("Digite o número da placa:"));
        formulario.add(entradaPlaca);
        formulario.add(ElementosJFrame.button("Adicionar rota", new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    if (frota.localizar(entradaPlaca.getText()) != null) {

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
                        formulario1.add(ElementosJFrame.button("Confirmar", new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Rota rota = new Rota(placa,
                                        LocalDate.of(Integer.parseInt(entradaData.getText().split("/")[2]),
                                                Integer.parseInt(entradaData.getText().split("/")[1]),
                                                Integer.parseInt(entradaData.getText().split("/")[0])),
                                        Integer.parseInt(entradaDistancia.getText()));
                                try {
                                    frota.localizar(entradaPlaca.getText()).setRota(rota);
                                    AddRotaPage.dispose();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }));
                        AddRotaPage.add(formulario1);
                        AddRotaPage.pack();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }));

        AddRotaPage.add(formulario);

        AddRotaPage.pack();
    }

    public static void formLocalizar(ActionEvent e) {
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

        buttonLocalizaVeiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                localizarVeiculo(e);
            }
        });
    }

    // Funções de instanciação

    public static void criarVeiculo() {
        String placa = entradaPlaca.getText();
        float autonomia = Float.parseFloat(entradaAutonomia.getText());
        float valor_venda = Float.parseFloat(entradaVenda.getText());
        String tipo = ((String) tipoVeiculo.getSelectedItem());
        if (tipo.compareTo("Carro") == 0) {
            Carro carro = new Carro(placa, autonomia, valor_venda);
            frota.inserirVeiculo(carro);
        } else if (tipo.compareTo("Caminhão") == 0) {
            Caminhao caminhao = new Caminhao(placa, autonomia, valor_venda);
            frota.inserirVeiculo(caminhao);
        } else if (tipo.compareTo("Van") == 0) {
            try {
                Utilitario van = new Utilitario(placa, tipo, TANQUE_VAN, autonomia, valor_venda);
                frota.inserirVeiculo(van);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tipo.compareTo("Furgão") == 0) {
            try {
                Utilitario furgao = new Utilitario(placa, tipo, TANQUE_FURGAO, autonomia, valor_venda);
                frota.inserirVeiculo(furgao);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void localizarVeiculo(ActionEvent e) {
        String placa = entradaPlacaLocalizar.getText();
        System.out.println(placa);
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
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void listarVeiculos(ActionEvent e) {
        JFrameAplication ListagemVeiculos = new JFrameAplication();
        ListagemVeiculos.setSize(500, 500);
        ListagemVeiculos.setVisible(true);
        ListagemVeiculos.setTitle("Listagem de veículos");

        JPanel panel = new JPanel();

        for (Veiculo veiculo : frota.localizar()) {
            if (veiculo != null) {
                JLabel label = new JLabel(veiculo.toString());
                panel.add(label);
            }
        }
        ListagemVeiculos.add(panel);

        ListagemVeiculos.pack();
    }

}