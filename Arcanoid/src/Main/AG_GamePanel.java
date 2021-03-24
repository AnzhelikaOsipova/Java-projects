package Main;

import Ball.AG_Ball;
import Cube.AG_Cube;
import Platform.AG_Platform;
import Vector.AG_Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Ball.AG_Ball;
import Cube.AG_Cube;
import Platform.AG_Platform;
import Vector.AG_Vector;


import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.io.*;

//import static java.awt.event.KeyEvent.VK_A;

public class AG_GamePanel extends JPanel {
    int PanelWidth = 1920;
    int PanelHeight = 1280;
    int thickness=0;
    AG_Ball ball;
    AG_Cube[][] cubes;
    AG_Platform platform;
    boolean GameRuns=true;
    JFrame jfrmUsed;


    public AG_GamePanel(JFrame jfrm) {
        jfrmUsed = jfrm;
        // параметры - ВНЕШНИЕ размеры рамки
        setPreferredSize(new Dimension(PanelWidth, PanelHeight));

        //толщина рамки добавляется к размерам во внутрь, т.е. внутренняя граница=внешняя-thickness
        setBorder(BorderFactory.createLineBorder(Color.blue, thickness));
        //создание шарика
        ball = new AG_Ball(new AG_Vector((PanelWidth) / 2 - 15, (PanelHeight) / 2 + 15), (PanelWidth) / 60);
        int cubeSize = 1 + (PanelWidth ) / 16; // чему будет равна длинная сторона кубика

        //создание массива кубиков (15 шт)*3
        // длины cubesize, начиная с 3 пикселя, с просветами по
        cubes = new AG_Cube[15][3];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    cubes[i][j] = new AG_Cube(cubeSize, 1, new AG_Vector(3, ((PanelHeight - 5) / 5) - j * (PanelWidth / 20)));
                }
                else{
                    cubes[i][j] = new AG_Cube(cubeSize, 1, new AG_Vector((cubeSize + 5) * i + 3, ((PanelHeight - 5) / 5) - j * (PanelWidth / 20)));
                    //cubes[i][j] = new AG_Cube(cubeSize, new AG_Vector(((PanelWidth / 16) * i)+ 5 + 2*i, ((PanelHeight - 5) / 5) - j * (PanelWidth / 20)));
                }
            }
        }
        //создание платформы
        platform = new AG_Platform(new AG_Vector((PanelWidth) / 2 - 150, PanelHeight - 70), (PanelWidth) / 10, (PanelHeight) / 14);

        //обработчик клавиатуры
        this.setFocusable(true);
        //this.addAncestorListener();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                //управление платформой с помощью кнопок a и d или стрелок
                if (key == e.VK_A || key == e.VK_LEFT) {
                    platform.rendering(0, PanelWidth);

                }
                if (key == e.VK_D || key == e.VK_RIGHT){
                    platform.rendering(1, PanelWidth);

                }
                //выход по esc
                if (key == e.VK_ESCAPE)
                    System.exit(0);

                if (key == e.VK_SPACE)
                    ball.start();
            }
           /* @Override
            public void keyReleased(KeyEvent e) {
            }
            @Override
            public void keyTyped(KeyEvent e) {
            }*/
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        reSize();
        Color newColor=new Color(0, 0, 0);
        g.setColor(newColor);
        g.fillRect(0, 0, PanelWidth, PanelHeight);
        ball.rendering(ball.getV(),PanelWidth,PanelHeight);
        platform.platformBallRender(ball, PanelWidth, PanelHeight);

        cubes[0][0].cubeBallRender(ball, PanelWidth, PanelHeight, cubes);
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 3; j++){
                cubes[i][j].paintComponent(g,cubes[i][j].strength);
            }

        ball.paintComponent(g);
        platform.paintComponent(g);
    }

    public void over(){
        if (ball.gameEnds(ball.isOut))
            GameRuns = false;
        else GameRuns=true;
    }

    public boolean win(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 3; j++)
                if (cubes[i][j].winner(cubes[i][j].win))
                    return true;
        }
        return  false;
    }

    public  void NewGame(){
        GameRuns=true;
        ball=null;
        platform=null;

        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 3; j++)
                cubes[i][j]=null;
        ball = new AG_Ball(new AG_Vector((PanelWidth) / 2 - 15, (PanelHeight) / 2 + 15), 10);
        int cubeSize = 1 + (PanelWidth ) / 16;

        cubes = new AG_Cube[15][3];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    cubes[i][j] = new AG_Cube(cubeSize, 1, new AG_Vector(3, ((PanelHeight - 5) / 5) - j * (PanelWidth / 20)));
                }
                else{
                    cubes[i][j] = new AG_Cube(cubeSize, 1, new AG_Vector((cubeSize + 5) * i + 3, ((PanelHeight - 5) / 5) - j * (PanelWidth / 20)));
                }
            }
        }
        platform = new AG_Platform(new AG_Vector((PanelWidth) / 2 - 150 - 70, PanelHeight - 70), 100, 50);

    }

    public void reSize()
    {
        Dimension windowSize = jfrmUsed.getContentPane().getSize();
        setPreferredSize(windowSize);
        setBorder(BorderFactory.createLineBorder(Color.blue, thickness));
        PanelWidth = windowSize.width;
        PanelHeight = windowSize.height;

        platform.reSize(PanelWidth, PanelHeight);
        ball.reSize(PanelWidth, PanelHeight);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    cubes[i][j].reSize(PanelWidth, PanelHeight, i, j);
                }
                else{
                    cubes[i][j].reSize(PanelWidth, PanelHeight, i, j);
                }
            }
        }
    }


}


