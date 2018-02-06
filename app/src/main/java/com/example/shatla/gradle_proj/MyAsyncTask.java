package com.example.shatla.gradle_proj;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.androidlibrary.AndroidActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
/*import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
*/
import java.io.IOException;

import myApi.MyApi;
public class MyAsyncTask extends AsyncTask<Context,Void,String> {

    MyApi myApi;
    Context mContext;
    ProgressDialog Dialog;

    public MyAsyncTask(Context mContext){
        this.mContext = mContext;
        Dialog = new ProgressDialog(mContext);
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(),null).
                setRootUrl("http://10.0.2.2:8080/_ah/api/").setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                abstractGoogleClientRequest.setDisableGZipContent(true);
            }
        });
        myApi = builder.build();
    }

    @Override
    protected void onPreExecute() {
        Dialog.setMessage("Loading Some best joke ... (maybe)");
        Dialog.show();
    }

    @Override
    protected String doInBackground(Context... contexts) {

        String result = "Sorry some error!! No joke today";
        try {
            result = myApi.getJoke().execute().getData();
            Log.i("JOKE",result);

        }
        catch (Exception e){
            Log.e("ERROR",e.toString());
        }
        finally {
            return result;
        }

    }

    @Override
    protected void onPostExecute(String result) {
        Dialog.dismiss();
        //Toast.makeText(mContext,"YO !! cheers to the joke",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, AndroidActivity.class);
        intent.putExtra("Jokes",result);
        mContext.startActivity(intent);
    }
}
