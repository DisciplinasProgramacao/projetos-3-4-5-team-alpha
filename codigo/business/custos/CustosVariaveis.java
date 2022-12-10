package business.custos;
import java.io.Serializable;

public class CustosVariaveis implements Custos, Serializable {
    private double precoCusto;
    private String descricao;

    public CustosVariaveis(String descricao, double preco) {
        this.precoCusto = preco;
        this.descricao = descricao;
    }

    @Override
    public double calcularCustoTotal() {
        return this.precoCusto;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
