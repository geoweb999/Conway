package src;


public class AlwaysAliveCell extends AbstractCell {
	
	public AlwaysAliveCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}
	
	public  AbstractCell cellForNextGeneration() {
		this.setAge(this.getAge() + 1);
		return this;
	}
	
	public boolean willBeAliveInNextGeneration() {
		return true;
	}
	
	public boolean getIsAlive() {
		return true;
	}

	public AbstractCell BorgForNextGeneration() {
		return this;
	}
}