package com.liuchuanzheng.study_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import tech.linjiang.pandora.Pandora;

public class MainActivity extends AppCompatActivity {
    Button   btn_add;
    Button   btn_delete;
    Button   btn_update;
    Button   btn_query;
    TextView et_content;
    TextView tv_result;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "liuchuanzheng1.db").allowMainThreadQueries().build();
    }

    private void initView() {
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        btn_query = findViewById(R.id.btn_query);
        et_content = findViewById(R.id.et_content);
        tv_result = findViewById(R.id.tv_result);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_content.getText().toString().trim().isEmpty()) {
                    User user = new User();
                    user.firstName = "爱新觉罗";
                    user.lastName = et_content.getText().toString().trim();
                    db.userDao().insertAll(user);
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_content.getText().toString().trim().isEmpty()) {
                    User user =db.userDao().findByName("爱新觉罗","刘传政");
                    if (user != null) {
                        db.userDao().delete(user);
                    }

                }
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_content.getText().toString().trim().isEmpty()) {
                    User user = db.userDao().findByName("爱新觉罗", et_content.getText().toString().trim());
                    if (user != null) {
                        user.lastName = "刘传政";
                        db.userDao().update(user);
                    }

                }

            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder s = new StringBuilder();
                List<User> all = db.userDao().getAll();
                for (User user : all) {
                    s.append(user.toString()+"\n");

                }
                tv_result.setText(s);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开启调试控制台
        Pandora.get().open();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
