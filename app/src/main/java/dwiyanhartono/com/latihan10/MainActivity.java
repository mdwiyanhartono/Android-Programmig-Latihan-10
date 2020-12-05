package dwiyanhartono.com.latihan10;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imgheader;
    Button btnswtch;
    Button btnsubmit;
    private int indx = 1;
    private int nilaipanjang = 0;
    private int nilailebar = 0;
    SeekBar sheekpanjang;
    SeekBar sheeklebar;
    TextView txtpanjang;
    TextView txtlebar;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgheader = findViewById(R.id.imgheader);
        btnswtch = findViewById(R.id.button_Main_Switch);
        btnsubmit = findViewById(R.id.btnsubmit);
        sheekpanjang = findViewById(R.id.seekbarpanjang);
        sheeklebar = findViewById(R.id.seekbarlebar);
        txtpanjang = findViewById(R.id.txtpanjangVal);
        txtlebar = findViewById(R.id.txtlebarVal);
        fb = findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
        btnswtch();
        btnsbm();
        seek();
    }

    private void ShowDialog() {
        final Button BtnSubmit;
        final Button BtnExit;
        final RatingBar Rate;
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.profile);
        Rate = d.findViewById(R.id.rating);
        BtnSubmit = d.findViewById(R.id.button_Submit);
        BtnExit = d.findViewById(R.id.button_Exit);
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float Rating = Rate.getRating();
                String Kategori = "";
                if (Rating >= 5) {
                    Kategori = "Sangat Setuju";
                } else if (Rating >= 4) {
                    Kategori = "Setuju";
                } else if (Rating >= 3) {
                    Kategori = "Kurang Setuju";
                } else if (Rating >= 2) {
                    Kategori = "Tidak Setuju";
                } else {
                    Kategori = "Sangat Tidak Setuju";
                }
                String Pesan = "Nilai rating adalah " + Rating +
                        " yang artinya saya " + Kategori;
                Toast.makeText(MainActivity.this, Pesan, Toast.LENGTH_LONG).show();
            }
        });
        BtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Konfirmasi = getResources().getString(R.string.Konfirmasi);
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage(Konfirmasi)
                        .setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                d.dismiss();
                            }
                        })
                        .setNegativeButton("Nggak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Konfirmasi");
                alert.show();
            }
        });
        d.show();
    }

    private void btnswtch() {
        btnswtch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indx == 1) {
                    imgheader.setImageResource(R.drawable.bg2);
                    indx++;
                } else {
                    imgheader.setImageResource(R.drawable.bg1);
                    indx--;
                }
            }
        });
    }

    private void btnsbm() {
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int Nilai_Luas = nilaipanjang * nilailebar;
                final int Nilai_Kell = 2 * (nilaipanjang + nilailebar);
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("Silakan pilih menghitung Luas atau Keliling:")
                        .setCancelable(false)
                        .setPositiveButton("Luas", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, LuasActivity.class);
                                intent.putExtra("luas", Nilai_Luas);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Keliling", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, KellActivity.class);
                                intent.putExtra(KellActivity.KUNCI_KELL, Nilai_Kell);
                                startActivity(intent);
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Konfirmasi");
                alert.show();
            }
        });
    }

    private void seek() {
        String Teks_Panjang = String.valueOf(sheekpanjang.getProgress());
        txtpanjang.setText(Teks_Panjang);
        sheekpanjang.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nilaipanjang = progress;
                txtpanjang.setText(String.valueOf(nilaipanjang));
                //txtpanjang.setText(String.valueOf(sheekpanjang.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        String Teks_Lebar = String.valueOf(sheeklebar.getProgress());
        txtlebar.setText(Teks_Lebar);
        sheeklebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nilailebar = progress;
                txtlebar.setText(String.valueOf(progress));
                //txtlebar.setText(String.valueOf(sheeklebar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

}
