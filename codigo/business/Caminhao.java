package business;

import business.custos.CustosCaminhao;

public class Caminhao extends Veiculo {

    private static final float VALOR_VISTORIA = 1000.00F,
            VALOR_MANUTENCAO = 1000.00F,
            VALOR_ADICIONAL_SEGURO = 2000.00F,
            PERCENTUAL_IPVA = 0.01F,
            PERCENTUAL_SEGURO = 0.02F;
    private static final int QUILOMETRO_VISTORIA = 30000,
            QUILOMETRO_MANUTENCAO = 20000;
    private static final int TANQUE = 250;
    private int qtdManutencao, qtdVistoria;
    private CustosCaminhao custosCaminhao;

    public Caminhao(String placa, float autonomia, float valor_venda) {
        super(placa, TANQUE, autonomia, valor_venda, PERCENTUAL_IPVA);
    }

    @Override
    public float getGastos() {
        float gastos = super.calcular_Ipva();
        gastos += calcular_Seguro();
        gastos += calcular_Manutencao();
        gastos += calcular_Vistoria();

        return gastos;
    }

    @Override
    public float calcular_Seguro() {
        custosCaminhao = new CustosCaminhao(PERCENTUAL_SEGURO, super.getValor_venda(), VALOR_ADICIONAL_SEGURO);
        return custosCaminhao.getSeguro();
    }

    public float calcular_Manutencao() {
        return custosCaminhao.calcularCusto(VALOR_MANUTENCAO, QUILOMETRO_MANUTENCAO, super.getKm_rodados(), qtdManutencao++);
    }

    public float calcular_Vistoria() {
        return custosCaminhao.calcularCusto(VALOR_VISTORIA, QUILOMETRO_VISTORIA, super.getKm_rodados(), qtdVistoria++);
    }
}
