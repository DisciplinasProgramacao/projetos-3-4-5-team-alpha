import javax.swing.*;

import business.Frota;
import business.Carro;

public class JFrameAplication extends JFrame {
    public static void main(String[] args) {
        Frota frota = new Frota();

        for(int i = 100; i < 110; i++) {
            Carro carro = new Carro(Integer.toString(i), 50, 5000F, 5514.18F);
            frota.inserirVeiculo(carro);
        }

        try {
            frota.salvar_arquivo("teste");
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        JFrameAplication window = new JFrameAplication();
        window.setSize(500,500);
        window.setVisible(true);
        //Objetos swing 
    
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}

