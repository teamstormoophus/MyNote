package com.storm.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.storm.mynote.fragment.NoteFragment;
import com.storm.mynote.fragment.TaskFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private NoteList noteList = new NoteList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabsLayout);

        ViewPagerApdater adapter = new ViewPagerApdater(getSupportFragmentManager());
        adapter.addFragment(new NoteFragment(), "Note");
        adapter.addFragment(new TaskFragment(), "Task");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void addNotes(View view) {
        Intent activity2Intent = new Intent(this.getApplicationContext(), AddNoteActivity.class);
        activity2Intent.putExtra("noteList", this.noteList);
        startActivity(activity2Intent);
    }

    public void setNoteList(NoteList noteList){
        this.noteList = noteList;
    }

    public NoteList getNoteList(){
        return this.noteList;
    }
}
