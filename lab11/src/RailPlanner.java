import java.util.*;

public class RailPlanner {
    public static class City {
        private String name;
        private City parent;
        private int rank;

        public City(String name) {
            this.name = name;
            this.parent = this;
            this.rank = 0;
        }

        @Override
        public String toString() {
            return name;
        }

        public City find() {
            if (parent == this) {
                return this;
            }
            return parent.find();
        }

        public void union(City otherCity) {
            City root1 = this.find();
            City root2 = otherCity.find();

            if (root1 != root2) {
                if (root1.rank > root2.rank) {
                    root2.parent = root1;
                } else {
                    root1.parent = root2;
                    if (root1.rank == root2.rank) {
                        root2.rank++;
                    }
                }
            }
        }
    }

    public static class TrainTrack {
        private City city1;
        private City city2;
        private int cost;

        @Override
        public String toString() {
            return String.format("Track from %s to %s", city1.toString(), city2.toString());
        }
    }

    public static class TrackIterator implements Iterator<String> {
        private final List<String> tracks;
        private int index;

        public TrackIterator(List<String> tracks) {
            this.tracks = tracks;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < tracks.size();
        }

        @Override
        public String next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            index++;
            return tracks.get(index);
        }
    }

    public List<String> chooseTracks(Set<TrainTrack> distances) {
        List<String> mst = new ArrayList<>();
        PriorityQueue<TrainTrack> pq = new PriorityQueue<>(distances);

        while (!pq.isEmpty()) {
            TrainTrack track = pq.poll();
            City city1 = track.city1;
            City city2 = track.city2;

            if (city1.find() != city2.find()) {
                mst.add(track.toString());
                city1.union(city2);
            }
        }

        return mst;
    }

    public Iterator<String> getTrackIterator(Set<TrainTrack> distances) {
        List<String> mst = chooseTracks(distances);
        return new TrackIterator(mst);
    }
}
