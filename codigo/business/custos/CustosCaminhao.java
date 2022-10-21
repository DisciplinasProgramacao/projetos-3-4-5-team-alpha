package business.custos;

public class CustosCaminhao extends Custos {
    private float seguro;

    public CustosCaminhao(float porcentagem, float valorVenda, float valorAdicional) {
        this.seguro = porcentagem * valorVenda + valorAdicional;
    }

    public float getSeguro() {
        return this.seguro;
    }
}
