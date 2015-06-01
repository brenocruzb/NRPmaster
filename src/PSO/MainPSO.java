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
		
		PSO pso = new PSO(nrp, 100, dimensao);
				
		pso.avaliarParticulas(20);
		for (int i = 0; i < pso.getP().length; i++) {
			System.out.println(pso.getP()[i].getpBest().getSatisfacao());			
		}
		System.out.println("===========");
		System.out.println(Particula.getgBest().getSatisfacao());
		System.out.println(Particula.getgBest().getCusto());
		for (int i = 0; i < Particula.getgBest().tamanho(); i++) {
			System.out.print(Particula.getgBest().getSolucao()[i]+" ");
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
