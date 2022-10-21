package business.custos;

public final class CustosCarro extends Custos{
    public static float calcularSeguro(float porcentagem, float valorVenda, float valorAdicional){
        return porcentagem*valorVenda+valorAdicional;
    }
}
