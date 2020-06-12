package com.example.roboticsapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements JoystickView.JoystickListener {

    byte[] prev= new byte[3];
    int joyno=-1;
    int axis_check=-1;
    char dir='N';

    void sendData(byte a, int ind)
    {
        if((prev[ind]!=a))
        {
            if((a!=0)&&((ind==0)||ind==2))
                prev[ind]+=a;
            else {
                if(a<0&&a!=-128)
                    prev[ind]=0;
                else
                    prev[ind] = a;
            }
            //DataTransfer numSender = new DataTransfer("192.186.29.31", 4000);
            //numSender.execute(prev);
            TextView textFinal= findViewById(R.id.textView2);
            textFinal.setText(" "+prev[0]+" "+prev[1]+" "+prev[2]);
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        JoystickView joystick = new JoystickView(this);
        //JoystickView joystick2 = new JoystickView(this);


        setContentView( R.layout.activity_main );

        ImageView imageView= (ImageView)findViewById(R.id.imageView); //up
        ImageView imageView2= (ImageView)findViewById(R.id.imageView2); //right
        ImageView imageView3= (ImageView)findViewById(R.id.imageView3); //left
        ImageView imageView4= (ImageView)findViewById(R.id.imageView4); //down
        ImageView imageView5= (ImageView)findViewById(R.id.imageView5); //triangle
        ImageView imageView6= (ImageView)findViewById(R.id.imageView6); //square
        ImageView imageView7= (ImageView)findViewById(R.id.imageView7); //circle
        ImageView imageView8= (ImageView)findViewById(R.id.imageView8); //cross
        ImageView imageView9= (ImageView)findViewById(R.id.imageView9);//circle
        ImageView imageView10= (ImageView)findViewById(R.id.imageView10); //cross
        //ImageView imageView11= (ImageView)findViewById(R.id.imageView11); //cross
        SurfaceView surface2= (SurfaceView)findViewById(R.id.SurfaceView2); //cross
        SurfaceView surface= (SurfaceView)findViewById( R.id.SurfaceView1 ) ;

        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {

                        switch (motionEvent.getActionMasked()) {

                            case MotionEvent.ACTION_DOWN:

                                byte x = 0x01;
                                sendData(x, 0);
                                TextView textView= findViewById(R.id.textView4);

                                textView.setText("Forward");
                                return true;

                            case MotionEvent.ACTION_UP:
                                byte y = (byte)-1;
                                sendData(y, 0);
                                TextView textView_= findViewById(R.id.textView4);

                                textView_.setText("Stop");
                                return true;
                        }
                        return false;
            }


                ;/*if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    byte x = 0x01;
                    sendData(x, 0);
                    TextView textView= findViewById(R.id.textView4);
                    textView.setText("Forward");
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    byte x = 0;
                    sendData(x, 0);
                    TextView textView= findViewById(R.id.textView4);
                    textView.setText("Stop");
                }*/

        });

        imageView2.setOnTouchListener(new View.OnTouchListener() { //right
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        byte x = 0x02;
                        sendData(x, 0);
                        TextView textView= findViewById(R.id.textView4);
                        textView.setText("Right");
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = (byte)-2;
                        sendData(y, 0);
                        TextView textView_= findViewById(R.id.textView4);

                        textView_.setText("Stop");
                        return true;
                }

                return false;
            }
        });
        imageView3.setOnTouchListener(new View.OnTouchListener() {//Backward
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        byte x = 0x04;
                        sendData(x, 0);
                        TextView textView= findViewById(R.id.textView4);
                        textView.setText("Backward");
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = (byte)-4;
                        sendData(y, 0);
                        TextView textView_= findViewById(R.id.textView4);

                        textView_.setText("Stop");
                        return true;
                }

                return false;
            }
        });
        imageView4.setOnTouchListener(new View.OnTouchListener() { //left
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        byte x = 0x08;
                        sendData(x, 0);
                        TextView textView= findViewById(R.id.textView4);
                        textView.setText("Left");
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = (byte)-8;
                        sendData(y, 0);
                        TextView textView_= findViewById(R.id.textView4);

                        textView_.setText("Stop");
                        return true;
                }
                return false;
            }
        });
        imageView5.setOnTouchListener(new View.OnTouchListener() { //triangle
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        byte x = 0x40;
                        sendData(x, 0);
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = (byte)-64;
                        sendData(y, 0);
                        return true;
                }
                return false;
            }
        });
        imageView6.setOnTouchListener(new View.OnTouchListener() { //square
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        byte x = 0x02;
                        sendData(x, 2);
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = 0;
                        sendData(y, 2);
                        return true;
                }

                return false;
            }
        });
        imageView7.setOnTouchListener(new View.OnTouchListener() { //circle
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:
                        int a=128;
                        byte x = (byte)0x80;
                        sendData( x, 0 );
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = 0;
                        sendData( y, 0 );
                        return true;
                }
                return false;
            }
        });
        imageView8.setOnTouchListener(new View.OnTouchListener() { //cross
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        byte x = (byte) 0x01;
                        sendData( x, 2 );
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = 0;
                        sendData( y, 2 );
                        return true;
                }

                return false;
            }
        });
        imageView9.setOnTouchListener(new View.OnTouchListener() { //cross
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        byte x = 16;
                        sendData(x, 0);
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = (byte) -16;
                        sendData(y, 0);
                        return true;
                }

                return false;
            }
        });
        imageView10.setOnTouchListener(new View.OnTouchListener() { //cross
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:

                        byte x = 0x20;
                        sendData(x, 0);
                        return true;

                    case MotionEvent.ACTION_UP:
                        byte y = (byte)-32;
                        sendData(y, 0);
                        return true;
                }
                return false;
            }
        });
        surface.setOnTouchListener(new View.OnTouchListener() { //cross
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   joyno=1;
                }
                return false;
            }
        });
        surface2.setOnTouchListener(new View.OnTouchListener() { //cross
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    joyno=0;
                }
                return false;
            }
        });
    }


   /* public class Transfer implements Runnable {

        Socket s;
        DataOutputStream dos;
        PrintWriter pw;
        String ipAdd="192.168.29.31";
        int ipPort=4000;
        String op="Lala\n";
        Handler handler= new Handler();
        @Override
        public void run() {
            try{

                s = new Socket(ipAdd, ipPort);

                pw = new PrintWriter(s.getOutputStream());
                while(true) {
                    pw.write( op );
                    pw.flush();
                    ToggleButton tb=(ToggleButton) findViewById(R.id.toggleButton) ;
                    if(!tb.isChecked())
                        break;
                }
                pw.close();
                s.close();

            }catch(IOException ioe) {

                ioe.printStackTrace();
            }
        }
    }*/


    public void onJoystickMoved(float newX, float newY, float OX, float OY, int id, float radius) {
        double angle = 0;
        float x_cor = 0, y_cor = 0;
        try {
            float hypotenuse = (float) Math.sqrt( Math.pow( newX - OX, 2 ) + Math.pow( newY - OY, 2 ) );
            float sin = (newY - OY) / hypotenuse; //sin = o/h
            //float cos = (newX - OX) / hypotenuse; //cos = a/h
            angle = Math.toDegrees( Math.asin( sin ) );
            //editText3.setText(angle+"");
        } catch (Exception e) {
            angle = -1;
            //editText3.setText("No movement");
        }
        if ((newY - OY) >= 0 && ((newX - OX) >= 0)) //1->4
        {
            angle = 360 - angle;
            x_cor = (newX - OX) / radius * 100;
            y_cor = -(newY - OY) / radius * 100;
        } else if ((newY - OY) <= 0 && ((newX - OX) >= 0)) //4->
        {
            angle = -angle;
            x_cor = (newX - OX) / radius * 100;
            y_cor = -(newY - OY) / radius * 100;
        } else if ((newY - OY) >= 0 && (newX - OX <= 0)) //2
        {
            x_cor = (newX - OX) / radius * 100;
            y_cor = -(newY - OY) / radius * 100;
            angle = 180 + angle;
        } else if ((newY - OY) <= 0 && ((newX - OX) <= 0)) //3
        {
            x_cor = (newX - OX) / radius * 100;
            y_cor = -(newY - OY) / radius * 100;
            angle = 180 + angle;
        }
        //TextView textView2 = (TextView)findViewById(R.id.textView);

        if(angle>45&&angle<135)
        {
            //textView2.setText("Up "+joyno); //+OX+" "+OY+" "+newX+" "+newY+
            if(joyno==1)
            {
                byte x=0x01;
                sendData( x, 1);
            } else if(joyno==0)
            {
                byte x=0x10;
                sendData( x, 1);
            }
            dir='u';
        }
        else if(angle>=135&&angle<225)
        {
            //textView2.setText("Left "+joyno); //+OX+" "+OY+" "+newX+" "+newY+" "
            if(joyno==1)
            {
                if(newX==OX)
                {
                    if(axis_check==0&&dir=='r')
                    {
                        byte y=0;
                        sendData( y,0 );
                    } else
                    {
                        byte x = 0x08;
                        sendData( x, 1 );
                    }
                    dir='l';
                    axis_check=1;
                }else {
                    byte x = 0x08;
                    sendData( x, 1 );
                }
            } else if(joyno==0)
            {
                if(newX==OX)
                {
                    if(axis_check==1&&dir=='r')
                    {
                        byte y=0;
                        sendData( y,0 );
                    } else
                    {
                        byte x = (byte)0x80;
                        sendData( x, 1 );
                    }
                    dir='l';
                    axis_check=0;
                }else {
                    byte x = (byte)0x80;
                    sendData( x, 1 );
                }
                dir='l';
            }
        }
        else if(angle>=225&&angle<315)
        {
            //textView2.setText("Down "+joyno); //+OX+" "+OY+" "+newX+" "+newY+" "
            if(joyno==1)
            {
                byte x=0x04;
                sendData( x, 1);
            } else if(joyno==0)
            {
                byte x=0x40;
                sendData( x, 1);
            }
            dir='d';
        }
        else
        {
            //textView2.setText("Right "+joyno); //+OX+" "+OY+" "+newX+" "+newY+" "
            /*if(joyno==1)
            {
                byte x=0x02;
                sendData( x, 1);
            } else if(joyno==0)
            {
                byte x=(byte)0x20;
                sendData( x, 1);
            }
            */
            if(joyno==1)
            {
                if(newX==OX)
                {
                    if(axis_check==0&&dir=='l')
                    {
                        byte y=0;
                        sendData( y,0 );
                    } else
                    {
                        byte x = 0x02;
                        sendData( x, 1 );
                    }
                    dir='r';
                    axis_check=1;
                }else {
                    byte x = 0x02;
                    sendData( x, 1 );
                }
                dir='r';
            }
            if(joyno==0)
            {
                if(newX==OX)
                {
                    if(axis_check==1&&dir=='l')
                    {
                        byte y=0;
                        sendData( y,0 );
                    } else
                    {
                        byte x = (byte)0x20;
                        sendData( x, 1 );
                    }
                    dir='r';
                    axis_check=0;
                }else {
                    byte x = (byte)0x20;
                    sendData( x, 1 );
                }
                dir='r';
            }
        }
        if(OX==newX&&OY==newY)
        {
            byte x=0;
            sendData( x, 1);
        }

    }
}
