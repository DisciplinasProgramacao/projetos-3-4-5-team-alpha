import javax.swing.*;

import ElementosJFrame.ElementosJFrame;

import java.awt.*;
import java.awt.event.*;

import business.*;

public class JFrameAplication extends JFrame {

    // private static JLabel label2 = new JLabel("Outra coisa!");

    // Componentes Menu
    private static JButton buttonAddVeiculo = new JButton("Inserir veículo");
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
    private static JTextField entradaTanque = new JTextField(30);
    private static JTextField entradaAutonomia = new JTextField(30);
    private static JTextField entradaVenda = new JTextField(30);
    private static JTextField entradaPercentualIPVA = new JTextField(30);
    private static String[] tiposVeiculo = { "Caminhao", "Carro", "Utilitario" };
    private static JComboBox tipoVeiculo = new JComboBox<String>(tiposVeiculo);

    // Componentes tela localizar veículo
    private static JTextField entradaPlacaLocalizar = new JTextField(30);
    private static JButton buttonLocalizaVeiculo = new JButton("Localizar");

    //Componentes tela inserir rota
    private static JButton buttonInserirRota = new JButton("Localizar");

    public static void main(String[] args) {

        // Config janela
        JFrameAplication window = new JFrameAplication();
        window.setSize(500, 500);
        window.setVisible(true);
        window.setTitle("Mãos Atadas");

        window.setLayout(new FlowLayout());

        // Botões do Menu
        window.getContentPane().add(ElementosJFrame.button("Inserir veículo", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formVeiculo(e);
            }
        }));
        window.getContentPane().add(buttonAddLocalizar);
        window.getContentPane().add(buttonAddRota);

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
        formulario.add(ElementosJFrame.label("Tanque:"));
        formulario.add(entradaTanque);
        formulario.add(ElementosJFrame.label("Autonomia:"));
        formulario.add(entradaAutonomia);
        formulario.add(ElementosJFrame.label("Valor de venda:"));
        formulario.add(entradaVenda);
        formulario.add(ElementosJFrame.label("Percentual do IPVA:"));
        formulario.add(entradaPercentualIPVA);
        formulario.add(buttonEnviaVeiculoNovo);

        AddVeiculoPage.add(formulario);

        AddVeiculoPage.pack();

        buttonEnviaVeiculoNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarVeiculo();
            }
        });

        buttonLocalizaVeiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                localizarVeiculo(e);
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
        formulario.add(buttonInserirRota);

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
    }

    // Funções de instanciação

    public static void criarVeiculo() {
        String placa = entradaPlaca.getText();
        int tanque = Integer.parseInt(entradaTanque.getText());
        float autonomia = Float.parseFloat(entradaAutonomia.getText());
        float valor_venda = Float.parseFloat(entradaVenda.getText());
        float percental_ipva = Float.parseFloat(entradaPercentualIPVA.getText());
        String tipo = ((String) tipoVeiculo.getSelectedItem());
        System.out.println(
                placa + " " + tanque + " " + autonomia + " " + valor_venda + " " + percental_ipva + " " + tipo);
        if (tipo.compareTo("Carro") == 0) {
            Carro carro = new Carro(placa, tanque, autonomia, valor_venda);
        } else if (tipo.compareTo("Caminhao") == 0) {
            Caminhao caminhao = new Caminhao(placa, tanque, autonomia, valor_venda);
        } else if (tipo.compareTo("Utilitario") == 0) {

        }
    }

    public static void localizarVeiculo(ActionEvent e) {
       
       
    }

}