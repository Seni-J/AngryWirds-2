package ch.cpnv.angrywirds.activities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.model.Button;
import ch.cpnv.angrywirds.model.Data.Language;
import ch.cpnv.angrywirds.providers.VocProvider;

public class Welcome extends Game implements InputProcessor {

    public static final float WORLD_WIDTH = 1600;
    public static final float WORLD_HEIGHT = 900;

    private SpriteBatch batch;
    private Texture background;
    private BitmapFont title;
    private VocProvider vocSource = VocProvider.getInstance();
    private ArrayList<Button> leftButtons;
    private ArrayList<Button> rightButtons;
    private Button startGame;
    private String firstLangISO;
    private String secondLangISO;
    private String firstLangDisplay;
    private String secondLangDisplay;

    private OrthographicCamera camera;

    public Welcome() {

        leftButtons = new ArrayList<Button>();
        rightButtons = new ArrayList<Button>();
        startGame = new Button(new Vector2(650,600), "Jouer");
        firstLangISO = new String();
        secondLangISO = new String();
        firstLangDisplay = "(choisir)";
        secondLangDisplay = "(choisir)";

        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("background.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        title= new BitmapFont();
        title.setColor(Color.ROYAL);
        title.getData().setScale(6);

        float pY = 450;
        for(Language l : vocSource.getLanguages()) {
            leftButtons.add(new Button(new Vector2(150, pY), l.getDisplayName()));
            rightButtons.add(new Button(new Vector2(650, pY), l.getDisplayName()));
            pY -= 100;
        }

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void create() {

    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render() {
        update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        title.draw(batch,"Exercice de " + firstLangDisplay + " en " + secondLangDisplay,100,WORLD_HEIGHT - 100);
        if(firstLangISO.isEmpty()) {
            for (Button bL : leftButtons) {
                bL.draw(batch);
            }
        }
        if(secondLangISO.isEmpty()) {
            for (Button bR : rightButtons) {
                bR.draw(batch);
            }
        }

        if(!firstLangISO.isEmpty() && !secondLangISO.isEmpty()){
            startGame.draw(batch);
        }
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 pt3 = camera.unproject(new Vector3(screenX, screenY, 0)); // Convert from screen coordinates to camera coordinate

        if(firstLangISO.isEmpty()) {
            for (Button b : leftButtons) {
                if (b.isTouched(new Vector2(pt3.x, pt3.y))) {
                    for (Language l : vocSource.getLanguages()) {
                        if (l.getDisplayName() == b.getText()) {
                            firstLangISO = l.getISO_639_1();
                            firstLangDisplay = l.getDisplayName();
                        }
                    }
                }
            }
        }
        if(secondLangISO.isEmpty()) {
            for (Button b : rightButtons) {
                if (b.isTouched(new Vector2(pt3.x, pt3.y))) {
                    for (Language l : vocSource.getLanguages()) {
                        if (l.getDisplayName() == b.getText()) {
                            secondLangISO = l.getISO_639_1();
                            secondLangDisplay = l.getDisplayName();
                        }
                    }
                }
            }
        }

        if(!firstLangISO.isEmpty() && !secondLangISO.isEmpty()) {
            if (startGame.isTouched(new Vector2(pt3.x, pt3.y))) {
                AngryWirds.pages.push(new Play(firstLangISO,secondLangISO));
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
