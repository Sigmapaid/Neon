package com.example.neon;

import android.os.AsyncTask;

//public class GetDateFromServerController extends AsyncTask<Integer, Void, String> {
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        mProgress.setProgress(0);
//        mProgress.setVisibility(View.Visible);
//    }
//
//    @Override
//    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
//        mProgress.setProgress(values[0]);
//    }
//
//    @Override
//    protected Void doInBackground(Void... params) {
//
//        for( int i = 0; i < 100; i++ ) {
//            try {
//                Thread.sleep(1000);
//            } catch( InterruptedException e ) {}
//            publishProgress(i);
//        }
//
//        return "All done!";
//
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute(aVoid);
//        if( isCancelled() ) {
//            return;
//        }
//
//        mProgress.setVisibility(View.GONE);
//        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
//    }
//}
