package ca.bcit.comp3717.artvancouver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        username = findViewById(R.id.usernameEnterEDT);
    }

    public void onClickLogin(View view) {
        Intent i = new Intent(MainActivity.this, MenuActivity.class);
        i.putExtra("username", username.getText());
        startActivity(i);
    }
}
