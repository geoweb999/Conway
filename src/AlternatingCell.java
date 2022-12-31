package src;

// a class that has a cell that alternates between alive and not alive
public class AlternatingCell extends AbstractCell {
	
	public AlternatingCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}
	
	public  AbstractCell cellForNextGeneration() {
		int age = this.getAge() + 1;
		AlternatingCell next = new AlternatingCell(getRow(), getColumn(), world);
		next.setIsAlive(willBeAliveInNextGeneration());
		next.setAge(age);
		return next;
	}	
	
	
	public boolean willBeAliveInNextGeneration() {
		return (!this.getIsAlive());
	}
	
	public char displayCharacter() {
		return getIsAlive() ? '∷' : '*';
	}

	public AbstractCell BorgForNextGeneration() {
		return this;
	}
}