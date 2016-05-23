package PSO;

import java.util.ArrayList;

import NRP.Solucao;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Iterator;

public class ArquivadorMultiSwarm {	
	private ArrayList<Solucao> arquivador;
	
	public static int var = 1;
	
	private int tamanhoMaximo;
	
	public ArquivadorMultiSwarm(int tamanhoArquivador){		
		this.arquivador = new ArrayList<>();
		this.tamanhoMaximo = tamanhoArquivador;
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
					Solucao s1 = this.arquivador.get(0);					
					for(int i = 1; i < this.arquivador.size(); i++){
						Solucao s2 = this.arquivador.get(i);
						if( s1.getCusto() == s2.getCusto() && 
							s1.getSatisfacao() == s2.getSatisfacao()){
								this.arquivador.remove(s1);
								this.arquivador.add(solucao);
								return true;
						}else{
							s1 = this.arquivador.get(i);
						}
					}
					sucesso = true;
					break;
				default:
					return false;				
				}
			}
			if(sucesso){									
				if(this.tamanhoMaximo == -1 || this.arquivador.size() < this.tamanhoMaximo){										
					this.arquivador.add(solucao);
				}else{
					//Arquivador cheio
					return filter(arquivador, solucao);					
				}							
			}
		}
		return true;							
	}	
	
	/**Verifica se s1 domina s2
	 * 	
	 * @return Se s1 domina s2 retorna 1, se s1 é dominado por s2 retorna 0, s1 não domina s2 retorna -1**/
	public static int domina(Solucao s1, Solucao s2){		
		
		if( s1.getCusto() < s2.getCusto() && 
			s1.getSatisfacao() > s2.getSatisfacao()){			
			return 1;
		}else if(s1.getCusto() >= s2.getCusto() && 
				 s1.getSatisfacao() <= s2.getSatisfacao()){			
			return 0;
		}		
//		System.out.println("Nulo: "+  s1.getSatisfacao()+
//				", "+s2.getSatisfacao()+"/"+s1.getCusto()+", "+s2.getCusto());
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
			DecimalFormat formato = new DecimalFormat("#.#####");
						
			solucao.setDistancia(Double.valueOf(formato.format(distanciaEuclidiana(ideal, objetivos)).replace(',', '.')));
//			solucao.setDistancia(distanciaEuclidiana(ideal, objetivos));
			//solucao.setDistancia(1);
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
		}	
		catch(ArrayIndexOutOfBoundsException ex){
			ex.printStackTrace(); 
			return false;
		}
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
	
	public void salvarArquivador(int pos){
		try {
			System.out.println("Entrou " + var++);
			FileWriter f1 = new FileWriter("Resultados/Arquivador" + pos + ".txt", true);			
			PrintWriter p1 = new PrintWriter(f1);
			
			for (int i = 0; i < this.getArquivadorMultiSwarm().size(); i++) {
				Solucao solucao = this.getArquivadorMultiSwarm().get(i);								
				p1.println(solucao.getSatisfacao() + " " + solucao.getCusto());				
			}												
			p1.println();
			f1.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void limparArquivos(int populacao){
		for(int i = 0; i < populacao/2; i++){
			File file = new File("Resultados/Arquivador"+i+".txt");
			if(file.exists()){
				file.delete();
			}			
		}
	}

}
