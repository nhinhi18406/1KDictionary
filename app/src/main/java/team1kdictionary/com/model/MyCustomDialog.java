package team1kdictionary.com.model;

import android.app.Activity;
import android.app.Dialog;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Locale;

import team1kdictionary.com.onekdictionary.R;

public class MyCustomDialog extends Dialog {
    ImageView imgFav, imgSound;
    TextView tvWord, tvInfo, txtClose;
    Button btnTextToSpeech;

    TextToSpeech textToSpeech;
    Integer dem = 0;

    Activity context;

    public MyCustomDialog(@NonNull Activity context) {
        super(context);
        this.context = context;
        setContentView(R.layout.custom_dialog);
        addControls();
        addEvents();
    }

    private void addEvents() {
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dem == 0) {
                    imgFav.setImageResource(R.drawable.ic_favorite_white);
                    dem += 1;
                } else if (dem == 1) {
                    imgFav.setImageResource(R.drawable.ic_favorite_border_white);
                    dem -= 1;
                }
            }
        });


        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = tvWord.getText().toString();
                int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH,
                        null);
            }
        });


    }

    private void addControls() {
        imgFav = findViewById(R.id.imgFav);
        imgSound = findViewById(R.id.imgSound);
        tvWord = findViewById(R.id.tvWord);
        tvInfo = findViewById(R.id.tvInfo);
        txtClose = findViewById(R.id.txtClose);
        btnTextToSpeech = findViewById(R.id.btnTestSpeak);
        setTitle("Word");
        setCanceledOnTouchOutside(true);

        textToSpeech = new TextToSpeech(context.getApplicationContext()
                , new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }
}
