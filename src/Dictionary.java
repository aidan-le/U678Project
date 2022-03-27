import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Dictionary {
    private final static ArrayList<String> dictionary = new ArrayList<>();

    /**
     * Constructs a Dictionary object
     */
    public Dictionary() {
        loadWords();
        insertionSort();
    }

    /**
     * Returns a five-letter English word.
     * @return
     */
    public String getWord() {
        int index = (int)(Math.random() * dictionary.size());
        return dictionary.get(index);
    }

    /**
     * Checks the validity of a word in the game Wordle
     * @param word The word to check
     * @return Returns whether the word is a part of the English language and five letters long.
     */
    public boolean isValid(String word) {
        int left = 0;
        int right = dictionary.size() - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (word.compareTo(dictionary.get(middle)) < 0) {
                right = middle - 1;
            } else if (word.compareTo(dictionary.get(middle)) > 0) {
                left = middle + 1;
            } else {
                return word.length() == 5;
            }
        }

        return false;
    }

    private static void loadWords() {
        try {
            Scanner input = new Scanner(new File("src\\dictionary.txt"));
            while (input.hasNext()) {
                String word = input.next();
                dictionary.add(word);
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertionSort()
    {
        for (int j = 1; j < dictionary.size(); j++)
        {
            String temp = dictionary.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(dictionary.get(possibleIndex - 1)) < 0)
            {
                dictionary.set(possibleIndex, dictionary.get(possibleIndex - 1));
                possibleIndex--;
            }
            dictionary.set(possibleIndex, temp);
        }
    }
}
