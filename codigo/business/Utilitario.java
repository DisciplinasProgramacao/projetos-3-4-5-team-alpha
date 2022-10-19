package business;

public class Utilitario extends Veiculo {
    private static float VALOR_ALINHAMENTO = 120.0F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000;
    private static float VALOR_VISTORIA = 1000.0F;
    private static final int QUILOMETRO_VISTORIA = 10000;
    private static final float PERCENTUAL_IPVA = 0.03F;
    private static final float PERCENTUAL_SEGURO = 0.03F;
    private int qtdVistoria;
    private int qtdAlinhamento;
    private String tipo_utilitario;
    
    
    
    public Utilitario(String placa, String cod_tipo, String tipo, int tanque, float autonomia, float valor_venda) throws Exception {
		super(placa, cod_tipo, tanque, autonomia, valor_venda);

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

    public String getTipo_utilitario() {
        return tipo_utilitario;
    }

    public float getValor_alinhamento() {
        return qtdAlinhamento * VALOR_ALINHAMENTO;
    }

    public float getValor_vistoria() {
        return qtdVistoria * VALOR_VISTORIA;
    }

    public double getGastos() {
    	float gastos = 0;
    	gastos += getValor_alinhamento();
    	gastos += getValor_vistoria();
    	gastos += super.getValor_ipva();
    	gastos += super.getValor_seguro();
    	
    	return gastos;
    }

    public int getQtdVistoria() {
        return qtdVistoria;
    }
	
	
	
	public void setQtdVistoria(int qtdVistoria) {
		this.qtdVistoria = qtdVistoria;
	}
	 
	public void setTipoUtilitario(String tipo_utilitario) {
		this.tipo_utilitario = tipo_utilitario;
	}

	@Override
	public void calcular_ipva() {
		float valor_ipva = super.getValor_venda() * PERCENTUAL_IPVA;
		super.setValorIpva(valor_ipva);
	}

	@Override
	public void calcular_seguro() {
		float valor_seguro = super.getValor_venda() * PERCENTUAL_SEGURO;
		super.setValorSeguro(valor_seguro);
	}
	
	private void calcularAlinhamento() {
		if(super.getKm_rodados() == QUILOMETRO_ALINHAMENTO) {
			this.qtdAlinhamento++;
		}
	}
	
	private void calcularVistoria() {
		if(super.getKm_rodados() == QUILOMETRO_VISTORIA) {
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
