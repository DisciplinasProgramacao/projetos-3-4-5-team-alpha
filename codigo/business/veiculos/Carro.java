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
    public float getGastos() {
        float gastos = calcular_Ipva();
        gastos += calcular_Seguro();
        gastos += calcular_Alinhamento();

        return gastos;
    }

    @Override
    public float calcular_Seguro() {
        return custosCarro.calcular_seguro();
    }

    @Override
    public float calcular_Ipva() {
        return custosCarro.calcular_Ipva();
    }

    public float calcular_Alinhamento() {
        return custosCarro.calcular_Alinhamento(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return "Carro - " + super.toString();
    }
}
