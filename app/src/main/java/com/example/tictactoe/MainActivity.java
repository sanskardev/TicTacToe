package com.example.tictactoe;

import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final char[] current = {'X'};
        final boolean[][] flag = new boolean[3][3];
        final TextView no_of_moves = findViewById(R.id.moves);
        final TextView[][] tiles = new TextView[3][3];

        setTilesinArray(tiles);


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
                            setSymbolToTile(no_of_moves, tiles, finalI, finalJ, current[0], flag);
                        }
                    }
                });
            }
        }


        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_tiles(tiles, flag, no_of_moves);
            }
        });

    }


    public void setTilesinArray(TextView[][] tiles) {

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


    public void setSymbolToTile(TextView no_of_moves, TextView[][] tiles, int i , int  j, char current, boolean[][] flag) {

        int moves = Integer.parseInt(no_of_moves.getText().toString());
        no_of_moves.setText(String.valueOf(moves + 1));
        tiles[i][j].setText(current);
        flag[i][j] = true;
        current = ('X' == current) ? 'O' : 'X';

    }


    public void reset_tiles(TextView[][] tiles, boolean[][] flag, TextView no_of_moves) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j].setText("");
                flag[i][j] = false;
                no_of_moves.setText(String.valueOf(0));
            }
        }

    }


}