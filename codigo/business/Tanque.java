package business;

public class Tanque {

    private float litragemAtual, capaciadadeMaxima;
    private Combustivel combustivel;

    public Tanque(float capaciadadeMaxima, Combustivel combustivel) {
        setCapacidadeMaxima(capaciadadeMaxima);
        this.combustivel = combustivel;
    }

    private void setCapacidadeMaxima(float capaciadadeMaxima) {
        if(capaciadadeMaxima > 0) {
            this.capaciadadeMaxima = capaciadadeMaxima;
        }
    }
}
