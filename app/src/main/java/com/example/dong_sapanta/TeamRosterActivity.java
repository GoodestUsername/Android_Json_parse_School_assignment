package com.example.dong_sapanta;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamRosterActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private ListView rosterLv;
    private int teamNumber;

    private static final String SERVICE_URL = "https://statsapi.web.nhl.com/api/v1/teams";
    private ArrayList<Roster> rosterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_roster);
        teamNumber = (Integer) getIntent().getExtras().get("index");

        rosterList = new ArrayList<>();
        rosterLv = findViewById(R.id.rosterList);
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
            JSONArray jsonArr;
            String jsonStr = sh.makeServiceCall(SERVICE_URL + "/" + teamNumber + "/roster");

            try {
                JSONObject json = new JSONObject(jsonStr);
                jsonArr = json.getJSONArray("roster");
                if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonArr.get(1));
                    Gson gson = new Gson();


                    ArrayList<Roster> list = new ArrayList<>();
                    if (jsonArr != null) {
                        int len = jsonArr.length();
                        JSONObject jsonObj;
                        for (int i=0;i<len;i++){
                            jsonObj = jsonArr.getJSONObject(i);
                            Roster person = gson.fromJson(jsonObj.get("person").toString(), Roster.class);
                            list.add(person);

                        }
                    }
                    rosterList = list;

                }
                else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG)
                            .show());
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            TeamRosterAdapter adapter = new TeamRosterAdapter(TeamRosterActivity.this, rosterList);

            rosterLv.setAdapter(adapter);

            rosterLv.setOnItemClickListener((adapterView, view, i, id) -> {
                Intent intent;
                intent = new Intent(TeamRosterActivity.this, PlayerDetailsActivity.class);
                intent.putExtra("link", rosterList.get(i).getLink());
                startActivity(intent);
            });
        }
    }
}