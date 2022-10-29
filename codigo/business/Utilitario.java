package business;

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
    private CustosUtilitario custosUtilitario;

    public Utilitario(String placa, String tipo, int tanque, float autonomia, float valor_venda) throws Exception {
        super(placa, tanque, autonomia, valor_venda, PERCENTUAL_IPVA);

        if (tipo.equals("Van") || tipo.equals("Furgão")) {
            this.setTipoUtilitario(tipo);
        } else {
            throw new Exception("Os tipos de utilitário podem ser apenas 'Van' ou 'Furgão'");
        }
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
        float gastos = super.calcular_Ipva();
        gastos += calcular_Seguro();
        gastos += getValor_Alinhamento();
        gastos += calcular_Vistoria();

        return gastos;
    }

    @Override
    public float calcular_Seguro() {
        custosUtilitario = new CustosUtilitario(PERCENTUAL_SEGURO, super.getValor_venda());
        return custosUtilitario.getSeguro();
    }

    public float calcular_Alinhamento() {
        return custosUtilitario.calcularCusto(VALOR_ALINHAMENTO, QUILOMETRO_ALINHAMENTO, super.getKm_rodados(), qtdAlinhamento++);
    }

    public float calcular_Vistoria() {
        return custosUtilitario.calcularCusto(VALOR_VISTORIA, QUILOMETRO_VISTORIA, super.getKm_rodados(), qtdVistoria++);
    }
}