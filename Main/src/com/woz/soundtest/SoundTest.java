package com.woz.soundtest;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created: Woz
 * Date: 10/30/12 *
 */

public class SoundTest implements ApplicationListener
{
    Texture dropImage;
    Texture bucketImage;

    Sound dropSound;
    Music rainMusic;

    OrthographicCamera camera;
    SpriteBatch batch;

    Rectangle box;
    long lastDropTime;

    @Override
    public void create()
    {
        /*
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("SoundTest.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        rainMusic.setLooping(true);
        rainMusic.play();
        */

        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        batch = new SpriteBatch();

        box = new Rectangle();
        box.x = 800 / 2 - 48 / 2;
        box.y = 20;
        box.width = 48;
        box.height = 48;
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        camera.update();

        /*
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, box.x, box.y);
        batch.end();

        */

        if (Gdx.input.isTouched())
        {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            box.x = touchPos.x - 48 / 2;
        }
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
    }
}
