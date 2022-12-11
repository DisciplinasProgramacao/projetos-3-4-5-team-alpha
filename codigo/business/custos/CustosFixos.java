package business.custos;
import java.io.Serializable;

public abstract class CustosFixos implements Custos, Serializable  {
    private int qdtCustos;
    private double custoAbastecimento = 0;

    public double calcularIpva(double porcentagem, double valorVenda) {
        return porcentagem * valorVenda;
    }

    public double calcularSeguro(double porcentagem, double valorVenda) {
        return porcentagem * valorVenda;
    }

    public double calcularCustos(int quilometragem, double valor, int km_rodados, int qtdCustos) {
        if (km_rodados >= (quilometragem * qtdCustos)) {
            qdtCustos = qtdCustos;
            return valor * (km_rodados/quilometragem);
        }

        return 0F;
    }

    public int getQtdCustos() {
        return this.qdtCustos++;
    }
    
    public abstract double calcularCustoTotal();

    public double getCustoAbastecimento() {
        return this.custoAbastecimento;
    }

    public void custoAbastecimento(double custoReabastecimento) {
        this.custoAbastecimento += custoReabastecimento;
    }
}
