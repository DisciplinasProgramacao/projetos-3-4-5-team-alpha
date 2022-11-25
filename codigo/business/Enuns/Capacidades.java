package business.Enuns;

public enum Capacidades {
    CARRO(50),
    VAN(60),
    FURGAO(80),
    CAMINHAO(250);

    private float capacidadeMaxima;

    Capacidades(float capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public float getCapacidadeMaxima() {
        return this.capacidadeMaxima;
    }
    @Override
    public String toString() {
        String str = this.name();
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}