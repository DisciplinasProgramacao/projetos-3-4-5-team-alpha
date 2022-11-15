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
    public double getGastos() {
        return custosCaminhao.calcularCustoTotal();
    }

    @Override
    public float calcularSeguro() {
        return custosCaminhao.calcularSeguro();
    }

    @Override
    public float calcularIpva() {
        return custosCaminhao.calcularIpva();
    }

    public float calcularManutencao() {
        return custosCaminhao.calcularManutencao(super.getKm_rodados());
    }

    public float calcularVistoria() {
        return custosCaminhao.calcularVistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return "Caminhão - " + super.toString();
    }
}
