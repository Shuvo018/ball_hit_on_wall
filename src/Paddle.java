import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int id;
    int speed = 10;
    int xDirection;
    Paddle(int x, int y, int width, int height, int id){
        super(x, y, width, height);
        this.id = id;
    }
    public void setXDirection(int direction){
        this.xDirection = direction;
    }
    public void move(){
        x += xDirection;
    }

    public void draw(Graphics g){
        if(id == 1){
            g.setColor(Color.blue);
        }else{
            g.setColor(Color.darkGray);
        }
        g.fillRect(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            setXDirection(speed);
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            setXDirection(-speed);
        }
    }

    public void keyReleased(KeyEvent e) {
        setXDirection(0);
    }

}
