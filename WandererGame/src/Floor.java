import java.awt.*;

public class Floor {


    public static void drawFloor(Graphics graphics, int tiles, int imgPixels) {

        for (int i = 0; i < tiles; i++) {

            for (int j = 0; j < tiles; j++) {
                PositionedImage tile = new PositionedImage("img/floor.png", j * imgPixels, i * imgPixels);
                tile.draw(graphics);
            }
        }
    }


    public static void drawWalls(Graphics graphics, int tiles, int imgPixels) {

        for (int i = 0; i < tiles; i++) {
            for (int j = 0; j < tiles; j++) {
                if (wallMatrix(Board.level)[j][i]) {
                    PositionedImage wall = new PositionedImage("img/wall.png", j * imgPixels, i * imgPixels);
                    wall.draw(graphics);
                }

            }
        }


    }

    public static boolean[][] wallMatrix(int type) {
        boolean[][] wallMatrix = new boolean[Board.tiles][Board.tiles];

        if (type == 1 || type == 5 || type == 9|| type > 12) {
            wallMatrix[0][3] = true;
            wallMatrix[0][4] = true;
            wallMatrix[1][4] = true;
            wallMatrix[2][4] = true;
            wallMatrix[2][5] = true;

            wallMatrix[7][8] = true;
            wallMatrix[7][9] = true;
            wallMatrix[8][8] = true;
            wallMatrix[8][9] = true;

            wallMatrix[5][5] = true;
            wallMatrix[5][6] = true;
            wallMatrix[5][7] = true;
            wallMatrix[6][3] = true;


            wallMatrix[7][2] = true;
            wallMatrix[7][3] = true;
            wallMatrix[7][4] = true;

            wallMatrix[3][1] = true;
            wallMatrix[4][1] = true;

            wallMatrix[2][0] = true;
            wallMatrix[2][1] = true;

            wallMatrix[4][8] = true;
            wallMatrix[4][9] = true;

            wallMatrix[8][6] = true;
            wallMatrix[9][6] = true;

            wallMatrix[1][9] = true;
            wallMatrix[2][9] = true;
            wallMatrix[2][8] = true;

            wallMatrix[8][1] = true;
            wallMatrix[8][2] = true;
            wallMatrix[9][2] = true;

        }

        if (type == 2 || type == 6 || type == 10){
            wallMatrix[0][2] = true;
            wallMatrix[0][3] = true;
            wallMatrix[0][4] = true;
            wallMatrix[1][4] = true;
            wallMatrix[2][4] = true;
            wallMatrix[2][5] = true;
            wallMatrix[2][6] = true;

            wallMatrix[7][8] = true;
            wallMatrix[8][8] = true;

            wallMatrix[7][6] = true;
            wallMatrix[7][7] = true;
            wallMatrix[7][3] = true;


            wallMatrix[7][2] = true;
            wallMatrix[6][3] = true;
            wallMatrix[7][4] = true;

            wallMatrix[3][1] = true;
            wallMatrix[4][1] = true;

            wallMatrix[4][4] = true;
            wallMatrix[4][5] = true;
            wallMatrix[5][5] = true;

            wallMatrix[2][0] = true;
            wallMatrix[2][1] = true;

            wallMatrix[4][8] = true;
            wallMatrix[4][9] = true;
            wallMatrix[5][8] = true;
            wallMatrix[5][9] = true;

            wallMatrix[8][6] = true;
            wallMatrix[9][6] = true;

            wallMatrix[1][9] = true;
            wallMatrix[2][9] = true;
            wallMatrix[2][8] = true;

            wallMatrix[8][1] = true;
            wallMatrix[8][2] = true;
            wallMatrix[9][2] = true;

        }

        if (type == 3 || type == 7 || type == 11) {
            wallMatrix[1][0] = true;
            wallMatrix[2][1] = true;
            wallMatrix[3][2] = true;
            wallMatrix[4][3] = true;

            wallMatrix[6][5] = true;

            wallMatrix[6][6] = true;
            wallMatrix[6][7] = true;
            wallMatrix[7][6] = true;
            wallMatrix[7][7] = true;

            wallMatrix[2][8] = true;
            wallMatrix[3][8] = true;

            wallMatrix[6][2] = true;
            wallMatrix[6][3] = true;
            wallMatrix[7][2] = true;
            wallMatrix[7][3] = true;

            wallMatrix[0][4] = true;
            wallMatrix[0][5] = true;
            wallMatrix[0][6] = true;
            wallMatrix[0][7] = true;
            wallMatrix[0][8] = true;
            wallMatrix[0][9] = true;

            wallMatrix[2][0] = true;
            wallMatrix[3][1] = true;
            wallMatrix[4][2] = true;

            wallMatrix[8][7] = true;
            wallMatrix[7][8] = true;

            wallMatrix[2][5] = true;
            wallMatrix[2][6] = true;
            wallMatrix[2][7] = true;
            wallMatrix[4][8] = true;
            wallMatrix[4][9] = true;
            wallMatrix[7][1] = true;
            wallMatrix[8][3] = true;


            wallMatrix[8][8] = true;
            wallMatrix[9][8] = true;
            wallMatrix[9][9] = true;


            wallMatrix[9][0] = true;
            wallMatrix[8][0] = true;
            wallMatrix[9][1] = true;
            wallMatrix[8][1] = true;


        }

        if (type == 4 || type == 8 || type == 12){
            wallMatrix[2][0] = true;
            wallMatrix[3][0] = true;
            wallMatrix[3][1] = true;

            wallMatrix[0][2] = true;
            wallMatrix[0][3] = true;
            wallMatrix[1][3] = true;

            wallMatrix[6][6] = true;
            wallMatrix[6][7] = true;
            wallMatrix[7][6] = true;
            wallMatrix[7][7] = true;

            wallMatrix[9][0] = true;
            wallMatrix[9][1] = true;
            wallMatrix[9][2] = true;
            wallMatrix[9][3] = true;
            wallMatrix[9][4] = true;

            wallMatrix[5][5] = true;
            wallMatrix[6][5] = true;
            wallMatrix[8][7] = true;

            wallMatrix[4][0] = true;
            wallMatrix[5][0] = true;
            wallMatrix[4][1] = true;
            wallMatrix[5][1] = true;
            wallMatrix[5][2] = true;
            wallMatrix[6][1] = true;

            wallMatrix[0][6] = true;
            wallMatrix[1][6] = true;
            wallMatrix[2][6] = true;
            wallMatrix[3][6] = true;
            wallMatrix[3][7] = true;
            wallMatrix[3][8] = true;
            wallMatrix[0][9] = true;
            wallMatrix[0][7] = true;
            wallMatrix[0][8] = true;

            wallMatrix[6][2] = true;
            wallMatrix[6][3] = true;
            wallMatrix[7][2] = true;
            wallMatrix[7][3] = true;

            wallMatrix[8][8] = true;
            wallMatrix[9][8] = true;
            wallMatrix[9][9] = true;
        }
        return wallMatrix;
    }

}
