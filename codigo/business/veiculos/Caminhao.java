package business.veiculos;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.*;

public class Caminhao extends Veiculo {

    private static Capacidades caminhao = Capacidades.CAMINHÃO;

    public Caminhao(String placa, Combustivel combustivel, double valorVenda) throws IllegalArgumentException, ArithmeticException {
        super(placa, combustivel, caminhao, valorVenda); 
        super.custosFixos = new CustosCaminhao(valorVenda);
    }

    @Override
    public double calcularSeguro() {
        return ((CustosCaminhao) this.custosFixos).calcularSeguro();
    }

    @Override
    public double calcularIpva() {
        return ((CustosCaminhao) this.custosFixos).calcularIpva();
    }

    public double calcularManutencao() {
        return ((CustosCaminhao) this.custosFixos).calcularManutencao(super.getKm_rodados());
    }

    public double calcularVistoria() {
        return ((CustosCaminhao) this.custosFixos).calcularVistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return caminhao + " " + super.toString();
    }

    @Override
    public String getGastos() {
        String saida = super.getGastos() +
            "+2&- Manutenção: R$ " + String.format("%.02f", this.calcularManutencao()) + " (" + ((CustosCaminhao) custosFixos).qtdManutencao() + ")#" +
            "+2&- Vistoria: R$ " + String.format("%.02f", this.calcularVistoria()) + " (" + ((CustosCaminhao) custosFixos).qtdVistoria() + ")#";

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
