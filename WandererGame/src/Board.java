import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JComponent implements KeyListener {

    static int tileSize = 72;
    static int tiles = 10;
    String heroDirection = "img/hero-down.png";
    static Hero hero = new Hero();
    private List<Monster> monsters = new ArrayList<Monster>();
    private List<MapObject> mapObjects = new ArrayList<>();
    int moveCount = 0;
    boolean battleMode;
    Monster enemy;
    int battleTurnIndex;
    boolean heroGotKey;
    boolean bossDead;
    boolean newGame;
    boolean youWin;
    boolean doorOnMap;
    boolean finalDoorOnMap;
    static int level = 1;
    boolean startOfLevel = true;
    boolean gameOver;
    boolean start = true;
    boolean instructions;
    static int score;
    static int highscore;
    static boolean newHighscoreScreen;


    public Board() {                                                                       // setting everything up here

        setPreferredSize(new Dimension(720, 820));
        setVisible(true);

    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        if (start || newGame) {
            PositionedImage startScreen = new PositionedImage("img/startScreen.png", 0, 0);
            startScreen.draw(graphics);
            int statsBarHeight = 100;
            graphics.setColor(Color.black);
            graphics.fillRect(0, (tiles * tileSize), tiles * tileSize, statsBarHeight);

            graphics.setColor(Color.cyan);
            Font font = new Font("Consolas", Font.PLAIN, 20);
            graphics.setFont(font);

            String levelText = ("* PRESS ENTER TO START *");
            graphics.drawString(levelText, 10, (tiles * tileSize) + 40);

        } else if (instructions) {
            PositionedImage instructionsImg = new PositionedImage("img/instructions.png", 0, 0);
            instructionsImg.draw(graphics);
            int statsBarHeight = 100;
            graphics.setColor(Color.black);
            graphics.fillRect(0, (tiles * tileSize), tiles * tileSize, statsBarHeight);


            graphics.setColor(Color.cyan);
            Font font = new Font("Consolas", Font.PLAIN, 20);
            graphics.setFont(font);

            String levelText = ("* PRESS ENTER TO CONTINUE *");
            graphics.drawString(levelText, 10, (tiles * tileSize) + 40);

        } else {
            if (youWin) {
                if (newHighscoreScreen) {
                    PositionedImage youWinNewHighscoreImg = new PositionedImage("img/youWinNewHighscore.png", 0, 0);
                    youWinNewHighscoreImg.draw(graphics);

                    int statsBarHeight = 100;
                    graphics.setColor(Color.black);
                    graphics.fillRect(0, (tiles * tileSize), tiles * tileSize, statsBarHeight);


                    graphics.setColor(Color.cyan);
                    Font font = new Font("Consolas", Font.PLAIN, 20);
                    graphics.setFont(font);

                    String overText = ("Your score: " + score + " | Highscore: " + highscore);
                    graphics.drawString(overText, 31, (tiles * tileSize) + 25);

                    String restartText = ("* PRESS ENTER TO PLAY AGAIN *");
                    graphics.drawString(restartText, 10, (tiles * tileSize) + 55);
                } else {
                    PositionedImage youWinImg = new PositionedImage("img/youWin.png", 0, 0);
                    youWinImg.draw(graphics);

                    int statsBarHeight = 100;
                    graphics.setColor(Color.black);
                    graphics.fillRect(0, (tiles * tileSize), tiles * tileSize, statsBarHeight);


                    graphics.setColor(Color.cyan);
                    Font font = new Font("Consolas", Font.PLAIN, 20);
                    graphics.setFont(font);

                    String overText = ("Your score: " + score + " | Highscore: " + highscore);
                    graphics.drawString(overText, 31, (tiles * tileSize) + 25);

                    String restartText = ("* PRESS ENTER TO PLAY AGAIN *");
                    graphics.drawString(restartText, 10, (tiles * tileSize) + 55);
                }

            } else if (gameOver) {
                if (newHighscoreScreen) {
                    PositionedImage newHighscoreImg = new PositionedImage("img/newHighscore.png", 0, 0);
                    newHighscoreImg.draw(graphics);
                } else {
                    PositionedImage gameOver = new PositionedImage("img/gameOver.png", 0, 0);
                    gameOver.draw(graphics);
                }

                int statsBarHeight = 100;
                graphics.setColor(Color.black);
                graphics.fillRect(0, (tiles * tileSize), tiles * tileSize, statsBarHeight);


                graphics.setColor(Color.cyan);
                Font font = new Font("Consolas", Font.PLAIN, 20);
                graphics.setFont(font);

                String overText = ("Your score: " + score + " | Highscore: " + highscore);
                graphics.drawString(overText, 31, (tiles * tileSize) + 25);

                String restartText = ("* PRESS ENTER TO PLAY AGAIN *");
                graphics.drawString(restartText, 10, (tiles * tileSize) + 55);
            }

            if (!gameOver) {


                if (startOfLevel) {
                    monsters.clear();
                    mapObjects.clear();
                    hero.newLevel();
                    hero.positionX = 0;
                    hero.positionY = 0;
                    heroDirection = "img/hero-down.png";
                    heroGotKey = false;
                    bossDead = false;
                    if (level >= 1) {
                        if (level == 1 || level == 5) {
                            mapObjects.add(new Shield());
                            mapObjects.add(new Sword());
                        }
                        monsters.add(new SkeletonWithKey());
                        monsters.add(new Boss());
                        for (int i = 0; i < 2; i++) {
                            monsters.add(new Skeleton());
                        }

                        if (level > 1) {
                            mapObjects.add(new potionRed());
                            for (int i = 0; i < level - 2; i++) {
                                monsters.add(new Zombie());
                            }
                        }
                        if (level > 3) {
                            mapObjects.add(new potionGreen());
                            mapObjects.add(new potionRed());
                        }
                        if (level > 4) {
                            mapObjects.add(new ZombieEgg());
                        }
                    }
                    startOfLevel = false;
                }

                Floor.drawFloor(graphics, tiles, tileSize);
                Floor.drawWalls(graphics, tiles, tileSize);


                for (Monster monster : monsters) {                                  // works for any type of monsters

                    PositionedImage monsterImg = new PositionedImage(monster.img, monster.positionX, monster.positionY);
                    monsterImg.draw(graphics);

                }

                for (MapObject mapObject : mapObjects) {
                    PositionedImage mapObjectImg = new PositionedImage(mapObject.img, mapObject.positionX, mapObject.positionY);
                    mapObjectImg.draw(graphics);
                }

                PositionedImage heroImg = new PositionedImage(heroDirection, hero.getPositionX(), hero.getPositionY());
                heroImg.draw(graphics);

                if (battleMode) {
                    graphics.setColor(Color.red);
                    graphics.drawRect(hero.positionX, hero.positionY, 72, 72);
                }

                // Draw the stats bar
                int statsBarHeight = 100;  // Adjust the height based on your requirements
                graphics.setColor(Color.black);
                graphics.fillRect(0, (tiles * tileSize), tiles * tileSize, statsBarHeight);

                // Draw the stats text
                graphics.setColor(Color.cyan);
                Font font = new Font("Consolas", Font.PLAIN, 14);
                graphics.setFont(font);

                String levelText = ("Level: " + level);
                graphics.drawString(levelText, 10, (tiles * tileSize) + 20);  // Adjust X and Y positions based on your requirements

                String statsText = ("Hero (Level " + hero.heroLevel + ") HP: " + (int) hero.currentHP + "/" + (int) hero.maxHP + " | DP: " + (int) hero.dp + " | SP: " + (int) hero.sp);
                graphics.drawString(statsText, 10, (tiles * tileSize) + 40);

                if (finalDoorOnMap) {
                    String keyText = ("Could this strange door lead outside of this dungeon?");
                    graphics.drawString(keyText, 10, (tiles * tileSize) + 60);
                }
                if (doorOnMap) {
                    String keyText = ("Go to the door to enter the next level.");
                    graphics.drawString(keyText, 10, (tiles * tileSize) + 60);
                } else if (!heroGotKey && !bossDead) {
                    String keyText = ("Your hero must find a key and defeat the boss.");
                    graphics.drawString(keyText, 10, (tiles * tileSize) + 60);
                } else if (heroGotKey && !bossDead) {
                    String keyText = ("You found the key! Now you must defeat the boss.");
                    graphics.drawString(keyText, 10, (tiles * tileSize) + 60);
                } else if (!heroGotKey && bossDead) {
                    String keyText = ("You defeated the boss! Now you must find the key.");
                    graphics.drawString(keyText, 10, (tiles * tileSize) + 60);
                }
            }

        }
    }

    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("Awesome RPG Adventure by Karolínka");
        Board board = new Board();
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(board);


    }

    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // But actually we can use just this one for our goals here
    @Override
    public void keyReleased(KeyEvent e) {

        if (start) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                start = false;
                instructions = true;
            }
        } else if (newGame) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                newGame = false;
                startOfLevel = true;
            }
        } else if (instructions) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                instructions = false;
                startOfLevel = true;
            }
        } else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                level = 1;
                hero = new Hero();
                newHighscoreScreen = false;
                newGame = true;
                gameOver = false;
                youWin = false;
                doorOnMap = false;
                finalDoorOnMap = false;
            }
        } else {

            int nextHeroX = hero.getPositionX();
            int nextHeroY = hero.getPositionY();


            while (battleMode) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    battle(enemy);
                    battleTurnIndex++;
                } else {
                    break;
                }

            }
            if (!battleMode) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    heroDirection = "img/hero-up.png";
                    nextHeroY -= tileSize;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    heroDirection = "img/hero-down.png";
                    nextHeroY += tileSize;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    heroDirection = "img/hero-left.png";
                    nextHeroX -= tileSize;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    heroDirection = "img/hero-right.png";
                    nextHeroX += tileSize;
                }

                if (canMoveThere(nextHeroX, nextHeroY)) {
                    hero.setPositionX(nextHeroX);
                    hero.setPositionY(nextHeroY);
                    moveCount++;
                }


                if (moveCount == 2) {
                   /* if (level > 5) {
                        for (Monster monster : monsters) {
                            for (int i = 0; i < 3; i++) {
                                monster.moveRandomOneTile();
                            }
                            moveCount = 0;  // Reset move count after moving skeletons
                        }
                    } else if (level > 2) {
                        for (Monster monster : monsters) {
                            for (int i = 0; i < 2; i++) {
                                monster.moveRandomOneTile();
                            }
                            moveCount = 0;  // Reset move count after moving skeletons
                        }
                    } else {*/
                    for (Monster monster : monsters) {
                        monster.moveRandomOneTile();
                        moveCount = 0;  // Reset move count after moving skeletons
                    }
                    // }
                }
            }
        }
        // and redraw to have a new picture with the new coordinates
        repaint();

    }

    public static int[] whereHero() {
        int[] whereHero = new int[]{hero.getPositionX(), hero.getPositionY()};
        return whereHero;
    }

    public boolean canMoveThere(int x, int y) {
        int row = y / tileSize;
        int col = x / tileSize;

        if (x == hero.getPositionX() && y == hero.getPositionY()) {                  // if hero didn't move, return false
            return false;
        }

        if (Floor.wallMatrix(level)[col][row]) {                                    // if there's wall, hero cannot go there
            return false;
        }

        for (Monster monster : monsters) {                                         // if there's a monster, battle starts
            if (monster.getPositionX() == x && monster.getPositionY() == y) {
                enemy = monster;
                battleMode = true;
                return false;
            }
        }

        for (MapObject mapObject : mapObjects) {                                         // if there's an object, hero uses it, hero moves
            if (mapObject.getPositionX() == x && mapObject.getPositionY() == y) {
                if (mapObject instanceof potionRed) {
                    hero.redPotion();
                }
                if (mapObject instanceof potionGreen) {
                    hero.greenPotion();
                }
                if (mapObject instanceof Shield) {
                    hero.gotShield();
                }
                if (mapObject instanceof Sword) {
                    hero.gotSword();
                }
                if (mapObject instanceof ZombieEgg) {
                    spawnZombies();
                }
                if (mapObject instanceof Door) {
                    stepOnDoor();
                }
                if (mapObject instanceof FinalDoor) {
                    stepOnFinalDoor();
                }
                mapObjects.remove(mapObject);
                return true;
            }
        }
        return true;                                                                // if there is empty tile, hero goes there
    }


    public void battle(Monster monster) {

        if (battleTurnIndex % 2 == 0) {                                                      // hero attacks

            double strike = hero.sp * hero.d6() * battleModifier();

            if (strike > monster.dp) {                                             // if the strike is successful
                monster.currentHP = monster.currentHP - (strike - monster.dp);

                if (monster.currentHP <= 0) {
                    if (monster instanceof SkeletonWithKey) {
                        heroGotKey = true;
                    } else if (monster instanceof Boss) {
                        bossDead = true;
                    }

                    monsters.remove(monster);
                    hero.levelUp();
                    battleTurnIndex = 0;
                    battleMode = false;

                    if (heroGotKey && bossDead && level == 10) {
                        mapObjects.add(new FinalDoor());
                        finalDoorOnMap = true;
                    } else if (heroGotKey && bossDead && !doorOnMap) {
                        mapObjects.add(new Door());
                        doorOnMap = true;
                    }
                }
            }


        } else {

            double strike = monster.sp * monster.d6() * battleModifier();                                // monster attacks
            if (strike > hero.dp) {
                hero.currentHP = hero.currentHP - (strike - hero.dp);
                if (hero.currentHP <= 0) {
                    setScore();
                    gameOver = true;
                }
            }

        }

    }

    public static void setScore() {
        Path path = Paths.get("src/highscore.txt");
        score = hero.heroLevel * ((int) hero.currentHP + (int) hero.dp + (int) hero.sp);
        if (score < 0) {
            score = 0;
        }
        try {
            String highscoreString = Files.readString(path);
            highscore = Integer.parseInt(highscoreString);
            if (score > highscore) {
                newHighscoreScreen = true;
                Files.write(path, String.valueOf(score).getBytes());
            }
        } catch (IOException e) {
            System.out.println("Unable to track highscore, but you did great! :-)");
        }
    }

    public static double battleModifier() {
        double modifier = level * 0.5;
        if (modifier < 1.2) {
            return 1.2;
        } else {
            return modifier;
        }
    }

    public void spawnZombies() {
        Random random = new Random();
        int index = random.nextInt(8) + 1;
        for (int i = 0; i < index; i++) {
            monsters.add(new Zombie());
            moveCount = 0;
        }
    }

    public void stepOnFinalDoor() {
        setScore();
        youWin = true;
        gameOver = true;
    }

    public void stepOnDoor() {
        level++;
        startOfLevel = true;
        battleMode = false;
        moveCount = 0;
        doorOnMap = false;
    }
}