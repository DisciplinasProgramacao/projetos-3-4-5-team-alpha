package business.custos;
import java.io.Serializable;

public abstract class CustosFixos implements Custos, Serializable  {
    int qdtCustos;

    public float calcularIpva(float porcentagem, float valorVenda) {
        return porcentagem * valorVenda;
    }

    public float calcularSeguro(float porcentagem, float valorVenda) {
        return porcentagem * valorVenda;
    }

    public float calcularCustos(int quilometragem, float valor, int km_rodados, int qtdCustos) {
        if (km_rodados >= (quilometragem * qtdCustos)) {
            qdtCustos = qtdCustos;
            return valor * (km_rodados/quilometragem);
        }

        return 0F;
    }

    public int getQtdCustos() {
        return this.qdtCustos + 1;
    }

    @Override
    public abstract float calcularCustoTotal();
}
