package by.diogenoz.hello3.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity {

    Game game;
    TextView hNameTextView;
    TextView aNameTextView;
    TextView hSetTextView;
    TextView aSetTextView;

    Button hScoreButton;
    Button hAceButton;
    Button hErrSrvButton;
    Button hBlockButton;
    Button hErrAttButton;
    Button hAtt1Button;
    Button hAtt2Button;
    Button hAtt3Button;
    Button hAtt4Button;
    Button hAtt5Button;
    Button hAtt6Button;

    Button aScoreButton;
    Button aAceButton;
    Button aErrSrvButton;
    Button aBlockButton;
    Button aErrAttButton;
    Button aAtt1Button;
    Button aAtt2Button;
    Button aAtt3Button;
    Button aAtt4Button;
    Button aAtt5Button;
    Button aAtt6Button;

    Button cancelButton;

    MatchOptions match_options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();

    }
    private void logToSql(Round round){
        //
        //adapter.addItem(round);
    }

    private void setUpView() {

        match_options=new MatchOptions(3,5,3);
        game= new Game("Home1","Away1",match_options,this);


        hNameTextView = (TextView) findViewById(R.id.HNameTextView);
        aNameTextView = (TextView) findViewById(R.id.ANameTextView);
        hSetTextView = (TextView) findViewById(R.id.HSetTextView);
        aSetTextView = (TextView) findViewById(R.id.ASetTextView);

        hScoreButton = (Button) findViewById(R.id.HScoreButton);
        hAceButton = (Button) findViewById(R.id.HAceButton);
        hErrSrvButton = (Button) findViewById(R.id.HErrSrvButton);
        hBlockButton = (Button) findViewById(R.id.HBlockButton);
        hErrAttButton = (Button) findViewById(R.id.HErrAttButton);
        hAtt1Button = (Button) findViewById(R.id.HAtt1Button);
        hAtt2Button = (Button) findViewById(R.id.HAtt2Button);
        hAtt3Button = (Button) findViewById(R.id.HAtt3Button);
        hAtt4Button = (Button) findViewById(R.id.HAtt4Button);
        hAtt5Button = (Button) findViewById(R.id.HAtt5Button);
        hAtt6Button = (Button) findViewById(R.id.HAtt6Button);

        aScoreButton = (Button) findViewById(R.id.AScoreButton);
        aAceButton = (Button) findViewById(R.id.AAceButton);
        aErrSrvButton = (Button) findViewById(R.id.AErrSrvButton);
        aBlockButton = (Button) findViewById(R.id.ABlockButton);
        aErrAttButton = (Button) findViewById(R.id.AErrAttButton);
        aAtt1Button = (Button) findViewById(R.id.AAtt1Button);
        aAtt2Button = (Button) findViewById(R.id.AAtt2Button);
        aAtt3Button = (Button) findViewById(R.id.AAtt3Button);
        aAtt4Button = (Button) findViewById(R.id.AAtt4Button);
        aAtt5Button = (Button) findViewById(R.id.AAtt5Button);
        aAtt6Button = (Button) findViewById(R.id.AAtt6Button);



    }
    public void addhName(View view) {
        hNameTextView.setText(game.gethName());
    }
    public void addaName(View view) {
        aNameTextView.setText(game.getaName());
    }
    public void addhSet(View view) {
        hSetTextView.setText(Integer.toString(game.gethSet()));
    }
    public void addaSet(View view) {
        aSetTextView.setText(Integer.toString(game.getaSet()));
    }
    public void addhScore(View view) {
         hScoreButton.setText(Integer.toString(game.gethScore()));
    }
    public void addaScore(View view) {
        aScoreButton.setText(Integer.toString(game.getaScore()));
    }

    public void addhAce(View view) {
        game.addMatchEvent(1,1);
        //hAceButton.setHint(Integer.toString(game.getMatchStat().gethAces()));



        refreshScore(view);
    }
    public void addaAce(View view) {
        game.addMatchEvent(2,1);
        aAceButton.setHint(Integer.toString(game.getMatchStat().getaAces()));
        refreshScore(view);
    }
    private void refreshScore(View view){
        addhScore(view);
        addaScore(view);
        addhName(view);
        addaName(view);
        addhSet(view);
        addaSet(view);


    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        game.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
