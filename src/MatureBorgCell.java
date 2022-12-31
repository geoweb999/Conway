package src;

import java.util.Random;

public class MatureBorgCell extends AbstractBorgCell {

    public MatureBorgCell(int r, int c, ConwayWorld w) {
        super(r, c, w);
    }

    public AbstractCell BorgForNextGeneration() {
        int age = getAge() + 1;
        setAge(age);
        if (age > DEATH) {
            // replace with Zombie
            AbstractCell n = new ZombieBorgCell(this.getRow(), this.getColumn(), this.world);
            n.setAge(age);
            n.setIsAlive(true);
            n.setBorg(true);
            return n;
        }
        if (age > PARENT) {
            // run birth process
            this.splitBorg(BIRTHRATE);
            return this;
        } else {
            // attempt to assimilate a cell
            this.cellAssimilation();
            return this;
        }
    }

    public void splitBorg(int bound) {
        int row, col;
        Random random = new Random();
        int count = 0;
        boolean split = false;
        // spawing has a 1/bound chance of happening
        if (random.nextInt(bound + 1) % bound == 0) {
            while (count < 10 && !split) {
                row = this.getRow() + random.nextInt(3) - 1;
                col = this.getColumn() + random.nextInt(3) - 1;
                if (this.world.isValid(row, col) && !this.world.isBorg(row, col)) {
                    AbstractCell n = new BabyBorgCell(row, col, this.world);
                    n.setAge(0);
                    this.world.replaceCell(n);
                    split = true;
                }
                count++;
            }
        }
    }

    public void cellAssimilation() {
        // check random neighboring cell for assimilation opportunities and randomly
        // spawn a baby borg
        // mature borgs convert adjacent cells to baby borgs
        // check random surrounding grid positions for alive cells (up to 10 tries)
        // (isAlive validates grid coordinates are valid)
        // if alive, then convert to new baby borg
        // don't assimiulate borgs
        Random random = new Random();
        boolean assimilated = false;
        int count = 0;
        int row, col;
        while (count < 10 && !assimilated) {
            count++;
            row = this.getRow() + random.nextInt(3) - 1;
            col = this.getColumn() + random.nextInt(3) - 1;
            if (this.world.isAlive(row, col) && !this.world.isBorg(row, col)) {
                AbstractCell n = new BabyBorgCell(row, col, this.world);
                n.setAge(0);
                this.world.replaceCell(n);
                assimilated = true;
            }
        }
    }

    public boolean willBeAliveInNextGeneration() {
        return false;
    }

    public char displayCharacter() {
        return getIsAlive() ? '■' : '■';
    }
}