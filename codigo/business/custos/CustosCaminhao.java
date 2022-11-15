package business.custos;

public class CustosCaminhao extends CustosFixos {
    private static final float VALOR_VISTORIA = 1000.00F,
            VALOR_MANUTENCAO = 1000.00F,
            VALOR_ADICIONAL_SEGURO = 2000.00F,
            PERCENTUAL_IPVA = 0.01F,
            PERCENTUAL_SEGURO = 0.02F;
    private static final int QUILOMETRO_VISTORIA = 30000,
            QUILOMETRO_MANUTENCAO = 20000;
    private final float VALOR_VENDA;
    private float custoManutencao = 0F, custoVistoria = 0F, valorIpva = 0F, valorSeguro = 0F;
    private int qtdManutencao, qtdVistoria;

    public CustosCaminhao(float valorVenda) {
        this.VALOR_VENDA = valorVenda;
        this.calcularIpva();
        this.calcularSeguro();
    }

    public float calcularIpva() {
        this.valorIpva = super.calcularIpva(PERCENTUAL_IPVA, VALOR_VENDA);

        return this.valorIpva;
    }

    public float calcularSeguro() {
        this.valorSeguro = super.calcularSeguro(PERCENTUAL_SEGURO, VALOR_VENDA) + VALOR_ADICIONAL_SEGURO;

        return this.valorSeguro;
    }

    public float calcularManutencao(int km_rodados) {
        this.custoManutencao = super.calcularCustos(QUILOMETRO_MANUTENCAO, VALOR_MANUTENCAO, km_rodados, qtdManutencao);
        this.qtdManutencao = super.getQtdCustos();

        return this.custoManutencao;
    }

    public float calcularVistoria(int km_rodados) {
        this.custoVistoria = super.calcularCustos(QUILOMETRO_VISTORIA, VALOR_VISTORIA, km_rodados, qtdVistoria);
        this.qtdVistoria = super.getQtdCustos();

        return this.custoVistoria;
    }

    @Override
    public float calcularCustoTotal() {
        return this.valorIpva + this.valorSeguro + this.custoManutencao + this.custoVistoria;
    }
}
