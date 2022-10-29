package business.custos;

import java.io.Serializable;

public class Custos implements Serializable {
    private float ipva;

    public float getIpva() {
        return this.ipva;
    }

    public float calcularIpva(float porcentagem, float valorVenda) {
        return this.ipva = porcentagem * valorVenda;
    }
}
