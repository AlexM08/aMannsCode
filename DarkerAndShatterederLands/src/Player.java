import java.util.Random;

public class Player {

    // Player starting stats
    private static final int FULL_HEALTH = 100;
    private static final int STARTING_NUMBER_OF_POTIONS = 3;
    private static final int POTION_HEALING = 25;
    private static final int MAXIMUM_ATTACK_DAMAGE = 25;
    private static final String NO_NAME = "";

    // Player equipment
    private int health;
    private Armor Armor;
    private int attackDamage;
    private int killCount;
    private boolean hasWeapon;
    private boolean hasArmor;
    private Backpack backpack;
    private String name;
    private int potions;
    private Weapon weapon;

    // Java random num generator
    private static Random RANDOM = new Random();

    // Builds a new Player
    public Player() {
        name = NO_NAME;
        hasWeapon = false;
        hasArmor = false;
        health = FULL_HEALTH;
        potions = STARTING_NUMBER_OF_POTIONS;
        killCount = 0;
        weapon = new Weapon("stick and rock");
        Armor = new Armor("dirty rags");
        backpack = new Backpack();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(Armor Armor) {
        this.Armor = Armor;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }

    public void setHasArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public Armor getArmor() {
        return Armor;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getKillCount() {
        return killCount;
    }

    public boolean isHasWeapon() {
        return hasWeapon;
    }

    public boolean isHasArmor() {
        return hasArmor;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public String getName() {
        return name;
    }

    public int getPotions() {
        return potions;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public static Random getRANDOM() {
        return RANDOM;
    }

    // Player damage-dealing
    public int attack() {
        if (hasWeapon) {
            weapon.useWeapon();
            if (weapon.hitpoints() <= 0) {
                System.out.println("\nYour " + weapon.name() + " broke.");
                hasWeapon = false;
            }
            return RANDOM.nextInt(MAXIMUM_ATTACK_DAMAGE) + weapon.damageIncrease();
        }
        return RANDOM.nextInt(MAXIMUM_ATTACK_DAMAGE);
    }

    // Player damage-taking
    public void takeDamage(int damage) {
        if (hasArmor) {
            Armor.useArmor();
            health = health - Math.max(damage - Armor.damageBlocked(), 0);
            if (Armor.hitpoints() <= 0) {
                System.out.println("\nYour " + Armor.name() + " broke.");
                hasArmor = false;
            }
        }
        else {
            health = health - damage;
        }
    }

    // Player consume potion and heal
    public void usePotion() {
        if (potions <= 0) return;
        health = health + POTION_HEALING;
        potions--;
    }

    // killCount up
    public void killCountUp() {
        killCount++;
    }

    // Player arms themselves
    public void addWeapon(String type) {
        if (type == null) return;
        weapon = new Weapon(type);
        hasWeapon = true;
    }

    // Player armors themselves
    public void addArmor(String type) {
        if (type == null) return;
        Armor = new Armor(type);
        hasArmor = true;
    }
}

    public String getData()
    {
        return
                name + " "
                        + hasWeapon + " "
                        + hasArmor + " "
                        + enemiesKilled + " "
                        + health + " "
                        + potionsRemaining + " "
                        + backpack.getGold();
    } // end of method getData()

    public void reset()
    {
        health = FULL_HEALTH;
        potionsRemaining = DEFAULT_NUMBER_OF_POTIONS;
        enemiesKilled = 0;
        hasSword = false;
        hasArmor = false;
    }
