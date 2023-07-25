import java.io.File;
import java.nio.file.Files;
import java.util.*;

/**
 * AnagramFinder is a utility class that loads a dictionary of words from the given directory,
 * finds anagrams for a user-provided word, and returns the longest anagram (if found).
 */
public class AnagramFinder {

    // A HashMap to store the sorted characters of words as keys and their anagrams as values.
    private static Map<String, List<String>> dictionary = new HashMap<>();

    /**
     * The main method of AnagramFinder.
     * It loads the dictionary from the specified directory and then repeatedly prompts the user to
     * enter a word to find its longest anagram.
     *
     * @param args Command-line arguments. The first argument should be the path to the dictionary directory.
     * @throws Exception If an error occurs while reading the dictionary files.
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Please provide the directory path as an argument");
            System.exit(0);
        }

        // Load the dictionary from the specified directory
        File folder = new File(args[0]);
        loadDictionary(folder);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("matchFor> ");
                String word = scanner.nextLine().trim();
                if (word.isEmpty()) {
                    break;
                }
                // Find the longest anagram for the given word and display the result.
                System.out.println("The longest anagram is... '" + searchAnagram(word) + "'");
            }
        }
    }

    /**
     * Load the dictionary from a given directory by reading all text files and organizing the words
     * into anagrams in the dictionary map.
     *
     * @param folder The directory containing text files with words.
     * @throws Exception If an error occurs while reading the dictionary files.
     */
    static void loadDictionary(File folder) throws Exception {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            // Read each line (word) from the file and store it in the dictionary map.
            Files.lines(file.toPath())
                    .forEach(word -> dictionary.computeIfAbsent(sortChars(word), k -> new ArrayList<>()).add(word));
        }
    }

    /**
     * Find the longest anagram for a given word.
     *
     * @param word The word for which the longest anagram is to be found.
     * @return The longest anagram of the given word, or "not found here" if no anagram is found.
     */
    public static String searchAnagram(String word) {
        String key = sortChars(word);
        return dictionary.getOrDefault(key, Collections.singletonList("not found here")).stream()
                .filter(w -> !w.equalsIgnoreCase(word))
                .max(Comparator.comparing(String::length))
                .orElse("not found here");
    }

    /**
     * Sort the characters of a word in alphabetical order.
     *
     * @param word The input word.
     * @return The input word with its characters sorted in alphabetical order.
     */
    private static String sortChars(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
