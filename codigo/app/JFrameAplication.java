import java.time.LocalDate;

import javax.swing.*;

import business.Frota;
import business.Rota;
import business.Veiculo;
import business.Carro;

public class JFrameAplication extends JFrame {
    public static void main(String[] args) {
        Frota frota = new Frota();

        for(int i = 100; i < 110; i++) {
            Carro carro = new Carro(Integer.toString(i), 50, 5000F, 5514.18F);
            frota.inserirVeiculo(carro);
        }

        for(int i = 100; i < 110; i+=2) {
            Rota rota = new Rota(Integer.toString(i), LocalDate.now(), 1000);
            try {
                frota.localizar(Integer.toString(i)).setRota(rota);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        System.err.println("Antes de salvar:");

        for(Veiculo veiculo : frota.localizar()) {
            Rota rota = veiculo.getRota();

            if(rota != null)
                System.out.println("Veículo: " + veiculo.getPlaca() + ", Rota: " + rota.getPlaca());
        }

        try {
            frota.salvar_arquivo("teste");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Frota frotaCarregada = new Frota();

        try {
            frotaCarregada.carregar_arquivo("teste");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.err.println("\nDepois de salvar:");

        for(Veiculo veiculo : frotaCarregada.localizar()) {
            Rota rota = veiculo.getRota();

            if(rota != null)
                System.out.println("Veículo: " + veiculo.getPlaca() + ", Rota: " + rota.getPlaca());
        }
        

        JFrameAplication window = new JFrameAplication();
        window.setSize(500,500);
        window.setVisible(true);
        //Objetos swing 
    
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}

