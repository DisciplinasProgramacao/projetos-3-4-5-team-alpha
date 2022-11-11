package business.veiculos;

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

    @Override
    public double getGastos() {
        return calcular_Ipva() + calcular_Seguro() + calcular_Alinhamento();
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
