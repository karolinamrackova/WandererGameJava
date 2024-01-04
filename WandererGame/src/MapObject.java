import java.util.Random;

public class MapObject {
    String img;
    int x;
    int y;

    int positionX;
    int positionY;
    private static final int tileSize = Board.tileSize;
    private static final int numTiles = Board.tiles;


    public MapObject() {
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
