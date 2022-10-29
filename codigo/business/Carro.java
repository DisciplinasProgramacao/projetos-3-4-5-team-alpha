package business;

import business.custos.CustosCarro;

public class Carro extends Veiculo {
    private static final float VALOR_ALINHAMENTO = 80.00F,
            VALOR_ADICIONAL_SEGURO = 300.00F,
            PERCENTUAL_IPVA = 0.04F,
            PERCENTUAL_SEGURO = 0.05F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000;
    private static final int TANQUE = 50;
    private int qtdAlinhamento;
    private CustosCarro custosCarro;

    public Carro(String placa, float autonomia, float valor_venda) {
        super(placa, TANQUE, autonomia, valor_venda, PERCENTUAL_IPVA);
    }

    @Override
    public float getGastos() {
        float gastos = super.calcular_Ipva();
        gastos += calcular_Seguro();
        gastos += calcular_Alinhamento();

        return gastos;
    }

    @Override
    public float calcular_Seguro() {
        custosCarro = new CustosCarro(PERCENTUAL_SEGURO, super.getValor_venda(), VALOR_ADICIONAL_SEGURO);
        return custosCarro.getSeguro();
    }

    public float calcular_Alinhamento() {
        return custosCarro.calcularCusto(VALOR_ALINHAMENTO, QUILOMETRO_ALINHAMENTO, super.getKm_rodados(), qtdAlinhamento++);
    }
}
