package business.veiculos;

import business.custos.CustosUtilitario;

public class Utilitario extends Veiculo {
    private String tipo_utilitario;
    private CustosUtilitario custosUtilitario;

    public Utilitario(String placa, String tipo, int tanque, float autonomia, float valor_venda) throws Exception {
        super(placa, tanque, autonomia);

        custosUtilitario = new CustosUtilitario(valor_venda);

        if (tipo.equals("Van") || tipo.equals("Furgão")) {
            this.setTipoUtilitario(tipo);
            
        } else {
            throw new Exception("Os tipos de utilitário podem ser apenas 'Van' ou 'Furgão'");
        }
    }

    public String getTipo_utilitario() {
        return tipo_utilitario;
    }

    public void setTipoUtilitario(String tipo_utilitario) {
        this.tipo_utilitario = tipo_utilitario;
    }

    @Override
    public double getGastos() {
        return calcular_Ipva() + calcular_Seguro() + calcular_Alinhamento() + calcular_Vistoria();
    }

    @Override
    public float calcular_Seguro() {
        return custosUtilitario.calcular_seguro();
    }

    @Override
    public float calcular_Ipva() {
        return custosUtilitario.calcular_Ipva();
    }

    public float calcular_Alinhamento() {
        return custosUtilitario.calcular_Alinhamento(super.getKm_rodados());
    }

    public float calcular_Vistoria() {
        return custosUtilitario.calcular_Vistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return this.getTipo_utilitario() + " - " + super.toString();
    }
}