package com.example.imageredctor;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortByValue {
        HashMap<Double, Integer> map ;
        public SortByValue(HashMap map){
            this.map = map;
        }
        //method to add elements in the HashMap

        //sort elements by values
        public Map sortByValue(boolean order)
        {
//convert HashMap into List
            List<Entry<Double, Integer>> list = new LinkedList<Entry<Double, Integer>>(map.entrySet());
//sorting the list elements
            Collections.sort(list, new Comparator<Entry<Double, Integer>>()
            {
                public int compare(Entry<Double, Integer> o1, Entry<Double, Integer> o2)
                {
                    if (order)
                    {
//compare two object and return an integer
                        return o2.getValue().compareTo(o1.getValue());}
                    else
                    {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                }
            });
//prints the sorted HashMap
            Map<Double, Integer> sortedMap = new LinkedHashMap<Double, Integer>();
            for (Entry<Double, Integer> entry : list)
            {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            return sortedMap;
        }
        //method for printing the elements
        public void printMap(Map<Double, Integer> map)
        {
            System.out.println("Company\t Price ");
            for (Entry<Double, Integer> entry : map.entrySet())
            {
                System.out.println(entry.getKey() +"\t"+entry.getValue());
            }
            System.out.println("\n");
        }
}