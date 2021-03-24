package Platform;

import Ball.AG_Ball;
import Vector.AG_Vector;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.*;

public class AG_Platform extends JComponent {
    //это координаты самой верхней точки платформы
    AG_Vector Pos;
    //малая и большая полуоси
    int WAxis, HAxis;
    boolean TimerOn = false;
    long start = 0, end = 0;

    //инициализация платформы
    public AG_Platform(AG_Vector Coord, int SmallAxisA, int BigAxisB){
        super();
        Pos = Coord;
        WAxis = SmallAxisA;
        HAxis = BigAxisB;
    }

    //обработка движения платформы
    public void rendering(int key, int WPanel){
        if(key == 1)
            Pos.x+=35;
        if(key == 0)
            Pos.x-=35;
        if(Pos.x + 2 * WAxis > WPanel)
            Pos.x = WPanel - 2 * WAxis;
        if(Pos.x < 0)
            Pos.x = 0;
    }

    //обрабатываем наличие коллизии платформы с шариком
    public AG_Vector collisionPlatformBall(AG_Ball ball)
    {
        AG_Vector bCenter = new AG_Vector(ball.getPos().x, ball.getPos().y);
        //переносим начало системы координат в центр платформы
        bCenter.x += ball.getRadius() - (Pos.x + WAxis);
        bCenter.y += ball.getRadius() - (Pos.y + HAxis);

        double x, y;
        //ищем точку возможной коллизии на платформе
        if(bCenter.x != 0)
        {
            //находим уравнение прямой, соединяющей центры шара и платформы
            double k = bCenter.y / bCenter.x;
            x = sqrt((HAxis * HAxis * WAxis * WAxis) / (HAxis * HAxis + WAxis * WAxis * k * k));
            if(k > 0)
                x = -x;
            y = k * x;
            if(k == 0)
            {
                if (bCenter.x < 0)
                    x = -x;
                y = 0;
            }
        }
        else
        {
            x = 0;
            y = -HAxis;
        }
        //определяем наличие коллизии
        double MinD = ball.getRadius() + sqrt(x * x + y * y);
        double D = sqrt(bCenter.x * bCenter.x + bCenter.y * bCenter.y);
        //если коллизия не произошла, возвращаем нулевой вектор
        AG_Vector CollPoint = new AG_Vector(0, 0);
        if(D <= MinD)
        {
            CollPoint.x = x;
            CollPoint.y = y;
        }
        //при наличии коллизии возвращаем точку, в которой она произошла
        return CollPoint;
    }

    //обработка коллизии шара и платформы
    public void platformBallRender(AG_Ball ball, int PanW, int PanH)
    {
        //проерка наличия коллизии
        AG_Vector P = collisionPlatformBall(ball);

        //при отсутствии коллизии выходим
        if(P.x == 0 && P.y == 0)
            return;
        if(!TimerOn)
        {
            TimerOn = true;
            start = System.currentTimeMillis();
        }
        else
        {
            end = System.currentTimeMillis();
            if((end - start) < 700)
                return;
            TimerOn = false;
        }
        //считаем направление вектора нормали в точке коллизии
        AG_Vector Norm = new AG_Vector(P.x, P.y);
        AG_Vector V = ball.getV();
        //меняем направление скорости шарика
        double AngleB = abs(Norm.getAngle(V)), AngleA = PI - AngleB;
        if(P.x == 0) AngleA = 0;
        if (V.x >= 0)
            V = V.rotate(-(AngleB - AngleA));
        else
            V = V.rotate(-(AngleA - AngleB));
        ball.setV(new AG_Vector(-ball.getV().x, -ball.getV().y));
        while(P.x != 0 && P.y != 0) {
            P = collisionPlatformBall(ball);
            ball.rendering(ball.getV(), PanW, PanH);
        }
        ball.setV(V);
    }

    public void reSize(int W, int H)
    {
        Pos.y = H - H / 12;
        WAxis = W / 10;
        HAxis = H / 14;
    }

    @Override
    //отрисовка платформы
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color newColor=new Color(0, 183, 23);
        g.setColor(newColor);
        g.fillArc((int)Pos.x, (int)Pos.y, WAxis * 2, HAxis * 2, 0, 180);
    }
}
