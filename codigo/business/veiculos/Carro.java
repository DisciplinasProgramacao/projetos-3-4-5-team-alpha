package business.veiculos;

import business.Capacidades;
import business.Combustivel;
import business.custos.CustosCarro;

public class Carro extends Veiculo {
    public Carro(String placa, Combustivel combustivel,float valor_venda) throws Exception {
        super(placa, combustivel, Capacidades.CARRO);
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
