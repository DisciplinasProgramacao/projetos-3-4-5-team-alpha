package business.veiculos;

import business.Enuns.Capacidades;
import business.Enuns.Combustivel;
import business.custos.CustosCaminhao;

public class Caminhao extends Veiculo {

    public Caminhao(String placa, Combustivel combustivel,float valor_venda) throws Exception {
        super(placa, combustivel, Capacidades.CAMINHAO);
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
