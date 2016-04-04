package NRP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import PSO.Particula; 

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NRP nrp = new NRP("Entrada/nrp1.txt");
		System.out.println(nrp.getCustoTotal());
		System.out.println((int)((30/100.0) * nrp.getCustoTotal()));
//		nrp.carregar();
//		System.out.println(nrp.getCustoDoCliente(nrp.getClientes().get(2)));
		ArrayList<Cliente> c = new ArrayList<>();
		int max = 14;
		for (int i = 1; i <= max; i++) {
			c.add(nrp.getClientes().get(i));
			
		}
//		c.add(nrp.getClientes().get(1));
//		c.add(nrp.getClientes().get(2));
//		c.add(nrp.getClientes().get(15));
//		c.add(nrp.getClientes().get(17));
//		c.add(nrp.getClientes().get(20));
		
		System.out.println("Custo final: "+nrp.getCustoDosClientes(c));
		
		Solucao solu = new Solucao(nrp.getClientesTotal());
		int[] lista = new int[nrp.getClientesTotal()];
		for (int i = 1; i <= max; i++) {
			lista[i] = 1;
		}
//		lista[1] = 1;
//		lista[2] = 1;
//		lista[15] = 1;
//		lista[17] = 1;
//		lista[20] = 1;			
		
		solu.setSolucao(lista);
	//	Particula p = new Particula(nrp, (int) ((30/100.0) * nrp.getCustoTotal()));
	//	p.avalia(solu);
		System.out.println("Custo final2: "+solu.getCusto());
		System.out.println("susto final2: "+solu.getSatisfacao());
		/**=========================================**/
		File entrada = new File("C:/Users/Breno Cruz/Desktop/testeEntrada.txt");
		try {
			Scanner file = new Scanner(entrada);
			
			int a = 0;
			int[] array = new int[100];
			while(file.hasNext()){
				array[a++] = file.nextInt();					
			}			
			Solucao sol = new Solucao(nrp.getClientesTotal());
			sol.setSolucao(array);
		//	p.avalia(sol);
			
			System.out.println("Custo de Sol "+sol.getCusto());
			System.out.println("Satisfação de Sol "+sol.getSatisfacao());					
			
			file.close();			
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		//System.out.println(nrp.getClientes().get(100).getPeso());
		
		//System.out.println(nrp.getCustoTotal());
				
		
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
