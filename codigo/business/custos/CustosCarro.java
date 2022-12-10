package business.custos;

public class CustosCarro extends CustosFixos {
    private static final double VALOR_ALINHAMENTO = 80.00F,
    VALOR_ADICIONAL_SEGURO = 300.00F,
    PERCENTUAL_IPVA = 0.04F,
    PERCENTUAL_SEGURO = 0.05F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000;
    private final double VALOR_VENDA;
    private double custoAlinhamento = 0F, valorIpva = 0F, valorSeguro = 0F;
    private int qtdAlinhamento;

    public CustosCarro(double valorVenda) throws ArithmeticException {
        if(valorVenda > 0) {
            this.VALOR_VENDA = valorVenda;
            this.calcularIpva();
            this.calcularSeguro();

        } else
            throw new ArithmeticException("O valor de venda tem que ser positivo.");
    }

    public double calcularIpva() {
        this.valorIpva = super.calcularIpva(PERCENTUAL_IPVA, VALOR_VENDA);

        return this.valorIpva;
    }


    public double calcularSeguro() {
        this.valorSeguro = super.calcularIpva(PERCENTUAL_SEGURO, VALOR_VENDA) + VALOR_ADICIONAL_SEGURO;

        return this.valorSeguro;
    }

    public double calcularAlinhamento(int km_rodados) {
        this.custoAlinhamento = super.calcularCustos(QUILOMETRO_ALINHAMENTO, VALOR_ALINHAMENTO, km_rodados, qtdAlinhamento);
        this.qtdAlinhamento = super.getQtdCustos();

        return this.custoAlinhamento;
    }

    public int qtdAlinhamento() {
        return this.qtdAlinhamento;
    }
   
    @Override
    public double calcularCustoTotal() {
        return this.valorIpva + this.valorSeguro + this.custoAlinhamento;
    }
}
