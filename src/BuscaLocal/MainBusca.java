package BuscaLocal;

import NRP.NRP;

public class MainBusca {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NRP nrp = new NRP("C:/Users/Breno Cruz/Desktop/NRP-master/nrp-tests/nrp1.txt");
		
		BuscaLocal busca = new BuscaLocal(nrp);
		busca.melhorSolucao(10, 10);		
		busca.salvarSolucao();
		
		
		
//		busca.gerarVizinhos(10);
//		System.out.println(busca.melhorVizinho(busca.getVizinhos()).getSatisfacao());
		
				
//		for (int i = 0; i < busca.getSolucaoAtual().getResultado().length; i++) {
//			System.out.println(busca.getSolucaoAtual().getResultado()[i]);
//		}
//		
		
//		for (int i = 0; i < busca.getVizinhos().size(); i++) {
//			for (int j = 0; j < busca.getVizinhos().get(i).getSolucao().length; j++) {
//				busca.avalia(busca.getVizinhos().get(i));
//				System.out.println(busca.getVizinhos().get(i).getSatisfacao());
//			}
//			System.out.println();
//		}

	}

}
