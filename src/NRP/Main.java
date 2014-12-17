package NRP;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NRP nrp = new NRP("Entrada/nrp1.txt");
//		nrp.carregar();
//		System.out.println(nrp.getCustoDoCliente(nrp.getClientes().get(2)));
		ArrayList<Cliente> c = new ArrayList<>();
//		for (int i = 1; i <= 100; i++) {
//			c.add(nrp.getClientes().get(i));
//			
//		}
		c.add(nrp.getClientes().get(1));
//		c.add(nrp.getClientes().get(2));
//		c.add(nrp.getClientes().get(3));
		c.add(nrp.getClientes().get(7));
//		c.add(nrp.getClientes().get(27));
		System.out.println(nrp.getCustoDosClientes(c));		
		
		System.out.println(NRP.custoTotal);
				
		
	}
	
}		
		



//===========================================================================================================
		
//		
////		System.out.println(nrp.getClientes().get(2).getRequisitos().get(1).getCusto());
//		for (int i = 0; i < nrp.getMatrizAdjacente().size(); i++) {			
//			if(nrp.getMatrizAdjacente().get(i).get(98) == 1){
////				System.out.println(nrp.getRequisitos().get(i).getCusto());//custo
//				lista.add(nrp.getRequisitos().get(i).getId());
//				System.out.println(nrp.getRequisitos().get(i).getId());
//				
////				if(nrp.getMatrizAdjacente().get(98).
////				}
//			}
//			
//		}
//		if(lista.contains(2)){
//			System.out.println("Contem!");
//		}
////		System.out.println(nrp.getMatrizAdjacente().size());
////		nrp.getCustoDoCliente(nrp.getClientes().get(2), nrp.getClientes().get(2).getRequisitos());
//
//
//
//==========================================================================================
//
//		ArrayList<Integer> lista = new ArrayList<>();
//		nrp.carregar();		
//						
////		lista.add(nrp.getRequisitos().get(98).getId());
////		lista.add(nrp.getRequisitos().get(135).getId());
////		lista.add(nrp.getRequisitos().get(103).getId());
//		
////		System.out.println(nrp.getClientes().get(2).getRequisitos().size());
//		
//		/**parte 1*/
//		for(int i = 1; i < nrp.getClientes().get(2).getRequisitos().size(); i++){
//			lista.add(nrp.getClientes().get(2).getRequisitos().get(i).getId());
//		}
//		
//		System.out.println("============Antes============");
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i));
//		}
//		/**parte 2*/
//		for (int i = 0; i < lista.size(); i++) {			
//			for (int j = 0; j < nrp.getMatrizAdjacente().size(); j++) {
//				int bool = nrp.getMatrizAdjacente().get(j).get(lista.get(i));
//				if(bool == 1){
//					int elementoDaMatriz = nrp.getRequisitos().get(j).getId();
//					if(!lista.contains(elementoDaMatriz)){
//						lista.add(elementoDaMatriz);
//					}
//				}
//				
//			}
//		}
//		System.out.println("============Depois===========");
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i));
//		}	
//		System.out.println("==================Custo=================");
//		/**Parte 3*/
//		int custo = 0;
//		for (int i = 0; i < lista.size(); i++) {
//			custo += nrp.getRequisitos().get(lista.get(i)).getCusto();
////			System.out.println(nrp.getRequisitos().get(lista.get(i)).getCusto());
//		}
//		System.out.println(custo);
//	}
//
//}
