import java.util.Scanner;
public class Wordle {
    private final int TURNS = 6;
    private final String SOLVED = "@@@@@";

    private final Scanner scanner;
    private final String[][] letterGrid;
    private final Dictionary dictionary;

    public Wordle() {
        scanner = new Scanner(System.in);
        letterGrid = new String[12][5];
        dictionary = new Dictionary();

        for (int i = 0; i < letterGrid.length; i++) {
            String[] row = letterGrid[i];
            for (int j = 0; j < row.length; j++) {
                letterGrid[i][j] = " ";
            }
        }
    }

    /**
     * Starts the game.
     */
    public void start() {
        Word word = new Word(dictionary.getWord());
        displayRules();

        for (int turn = 1; turn <= TURNS; turn++) {
            clearScreen();
            System.out.println("Turn "+turn+":");
            displayBoard();

            System.out.print("Your guess: ");
            String guess = scanner.nextLine().toLowerCase();
            while (!dictionary.isValid(guess)) {
                System.out.print("Valid guess please: ");
                guess = scanner.nextLine().toLowerCase();
            }
            String result = word.checkWord(guess);

            letterGrid[(turn - 1) * 2] = result.split("");
            letterGrid[(turn - 1) * 2 + 1] = guess.split("");

            if (result.equals(SOLVED)) {
                System.out.println("Congratulations for solving the WORDLE, you guessed the word in "+turn+" tries!");
                scanner.close();
                return;
            }
        }

        System.out.println("You lost, the word was "+word.getWord()+".");
        scanner.close();
    }

    private void clearScreen() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    private void displayBoard() {
        for (int r = 0; r < letterGrid.length; r++) {
            String[] row = letterGrid[r];
            for (String letter : row) {
                System.out.print(letter);
            }
            System.out.print(r % 2 == 0 ? " : " : "\n");
        }
    }

    private void displayRules() {
        String rules = "Guess the WORDLE in six tries.\n"+
                "Each guess must be a valid five-letter word. Hit the enter button to submit.\n"+
                "After each guess, the color of the tiles will change to show how close your guess was to the word.\n"+
                "\n"+
                "Examples:\n"+
                "WEARY\n"+
                "@XXXX\n"+
                "Here, the letter W is in the word and in the correct spot.\n"+
                "\n"+
                "PILLS\n"+
                "XOXXX\n"+
                "Here, the letter O is in the word but in the wrong spot.\n"+
                "\n"+
                "SMART\n"+
                "XXXXX\n"+
                "Here, none of the letters are in the word.\n"+
                "\n"+
                "Press enter to continue:";
        System.out.println(rules);
        scanner.nextLine();
    }
}
