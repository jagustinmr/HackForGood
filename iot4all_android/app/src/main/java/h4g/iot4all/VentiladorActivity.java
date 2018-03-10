package h4g.iot4all;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

//Clase para el ventilador
public class VentiladorActivity extends AppCompatActivity {

    private ReaderUtils readerUtils;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventilador);
        TextView text = (TextView) findViewById(R.id.textView2);
        text.setText("Ventilador");
        readerUtils = new ReaderUtils(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton floatingActionButton1 = (FloatingActionButton)findViewById(R.id.fab8);
        FloatingActionButton floatingActionButton2 = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        FloatingActionButton floatingActionButton3 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetMethodDemo getMethodDemo = new GetMethodDemo();
                getMethodDemo.execute();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetMethodDemoOff getMethodDemo2 = new GetMethodDemoOff();
                getMethodDemo2.execute();
            }
        });
    }


    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Te escucho");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "No soportado",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //  txtSpeechInput.setText(result.get(0));
                    compareTexts(result.get(0));
                }
                break;
            }

        }
    }

    private void compareTexts(String text){
        readerUtils.speak(text);

        if(text.equals("encender ventilador")){
            GetMethodDemo getMethodDemo = new GetMethodDemo();
            getMethodDemo.execute();

        }

        if(text.equals("apagar ventilador")){
            GetMethodDemoOff getMethodDemo2 = new GetMethodDemoOff();
            getMethodDemo2.execute();

        }
    }
}
