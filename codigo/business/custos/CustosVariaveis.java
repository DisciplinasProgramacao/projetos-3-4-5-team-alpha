package business.custos;
import java.io.Serializable;

public class CustosVariaveis implements Custos, Serializable {
    private float custoTotal = 0F;

    public void addNovoCustoVariavel(float preco) {
        custoTotal += preco;
    }

    @Override
    public float calcularCustoTotal() {
        return custoTotal;
    }
}
