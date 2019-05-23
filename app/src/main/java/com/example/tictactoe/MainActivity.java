package com.example.tictactoe;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.tictactoe.R.id.main_layout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView[][] tiles = new TextView[3][3];
        int temp;

        String[][] tile_names= new String[][]{{"tile_00", "tile_01", "tile_02"}, {"tile_10", "tile_11", "tile_12"}, {"tile_20", "tile_21", "tile_22"}};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp = this.getResources().getIdentifier(tile_names[i][j], "id", this.getPackageName());
                tiles[i][j] = findViewById(temp);
            }
        }

        final ConstraintLayout main_layout = findViewById(R.id.main_layout);
        final String[] current = {"X"};
        final boolean[][] flag = new boolean[3][3];
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final int[] moves = {0};
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.result, main_layout, false);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(100, 100));
        final TextView[] result = {findViewById(R.id.winner)};



        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                tiles[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flag[finalI][finalJ]) {
                            if (Build.VERSION.SDK_INT >= 26) {
                                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(100);
                            }
                        } else {
                            moves[0]++;
                            tiles[finalI][finalJ].setText(current[0]);
                            flag[finalI][finalJ] = true;
                            if (current[0].equals("X"))
                                current[0] = "O";
                            else
                                current[0] = "X";
                            if (moves[0] == 9) {
                                result[0].setText("Draw!");
                                main_layout.addView(view);
                            }
                            else if (moves[0] > 5) {
                                int k = 0;
                                while (tiles[k][finalJ].equals(tiles[finalI][finalJ])) {
                                    if (2 == k) {
                                        result[0].setText(tiles[finalI][finalJ] + "Wins!");
                                        main_layout.addView(view);
                                    }
                                    k++;
                                }
                            }
                        }
                    }
                });
            }
        }

        Button reset = (Button) findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tiles[i][j].setText("");
                        flag[i][j] = false;
                        moves[0] = 0;
                    }
                }
            }
        });

    }
}
