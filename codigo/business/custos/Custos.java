package business.custos;

public class Custos {
    private float ipva;
    private float custosAdicionais;

    public float getIpva() {
        return this.ipva;
    }

    public float getCustosAdicionais() {
        return this.custosAdicionais;
    }

    public void calcular(float valor, int quilometragem, int km_rodados) {
        this.custosAdicionais = valor * (quilometragem / km_rodados);
    }

    public void calcularIpva(float porcentagem, float valorVenda) {
        this.ipva = porcentagem * valorVenda;
    }
}
