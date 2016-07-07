public class Player extends Entity {

    private static Player instance = null;
    private long experience = 0;
    private long experienceNeeded = 10;

    private BasicTile[][] visibleTiles;

    public Player() {
        awareness = 2;
    }//end constructor

    @Override
    public void fight(Entity e) {
        setHealth(health - e.getAttack());
        e.setHealth(e.getHealth() - attack);
        if (health < 0) {
            System.out.println("You're dead");
        }//end if
        if (e.getHealth() < 0) {
            e.die();
            System.out.println("They died");
        }//end if
    }//end if

    @Override
    public void die() {

    }

    public void placeOn(BasicTile tile) {
        location = tile;
        location.occupyBy(this);
        location.setVisible(true);
        visibleTiles = lookAtTiles();
    }//end placeOn

    public boolean moveTo(BasicTile target) {
        if (!target.isOccupied()) {
            target.occupyBy(this);
            location.occupyBy(null);
            location = target;
            removeVisibleTiles();
            visibleTiles = lookAtTiles();
            return true;
        }//end if
        else {
            this.fight(target.getOccupier());
            return false;
        }//end else
    }//end moveTo

    public BasicTile[][] lookAtTiles() {
        BasicTile[][] connectedTiles = new BasicTile[4][awareness];
        BasicTile start = location;
        start.setVisible(true);
        start = location.getLeftConnection();
        for (int i = 0; i < awareness && start != null; i++, start = start.getLeftConnection()) {
            connectedTiles[0][i] = start;
            start.setVisible(true);
        }//end for
        start = location.getTopConnection();
        for (int i = 0; i < awareness && start != null; i++, start = start.getTopConnection()) {
            connectedTiles[1][i] = start;
            start.setVisible(true);
        }//end for
        start = location.getRightConnection();
        for (int i = 0; i < awareness && start != null; i++, start = start.getRightConnection()) {
            connectedTiles[2][i] = start;
            start.setVisible(true);
        }//end for
        start = location.getBottomConnection();
        for (int i = 0; i < awareness && start != null; i++, start = start.getBottomConnection()) {
            connectedTiles[3][i] = start;
            start.setVisible(true);
        }//end for
        return connectedTiles;
    }//end lookAtTiles

    public void removeVisibleTiles() {
        for (BasicTile[] row : visibleTiles) {
            for (BasicTile tile : row) {
                if (tile != null) {
                    tile.setVisible(false);
                }//end if
            }//end for
        }//end for
        location.setVisible(false);
    }//end removeVisibleTiles


    public long getExperience() {
        return experience;
    }//end getExperience

    public void setExperience(long in) {
        experience = in;
        if (experience >= experienceNeeded) {
            System.out.println("Level up");
            level++;
            experienceNeeded *= .25;
        }//end if
    }//end setExperience

    public long getExperienceNeeded() {
        return experienceNeeded;
    }//end getExperienceNeeded

    public BasicTile[][] getVisibleTiles() {
        return visibleTiles;
    }//end getVisibleTiles

}//end Player class
