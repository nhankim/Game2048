package com.example.administrator.game2048;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.TypedArray;

import com.example.administrator.custom.OVuong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameData {
    private static GameData gameData;
    private int[] dsMau;
    private ArrayList<Integer> dsSo = new ArrayList<>();
    private int[][] mangHaiChieu = new int[4][4];
    private Random rd = new Random();
    private int diem, diemMax;

    static {
        gameData = new GameData();
    }

    public static GameData getGameData() {
        return gameData;
    }

    public ArrayList<Integer> getDsSo() {
        return dsSo;
    }

    public void newGame() {
        dsSo.clear();
        diem = diem * 0;
    }

    public void setDiemMax(int diemMax) {
        this.diemMax = diemMax;
    }

    public int getDiemMax() {
        if (diem > diemMax) {
            diemMax = diem;
        } else {
            return diemMax;
        }
        return diemMax;
    }

    public int getDiem() {
        return diem;
    }

    public void taoDuLieuBoardGame(Activity context) {
        dsSo.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mangHaiChieu[i][j] = 0;
                dsSo.add(0);
            }
        }
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.mautungoso);
        dsMau = new int[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++) {
            dsMau[i] = typedArray.getColor(i, 0);
        }
        typedArray.recycle();
        taoSo();
        themSoVaoDanhSachSo();
    }

    public int setColor(int so) {
        if (so == 0) {
            return R.color.okhongcoso;
        } else {
            int a = (int) (Math.log(so) / Math.log(2));
            return dsMau[a - 1];
        }
    }


    public void taoSo() {
        int soO = 0;
        for (int i = 0; i < 16; i++) {
            if (dsSo.get(i) == 0) {
                soO++;
            }
        }
        int soOTao;
        if (soO > 1) {
            soOTao = rd.nextInt(2) + 1;
        } else {
            if (soO == 1) {
                soOTao = 1;
            } else {
                soOTao = 0;
            }
        }
        while (soOTao != 0) {
            int i = rd.nextInt(4), j = rd.nextInt(4);
            if (mangHaiChieu[i][j] == 0) {
                mangHaiChieu[i][j] = 2;
                soOTao--;
            }

        }
    }

    public void themSoVaoDanhSachSo() {
        dsSo.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dsSo.add(mangHaiChieu[i][j]);
            }
        }
    }

    public void vuotTrai() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = mangHaiChieu[i][j];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j + 1; k < 4; k++) {
                        int sox = mangHaiChieu[i][k];
                        if (sox == 0) {
                            continue;
                        } else {
                            if (sox == so) {
                                mangHaiChieu[i][j] = so * 2;
                                diem = diem + so * 2;
                                mangHaiChieu[i][k] = 0;
                                break;
                            } else
                                break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = mangHaiChieu[i][j];
                if (so == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int so1 = mangHaiChieu[i][k];
                        if (so == so1) {
                            continue;
                        } else {
                            mangHaiChieu[i][j] = mangHaiChieu[i][k];
                            mangHaiChieu[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        themSoVaoDanhSachSo();
    }

    public void vuotPhai() {
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = mangHaiChieu[i][j];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j - 1; k >= 0; k--) {
                        int sox = mangHaiChieu[i][k];
                        if (sox == 0) {
                            continue;
                        } else {
                            if (sox == so) {
                                mangHaiChieu[i][j] = so * 2;
                                diem = diem + so * 2;
                                mangHaiChieu[i][k] = 0;
                                break;
                            } else
                                break;
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = mangHaiChieu[i][j];
                if (so == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int so1 = mangHaiChieu[i][k];
                        if (so == so1) {
                            continue;
                        } else {
                            mangHaiChieu[i][j] = mangHaiChieu[i][k];
                            mangHaiChieu[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        themSoVaoDanhSachSo();
    }

    public void vuotLen() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = mangHaiChieu[j][i];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j + 1; k < 4; k++) {
                        int sox = mangHaiChieu[k][i];
                        if (sox == 0) {
                            continue;
                        } else {
                            if (sox == so) {
                                mangHaiChieu[j][i] = so * 2;
                                diem = diem + so * 2;
                                mangHaiChieu[k][i] = 0;
                                break;
                            } else
                                break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = mangHaiChieu[j][i];
                if (so == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int so1 = mangHaiChieu[k][i];
                        if (so == so1) {
                            continue;
                        } else {
                            mangHaiChieu[j][i] = mangHaiChieu[k][i];
                            mangHaiChieu[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        themSoVaoDanhSachSo();
    }

    public void vuotXuong() {
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = mangHaiChieu[j][i];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j - 1; k >= 0; k--) {
                        int sox = mangHaiChieu[k][i];
                        if (sox == 0) {
                            continue;
                        } else {
                            if (sox == so) {
                                mangHaiChieu[j][i] = so * 2;
                                diem = diem + so * 2;
                                mangHaiChieu[k][i] = 0;
                                break;
                            } else
                                break;
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = mangHaiChieu[j][i];
                if (so == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int so1 = mangHaiChieu[k][i];
                        if (so == so1) {
                            continue;
                        } else {
                            mangHaiChieu[j][i] = mangHaiChieu[k][i];
                            mangHaiChieu[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        themSoVaoDanhSachSo();
    }

    public int endGame() {
        int controng = 0, congdon = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (mangHaiChieu[i][j] == 0)
                    controng = 1;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (mangHaiChieu[i][j] == mangHaiChieu[i][j + 1] || mangHaiChieu[i][j] == mangHaiChieu[i + 1][j])
                    congdon = 1;
        if (controng == 0 && congdon == 0)
            return 1;
        else
            return 0;
    }

}