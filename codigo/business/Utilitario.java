package business;

import javax.sound.midi.Soundbank;

import business.custos.CustosUtilitario;

public class Utilitario extends Veiculo {
    private static final float VALOR_ALINHAMENTO = 120.0F,
            VALOR_VISTORIA = 1000.0F,
            PERCENTUAL_IPVA = 0.03F,
            PERCENTUAL_SEGURO = 0.03F;
    private static final int QUILOMETRO_ALINHAMENTO = 10000,
            QUILOMETRO_VISTORIA = 10000;
    private int qtdVistoria, qtdAlinhamento;
    private String tipo_utilitario;
    private CustosUtilitario custos;

    public Utilitario(String placa, String tipo, int tanque, float autonomia, float valor_venda) throws Exception {
        super(placa, tanque, autonomia, valor_venda, PERCENTUAL_IPVA);

        if (tipo.equals("Van") || tipo.equals("Furgão")) {
            this.setTipoUtilitario(tipo);
        } else {
            throw new Exception("Os tipos de utilitário podem ser apenas 'Van' ou 'Furgão'");
        }

        this.calcular_ipva();
        this.calcular_seguro();
    }

    public String getTipo_utilitario() {
        return tipo_utilitario;
    }

    public float getAlinhamento() {
        return custos.getCustosAdicionais();
    }

    public float getVistoria() {
        return custos.getCustosAdicionais();
    }

    public void setTipoUtilitario(String tipo_utilitario) {
        this.tipo_utilitario = tipo_utilitario;
    }

    @Override
    public float getGastos() {
        float gastos = super.calcular_ipva();
        gastos += calcular_seguro();
        gastos += getAlinhamento();
        gastos += getVistoria();

        return gastos;
    }

    @Override
    public float calcular_seguro() {
        custos = new CustosUtilitario(PERCENTUAL_SEGURO, super.getValor_venda());
        return custos.getSeguro();
    }

    @Override
    public float getGastosAdicionais() {
        return 0;
    }

    public void calcular_alinhamento() throws Exception {
        custos.calcular(VALOR_ALINHAMENTO, QUILOMETRO_ALINHAMENTO, super.getKm_rodados());
        this.qtdAlinhamento = ((int) super.getKm_rodados() / qtdAlinhamento);

    }

    public void calcular_vistoria() throws Exception {
        if (super.getKm_rodados() >= (QUILOMETRO_VISTORIA * qtdVistoria)) {
            custos.calcular(VALOR_VISTORIA, QUILOMETRO_VISTORIA, super.getKm_rodados());
            this.qtdVistoria++;
        } else {
            throw new Exception();
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

    public String toString() {
        return (super.toString() + "Gastos: " + this.getGastos());
    }
}
