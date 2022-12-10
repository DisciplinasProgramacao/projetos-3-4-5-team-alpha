package business.veiculos;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.*;

public class Carro extends Veiculo {

    private static Capacidades carro = Capacidades.CARRO;

    public Carro(String placa, Combustivel combustivel, double valor_venda) throws ArithmeticException, IllegalArgumentException {
        super(placa, combustivel, carro);
        
        super.custosFixo = new CustosCarro(valor_venda);
    }

    @Override
    public double calcularSeguro() {
        return ((CustosCarro) this.custosFixo).calcularSeguro();
    }

    @Override
    public double calcularIpva() {
        return ((CustosCarro) this.custosFixo).calcularIpva();
    }

    public double calcularAlinhamento() {
        return ((CustosCarro) this.custosFixo).calcularAlinhamento(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return carro + " " + super.toString();
    }

    @Override
    public String getGastos() {
        String saida = super.getGastos() +
        "+2&- Alinhamento: R$ " + String.format("%.02f", this.calcularAlinhamento()) + " (" + ((CustosCarro) custosFixo).qtdAlinhamento() + ")";
        
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
