package business.custos;

public class CustosCaminhao extends CustosFixos {
    private static final double VALOR_VISTORIA = 1000.0,
            VALOR_MANUTENCAO = 1000.0,
            VALOR_ADICIONAL_SEGURO = 2000.0,
            PERCENTUAL_IPVA = 0.01,
            PERCENTUAL_SEGURO = 0.02;
    private static final int QUILOMETRO_VISTORIA = 30000,
            QUILOMETRO_MANUTENCAO = 20000;
    private final double VALOR_VENDA;
    private double custoManutencao = 0, custoVistoria = 0, valorIpva = 0, valorSeguro = 0;
    private int qtdManutencao, qtdVistoria;

    public CustosCaminhao(double valorVenda) throws IllegalArgumentException {
        if(valorVenda > 0) {
            this.VALOR_VENDA = valorVenda;
            this.calcularIpva();
            this.calcularSeguro();
            
        } else
            throw new IllegalArgumentException ("O valor de venda precisa ser positivo.");
    }

    public double calcularIpva() {
        this.valorIpva = super.calcularIpva(PERCENTUAL_IPVA, VALOR_VENDA);

        return this.valorIpva;
    }

    public double calcularSeguro() {
        this.valorSeguro = super.calcularSeguro(PERCENTUAL_SEGURO, VALOR_VENDA) + VALOR_ADICIONAL_SEGURO;

        return this.valorSeguro;
    }

    public double calcularManutencao(int km_rodados) {
        this.custoManutencao = super.calcularCustos(QUILOMETRO_MANUTENCAO, VALOR_MANUTENCAO, km_rodados, qtdManutencao);
        this.qtdManutencao = super.getQtdCustos();

        return this.custoManutencao;
    }

    public double calcularVistoria(int km_rodados) {
        this.custoVistoria = super.calcularCustos(QUILOMETRO_VISTORIA, VALOR_VISTORIA, km_rodados, qtdVistoria);
        this.qtdVistoria = super.getQtdCustos();

        return this.custoVistoria;
    }

    public int qtdManutencao() {
        return this.qtdManutencao;
    }

    public int qtdVistoria() {
        return this.qtdVistoria;
    }

    @Override
    public double calcularCustoTotal() {
        return this.valorIpva + this.valorSeguro + this.custoManutencao + this.custoVistoria + super.getCustoAbastecimento();
    }
}
