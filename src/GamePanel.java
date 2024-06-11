import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable{
    static final int GAME_HEIGHT =  600;
    static final int GAME_WIDTH = (GAME_HEIGHT/2)+200;

    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    final int paddle_width = 100;
    final int paddle_height = 25;
    final int ball_size = 20;
    Paddle paddle1, paddle2, paddle3,paddle4,paddle5,paddle6;
    Ball ball;
    Image image;
    Graphics graphics;
    Thread gameThread;
    GamePanel(){
        newPaddle();
        newBall();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(new keyOperations());



        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newBall() {
        ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, ball_size, ball_size);
    }

    private void newPaddle() {
        paddle1 = new Paddle(100, GAME_HEIGHT-paddle_height, paddle_width, paddle_height, 1);
        paddle2 = new Paddle(paddle_width*0, paddle_height, paddle_width, paddle_height, 2);
        paddle3 = new Paddle(paddle_width*1, paddle_height, paddle_width, paddle_height, 3);
        paddle4 = new Paddle(paddle_width*2, paddle_height, paddle_width, paddle_height, 4);
        paddle5 = new Paddle(paddle_width*3, paddle_height, paddle_width, paddle_height, 5);
        paddle6 = new Paddle(paddle_width*4, paddle_height, paddle_width, paddle_height, 6);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        g.drawImage(image, 0, 0, this);

        g.drawLine(0, GAME_HEIGHT/2, GAME_WIDTH, GAME_HEIGHT/2);
        drawPaddles(g);
        drawBall(g);
        topPaddleColor(g, 0);
    }


    private void drawBall(Graphics g) {
        ball.draw(g);
    }

    private void drawPaddles(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        paddle3.draw(g);
        paddle4.draw(g);
        paddle5.draw(g);
        paddle6.draw(g);
    }
    void move(){
        paddle1.move();
        ball.move();
    }

    @Override
    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime  = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    private void checkCollision() {
//        left and right edge
        if(ball.x >= GAME_WIDTH-ball_size){
            ball.setXDirection(-ball.speed);
        }
        if(ball.x <= 0){
            ball.setXDirection(ball.speed);
        }
//        hiting bottom paddle
        if(ball.intersects(paddle1)){
            ball.setYDirection(-ball.speed);
        }

//        hiting top paddle
        if(ball.intersects(paddle2) ||
                ball.intersects(paddle3) ||
                ball.intersects(paddle4) ||
                ball.intersects(paddle5) ||
                ball.intersects(paddle6)){

            ball.setYDirection(ball.speed);
        }
//        paddle boundary
        if(paddle1.x+paddle_width > GAME_WIDTH){
            paddle1.x = GAME_WIDTH-paddle_width;
        }
        if(paddle1.x<0){
            paddle1.x = 0;
        }
    }
    private void topPaddleColor(Graphics g, int i) {
    }

    public class keyOperations extends KeyAdapter{
        public void keyPressed(KeyEvent e) {

            paddle1.keyPressed(e);
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                newPaddle();
                newBall();
            }

        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
        }
    }
}
