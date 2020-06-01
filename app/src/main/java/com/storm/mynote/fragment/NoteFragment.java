package com.storm.mynote.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.storm.mynote.AddNoteActivity;
import com.storm.mynote.EditNoteActivity;
import com.storm.mynote.MainActivity;
import com.storm.mynote.Note;
import com.storm.mynote.NoteList;
import com.storm.mynote.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class NoteFragment extends Fragment {
    private View view;
    private ListView listView;

    public NoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_note, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String fileName = "noteFiles";
        NoteList noteList = new NoteList();
        try {
            FileInputStream inputFile = getActivity().openFileInput(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(inputFile);

            noteList = (NoteList) objectIn.readObject();

            ((MainActivity)this.getActivity()).setNoteList(noteList);

            objectIn.close();
            inputFile.close();
        } catch (FileNotFoundException e){
//            noteList.addNote(new Note("Hello", "every"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        listView = view.findViewById(R.id.listview);

        ArrayAdapter<Note> noteArrayAdapter = new ArrayAdapter<Note>(getActivity(), android.R.layout.simple_list_item_activated_1, noteList.getArray());

        listView.setAdapter(noteArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), EditNoteActivity.class);

                Note note = (Note) parent.getItemAtPosition(position);
                i.putExtra("note", note);

                startActivity(i);
            }
        });
        System.out.println("Length: " + noteList.getArray().length);
    }
}
