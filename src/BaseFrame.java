import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {

    public static Dimension WINDOW_DIMENSION;

    private Artist painter;

    private Board gameBoard;

    private Player user = new Player();

    public BaseFrame() {
        WINDOW_DIMENSION = new Dimension(BasicTile.TILE_WIDTH * Board.BOARD_WIDTH, BasicTile.TILE_HEIGHT * Board.BOARD_HEIGHT);
        setSize(WINDOW_DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        gameBoard = new Board(this);
        painter = new Artist(gameBoard, this);
        add(painter);
        addKeyListener(new ArtistKeyListener(gameBoard, this, painter));
        Timer timer = new Timer(1, e -> painter.repaint());
        timer.start();
    }//end constructor

    public Player getUser() {
        return user;
    }//end getUser
}//end BaseFrameClass
