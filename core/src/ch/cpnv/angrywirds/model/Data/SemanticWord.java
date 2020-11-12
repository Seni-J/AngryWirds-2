package ch.cpnv.angrywirds.model.Data;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;

import ch.cpnv.angrywirds.model.ObjectOutOfBoundsException;

public class SemanticWord {
    private HashMap<String,String> values;

    public SemanticWord(){
        values = new HashMap<String, String>();
    }

    public void addTranslation(String language, String value) throws TranslationExistsException {
        if(values.containsValue(value)) throw new TranslationExistsException("Translation already exist...");
        values.put(language, value);
    }

    public String getValue(String language){
        for(String key : values.keySet()){
            if(key == language){
                return values.get(key);
            }
        }
        return "Not Found";
    }

    //Throw Exception when Translation for a word exists
    public class TranslationExistsException extends Exception {
        public TranslationExistsException(String msg) {
            super(msg);
        }
    }

    //Throw Exception when Translation language does not exists
    public class TranslationDoesNotExistsException extends Exception {
        public TranslationDoesNotExistsException(String msg) {
            super(msg);
        }
    }
}
