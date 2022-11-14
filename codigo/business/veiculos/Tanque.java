package business.veiculos;

import business.Combustivel;

public class Tanque {
    private float litragemAtual;
    private float capacidadeMaxima;
    private Combustivel combustivel;
    
    public Tanque(float litragemAtual, float capacidadeMaxima, Combustivel combustivel){
        this.capacidadeMaxima = capacidadeMaxima;
        this.litragemAtual = litragemAtual;
        this.combustivel = combustivel;
    }
    public boolean consumirCombustivel(float distancia){
        if(distancia < (litragemAtual + (distancia * combustivel.getConsumo()))){
            litragemAtual = litragemAtual -  (distancia/combustivel.getConsumo());
            System.out.println(litragemAtual + (distancia * combustivel.getConsumo()));
            System.err.println(distancia);
            return true;
        }
        return false;
    }
    public void reabastecer(float litro){
        if((litro+litragemAtual) < capacidadeMaxima){
            litragemAtual =+ litro;
        }
    }
    public float getLitragemAtual(){
        return litragemAtual;
    }
    public Combustivel getCombustivel(){
        return combustivel;
    }
}
