package business.veiculos;

import java.io.Serializable;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;

public class Tanque implements Serializable{
    private float litragemAtual;
    private final Capacidades CAPACIDADE_MAXIMA;
    private Combustivel combustivel;
    public final double AUTONOMIA_MAXIMA;
    
    public Tanque(Combustivel combustivel, Capacidades capacidade){
        this.CAPACIDADE_MAXIMA = capacidade;
        this.AUTONOMIA_MAXIMA = CAPACIDADE_MAXIMA.getCapacidadeMaxima() * combustivel.getConsumo();
        this.setLitragemAtual(CAPACIDADE_MAXIMA.getCapacidadeMaxima());
        this.combustivel = combustivel;
    }

    public boolean consumirCombustivel(float distancia){
        if(distancia <= autonomia()){
            setLitragemAtual(litragemAtual - (distancia / combustivel.getConsumo()));
            return true;
        }

        return false;
    }

    public float autonomia(){
        return litragemAtual*combustivel.getConsumo();
    }

    public float reabastecer(){
        float preco = ((CAPACIDADE_MAXIMA.getCapacidadeMaxima() - litragemAtual) * combustivel.getPrecoMedio());
        setLitragemAtual(CAPACIDADE_MAXIMA.getCapacidadeMaxima());
        return preco;
    }

    public float getLitragemAtual(){
        return litragemAtual;
    }

    public void setLitragemAtual(float litragemAtual) {
        if(litragemAtual >= 0 && litragemAtual <= CAPACIDADE_MAXIMA.getCapacidadeMaxima()) {
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
