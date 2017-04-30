package interview.li;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 写一个RetainBestCache的数据结构，给定了getRank函可以直接用来得到当前cache的rank，然后有capacity的限制，
 * 当达到capacity的时候要拿出当前所存lowest rank的cache，跟新的cache比较，保留rank较高的那个
 * 实现get跟set，然后getRank是给定了可以直接用。在没有达到capacity的情况下只需要insert到minheap就好，
 * 如果达到了capacity就拿出lowest rank cache跟新来的cache比较，保留较大的，所以会是O(logn)。
 * 还需要一个hash map，每次insert到minheap的时候也要存入hash map，这样可以做到get的时候是O(1)。

 */
public class RetainBestCache {

    /*public class RankCache {


        // constructor
        RankCache(DataSource ds, int capacity) {
            // implement
        }

        public T get(K key) {
            // implement. If key in cache, return the object. Otherwise get from data source and put in cache
            // if cache is full (capacity reached), evict the one with lowest rank.
        }
    }
    interface Rankable { long getRank(); }
    interface DataSource { T get(K key); }
*/
    public class RankCache {

        Map<Integer, Rankable> cache = new HashMap<>(); // so we can get with O(1)
        PriorityQueue<Rankable> minHeap;
        DataSource ds;
        int capacity;

        // constructor
        RankCache(DataSource ds, int capacity) {
            // implement
            this.ds = ds;
            this.capacity = capacity;
            minHeap = new PriorityQueue<>(capacity, new Comparator<Rankable>() {
                @Override
                public int compare(Rankable o1, Rankable o2) {
                    return Long.compare(o1.getRank(), o2.getRank());
                }
            });
        }

        public Rankable get(int key) {
            // implement. If key in cache, return the object. Otherwise get from data source and put in cache
            // if cache is full (capacity reached), evict the one with lowest rank.
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            Rankable rankable = ds.get(key);
            if (cache.size() < capacity) {
                cache.put(key, rankable);
                minHeap.offer(rankable);
            } else {
                if (minHeap.peek().getRank() < rankable.getRank()) {

                    // how to remove minHeap.peek from cache? will need to iterate through cache map
                    // or if we have a getKey method in Rankable interface, we could remove by its key


                    minHeap.poll();
                    minHeap.offer(rankable);
                }
                // else, do not add rankable
            }

            return rankable;
        }
    }

    interface Rankable {
        long getRank();
    }

    interface DataSource {
        Rankable get(int key);
    }


}
