import java.util.*;

public class Main {
    public static void main(String args[]) {
        Car car0 = new Car("Audi R8");
        Car car1 = new Car("Porsche 911");
        car0.addRating(4);
        car1.addRating(5);
        List<IShoppingItem> itemList = new ArrayList<>();
        itemList.add(car0);
        itemList.add(car1);
        StoreDepartment<IShoppingItem> inventory = new StoreDepartment<>(itemList);
        itemList.sort(inventory.getNameComparator());
        for (IShoppingItem item : itemList) {
            System.out.println(item.getName());
        }
    }

}