package business;

import business.custos.CustosCarro;

public class Carro extends Veiculo {
    private static float VALOR_ALINHAMENTO = 80.00F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000;
    private static final float PERCENTUAL_SEGURO = 0.05F;
    private static final float VALOR_ADICIONAL_SEGURO = 300;
    private int qtdAlinhamento;



    public Carro(String placa, int tanque, float autonomia, float valor_venda) {
        super(placa, tanque, autonomia, valor_venda, 0.04F);

    	this.calcular_ipva();
    	this.calcular_seguro();
    }



    public int getQtdAlinhamento() {
        return qtdAlinhamento;
    }
 
    public float getValor_alinhamento() {
        return this.getQtdAlinhamento() * VALOR_ALINHAMENTO;
    }



    @Override
    public float getGastos() {
        float gastos = super.calcular_ipva();
        gastos += calcular_seguro();
    	gastos += getValor_alinhamento();
    	
    	return gastos;
    }

    @Override
    public float calcular_seguro() {
        return CustosCarro.calcularSeguro(PERCENTUAL_SEGURO, super.getValor_venda(), VALOR_ADICIONAL_SEGURO);
    }

    public void calcular_alinhamento() {
		if(super.getKm_rodados() >= QUILOMETRO_ALINHAMENTO) {
			CustosCarro.calcular(VALOR_ALINHAMENTO, QUILOMETRO_ALINHAMENTO, getKm_rodados() );
		}
	}

    @Override
    public void setRota(Rota rota) throws Exception{
        if((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_ALINHAMENTO){
                super.setRota(rota);
        } else {
            throw new Exception("Essa viagem excede os km necessário para o alinhamento, por favor realiza-lo");
        }
    }
}
