package markovText;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(String word: myWords){
        System.out.print(" "+word);
        }
        return ret.trim();
    }

    public int hashCode() {
        WordGram wg = new WordGram(myWords, 0, myWords.length);
        String wgString = wg.toString();
        int hc = wgString.hashCode();
        return hc;
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
            return false;
        }
        else{
             for(int j = 0; j < this.length(); j++){
                 if(!myWords[j].equals(other.wordAt(j))){
                     return false;
                    }
             }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        
        String[] myWordsShift = new String[myWords.length];
        for(int j = 0; j < myWords.length; j++){

             if(j == myWords.length - 1){
                 myWordsShift[j] = word;
             }
             else{
                 myWordsShift[j] = myWords[j+1];
                }
        }
        WordGram out = new WordGram(myWordsShift, 0, myWords.length);
        return out;
    }

}