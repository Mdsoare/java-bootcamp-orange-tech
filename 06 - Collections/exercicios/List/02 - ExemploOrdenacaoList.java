/**
* Objetivo: Dadas as seguintes informacoes sobre meus gatos, crie uma lista
* e ordene-a exibindo:
* (nome - idade - cor);
*
* Gato 1 = nome : Jon, idade : 18, cor : preto
* Gato 2 = nome : Simba, idade : 6, cor : tigrado
* Gato 3 = nome : Jon, idade : 12, cor : amarelo
*
* @version 1.0
* @since 2023-03-12
*/
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Comparator; 
import java.util.List; 


public class ExemploOrdenacaoList {
	
	public static void main(String[] args){
		
		List<Gato> meusGatos = new ArrayList<>(){{
			add(new Gato("Jon", 18, "preto"));
			add(new Gato("Simba", 6, "tigrado"));
			add(new Gato("Jon", 12, "amarelo"));
		}};
		
		System.out.println("--\tOrdem de Insercao\t--");
		System.out.println(meusGatos);
		
		
		System.out.println("--\tOrdem aleatoria\t--");
		Collections.shuffle(meusGatos);
		System.out.println(meusGatos);
		
		
		System.out.println("--\tOrdem Natural (Nome)\t--");
		Collections.sort(meusGatos);
		System.out.println(meusGatos);
		
		System.out.println("--\tOrdem Idade\t--");
		Collections.sort(meusGatos, new ComparatorIdade()); 
		// meusGatos.sort(new ComparatorIdade()); // Tb funciona substituindo a linha aciona "Collections.sort(meusGatos, new ComparatorIdade());"
		System.out.println(meusGatos);
		
		System.out.println("--\tOrdem Cor\t--");
		meusGatos.sort(new ComparatorCor());
		System.out.println(meusGatos);
				
		System.out.println("--\tOrdem Nome/Cor/Idade\t--");
		meusGatos.sort(new ComparatorNomeCorIdade());
		System.out.println(meusGatos);
	}
}

class Gato implements Comparable<Gato>{ //Define a classe Gato e implementa a interface Comparable com tipo de argumento Gato
	private String nome;
	private Integer idade;
	private String cor;
	
	public Gato(String nome, Integer idade, String cor) { 
		this.nome = nome; 
		this.idade = idade; 
		this.cor = cor; 
	}
	
	public String getNome() { 
		return nome;
	}

	public Integer getIdade() {
		return idade; 
	}

	public String getCor() { 
		return cor; 
	}
	
	@Override
	public String toString(){ // Necessario porque os atributos sao private. Do contrario seriam impressos os enderecos de memoria
		return "{" +
				"nome='" + nome + '\'' +
				", idade='" + idade + '\'' +
				", cor='" + cor + '\'' +
				'}';
	}
	
	@Override
	public int compareTo(Gato gato) { // Implementacao do metodo compareTo da interface Comparable com tipo de argumento Gato
		return this.getNome().compareToIgnoreCase(gato.getNome());
	}

}

class ComparatorIdade implements Comparator<Gato>{ // Necessario pq ja existe uma comparacao com o metodo compareTo
	@Override
	public int compare(Gato g1, Gato g2){
		return Integer.compare(g1.getIdade(), g2.getIdade());
	}
}

class ComparatorCor implements Comparator<Gato>{ 
	@Override
	public int compare(Gato g1, Gato g2){
		return g1.getCor().compareToIgnoreCase(g2.getCor());
	}
}
	
class ComparatorNomeCorIdade implements Comparator<Gato>{ 
	@Override
	public int compare(Gato g1, Gato g2){
			
		int nome = g1.getNome().compareToIgnoreCase(g2.getNome());
		if (nome != 0 ) return nome;
		
		int cor = g1.getCor().compareToIgnoreCase(g2.getCor());
		if (cor != 0 ) return cor;
		
		return Integer.compare(g1.getIdade(), g2.getIdade());
	}
}