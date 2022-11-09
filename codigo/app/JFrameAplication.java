import javax.swing.*;

import ElementosJFrame.ElementosJFrame;
import business.*;
import business.veiculos.Caminhao;
import business.veiculos.Carro;
import business.veiculos.Utilitario;
import business.veiculos.Veiculo;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import java.util.*;
import java.util.List;

public class JFrameAplication extends JFrame {

    public static Frota frota = new Frota();

    // Variavéis
    private static final int TANQUE_VAN = 60;
    private static final int TANQUE_FURGAO = 80;

    // private static JLabel label2 = new JLabel("Outra coisa!");

    // Componentes Menu
    private static JButton buttonAddRota = new JButton("Incluir rota");
    private static JButton buttonAddLocalizar = new JButton("Localizar veículo");
    private static JButton buttonSalvarArquivo = new JButton("Salvar no arquivo");
    private static JButton buttonCarregarArquivo = new JButton("Carregar arquivo");
    private static JButton buttonOrdenarCustos = new JButton("Ordenar veículos por custo decrescente");

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
    private static JComboBox<String> tipoVeiculo = new JComboBox<String>(tiposVeiculo);

    // Componentes tela localizar veículo
    private static JTextField entradaPlacaLocalizar = new JTextField(30);
    private static JButton buttonLocalizaVeiculo = new JButton("Localizar");

    // Componentes tela carregar arquivo
    private static JButton buttonEnviaArquivoCarregar = new JButton("Carregar");
    private static JTextField entradaCarregarArquivo = new JTextField(30);

    // Componentes tela salvar arquivo
    private static JButton buttonEnviaArquivoSalvar = new JButton("Salvar");
    private static JTextField entradaSalvarArquivo = new JTextField(30);

    public static void main(String[] args) {

        // Config janela
        JFrameAplication window = new JFrameAplication();
        window.setSize(1000, 500);
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
        window.getContentPane().add(buttonSalvarArquivo);
        window.getContentPane().add(buttonCarregarArquivo);
        window.getContentPane().add(buttonOrdenarCustos);

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

        buttonSalvarArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formSalvarArquivo(e);
            }
        });

        buttonCarregarArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formCarregarArquivo(e);
            }
        });

        buttonOrdenarCustos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ordenarCustosDecrescente(e);
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

    public static void formCarregarArquivo(ActionEvent e) {
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

        buttonEnviaArquivoCarregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarArquivo(e);
                AddCarregarArquivo.dispose();
            }
        });
    }

    public static void formSalvarArquivo(ActionEvent e) {
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

        buttonEnviaArquivoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarArquivo(e);
                AddSalvarArquivo.dispose();
            }
        });
    }

    // Funções de instanciação

    public static void criarVeiculo() {
        String placa = entradaPlaca.getText();
        float autonomia = Float.parseFloat(entradaAutonomia.getText());
        float valor_venda = Float.parseFloat(entradaVenda.getText());
        String tipo = ((String) tipoVeiculo.getSelectedItem());

        switch (tipo) {
            case "Carro":
                Carro carro = new Carro(placa, autonomia, valor_venda);
                frota.inserirVeiculo(carro);
                break;

            case "Caminhão":
                Caminhao caminhao = new Caminhao(placa, autonomia, valor_venda);
                frota.inserirVeiculo(caminhao);
                break;

            case "Van":
                try {
                    Utilitario van = new Utilitario(placa, tipo, TANQUE_VAN, autonomia, valor_venda);
                    frota.inserirVeiculo(van);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "Furgão":
                try {
                    Utilitario furgao = new Utilitario(placa, tipo, TANQUE_FURGAO, autonomia, valor_venda);
                    frota.inserirVeiculo(furgao);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void localizarVeiculo(ActionEvent e) {
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        int i = 1;
        for (Veiculo selecionado : frota.toArray()) {
            JLabel label = new JLabel(i++ + ". " + selecionado.toString());
            panel.add(label);
        }

        ListagemVeiculos.add(panel);
        ListagemVeiculos.pack();
    }

    public static void carregarArquivo(ActionEvent e) {
        String entradaNomeArquivo = entradaCarregarArquivo.getText();

        try {
            frota.carregar_arquivo(entradaNomeArquivo);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void salvarArquivo(ActionEvent e) {
        String entradaNomeArquivo = entradaSalvarArquivo.getText();

        try {
            frota.salvar_arquivo(entradaNomeArquivo);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void ordenarCustosDecrescente(ActionEvent e) {
        JFrameAplication ListagemVeiculos = new JFrameAplication();
        ListagemVeiculos.setSize(500, 500);
        ListagemVeiculos.setVisible(true);
        ListagemVeiculos.setTitle("Veículos em ordem decrescente de custo");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        int i = 1;
        veiculos = frota.ordenarCustosDecrescentes();
        for (Veiculo veiculo : veiculos) {
            JLabel label = new JLabel(i++ + ". " + veiculo.toString());
            panel.add(label);
        }

        ListagemVeiculos.add(panel);
        ListagemVeiculos.pack();
    }

}