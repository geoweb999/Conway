// implementation of a borg cell
// borgs have a life cycle defined by the constants below:
//     baby -- mature -- parent -- zombie -- recycled Conway cell
// Borgs are not alive for the purposes of assessing neighbors for Conway cells
// Borgs assimilate one neighboring cells that are alive after they have lived MATURITY years
// Borgs begin life as a baby borg which cannot assimlate until they mature
// After MATURITY years, a baby borg becomes a mature borg
// After PARENT years, a mature borg has a 1 in 20 chance of birthing a baby borg into an adjacent ecll
// After DEATH years, a mature borg turns into a zombie (and is now alive for conway cells)
// After DECOMPOSE years, a zombie borg becomes a random live Conway cell that cannot be assimilated for 10 years
// 

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;

public abstract class AbstractBorgCell extends AbstractCell {

    static final int MATURITY = 5;
    static final int PARENT = MATURITY + 5;
    static final int DEATH = PARENT + 5;
    static final int DECOMPOSE = DEATH + 20;
    static final int BIRTHRATE = 10; // 1 / 10 chance of birth
    
	public AbstractBorgCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
        this.setAge(0);
        this.setIsAlive(false);
        this.setBorg(true);
        this.setChanged(true);
	}

	public char displayCharacter() {
		return getIsAlive() ? '■' : '■';
	}
    
    public Color getColor() {
        int age = this.getAge();
        if (age < MATURITY ) {
            Color c = new Color(Color.PINK);
            return c;
        } else if (age < PARENT) {
            Color c = new Color(Color.RED);
            return c;            
        } else if (age < DEATH) {
            Color c = new Color(Color.MAGENTA);
            return c;            
        } else if (age < DECOMPOSE) {
            Color c = new Color(Color.GRAY);
            return c;            
        }
        Color c = new Color(Color.LIGHT_GRAY);
        return c;
    }

	public  AbstractCell cellForNextGeneration() {
        return this;
	}	

    public  boolean willBeAliveInNextGeneration() {
        return this.getIsAlive();
    };
    
    public abstract AbstractCell BorgForNextGeneration(); 
}