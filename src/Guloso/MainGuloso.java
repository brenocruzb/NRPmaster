package Guloso;

//import java.util.ArrayList;

import NRP.NRP;

public class MainGuloso {
	
	public static void main(String[] args) {
		
		NRP nrp = new NRP("Entrada/nrp3.txt");
		
		Guloso gula = new Guloso(nrp, 70);
		
		
		
//		ArrayList<Cliente> cliente = gula.gerarSolucao();
		
//		for (int i = 0; i < cliente.size(); i++) {
//			System.out.println(cliente.get(i).getPeso());
//		}
		System.out.println("Custo: "+gula.getCustoTotal());
		System.out.println("Custo Total: "+nrp.getCustoTotal());
		System.out.println("SATISFACAO: "+gula.getSatisfacaoTotal());
	}

}
		
		
		
//		ArrayList<Cliente> cliente = gula.ordena();
//		
//		for (int i = 0; i < nrp.getClientes().size(); i++) {			
////			System.out.println(nrp.getClientes().get(i).getId());
//			System.out.print(nrp.getClientes().get(i).getPeso()+" ");
//		}
//		System.out.println();
//		
//		System.out.println("================================="+nrp.getClientes().size());
//		
//		for (int i = 0; i < cliente.size(); i++) {
////			System.out.println(cliente.get(i).getId());
//			System.out.print(cliente.get(i).getPeso()+" ");
//		}
//		

//	}
