package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;
import java.util.Random;


public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(s / 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);
        }

        return "Enfin réveillé après avoir dormi pendant " + s + " millisecondes !";

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressBar.get().setProgress(values[0]);
    }

    protected void onPostExecute (String result){
        mTextView.get().setText(result);
    }
}
