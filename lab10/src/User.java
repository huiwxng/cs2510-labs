import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private Set<User> friends;
    private User parent;
    private int rank;

    public User(String name) {
        this.name = name;
        this.friends = new HashSet<>();
    }

    public User(String name, Set<User> friends) {
        this(name);
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

    public User find() {
        if (this.parent != this) {
            this.parent = this.parent.find();
        }
        return this.parent;
    }

    public void union(User other) {
        User root1 = this.find();
        User root2 = other.find();

        if (root1.equals(root2)) {
            return;
        }

        if (root1.rank > root2.rank) {
            root2.parent = root1;
        } else {
            root1.parent = root2;
            if (root1.rank == root2.rank) {
                root2.rank++;
            }
        }
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
