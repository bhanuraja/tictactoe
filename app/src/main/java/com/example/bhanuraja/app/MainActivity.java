package com.example.bhanuraja.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    boolean gameActive=true;
    //0=yellow 1=red 2=notplayed
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
public void playAgain(View view){
    gameActive=true;
    LinearLayout gamemenu=(LinearLayout)findViewById(R.id.game);
    gamemenu.setVisibility(View.INVISIBLE);
    for(int i=0;i<9;i++){
        gameState[i]=2;
    }
    GridLayout gridLayout=(GridLayout)findViewById(R.id.gridlayout);
    for(int i=0;i<9;i++){
        ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
    }

}

  public void dropIn(View view){
      ImageView counter =(ImageView)view;

      int tappecounter=Integer.parseInt(counter.getTag().toString());
      if(gameState[tappecounter]==2&&gameActive) {
          counter.setTranslationY(-1000f);
          gameState[tappecounter]=activePlayer;
          if (activePlayer == 0) {
              counter.setImageResource(R.drawable.yellow);
              activePlayer = 1;
          } else {
              counter.setImageResource(R.drawable.red);
              activePlayer = 0;
          }
          counter.animate().translationYBy(1000f).rotation(360).setDuration(500);

          for(int[] winningPosition: winningPositions){
              if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                      gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                              gameState[winningPosition[0]]!=2) {
                  gameActive=false;
                  if(gameState[winningPosition[0]]==0){
                      TextView message=(TextView)findViewById(R.id.textView);
                      message.setText("Yellow is the Winner");
                  }
                  else{
                      TextView message=(TextView)findViewById(R.id.textView);
                      message.setText("Red is the Winner");
                  }

                  LinearLayout gamemenu=(LinearLayout)findViewById(R.id.game);
              gamemenu.setVisibility(View.VISIBLE);
              }else{ boolean gameIsOver=true;
                  for(int counterState:gameState){
                      if(counterState==2){gameIsOver=false;}
                  }
                  if(gameIsOver){
                      TextView message=(TextView)findViewById(R.id.textView);
                      message.setText("Game is draw");
                      LinearLayout gamemenu=(LinearLayout)findViewById(R.id.game);
                      gamemenu.setVisibility(View.VISIBLE);
                  }
              }

          }
      }

  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
