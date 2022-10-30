package business.custos;

public class CustosUtilitario extends Custos {
    private static final float VALOR_ALINHAMENTO = 120.0F,
            VALOR_VISTORIA = 500.0F,
            PERCENTUAL_IPVA = 0.03F,
            PERCENTUAL_SEGURO = 0.03F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000,
            QUILOMETRO_VISTORIA = 10000;
    private final float valorVenda;
    private float valorAlinhamento = 0F, valorVistoria = 0F;
    private int qtdVistoria, qtdAlinhamento;

    public CustosUtilitario(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public float calcular_seguro() {
        return (PERCENTUAL_SEGURO * valorVenda);
    }

    public float calcular_Alinhamento(int km_rodados) {
        if (km_rodados >= (QUILOMETRO_ALINHAMENTO * qtdAlinhamento)) {
            qtdAlinhamento++;
            this.valorAlinhamento = VALOR_ALINHAMENTO * (km_rodados/QUILOMETRO_ALINHAMENTO);
        }

        return this.valorAlinhamento;
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
