package br.edu.ifpb.recup_android.Async_Task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

import br.edu.ifpb.recup_android.Util.Response;
import br.edu.ifpb.recup_android.Util.HtttpService;

/**
 * Created by Rafael on 10/02/2016.
 */
public class Ant_perf extends AsyncTask<JSONObject, Void, Response>{

    Context context;

    public Ant_perf(Context context) {
        this.context = context;
    }

    @Override
    protected Response doInBackground(JSONObject... valores) {

        Response response = null;

        try {

            response = HttpService.sendJSONPostResquest("calcularPerfilAntropometrico", valores[0]);

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

                Toast.makeText(context, "Perfil antopometrico: " + valor, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {

            Log.e("Rec App", "JSONException: " + e);
        }
    }
}
}
