package com.storm.mynote;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteList implements Serializable {
    private ArrayList<Note> noteList = new ArrayList<>();

    public NoteList(){
//        noteList = new ArrayList<>();
    }

    public void addNote(Note note){
        this.noteList.add(note);
    }

    public void setNote(Note note){
        for(int i = 0; i < noteList.size(); i++){
            if(noteList.get(i).getId().equals(note.getId())){
                noteList.set(i, note);
            }
        }
    }

    public void deleteNote(Note note){
        for(int i = 0; i < noteList.size(); i++){
            if(noteList.get(i).getId().equals(note.getId())){
                noteList.remove(i);
            }
        }
//        noteList.remove(note);
    }

    public Note[] getArray(){
        Note[] noteArray = new Note[noteList.size()];

        for(int i = 0; i < noteList.size(); i++){
            noteArray[i] = noteList.get(i);
        }

        return noteArray;
    }

    @NonNull
    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < noteList.size(); i++){
            res += noteList.get(i) + "----";
        }
        return res;
    }
}
