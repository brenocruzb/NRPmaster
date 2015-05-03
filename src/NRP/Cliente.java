package NRP;

import java.util.ArrayList;

public class Cliente {

	private int id;
	private int peso;
	private ArrayList<Requisito> requisitos;
	
		
	public Cliente(int id, int peso){
		this.id = id;
		this.peso = peso;
		this.requisitos = new ArrayList<>();
		this.requisitos.add(new Requisito(0, 0));
		
		}
	
	public void Clone(Cliente c){
		this.id = c.getId();
		this.peso = c.getPeso();
		this.requisitos.clear();
		for(Requisito req: c.getRequisitos())
			this.requisitos.add(req);						
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPeso() {
		return peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public ArrayList<Requisito> getRequisitos() {
		return requisitos;
	}
	
	public void setRequisitos(ArrayList<Requisito> requisitos) {
		this.requisitos = requisitos;
	}
	
}
