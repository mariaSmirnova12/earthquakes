package quakes;

public class PhraseFilter implements Filter {
    String whereToLook;
    String str;

    public PhraseFilter(String whereToLook, String str){
        this.whereToLook = whereToLook;
        this.str = str;
    }
    
    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        switch(whereToLook){
           case("start"): {
               return (title.substring(0,str.length()).equals(str));
           }
           case("end"): {
               return (title.substring(title.length()-str.length()).equals(str));
           }
           default:  {
               if (title.indexOf(str)!=(-1)) {
                   return true;
               }
               return false;
          }
       }
    }

    public String getName(){
        return "PhraseFilter";
    }
}
