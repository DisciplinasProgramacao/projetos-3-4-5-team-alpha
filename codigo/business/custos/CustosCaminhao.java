package business.custos;

public class CustosCaminhao extends Custos {
    private static final float VALOR_VISTORIA = 1000.00F,
            VALOR_MANUTENCAO = 1000.00F,
            VALOR_ADICIONAL_SEGURO = 2000.00F,
            PERCENTUAL_IPVA = 0.01F,
            PERCENTUAL_SEGURO = 0.02F;
    private static final int QUILOMETRO_VISTORIA = 30000,
            QUILOMETRO_MANUTENCAO = 20000;
    private final float valorVenda;
    private float valorManutencao = 0F, valorVistoria = 0F;
    private int qtdManutencao, qtdVistoria;

    public CustosCaminhao(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public float calcular_seguro() {
        return (PERCENTUAL_SEGURO * valorVenda) + VALOR_ADICIONAL_SEGURO;
    }

    public float calcular_Manutencao(int km_rodados) {
        if (km_rodados >= (QUILOMETRO_MANUTENCAO * qtdManutencao)) {
            qtdManutencao++;
            this.valorManutencao = VALOR_MANUTENCAO * (km_rodados/QUILOMETRO_MANUTENCAO);
        }

        return this.valorManutencao;
    }

    public float calcular_Vistoria(int km_rodados) {
        if (km_rodados >= (QUILOMETRO_VISTORIA * qtdVistoria)) {
            qtdVistoria++;
            this.valorVistoria = VALOR_VISTORIA * (km_rodados/QUILOMETRO_VISTORIA);
        }

        return this.valorVistoria;
    }

    public float calcular_Ipva(){
        return super.calcularIpva(PERCENTUAL_IPVA, valorVenda);
    }
}
