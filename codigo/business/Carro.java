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
    private CustosCarro custos;

    public Carro(String placa, float autonomia, float valor_venda) {
        super(placa, TANQUE, autonomia, valor_venda, PERCENTUAL_IPVA);

        super.calcular_ipva();
        this.calcular_seguro();
    }

    public float getAlinhamento() {
        return custos.getCustosAdicionais();
    }

    @Override
    public float getGastos() {
        this.calcular_alinhamento();
        float gastos = super.calcular_ipva();
        gastos += calcular_seguro();
        gastos += getAlinhamento();

        return gastos;
    }

    @Override
    public float calcular_seguro() {
        custos = new CustosCarro(PERCENTUAL_SEGURO, super.getValor_venda(), VALOR_ADICIONAL_SEGURO);
        return custos.getSeguro();
    }

    public void calcular_alinhamento() {
        custos.calcular(VALOR_ALINHAMENTO, QUILOMETRO_ALINHAMENTO, super.getKm_rodados());
        this.qtdAlinhamento++;
    }

    @Override
    public void setRota(Rota rota)  {
        super.setRota(rota);
    }

}
