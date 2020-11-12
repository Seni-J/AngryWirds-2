package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public final class Button extends TextualObject{

    private static final String PICNAME = "button.png";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;
    private static final int TEXT_OFFSET_X = 50; // to place the text inside the bubble
    private static final int TEXT_OFFSET_Y = 50;

    private BitmapFont font;


    public Button(Vector2 position, String text) {
        super(position, WIDTH, HEIGHT, PICNAME, text);
        font= new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
    }

    public void draw(Batch batch)
    {
        super.draw(batch);
        font.draw(batch, getText(), getX()+TEXT_OFFSET_X, getY()+TEXT_OFFSET_Y);
    }

    public boolean isTouched(Vector2 point){
        if(this.getBoundingRectangle().contains(point)){
            return true;
        }else{
            return false;
        }
    }
}
