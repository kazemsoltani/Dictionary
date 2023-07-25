import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for AnagramFinder. It contains test cases for various scenarios.
 */
class AnagramFinderTest {

    /**
     * Set up the test by loading the dictionary before running the test cases.
     *
     * @throws Exception If an error occurs while loading the dictionary.
     */
    @BeforeAll
    static void setUp() throws Exception {
        AnagramFinder.loadDictionary(new File("./dictionary"));
    }

    /**
     * Test finding anagrams in dictionary1.
     */
    @Test
    void testSearchAnagramInDictionary1() {
        assertEquals("silent", AnagramFinder.searchAnagram("listen"));
        assertEquals("articles", AnagramFinder.searchAnagram("recitals"));
        assertEquals("not found here", AnagramFinder.searchAnagram("we"));
    }

    /**
     * Test finding anagrams in dictionary2.
     */
    @Test
    void testSearchAnagramInDictionary2() {
        assertEquals("apple", AnagramFinder.searchAnagram("leppa"));
        assertEquals("mango", AnagramFinder.searchAnagram("among"));
        assertEquals("not found here", AnagramFinder.searchAnagram("pear"));
    }

    /**
     * Test finding anagrams for an empty word.
     */
    @Test
    void testEmptyWord() {
        assertEquals("not found here", AnagramFinder.searchAnagram(""));
    }

    /**
     * Test finding anagrams for a word with no anagrams in the dictionary.
     */
    @Test
    void testWordWithNoAnagrams() {
        assertEquals("not found here", AnagramFinder.searchAnagram("programming"));
    }
}
