package business.veiculos;

import java.util.ArrayList;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.CustosUtilitario;

public class Utilitario extends Veiculo {
    static ArrayList<Capacidades> tiposUtilitario;
    private Capacidades tipo_utilitario;
    
    static{
        tiposUtilitario = new ArrayList<Capacidades>();
        tiposUtilitario.add(Capacidades.FURGAO);
        tiposUtilitario.add(Capacidades.VAN);
    }

    public Utilitario(String placa, Capacidades tipo, Combustivel combustivel, float valor_venda) throws NoSuchFieldException, ArithmeticException  {
        super(placa, combustivel, tipo);
        

        super.custosFixo = new CustosUtilitario(valor_venda);

      if(tiposUtilitario.contains(tipo)){
            this.setTipoUtilitario(tipo);
        }
        else {
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