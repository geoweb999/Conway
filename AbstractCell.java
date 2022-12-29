
public abstract class AbstractCell {
	
	private boolean isAlive;		// is the cell considered alive
	private int row;				// row position in world
	private int column;				// column position in world
    private int cellAge;			// age of cell (increment by 1 each turn) 
    private boolean isBorg;			// a hack to test if a cell contains any type of borg
	private boolean changed;      // tracks if cell has changed

	protected ConwayWorld world;	// contains grid of all cells
	
	public AbstractCell(int r, int c, ConwayWorld w) {
		row = r;
		column = c;
		world = w;
		isAlive = false;
        cellAge = 0;
        isBorg = false;
		changed = false;
	}
    
    public int getAge() {
        return cellAge;
    }

    public void setAge(int x) {
        cellAge = x;
    }

	public void setChanged(boolean val) {
		changed = val;
	}

	public boolean isChanged() {
		return changed;
	}

    public boolean isBorg() {
        return isBorg;
    }

    public void setBorg(boolean flag) {
        isBorg = flag;
    }
    
	public char displayCharacter() {
		return isAlive ? 'O' : '.';
	}
	
	public void setIsAlive(boolean value) {
		isAlive = value;
	}
	
	public boolean getIsAlive() {
		return isAlive;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}

  	public int neighborCount() {
		int count = 0;
		
		int r = getRow();
		int c = getColumn();

    // the row above
    if (world.isAlive(r - 1, c - 1)) count++;
    if (world.isAlive(r - 1, c)) count++;
    if (world.isAlive(r - 1, c + 1)) count++;

    // Same row as this cell
    if (world.isAlive(r, c - 1)) count++;
    if (world.isAlive(r, c + 1)) count++;

    // The row below
    if (world.isAlive(r + 1, c - 1)) count++;
    if (world.isAlive(r + 1, c)) count++;
    if (world.isAlive(r + 1, c + 1)) count++;
    
		return count;
	}

	public AbstractCell BorgForNextGeneration() {
		return this;
	}

	
	public abstract AbstractCell cellForNextGeneration();
	public abstract boolean willBeAliveInNextGeneration();
}