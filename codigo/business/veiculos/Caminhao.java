package business.veiculos;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.*;

public class Caminhao extends Veiculo {

    private static Capacidades caminhao = Capacidades.CAMINHÃO;

    public Caminhao(String placa, Combustivel combustivel, double valor_venda) throws IllegalArgumentException, ArithmeticException {
        super(placa, combustivel, caminhao); 
        super.custosFixo = new CustosCaminhao(valor_venda);
    }

    @Override
    public double calcularSeguro() {
        return ((CustosCaminhao) this.custosFixo).calcularSeguro();
    }

    @Override
    public double calcularIpva() {
        return ((CustosCaminhao) this.custosFixo).calcularIpva();
    }

    public double calcularManutencao() {
        return ((CustosCaminhao) this.custosFixo).calcularManutencao(super.getKm_rodados());
    }

    public double calcularVistoria() {
        return ((CustosCaminhao) this.custosFixo).calcularVistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return caminhao + " " + super.toString();
    }

    @Override
    public String getGastos() {
        String saida = super.getGastos() +
            "+2&- Manutenção: R$ " + String.format("%.02f", this.calcularManutencao()) + " (" + ((CustosCaminhao) custosFixo).qtdManutencao() + ")#" +
            "+2&- Vistoria: R$ " + String.format("%.02f", this.calcularVistoria()) + " (" + ((CustosCaminhao) custosFixo).qtdVistoria() + ")#";

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
