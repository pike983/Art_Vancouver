package ca.bcit.comp3717.artvancouver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    public void onFindArt(View view) {
        Log.i("Info", "button pressed");
        Intent intent = new Intent(this, artsearchactivity.class);
        startActivity(intent);
    }

    public void onDiscussArt(View view) {
        Log.i("Info", "button pressed");
        Intent intent = new Intent(this, ForumActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
    }

    public void onLogOut(View view) {
        finish();
    }
}
