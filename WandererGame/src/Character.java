import java.util.Random;

public abstract class Character {
    double currentHP;
    double maxHP;
    double dp;
    double sp;
    int level;
    int positionX;
    int positionY;
    Random random = new Random();


    public Character() {
        level = 1;
    }

    public int d6() {
        return random.nextInt(5) + 1;
    }

    public int d6Multiple(int index) {
        int result = 0;
        for (int i = 0; i < index; i++) {
            result = result + random.nextInt(5) + 1;
        }
        return result;
    }

    public int randomDirection() {
        return random.nextInt(4);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
