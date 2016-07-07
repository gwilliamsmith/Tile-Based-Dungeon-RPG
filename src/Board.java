import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Board {

    public static final int BOARD_WIDTH = 21;
    public static final int BOARD_HEIGHT = 11;

    private int startingX;
    private int startingY;

    private static final double MIN_TILE_COVERAGE = .5;
    private static final int MIN_TILE_COUNT = (int) ((BOARD_WIDTH * BOARD_HEIGHT) * MIN_TILE_COVERAGE);
    private int tileCount = 0;

    private BasicTile startingTile;

    private BaseFrame base;

    public static BasicTile[][] TILES = new BasicTile[BOARD_HEIGHT][BOARD_WIDTH];

    public Board(BaseFrame ref) {
        base = ref;
        Random rand = new Random();
        startingX = rand.nextInt(BOARD_WIDTH);
        startingY = rand.nextInt(BOARD_HEIGHT);
        TILES[startingY][startingX] = new BasicTile(startingX, startingY, true, true, true, false);
        tileCount++;
        while (tileCount < MIN_TILE_COUNT) {
            generateBoard(TILES[startingY][startingX]);
            if (tileCount < MIN_TILE_COUNT) {
                resetBoard();
            }//end if
        }//end while
        finishBoard();
    }//end constructor

    private void generateBoard(BasicTile startingTile) {
        LinkedList<BoardCreationTuple> moves = new LinkedList<>();
        int localX = startingTile.getXLoc();
        int localY = startingTile.getYLoc();
        if (startingTile.isLeft() && startingTile.getLeftConnection() == null && localX > 0) {
            if (TILES[localY][localX - 1] == null) {
                boolean left = potentialLeft(startingTile, 1);
                boolean up = potentialTop(startingTile, 1);
                boolean down = potentialBottom(startingTile, 1);
                BasicTile temp = new BasicTile(localX - 1, localY, left, up, true, down);
                TILES[localY][localX - 1] = temp;
                startingTile.setLeftConnection(temp);
                temp.setRightConnection(temp);
                tileCount++;
                generateBoard(temp);
            }//end if
        }//end if
        if (startingTile.isTop() && startingTile.getTopConnection() == null && localY > 0) {
            if (TILES[localY - 1][localX] == null) {
                boolean left = potentialLeft(startingTile, 2);
                boolean up = potentialTop(startingTile, 2);
                boolean right = potentialRight(startingTile, 2);
                BasicTile temp = new BasicTile(localX, localY - 1, left, up, right, true);
                TILES[localY - 1][localX] = temp;
                startingTile.setTopConnection(temp);
                temp.setBottomConnection(startingTile);
                tileCount++;
                generateBoard(temp);
            }//end if
        }//end if
        if (startingTile.isRight() && startingTile.getRightConnection() == null && localX < BOARD_WIDTH - 1) {
            if (TILES[localY][localX + 1] == null) {
                boolean up = potentialTop(startingTile, 3);
                boolean right = potentialRight(startingTile, 3);
                boolean down = potentialBottom(startingTile, 3);
                BasicTile temp = new BasicTile(localX + 1, localY, true, up, right, down);
                TILES[localY][localX + 1] = temp;
                startingTile.setRightConnection(temp);
                temp.setLeftConnection(startingTile);
                tileCount++;
                generateBoard(temp);
            }//end if
        }//end if
        if (startingTile.isBottom() && startingTile.getBottomConnection() == null && localY < BOARD_HEIGHT - 1) {
            if (TILES[localY + 1][localX] == null) {
                boolean left = potentialLeft(startingTile, 4);
                boolean right = potentialRight(startingTile, 4);
                boolean down = potentialBottom(startingTile, 4);
                BasicTile temp = new BasicTile(localX, localY + 1, left, true, right, down);
                TILES[localY + 1][localX] = temp;
                startingTile.setBottomConnection(temp);
                temp.setTopConnection(startingTile);
                tileCount++;
                generateBoard(temp);
            }//end if
        }//end if
    }//end generateBoard

    public void finishBoard() {
        ArrayList<BasicTile> locations = new ArrayList<>();
        for (BasicTile[] row : TILES) {
            for (BasicTile tile : row) {
                if (tile != null) {
                    locations.add(tile);
                    if (tile.isLeft()) {
                        if (tile.getXLoc() <= 0) {
                            tile.setLeft(false);
                        }//end if
                        else {
                            if (TILES[tile.getYLoc()][tile.getXLoc() - 1] == null) {
                                tile.setLeft(false);
                            }//end if
                            else {
                                if (tile.getLeftConnection() == null) {
                                    tile.setLeftConnection(TILES[tile.getYLoc()][tile.getXLoc() - 1]);
                                }//end if
                            }//end else
                        }//end else
                    }//end if
                    if (tile.isRight()) {
                        if (tile.getXLoc() >= BOARD_WIDTH - 1) {
                            tile.setRight(false);
                        }//end if
                        else {
                            if (TILES[tile.getYLoc()][tile.getXLoc() + 1] == null) {
                                tile.setRight(false);
                            }//end if
                            else {
                                if (tile.getRightConnection() == null) {
                                    tile.setRightConnection(TILES[tile.getYLoc()][tile.getXLoc() + 1]);
                                }//end if
                            }//end else
                        }//end else
                    }//end if
                    if (tile.isTop()) {
                        if (tile.getYLoc() <= 0) {
                            tile.setTop(false);
                        }//end if
                        else {
                            if (TILES[tile.getYLoc() - 1][tile.getXLoc()] == null) {
                                tile.setTop(false);
                            }//end if
                            else {
                                if (tile.getTopConnection() == null) {
                                    tile.setTopConnection(TILES[tile.getYLoc() - 1][tile.getXLoc()]);
                                }//end if
                            }//end else
                        }//end else
                    }//end if
                    if (tile.isBottom()) {
                        if (tile.getYLoc() >= BOARD_HEIGHT - 1) {
                            tile.setBottom(false);
                        }//end if
                        else {
                            if (TILES[tile.getYLoc() + 1][tile.getXLoc()] == null) {
                                tile.setBottom(false);
                            }//end if
                            else {
                                if (tile.getBottomConnection() == null) {
                                    tile.setBottomConnection(TILES[tile.getYLoc() + 1][tile.getXLoc()]);
                                }//end if
                            }//end else
                        }//end else
                    }//end if
                }//end if
            }//end for
        }//end for
        Random rand = new Random();
        startingTile = locations.get(rand.nextInt(locations.size()));
        /*
        startingTile.occupyBy(base.getUser());
        startingTile.setVisible(true);
        startingTile.revealAdjacent();
        */
        base.getUser().placeOn(startingTile);
        int numberOfEnemies = rand.nextInt(tileCount / 2) + 1;
        for (int i = 0; i < numberOfEnemies; i++) {
            int randTile = rand.nextInt(locations.size());
            while (locations.get(randTile).isOccupied()) {
                randTile = rand.nextInt(locations.size());
            }//end while
            BasicTile temp = locations.get(randTile);
            temp.occupyBy(new Enemy(1, temp));
        }//end for
    }//end finishBoard

    public void resetBoard() {
        Random rand = new Random();
        TILES = new BasicTile[BOARD_HEIGHT][BOARD_WIDTH];
        startingX = rand.nextInt(BOARD_WIDTH);
        startingY = rand.nextInt(BOARD_HEIGHT);
        BasicTile temp = createNewTile(new BasicTile(startingX, startingY, false, false, false, false), startingX, startingY, -1);
        TILES[startingY][startingX] = temp;
        tileCount = 1;
    }//end resetBoard

    public BasicTile createNewTile(BasicTile startingTile, int x, int y, int direction) {
        boolean left = potentialLeft(startingTile, direction);
        boolean top = potentialTop(startingTile, direction);
        boolean right = potentialRight(startingTile, direction);
        boolean bottom = potentialBottom(startingTile, direction);
        return new BasicTile(x, y, left, top, right, bottom);
    }//end createNewTile

    public boolean potentialLeft(BasicTile startingTile, int direction) {
        if (direction == 3) {
            return true;
        }//end if
        Random rand = new Random();
        double percent = 1 - ((double) tileCount) / (double) MIN_TILE_COUNT;
        boolean out = rand.nextBoolean() && startingTile.getXLoc() - 1 > 0;
        if (out) {
            out = TILES[startingTile.getYLoc()][startingTile.getXLoc() - 1] == null;
            if (!out) {
                return false;
            }//end if
        }//end if
        return out;
    }//end potentialLeft

    public boolean potentialTop(BasicTile startingTile, int direction) {
        if (direction == 4) {
            return true;
        }//end if
        Random rand = new Random();
        double percent = 1 - ((double) tileCount) / (double) MIN_TILE_COUNT;
        boolean out = rand.nextBoolean() && startingTile.getYLoc() - 1 > 0;
        if (out) {
            out = TILES[startingTile.getYLoc() - 1][startingTile.getXLoc()] == null;
            if (!out) {
                return false;
            }//end if
        }//end if
        return out;
    }//end potentialTop

    public boolean potentialRight(BasicTile startingTile, int direction) {
        if (direction == 1) {
            return true;
        }//end if
        Random rand = new Random();
        double percent = 1 - ((double) tileCount) / (double) MIN_TILE_COUNT;
        boolean out = rand.nextBoolean() && startingTile.getXLoc() + 1 < BOARD_WIDTH - 1;
        if (out) {
            out = TILES[startingTile.getYLoc()][startingTile.getXLoc() + 1] == null;
            if (!out) {
                return false;
            }//end if
        }//end if
        return out;
    }//end potentialRight

    public boolean potentialBottom(BasicTile startingTile, int direction) {
        if (direction == 2) {
            return true;
        }//end if
        Random rand = new Random();
        double percent = 1 - ((double) tileCount) / (double) MIN_TILE_COUNT;
        boolean out = rand.nextBoolean() && startingTile.getYLoc() + 1 < BOARD_HEIGHT - 1;
        if (out) {
            out = TILES[startingTile.getYLoc() + 1][startingTile.getXLoc()] == null;
            if (!out) {
                return false;
            }//end if
        }//end if
        return out;
    }//end potentialDown

}//end Board class
