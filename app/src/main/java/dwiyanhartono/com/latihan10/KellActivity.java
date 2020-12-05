package dwiyanhartono.com.latihan10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KellActivity extends AppCompatActivity {

    TextView TV_Hasil;
    public static final String KUNCI_KELL = "kell";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kell);
        Inisial();
        Set_Object();
    }

    private void Inisial() {
        TV_Hasil = findViewById(R.id.textView_Kell_Hasil);
    }


    private void Set_Object() {
        Intent intent = getIntent();
        int Nilai_Kell = intent.getIntExtra(KUNCI_KELL,0);
        TV_Hasil.setText("Keliling Persegi adalah: " + Nilai_Kell);
    }

}
