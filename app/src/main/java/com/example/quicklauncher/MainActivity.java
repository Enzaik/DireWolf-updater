package com.example.quicklauncher;

import android.app.DownloadManager;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ListView myList = (ListView) findViewById(R.id.list_view);
        final String[] lists = {"DireWolf 7.4 Unified", "DireWolf 4.7 Murali"};

        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lists);
        myList.setAdapter(adapt);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DireWolfPaths myDirewolfPaths = new DireWolfPaths();
                String urlNoSlash = myDirewolfPaths.getDirewolf(position);
                String url = urlNoSlash + "/download"; //final URL
                String[] tmpFilename = urlNoSlash.split("DireWolf_v");
                String filename = "DireWolf_v" + tmpFilename[1]; //final filename

                //

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setDescription("Some description");
                request.setTitle(filename);
// in order for this if to run, you must use the android 3.2 to compile your app
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir("sdcard/Downloads", filename);

// get download service and enqueue file
                DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                manager.enqueue(request);

                //



                String myId = lists[position];
                Toast toast = Toast.makeText(getApplicationContext(),
                        "This is " + myId,
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast M = Toast.makeText(this, "Leaving Temporarily DireWolf", Toast.LENGTH_SHORT);
        M.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                String url = "https://sourceforge.net/projects/direwolf/files/DireWolf_v7.4_TP4_Unified_06-03-0045.zip/download";
                String tmp_filename = url.split("https://sourceforge.net/projects/direwolf/files/")[1];
                String filename = tmp_filename.split("/download")[0];

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setDescription("Some descrition");
                request.setTitle(filename);
// in order for this if to run, you must use the android 3.2 to compile your app
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir("sdcard/Downloads", filename);

// get download service and enqueue file
                DownloadManager manager = (DownloadManager) getSystemService(this.DOWNLOAD_SERVICE);
                manager.enqueue(request);

                Toast.makeText(getApplicationContext(), "Downloading DireWolf", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...

                Toast myToastt = Toast.makeText(getApplicationContext(), "No updates2 available", Toast.LENGTH_LONG);
                myToastt.show();
                return true;

            case R.id.action_include_olds:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                ListView myList = (ListView) findViewById(R.id.list_view);
                 String[] lists = {"DireWolf 7.4 Unified", "DireWolf 4.7 Murali"};
                ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lists);
                myList.setAdapter(adapt);

                if (item.isChecked()) {
                    item.setChecked(false);
                  //  String[] listst = {"first", "1", "2", "3", "4", "5", "6", "7"};
                    ArrayAdapter<String> adaptn = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lists);
                    myList.setAdapter(adaptn);
                }
                else {
                    item.setChecked(true);
                    final String[] listss = {"DireWolf 7.4 Unified", "DireWolf 4.7 Murali","DireWolf 7.3 Unified", "DireWolf 4.6 Murali"};
                    ArrayAdapter<String> adaptt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listss);
                    myList.setAdapter(adaptt);

                    myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String myId = listss[position];
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "This is " + myId,
                                    Toast.LENGTH_SHORT);

                            toast.show();
                        }
                    });


                    Toast myToasttt = Toast.makeText(getApplicationContext(), "No updates3 available", Toast.LENGTH_LONG);
                    myToasttt.show();
                    return true;
                }


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
