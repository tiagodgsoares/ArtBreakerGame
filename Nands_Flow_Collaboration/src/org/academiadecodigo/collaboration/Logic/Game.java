package org.academiadecodigo.collaboration.Logic;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.GameOverScreen;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.StartMenuScreen;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.WinnerScreen;
import org.academiadecodigo.collaboration.Sound.*;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.GameScreen;
import org.academiadecodigo.collaboration.SimpleGraphics.PaddleDraw;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Game implements KeyboardHandler {
    /*
    class to manage the overall game flow. This class will handle initializing the game window,
    updating game objects, handling user input, and rendering graphics.
     */
    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    private PaddleDraw paddleDraw;
    private Brick[] bricks;
    private Boss boss;
    private BossProjectile projectile;
    private Ball ball;
    private BackgroundMusic backgroundMusic;
    private BallTapPaddleSound ballTapPaddleSound;
    private BallTapBrickSound ballTapBrickSound;
    private GameOverMusic gameOverMusic;
    private WinnerScreen winnerScreen;
    private WinnerSound winnerSound;
    private Keyboard keyboard;
    private boolean startedGame;

    public  Game() {
        StartMenuScreen startMenuScreen = new StartMenuScreen();

        keyboard = new Keyboard(this);
        addKeyboard();

        while (!startedGame) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        startMenuScreen.deleteMenu();
        init();
    }

    public void init() {
        this.gameScreen = new GameScreen();
        this.paddleDraw = new PaddleDraw();
        this.boss = new Boss();
        this.bricks = new Brick[200];
        for (int i = 0; i < this.bricks.length; i++) {
            this.bricks[i] = new Brick();
        }
        this.ball = new Ball();
        this.backgroundMusic = new BackgroundMusic();
        this.ballTapPaddleSound = new BallTapPaddleSound();
        this.ballTapBrickSound = new BallTapBrickSound();
    }

    public void start() {
        startedGame = true;
        backgroundMusic.setMusic();

        int counter = 0;
        boolean bricksDestroyed = false;

        while (ball.getY() < paddleDraw.paddle.getY() && (!bricksDestroyed || boss.getIsDestroyed() > 0)) {

            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (paddleDraw.getIsDestroyed()) {
                break;
            } else if (!this.gameScreen.isPaused()) {
                moveBall();
                moveBoss();

                if (((counter % 1000) == 0) &&   // projectile speed
                        (boss.getIsDestroyed() != 0)) {
                    shoot();
                }
                moveShoot(projectile);
                counter++;
            }

            boolean aux = true;
            for (int i = 0; i < bricks.length; i++) {
                if(!bricks[i].getIsDestroyed()) {
                    aux = false;
                }
            }
            bricksDestroyed = aux;

        }

        if (bricksDestroyed && boss.getIsDestroyed() <= 0) {
            this.backgroundMusic.stop();
            this.winnerScreen = new WinnerScreen();
            this.winnerSound = new WinnerSound();
            this.winnerSound.setMusic();
        } else {
            this.backgroundMusic.stop();
            this.gameOverScreen = new GameOverScreen();
            this.gameOverMusic = new GameOverMusic();
            this.gameOverMusic.setMusic();
        }

    }

    public void moveBall() {
        //Brick collision
        for (int i = 0; i < bricks.length; i++) {
            if ((bricks[i].getIsDestroyed() == false) &&
                    (((ball.getX() + 8) >= bricks[i].getInitialFixedX()) && ((ball.getX() + 8) <= bricks[i].getInitialFixedX() + 1.5)) &&
                    ((ball.getY() + 8 >= bricks[i].getInitialFixedY() + 1.5) && (ball.getY() + 8 <= (bricks[i].getFinalFixedY() - 1.5)))) { // try increasing 12 more
                bricks[i].setIsDestroyed();
                ball.setSpeed(0.005);  // --- ToDo SET BALL SPEED INCREMENT HERE ----
                bricks[i].delete();
                ball.changeDirectionX();
                ballTapBrickSound.setMusic();
            } else if ((bricks[i].getIsDestroyed() == false) &&
                    (((ball.getX() + 8) >= bricks[i].getFinalFixedX() - 1.5) && ((ball.getX() + 8) <= bricks[i].getFinalFixedX())) &&
                    ((ball.getY() + 8 >= bricks[i].getInitialFixedY() + 1.5) && (ball.getY() + 8 <= (bricks[i].getFinalFixedY() - 1.5)))) { // try increasing 12 more
                bricks[i].setIsDestroyed();
                ball.setSpeed(0.005);  // --- ToDo SET BALL SPEED INCREMENT HERE ----
                bricks[i].delete();
                ball.changeDirectionX();
                ballTapBrickSound.setMusic();
            } else if ((bricks[i].getIsDestroyed() == false) &&
                    ((ball.getY() + 8 >= bricks[i].getInitialFixedY()) && (ball.getY() + 8 <= bricks[i].getFinalFixedY())) &&
                    ((ball.getX() + 8 >= bricks[i].getInitialFixedX()) && (ball.getX() + 8 <= bricks[i].getFinalFixedX()))) {
                bricks[i].setIsDestroyed();
                ball.setSpeed(0.005);  // --- ToDo SET BALL SPEED INCREMENT HERE ----
                bricks[i].delete();
                ball.changeDirectionY();
                ballTapBrickSound.setMusic();
            }
        }
        //Boss collision
        if ((boss.getIsDestroyed() != 0) &&
                (((ball.getX() + (ball.getWidth() / 2)) >= boss.getBossX()) && ((ball.getX() + (ball.getWidth() / 2)) <= (boss.getBossX() + 110))) &&
                (((ball.getY() + (ball.getHeight() / 2)) >= boss.getBossY()) && ((ball.getY() + (ball.getHeight() / 2)) <= (boss.getBossY() + 110)))) {
            ball.changeDirectionX();
            ball.changeDirectionY();
            boss.changeDirectionX();
            boss.setIsDestroyed();  // ToDo SET DAMAGE ANIMATION and DESTROYED ANIMATION
        }
        //Paddle collision
        if ((((ball.getX() + (ball.getWidth()/2)) >= paddleDraw.paddle.getX() - 5) && ((ball.getX() + (ball.getWidth()/2)) <= (paddleDraw.paddle.getX() + 5)) &&
                ((ball.getY() + (ball.getHeight()/2)) >= (paddleDraw.paddle.getY() - 8))) ||
                ((((ball.getX() + (ball.getWidth()/2)) >= (paddleDraw.paddle.getX() + paddleDraw.paddle.getWidth() - 5)) &&
                        ((ball.getX() + (ball.getWidth()/2)) <= (paddleDraw.paddle.getX() + paddleDraw.paddle.getWidth() + 5)) &&
                        ((ball.getY() + (ball.getHeight()/2)) >= (paddleDraw.paddle.getY() - 8))))) {
            ball.changeDirectionX();
            ball.setDirectionNorth();
            ballTapPaddleSound.setMusic();
        } else if (
                (ball.getY() + ball.getHeight() >= paddleDraw.paddle.getY()) &&
                        (ball.getX() + (ball.getWidth()/2) >= paddleDraw.paddle.getX()) &&
                        (ball.getX() + (ball.getWidth()/2) <= paddleDraw.paddle.getX() + paddleDraw.paddle.getWidth())
        ) {
            ball.setDirectionNorth();
            ballTapPaddleSound.setMusic();
        }
        ball.move();
    }
    public void moveBoss() {
        if (boss.getIsDestroyed() != 0) {
            boss.move();
        }
    }
    public void shoot() {
        projectile = new BossProjectile(boss);
    }
    public void moveShoot(BossProjectile projectile) {
        projectile.move();
        for (int i = 0; i < bricks.length; i++) {  // PROJECTILE COLLISION WITH BRICKS
            if ((bricks[i].getIsDestroyed() == false) &&
                    ((projectile.getY() + 30 >= bricks[i].getInitialFixedY() && (projectile.getY()+30 <= bricks[i].getFinalFixedY()))) &&
                    ((projectile.getX() >= bricks[i].getInitialFixedX()) && (projectile.getX() <= bricks[i].getFinalFixedX()))) {
                projectile.setIsDestroyed();
                projectile.delete();
            }
        }
        if (projectile.getY() + 30 >= GameScreen.height + GameScreen.PADDING - 3) {  // PROJECTILE COLLISION WITH SCREEN
            projectile.setIsDestroyed();
            projectile.delete();
        }
        if ((projectile.getIsDestroyed() == false) &&  // PROJECTILE COLLISION WITH PADDLE
                (projectile.getY() + 30 >= paddleDraw.paddle.getY()) &&
                ((projectile.getX() >= paddleDraw.paddle.getX()) && (projectile.getX() <= paddleDraw.paddle.getX() + 140))) {
            paddleDraw.setDestroyed();
            paddleDraw.paddle.delete();
        }
    }

    private void addKeyboard() {

        KeyboardEvent start = new KeyboardEvent();
        start.setKey(KeyboardEvent.KEY_S);
        start.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(start);

        KeyboardEvent leave = new KeyboardEvent();
        leave.setKey(KeyboardEvent.KEY_B);
        leave.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(leave);

        KeyboardEvent retry = new KeyboardEvent();
        retry.setKey(KeyboardEvent.KEY_R);
        retry.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(retry);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int keyPressed = keyboardEvent.getKey();

        if (keyPressed == KeyboardEvent.KEY_S && !startedGame) {
            startedGame = true;
        }

        if (keyPressed == KeyboardEvent.KEY_B){
            System.exit(0);
        }

        if (keyPressed == KeyboardEvent.KEY_R) {
            gameOverScreen.delete();
            if (gameOverMusic != null) {
                gameOverMusic.stop();
            }

            //Game game = new Game();
            while (!startedGame) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            this.gameScreen = new GameScreen();
            this.paddleDraw = new PaddleDraw();
            this.boss = new Boss();
            this.bricks = new Brick[200];
            for (int i = 0; i < this.bricks.length; i++) {
                this.bricks[i] = new Brick();
            }
            this.ball = new Ball();
            this.backgroundMusic = new BackgroundMusic();
            this.ballTapPaddleSound = new BallTapPaddleSound();
            this.ballTapBrickSound = new BallTapBrickSound();
            start();
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}