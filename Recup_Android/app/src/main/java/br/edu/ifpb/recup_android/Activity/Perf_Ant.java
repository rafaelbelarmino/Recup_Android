package br.edu.ifpb.recup_android.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ifpb.recup_android.Async_Task.Ant_perf;
import br.edu.ifpb.recup_android.R;
import org.json.JSONException;
import org.json.JSONObject;

public class Perf_Ant extends Activity {


    private Spinner spnSexo;
    private ArrayAdapter<String> spnAdapter;
    private EditText Text_peso;
    private EditText Text_Altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perf__ant);

        spnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnSexo = (Spinner) findViewById(R.id.But_Sexo);
        spnSexo.setAdapter(spnAdapter);

        spnAdapter.add("Masculino");
        spnAdapter.add("Feminino");


        Button Enviar = (Button) findViewById(R.id.But_Enviar);

        Enviar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JSONObject geral = new JSONObject();
                        JSONObject entrevistado = new JSONObject();

                        try {


                            EditText alturaEditText = (EditText) findViewById(R.id.Text_Altura);
                            String altura = alturaEditText.getText().toString();
                            geral.put("altura", altura);


                            EditText pesoEditText = (EditText) findViewById(R.id.Text_peso);
                            String peso = pesoEditText.getText().toString();
                            geral.put("peso", peso);

                            //Sexo
                            String Sexo = spnSexo.getSelectedItem().toString();
                            if (Sexo.equals("Masculino")) {
                                entrevistado.put("sexo", "M");
                            } else {
                                entrevistado.put("sexo", "F");
                            }

                            //Data de Nascimento
                            EditText nascimentoEditText = (EditText) findViewById(R.id.Text_Nascimento);
                            String nascimento = nascimentoEditText.getText().toString();

                            entrevistado.put("nascimento", nascimento);

                            geral.put("entrevistado", entrevistado);

                            Ant_perf Antperf = new Ant_perf(v.getContext());
                            Antperf .execute(geral);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }
}

