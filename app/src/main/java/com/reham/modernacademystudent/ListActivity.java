package com.reham.modernacademystudent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setTitle("List View");

        pdfView = (PDFView) findViewById(R.id.pdfViewer);
        pdfView.fromAsset("list.pdf").load();

        // put url of any pdf to download from the internet!!
//        new RetrievePDFStream().execute("http://ancestralauthor/download/list.pdf");

        //new RetrievePDFBytes().execute("http://ancestralauthor/download/list.pdf");
    }


//    class RetrievePDFBytes extends AsyncTask<String,Void, byte[]>
//    {
//
//        @Override
//        protected byte[] doInBackground(String... strings) {
//            InputStream inputStream= null;
//            try
//            {
//                URL url = new URL(strings[0]);
//                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//                if(urlConnection.getResponseCode() == 200)
//                {
//                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
//                }
//            }
//            catch (IOException e)
//            {
//                return null;
//            }
//            try {
//                return IOUtils.toByteArray(inputStream);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(byte[] bytes) {
//            pdfView.fromBytes(bytes).load();
//        }
//    }




//    class RetrievePDFStream extends AsyncTask<String,Void, InputStream>
//    {
//
//        @Override
//        protected InputStream doInBackground(String... strings) {
//            InputStream inputStream= null;
//            try
//            {
//                URL url = new URL(strings[0]);
//                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//                if(urlConnection.getResponseCode() == 200)
//                {
//                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
//                }
//            }
//            catch (IOException e)
//            {
//                return null;
//            }
//            return inputStream;
//        }
//
//        @Override
//        protected void onPostExecute(InputStream inputStream) {
//            pdfView.fromStream(inputStream).load();
//        }
//    }
}
