package markovText;

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{

    private String[] myText;
    private Random myRandom;
    int myOrder;
    HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<WordGram, ArrayList<String>>();

    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public ArrayList<String> getFollows(WordGram key){
        ArrayList<String> follows = map.get(key);
        return follows;
    }

    public void printMap(){
        System.out.println("size is: "+map.size());
        int max = 0;
        for(WordGram key: map.keySet()){
            System.out.println(key.toString());
            System.out.println(" values: "+map.get(key));
        }
        System.out.println("max is "+ max);
    }
     public void printMaxElemInMap(){
         System.out.println("check max!");
         System.out.println("size is: "+map.size());
         int max = 0;
         WordGram maxEl = null;
        for(WordGram key: map.keySet()){
            if(map.get(key).size() > max){
                max = map.get(key).size();
                maxEl = key;
            }
        }
        System.out.println("max is ");
        System.out.println("max is "+ max);
    }
   
    public void buildMap(){
        System.out.println("start! ");
        WordGram key;
        String value = "";
        for(int k=0; k < myText.length-myOrder+1; k++){
            key = new WordGram(myText,k,myOrder);
            if( k + myOrder >= myText.length){
                value = "";
            }
            else{
                value = myText[k+myOrder];
            }
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
           
           key = key.shiftAdd(value);
        }
    }
     public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        String[] obj = new String[myOrder];

        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        for(int k=0; k < myOrder; k++){
            sb.append(myText[index + k]);
            sb.append(" ");
        }
        WordGram wg = new WordGram(myText,index,myOrder);

        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(wg);
            if (follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
}
