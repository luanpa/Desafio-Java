package lambdas;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Desafio {
	
	public static void main(String[] args) {
		
		//Produto p = new Produto("Ipad", 3235.89, 0.13);
		
		/*
		 * 1. a Partir do produto calcular o preço real com Desconto
		 * 2. Imposto Municipal: >= 2500 (8.5%)/ < 2500 (inseto)
		 * 3. frete: >= 300 (100)/ < 3000 (50)
		 * 4. Arendondar: deixar duas casas decimais
		 * 5. formatar: R$ 1234,56
		 */
		
		Function<Produto, Double> precoFinal = produto -> produto.preco * (1 - produto.desconto);
		UnaryOperator<Double> impostoMunicipal = preco -> preco >= 2500 ? preco * 1.085 : preco;
		UnaryOperator<Double> frete = preco -> preco >= 3000 ? preco + 100 : preco + 50;
		UnaryOperator<Double> arrendodamento = preco -> Double.parseDouble(String.format("%.2f", preco));
		Function<Double, String> formatar = preco -> ("R$" + preco).replace(".", ",");
		
		Produto p = new Produto("Ipad", 3235.89, 0.13);
		String preco = precoFinal
				.andThen(impostoMunicipal)
				.andThen(frete)
				.andThen(arrendodamento)
				.andThen(formatar)
				.apply(p);
				
				System.out.println("o preco final e " + preco);
				
	}

}
