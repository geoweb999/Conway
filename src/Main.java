package src;

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

        // Add an alternating cell
        row = random.nextInt(world.ROWS);
        col = random.nextInt(world.COLS);
        AbstractCell c = new AlternatingCell(row, col, world);
        world.replaceCell(c);

        
         // add some random borgs
         for (int i = 0; i < 4; i++) {
            row = random.nextInt(world.ROWS);
            col = random.nextInt(world.COLS);
            AbstractCell b = new BabyBorgCell(row, col, world);
            world.replaceCell(b);
        }

        panel = new JPanel();
        Dimension dim = new Dimension(1000, 1000);
        panel.setPreferredSize(dim);
        frame = new JFrame("Conway with Borgs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(world.ROWS * 2 + 10, world.COLS * 2 + 40);
        Container contentPane = frame.getContentPane();
        contentPane.add(panel);
        frame.setVisible(true);

        Integer i = 0;
        long lastTime = System.nanoTime();
        Double fps = 0.0;
        String title;
        while (true) {
            i++;
            fps = 1000000000.0 / (System.nanoTime() - lastTime); 
            lastTime = System.nanoTime();
            Graphics g = panel.getGraphics();
            title = String.format("%.1f", fps);
            frame.setTitle("Conway with Borgs (FPS: " + title + ")");
            Graphics(world, g);
            world.advanceToNextGeneration();
            g.dispose();
            //Thread.sleep(30);
        }
    }

    public static void Graphics(ConwayWorld world, Graphics g) {
        int BOX_DIM = 2;
        for (int r = 0; r < world.ROWS; r++) {
			for (int c = 0; c < world.COLS; c++) {
                if (!world.isAlive(r,c)) {
                    g.setColor(Color.WHITE);
                    g.fillRect(r * BOX_DIM, c * BOX_DIM, BOX_DIM, BOX_DIM);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(r * BOX_DIM, c * BOX_DIM, BOX_DIM, BOX_DIM);
                }
                if (world.isBorg(r, c)) {

                    int age = world.getAge(r, c);
                    int red = 255 - (9 * age);
                    red = (red < 50) ? 50: red;
                    Color color = new Color(red, 10, 255-red);
                    g.setColor(color);
                    g.fillRect(r * BOX_DIM, c * BOX_DIM, BOX_DIM, BOX_DIM);                    
                }
            }
        }
    }
}
