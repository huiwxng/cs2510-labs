import java.util.*;

public class StoreDepartment<E extends IShoppingItem> {
    private final List<E> inventory;

    public StoreDepartment(List<E> inventory) {
        this.inventory = inventory;
    }

    public void addItem(E item) {
        this.inventory.add(item);
    }

    public Comparator<E> getNameComparator() {
        return Comparator.comparing(IShoppingItem::getName);
    }


    public Comparator<E> getRatingComparator() {
        return Comparator.comparingDouble(IShoppingItem::getRating);
    }

}