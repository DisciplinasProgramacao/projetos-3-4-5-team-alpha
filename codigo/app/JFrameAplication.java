import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

    public class JFrameAplication extends JFrame {
        
        //private static JLabel label2 = new JLabel("Outra coisa!");
        private static JButton buttonAddVeiculo = new JButton("Inserir veículo");
        private static JLabel labelPlaca = new JLabel("Placa: ");
        private static JTextField entradaPlaca = new JTextField(25);
        private static JLabel labelTanque = new JLabel("Tanque: ");
        private static JTextField entradaTanque = new JTextField(25);
        


        public static void main(String[] args) {
    
            JFrameAplication window = new JFrameAplication();
            window.setSize(500,500);
            window.setVisible(true);
            window.setTitle("Mãos Atadas");
    
            window.setLayout(new FlowLayout());
            
            window.getContentPane().add(buttonAddVeiculo);
            
            buttonAddVeiculo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    buttonClick(e); 
                    
                }
            });

            //Objetos swing 
        
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        }//Fim da main
        public static void buttonClick (ActionEvent e){
            //JOptionPane.showMessageDialog(null, "Botão funciona", "teste", JOptionPane.INFORMATION_MESSAGE);
            JFrameAplication AddVeiculoPage = new JFrameAplication();
            AddVeiculoPage.setSize(500,500);
            AddVeiculoPage.setVisible(true);
            AddVeiculoPage.setTitle("Adicionar Veículo");


            
            JPanel formulario = new JPanel();
            formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
            formulario.add(labelPlaca);
            formulario.add(entradaPlaca);
            formulario.add(labelTanque);
            formulario.add(entradaTanque);
            AddVeiculoPage.add(formulario);

            


        }
    }

