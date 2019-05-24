package com.example.tictactoe;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    TextView no_of_moves;
    char current;
    boolean[][] flag;
    TextView[][] tiles;
    Dialog result_dialog;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result_dialog = new Dialog(this);

        no_of_moves = findViewById(R.id.moves);
        current = 'X';
        flag = new boolean[3][3];
        tiles = new TextView[3][3];

        setTilesInArray();


        no_of_moves.setText(String.valueOf(0));


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                tiles[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flag[finalI][finalJ]) {

                            vibrate();

                        } else {

                            setSymbolToTile(finalI, finalJ);

                            if (Integer.parseInt(no_of_moves.getText().toString()) >= 5) {
                                checkHorizontal();
                                checkVertical();
                                checkDiagonal();
                                checkDraw();
                            }

                        }
                    }
                });
            }
        }


        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_tiles();
            }
        });

    }


    public void setTilesInArray() {

        int temp;
        String[][] tile_names= new String[][]{{"tile_00", "tile_01", "tile_02"}, {"tile_10", "tile_11", "tile_12"}, {"tile_20", "tile_21", "tile_22"}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp = this.getResources().getIdentifier(tile_names[i][j], "id", this.getPackageName());
                tiles[i][j] = findViewById(temp);
            }
        }

    }


    public void vibrate() {

        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(100);
        }

    }


    public void setSymbolToTile(int i , int  j) {

        int moves = Integer.parseInt(no_of_moves.getText().toString());
        no_of_moves.setText(String.valueOf(moves + 1));
        tiles[i][j].setText(String.valueOf(current));
        flag[i][j] = true;
        current = ('X' == current) ? 'O' : 'X';

    }


    public void checkHorizontal() {

    }

    public void checkVertical() {

    }

    public void checkDiagonal() {

    }

    public void checkDraw() {

        if (9 == Integer.parseInt(no_of_moves.getText().toString())) {

            no_of_moves.setText("asdasd");

            result_dialog.setContentView(R.layout.result);

            TextView winner = result_dialog.findViewById(R.id.winner);
            winner.setText(getString(R.string.draw));

            Button play_again = result_dialog.findViewById(R.id.play_again);
            play_again.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset_tiles();
                    result_dialog.dismiss();
                }
            });

        }

    }


    public void reset_tiles() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j].setText("");
                flag[i][j] = false;
                no_of_moves.setText(String.valueOf(0));
            }
        }

    }


}