import java.util.*;

public class FundiesStringBuilder {
    private List<Character> characters;
    private List<List<Character>> undoList;

    public FundiesStringBuilder() {
        this.characters = new ArrayList<>();
        this.undoList = new ArrayList<>();
    }

    public FundiesStringBuilder(String text) {
        this();
        for (char c : text.toCharArray()) {
            this.characters.add(c);
        }
    }

    public void append(String text) {
        undoList.add(characters);
        for (char c : text.toCharArray()) {
            characters.add(c);
        }
    }

    public void insert(int index, String text) {
        undoList.add(characters);
        for (int i = 0; i < text.length(); i++) {
            characters.add(index + i, text.charAt(i));
        }
    }

    public void reverse() {
        undoList.add(characters);
        List<Character> temp = new ArrayList<>();
        for (int i = characters.size() - 1; i > 0; i--) {
            temp.add(characters.get(i));
        }
        characters = temp;
    }

    public void undo() {
        if (!undoList.isEmpty()) {
            characters = undoList.removeLast();
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (char c : characters) {
            str += c;
        }
        return str;
    }
}