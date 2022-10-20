package business;
public class Carro extends Veiculo {
    private static float VALOR_ALINHAMENTO = 80.00F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000;
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
        return 0.0F;
    }

    @Override
    public void calcular_seguro() {

    }

    public void calcular_alinhamento() {
		if(super.getKm_rodados() == QUILOMETRO_ALINHAMENTO) {
			this.qtdAlinhamento++;
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
