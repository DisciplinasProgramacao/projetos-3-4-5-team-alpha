package business.custos;

public final class CustosCaminhao extends Custos{

    public static float calcularSeguro(float porcentagem, float valorVenda, float valorAdicional){
        return porcentagem*valorVenda+valorAdicional;
    }
}
