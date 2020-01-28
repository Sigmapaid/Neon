package com.example.neon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.neon.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class NoteEditorActivity extends AppCompatActivity {

    private String path = Environment.getExternalStorageDirectory().toString() + "/Neon/Notes";
    private final int MEMORY_ACCESS = 5;

//    File Root = Environment.getExternalStorageDirectory();


    private ImageView ivBackNotes;
    private ImageView btnSaveNote;
    private EditText etNoteTitle;
    private EditText etNoteEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        ivBackNotes = (ImageView) findViewById(R.id.ivBackNotes);
        btnSaveNote = (ImageView) findViewById(R.id.btnSaveNote);
        etNoteTitle = (EditText) findViewById(R.id.etNoteTitle);
        etNoteEditor = (EditText) findViewById(R.id.etNoteEditor);

//        Log.d("getAbsolutePath", Root.getAbsolutePath());
//        Log.d("getAbsoluteFile", String.valueOf(Root.getAbsoluteFile()));

        if(ActivityCompat.shouldShowRequestPermissionRationale(NoteEditorActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){}
//        if(ActivityCompat.checkSelfPermission(NoteEditorActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){}
        else {
            ActivityCompat.requestPermissions(NoteEditorActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_ACCESS);
        }

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDir();
                createFile();
                finish();
            }
        });

        ivBackNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteEditorActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MEMORY_ACCESS:
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {

                }
                else {
                    Toast.makeText(getApplicationContext(), "Jeśli nie zostanie wyrażona zgoda na dostęp do pamięci nie będzie możliwości zapisania pliku.",Toast.LENGTH_LONG).show();
                }
        }
    }

    public void createDir() {
        File directory = new File(path);
        if (!directory.exists()) {
            try {
                directory.mkdirs();

            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    public void createFile() {
        File file;
        if (etNoteTitle.getText().toString().isEmpty()) {
            file = new File(path + "/" + System.currentTimeMillis() + ".txt");
        }
        else {
            file = new File(path + "/" + etNoteTitle.getText().toString() + ".txt");

        }
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        try {
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(etNoteEditor.getText().toString());
            outputStreamWriter.close();
            fileOutputStream.close();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            Log.i("blad zapisu", e.toString());
        }

    }
}
