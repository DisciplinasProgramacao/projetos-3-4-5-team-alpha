package business;

public class Utilitario extends Veiculo {
    private static float VALOR_ALINHAMENTO = 120.0F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000;
    private static float VALOR_VISTORIA = 1000.0F;
    private static final int QUILOMETRO_VISTORIA = 10000;
    private static final float PERCENTUAL_SEGURO = 0.03F;
    private int qtdVistoria;
    private int qtdAlinhamento;
    private String tipo_utilitario;
    
    
    
    public Utilitario(String placa, String tipo, int tanque, float autonomia, float valor_venda) throws Exception {
		super(placa, tanque, autonomia, valor_venda, 0.03F);

    	if(tipo.equals("Van") || tipo.equals("Furgão")) {    		
			this.setTipoUtilitario(tipo);
    	} else {
			throw new Exception();
		}

    	this.calcular_ipva();
    	this.calcular_seguro();
    }
    
    

    public int getQtdAlinhamento() {
        return qtdAlinhamento;
    }

	public int getQtdVistoria() {
        return qtdVistoria;
    }

    public String getTipo_utilitario() {
        return tipo_utilitario;
    }

    public float getValor_alinhamento() {
        return this.getQtdAlinhamento() * VALOR_ALINHAMENTO;
    }

    public float getValor_vistoria() {
        return this.getQtdVistoria() * VALOR_VISTORIA;
    }


	 
	public void setTipoUtilitario(String tipo_utilitario) {
		this.tipo_utilitario = tipo_utilitario;
	}



	@Override
    public float getGastos() {
    	float gastos = super.getValor_ipva();
    	gastos += super.getValor_seguro();
        gastos += getValor_alinhamento();
    	gastos += getValor_vistoria();
    	
    	return gastos;
    }

	@Override
	public void calcular_seguro() {
		float valor_seguro = super.getValor_venda() * PERCENTUAL_SEGURO;
		super.setValorSeguro(valor_seguro);
	}
	
	public void calcular_alinhamento() {
		if(super.getKm_rodados() >= (QUILOMETRO_ALINHAMENTO*qtdAlinhamento)) {
			this.qtdAlinhamento++;
		}
	}

	public void calcular_vistoria() {
		if(super.getKm_rodados() >= (QUILOMETRO_VISTORIA*qtdVistoria)) {
			this.qtdVistoria++;
		}
	}

	@Override
    public void setRota(Rota rota) throws Exception{
        if((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_ALINHAMENTO){
            if((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_VISTORIA){
                super.setRota(rota);
            }else{
                throw new Exception("Essa viagem excede os km necessário para o alinhamento, por favor realiza-la");
            }
        }else{
            throw new Exception("Essa viagem excede os km necessário para a vistoria, por favor realiza-la");
        }
    }
}
