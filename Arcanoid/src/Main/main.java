package Main;//package Main;

import Ball.AG_Ball;

import javax.swing.*;
//import Arcanoid.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

class AG_ArcanoidGame implements ActionListener {
    boolean GameRun = true;
    //private boolean isGameOver = false;
    //public Timer AGtime;
    JLabel jlab;
    JFrame jfrm, jfrm2;
    AG_GamePanel ag;
    JTextField table = new JTextField(100);
    JButton but = new JButton("OK");
    String TableText[] = new String[500];
    int cnt = 0;


    AG_ArcanoidGame()  {

            //создание фрейма и его заголовка
            jfrm = new JFrame("Arcanoid");
            jfrm2 = new JFrame("Name");
            //задает размеры фрейма
            jfrm.setSize(1920, 1280);
            jfrm2.setSize(500, 400);
        //фрейм будет закрываться при нажатии на кнопку выхода
            jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jfrm2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //если убрать следующую строку, рамка будет ровно по границе экрана(возможно стоит подумать над этим)
            jfrm.setLayout(new FlowLayout());//BorderLayout() по размеру фрейма
            jfrm2.setLayout(new GridLayout(1, 1));

            //добавление объектов во фрейм
            ag = new AG_GamePanel(jfrm);
            jfrm.add(ag);

            jlab = new JLabel();
            JMenuBar jmb = new JMenuBar();
            JMenu jmGame = new JMenu("Game");

            //jmGame;

            JMenuItem jmiNewGame = new JMenuItem("New game");
            JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_ESCAPE);

            jmiExit.setAccelerator(
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, InputEvent.CTRL_MASK)
            );

            jmiNewGame.addActionListener(this);
            jmiExit.addActionListener(this);

            jmGame.add(jmiNewGame);
            jmGame.add(jmiExit);

            jmb.add(jmGame);


            jfrm.getContentPane().add(jlab);
            jfrm.setJMenuBar(jmb);

            //делаем фрейм видимым
            jfrm.setVisible(true);

        /*if (ball.gameEnds(ball.isOut))
            GameRuns = false;
        else GameRuns=true;*/
        try {

        while (GameRun) {
            while (ag.GameRuns) {
                ag.revalidate();
                ag.repaint();
                if (ag.win()) {
                    JOptionPane.showMessageDialog(null, "YOU WIN", "CONGRATULATIONS!", 1);
                    SaveLoadScore(ag.cubes[0][0].score);
                    ag.NewGame();
                }
                ag.over();
            }
            JOptionPane.showMessageDialog(null, "GAME OVER", "=(", 1);
                SaveLoadScore(ag.cubes[0][0].score);

            ag.NewGame();

        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void actionPerformed(ActionEvent ae) {
        String comStr=ae.getActionCommand();
        if (comStr.equals("Exit")) System.exit(0);
        else {
            //jlab.setText(comStr+"Selected");
        }
    }

    public void SaveLoadScore(int score) throws IOException {
        //FileInputStream fstream = null;
        //FileOutputStream ostream = null;
        //JButton but = new JButton("OK");
       // JButton jmiExit = new  JButton("Exit", KeyEvent.VK_ESCAPE);
            //String line1;
            table.addActionListener(this);
            but.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileOutputStream ostream = null;
                    try {
                        FileInputStream fstream = new FileInputStream("D://Программирование//Проекты//Java//Arcanoid//output.txt");
                        BufferedReader br =new BufferedReader(new InputStreamReader(fstream));
                        cnt = 0;
                        while((TableText[cnt++] = br.readLine()) != null);
                        cnt--;
                        ostream = new FileOutputStream("D://Программирование//Проекты//Java//Arcanoid//output.txt");
                        String line;
                        line = table.getText();
                        TableText[cnt++] = line + " " + String.valueOf(score) + " ";
                        for(int i = 0; i < cnt; i++)
                        {
                            byte[] buf;
                            buf = TableText[i].getBytes();
                            ostream.write(buf,0,buf.length);
                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    jfrm2.setVisible(false);

                }
            });
            jfrm2.getContentPane().add(table);
            jfrm2.getContentPane().add(but);
            jfrm2.setVisible(true);

    }

    public static void main(String[] args) {

        new AG_ArcanoidGame();
    }
}