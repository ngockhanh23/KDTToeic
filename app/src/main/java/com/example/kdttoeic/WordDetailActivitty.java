package com.example.kdttoeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kdttoeic.model.Word;

public class WordDetailActivitty extends AppCompatActivity {
    TextView tvEn, tvSpell, tvVe, tvEx;
    ImageView ivDecribe, ivAudio;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        Intent i = getIntent();
        Word word = (Word) i.getSerializableExtra("word");

        tvEn = findViewById(R.id.tvEn);
        tvSpell = findViewById(R.id.tvSpell);
        tvVe = findViewById(R.id.tvVe);
        tvEx = findViewById(R.id.tvEx);
        ivDecribe = findViewById(R.id.ivDecribe);
        ivAudio = findViewById(R.id.ivAudio);

        tvEn.setText(word.getEn());
        tvSpell.setText("/" + word.getSpell() + "/");
        tvVe.setText(word.getVe());
        tvEx.setText(word.getExample());
        ivDecribe.setImageBitmap(Utils.convertToBitmapFromAssets(WordDetailActivitty.this, word.getImage()));
        ivAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AssetFileDescriptor afd = getAssets().openFd("audio/" + word.getAudio());
                    //Khởi tạo Media Player
                    if (player == null) {
                        player = new MediaPlayer();
                    }
                    player.stop();
                    player.reset();
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    afd.close();
                    player.prepare();
                    player.start();
                } catch (Exception ex) {
                    Toast.makeText(WordDetailActivitty.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}