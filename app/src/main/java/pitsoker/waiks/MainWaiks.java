package pitsoker.waiks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
classe gerant l ecran d accueil
 */

public class MainWaiks extends AppCompatActivity implements View.OnClickListener{
    Button connection;
    final String PREFS_NAME = "MyPrefsFile";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_waiks);
        FirstTimeIdentifierDao ftiDao = new FirstTimeIdentifierDao(this);

        connection = (Button) findViewById(R.id.connexion);
        connection.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("my_first_time", true)) {
            System.out.println("first tiem");
            FirstTimeIdentifier ft = new FirstTimeIdentifier(1, "first");
            ftiDao.ajouter(ft);
            settings.edit().putBoolean("my_first_time", false).commit();
        }
        else{
            FirstTimeIdentifier ft2 = new FirstTimeIdentifier(1, "notfirst");
            ftiDao.modifier(ft2);
        }
        ftiDao.selectTest();

    }

    @Override
    public void onStart(){
        super.onStart();
    }


    @Override
    public void onClick(View v) {


        if(v == connection) {
            Intent i = new Intent(MainWaiks.this, GoogleSign.class);
            startActivity(i);
        }

    }
}