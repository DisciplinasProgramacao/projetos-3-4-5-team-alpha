package business.Enuns;

public enum Combustivel {
    GASOLINA(12F, 4.48F),
    ETANOL(8F, 3.65F),
    DIESEL(3.5F, 6.65F);

    private double consumo, precoMedio;

    Combustivel(double consumo, double precoMedio) {
        this.consumo = consumo;
        this.precoMedio = precoMedio;
    }

    public double getConsumo() {
        return this.consumo;
    }

    public double getPrecoMedio() {
        return this.precoMedio;
    }

    @Override
    public String toString() {
        String str = this.name();
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
