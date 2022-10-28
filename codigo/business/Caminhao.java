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
        return this.getQtdManutencao() * VALOR_MANUTENCAO;
    }

    public float getValor_vistoria() {
        return this.getQtdVistoria() * VALOR_VISTORIA;
    }

    @Override
    public float getGastos(){
        float gastos = super.calcular_ipva();
        gastos += calcular_seguro();
        gastos += calcular_manutencao();
        gastos += getValor_vistoria();

        return gastos;
    }

    @Override
    public float calcular_seguro() {
        custos = new CustosCaminhao(PERCENTUAL_SEGURO, super.getValor_venda(), VALOR_ADICIONAL_SEGURO);
        return custos.getSeguro();
    }

    public float calcular_manutencao() {
       
            return custos.calcular(VALOR_MANUTENCAO, QUILOMETRO_MANUTENCAO, super.getKm_rodados());
        
    }

    public void calcular_vistoria() {
        if (super.getKm_rodados() >= (QUILOMETRO_VISTORIA * qtdVistoria)) {
            this.qtdVistoria++;
        }
    }

    @Override
    public void setRota(Rota rota)  {
        if(rota.getDistancia() < this.getAutonomia()){
            super.setRota(rota);
        }
        
    }

}
