package com.example.tictactoe;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                tiles[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tiles[finalI][finalJ].setText(current[0]);
                        if (current[0].equals("X"))
                            current[0] = "O";
                        else
                            current[0] = "X";
                    }
                });
            }
        }

    }
}
