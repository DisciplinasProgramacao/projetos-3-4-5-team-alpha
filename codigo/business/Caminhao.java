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
    private CustosCaminhao custos;

    public Caminhao(String placa, float autonomia, float valor_venda) {
        super(placa, TANQUE, autonomia, valor_venda, PERCENTUAL_IPVA);
    }

    public int getQtdManutencao() {
        return qtdManutencao;
    }

    public int getQtdVistoria() {
        return qtdVistoria;
    }

    public float getValor_manutencao() {
        return this.qtdManutencao * VALOR_MANUTENCAO;
    }

    public float getValor_vistoria() {
        return this.qtdVistoria * VALOR_VISTORIA;
    }

    @Override
    public float getGastos(){
        float gastos = super.calcular_ipva();
        gastos += calcular_seguro();
        gastos += getValor_manutencao();
        gastos += getValor_vistoria();

        return gastos;
    }

    @Override
    public float calcular_seguro() {
        custos = new CustosCaminhao(PERCENTUAL_SEGURO, super.getValor_venda(), VALOR_ADICIONAL_SEGURO);
        return custos.getSeguro();
    }

    public void calcular_manutencao(int distancia) {
        if (super.getKm_rodados() >= (QUILOMETRO_MANUTENCAO * qtdManutencao)) {
            int manutencao = distancia / QUILOMETRO_MANUTENCAO;
            this.qtdManutencao += manutencao;
        }
    }

    public void calcular_vistoria(int distancia) {
        int vistoria = distancia / QUILOMETRO_VISTORIA;
        if (super.getKm_rodados() >= (QUILOMETRO_VISTORIA * qtdVistoria)) {
            this.qtdVistoria += vistoria;
        }
}

    @Override
    public void setRota(Rota rota)  {
        if(rota.getDistancia() < this.getAutonomia()){
            super.setRota(rota);
            calcular_vistoria(rota.getDistancia());
            calcular_manutencao(rota.getDistancia());
        }
    }
}
