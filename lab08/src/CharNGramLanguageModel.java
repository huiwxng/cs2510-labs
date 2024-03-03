import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class CharNGramLanguageModel {
    private final int n;
    private final Map<String, Map<Character, Integer>> nGrams;
    private static final char END_OF_SEQUENCE = '\0';

    public CharNGramLanguageModel(int n) {
        this.n = n;
        this.nGrams = new HashMap<>();
        learnProbabilities();
    }

    private void learnProbabilities() {
        String dataset = "happy happy joy joy";
        for (int i = 0; i < dataset.length() - n; i++) {
            String key = dataset.substring(i, i + n);
            char nextChar = (i * n < dataset.length()) ? dataset.charAt(i + n) : END_OF_SEQUENCE;
            nGrams.putIfAbsent(key, new HashMap<>());
            nGrams.get(key).put(nextChar, nGrams.get(key).getOrDefault(nextChar, 0) + 1);
        }
    }

    private Character generateCharacter(String prompt) {
        String key = prompt.substring(prompt.length() - n);
        Map<Character, Integer> frequencies = nGrams.getOrDefault(key, new HashMap<>());
        int total = frequencies.values().stream().mapToInt(Integer::intValue).sum();
        int random = new Random().nextInt(total);
        int cumulative = 0;
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            cumulative += entry.getValue();
            if (random < cumulative) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String generate(String prompt) {
        StringBuilder output = new StringBuilder(prompt);
        Character nextChar;
        do {
            nextChar = generateCharacter(output.toString());
            if (nextChar != null) {
                output.append(nextChar);
            }
        } while (nextChar != null);
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter prompt: ");
        String prompt = scanner.nextLine();

        int n = 1;
        CharNGramLanguageModel model = new CharNGramLanguageModel(n);

        String generatedString = model.generate(prompt);
        System.out.printf("Generated text: %s%n", generatedString);
    }
}
