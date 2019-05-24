package com.example.tictactoe;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    int no_of_moves;
    char current;
    boolean[][] flag;
    TextView[][] tiles;
    TextView turn;
    Dialog result_dialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        result_dialog = new Dialog(this);
        result_dialog.setCancelable(false);
        result_dialog.setCanceledOnTouchOutside(false);
        current = 'X';
        flag = new boolean[3][3];
        tiles = new TextView[3][3];
        turn = findViewById(R.id.turn);


        result_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        setTilesInArray();
        no_of_moves = 0;
        turn.setText(R.string.x_turn);




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

                            if (no_of_moves >= 5) {
                                checkHorizontal(finalI);
                                checkVertical(finalJ);
                                checkDiagonal(finalI, finalJ);
                                checkDraw();
                            }

                            changeCurrent();

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

        no_of_moves++;
        if ('X' == current) {
            tiles[i][j].setTextColor(Color.RED);
        } else {
            tiles[i][j].setTextColor(Color.BLUE);
        }
        tiles[i][j].setText(String.valueOf(current));
        flag[i][j] = true;

    }

    public void changeCurrent() {

        if ('X' == current) {
            current = 'O';
            turn.setText(R.string.o_turn);
        } else {
            current = 'X';
            turn.setText(R.string.x_turn);
        }

    }


    public void checkHorizontal(int row) {

        int yes = 0;

        for (int i = 0 ; (i < 3) && (tiles[row][i].getText().equals(String.valueOf(current))) ; i++) {
            yes++;
        }
        if (yes == 3) {

            //If winning move is 9th, then checkDraw() will change result TextView to "It\'s a draw!". This is why no_of_moves is changed to 10.
            no_of_moves = 10;
            if (current == 'X') {

                deploy_result("X wins!");

            } else {

                deploy_result("O wins!");

            }
        }
    }

    public void checkVertical(int column) {

        int yes = 0;

        for (int i = 0 ; (i < 3) && (tiles[i][column].getText().equals(String.valueOf(current))) ; i++) {
            yes++;
        }
        if (yes == 3) {

            //If winning move is 9th, then checkDraw() will change result TextView to "It\'s a draw!". This is why no_of_moves is changed to 10.
            no_of_moves = 10;
            if (current == 'X') {

                deploy_result("X wins!");

            } else {

                deploy_result("O wins!");

            }
        }

    }

    public void checkDiagonal(int I, int J) {

        if (I == J) {

            int yes = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j && tiles[i][j].getText().equals(String.valueOf(current))) {
                        yes++;
                    }
                }
            }

            if (3 == yes) {

                //If winning move is 9th, then checkDraw() will change result TextView to "It\'s a draw!". This is why no_of_moves is changed to 10.
                no_of_moves = 10;

                if ('X' == current) {
                    deploy_result("X wins!");
                } else {
                    deploy_result("O wins!");
                }
            }

        }

        if (2 == I + J) {

            int yes = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (2 == i + j && tiles[i][j].getText().equals(String.valueOf(current))) {
                        yes++;
                    }
                }
            }

            if (3 == yes) {

                //If winning move is 9th, then checkDraw() will change result TextView to "It\'s a draw!". This is why no_of_moves is changed to 10.
                no_of_moves = 10;

                if ('X' == current) {
                    deploy_result("X wins!");
                } else {
                    deploy_result("O wins!");
                }
            }

        }

    }

    public void checkDraw() {

        if (9 == no_of_moves) {

            deploy_result("It\'s a draw!");

        }

    }


    public void reset_tiles() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j].setText("");
                flag[i][j] = false;
                no_of_moves = 0;
            }
        }
        if ('O' == current) {
            changeCurrent();
        }

    }

    public void deploy_result(String result_winner) {

        result_dialog.setContentView(R.layout.result);

        TextView winner = result_dialog.findViewById(R.id.winner);
        winner.setText(result_winner);

        result_dialog.show();

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