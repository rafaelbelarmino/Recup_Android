package br.edu.ifpb.recup_android.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.recup_android.R;
import br.edu.ifpb.recup_android.Async_Task.IMC_Sync;

public class Imc_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imc);

        Button Calcular = (Button) findViewById(R.id.calcula_imc);

        Calcular.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JSONObject geral = new JSONObject();

                        try {


                            EditText alturaEditText = (EditText) findViewById(R.id.altura_imc);
                            String altura = alturaEditText.getText().toString();
                            geral.put("Altura", altura);


                            EditText pesoEditText = (EditText) findViewById(R.id.peso_imc);
                            String peso = pesoEditText.getText().toString();
                            geral.put("peso", peso);

                            IMCAsyncTask calcularIMCAsyncTask = new IMCAsyncTask(v.getContext());
                            calcularIMCAsyncTask.execute(geral);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

}