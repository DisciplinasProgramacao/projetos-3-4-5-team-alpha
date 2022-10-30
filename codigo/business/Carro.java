package business;

import business.custos.CustosCarro;

public class Carro extends Veiculo {
    private static final int TANQUE = 50;
    private final float valorVenda;
    private CustosCarro custosCarro;

    public Carro(String placa, float autonomia, float valorVenda) {
        super(placa, TANQUE, autonomia);
        custosCarro = new CustosCarro(valorVenda);
        this.valorVenda = valorVenda;
    }

    public float getValorVenda() {
        return this.valorVenda;
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
