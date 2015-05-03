package PSO;

import NRP.NRP;
import NRP.Solucao;

public class MainPSO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		NRP nrp = new NRP("Entrada/nrp1.txt");
		
		int dimensao = (int) ((30/100.0) * nrp.getCustoTotal());
		int swarm = 10;
		int populacao = 200/swarm;
		int trocarGBest = 20;
		
		PSO[] pso = new PSO[swarm];
		Solucao aux = new Solucao(nrp.getClientesTotal());
		
		for(int i = 0; i < swarm; i++){
			pso[i] = new PSO(nrp, populacao, dimensao);			
		}
		
		for(int i = 0; i < swarm; i++){
			for(int j = 1; j <= 100; j++){
				if(j % trocarGBest == 0){
					
					pso[i].avaliarParticulas(trocarGBest);
					
					aux.Clone(pso[0].gBest);		
					for(int k = 0; k < pso.length - 1 ; k++){
						pso[k].gBest.Clone(pso[k + 1].gBest);
					}
					pso[swarm - 1].gBest.Clone(aux);
				}									
			}			
		} 
	
		for(int i = 0; i < swarm; i++){
			System.out.println("=================");
			System.out.println("GBest "+i+": ");
			System.out.println("Custo: "+pso[i].gBest.getCusto());
			System.out.println("Satisfação: "+pso[i].gBest.getSatisfacao());
		}
			
		
		
//		pso[0].gBest = pso[1].gBest;
//		pso[1].gBest = pso[2].gBest;
//		pso[2].gBest = pso[3].gBest;
//		pso[3].gBest = pso[4].gBest;
//		pso[4].gBest = aux;
		
//		for(int i = 0; i < 5; i++)
//			pso[i].avaliarParticulas(10);
			 
		
//		for (int i = 0; i < pso.getP().length; i++) {
//			System.out.println(pso.getP()[i].getpBest().getSatisfacao());			
//		}
//		System.out.println("===========");
//		System.out.println(Particula.getgBest().getSatisfacao());
	}
}
