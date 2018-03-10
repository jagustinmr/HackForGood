package h4g.iot4all;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

//Clase para el speech
public class ReaderUtils implements TextToSpeech.OnInitListener {

    private Context context;
    private TextToSpeech textToSpeech;

    public ReaderUtils(Context context){
        this.context = context;
        textToSpeech = new TextToSpeech(context, ReaderUtils.this);
    }

    public void speak(String text){
        if(text != null && !text.isEmpty()){
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            //Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }

    public void stop(){
        if(textToSpeech.isSpeaking())
            textToSpeech.stop();
    }

    public void close(){
        textToSpeech.shutdown();
    }

    @Override
    public void onInit(int Text2SpeechCurrentStatus) {
        if (Text2SpeechCurrentStatus == TextToSpeech.SUCCESS){
            Log.i("ReaderUtils","onInit()-SUCCESS");
        }
    }



}
