package ch.cpnv.angrywirds.model.Data;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class SemanticWord {
    private HashMap<String,String> values;

    public SemanticWord(){
        values = new HashMap<String, String>();
    }

    public void addTranslation(String language, String value){
        values.put(language,value);
    }

    public String getValue(String language){
        for(String key : values.keySet()){
            if(key == language){
                return values.get(key);
            }
        }
        return "Not Found";
    }

}
