import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArtistKeyListener implements KeyListener {

    private Board gameBoard;
    private BaseFrame base;
    private Artist painter;

    public ArtistKeyListener(Board in, BaseFrame ref, Artist painterIn) {
        base = ref;
        gameBoard = in;
        painter = painterIn;
    }//end constructor

    @Override
    public void keyTyped(KeyEvent e) {

    }//end keyTyped

    @Override
    public void keyPressed(KeyEvent e) {
        Player user = base.getUser();
        int xInfluence = 0;
        int yInfluence = 0;
        boolean success = false;
        if ((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && user.getLocation().getLeftConnection() != null) {
            success = user.moveTo(user.getLocation().getLeftConnection());
        }//end if
        else if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) && user.getLocation().getTopConnection() != null) {
            success = user.moveTo(user.getLocation().getTopConnection());
        }//end if
        else if ((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && user.getLocation().getRightConnection() != null) {
            success = user.moveTo(user.getLocation().getRightConnection());
        }//end if
        else if ((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && user.getLocation().getBottomConnection() != null) {
            success = user.moveTo(user.getLocation().getBottomConnection());
        }//end if
    }//end keyPressed

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
