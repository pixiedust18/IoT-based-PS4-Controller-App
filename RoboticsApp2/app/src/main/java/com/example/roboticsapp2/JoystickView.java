package com.example.roboticsapp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.util.Log;


public class JoystickView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private float OX;
    private float OY;
    private float baseRadius;
    private float hatRadius;
    private JoystickListener joystickCallback;


    private void setupDimensions()
    {
        OX = getWidth() / 2;
        OY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / (float)3.5;
        hatRadius = Math.min(getWidth(), getHeight()) / (float)5;
    }

    public JoystickView(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener)
            joystickCallback = (JoystickListener) context;
    }

    public JoystickView(Context context, AttributeSet attributes, int style)
    {
        super(context, attributes, style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener)
            joystickCallback = (JoystickListener) context;
    }

    public JoystickView(Context context, AttributeSet attributes)
    {
        super(context, attributes);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener)
            joystickCallback = (JoystickListener) context;
    }

    private void drawJoy(float newX, float newY)
    {
        if(getHolder().getSurface().isValid())
        {
            Canvas canvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            colors.setARGB(255, 100, 100, 100);
            canvas.drawCircle(OX, OY, baseRadius, colors);


            colors.setARGB(255, 0, 0, 60); //top
            canvas.drawCircle(newX, newY, hatRadius, colors);
            joystickCallback.onJoystickMoved(newX, newY, OX, OY, getId(), baseRadius);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        setupDimensions();
        drawJoy(OX, OY);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public boolean onTouch(View v, MotionEvent e)
    {
        if(v.equals(this))
        {
            if(e.getAction() != e.ACTION_UP)
            {
                float displacement = (float) Math.sqrt((Math.pow(e.getX() - OX, 2)) + Math.pow(e.getY() - OY, 2));
                if(displacement < baseRadius)
                {
                    drawJoy(e.getX(), e.getY());
                    //Log.i( "XY", ""+e.getX()+" "+e.getY() );
                    //joystickCallback.onJoystickMoved((e.getX() - OX)/baseRadius, (e.getY() - OY)/baseRadius, OX, OY, getId());
                }
                else
                {
                    float ratio = baseRadius / displacement;
                    float constrainedX = OX + (e.getX() - OX) * ratio;
                    float constrainedY = OY + (e.getY() - OY) * ratio;
                    drawJoy(constrainedX, constrainedY);
                    //Log.i( "XY", ""+e.getX()+" "+e.getY() );

                    //joystickCallback.onJoystickMoved((constrainedX-OX)/baseRadius, (constrainedY-OY)/baseRadius, OX, OY, getId());
                }
            }
            else
                drawJoy(OX, OY);
            //joystickCallback.onJoystickMoved(0,0, 0, 0 , getId());
        }
        return true;
    }

    public interface JoystickListener
    {
        void onJoystickMoved(float x2, float y2, float x1, float y1, int id, float baseRadius);
    }
}