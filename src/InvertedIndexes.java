import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class InvertedIndexes {
    private final HashMap<String, HashSet<Integer>> invertedIndexes = new HashMap<>();


    void invertedIndexing(ArrayList<Contact> contacts) {
        for (int i = 0; i < contacts.size(); i++) {
            sliceAndMark(contacts.get(i).searchPrint(), i);
        }
    }

    void sliceAndMark(String entry, int row) {
        entry = entry.toLowerCase();
        String[] parts = entry.split("\\|");

        for (String part : parts) {
            if (!part.equals("[no data]") && !part.equals("[no number]")) {
                invertedIndexes.computeIfAbsent(part, k -> new HashSet<>());
                invertedIndexes.get(part).add(row);
            }
        }
    }

    void findWords(String words, ArrayList<Contact> contacts){
        words = words.toLowerCase();
        HashSet<Integer> results = new HashSet<>();
        String[] parts = words.split(" ");

        for(String word : parts){
            if(invertedIndexes.containsKey(word)){
                results.addAll(invertedIndexes.get(word));
            }
        }

        if (results.isEmpty()) {
            findPartialWords(words, contacts, results);
        } else {
            System.out.println("Found " + results.size() + " results: ");
            for (int i : results) {
                System.out.println(contacts.get(i));
            }
        }
    }

    void findPartialWords(String words, ArrayList<Contact> contacts, HashSet<Integer> results){
        for(int i = 0; i < contacts.size(); i++){
            String name = contacts.get(i).searchPrint().toLowerCase();
            if(name.contains(words)){
                results.add(i);
            }
        }
        if(results.isEmpty()){
            System.out.println("No results found");
        }else{
            System.out.println("Found " + results.size() + " results: ");
            for (int i : results) {
                System.out.println(contacts.get(i));
            }
        }
    }

}
