package com.example.shatla.gradle_proj;

import android.content.Context;
import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by pulkit4tech on 1/10/16.
 */

public class TestingAsyncTask extends AndroidTestCase {
    private static final String TAG = "JokeAsyncTaskTests";
    MyAsyncTask mJokeAsyncTask;
    Context mContext;
    String mJoke = "";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mJoke = null;
        mContext = getContext();
        mJokeAsyncTask = new MyAsyncTask(mContext) {
            @Override
            protected void onPostExecute(String s) {

            }
        };
    }

    @Test
    public void MyAsyncTask() {
        mJokeAsyncTask.execute();
        try {
            mJoke = mJokeAsyncTask.get(10, TimeUnit.SECONDS);
            assertNotNull(mJoke);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //Log.d("JokeAsyncTaskExec", "Execution Exception");
            e.printStackTrace();
        } catch (TimeoutException e) {
            //Log.d("TimeOut", "TimeOut Exception");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
