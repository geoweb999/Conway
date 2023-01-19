package src;


public class NeverAliveCell extends AbstractCell {
	
	public NeverAliveCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}
	
	public  AbstractCell cellForNextGeneration() {
		this.setAge(this.getAge() + 1);
		return this;
	}
	
	public boolean willBeAliveInNextGeneration() {
		return false;
	}

	public boolean getIsAlive() {
		return false;
	}
	public AbstractCell BorgForNextGeneration() {
		return this;
	}

}