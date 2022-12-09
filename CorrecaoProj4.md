# Projeto 4 - Gestão de Frota (parte 2)

    
## Nota: 17,7

## Comentários
	- cuidado com string em construtor de utilitário. Isso leva a 'if' de tipo (viola LSP)
	- ainda sem arquivo de dados para teste... na parte 5 isso desconta pontos
	
## Correção

### Modelagem: 2/2   
	- Modularização de tanque e combustível 
	
### Requisitos dos veículos, de acordo com a modelagem: 7/9  
	- Restrição de combustível: 1,5/3 -- não restringiu carro com Diesel, por exemplo. Lembrem-se que uma classe pode ser acessada de fora do app. 
	- Abastecimento e autonomia: 3/3
	- Custos fixos e variáveis: 2,5/3 -- um custo precisa ter ao menos uma descrição, né?
	
	
### Requisitos da empresa no programa principal: 8,7/9 
	- Quilometragem média das rotas da empresa: 3/3 
	- Filtro para busca de rotas por data: 2,7/3 -- ok, mas mostrem a informação direito. Senão, para que fazer uma GUI??
	- Um dos dois abaixo: 3/3
		- Os 3 veículos que mais fizeram rotas 
		- Lista de veículos ordenada decrescentemente por custos gerados 

