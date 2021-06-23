package com.example.ttcxogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.ttcxogame.MainActivity.EXTRA_MESSAGE;

public class GameScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean P1Turn = true;
    private int Turns;
    private String XName, OName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        Intent intent = getIntent();
        boolean mode = intent.getBooleanExtra("Mode.cad", false);
        String Xname = intent.getStringExtra(EXTRA_MESSAGE);
        String Oname = intent.getStringExtra("cocm.example.ttcxogame");
        XName = Xname;
        OName = Oname;

        TextView textViewPlayer1 = findViewById(R.id.SetXname);
        TextView textViewPlayer2 = findViewById(R.id.SetOname);
        textViewPlayer1.setText("X: "+XName);
        textViewPlayer2.setText("O: "+OName);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "Btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonNew = findViewById(R.id.New);
        buttonNew.setOnClickListener(v -> resetBoard());
        Button buttonBack = findViewById(R.id.Back);
        buttonBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onClick(View v) {

            if (!((Button) v).getText().toString().equals("")) {
                return;
            }
            if (P1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");

            }
            Turns++;
            if (checkForWin()) {
                if (P1Turn) {
                    Player1Wins();
                } else {
                    Player2Wins();
                }
            } else if (Turns == 9) {
                draw();
            } else {
                P1Turn = !P1Turn;
            }

        }


    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void Player1Wins() {
        Toast.makeText(this, XName + " Wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void Player2Wins() {
        Toast.makeText(this, OName + " Wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();

    }

    private void resetBoard() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");

            }
        }
        Turns=0;
        P1Turn=true;
    }
}

