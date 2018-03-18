package com.tasyafauzia.android.tasyafauzia_1202154307_studycase4;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListNama extends AppCompatActivity {

        //Mendeklarasikan private variable
        private ListView vListView;
        private ProgressBar vProgressBar;
        private Button vStart;

        //Menyimpan string nama mahasiswa pada variable array mahasiswa
        private String [] Mahasiswa= {
                "Tasya", "Fauzia", "Iqbal", "Kazim", "Ilham",
                "Aisyah", "Zahra", "Fatimah", "Khadijah", "Muhammad",
                "Azka", "Rizaldi", "Hilman"
        };
        //Melakukan deklarasi variable private untuk menambah item pada listview
        private AddItemToListView vAddItemToListView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_nama);

            vProgressBar = (ProgressBar) findViewById(R.id.progressbar);
            vListView = (ListView) findViewById(R.id.ListView);
            vStart = (Button) findViewById(R.id.button_mulai);

            //Membuat progressbar yang bersifat visible pada saat aplikasi sedang berjalan
            vListView.setVisibility(View.GONE);

            //Mensetting adapter
            vListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));

            //Perintah untuk start async pada saat button di klik
            vStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Adapter proses dengan async task
                    vAddItemToListView = new AddItemToListView();
                    vAddItemToListView.execute();
                }
            });
        }

        //Perintah untuk proses async task dalam suatu class
        public class AddItemToListView  extends AsyncTask<Void, String, Void> {

            private ArrayAdapter<String> vAdapter;
            private int counter=1;
            ProgressDialog vProgressDialog = new ProgressDialog(ListNama.this);

            //Dipanggil pada thread UI sebelum task dilakukan
            //Langkah ini biasanya digunakan untuk mensetup task
            @Override
            protected void onPreExecute() {
                vAdapter = (ArrayAdapter<String>) vListView.getAdapter(); //casting suggestion

                //Perintah untuk progress dialog
                vProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                vProgressDialog.setTitle("Loading");
                vProgressDialog.setProgress(0);

                //Perintah untuk cancel pada dialog
                vProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Process was cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        vAddItemToListView.cancel(true);
                        //Perintah untuk menampilkan progress bar pada layar sesaat setelah melakukan klik cancel
                        vProgressBar.setVisibility(View.VISIBLE);
                        dialog.dismiss();

                    }
                });
                vProgressDialog.show();
            }

            //Dipanggil di thread latar belakang segera setelah onPreExecute () selesai mengeksekusi.
            //Langkah ini digunakan untuk melakukan perhitungan background yang bisa memakan waktu lama.
            @Override
            protected Void doInBackground(Void... params) {
                for (String item : Mahasiswa){
                    publishProgress(item);
                    try {
                        Thread.sleep(200);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if(isCancelled()){
                        vAddItemToListView.cancel(true);
                    }
                }
                return null;
            }

            //Dipanggil pada thread UI setelah melakukan panggilan untuk publishProgress
            //Metode ini digunakan untuk menampilkan kemajuan yang terjadi pada interface
            @Override
            protected void onProgressUpdate(String... values) {
                vAdapter.add(values[0]);

                Integer current_status = (int)((counter/(float)Mahasiswa.length)*100);
                vProgressBar.setProgress(current_status);

                //Mensetting tampilan sebuah progress pada dialog progress
                vProgressDialog.setProgress(current_status);

                //Mensetting message berupa persentase (bentuk persen) progress pada dialog progress
                vProgressDialog.setMessage(String.valueOf(current_status+"%"));
                counter++;
            }

            //Dipanggil pada thread UI setelah proses perhitungan latar belakang telah selesai
            //Hasil perhitungan latar belakang dilewatkan ke langkah ini sebagai parameter.
            @Override
            protected void onPostExecute(Void Void) {
                //Melakukan hide (menyembunyikan) progressbar
                vProgressBar.setVisibility(View.GONE);

                //Menghilangkan progress dialog
                vProgressDialog.dismiss();
                vListView.setVisibility(View.VISIBLE);
            }


        }
    }

