public class BabyBorgCell extends AbstractBorgCell {

    private int Gravity; // determine if baby moves towards(-1) or away(1) from center of Grid

    public BabyBorgCell(int r, int c, ConwayWorld w) {
        super(r, c, w);
        Gravity = 0;
    }

    public int getGravity() {
        return Gravity;
    }

    public AbstractCell BorgForNextGeneration() {
        // check to see if baby has matured and return mature borg if so
        int age = getAge() + 1;
        this.setAge(age);
        if (age > MATURITY) {
            AbstractCell next = new MatureBorgCell(getRow(), getColumn(), world);
            next.setAge(age);
            return next;
        } else if (age >= 2) {
            this.cellMove();
            return this.world.getCell(this.getRow(), this.getColumn(), this.world);
        } else {
            return this;
        }
    }

    public void cellMove() {
        // move to center of universe
        int age = this.getAge();
        int x = this.getRow();
        int y = this.getColumn();
        int r = (((this.world.ROWS / 2) - x) <= 0) ? 1 : -1;
        int c = (((this.world.COLS / 2) - y) <= 0) ? 1 : -1;
        // try to move into cell @ r, c
        r *= getGravity();
        c *= getGravity();
        r = x + r;
        c = y + c;
        if (this.world.isValid(r, c) && !this.world.isBorg(r, c)) {
            // move the cell to new position
            AbstractCell b = new BabyBorgCell(r, c, this.world);
            b.setAge(age);
            this.world.replaceCell(b);
            // create a new conway cell replacing the borgs position
            AbstractCell cell = new ConwayCell(x, y, this.world);
            cell.setBorg(false);
            this.world.replaceCell(cell);
        }
    }

    public boolean willBeAliveInNextGeneration() {
        return false;
    }

    public char displayCharacter() {
        return getIsAlive() ? '◆' : '◆';
    }

}