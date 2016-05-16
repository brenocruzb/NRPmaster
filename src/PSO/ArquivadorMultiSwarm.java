package PSO;

import java.util.ArrayList;

import NRP.Solucao;

import java.math.BigDecimal;
import java.util.Iterator;

public class ArquivadorMultiSwarm {	
	private ArrayList<Solucao> arquivador;
	
	private static final int TAMANHO_MAXIMO = 10;
	
	public ArquivadorMultiSwarm(){		
		this.arquivador = new ArrayList<>();				
	}
	
	public ArrayList<Solucao> getArquivadorMultiSwarm(){
		return this.arquivador;
	}	
	
	public boolean inserirLider(Solucao solucao){
		boolean sucesso = false;
		if(this.arquivador.size() == 0){
			this.arquivador.add(solucao);			
		}else{
			for(Solucao s : this.arquivador){
				int domina = ArquivadorMultiSwarm.domina(solucao, s);
				switch (domina) {
				case 1:
					this.arquivador.remove(s);
					this.arquivador.add(solucao);
					return true;
				case -1:
					if(ArquivadorMultiSwarm.TAMANHO_MAXIMO == -1 || this.arquivador.size() < ArquivadorMultiSwarm.TAMANHO_MAXIMO){										
						sucesso = true;
						break;
					}else{
						//Arquivador cheio
						return filter(arquivador, solucao);
					}
				default:
					return false;					
				}
			}
			if(sucesso){
				this.arquivador.add(solucao);
			}
		}
		return true;							
	}
	
	public int getTamanhoMaximo(){ return ArquivadorMultiSwarm.TAMANHO_MAXIMO; }
	
	/**Verifica se s1 domina s2
	 * 	
	 * @return Se s1 domina s2 retorna 1, se s1 é dominado por s2 retorna 0, s1 não domina s2 retorna -1**/
	public static int domina(Solucao s1, Solucao s2){
		if( s1.getCusto() <= s2.getCusto() && 
			s1.getSatisfacao() >= s2.getSatisfacao()){
			return 1;
		}else if(s1.getCusto() > s2.getCusto() && 
				 s1.getSatisfacao() < s2.getSatisfacao()){
			return 0;
		}		
		return -1;
	}
	
	
	public boolean atualizarArquivador(Solucao solucao) {						
		return this.inserirLider(solucao);				
	}
	
	public void copiarAquivador(ArquivadorMultiSwarm arquivador){	
		for(Solucao s : arquivador.getArquivadorMultiSwarm()){
			this.atualizarArquivador(s);
		}		
	}
	
	
/** 
 * Basicamente, essa função filter vai ser executada quando o arquivo ficar cheio e uma nova solução não dominada tenta entrar
*  Front é uma lista das soluções não dominadas (é imporante que sejam não dominadas).
*  new_solution é a nova solução não dominada que tenta entrar.
*  O método vai calcular o vetor ideal e retirar a solução mais longe do ideal.
*/
	public boolean filter(ArrayList<Solucao> arquivador, Solucao new_solution) {

		//Obtains the ideal solution from the current front
		double[] ideal = obterIdeal(arquivador);		

		arquivador.add(new_solution);
		//Para cada solucao calcula sua distancia em relacao a solucao ideal
		//For each solution on the front, it calculates the distance to the ideal point
		for (Iterator<Solucao> iterator = arquivador.iterator(); iterator.hasNext();) {
			Solucao solucao = iterator.next();
			double[] objetivos = {solucao.getCusto(), solucao.getSatisfacao()};
			solucao.setDistancia(distanciaEuclidiana(ideal, objetivos));
			//Round up the distance
			BigDecimal b = new BigDecimal(Solucao.MENOR_DISTANCIA);//Inicializar Menor Distancia		 
			solucao.setDistancia((b.setScale(5, BigDecimal.ROUND_UP)).doubleValue());
		}

		double highDistanceValue = 0;
		int index = -1;
		for (int i = 0; i<arquivador.size(); i++) {
			Solucao solucao = arquivador.get(i);
			if(solucao.getDistancia() >= highDistanceValue){
				highDistanceValue = solucao.getDistancia();
				index = i;
			}
		}
		try{
			arquivador.remove(index);
		}	catch(ArrayIndexOutOfBoundsException ex){ex.printStackTrace(); return false;}

		return true;
	}


	public double[] obterIdeal(ArrayList<Solucao> arquivador){
						
		double[] ideal = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
					
		for(Solucao solucao : arquivador){
			double[] objetivos = {solucao.getCusto(), solucao.getSatisfacao()};
			
			for(int j = 0; j < 2; j++){
				if(objetivos[j] <= ideal[j]){
					ideal[j] = objetivos[j];
				}
			}			
		}
		
		return ideal;		
	}


	public static double distanciaEuclidiana(double[] vetor1, double[] vetor2){
		double soma = 0;
		for (int i = 0; i < vetor1.length; i++) {
			soma += Math.pow(vetor1[i]-vetor2[i],2);
		}
		return Math.sqrt(soma);
	}

}
