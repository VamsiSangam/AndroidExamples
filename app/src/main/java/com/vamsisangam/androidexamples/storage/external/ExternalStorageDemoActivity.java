package com.vamsisangam.androidexamples.storage.external;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import com.vamsisangam.androidexamples.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ExternalStorageDemoActivity extends Activity {
    TextView txtMessage;
    private static final int REQUEST_CODE_REQUEST_EXTERNAL_STORAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage_demo);
        init();

        // if API level >= 23, you need to request permission at runtime
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                // if permission doesn't already exists request for it
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_REQUEST_EXTERNAL_STORAGE);
            } else {
                txtMessage.setText("Write external storage permission already granted.");
            }
        } else {
            txtMessage.setText("Runtime permission not needed - API level - " + Build.VERSION.SDK_INT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_REQUEST_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                txtMessage.setText("Write to external storage, permission granted!");
                txtMessage.setTextColor(Color.GREEN);
            } else {
                txtMessage.setText("Write to external storage, permission REVOKED!");
                txtMessage.setTextColor(Color.RED);
            }
        }
    }

    private void init() {
        txtMessage = (TextView) findViewById(R.id.txtMessage);
    }

    public void checkAvailability(View v) {
        String state = Environment.getExternalStorageState();
        txtMessage.setTextColor(Color.BLACK);

        txtMessage.setText("State - " + state);

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            txtMessage.append(", media is mounted.");
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            txtMessage.append(", media is mounted but read only.");
        } else {
            txtMessage.append("Not available");
            txtMessage.setTextColor(Color.RED);
        }
    }

    public void createNewDirectory(View v) {
        try {
            File dir = Environment.getExternalStorageDirectory();
            File newDir = new File(dir, "demo");

            txtMessage.setText("getExternalStorageDirectory() path = " + dir.getAbsolutePath() + "\n");
            txtMessage.append("New folder directory = " + newDir.getAbsolutePath() + "\n");

            if (!newDir.exists()) {
                if (newDir.mkdir()) {
                    txtMessage.append("New directory created successfully!\n");
                } else {
                    txtMessage.append("New directory could not be created now!\n");
                }
            } else {
                txtMessage.append("New directory already present!\n");
            }
        } catch (Exception ex) {
            txtMessage.append("Exception - " + ex.getMessage());
        }
    }

    public void createNewFile(View v) {
        try {
            File dir = getExternalFilesDir(null);
            File newFile = new File(dir, "numbers.txt");

            txtMessage.setText("getExternalFilesDir() path = " + dir.getAbsolutePath() + "\n");
            txtMessage.append("New file path = " + newFile.getAbsolutePath() + "\n");

            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));

            for (int i = 1; i <= 15; ++i) {
                bw.write(Integer.toString(i * i * i) + "\n");
            }

            bw.close();
            txtMessage.append("Written to file successfully.");
        } catch (Exception ex) {
            txtMessage.append("Exception - " + ex.getMessage());
        }
    }

    public void listFiles(View v) {
        try {
            File dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

            txtMessage.setText("Environment.DIRECTORY_DCIM" + Environment.DIRECTORY_DCIM + "\n");
            txtMessage.append("DCIM folder path = " + dcim.getAbsolutePath() + "\n");

            File camera = new File(dcim, "camera");

            if (camera.exists()) {
                txtMessage.append("\"camera\" folder exists in path = "
                           + camera.getAbsolutePath() + "\n");

                txtMessage.append("Listing all files in dcim folder -\n");
                for (String file : dcim.list()) {
                    txtMessage.append(file + "\n");
                }

                txtMessage.append("Listing all files in camera folder -\n");
                for (String file : camera.list()) {
                    txtMessage.append(file + "\n");
                }
            } else {
                txtMessage.append("\"camera\" folder does not exist!");
            }
        } catch (Exception ex) {
            txtMessage.append("Exception - " + ex.getMessage());
        }
    }
}
