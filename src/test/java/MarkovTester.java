import markovText.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class MarkovTester {

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    public String readFromFile(String name){
        FileInputStream stream = null;
        StringBuilder str = new StringBuilder();
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File fileName = new File(classLoader.getResource(name).getFile());
            stream = new FileInputStream(fileName);
            BufferedReader reader = null;
            String nextLine;
            reader = new BufferedReader(new InputStreamReader(stream));
            while ((nextLine = reader.readLine()) != null) {
                str.append(nextLine);
            }
        }
        catch (IOException e) {
            System.err.println("Problem looking for dictionary file: " + name);
            e.printStackTrace();
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str.toString();
    }

    @Test
    public void testGetFollowsHashmap(){
       String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        int seed = 42;
        EfficientMarkovModel mz = new EfficientMarkovModel(2, 42);
        mz.setTraining(st);
        mz.buildMap();
        HashMap<String, ArrayList<String>> res = mz.getMap();
        assertEquals("hashmap size ", res.size(), 24);
        List<String> keysActual = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry : res.entrySet()) {
            if(entry.getValue().size() == 3){
                System.out.println("Key: " + entry.getKey());
                keysActual.add(entry.getKey());
            }
           // System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue().size());
        }
        assertEquals("hashmap size ", res.size(), 24);
        List<String> keys = Arrays.asList( "hi", "-t", "is", "th", "s-");
        assertEquals(keys, keysActual);
    }

    @Test
    public void testGetFollowsWithFileConfucius(){
        String st = readFromFile("dataMarkov/confucius.txt");
        st = st.replace('\n', ' ');
        EfficientMarkovModel mz = new EfficientMarkovModel(1);
        mz.setTraining(st);
        mz.buildMap();
        ArrayList<String> tt = mz.getFollows("t");
        System.out.println("size - "+tt.size());
        assertEquals("map size ", 11548, tt.size());
        String str = mz.getRandomText(100);
        printOut(str);
    }

    @Test
    public void testGetFollowsWithFileMelville(){
        String st = readFromFile("dataMarkov/melville.txt");
        st = st.replace('\n', ' ');
        EfficientMarkovModel mz = new EfficientMarkovModel(1);
       mz.setTraining(st);
        mz.buildMap();
        ArrayList<String> tt = mz.getFollows("o");
        System.out.println("size - "+tt.size());
        assertEquals("map size ", 4694, tt.size());
    }

}
