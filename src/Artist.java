import javax.swing.*;
import java.awt.*;

public class Artist extends JPanel {

    private Board gameBoard = null;
    private BaseFrame base;
    private boolean playerTileClicked = false;

    public static int WINDOW_CENTER_WIDTH;
    public static int WINDOW_CENTER_HEIGHT;

    public int windowX = 0;
    public int windowY = 0;

    public Artist(Board gameBoardIn, BaseFrame ref) {
        gameBoard = gameBoardIn;
        setSize(BaseFrame.WINDOW_DIMENSION);
        addMouseListener(new ArtistMouseListener(gameBoard, base, this));
        base = ref;
        WINDOW_CENTER_WIDTH = base.getWidth() / 2;
        WINDOW_CENTER_HEIGHT = base.getHeight() / 2;
    }//end constructor

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Player user = base.getUser();

        g.setColor(Color.MAGENTA);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(0, 0, base.getWidth(), base.getHeight());

        int middleWidth = getWidth() / 2 - BasicTile.TILE_WIDTH / 2;
        int middleHeight = getHeight() / 2 - BasicTile.TILE_HEIGHT / 2;

        drawTile(g, user.getLocation(), middleWidth, middleHeight);
        BasicTile[][] visibleTiles = user.getVisibleTiles();
        for (int i = 0; i < visibleTiles[0].length && visibleTiles[0][i] != null; i++) {
            drawTile(g, visibleTiles[0][i], middleWidth - BasicTile.TILE_WIDTH * (i + 1), middleHeight);
        }//end for
        for (int i = 0; i < visibleTiles[1].length && visibleTiles[1][i] != null; i++) {
            drawTile(g, visibleTiles[1][i], middleWidth, middleHeight - BasicTile.TILE_HEIGHT * (i + 1));
        }//end for
        for (int i = 0; i < visibleTiles[2].length && visibleTiles[2][i] != null; i++) {
            drawTile(g, visibleTiles[2][i], middleWidth + BasicTile.TILE_WIDTH * (i + 1), middleHeight);
        }//end for
        for (int i = 0; i < visibleTiles[3].length && visibleTiles[3][i] != null; i++) {
            drawTile(g, visibleTiles[3][i], middleWidth, middleHeight + BasicTile.TILE_HEIGHT * (i + 1));
        }//end for
    }//end paint

    private void drawTile(Graphics g, BasicTile tile, int x, int y) {
        g.drawImage(tile.getImage(), x, y, null);
        if (tile.isOccupied()) {
            Entity unit = tile.getOccupier();
            if (unit instanceof Player) {
                g.setColor(Color.BLUE);
                g.fillRect(x + BasicTile.TILE_WIDTH / 3, y + BasicTile.TILE_HEIGHT / 3, BasicTile.TILE_WIDTH / 3, BasicTile.TILE_HEIGHT / 3);
                //g.drawString("Player", x+35, y+35);
            }//end if
            else if (unit instanceof Enemy) {
                g.setColor(Color.RED);
                //g.drawString("Enemy", x+35, y+35);
                g.fillRect(x + BasicTile.TILE_WIDTH / 3, y + BasicTile.TILE_HEIGHT / 3, BasicTile.TILE_WIDTH / 3, BasicTile.TILE_HEIGHT / 3);
            }//end else if
        }//end if
    }//end drawTile
}//end Artist
