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

    public float calcular(float valor, int quilometragem, int km_rodados) {
         this.custosAdicionais = valor * (km_rodados/quilometragem);
         return this.custosAdicionais;
    }

    public float calcularIpva(float porcentagem, float valorVenda) {
        return this.ipva = porcentagem * valorVenda;
    }
}
