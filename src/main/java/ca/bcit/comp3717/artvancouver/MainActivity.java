package ca.bcit.comp3717.artvancouver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void onClickLogin(View view) {
        Intent i = new Intent(MainActivity.this, ArtInfoActivity.class);
        startActivity(i);
    }

    public void onClickCreateAccount(View view) {
        Intent i = new Intent(MainActivity.this, MapActivity.class);
        startActivity(i);
    }
}
