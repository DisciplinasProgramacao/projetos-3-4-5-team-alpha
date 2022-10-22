package ElementosJFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

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
