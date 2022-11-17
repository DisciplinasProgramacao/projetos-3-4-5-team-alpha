package business.veiculos;

import java.io.Serializable;

import business.Capacidades;
import business.Combustivel;

public class Tanque implements Serializable{
    private float litragemAtual;
    private final Capacidades CAPACIDADE_MAXIMA;
    private Combustivel combustivel;
    
    public Tanque(Combustivel combustivel, Capacidades capacidade){
        System.out.println(capacidade.getCapacidadeMaxima());
        this.CAPACIDADE_MAXIMA = capacidade;
        this.setLitragemAtual(CAPACIDADE_MAXIMA.getCapacidadeMaxima());
        this.combustivel = combustivel;
        System.out.println(combustivel);
    }

    public boolean consumirCombustivel(float distancia){
        if(distancia < (litragemAtual + (distancia * combustivel.getConsumo()))){
            setLitragemAtual(litragemAtual - (distancia / combustivel.getConsumo()));
            System.out.println(litragemAtual + (distancia * combustivel.getConsumo()));
            System.err.println(distancia);
            return true;
        }

        return false;
    }

    public float reabastecer(){
        setLitragemAtual(CAPACIDADE_MAXIMA.getCapacidadeMaxima());
        return ((CAPACIDADE_MAXIMA.getCapacidadeMaxima() - litragemAtual) * combustivel.getPrecoMedio());
    }

    public float getLitragemAtual(){
        return litragemAtual;
    }

    public void setLitragemAtual(float litragemAtual) {
        if(litragemAtual > 0 && litragemAtual <= CAPACIDADE_MAXIMA.getCapacidadeMaxima()) {
            this.litragemAtual = litragemAtual;
        }
    }

    public Combustivel getCombustivel(){
        return combustivel;
    }

    public float getCapacidadeMaxima(){
        return CAPACIDADE_MAXIMA.getCapacidadeMaxima();
    }
}
