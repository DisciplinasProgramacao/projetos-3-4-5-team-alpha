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

    public float getValor_Alinhamento() {
        return this.qtdAlinhamento * VALOR_ALINHAMENTO;
    }

    public float getValor_vistoria() {
        return this.qtdVistoria * VALOR_VISTORIA;
    }

    public void setTipoUtilitario(String tipo_utilitario) {
        this.tipo_utilitario = tipo_utilitario;
    }

    @Override
    public float getGastos() {
        float gastos = super.calcular_ipva();
        gastos += calcular_seguro();
        gastos += getValor_Alinhamento();
        gastos += getValor_vistoria();

        return gastos;
    }

    @Override
    public float calcular_seguro() {
        custos = new CustosUtilitario(PERCENTUAL_SEGURO, super.getValor_venda());
        return custos.getSeguro();
    }

    public void calcular_alinhamento(int distancia) {
        if (super.getKm_rodados() >= (QUILOMETRO_ALINHAMENTO * qtdAlinhamento)) {
            int manutencao = distancia / QUILOMETRO_ALINHAMENTO;
            this.qtdAlinhamento += manutencao;
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
            calcular_alinhamento(rota.getDistancia());
            calcular_vistoria(rota.getDistancia());
        }
    }

}