/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author xabie
 */
public class Graph extends JPanel {
    private static final int X_SIZE = 5;
    private static final int Y_SIZE = 5;
    
    private static final int Y_CORRECTOR= 50;
    private int SCR_X;
    private int SCR_Y;

    
    private Genetic gen;
    
    private int x;
    private ArrayList<Point> points;
    private int ceil;

    @Override
    public void paint(Graphics e) {
        super.paint(e);
        AffineTransform af = new AffineTransform();
        af.scale(2.2,2.2);
        af.translate(0,-360);
        
        Graphics2D g2d = (Graphics2D) e;
        g2d.transform(af);
        g2d.drawString("AVG Fitness", 20, 400);
        
        g2d.drawString("Generaciones", 270, SCR_Y-80);
        g2d.drawLine(0, SCR_Y-70, 500, SCR_Y-70);
        g2d.drawLine(10, SCR_Y, 10, 0);
        Color c = g2d.getColor();
        g2d.setColor(Color.RED);
        g2d.drawLine(0, (int)(SCR_Y-19-ceil), 500, (int)(SCR_Y-19-ceil));
        g2d.drawString("Techo de fitness", 270, SCR_Y-124);
        g2d.setColor(c);
        
        for (int i = 0; i < points.size()-1; i++) {
            g2d.drawLine((int)points.get(i).getX()+11, (int)(SCR_Y-19-points.get(i).getY()), (int)points.get(i+1).getX()+11, (int)(SCR_Y-19-points.get(i+1).getY()));
        }
              
    }
    
    public void update(double fit) {
        points.add(new Point(x, (int)fit));
        x++;
        repaint();
    }
    
    public Graph(int SCR_X, int SCR_Y, int ceil) {
        this.SCR_X = SCR_X;
        this.ceil = ceil;
        this.SCR_Y = SCR_Y-Y_CORRECTOR;
        x = 0;
        points = new ArrayList<>();
    }
}
