package business.veiculos;

import business.Combustivel;
import business.custos.CustosCaminhao;

public class Caminhao extends Veiculo {

    public Caminhao(String placa, float litragemAtual, float capacidadeMaxima, Combustivel combustivel,float valor_venda) {
        super(placa, litragemAtual, capacidadeMaxima, combustivel);
        super.custosFixo = new CustosCaminhao(valor_venda);
    }

    @Override
    public float calcularSeguro() {
        return ((CustosCaminhao) this.custosFixo).calcularSeguro();
    }

    @Override
    public float calcularIpva() {
        return ((CustosCaminhao) this.custosFixo).calcularIpva();
    }

    public float calcularManutencao() {
        return ((CustosCaminhao) this.custosFixo).calcularManutencao(super.getKm_rodados());
    }

    public float calcularVistoria() {
        return ((CustosCaminhao) this.custosFixo).calcularVistoria(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return "Caminhão - " + super.toString();
    }
}
