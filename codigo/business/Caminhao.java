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
    private int qtdManutencao, qtdVistoria;
    private CustosCaminhao custos;



    public Caminhao(String placa, int tanque, float autonomia, float valor_venda) {
        super(placa, tanque, autonomia, valor_venda, PERCENTUAL_IPVA);
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
    public float getGastos() throws Exception {
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

    public float calcular_manutencao() throws Exception{
		if(super.getKm_rodados() >= (QUILOMETRO_MANUTENCAO * qtdManutencao)) {
			custos.calcular(VALOR_MANUTENCAO, QUILOMETRO_MANUTENCAO, super.getKm_rodados());
		}
        throw new Exception("O caminhão ainda ainda não fez a manuntenção");
	}

	public void calcular_vistoria() {
		if(super.getKm_rodados() >= (QUILOMETRO_VISTORIA * qtdVistoria)) {
			this.qtdVistoria++;
		}
	}

    @Override
    public float getGastosAdicionais() {
        return this.getValor_manutencao();
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
