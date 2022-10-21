package business.custos;

public final class CustosUtilitario extends Custos {
    public static float calcularSeguro(float porcentagem, float valorVenda){
        return porcentagem*valorVenda;
    }
}
