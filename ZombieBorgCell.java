import java.util.Random;

public class ZombieBorgCell extends AbstractBorgCell {

    public ZombieBorgCell(int r, int c, ConwayWorld w) {
        super(r, c, w);
        this.setIsAlive(true);
    }

    public AbstractCell BorgForNextGeneration() {
        Random random = new Random();
        int age = getAge() + 1;
        setAge(age);
        AbstractCell n;
        if (age > DECOMPOSE) {
            int randomChoice = random.nextInt(1);
            n = new ConwayCell(this.getRow(), this.getColumn(), this.world);
            n.setBorg(true);
            return n;
        }
        return this;
    }

    public boolean willBeAliveInNextGeneration() {
        return true;
    }

    public char displayCharacter() {
        return getIsAlive() ? '◘' : '◇';
    }
}