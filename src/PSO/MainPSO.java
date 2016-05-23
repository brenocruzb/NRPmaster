package PSO;

import NRP.NRP;

public class MainPSO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		NRP nrp = new NRP("Entrada/nrp2.txt");
		
		int swarm = 5;
		int populacao = 200/swarm;							
		
		int trocarGBest = 20;
		
		ArquivadorMultiSwarm.limparArquivos(populacao/2);
		
		
		for(int z = 0; z < 20; z++){
			PSO[] pso = new PSO[swarm];
			ArquivadorMultiSwarm aux = new ArquivadorMultiSwarm(populacao/2);
			
			for(int i = 0; i < swarm; i++){
				pso[i] = new PSO(nrp, populacao);			
			}		
			
			for(int j = 1; j <= 1000; j++){
				if(j % trocarGBest == 0){
					for(int i = 0; i < swarm; i++){
						
						pso[i].avaliarParticulas(trocarGBest);
						
						aux.copiarAquivador(pso[0].getArquivador());		
						for(int k = 0; k < pso.length - 1 ; k++){
							pso[k].getArquivador().copiarAquivador(pso[k + 1].getArquivador());
						}
						pso[swarm - 1].getArquivador().copiarAquivador(aux);
					}									
				}			
			} 
			
			ArquivadorMultiSwarm arquivadorGeral = new ArquivadorMultiSwarm(-1);		
			for(int i = 0; i < swarm; i++){
				arquivadorGeral.copiarAquivador(pso[i].getArquivador());
			}

			arquivadorGeral.salvarArquivador(0);
		}
		
		
		
//		for(int i = 0; i < swarm; i++){
//			pso[i].getArquivador().salvarArquivador(i);
//		}
					
	}
}
