package business.veiculos;

import business.Combustivel;
import business.custos.CustosUtilitario;

public class Utilitario extends Veiculo {
    private String tipo_utilitario;
    private CustosUtilitario custosUtilitario;
    

    public Utilitario(String placa, String tipo, float litragemAtual, float capacidadeMaxima, Combustivel combustivel,float valor_venda) throws Exception {
        super(placa, litragemAtual, capacidadeMaxima, combustivel);

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
        return custosUtilitario.calcularCustoTotal();
    }

    @Override
    public float calcularSeguro() {
        return custosUtilitario.calcularSeguro();
    }

    @Override
    public float calcularIpva() {
        return custosUtilitario.calcularIpva();
    }

    public float calcularAlinhamento() {
        return custosUtilitario.calcularAlinhamento(super.getKm_rodados());
    }

    public float calcularVistoria() {
        return custosUtilitario.calcularVistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return this.getTipo_utilitario() + " - " + super.toString();
    }
}