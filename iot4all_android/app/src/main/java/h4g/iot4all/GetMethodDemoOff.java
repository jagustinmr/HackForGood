package h4g.iot4all;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


//Clase asynctask para el envio de la peticion de apagar un dispositivo
public class GetMethodDemoOff extends AsyncTask<Void , Void ,Void> {
    String server_response;
    private Ip _ip= Ip.getInstance();

    @Override
    protected Void doInBackground(Void... Void) {

        URL url;
        HttpURLConnection urlConnection = null;

        try {
            if(_ip.getIp().length()>10)
                url = new URL("http://"+_ip.getIp()+"/LED/OFF");
            else
                url = new URL("http://192.168.137.85/LED/OFF");
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                Log.i("result","OK");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
