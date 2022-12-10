package business.veiculos;

import java.util.ArrayList;
import java.util.List;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.*;

public class Utilitario extends Veiculo {
    static List<Capacidades> tiposUtilitario;
    private Capacidades tipo_utilitario;
    
    static {
        tiposUtilitario = new ArrayList<Capacidades>();
        tiposUtilitario.add(Capacidades.FURGÃO);
        tiposUtilitario.add(Capacidades.VAN);
    }

    public Utilitario(String placa, Capacidades tipo, Combustivel combustivel, double valor_venda) throws NoSuchFieldException, ArithmeticException, IllegalArgumentException  {
        super(placa, combustivel, tipo);
        
        super.custosFixo = new CustosUtilitario(valor_venda);
        
        if(tiposUtilitario.contains(tipo)) {
            this.setTipoUtilitario(tipo);
        } else {
            throw new NoSuchFieldException ("Os tipos de utilitário podem ser apenas \"Van\" ou \"Furgão\"");
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
        String saida = super.getGastos() +
        "+2&- Alinhamento: R$ " + String.format("%.02f", this.calcularAlinhamento()) + " (" + ((CustosUtilitario) custosFixo).qtdAlinhamento() + ")#" +
        "+2&- Vistoria: R$ " + String.format("%.02f", this.calcularVistoria()) + " (" + ((CustosUtilitario) custosFixo).qtdVistoria() + ")#";

        if(super.getCustosAdicionais().size() > 0) {
            saida += "# #+2&CUSTOS ADICIONAIS:";
            int index = 0;
            for(Custos custoAdicional : super.getCustosAdicionais()) {
                saida += "#+3&" + ++index + ". " + ((CustosVariaveis) custoAdicional).getDescricao() + " (R$ " + String.format("%.02f", custoAdicional.calcularCustoTotal()) + ")";
            }
        }

        return saida + "# #&GASTO TOTAL: R$ " + String.format("%.02f", super.getGastoTotal());
    }
}