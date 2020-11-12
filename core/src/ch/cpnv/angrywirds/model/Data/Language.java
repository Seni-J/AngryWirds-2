package ch.cpnv.angrywirds.model.Data;

public class Language {
    String displayName;
    String ISO_639_1;

    public Language(String ISO, String name){
        this.displayName = name;
        this.ISO_639_1 = ISO;
    }

    public String getDisplayName(){ return displayName; }

    public String getISO_639_1(){
        return ISO_639_1;
    }
}
