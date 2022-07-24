import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DarkerAndShatterederLands
{
    public static final int ATTACK = 1;

    public static final String CONFIRMATION = "mmhmm yes";

    public static final long DELAY = 2000;

    public static final int EXIT = 5;

    public static final int MAXIMUM_GOLD_DROP = 30;

    public static final int PENALTY_FOR_RUNNING = 5;

    public static final Random RANDOM = new Random();

    public static final int RUN = 3;

    public static final int VISIT_STORE = 4;

    public static final Scanner SCANNER = new Scanner(System.in);

    public static final int USE_POTION = 2;

    public static void main(String[] argument)
    {
        Player player = new Player();
        Backpack Backpack = player.getBackpack();

        int armourDropChance = 10;
        int healthPotionDropChance = 50;
        int WeaponDropChance = 10;
        boolean running = true;
        boolean ranAway = false;

        // Game introduction
        System.out.println("\fWelcome to DSL.");

        System.out.print("Would you like to load your previous game? ");
        String loadGameState = SCANNER.nextLine();

        if (CONFIRMATION.contains(loadGameState))
        {
            System.out.print("\nWhat is your name? ");
            String name = SCANNER.nextLine();

            name = name.replaceAll("\\s+","");
            player.setName(name);

            try
            {
                SaveState.loadState(player);
            }
            catch (FileNotFoundException exception)
            {
                System.out.println("\nYour saved game was not found. Starting a new game.");
                delay();
            }
            catch (IOException exception)
            {
                System.out.println("Input from the keyboard could not be read. Please restart.");
            }
        }

        while (running)
        {
            Enemy villain = new Enemy();

            while (villain.health() > 0)
            {
                printStatistics(player, villain);

                startBattle();

                int choice;

                try
                {
                    choice = Integer.parseInt(SCANNER.nextLine());
                }
                catch (NumberFormatException exception)
                {
                    choice = RUN;
                }

                switch (choice)
                {
                    case ATTACK:
                        ranAway = false;
                        int playerAttack = player.attack();
                        int enemyAttack = villain.attack();

                        System.out.println("\nYou dealt " + playerAttack + " damage.");
                        System.out.println("You took " + enemyAttack + " damage.");

                        villain.takeDamage(playerAttack);
                        player.takeDamage(enemyAttack);

                        delay();
                        break;

                    case USE_POTION:
                        if (player.health() > player.FULL_HEALTH - player.POTION_HEALING)
                        {
                            System.out.println("\nI'm going to assume that was a mistake.");
                            DarkerAndShatterederLands.delay();
                            break;
                        }

                        player.usePotion();

                        System.out.println("\nYou drank the potion. Health restored by: " + Player.POTION_HEALING + " HP");
                        System.out.println("Current HP: " + player.health());

                        delay();
                        break;

                    case RUN:
                        if (player.getBackpack().getGold() > PENALTY_FOR_RUNNING)
                        {
                            System.out.println("\n" + PENALTY_FOR_RUNNING + " Gold were stolen by the " + villain.name());
                            Backpack.removeGold(PENALTY_FOR_RUNNING);
                        }
                        else
                        {
                            System.out.println("\nThe enemy did " + PENALTY_FOR_RUNNING + " damage before you managed to escape");
                            player.takeDamage(PENALTY_FOR_RUNNING);
                        }

                        System.out.println("\nYou ran away!");
                        delay();

                        villain.takeDamage(villain.health());

                        ranAway = true;
                        break;

                    case VISIT_STORE:
                        Store.printStore(player);
                        break;

                    case EXIT:
                        System.out.println("\fExiting...");
                        System.out.print("Would you like to save? ");

                        if (CONFIRMATION.contains(SCANNER.nextLine()))
                        {
                            SaveState.saveState(player);
                        }

                        running = false;
                        return;
                }

                if (player.health() <= 0)
                {
                    System.out.println("\nD'oh! You died, game over.");

                    System.out.print("Would you like to try again? ");
                    String continueGame = SCANNER.nextLine();

                    if (CONFIRMATION.contains(continueGame))
                    {
                        running = true;
                        player.reset();
                    }
                    else
                    {
                        System.out.println("\nProgram terminated.");

                        villain.takeDamage(villain.health());
                        running = false;
                        return;
                    }
                }
            }

            if (!ranAway)
            {
                player.increaseEnemiesKilled();

                Backpack.addGold(RANDOM.nextInt(MAXIMUM_GOLD_DROP));

                if (RANDOM.nextInt(100) < WeaponDropChance)
                {
                    if (player.hasWeapon())
                    {
                        System.out.println("\nThe " + villain.name() + " dropped Weapon, but you have one.");
                    } // end of if (player.hasWeapon())
                    else
                    {
                        player.addWeapon("");
                        System.out.println("\nThe " + villain.name() + " dropped " + player.getWeapon().name() + ".\nYour attack damage has increased by " + player.getWeapon().damageIncrease() + ".");
                    }
                    delay();
                }

                else if (RANDOM.nextInt(100) < armourDropChance)
                {
                    if (player.hasArmour())
                    {
                        System.out.println("\nThe " + villain.name() + " dropped some armour, but you have some.");
                    } // end of if (player.hasArmour())
                    else
                    {
                        player.addArmour("leather");
                        System.out.println("\nThe " + villain.name() + " dropped " + player.getArmour().name() + ".\nYour damage taken has decreased by " + player.getArmour().damageBlocked() + ".");
                    }
                    delay();
                }

                else if (RANDOM.nextInt(100) < healthPotionDropChance)
                {
                    player.addPotions(1);
                    System.out.println("\nThe " + villain.name() + " dropped a health potion.");
                    delay();
                }
            }
        }
    }

    public static void startBattle()
    {
        System.out.println("\n1. Attack.");
        System.out.println("2. Use potion.");
        System.out.println("3. Run!");
        System.out.println("4. Visit Store.");
        System.out.println("5. Exit Game.");

        System.out.print("\nChoice? ");
    }


    public static void printStatistics(Player player, Enemy villain )
    {
        // Statistics
        System.out.println("\f# A " + villain.name() + " appeared #");

        System.out.println("\n# You have " + player.health() + " HP #");
        System.out.println("# Enemy has " + villain.health() + " HP #");
        System.out.println("# Potions left: " + player.getPotions() + " #");
        System.out.println("# Backpack has " + player.getBackpack().getGold() + " Gold #");
        System.out.println("# Enemies killed: " + player.enemiesKilled() + " #");

        // Weapon
        if (player.hasWeapon())
        {
            System.out.println("\n# Weapon type: " + player.getWeapon().name() + " | hitpoints: " + player.getWeapon().hitpoints() + "  #");
        } 

        if (player.hasArmour())
        {
            System.out.println("\n# Armour type: " + player.getArmour().name() + " | Armour hitpoints: " + player.getArmour().hitpoints() + "  #");
        } 
    }
    
    public static void delay()
    {
        try
        {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException exception)
        {
            System.out.println("\fThe game experienced an error.");
            System.out.println("Save failed.");
            System.out.println("Please restart.");

            System.exit(0);
        } 
    } 
} 
