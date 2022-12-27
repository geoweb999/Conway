import java.util.Random;
//import java.util.Scanner;

class Main {

    public static void main(String[] args) throws InterruptedException {
        ConwayWorld world = new ConwayWorld();
        Random random = new Random();

        // Add some live conway cells, in a horizontal line
        for (int i = 0; i < 8; i++) {
            ConwayCell c = new ConwayCell(5, 5 + i, world);
            c.setIsAlive(true);
            world.replaceCell(c);
        }

        // Add an always-alive cell
        int row = random.nextInt(15);
        int col = random.nextInt(20);
        AbstractCell a = new AlwaysAliveCell(row, col, world);
        world.replaceCell(a);

        // Add a never-alive cell
        row = random.nextInt(15);
        col = random.nextInt(20);
        AbstractCell n = new NeverAliveCell(row, col, world);
        world.replaceCell(n);

        // Add an alternating cell
        row = random.nextInt(15);
        col = random.nextInt(20);
        AbstractCell c = new AlternatingCell(row, col, world);
        world.replaceCell(c);

        /*
         * // add line of Borgs
         * for (int i = 0; i < 4; i++) {
         * AbstractCell b = new BabyBorgCell(10, 8 + i, world);
         * world.replaceCell(b);
         * }
         */
        // Add a borg cell to a random position
        row = random.nextInt(world.ROWS);
        col = random.nextInt(world.COLS);
        AbstractCell b0 = new BabyBorgCell(row, col, world);
        world.replaceCell(b0);

        // Add a borg cell to a random position
        row = random.nextInt(world.ROWS);
        col = random.nextInt(world.COLS);
        AbstractCell b1 = new BabyBorgCell(row, col, world);
        world.replaceCell(b1);

        // Go!
        // Scanner scnr = new Scanner(System.in);
        while (true) {
            clearConsole();
            System.out.println(world.displayString());
            world.advanceToNextGeneration();
            // String x = scnr.nextLine();
            // wait time was 500msec
            Thread.sleep(150);
        }
    }

    public static void clearConsole() {
        // This crazy looking string clears the console.
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}