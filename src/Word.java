public class Word {
    private final String CORRECT = "@";
    private final String WRONG = "X";
    private final String FOUND = "O";

    private final String word;

    /**
     * Constructs a new Word object and initializes the word instance variable to the word
     * @param word is a word
     */
    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
    /**
     * Returns a string showing the status of each letter
     * X means that the letter is not in the word.
     * O means that the letter is in the word but not in the right position.
     * @ means that the character is in the word and in the right place.
     * @param guess word to compare with
     * @return string comprised of X, O, and @
     */
    public String checkWord(String guess) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < guess.length(); i++) {
            String guessChar = guess.substring(i, i+1);
            result.append(findCharacter(guessChar, i));
        }
        return result.toString();
    }

    private String findCharacter(String character, int expectedIndex) {
        for (int i = 0; i < word.length(); i++) {
            String wordCharacter = word.substring(i, i+1);
            System.out.println(wordCharacter);
            if (wordCharacter.equals(character) && i == expectedIndex) {
                return CORRECT;
            } else if (wordCharacter.equals(character)) {
                return FOUND;
            }
        }
        return WRONG;
    }
}
