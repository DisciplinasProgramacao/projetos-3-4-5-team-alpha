package business;

public enum Combustivel {
    GASOLINA(12F, 4.48F),
    ETANOL(8F, 3.65F),
    DIESEL(3.5F, 6.65F);

    private float consumo, precoMedio;

    Combustivel(float consumo, float precoMedio) {
        this.consumo = consumo;
        this.precoMedio = precoMedio;
    }

    public float getConsumo() {
        return this.consumo;
    }

    public float getPrecoMedio() {
        return this.precoMedio;
    }
}
