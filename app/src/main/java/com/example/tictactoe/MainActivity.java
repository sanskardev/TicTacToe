package com.example.tictactoe;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        final String[] current = {"X"};
        final boolean[][] flag = new boolean[3][3];
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                final int finalI1 = i;
                final int finalJ1 = j;
                tiles[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flag[finalI][finalJ]) {
                            if (Build.VERSION.SDK_INT >= 26) {
                                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(200);
                            }
                        } else {
                            tiles[finalI][finalJ].setText(current[0]);
                            flag[finalI1][finalJ1] = true;
                            if (current[0].equals("X"))
                                current[0] = "O";
                            else
                                current[0] = "X";
                        }
                    }
                });
            }
        }

        Button reset = findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tiles[i][j].setText("");
                        flag[i][j] = false;
                    }
                }
            }
        });

    }
}
