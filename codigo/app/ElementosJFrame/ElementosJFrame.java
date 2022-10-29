package ElementosJFrame;


import javax.swing.*;
import java.awt.event.*;

public class ElementosJFrame {
    
    public static JLabel label (String mensagem){
        return new JLabel(mensagem);
    }
    public static JButton button(String msg, ActionListener funcao){
        JButton button = new JButton(msg);
        
        button.addActionListener(funcao);
        return  button;
    }
}
