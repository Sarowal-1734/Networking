package com.sarowal.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //URL link of the image
    String flower = "http://clipart-library.com/newimages/rose-clip-art-31.png";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image_view);
        new imageLoaderTask().execute(flower); //Calling doInBackground method
    }

    public class imageLoaderTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {
            //Background Thread
            try {
                URL url = new URL(flower);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();  //Start sending byte code
                InputStream inputStream = httpURLConnection.getInputStream(); //Recieving the byte codes
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream); //Decoding the byte codes
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute() {
            //Main Thread
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //Main Thread
            imageView.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            //Main Thread
            super.onProgressUpdate(values);
        }
    }
}