package business;

public class Caminhao extends Veiculo {
    
    private static float VALOR_VISTORIA = 1000.00F;
    private static final int QUILOMETRO_VISTORIA = 30000;
    private static float VALOR_MANUTENCAO = 1000.00F;
    private static final int QUILOMETRO_MANUTENCAO = 20000;
    private int qtdManutencao;
    private int qtdVistoria;



    public Caminhao(String placa, int tanque, float autonomia, float valor_venda) {
        super(placa, tanque, autonomia, valor_venda, 0.01F);
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
    public float getGastos() {
        return 0.0F;
    }

    @Override
    public void calcular_seguro() {

    }

    public void calcular_manutencao() {
		if(super.getKm_rodados() == QUILOMETRO_MANUTENCAO) {
			this.qtdManutencao++;
		}
	}

	public void calcular_vistoria() {
		if(super.getKm_rodados() == QUILOMETRO_VISTORIA) {
			this.qtdVistoria++;
		}
	}

    @Override
    public void setRota(Rota rota) throws Exception{
        if((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_VISTORIA){
            if((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_MANUTENCAO){
                super.setRota(rota);
            }else{
                throw new Exception("Essa viagem excede os km necessário para a manutenção, por favor realiza-la");
            }
        }else{
            throw new Exception("Essa viagem excede os km necessário para a vistoria, por favor realiza-la");
        }
    }
}
