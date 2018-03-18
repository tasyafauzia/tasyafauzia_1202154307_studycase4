package com.tasyafauzia.android.tasyafauzia_1202154307_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;

public class Pencarigambar extends AppCompatActivity {

    //Mendeklarasikan private variable
    private ImageView Image;
    private ProgressDialog progress;
    private EditText Url;
    private Button imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarigambar);

        Image = (ImageView)findViewById(R.id.gambar);
        Url = (EditText)findViewById(R.id.editUrl);
        imageButton = (Button) findViewById(R.id.buttoncari);

        //Melakukan eksekusi pada saat button diklik
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editUrl = Url.getText().toString();
                if(editUrl.isEmpty()){
                    //Perintah untuk show toast pada saat button di klik namun kolom edit text url dalam keadaan kosong
                    Toast.makeText(Pencarigambar.this," Enter URL Address",Toast.LENGTH_SHORT).show();
                }else {
                    // Menjalankan DownloadImage AsyncTask
                    new ImageDownload().execute(editUrl);
                }
            }
        });
    }
    private class ImageDownload extends AsyncTask<String, Void, Bitmap> {

        //Akan dipanggil di thread UI sebelum task dijalankan
        //Tahap ini digunakan untuk mensetup task
        protected void onPreExecute() {
            super.onPreExecute();
            // Membuat progress dialog
            progress = new ProgressDialog(Pencarigambar.this);
            // Menset judul pada progress dialog
            progress .setTitle("Search Image");
            // Menset message pada progress dialog
            progress .setMessage("Loading...");
            progress .setIndeterminate(false);
            // Menampilkan progress dialog
            progress .show();
        }

        //Akan dipanggil di thread latar belakang setelah onPreExecute () atau pada saat selesai mengeksekusi.
        //Tahap ini biasanya untuk memperhitungkan background yang bisa memakan waktu lama.
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                // untuk Download Image dari URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        //Akan dipanggil di thread UI setelah perhitungan latar belakang selesai.
        //Hasil perhitungan latar belakang dilewatkan ke langkah ini dan berlaku sebagai parameter.
        @Override
        protected void onPostExecute(Bitmap result) {
            // Melakukan set bitmap ke dalam ImageView
            Image.setImageBitmap(result);
            // Menutup progress dialog
            progress.dismiss();
        }
    }
}
