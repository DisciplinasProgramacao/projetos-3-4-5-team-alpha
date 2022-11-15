package business.veiculos;

import business.Combustivel;
import business.custos.CustosCarro;

public class Carro extends Veiculo {
    private CustosCarro custosCarro;

    public Carro(String placa, float litragemAtual, float capacidadeMaxima, Combustivel combustivel,float valor_venda) {
        super(placa, litragemAtual, capacidadeMaxima, combustivel);
        custosCarro = new CustosCarro(valor_venda);
    }

    @Override
    public double getGastos() {
        return custosCarro.calcularCustoTotal();
    }

    @Override
    public float calcularSeguro() {
        return custosCarro.calcularSeguro();
    }

    @Override
    public float calcularIpva() {
        return custosCarro.calcularIpva();
    }

    public float calcularAlinhamento() {
        return custosCarro.calcularAlinhamento(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return "Carro - " + super.toString();
    }
}
