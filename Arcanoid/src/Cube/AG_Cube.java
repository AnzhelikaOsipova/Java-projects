package Cube;

import Ball.AG_Ball;
import Vector.AG_Vector;

import javax.swing.*;
import java.awt.*;
import java.lang.Object;
import java.util.HashMap;
import javafx.util.*;
import java.util.Map;

/**
 * Created by Настя on 14.09.2017.
 */


import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;


import java.awt.geom.Point2D;
public class AG_Cube extends JComponent {

    private  int size;// сторона кубика
    private AG_Vector pos = new AG_Vector();
    public int strength; // Прочность. 0 - исчезнувший кубик
    public int score=0;
    public boolean win=false;

    public AG_Cube(int size, int strength, AG_Vector pos) {
        super();
        this.pos = pos;
        this.size = size;
        this.strength = strength;
    }


    public Pair<AG_Vector, Boolean> collisionCubeBall(AG_Ball ball, AG_Cube[][] cubes, int PanW, int PanH){
        AG_Vector CollPoint = new AG_Vector(0, 0); // возвращаемый вектор
        double border = 2*ball.getRadius() + (PanH - 5) / 5;
        double plus = PanW / 20;
        int cubeSize = 1 + (PanW) / 16;
        boolean f = false;
        int sign = 1;
        if  (ball.getPos().x < 0) { // A 0?
            sign = -1;
        }
        int num = (int) ball.getPos().x / (cubeSize  + 3);
        int x1 = (cubeSize + 5) * num + 3;
        int x2 = (cubeSize + 5) * num + 3 + cubeSize/3;
        //простейшие отбивания

        if ((num >= 0) && (num < 15)) {
            if ((ball.getPos().y <= border) && (cubes[num][0].strength > 0)) { // бьет в низ 1 ряда
                CollPoint.x = ball.getPos().x;
                CollPoint.y = border;
                cubes[num][0].strength--;
                score++;
                if (score==15*3)
                    win=true;
            }
            else if ((ball.getPos().y <= border - cubeSize/3)&& (cubes[num][0].strength == 0) && (num + sign >= 0)&& (num + sign < 15) &&(cubes[num + sign][0].strength > 0 ) && ((ball.getPos().x == x1) || (ball.getPos().x == x2))){
                CollPoint.x = ball.getPos().x;
                CollPoint.y = ball.getPos().y;
                cubes[num + sign][0].strength--;
                score++;
                if (score==15*3)
                    win=true;
                f = true;
            }

            else if ((ball.getPos().y <= border - plus) && (cubes[num][1].strength > 0)) { // бьет в низ 2 ряда
                CollPoint.x = ball.getPos().x;
                CollPoint.y = border - plus;
                cubes[num][1].strength--;
                score++;
                if (score==15*3)
                    win=true;
            }
            else if ((ball.getPos().y <= border - plus - cubeSize/3)&& (cubes[num][1].strength == 0) && (num + sign >= 0)&& (num + sign < 15) &&(cubes[num + sign][1].strength > 0 ) && ((ball.getPos().x == x1) || (ball.getPos().x == x2))){
                CollPoint.x = ball.getPos().x;
                CollPoint.y = ball.getPos().y;
                cubes[num + sign][1].strength--;
                score++;
                if (score==15*3)
                    win=true;
                f = true;
            }

            else if ((ball.getPos().y <= border - 2 * plus) && (cubes[num][2].strength > 0)) {// бьет в низ 3 ряда
                CollPoint.x = ball.getPos().x;
                CollPoint.y = border - 2*plus;
                cubes[num][2].strength--;
                score++;
                if (score==15*3)
                    win=true;
            }
            else if ((ball.getPos().y <= border - 2*plus - cubeSize/3)&& (cubes[num][2].strength == 0) && (num + sign >= 0)&& (num + sign < 15) &&(cubes[num + sign][2].strength > 0 ) && ((ball.getPos().x == x1) || (ball.getPos().x == x2))){
                CollPoint.x = ball.getPos().x;
                CollPoint.y = ball.getPos().y;
                cubes[num + sign][2].strength--;
                score++;
                if (score==15*3)
                    win=true;
                f = true;
            }
        }

       Pair<AG_Vector, Boolean> p = new Pair<AG_Vector, Boolean>(CollPoint, f);
       //p.put(CollPoint, f);
        return p;
    }

    public void cubeBallRender(AG_Ball ball, int PanW, int PanH, AG_Cube[][] cubes){
    	Pair<AG_Vector, Boolean> P = collisionCubeBall(ball, cubes, PanW, PanH);
        if (P.getKey().x == 0 && P.getKey().y == 0)//при отсутствии коллизии выходим
            return;
        if (P.getValue() == false) {
            ball.collisionBallWall(1);
        }
        else  ball.collisionBallWall(0);
        return;
        //ball.setV(v);
        // ball.rendering(v, PanW, PanH);
    }

    public void reSize(int W, int H, int i, int j) {
        size = W / 15 - 4;
        pos.x = (size + 5) * i + 2;
        pos.y = (H - 5) / 5 - j * (W / 20);
    }

    public boolean winner(boolean win){
        if(win)
            return true;
        else return false;
    }

    //@Override
    public void paintComponent(Graphics g, int i) {
        super.paintComponent(g);
        Color newColor=new Color(i*100, i*150, i*150);
        g.setColor(newColor);
        g.fillRect((int)pos.x,(int )pos.y, size, size/3);
    }
}