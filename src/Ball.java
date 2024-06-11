import java.awt.*;

public class Ball extends Rectangle {
    int xDirection, yDirection;
    int speed = 4;
    Ball(int x, int y, int width, int height){
        super(x, y, width, height);
        initialPosition();
    }
    private void initialPosition(){
        int x = 1;
        if(x == 0){
            setXDirection(speed);
        }else{
            setXDirection(-speed);
        }

        int y = 0;
        if(y == 0){
            setYDirection(speed);
        }else{
            setYDirection(-speed);
        }
    }
    public void setXDirection(int xDirection){
        this.xDirection = xDirection;
    }
    public void setYDirection(int yDirection){
        this.yDirection = yDirection;
    }

    public void move(){
        x += xDirection;
        y += yDirection;
    }
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x, y, width, height);
    }
}
