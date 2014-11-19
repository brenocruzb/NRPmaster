package NRP;

public class Requisito {
	
	private int custo;
	private int id;
	
	public Requisito (int id, int custo){
		this.id = id;
		this.custo = custo;
	}

	public int getCusto() {
		return custo;
	}
	
	public void setCusto(int custo) {
		this.custo = custo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
