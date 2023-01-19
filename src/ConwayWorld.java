package src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;

public class ConwayWorld {
	
	static final int ROWS = 750;
	static final int COLS = 325;
	
	private AbstractCell[][] grid = new AbstractCell[ROWS][COLS]; 
	
	public ConwayWorld() {
		
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				grid[r][c] = new ConwayCell(r, c, this);
			}
		}
	}

	// Replace a single cell
	public void replaceCell(AbstractCell cell) {
		grid[cell.getRow()][cell.getColumn()] = cell;
	}
	
	// Create the next generation for Borgs
	public void advanceToNextGeneration() {
		AbstractCell[][] nextGrid = new AbstractCell[ROWS][COLS]; 
        // check to see if any Borg assimilated cells
		// this has to be done before building the next generation grid
		// because assimilation will cause preceeding rows/columns to be converted
		// and the process of getting next gen cells will thus ignore those conversions
        for (int r = 0; r < ROWS; r++) {
        	for (int c = 0; c < COLS; c++) {
				if (grid[r][c].isBorg()) {
						grid[r][c] = grid[r][c].BorgForNextGeneration();
                }
            }
		}
        
		// Build the grid for the next generation
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				nextGrid[r][c] = grid[r][c].cellForNextGeneration();
			}
		}

		
		// Out with the old, in with the new
		grid = nextGrid;
	}

	public AbstractCell getCell(int r, int c, ConwayWorld w) {
		return grid[r][c];
	}
	
	// Returns true if (r, c) is a valid coordinate, and the cell is alive
	public boolean isAlive(int r, int c) {
		return this.isValid(r, c) && grid[r][c].getIsAlive();
	}
    // Returns true if (r,c) is valid and the cell contains a borg
    public boolean isBorg(int r, int c) {
		return this.isValid(r, c) && grid[r][c].isBorg();
	}
    
    // check if a cell is valid
    public boolean isValid(int r, int c) {
        return  r >= 0 && c >= 0 && r < ROWS && c < COLS;
    }

	public int getAge(int r, int c) {
        return  grid[r][c].getAge();
    }

	public boolean isChanged(int r, int c) {
		return grid[r][c].isChanged();
	}

}