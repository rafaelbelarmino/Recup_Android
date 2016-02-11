package br.edu.ifpb.recup_android.Async_Task;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import org.glassfish.grizzly.http.server.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
/**
 * Created by Rafael on 09/02/2016.
 */
public class VCT extends AsyncTask<JSONObject, Void, Response> {

    Context context;

    public VCTAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Response doInBackground(JSONObject... valores) {

        Response response = null;

        try {

            response = HttpService.sendJSONPostResquest("calcularVCT", valores[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {

        try {

            int status = response.getStatusCodeHttp();

            if (status == HttpURLConnection.HTTP_OK) {

                JSONObject json = new JSONObject(response.getContentValue());

                String valor = json.getString("valor");

                Toast.makeText(context, "Seu Valor Calórico Total é: " + valor, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {

            Log.e("Rec App", "JSONException: " + e);
        }
    }
}


