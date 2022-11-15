package business.veiculos;

import business.Combustivel;

public class Tanque {
    private float litragemAtual;
    private final float CAPACIDADE_MAXIMA;
    private Combustivel combustivel;
    
    public Tanque(float litragemAtual, float capacidadeMaxima, Combustivel combustivel){
        this.CAPACIDADE_MAXIMA = capacidadeMaxima;
        this.setLitragemAtual(litragemAtual);
        this.combustivel = combustivel;
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
        setLitragemAtual(CAPACIDADE_MAXIMA);
        return ((CAPACIDADE_MAXIMA - litragemAtual) * combustivel.getPrecoMedio());
    }

    public float getLitragemAtual(){
        return litragemAtual;
    }

    public void setLitragemAtual(float litragemAtual) {
        if(litragemAtual > 0 && litragemAtual < CAPACIDADE_MAXIMA) {
            this.litragemAtual = litragemAtual;
        }
    }

    public Combustivel getCombustivel(){
        return combustivel;
    }
}
