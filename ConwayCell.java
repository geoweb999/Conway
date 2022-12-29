
public class ConwayCell extends AbstractCell {

	public ConwayCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}	

  /**
   * These are the Conway Game of Life Rules
   */
	public boolean willBeAliveInNextGeneration() {
		int nc = neighborCount();
		
		if (getIsAlive()) {
			return nc == 2 || nc == 3;
		} else {
			return nc == 3;
		}
	}
	
	public AbstractCell cellForNextGeneration() {
		this.setAge(getAge() + 1);
		if (this.isBorg() == true && this.getAge() < 10) {
			this.setIsAlive(true);
			this.setBorg(true);
			return this;
		} else {
			this.setBorg(false);
			this.setIsAlive(willBeAliveInNextGeneration());
			return this;
		}
	}
	
	public AbstractCell BorgForNextGeneration() {
		return this;
	}
}