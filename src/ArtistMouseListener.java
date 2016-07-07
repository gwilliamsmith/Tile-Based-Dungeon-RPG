import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArtistMouseListener extends MouseAdapter {

    private Board gameBoard;
    private Artist canvas;
    private BaseFrame base;

    private boolean playerTileClicked;

    private BasicTile playerMoveA = null;

    public ArtistMouseListener(Board in, BaseFrame ref, Artist painter) {
        base = ref;
        gameBoard = in;
        canvas = painter;
    }//end constructor

    public void mouseClicked(MouseEvent e) {
        Player user = base.getUser();
        for (BasicTile[] tiles : Board.TILES) {
            for (BasicTile tile : tiles) {
                if (tile != null) {
                    if (tile.contains(e.getPoint())) {
                        if (tile.getOccupier() instanceof Player) {
                            System.out.println("Player info: Level: " + user.getLevel() +
                                    " Experience: " + user.getExperience() + "/" + user.getExperienceNeeded() +
                                    " Health: " + user.getHealth() + "/" + user.getMaxHealth() +
                                    " Attack: " + user.getAttack());
                        }//end if
                    }//end if
                }//end if
            }//end for
        }//end for
    }//end mouseClicked
}//end ArtistMouseListener class
