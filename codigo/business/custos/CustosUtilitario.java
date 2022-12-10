package business.custos;

public class CustosUtilitario extends CustosFixos {
    private static final double VALOR_ALINHAMENTO = 120.0F,
            VALOR_VISTORIA = 500.0F,
            PERCENTUAL_IPVA = 0.03F,
            PERCENTUAL_SEGURO = 0.03F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000,
            QUILOMETRO_VISTORIA = 10000;
    private final double VALOR_VENDA;
    private double custoAlinhamento = 0F, custoVistoria = 0F, valorIpva = 0F, valorSeguro = 0F;
    private int qtdVistoria, qtdAlinhamento;

    public CustosUtilitario(double valorVenda) throws ArithmeticException {
        if(valorVenda > 0) {
            this.VALOR_VENDA = valorVenda;
            this.calcularIpva();
            this.calcularSeguro();

        } else {
            throw new ArithmeticException("O valor de venda tem que ser positivo.");
        }
    }

    public double calcularIpva() {
        this.valorIpva = super.calcularIpva(PERCENTUAL_IPVA, VALOR_VENDA);

        return this.valorIpva;
    }

    public double calcularSeguro() {
        this.valorSeguro = super.calcularSeguro(PERCENTUAL_SEGURO, VALOR_VENDA);

        return this.valorSeguro;
    }

    public double calcularAlinhamento(int km_rodados) {
        this.custoAlinhamento = super.calcularCustos(QUILOMETRO_ALINHAMENTO, VALOR_ALINHAMENTO, km_rodados, qtdAlinhamento);
        this.qtdAlinhamento = super.getQtdCustos();

        return this.custoAlinhamento;
    }

    public double calcularVistoria(int km_rodados) {
        this.custoVistoria = super.calcularCustos(QUILOMETRO_VISTORIA, VALOR_VISTORIA, km_rodados, qtdVistoria);
        this.qtdVistoria = super.getQtdCustos();

        return this.custoVistoria;
    }

    public int qtdAlinhamento() {
        return this.qtdAlinhamento;
    }

    public int qtdVistoria() {
        return this.qtdVistoria;
    }

    @Override
    public double calcularCustoTotal() {
        return this.valorIpva + this.valorSeguro + this.custoAlinhamento + this.custoVistoria;
    }
}
