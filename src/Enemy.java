import java.util.Random;

public class Enemy extends Entity {

    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int level;

    private int maxValue;

    public Enemy(int levelIn, BasicTile location) {
        level = levelIn;
        maxValue = level * 2;
        maxHealth = generateHealth();
        health = maxHealth;
        attack = generateAttack();
        defense = generateDefense();
        super.location = location;
    }//end class

    private int generateAttack() {
        int out = 1;
        Random rand = new Random();
        for (int i = 0; i < level && out < maxValue; i++) {
            if (rand.nextBoolean()) {
                out++;
            }//end if
        }//end for
        return out;
    }//end generateAttack

    private int generateDefense() {
        int out = 0;
        Random rand = new Random();
        for (int i = 0; i < level && out <= maxValue - attack; i++) {
            if (rand.nextBoolean()) {
                out++;
            }//end if
        }//end for
        return out;
    }//end generateDefense

    private int generateHealth() {
        int out = 0;
        Random rand = new Random();
        for (int i = 0; i < level; i++) {
            out += rand.nextInt(5) + 1;
        }//end for
        return out;
    }//end generateHealth

    public int getDefense() {
        return defense;
    }

    @Override
    public void placeOn(BasicTile tile) {

    }

    @Override
    public boolean moveTo(BasicTile target) {
        return false;
    }

    @Override
    public void fight(Entity target) {
        System.out.println("Enemy fight");
    }//end fight

    @Override
    public void die() {
        this.location.occupyBy(null);
        this.location = null;
    }//end die

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public long giveExperience() {
        return level;
    }//end giveExperience
}//end Enemy class
