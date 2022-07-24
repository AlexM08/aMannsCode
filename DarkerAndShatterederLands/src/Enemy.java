import java.util.Random;

    public class Enemy
    {
        public static final String[] ENEMY_NAMES = { "Zombae", "Skelethon", "Jelly Cube", "Behelder", "Swearwolf", "Vampire" };

        public static final int MAXIMUM_ATTACK_DAMAGE = 20;

        public static final int MAXIMUM_HEALTH = 75;

        public static final int MINIMUM_HEALTH = 10;

        public static final Random RANDOM = new Random();

        private int health;
        private String name;

        public Enemy()
        {
            name = ENEMY_NAMES[RANDOM.nextInt(ENEMY_NAMES.length)];

            health = RANDOM.nextInt(MAXIMUM_HEALTH);
        }

        public int attack()
        {
            return RANDOM.nextInt(MAXIMUM_ATTACK_DAMAGE);
        } // end of method attack()

        public void takeDamage(int damage)
        {
            health = health - damage;
        } // end of method damageDealt(int damage)

        public String name()
        {
            return name;
        } // end of method name()

        public int health()
        {
            return health;
        } // end of method health()
    }

