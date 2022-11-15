package business.veiculos;

import business.Combustivel;
import business.custos.CustosCarro;

public class Carro extends Veiculo {
    public Carro(String placa, float litragemAtual, float capacidadeMaxima, Combustivel combustivel,float valor_venda) {
        super(placa, litragemAtual, capacidadeMaxima, combustivel);
        super.custosFixo = new CustosCarro(valor_venda);
    }

    @Override
    public float calcularSeguro() {
        return ((CustosCarro) this.custosFixo).calcularSeguro();
    }

    @Override
    public float calcularIpva() {
        return ((CustosCarro) this.custosFixo).calcularIpva();
    }

    public float calcularAlinhamento() {
        return ((CustosCarro) this.custosFixo).calcularAlinhamento(super.getKm_rodados());
    }

    @Override
    public String toString() {
        return "Carro - " + super.toString();
    }
}
