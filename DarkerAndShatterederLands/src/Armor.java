public class Armor {
        public static final int LEATHER_HITPOINTS = 5;
        public static final int LEATHER_DAMGAGE_BLOCKED = 2;

        public static final int IRON_HITPOINTS = 10;
        public static final int IRON_DAMAGE_BLOCKED = 5;

        public static final int PLATINUM_HITPOINTS = 20;
        public static final int PLATINUM_DAMAGE_BLOCKED = 10;

        private String name;
        private int hitpoints;
        private int damageBlocked;

        public Armor createArmor(String type)
        {
            Armor armor = null;
            if (type == null)

            name = type + " Armor";

            switch (type)
            {
                case "leather":
                    hitpoints = LEATHER_HITPOINTS;
                    damageBlocked = LEATHER_DAMGAGE_BLOCKED;
                    break;

                case "steel":
                    hitpoints = IRON_HITPOINTS;
                    damageBlocked = IRON_DAMAGE_BLOCKED;
                    break;

                case "platinum":
                    hitpoints = PLATINUM_HITPOINTS;
                    damageBlocked = PLATINUM_DAMAGE_BLOCKED;
                    break;

                default:
                    name = "cloth";
                    hitpoints = 0;
                    damageBlocked = 0;
                    break;
            }
            return armor;
        }

        public int damageBlocked()
        {
            return damageBlocked;
        }

        public int hitpoints()
        {
            return hitpoints;
        }

        public String name()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void useArmor()
        {
            hitpoints--;
        }


        public void repairArmor(int hitpointsToRepair)
        {
            hitpoints = hitpoints + hitpointsToRepair;
        } 
    }
