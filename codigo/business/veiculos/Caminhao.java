package business.veiculos;

import business.Combustivel;
import business.custos.CustosCaminhao;

public class Caminhao extends Veiculo {
    private CustosCaminhao custosCaminhao;

    public Caminhao(String placa, float litragemAtual, float capacidadeMaxima, Combustivel combustivel,float valor_venda) {
        super(placa, litragemAtual, capacidadeMaxima, combustivel);
        custosCaminhao = new CustosCaminhao(valor_venda);
    }
    
    @Override
    public float getGastos() {
        float gastos = calcular_Ipva();
        gastos += calcular_Seguro();
        gastos += calcular_Manutencao();
        gastos += calcular_Vistoria();

        return gastos;
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
