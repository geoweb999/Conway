package src;


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