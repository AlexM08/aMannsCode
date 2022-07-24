public class Weapon
{
    public static final int WOOD_HITPOINTS = 10;

    public static final int WOOD_DAMAGE_INCREASE = 5;

    public static final int METAL_HITPOINTS = 15;

    public static final int METAL_DAMAGE_INCREASE = 10;

    public static final int GOLD_HITPOINTS = 20;

    public static final int GOLD_DAMAGE_INCREASE = 15;

    private int damageIncrease;
    private int hitpoints;
    private String name;
    
    public Weapon weapon(String type)
    {
        Weapon weapon = null;
        if (type == null)

        name = type + " weapon";

        switch (type)
        {
            case "wood":
                hitpoints = WOOD_HITPOINTS;
                damageIncrease = WOOD_DAMAGE_INCREASE;
                break;

            case "metal":
                hitpoints = METAL_HITPOINTS;
                damageIncrease = METAL_DAMAGE_INCREASE;
                break;

            case "gold":
                hitpoints = GOLD_HITPOINTS;
                damageIncrease = GOLD_DAMAGE_INCREASE;
                break;

            default:
                name = "wood weapon";
                hitpoints = WOOD_HITPOINTS;
                damageIncrease = WOOD_DAMAGE_INCREASE;
                break;
        }
        return weapon;
    } 
    
    public String name()
    {
        return name;
    }

    public int hitpoints()
    {
        return hitpoints;
    }
    
    public int damageIncrease()
    {
        return damageIncrease;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public void useWeapon()
    {
        hitpoints--;
    }

    public void repairWeapon(int hitpointsToRepair)
    {
        hitpoints = hitpoints + hitpointsToRepair;
    } 
} 
