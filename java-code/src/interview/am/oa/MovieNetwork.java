package interview.am.oa;

import java.util.*;

/**
 * 要求找和movie相似的电影中排名前k个的电影（不包括当前movie）。就是找movie的所有neighbor中排名前k的电影。
 */
public class MovieNetwork {

    public List<Movie> getNearest(Movie movie, int k){
        Queue<Movie> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Movie> minHeap = new PriorityQueue<>(k, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Float.compare(o1.rating, o2.rating);
            }
        });

        queue.offer(movie);
        while (!queue.isEmpty()) {
            Movie current = queue.poll();
            if (current.movieId != movie.movieId) { // exclude itself ??
                minHeap.offer(current);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }

            visited.add(current.movieId);
            for (Movie neighbor : current.neighbors) {
                if (!visited.contains(neighbor.movieId)) {
                    queue.offer(neighbor);
                }
            }
        }

        List<Movie> list = new ArrayList<>();
        for (Movie m : minHeap) {
            list.add(m);
        }
        return list;
    }

    static class Movie
    {
        int movieId;
        float rating;
        List<Movie> neighbors;

        public Movie(int movieId, float rating) {
            this.movieId = movieId;
            this.rating = rating;
        }
    }

    public static void main(String[] args) {
        MovieNetwork movieNetwork = new MovieNetwork();
        Movie m1 = new Movie(1, 1.2f);
        Movie m2 = new Movie(2, 3.6f);
        Movie m3 = new Movie(3, 2.4f);
        Movie m4 = new Movie(4, 6.2f);
        m1.neighbors = Arrays.asList(m2, m3);
        m2.neighbors = Arrays.asList(m1, m4);
        m3.neighbors = Arrays.asList(m1, m2);
        m4.neighbors = Arrays.asList(m2, m3);
        List<Movie> list = movieNetwork.getNearest(m1, 2);
        for (Movie m: list) {

            System.out.println(m.movieId);
        }
    }
}
