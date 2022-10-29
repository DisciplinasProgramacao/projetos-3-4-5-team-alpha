package business.custos;

public class Custos {
    private float ipva;

    public float getIpva() {
        return this.ipva;
    }

    public float calcularIpva(float porcentagem, float valorVenda) {
        return this.ipva = porcentagem * valorVenda;
    }
}
