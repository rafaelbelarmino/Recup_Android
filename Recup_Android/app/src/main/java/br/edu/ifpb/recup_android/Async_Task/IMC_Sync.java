package br.edu.ifpb.recup_android.Async_Task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.sun.enterprise.config.serverbeans.HttpService;

import org.glassfish.grizzly.http.server.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import br.edu.ifpb.recup_android.Util.HtttpService;
import br.edu.ifpb.recup_android.Util.Response;

/**
 * Created by Rafael on 09/02/2016.
 */
public class IMC_Sync extends AsyncTask<JSONObject, Void, Response> {

    Context context;

    public IMC_Sync(Context context) {
        this.context = context;
    }

    @Override
    protected Response doInBackground(JSONObject... valores) {

        Response response = null;

        try {

            response = HttpService.sendJSONPostResquest("IMC", valores[0]);

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
                Toast.makeText(context, "Seu Índice de Massa Corporal é: "+valor, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {

            Log.e("Rec App", "JSONException: " + e);
        }
    }
}
