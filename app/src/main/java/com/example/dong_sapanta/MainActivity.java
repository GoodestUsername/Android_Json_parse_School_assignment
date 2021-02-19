package com.example.dong_sapanta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private ListView teamLv;
    // URL to get contacts JSON
    private static final String SERVICE_URL = "https://statsapi.web.nhl.com/api/v1/teams";
    private ArrayList<Team> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamList = new ArrayList<>();
        teamLv = findViewById(R.id.teamList);
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonStr);
                Gson gson = new Gson();
                BaseTeam baseTeam = gson.fromJson(jsonStr, BaseTeam.class);
                teamList = baseTeam.getTeams();
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                        "Couldn't get json from server. Check LogCat for possible errors!",
                        Toast.LENGTH_LONG)
                        .show());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            TeamAdapter adapter = new TeamAdapter(MainActivity.this, teamList);

            teamLv.setAdapter(adapter);

            teamLv.setOnItemClickListener((adapterView, view, i, id) -> {
                Intent intent;
                intent = new Intent(MainActivity.this, TeamRosterActivity.class);
                intent.putExtra("index", (int) id + 1);
                startActivity(intent);
            });
        }
    }

}