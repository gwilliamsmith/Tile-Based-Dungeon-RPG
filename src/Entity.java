
public abstract class Entity {

    protected int health = 10;
    protected int maxHealth = 10;
    protected int attack = 1;
    protected int level = 1;
    protected int awareness = 1;

    protected BasicTile location = null;

    public abstract void placeOn(BasicTile tile);

    public abstract boolean moveTo(BasicTile target);

    public abstract void fight(Entity target);

    public abstract void die();

    public BasicTile getLocation(){
        return location;
    }//end getLocation

    public void setLocation(BasicTile in) {
        location = in;
    }//end setLocation

    public int getHealth(){
        return health;
    }//end getHealth

    public void setHealth(int in){
        health = in;
    }//end setHealth

    public int getMaxHealth(){
        return maxHealth;
    }//end getMaxHealth

    public void setMaxHealth(int in){
        maxHealth = in;
    }//end setMaxHealth

    public int getAttack(){
        return attack;
    }//end getAttack

    public void setAttack(int in){
        attack = in;
    }//end setAttack

    public int getLevel(){
        return level;
    }//end getLevel

    public void setLevel(int in){
        level = in;
    }//end setLevel

    public int getAwareness(){
        return awareness;
    }//end getAwareness

}//end Entity class
