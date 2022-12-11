import javax.swing.*;
import java.awt.event.*;

public class ElementosJFrame {
    
    public static JLabel label(String mensagem) {
        return new JLabel(mensagem);
    }

    public static JButton button(String msg, ActionListener funcao) {
        JButton button = new JButton(msg);
        
        button.addActionListener(funcao);
        return  button;
    }

    public static JFrame errorWindow(String title, String message) {
        JFrame error = new JFrame();
        error.setSize(500, 80);
        error.setLocationRelativeTo(null);
        error.setTitle(title);
        error.add(ElementosJFrame.label(message));

        return error;
    }

    public static JPanel listarVeiculos(String entrada) {
        JPanel panel = new JPanel();
        String[] atributos = entrada.split("#");
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel label;
        for(String atributo : atributos) {
            if(atributo.startsWith("&")) {
                atributo = atributo.substring(1);
                atributo = "        " + atributo;
            }

            if(atributo.startsWith("+")) {
                String tabSpace = "";
                int quant = Integer.parseInt(atributo.substring(1, 2));
                
                for(int i = 0; i < quant; i++) {
                    tabSpace += "        ";
                }
                
                atributo = atributo.substring(3);
                atributo = tabSpace + atributo;
            }
            
            label = new JLabel(atributo);
            panel.add(label);
        }

        return panel;
    }
}
