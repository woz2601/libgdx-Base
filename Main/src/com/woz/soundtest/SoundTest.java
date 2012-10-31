package com.woz.soundtest;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;
import java.util.Iterator;

/**
 * Created: Woz
 * Date: 10/30/12 *
 */

public class SoundTest implements ApplicationListener
{
    //Texture dropImage;
    //Texture bucketImage;
    // Sound dropSound;

    Music intro;
    
    Music fluteLoop;
    Music bassLoop;
    Music bassLoop2;
    Music stabsLoop;
    Music padLoop;

    float bass2Volume;
    float volume;

    BitmapFont fpsFont;
    String fps;

    OrthographicCamera camera;
    SpriteBatch batch;

    Rectangle box;
    long lastFrameTime;
    
    int fpsSmoother;

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

        intro = Gdx.audio.newMusic(Gdx.files.internal("Intro.wav"));

        fluteLoop = Gdx.audio.newMusic(Gdx.files.internal("FluteLoop.wav"));
        bassLoop = Gdx.audio.newMusic(Gdx.files.internal("BassLoop.wav"));
        bassLoop2 = Gdx.audio.newMusic(Gdx.files.internal("BassLoop2.wav"));
        stabsLoop = Gdx.audio.newMusic(Gdx.files.internal("StabsLoop.wav"));
        padLoop = Gdx.audio.newMusic(Gdx.files.internal("PadLoop.wav"));

        intro.play();

        fluteLoop.setLooping(true);
        fluteLoop.setVolume(0);
        fluteLoop.play();

        bass2Volume = 0;
        bassLoop2.setLooping(true);
        bassLoop2.setVolume(bass2Volume);
        bassLoop2.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        batch = new SpriteBatch();

        box = new Rectangle();
        box.x = 800 / 2 - 48 / 2;
        box.y = 20;
        box.width = 48;
        box.height = 48;

        lastFrameTime = 0;
        fpsSmoother = 0;
        fpsFont = new BitmapFont();
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

        if (fpsSmoother == 10)
        {
            fps = (int)(1000000000.0 / (TimeUtils.nanoTime() - lastFrameTime)) + "";
            fpsSmoother = 0;
        }
        lastFrameTime = TimeUtils.nanoTime();
        fpsSmoother++;

        batch.begin();
        fpsFont.draw(batch, "FPS: " + fps, 20, 480 - 20);
        batch.end();

        if (Gdx.input.isTouched())
        {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            box.x = touchPos.x - 48 / 2;
        }

        if (!intro.isPlaying())
        {
            //fluteLoop.setLooping(true);
            //fluteLoop.play();

            fluteLoop.setVolume(1);

            if (bass2Volume < 1.0f)
            {
                bass2Volume += 0.2f;
            }
            bassLoop2.setVolume(bass2Volume);
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
        intro.dispose();
        fluteLoop.dispose();
        bassLoop.dispose();
        bassLoop2.dispose();
        stabsLoop.dispose();
        padLoop.dispose();

        batch.dispose();
    }
}
