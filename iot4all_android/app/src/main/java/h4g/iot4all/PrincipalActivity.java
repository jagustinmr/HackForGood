package h4g.iot4all;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//Clase con la lista de objetos disponibles en la aplicacion
public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv;

    private List<dispositivo> persons;
    private ReaderUtils readerUtils;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView txtSpeechInput;

    //ArrayList de los objetos disponibles en la demo
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new dispositivo(R.drawable.cafetera_icon2,"Cafetera", "20:00","",20));
        persons.add(new dispositivo(R.drawable.ventilador,"Ventilador", "21:00","",20));
        persons.add(new dispositivo(R.drawable.bombilla,"Luz", "22:00","",20));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princiapl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });


        readerUtils = new ReaderUtils(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        initializeData();
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.princiapl, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                    compareTexts(result.get(0));
                }
                break;
            }

        }
    }

    private void compareTexts(String text){
        readerUtils.speak(text);

        if(text.equals("cafetera")){
            Intent intent = new Intent(this, DispositivoActivity.class);
            startActivity(intent);

        }
        if(text.equals("ventilador")){
            Intent intent = new Intent(this, VentiladorActivity.class);
            startActivity(intent);
        }
       if(text.equals("luz")){
            Intent intent = new Intent(this, BombillaActivity.class);
           startActivity(intent);
       }

        if(text.equals("encender cafetera")){
            GetMethodDemo getMethodDemo = new GetMethodDemo();
            getMethodDemo.execute();

        }

        if(text.equals("apagar cafetera")){
            GetMethodDemoOff getMethodDemo2 = new GetMethodDemoOff();
            getMethodDemo2.execute();

        }

        if(text.equals("encender luz")){
            GetMethodDemo getMethodDemo = new GetMethodDemo();
            getMethodDemo.execute();

        }

        if(text.equals("apagar luz")){
            GetMethodDemoOff getMethodDemo2 = new GetMethodDemoOff();
            getMethodDemo2.execute();

        }

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

