package PSO;

import java.util.ArrayList;
import java.util.Iterator;

import NRP.Solucao;

public class EscolherMetodoSigma{

	public static void escolherLideres(Particula[] populacao, ArrayList<Solucao> lideres) {
		for (Iterator<Solucao> iter = lideres.iterator(); iter.hasNext();) {
			Solucao solucaotRepositorio =  iter.next();
			calcularSigmaVector(solucaotRepositorio);
		}
		
		for(int i = 0; i < populacao.length; i++){
			Particula particula = populacao[i];
			calcularSigmaVector(particula.getPosicao());
			escolherGlobalBestSigma(particula, lideres);
		}						
	}

	//Criar a variável double sigmaVectort
	private static void calcularSigmaVector(Solucao solucao){

		double satisf_2 = solucao.getSatisfacao()* solucao.getSatisfacao();
		double custo_2 = solucao.getCusto()*solucao.getCusto(); 	

		solucao.setSigmaVector((satisf_2 - custo_2) / (satisf_2 + custo_2));		
	}


	/**
	 * Método que escolhe qual particula do repositorio sera escolhida como global best
	 * Escolhe a partícula probabilisticamente atraves de uma roleta com os valores da
	 * distância Euclidiana dos sigmaVector
	 */
	private static void escolherGlobalBestSigma(Particula particula, ArrayList<Solucao> repositorio){
		double melhorValor = Double.MAX_VALUE;
		Solucao lBest = null; 
		//Calcula o valor da distancia euclidia dos sigmaVector de cada particula do repositorio
		//e escolhe a menor
		for (Iterator<Solucao> iter = repositorio.iterator(); iter.hasNext();) {
			Solucao rep = iter.next();
			double temp = Math.abs(particula.getPosicao().getSigmaVector() - rep.getSigmaVector());
			if(temp<melhorValor){
				melhorValor = temp;
				lBest = rep;
			}
		}
		
		particula.setLBest(lBest);
	}

}

