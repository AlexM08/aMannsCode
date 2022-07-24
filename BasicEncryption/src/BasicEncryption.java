import java.util.*;

public class BasicEncryption {

    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> randomizedList;
    private char character;
    private String line;
    private char[] letters;

    BasicEncryption() {
        scanner = new Scanner(System.in);
        random = new Random();
        list = new ArrayList<>();
        randomizedList = new ArrayList<>();
        character = ' ';

        createKey();
        promptUser();

    }

    private void promptUser() {
        while (true) {
            System.out.println("\n----------------------------------------------------");
            System.out.println("What is your wish?");
            System.out.println("(C)reateKey, (G)etKey, (E)ncrypt, (D)ecrypt, (Q)uit\n");
            char input = Character.toUpperCase(scanner.nextLine().charAt(0));
            // Switches to handle user input
            switch (input) {
                case 'C':
                    createKey();
                    break;
                case 'G':
                    getKey();
                    break;
                case 'E':
                    encrypt();
                    break;
                case 'D':
                    decrypt();
                    break;
                case 'Q':
                    endProgram();
                    break;
                default:
                    System.out.println("Not a valid input.");
            }
        }
    }

    private void createKey() {
        character = ' ';
        list.clear();
        randomizedList.clear();

        // starting with ASCII 32, to 127
        for (int i = 32; i < 127; i++) {
            list.add(Character.valueOf(character));
            character++;
        }
        randomizedList = new ArrayList<>(list);
        Collections.shuffle(randomizedList);
        System.out.println("New key has been created! Huzzah!");
    }

    private void getKey() {
        System.out.println("Key: ");
        for (Character x : list) {
            System.out.print(x);
        }
        System.out.println("\n-------------------------------------");
        for (Character y : randomizedList) {
            System.out.print(y);
        }
    }

    private void encrypt() {
        System.out.println("Enter your secret message");
        String message = scanner.nextLine();
        letters = message.toCharArray();

        // Searches list for match, then returns the char from the randomizedList
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (letters[i] == list.get(j)) {
                    letters[i] = randomizedList.get(j);
                    break;
                }
            }
        }
        System.out.println("Secret message: ");
        for (char x : letters) {
            System.out.print(x);
        }
    }

    private void decrypt() {
        System.out.println("Enter a message to decrypt: ");
        String message = scanner.nextLine();
        letters = message.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < randomizedList.size(); j++) {
                if (letters[i] == randomizedList.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        System.out.println("Not so secret message: ");
        for (char x : letters) {
            System.out.print(x);
        }
    }

    private void endProgram() {
        System.out.println("Thanks for trying it out :-)");
        System.exit(0);
    }
}
