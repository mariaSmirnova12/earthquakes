package markovText;

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{

    int N; 
    HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int N) {
        super();
        this.N = N;
        map = new HashMap<String, ArrayList<String>>();
    }

    public EfficientMarkovModel(int N, int seed) {
        myRandom = new Random(seed);
        this.N = N;
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public void buildMap(){
        String key = "";
        for(int k=0; k < myText.length()-N; k++){
            key = myText.substring(k, k+N);
            String value = myText.substring(k+N, k+N+1);
            //System.out.println("value: " + value);
            if(!map.containsKey(key)) {
                ArrayList<String> follows = new ArrayList<String>();
                follows.add(value);
                map.put(key, follows);
            }
            else{
                ArrayList<String> follows = map.get(key);
                follows.add(value);
                map.put(key, follows);
            }   
        }
    }
    
     public void printMap(){
         System.out.println("size is: "+map.size());
         int max = 0;
        for(String key: map.keySet()){
            if(map.get(key).size() > max){
                max= map.get(key).size();
            }
            System.out.println("key= "+key+" values: "+map.get(key));
        }
        System.out.println("max is "+ max);
    }

    public ArrayList<String> getFollows( String key){
        ArrayList<String> follows = map.get(key);
        return follows;
    }

    public HashMap<String, ArrayList<String>> getMap(){
        return map;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        buildMap();
        printMap();
        StringBuilder sb = new StringBuilder();
        int indexStart = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(indexStart, indexStart+N);
        sb.append(key);
        for(int k=0; k < numChars-N; k++){
            System.out.println("key: "+ key);
            ArrayList<String> follows = getFollows(key);
            //System.out.println("follows.size(): "+ follows.size());
            if (follows == null || follows.size() == 0){
                break;
            }
            int index = myRandom.nextInt(follows.size());
            //System.out.println("index: "+ index);
            String next = follows.get(index);
            sb.append(next);
            key =  key.substring(1) + next;
        }
        return sb.toString();
    }

}
