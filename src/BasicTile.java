import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BasicTile extends Rectangle {

    public static final int TILE_WIDTH = 75;
    public static final int TILE_HEIGHT = 75;

    private BufferedImage image;

    private int xLoc;
    private int yLoc;

    private boolean left = false;
    private boolean top = false;
    private boolean right = false;
    private boolean bottom = false;

    private BasicTile leftConnection = null;
    private BasicTile topConnection = null;
    private BasicTile rightConnection = null;
    private BasicTile bottomConnection = null;

    private boolean visible = false;

    private Entity occupier = null;

    public BasicTile(int x, int y, boolean l, boolean t, boolean r, boolean b) {
        super(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        xLoc = x;
        yLoc = y;
        left = l;
        top = t;
        right = r;
        bottom = b;
        generateImage();
    }//end BasicTile

    private void generateImage() {
        String imageLocation = "";
        if (left) {
            imageLocation += "Left";
        }//end if
        if (top) {
            imageLocation += "Top";
        }//end if
        if (right) {
            imageLocation += "Right";
        }//end if
        if (bottom) {
            imageLocation += "Bottom";
        }//end if
        if (!imageLocation.equals("")) {
            imageLocation += ".jpg";
            try {
                image = ImageIO.read(new File("./Res/" + imageLocation));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }//end try catch
        }//end if
    }//end generateImage

    public void revealAdjacent() {
        BasicTile temp;
        if (leftConnection != null) {
            leftConnection.setVisible(true);
        }//end if
        if (topConnection != null) {
            topConnection.setVisible(true);
        }//end if
        if (rightConnection != null) {
            rightConnection.setVisible(true);
        }//end if
        if (bottomConnection != null) {
            bottomConnection.setVisible(true);
        }//end if
    }//end revealAdjacent

    public boolean isOccupied() {
        return occupier != null;
    }//end isOccupiedByPlayer

    public void occupyBy(Entity in) {
        occupier = in;
    }//end occupyBy

    public Entity getOccupier() {
        return occupier;
    }//end getOccupier

    public Point centerCoordinates() {
        return new Point(x + TILE_WIDTH / 2, y + TILE_HEIGHT / 2);
    }//end centerCoordinates

    public BasicTile getLeftConnection() {
        return leftConnection;
    }//end getLeftConnection

    public void setLeftConnection(BasicTile in) {
        if (leftConnection == null) {
            left = true;
            leftConnection = in;
            in.setRightConnection(this);
            generateImage();
        }//end if
    }//end setLeftConnection

    public BasicTile getRightConnection() {
        return rightConnection;
    }//end getRightConnection

    public void setRightConnection(BasicTile in) {
        if (rightConnection == null) {
            right = true;
            rightConnection = in;
            in.setLeftConnection(this);
            generateImage();
        }//end if
    }//end setRightConnection

    public BasicTile getTopConnection() {
        return topConnection;
    }//end getTopConnection

    public void setTopConnection(BasicTile in) {
        if (topConnection == null) {
            top = true;
            topConnection = in;
            in.setBottomConnection(this);
            generateImage();
        }//end if
    }//end setTopConnection

    public BasicTile getBottomConnection() {
        return bottomConnection;
    }//end getBottomConnection

    public void setBottomConnection(BasicTile in) {
        if (bottomConnection == null) {
            bottom = true;
            bottomConnection = in;
            in.setTopConnection(this);
            generateImage();
        }//end if
    }//end setBottomConnection

    public boolean isLeft() {
        return left;
    }//end isLeft

    public void setLeft(boolean left) {
        this.left = left;
        generateImage();
    }//end setLeft

    public boolean isTop() {
        return top;
    }//end isTop

    public void setTop(boolean top) {
        this.top = top;
        generateImage();
    }//end setTop

    public boolean isRight() {
        return right;
    }//end isRight

    public void setRight(boolean right) {
        this.right = right;
        generateImage();
    }//end setRight

    public boolean isBottom() {
        return bottom;
    }//end isBottom

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
        generateImage();
    }//end setBottom

    public int getYLoc() {
        return yLoc;
    }

    public int getXLoc() {
        return xLoc;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isVisible() {
        return visible;
    }//end isVisible

    public void setVisible(boolean in) {
        visible = in;
    }//end setVisible
}//end BasicTile Class
