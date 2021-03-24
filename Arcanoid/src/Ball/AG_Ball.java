package Ball;

import Vector.AG_Vector;

import java.awt.*;
import javax.swing.JComponent;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

/**
 * Created by Анастасия on 13.09.2017.
 */
public class AG_Ball extends JComponent {
    private AG_Vector pos=new AG_Vector();
    private AG_Vector v=new AG_Vector();
    private  int radius;
    private boolean openedMouth;
    private int openedTime;
    public boolean isOut=false;
    private boolean isFrozen;

    public AG_Ball(AG_Vector pos,int radius){
        this.pos=pos;
        v.x=0;
        v.y=3;
        openedTime = 0;
        openedMouth = true;
        this.radius=radius;
        this.isFrozen = true;
    }
    public AG_Ball(AG_Vector pos,AG_Vector v,int radius) {
        super();
        this.pos=pos;
        this.v=v;
        this.radius = radius;
    }

    public void setSize(AG_Vector pos,int radius){
        this.pos=pos;
        this.radius = radius;
    }
    public void setV(AG_Vector vel){v = vel;}
    public AG_Vector getPos(){
        return pos;
    }
    public int getRadius(){
        return radius;
    }
   // public double getY(){return pos.y;}
    public AG_Vector getV(){return  v;}

    public void rendering(AG_Vector v, int WPanel, int HPanel){
        if (isFrozen) return;
        pos.x+=v.x;
        pos.y+=v.y;
        if(pos.x + radius > WPanel)
            pos.x = WPanel - radius;
        if(pos.x < 0)
           pos.x = 0;

        if(pos.y +radius> HPanel)
            isOut=true;
           // pos.y = HPanel-radius;
        if(pos.y < 0) {
            pos.y = 0;

        }

       if ((pos.x+radius==WPanel) || (pos.x==0))
            collisionBallWall(0);
        if (/*(pos.y+radius==HPanel)||*/(pos.y==0))
            collisionBallWall(1);
       openedTime++;
       if(openedTime >= 30)
       {
           openedTime = 0;
           openedMouth = !openedMouth;
       }
    }
    //0-вертикальная граница, 1-горизонтальная
    public  void collisionBallWall(int key){
        if(key==0)
            v.x=-v.x;
        else
            v.y=-v.y;
    }

    public void reSize(int W, int H)
    {
        radius = W / 60;
        v = v.norm();
        v.x *= radius / 5;
        v.y *= radius / 5;
    }

    public  boolean gameEnds(boolean isOut){
        if(isOut)
            return true;
        else return false;
    }

    public void start(){
        this.isFrozen = false;
    }

    @Override
     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        AG_Vector vec2, nvec = new AG_Vector(1, 0);
        double angle;
        Color newColor=new Color(255, 252, 0);
        g.setColor(newColor);
        g.fillOval((int)pos.x,(int)pos.y, 2 * this.radius, 2 * this.radius);

        if(v.x > 0)
            vec2 = v.norm().rotate(-PI / 2);
        else
            vec2 = v.norm().rotate(PI / 2);
        vec2.x *= radius / 2;
        vec2.y *= radius / 2;
        newColor=new Color(0, 0, 0);
        g.setColor(newColor);
        g.fillOval((int)(pos.x + radius * 3 / 4 + vec2.x),(int)(pos.y + radius * 3 / 4 + vec2.y), radius / 2, radius / 2);

        if(openedMouth)
        {
            angle = v.getAngle(nvec);
            g.fillArc((int)(pos.x), (int)(pos.y), 2 * radius, 2 * radius, v.radianToDegree(angle - PI / 6), 60);
        }
    }
}
