import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private Set<User> friends;

    public User(String name, Set<User> friends) {
        this.name = name;
        this.friends = new HashSet<>();
        this.friends.addAll(friends);
    }

    protected void addFriend(User friend) {
        this.friends.add(friend);
        friend.friends.add(this);
    }

    public Set<User> getFriends() {
        Set<User> copy = new HashSet<>();
        for (User friend : friends) {
            copy.add(new User(friend.toString(), new HashSet<>(friend.friends)));
        }
        return copy;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof User otherUser) {
            return this.toString().equals(otherUser.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
