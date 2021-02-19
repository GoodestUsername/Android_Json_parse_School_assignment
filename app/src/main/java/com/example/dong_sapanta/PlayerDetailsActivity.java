package com.example.dong_sapanta;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayerDetailsActivity extends AppCompatActivity {

    private static final String SERVICE_URL = "https://statsapi.web.nhl.com";
    private String newURL;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        String playerURLExtension = (String) getIntent().getExtras().get("link");
        newURL = SERVICE_URL + playerURLExtension;
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
            String jsonStr;
            JSONArray jsonArr;

            jsonStr = sh.makeServiceCall(newURL);

            if (jsonStr != null) {

                try {
                    JSONObject json = new JSONObject(jsonStr);
                    jsonArr = json.getJSONArray("people");
                    JSONObject details = jsonArr.getJSONObject(0);
                    String playerName = details.getString("fullName");
                    String teamName = details.getJSONObject("currentTeam").getString("name");
                    String age = details.getString("currentAge");
                    String nationality = details.getString("nationality");
                    String position = details.getJSONObject("primaryPosition").getString("name");
                    player = new Player(playerName, teamName, age, nationality, position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            TextView name = findViewById(R.id.name);
            String nameText = getResources().getString(R.string.player_details_name_label)
                    + player.getFullName();
            name.setText(nameText);

            TextView team = findViewById(R.id.team);
            String teamText = getResources().getString(R.string.player_details_team_name_label)
                    + player.getTeam();
            team.setText(teamText);

            TextView age = findViewById(R.id.age);
            String ageText = getResources().getString(R.string.player_details_age_label)
                    + player.getAge();
            age.setText(ageText);

            TextView nationality = findViewById(R.id.nationality);
            String nationalityText = getResources().getString(R.string.player_details_nationality_label)
                    + player.getNationality();
            nationality.setText(nationalityText);

            TextView position = findViewById(R.id.position);
            String positionText = getResources().getString(R.string.player_details_position_label)
                    + player.getPosition();
            position.setText(positionText);
        }
    }
}