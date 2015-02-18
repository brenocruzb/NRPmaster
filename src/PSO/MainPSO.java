package PSO;

import NRP.NRP;

public class MainPSO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NRP nrp = new NRP("Entrada/nrp1.txt");
		
		int dimensao = (int) ((70/100.0) * nrp.getCustoTotal());
		PSO pso = new PSO(nrp, 100, dimensao);
		
		pso.avaliarParticulas(20);
		for (int i = 0; i < pso.getP().length; i++) {
			System.out.println(pso.getP()[i].getpBest().getSatisfacao());			
		}
		System.out.println("===========");
		System.out.println(Particula.getgBest().getSatisfacao());
	}

}
