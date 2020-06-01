package com.storm.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.UUID;

public class AddNoteActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        intent = getIntent();
    }

    public void addNote(View view) {
        String title = ((EditText)findViewById(R.id.title)).getText().toString();
        String content = ((EditText)findViewById(R.id.content)).getText().toString();
        String id = UUID.randomUUID().toString();

        Note note = new Note(id, title, content);

        NoteList noteList = (NoteList) intent.getSerializableExtra("noteList");
        System.out.println("Size before add :" + noteList.getArray().length);
        noteList.addNote(note);
        System.out.println("Size After add :" + noteList.getArray().length);

        System.out.println("noteList: " + noteList);

        String fileName = "noteFiles";
        FileOutputStream fos;
        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fos);
            objectOut.writeObject(noteList);
            objectOut.close();
            fos.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Intent i = new Intent(AddNoteActivity.this, MainActivity.class);
        startActivity(i);
    }
}
