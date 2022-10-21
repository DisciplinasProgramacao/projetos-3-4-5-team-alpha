package business.custos;

public class Custos {
    
    public static float calcular(float valor, int distancia, int pecorrida){
        return valor*(distancia/pecorrida);
    }
    public static float calcularIpva(float porcentagem, float valorVenda){
        return porcentagem*valorVenda;
    }
}
