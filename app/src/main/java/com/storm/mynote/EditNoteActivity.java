package com.storm.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EditNoteActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        intent = getIntent();
        Note note = (Note) intent.getSerializableExtra("note");

        EditText title = findViewById(R.id.title);
        EditText content = findViewById(R.id.content);
        title.setText(note.getTitle());
        content.setText(note.getContent());

    }

    public void editNote(View view) {
        String title = ((EditText)findViewById(R.id.title)).getText().toString();
        String content = ((EditText)findViewById(R.id.content)).getText().toString();

        Note note = (Note) intent.getSerializableExtra("note");
        note.setTitle(title);
        note.setContent(content);

        String fileName = "noteFiles";
        NoteList noteList = new NoteList();
        try {
            FileInputStream inputFile = openFileInput(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(inputFile);

            noteList = (NoteList) objectIn.readObject();

            objectIn.close();
            inputFile.close();
        } catch (FileNotFoundException e){
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        noteList.setNote(note);

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

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void deleteNote(View view) {
        Note note = (Note) intent.getSerializableExtra("note");

        String fileName = "noteFiles";
        NoteList noteList = new NoteList();
        try {
            FileInputStream inputFile = openFileInput(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(inputFile);

            noteList = (NoteList) objectIn.readObject();

            objectIn.close();
            inputFile.close();
        } catch (FileNotFoundException e){
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        noteList.deleteNote(note);

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

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
