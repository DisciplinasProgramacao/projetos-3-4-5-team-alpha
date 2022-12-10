package business.veiculos;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.CustosCarro;

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
        return super.getGastos() +
            "+&- Alinhamento: R$ " + String.format("%.02f", this.calcularAlinhamento()) + " (" + ((CustosCarro) custosFixo).qtdAlinhamento() + ")" +
            "# #&GASTO TOTAL: R$ " + String.format("%.02f", super.getGastoTotal());
    }
}
