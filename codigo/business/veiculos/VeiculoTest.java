package business.veiculos;

import java.time.LocalDate;

import business.Rota;
import business.Enuns.Combustivel;

public class VeiculoTest {
    static Carro carro = new Carro("aaa", Combustivel.GASOLINA, 1);
    static Rota rota10 = new Rota("aaa", LocalDate.now(), 120);
    static Rota rota50 = new Rota("aaa", LocalDate.now(), 600);

    public static void reabastecimentoMaximo(){
        carro.setRota(rota10);
        System.err.println(carro.getAutonomia());
        System.err.println(carro.getKm_rodados());   
        carro.setRota(rota50);
        System.err.println(carro.getAutonomia());
        System.err.println(carro.getKm_rodados());
        System.err.println(carro.getAutonomia());
    }

    public static void main(String[] args) {
        reabastecimentoMaximo();
    }
  }
