import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;

class Main {
    static JPanel panel;
    static JFrame frame;
    public static void main(String[] args) throws InterruptedException {
        ConwayWorld world = new ConwayWorld();
        Random random = new Random();

        // Add some live conway cells, in a horizontal line
        for (int i = 0; i < 8; i++) {
            ConwayCell c = new ConwayCell(100, 100 + i, world);
            c.setIsAlive(true);
            world.replaceCell(c);
        }

        // Add an always-alive cell
        int row = random.nextInt(world.ROWS);
        int col = random.nextInt(world.COLS);
        AbstractCell a = new AlwaysAliveCell(row, col, world);
        world.replaceCell(a);

        // Add a never-alive cell
        row = random.nextInt(world.ROWS);
        col = random.nextInt(world.COLS);
        AbstractCell n = new NeverAliveCell(row, col, world);
        world.replaceCell(n);

        // Add an alternating cell
        row = random.nextInt(world.ROWS);
        col = random.nextInt(world.COLS);
        AbstractCell c = new AlternatingCell(row, col, world);
        world.replaceCell(c);

        
         // add line of Borgs
         for (int i = 0; i < 4; i++) {
            row = random.nextInt(world.ROWS);
            col = random.nextInt(world.COLS);
            AbstractCell b = new BabyBorgCell(row, col, world);
            world.replaceCell(b);
        }
        
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

        panel = new JPanel();
        Dimension dim = new Dimension(1000, 1000);
        panel.setPreferredSize(dim);
        frame = new JFrame("Conway with Borgs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(world.ROWS + 10, world.COLS + 10);
        Container contentPane = frame.getContentPane();
        contentPane.add(panel);
        frame.setVisible(true);

        while (true) {
            Graphics g = panel.getGraphics();
            Graphics(world, g);
            world.advanceToNextGeneration();
            g.dispose();
        }
    }

    public static void Graphics(ConwayWorld world, Graphics g) {
        int BOX_DIM = 1;
        for (int r = 0; r < world.ROWS; r++) {
			for (int c = 0; c < world.COLS; c++) {
                //if (world.isChanged(r, c)) {
                //    continue;
                //}
                if (!world.isAlive(r,c)) {
                    g.setColor(Color.WHITE);
                    g.fillRect(r * BOX_DIM, c * BOX_DIM, BOX_DIM, BOX_DIM);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(r * BOX_DIM, c * BOX_DIM, BOX_DIM, BOX_DIM);
                }
                if (world.isBorg(r, c)) {
                    //g.setColor(world.getColor(r, c));
                    int age = world.getAge(r, c);
                    if (age < 5 ) {
                        g.setColor(Color.GREEN);
                    } else if (age < 20) {
                        g.setColor(Color.CYAN);
                    } else if (age < 100) {
                        g.setColor(Color.MAGENTA);
                    } else if (age < 200) {
                        g.setColor(Color.GRAY);
                    } else {
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    g.fillRect(r * BOX_DIM, c * BOX_DIM, BOX_DIM, BOX_DIM);                    
                }
            }
        }
    }
