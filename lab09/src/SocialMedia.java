import java.util.*;

public class SocialMedia {
    private Set<User> users;
    private Random random;

    public SocialMedia() {
        this.users = new HashSet<>();
        random = new Random();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void befriend(User friend1, User friend2) {
        friend1.addFriend(friend2);
    }

    public boolean areConnected(User friend1, User friend2) {
        if (friend1 == null || friend2 == null || friend1.equals(friend2)) {
            return false;
        }

        Set<User> visited = new HashSet<>();
        return search(friend1, friend2, visited);
    }

    private boolean search(User current, User target, Set<User> visited) {
        if (current == target) {
            return true;
        }
        visited.add(current);
        for (User friend : current.getFriends()) {
            if (!visited.contains(friend) && search(friend, target, visited)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        if (users.isEmpty()) {
            return true;
        }
        User randomUser = findRandomUser();
        Set<User> visited = new HashSet<>();
        backtrack(randomUser, visited);

        return visited.size() == users.size();
    }

    private void backtrack(User current, Set<User> visited) {
        visited.add(current);
        for (User friend : current.getFriends()) {
            if (!visited.contains(friend)) {
                backtrack(friend, visited);
            }
        }
    }

    private User findRandomUser() {
        if (users.isEmpty()) {
            return null;
        }
        List<User> userList = new ArrayList<>(users);
        int randomIndex = random.nextInt(userList.size());
        return userList.get(randomIndex);
    }
}
