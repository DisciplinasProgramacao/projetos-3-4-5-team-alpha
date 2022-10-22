package business.custos;

public class CustosCarro extends Custos {
    private float seguro;

    public CustosCarro(float porcentagem, float valorVenda, float valorAdicional) {
        this.seguro = porcentagem * valorVenda + valorAdicional;
    }

    public float getSeguro() {
        return this.seguro;
    }
}
