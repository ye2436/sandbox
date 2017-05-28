package interview.li;

import java.util.Arrays;
import java.util.List;

/**
 * Suppose you have a long flowerbed in which some of the plots are planted and
 * some are not. However, flowers cannot be planted in adjacent plots -
 * they would compete for water and both would die. Given a flowerbed
 * (represented as an array containing booleans), return if a given number of
 * new flowers can be planted in it without violating the no-adjacent-flowers rule.
 */
public class FlowerBed {
    public boolean canPlaceFlowers(List<Boolean> flowerbed, int numberToPlace) {
        if(flowerbed == null || flowerbed.isEmpty()){
            //throw new IllegalArgumentException("bed is empty");
            return false;
        }

        if(numberToPlace == 0)
            return true;

        // ???
        if(flowerbed.size()==1){
            return !flowerbed.get(0) && numberToPlace<=1;
        }

        int counter = 0;
        for(int i=0; i< flowerbed.size(); i++){
            if(!flowerbed.get(i)){
                if((i==0 && !flowerbed.get(i+1))
                        || (i==flowerbed.size()-1 && !flowerbed.get(i-1))
                        || (!flowerbed.get(i+1) && !flowerbed.get(i-1)) ){
                    //place the flower
                    flowerbed.set(i, true);
                    counter++;
                    if(counter==numberToPlace)
                        return true;
                }
            }
        }

        return false;
    }

    public static boolean canPlaceFlowers2(List<Boolean> flowerbed, int numberToPlace) {
        if (flowerbed == null || flowerbed.isEmpty()) {
            // exception;
            return false;
        }

        if (numberToPlace == 0) return true;
        if (flowerbed.size() == 1) {
            return !flowerbed.get(0) && numberToPlace == 1;
        }

        int count = 0;
        for (int i=0; i<flowerbed.size(); i++) {
            if (flowerbed.get(i)) continue;
            if ((i == 0 || !flowerbed.get(i-1)) && (i == flowerbed.size()-1 || !flowerbed.get(i+1))) {
                flowerbed.set(i, true);
                count++;
                if (count == numberToPlace) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canPlaceFlowers2(Arrays.asList(true), 1));
    }
}
