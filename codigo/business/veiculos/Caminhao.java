package business.veiculos;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.CustosCaminhao;

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
        return super.getGastos() +
            "&- Manutenção: R$ " + String.format("%.02f", this.calcularManutencao()) + " (" + ((CustosCaminhao) custosFixo).qtdManutencao() + ")#" +
            "&- Vistoria: R$ " + String.format("%.02f", this.calcularVistoria()) + " (" + ((CustosCaminhao) custosFixo).qtdVistoria() + ")#" +
            "# #GASTO TOTAL: R$ " + String.format("%.02f", super.getGastoTotal());
    }
}
