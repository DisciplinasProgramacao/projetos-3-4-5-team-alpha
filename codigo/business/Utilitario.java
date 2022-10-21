package business;

import business.custos.CustosUtilitario;

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

        if (tipo.equals("Van") || tipo.equals("Furgão")) {
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
        float gastos = super.calcular_ipva();
        gastos += calcular_seguro();
        gastos += getValor_alinhamento();
        gastos += getValor_vistoria();

        return gastos;
    }

    @Override
    public float calcular_seguro() {
        return CustosUtilitario.calcularSeguro(PERCENTUAL_SEGURO,super.getValor_venda() );
    }

    
    public void calcular_alinhamento() {
		if(super.getKm_rodados() >= QUILOMETRO_ALINHAMENTO) {
			CustosUtilitario.calcular(VALOR_ALINHAMENTO, QUILOMETRO_ALINHAMENTO, getKm_rodados() );
		}
	}

    public void calcular_vistoria() {
        if (super.getKm_rodados() == QUILOMETRO_VISTORIA) {
            this.qtdVistoria++;
        }
    }

    @Override
    public void setRota(Rota rota) throws Exception {
        if ((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_ALINHAMENTO) {
            if ((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_VISTORIA) {
                super.setRota(rota);
            } else {
                throw new Exception("Essa viagem excede os km necessário para o alinhamento, por favor realiza-la");
            }
        } else {
            throw new Exception("Essa viagem excede os km necessário para a vistoria, por favor realiza-la");
        }
    }
}
