package business.veiculos;

import java.util.ArrayList;
import java.util.List;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.*;

public class Utilitario extends Veiculo {
    private static List<Capacidades> tiposUtilitarios;
    private Capacidades tipo_utilitario;
    
    static {
        tiposUtilitarios = new ArrayList<Capacidades>();
        tiposUtilitarios.add(Capacidades.FURGÃO);
        tiposUtilitarios.add(Capacidades.VAN);
    }

    public Utilitario(String placa, Capacidades tipo, Combustivel combustivel, double valorVenda) throws NoSuchFieldException, ArithmeticException, IllegalArgumentException  {
        super(placa, combustivel, tipo, valorVenda);
        
        super.custosFixos = new CustosUtilitario(valorVenda);
        
        if(tiposUtilitarios.contains(tipo)) {
            this.setTipoUtilitario(tipo);
        } else {
            throw new NoSuchFieldException ("Os tipos de utilitário podem ser apenas \"Van\" ou \"Furgão\"");
        }
    }

    @Override
    public String tipoVeiculo() {
        return tipo_utilitario.toString();
    }

    public Capacidades getTipo_utilitario() {
        return tipo_utilitario;
    }

    public void setTipoUtilitario(Capacidades tipo_utilitario) {
        this.tipo_utilitario = tipo_utilitario;
    }

    @Override
    public double calcularSeguro() {
        return ((CustosUtilitario) this.custosFixos).calcularSeguro();
    }

    @Override
    public double calcularIpva() {
        return ((CustosUtilitario) this.custosFixos).calcularIpva();
    }

    public double calcularAlinhamento() {
        return ((CustosUtilitario) this.custosFixos).calcularAlinhamento(super.getKm_rodados());
    }

    public double calcularVistoria() {
        return ((CustosUtilitario) this.custosFixos).calcularVistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return this.getTipo_utilitario() + " " + super.toString();
    }

    @Override
    public String getGastos() {
        String saida = super.getGastos() +
        "+2&- Alinhamento: R$ " + String.format("%.02f", this.calcularAlinhamento()) + " (" + ((CustosUtilitario) custosFixos).qtdAlinhamento() + ")#" +
        "+2&- Vistoria: R$ " + String.format("%.02f", this.calcularVistoria()) + " (" + ((CustosUtilitario) custosFixos).qtdVistoria() + ")#";

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