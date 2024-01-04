import java.util.Random;

public abstract class Monster extends Character {

    private int x;
    private int y;
    String img;
    int monsterIndex;
    private static final int tileSize = Board.tileSize;
    private static final int numTiles = Board.tiles;

    public Monster() {
        this.setMonsterIndex();
        Random random = new Random();
        do {
            int randomX = random.nextInt(numTiles);
            int randomY = random.nextInt(numTiles);
            int posX = randomX * tileSize;
            int posY = randomY * tileSize;

            if (!isWall(posX, posY) && !isHero(posX, posY)) {
                x = posX;
                y = posY;
                break;
            }

        } while (true);

        positionX = x;
        positionY = y;
    }

    private boolean isWall(int x, int y) {
        int row = y / tileSize;
        int col = x / tileSize;
        return row >= 0 && row < numTiles && col >= 0 && col < numTiles
                && Floor.wallMatrix(Board.level)[col][row];
    }

    private boolean isHero(int x, int y) {
        int heroX = Board.whereHero()[0];
        int heroY = Board.whereHero()[1];
        int heroTileX = heroX / tileSize;
        int heroTileY = heroY / tileSize;
        int tileX = x / tileSize;
        int tileY = y / tileSize;
        return heroTileX == tileX && heroTileY == tileY;
    }

    public void moveRandomOneTile() {

        do {
            int nextX = this.getPositionX();
            int nextY = this.getPositionY();
            int direction = randomDirection();

            if (direction == 0) {
                nextY = this.getPositionY() + Board.tileSize; // down
            } else if (direction == 1) {
                nextY = this.getPositionY() - Board.tileSize; // up
            } else if (direction == 2) {
                nextX = this.getPositionX() - Board.tileSize; // left
            } else if (direction == 3) {
                nextX = this.getPositionX() + Board.tileSize; // right
            }

            int row = nextY / tileSize;
            int col = nextX / tileSize;
            if (row >= 0 && row < Board.tiles && col >= 0 && col < Board.tiles
                    && !Floor.wallMatrix(Board.level)[col][row]) {
                this.setPositionY(nextY);
                this.setPositionX(nextX);
                break;
            }

        } while (true);
    }

    public void setMonsterIndex() {
        int randomIndex = random.nextInt(10) + 1;

        if (randomIndex == 1) {
            monsterIndex = level + 2;
        } else if (randomIndex > 1 && randomIndex <= 5) {
            monsterIndex = level + 1;
        } else if (randomIndex > 5) {
            monsterIndex = level;
        }
    }
}