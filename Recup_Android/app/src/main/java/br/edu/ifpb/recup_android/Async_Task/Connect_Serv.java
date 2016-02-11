package br.edu.ifpb.recup_android.Async_Task;

import android.os.AsyncTask;
import android.content.Intent;
import android.app.Activity;

import org.glassfish.grizzly.http.server.Response;
import org.json.JSONObject;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Rafael on 09/02/2016.
 */
public class Connect_Serv extends AsyncTask <JSONObject, void, Response> {

    private Activity activity;

    public StatusServer(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected Response doInBackground(JSONObject... params) {

        Response response = null;

        try {

            response = HttpService.sendGetRequest("statusServer");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
    @Override
    protected void onPostExecute(Response response) {

        try {

            int status;
            status = response.getStatusCodeHttp();

            if (status == 201) {

                JSONObject json = new JSONObject(response.getContentValue());

                Boolean statusServer = json.getBoolean("online");

                if(statusServer){
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }
                else{
                    activity.finish();
                }
            }

        } catch (JSONException e) {

            Log.e("Rec App", "JSONException: " + e);
        }
    }
}

}
