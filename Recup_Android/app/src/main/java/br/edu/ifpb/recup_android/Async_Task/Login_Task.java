package br.edu.ifpb.recup_android.Async_Task;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.glassfish.grizzly.http.server.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.edu.ifpb.recup_android.Activity.MainActivity;

/**
 * Created by Rafael on 09/02/2016.
 */
public class Login_Task extends AsyncTask<JSONObject, Void, Response> {

    private Activity activity;

    public Login_Task(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected Response doInBackground(JSONObject... valores) {

        Response response = null;

        try {

            response = HttpService.sendJSONPostResquest("verificarLogin",valores[0]);

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

            if (status == 202) {

                JSONObject json = new JSONObject(response.getContentValue());
                String codigo = json.getString("codigo");
                String nome = json.getString("nome");

                Toast.makeText(activity,nome+" está logado no sistema", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();

            }else{
                Toast.makeText(activity,"Usuario ou Senha incorreta", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {

            Log.e("Rec App", "JSONException: " + e);
        }
    }
}


