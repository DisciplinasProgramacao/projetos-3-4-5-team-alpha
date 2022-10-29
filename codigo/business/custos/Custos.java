package business.custos;

import java.io.Serializable;

public class Custos implements Serializable {
    private float ipva;
    private float custosAdicionais;

    public float getIpva() {
        return this.ipva;
    }

    public float calcularCusto(float valor, int quilometragem, int km_rodados, int qtdManutencao) {
        if (km_rodados >= (quilometragem * qtdManutencao)) {
            this.custosAdicionais = valor * (km_rodados / quilometragem);
        }

        return this.custosAdicionais;
    }

    public float calcularIpva(float porcentagem, float valorVenda) {
        return this.ipva = porcentagem * valorVenda;
    }
}
