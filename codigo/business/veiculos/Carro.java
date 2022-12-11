package business.veiculos;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.*;

public class Carro extends Veiculo {

    private static Capacidades carro = Capacidades.CARRO;

    public Carro(String placa, Combustivel combustivel, double valorVenda) throws ArithmeticException, IllegalArgumentException {
        super(placa, combustivel, carro, valorVenda);
        
        super.custosFixos = new CustosCarro(valorVenda);
    }

    @Override
    public String tipoVeiculo() {
        return carro.toString();
    }

    @Override
    public double calcularSeguro() {
        return ((CustosCarro) this.custosFixos).calcularSeguro();
    }

    @Override
    public double calcularIpva() {
        return ((CustosCarro) this.custosFixos).calcularIpva();
    }

    public double calcularAlinhamento() {
        return ((CustosCarro) this.custosFixos).calcularAlinhamento(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return carro + " " + super.toString();
    }

    @Override
    public String getGastos() {
        String saida = super.getGastos() +
        "+2&- Alinhamento: R$ " + String.format("%.02f", this.calcularAlinhamento()) + " (" + ((CustosCarro) custosFixos).qtdAlinhamento() + ")";
        
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
