package business.veiculos;

import java.io.Serializable;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;

public class Tanque implements Serializable{
    private double litragemAtual;
    private final Capacidades CAPACIDADE_MAXIMA;
    private Combustivel combustivel;
    public final double AUTONOMIA_MAXIMA;
    
    public Tanque(Combustivel combustivel, Capacidades capacidade) {
        this.CAPACIDADE_MAXIMA = capacidade;
        this.AUTONOMIA_MAXIMA = CAPACIDADE_MAXIMA.getCapacidadeMaxima() * combustivel.getConsumo();
        this.setLitragemAtual(CAPACIDADE_MAXIMA.getCapacidadeMaxima());
        this.combustivel = combustivel;
    }

    public boolean consumirCombustivel(double distancia) {
        if(distancia <= autonomia()){
            setLitragemAtual(litragemAtual - (distancia / combustivel.getConsumo()));
            return true;
        }

        return false;
    }

    public double autonomia() {
        return litragemAtual*combustivel.getConsumo();
    }

    public double reabastecer() {
        double preco = ((CAPACIDADE_MAXIMA.getCapacidadeMaxima() - litragemAtual) * combustivel.getPrecoMedio());
        setLitragemAtual(CAPACIDADE_MAXIMA.getCapacidadeMaxima());

        return preco;
    }

    public double getLitragemAtual() {
        return litragemAtual;
    }

    public void setLitragemAtual(double litragemAtual) {
        if(litragemAtual >= 0 && litragemAtual <= CAPACIDADE_MAXIMA.getCapacidadeMaxima()) {
            this.litragemAtual = litragemAtual;
        }
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public double getCapacidadeMaxima() {
        return CAPACIDADE_MAXIMA.getCapacidadeMaxima();
    }
}
