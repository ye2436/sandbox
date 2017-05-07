package interview.am;

import java.util.*;

/**
 * 要求找和movie相似的电影中排名前k个的电影（不包括当前movie）。就是找movie的所有neighbor中排名前k的电影。
 */
public class MovieNetwork {

    public List<Movie> getNearest(Movie movie, int k){
        Queue<Movie> queue = new LinkedList<>();
        Set<Movie> visited = new HashSet<>();
        PriorityQueue<Movie> minHeap = new PriorityQueue<>(k, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Float.compare(o1.rating, o2.rating);
            }
        });

        queue.offer(movie);
        while (!queue.isEmpty()) {
            Movie current = queue.poll();
            if (current != movie) { // exclude itself ??
                minHeap.offer(current);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }

            visited.add(current);
            for (Movie neighbor : movie.neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.offer(movie);
                }
            }
        }

        List<Movie> list = new ArrayList<>();
        for (Movie m : minHeap) {
            list.add(m);
        }
        return list;
    }

    class Movie
    {
        int movieId;
        float rating;
        List<Movie> neighbors;
    }

}
