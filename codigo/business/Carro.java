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

    @Override
    public float getGastosAdicionais() {
        return this.getAlinhamento();
    }

    public void calcular_alinhamento() throws Exception {
        if (super.getKm_rodados() >= (QUILOMETRO_ALINHAMENTO * qtdAlinhamento)) {
            custos.calcular(VALOR_ALINHAMENTO, QUILOMETRO_ALINHAMENTO, super.getKm_rodados());
            this.qtdAlinhamento++;
        } else {
            throw new Exception("");
        }
    }

    @Override
    public void setRota(Rota rota) throws Exception {
        if ((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_ALINHAMENTO) {
            super.setRota(rota);
        } else {
            throw new Exception("Essa viagem excede os km necessário para o alinhamento, por favor realiza-lo");
        }
    }

    public String toString() {
        return (super.toString() + " - Gastos: " + this.getGastos());
    }
}
