package br.edu.ifpb.recup_android.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.recup_android.Async_Task.VCT;
import br.edu.ifpb.recup_android.R;


public class Calc_VCT extends Activity {

    private Spinner spnSexo;
    private ArrayAdapter<String> spnAdapter;
    private EditText edtPeso;
    private EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc__vct);

        spnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnSexo = (Spinner) findViewById(R.id.spn_vct_sexo);
        spnSexo.setAdapter(spnAdapter);

        spnAdapter.add("Masculino");
        spnAdapter.add("Feminino");


        Button enviar = (Button) findViewById(R.id.btn_vct_enviar);

        enviar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JSONObject geral = new JSONObject();
                        JSONObject entrevistado = new JSONObject();

                        try {

                            EditText alturaEditText = (EditText) findViewById(R.id.Text_Altura);
                            String altura = alturaEditText.getText().toString();
                            geral.put("altura", altura);


                            EditText pesoEditText = (EditText) findViewById(R.id.Text_Peso);
                            String peso = pesoEditText.getText().toString();
                            geral.put("peso", peso);


                            EditText nivelEsporteEditText = (EditText) findViewById(R.id.Text_Esporte);
                            String nivelEsporte = nivelEsporteEditText.getText().toString();
                            geral.put("Esporte", nivelEsporte);


                            String sexo = spnSexo.getSelectedItem().toString();
                            if(sexo.equals("Masculino")){
                                entrevistado.put("sexo","M");
                            } else {
                                entrevistado.put("sexo","F");
                            }


                            EditText nascimentoEditText = (EditText) findViewById(R.id.Text_Nascimento);
                            String nascimento = nascimentoEditText.getText().toString();

                            entrevistado.put("nascimento", nascimento);

                            geral.put("entrevistado", entrevistado);

                            VCT vctAsyncTask = new VCT(v.getContext());
                            vctAsyncTask.execute(geral);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }
}
