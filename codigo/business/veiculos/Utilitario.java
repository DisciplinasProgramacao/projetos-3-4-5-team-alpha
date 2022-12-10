package business.veiculos;

import java.util.ArrayList;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.CustosUtilitario;

public class Utilitario extends Veiculo {
    static ArrayList<Capacidades> tiposUtilitario;
    private Capacidades tipo_utilitario;
    
    static {
        tiposUtilitario = new ArrayList<Capacidades>();
        tiposUtilitario.add(Capacidades.FURGAO);
        tiposUtilitario.add(Capacidades.VAN);
    }

    public Utilitario(String placa, Capacidades tipo, Combustivel combustivel, double valor_venda) throws NoSuchFieldException, ArithmeticException  {
        super(placa, combustivel, tipo);
        super.custosFixo = new CustosUtilitario(valor_venda);

        if(tiposUtilitario.contains(tipo)){
            this.setTipoUtilitario(tipo);
        } else {
            throw new NoSuchFieldException ("Os tipos de utilitário podem ser apenas 'Van' ou 'Furgão'");
        }
    }

    public Capacidades getTipo_utilitario() {
        return tipo_utilitario;
    }

    public void setTipoUtilitario(Capacidades tipo_utilitario) {
        this.tipo_utilitario = tipo_utilitario;
    }

    @Override
    public double calcularSeguro() {
        return ((CustosUtilitario) this.custosFixo).calcularSeguro();
    }

    @Override
    public double calcularIpva() {
        return ((CustosUtilitario) this.custosFixo).calcularIpva();
    }

    public double calcularAlinhamento() {
        return ((CustosUtilitario) this.custosFixo).calcularAlinhamento(super.getKm_rodados());
    }

    public double calcularVistoria() {
        return ((CustosUtilitario) this.custosFixo).calcularVistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return this.getTipo_utilitario() + " " + super.toString();
    }

    @Override
    public String getGastos() {
        return super.getGastos() +
            "&- Alinhamento: R$ " + String.format("%.02f", this.calcularAlinhamento()) + " (" + ((CustosUtilitario) custosFixo).qtdAlinhamento() + ")#" +
            "&- Vistoria: R$ " + String.format("%.02f", this.calcularVistoria()) + " (" + ((CustosUtilitario) custosFixo).qtdVistoria() + ")#" +
            "#GASTO TOTAL: R$ " + String.format("%.02f", super.getGastoTotal());
    }
}