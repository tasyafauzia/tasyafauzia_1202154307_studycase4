package com.tasyafauzia.android.tasyafauzia_1202154307_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Mendeklarasikan private variable
    private Button list, pencari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (Button)findViewById(R.id.list);
        pencari = (Button)findViewById(R.id.gambar);

        //perintah yang untuk melakukan eksekusi pada saat meng-klik button
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Perintah ketika button di-klik maka akan menampilkan toast
                Toast.makeText(MainActivity.this,"You Choose List Mahasiswa",Toast.LENGTH_SHORT).show();
                //Perintah Intent untuk lanjut menuju aktivitas yang selanjutnya ketika button di-klik.
                Intent menu1 = new Intent(MainActivity.this,ListNama.class);
                //Perintah untuk memulai suatu aktivitas
                startActivity(menu1);
            }
        });

        //Perintah untuk melakukan eksekusi pada saat button di-klik
        pencari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Perintah ketika button di-klik maka akan menampilkan toast
                Toast.makeText(MainActivity.this,"You Choose Pencari Gambar",Toast.LENGTH_SHORT).show();
                //Perintah Intent untuk lanjut menuju aktivitas yang selanjutnya ketika button di-klik.
                Intent menu2 = new Intent(MainActivity.this,Pencarigambar.class);
                //Perintah untuk memulai suatu aktivitas
                startActivity(menu2);
            }
        });
    }
}
