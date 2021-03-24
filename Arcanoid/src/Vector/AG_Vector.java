package Vector;
import static java.lang.Math.*;


public class AG_Vector {
    public double x;
    public double y;

    public AG_Vector(){
        x = 0;
        y = 0;
    }

    public AG_Vector(double x, double y ){
        this.x = x;
        this.y = y;
    }

    //сумма векторов
    public AG_Vector sum(AG_Vector B ){
        return new AG_Vector(this.x + B.x, this.y + B.y);
    }

    //длина вектора
    public double module(){
        return (Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
    }

    //разность векторов, аргумент функции-вектор, который вычитают
    public AG_Vector sub(AG_Vector B ){
        return new AG_Vector(this.x - B.x, this.y - B.y);
    }

    //скалярное произведение
    public double scalar(AG_Vector B){return(this.x*B.x + this.y*B.y);}

    //перевод из градусов в радинаны
    public double degreeToRadian(double angle) {
        return angle * PI / 180;
    }

    //перевод из радиан в градусы
    public int radianToDegree(double angle) {return (int)(angle * 180 / PI);}

    //угол между векторами в радианах
    public double getAngle(AG_Vector B){
        return(Math.atan2(this.x * B.y - B.x * this.y, this.x * B.x + this.y * B.y));//(acos(scalar(B) / (module() * B.module())));
    }

    // поворот вектора на заданный угол в радианах
    public AG_Vector rotate(double angle){
        AG_Vector rotated=new AG_Vector();
        rotated.x=this.x*Math.cos(angle) - this.y*Math.sin(angle);
        rotated.y=this.x*Math.sin(angle) + this.y*Math.cos(angle);
        return rotated;
    }

    //нормализация вектора
    public AG_Vector norm( ){
        return new AG_Vector((this.x/this.module()), (this.y /this.module()));
    }
}
