package business.custos;

public class CustosCarro extends Custos {
    private static final float VALOR_ALINHAMENTO = 80.00F,
    VALOR_ADICIONAL_SEGURO = 300.00F,
    PERCENTUAL_IPVA = 0.04F,
    PERCENTUAL_SEGURO = 0.05F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000;
    private float valorVenda, valorAlinhamento = 0F;
    private int qtdAlinhamento;

    public CustosCarro(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public float calcular_seguro() {
        return (PERCENTUAL_SEGURO * valorVenda) + VALOR_ADICIONAL_SEGURO;
    }

    public float calcular_Alinhamento(int km_rodados) {
        if (km_rodados >= (QUILOMETRO_ALINHAMENTO * qtdAlinhamento)) {
            qtdAlinhamento++;
            this.valorAlinhamento = VALOR_ALINHAMENTO * (km_rodados/QUILOMETRO_ALINHAMENTO);
        }

        return this.valorAlinhamento;
    }

    public float calcular_Ipva(){
        return super.calcularIpva(PERCENTUAL_IPVA, valorVenda);
    }
}
