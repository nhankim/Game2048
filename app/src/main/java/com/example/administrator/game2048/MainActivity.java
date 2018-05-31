package com.example.administrator.game2048;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.adapter.AdapterOSo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnReset, btnThoat;
    GridView gvBoardGame;
    TextView txtPoint, txtMaxPoint;
    AdapterOSo adapterOSo;

    float X, Y;
    String luuTrangThai = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        khoiTao();
        addEvents();
    }

    private void addEvents() {
        gvBoardGame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN://sự kiện nhấn xuống
                        X = event.getX();
                        Y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP://sự kiện nhả
                        if (Math.abs(event.getX() - X) > Math.abs(event.getY() - Y)) //vuốt ngang
                        {
                            if (event.getX() > X)
                            {
                                GameData.getGameData().vuotPhai();
                                adapterOSo.notifyDataSetChanged();
                                txtPoint.setText("" + GameData.getGameData().getDiem());
                                txtMaxPoint.setText("" + GameData.getGameData().getDiemMax());
                            }
                            else
                            {
                                GameData.getGameData().vuotTrai();
                                adapterOSo.notifyDataSetChanged();
                                txtPoint.setText("" + GameData.getGameData().getDiem());
                                txtMaxPoint.setText("" + GameData.getGameData().getDiemMax());
                            }
                        }
                        else //vuốt dọc
                        {
                            if (event.getY() > Y)
                            {
                                GameData.getGameData().vuotXuong();
                                adapterOSo.notifyDataSetChanged();
                                txtPoint.setText("" + GameData.getGameData().getDiem());
                                txtMaxPoint.setText("" + GameData.getGameData().getDiemMax());
                            }
                            else
                            {
                                GameData.getGameData().vuotLen();
                                adapterOSo.notifyDataSetChanged();
                                txtPoint.setText("" + GameData.getGameData().getDiem());
                                txtMaxPoint.setText("" + GameData.getGameData().getDiemMax());
                            }
                        }
                }

                if (GameData.getGameData().endGame() == 1)
                {
                    showDialogEnd();
                }
                return true;
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPoint.setText("0");
                GameData.getGameData().newGame();
                adapterOSo.notifyDataSetChanged();
                GameData.getGameData().taoDuLieuBoardGame(MainActivity.this);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showDialogEnd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.btn_plus);
        builder.setTitle("Trò chơi kết thúc");
        builder.setMessage("Bạn muốn làm gì?");
        builder.setPositiveButton("Chơi lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txtPoint.setText("0");
                GameData.getGameData().newGame();
                adapterOSo.notifyDataSetChanged();
                GameData.getGameData().taoDuLieuBoardGame(MainActivity.this);
            }
        });
        builder.setNegativeButton("Thoát!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();
    }

    private void addControls() {
        gvBoardGame = findViewById(R.id.gvBoardGame);
        txtPoint = findViewById(R.id.txtPoint);
        txtMaxPoint = findViewById(R.id.txtMaxPoint);
        btnReset = findViewById(R.id.btnReset);
        btnThoat = findViewById(R.id.btnThoat);

        txtMaxPoint.setText(""+GameData.getGameData().getDiemMax());
    }

    private void khoiTao() {
        GameData.getGameData().taoDuLieuBoardGame(MainActivity.this);
        adapterOSo = new AdapterOSo(MainActivity.this, R.layout.o_vuong, GameData.getGameData().getDsSo());
        gvBoardGame.setAdapter(adapterOSo);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(luuTrangThai,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Max",""+GameData.getGameData().getDiemMax());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(luuTrangThai,MODE_PRIVATE);
        String diemLuu = sharedPreferences.getString("Max","0");
        txtMaxPoint.setText(""+diemLuu);
        GameData.getGameData().setDiemMax(Integer.parseInt(diemLuu));
    }
}
