import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveState
{
    public static final Scanner scanner = new Scanner(System.in);

    public static void saveState(Player player)
    {
        if (player.getName().equals(""))
        {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            name = name.replaceAll("\\s+","");
            player.setName(name);
        }

        try
        {
            File userData = new File("users/" + player.getName() + ".txt");

            userData.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(userData);

            String encrypted = Encrypt.encrypt(player.getData());

            writer.println(encrypted);

            writer.close();

            System.out.println("Success. Exit and go play outside :-)");
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Data was not saved.");
        }
    }

    public static void loadState(Player player) throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("users/" + player.getName() + ".txt"));

        String[] data = Encrypt.decrypt(reader.readLine()).split(" ");

        reader.close();

        /* Extract the data into local variables from the string array */
        String name = data[0];
        boolean hasSword = Boolean.parseBoolean(data[1]);
        boolean hasArmor = Boolean.parseBoolean(data[2]);
        int enemiesKilled = Integer.parseInt(data[3]);
        int health = Integer.parseInt(data[4]);
        int numberOfPotions = Integer.parseInt(data[5]);
        int coins = Integer.parseInt(data[6]);

        player.setName(name);

        if (hasSword) player.addWeapon("wood");

        if (hasArmor) player.addArmor("leather");

        player.setEnemiesKilled(enemiesKilled);

        player.setHealth(health);

        player.setNumberOfPotions(numberOfPotions);

        player.getBackpack().setGold(coins);
    }

}
