package business.veiculos;

import business.custos.CustosCaminhao;

public class Caminhao extends Veiculo {
    private static final int TANQUE = 250;
    private final float valorVenda;
    private CustosCaminhao custosCaminhao;

    public Caminhao(String placa, float autonomia, float valor_venda) {
        super(placa, TANQUE, autonomia);
        this.valorVenda = valor_venda;
        custosCaminhao = new CustosCaminhao(valor_venda);
    }
    
    @Override
    public double getGastos() {
        return calcular_Ipva() + calcular_Seguro() + calcular_Manutencao() + calcular_Vistoria();
    }

    @Override
    public float calcular_Seguro() {
        return custosCaminhao.calcular_seguro();
    }

    @Override
    public float calcular_Ipva() {
        return custosCaminhao.calcular_Ipva();
    }

    public float calcular_Manutencao() {
        return custosCaminhao.calcular_Manutencao(super.getKm_rodados());
    }

    public float calcular_Vistoria() {
        return custosCaminhao.calcular_Vistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return "Caminhão - " + super.toString();
    }
}
