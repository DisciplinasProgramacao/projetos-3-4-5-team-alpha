package business.custos;

public class CustosUtilitario extends Custos {
    private float seguro;

    public CustosUtilitario(float porcentagem, float valorVenda) {
        this.seguro = porcentagem * valorVenda;
    }

    public float getSeguro() {
        return this.seguro;
    }
}
