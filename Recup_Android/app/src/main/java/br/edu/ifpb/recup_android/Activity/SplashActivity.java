package br.edu.ifpb.recup_android.Activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import br.edu.ifpb.recup_android.R;
import br.edu.ifpb.recup_android.Async_Task.Connect_Serv;

public class SplashActivity extends Activity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler SplashScreen = new Handler();
        SplashScreen.postDelayed(SplashActivity.this, 3000);
    }
    @Override
    public void run() {

        StatusServer server = new StatusServer(SplashActivity.this);
        server.execute();

    }
}