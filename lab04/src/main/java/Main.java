import java.util.List;

public class Main {
    public static boolean isInOrder(List<Coordinates> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i+1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void addInOrder(Coordinates element, List<Coordinates> list) {
        if (!isInOrder(list)) {
            throw new IllegalArgumentException("List is not in order");
        }

        for (int i = 0; i < list.size(); i++) {
            int compare = element.compareTo(list.get(i));

            if (compare < 0 || compare == 0) {
                list.add(i, element);
                return;
            }
        }

        list.add(element);
    }
}
