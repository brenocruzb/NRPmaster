package PSO;

import NRP.NRP;

public class MainPSO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		NRP nrp = new NRP("Entrada/nrp1.txt");
		
		//int dimensao = (int) ((30/100.0) * nrp.getCustoTotal());
		int swarm = 10;
		int populacao = 200/swarm;
		
//		PSO pso = new PSO(nrp, populacao/*, dimensao*/);
//		pso.avaliarParticulas(200);					
		
		int trocarGBest = 20;
		
		PSO[] pso = new PSO[swarm];
		ArquivadorMultiSwarm aux = new ArquivadorMultiSwarm();
		
		for(int i = 0; i < swarm; i++){
			pso[i] = new PSO(nrp, populacao);			
		}
		
//		for(int j = 0; j < pso[0].gBest.getSolucao().length; j++)
//			System.out.print(j+ " ");
		
		for(int i = 0; i < swarm; i++){
			for(int j = 1; j <= 100; j++){
				if(j % trocarGBest == 0){
					
					pso[i].avaliarParticulas(trocarGBest);
					
					aux.copiarAquivador(pso[0].getArquivador());		
					for(int k = 0; k < pso.length - 1 ; k++){
						pso[k].getArquivador().copiarAquivador(pso[k + 1].getArquivador());
					}
					pso[swarm - 1].getArquivador().copiarAquivador(aux);
				}									
			}			
		} 
		
//		for(int i = 0; i < swarm; i++){
//			System.out.println("=================");
//			System.out.println("GBest "+i+": ");
//			System.out.println("Custo: "+pso[i].gBest.getCusto());
//			System.out.println("Satisfação: "+pso[i].gBest.getSatisfacao());
//			for(int j = 0; j < pso[i].gBest.getSolucao().length; j++)
//				System.out.print(pso[i].gBest.getSolucao()[j]+" ");
//			System.out.println();
//		}
			
	}
}
