package business.veiculos;

import business.Combustivel;
import business.custos.CustosUtilitario;

public class Utilitario extends Veiculo {
    private String tipo_utilitario;
    
    public Utilitario(String placa, String tipo, float litragemAtual, float capacidadeMaxima, Combustivel combustivel,float valor_venda) throws Exception {
        super(placa, litragemAtual, capacidadeMaxima, combustivel);

        super.custosFixo = new CustosUtilitario(valor_venda);

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
    public float calcularSeguro() {
        return ((CustosUtilitario) this.custosFixo).calcularSeguro();
    }

    @Override
    public float calcularIpva() {
        return ((CustosUtilitario) this.custosFixo).calcularIpva();
    }

    public float calcularAlinhamento() {
        return ((CustosUtilitario) this.custosFixo).calcularAlinhamento(super.getKm_rodados());
    }

    public float calcularVistoria() {
        return ((CustosUtilitario) this.custosFixo).calcularVistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return this.getTipo_utilitario() + " - " + super.toString();
    }
}