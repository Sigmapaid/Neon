package com.example.neon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.example.neon.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FilenameFilter;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class NotesActivity extends AppCompatActivity {

    ImageView ivBackNotes;
    FloatingActionButton fabAddNote;

    String interlude = "\n\n\n/////-----/////-----/////\n\n\n";

    private String path = Environment.getExternalStorageDirectory().toString() + "/Neon/Notes/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ImageView ivBackNotes = (ImageView) findViewById(R.id.ivBackNotes);
        FloatingActionButton fabAddNote = (FloatingActionButton) findViewById(R.id.fabAddNote);

        ivBackNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, NoteEditorActivity.class);
                startActivity(intent);
            }
        });



    }


    private String getAllContent() {

        try {
            File file = new File(path);
            String[] paths = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".txt");
                }
            });
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<paths.length; i++) {
                stringBuilder.append(Files.toString(new File(path + paths[i])), Charsets.UTF_8);
                stringBuilder.append(interlude);
            }
        return stringBuilder.toString();
        }catch (Exception e) {


        }
        return "";
        }



}
