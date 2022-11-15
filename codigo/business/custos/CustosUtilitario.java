package business.custos;

public class CustosUtilitario extends CustosFixos {
    private static final float VALOR_ALINHAMENTO = 120.0F,
            VALOR_VISTORIA = 500.0F,
            PERCENTUAL_IPVA = 0.03F,
            PERCENTUAL_SEGURO = 0.03F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000,
            QUILOMETRO_VISTORIA = 10000;
    private final float VALOR_VENDA;
    private float custoAlinhamento = 0F, custoVistoria = 0F, valorIpva = 0F, valorSeguro = 0F;
    private int qtdVistoria, qtdAlinhamento;

    public CustosUtilitario(float valorVenda) {
        this.VALOR_VENDA = valorVenda;
        this.calcularIpva();
        this.calcularSeguro();
    }

    public float calcularIpva(){
        this.valorIpva = super.calcularIpva(PERCENTUAL_IPVA, VALOR_VENDA);

        return this.valorIpva;
    }

    public float calcularSeguro() {
        this.valorSeguro = super.calcularSeguro(PERCENTUAL_SEGURO, VALOR_VENDA);

        return this.valorSeguro;
    }

    public float calcularAlinhamento(int km_rodados) {
        this.custoAlinhamento = super.calcularCustos(QUILOMETRO_ALINHAMENTO, VALOR_ALINHAMENTO, km_rodados, qtdAlinhamento);
        this.qtdAlinhamento = super.getQtdCustos();

        return this.custoAlinhamento;
    }

    public float calcularVistoria(int km_rodados) {
        this.custoVistoria = super.calcularCustos(QUILOMETRO_VISTORIA, VALOR_VISTORIA, km_rodados, qtdVistoria);
        this.qtdVistoria = super.getQtdCustos();

        return this.custoVistoria;
    }

    @Override
    public float calcularCustoTotal() {
        return this.valorIpva + this.valorSeguro + this.custoAlinhamento + this.custoVistoria;
    }
}
