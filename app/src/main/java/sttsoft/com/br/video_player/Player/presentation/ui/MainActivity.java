package sttsoft.com.br.video_player.Player.presentation.ui;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import sttsoft.com.br.video_player.Player.Utils.views.BaseActivity;
import sttsoft.com.br.video_player.Player.presentation.viewmodel.MainViewModel;
import sttsoft.com.br.video_player.R;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;
    private EditText edtBarCode;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = getViewModel(MainViewModel.class);

        bindComponents();
        setupObservables();
        setupVideo(R.raw.loop);
        keyboardListener();
    }

    private void bindComponents() {
        edtBarCode = findViewById(R.id.edtBarCode);
        videoView = (VideoView) findViewById(R.id.videoView);
    }

    private void setupObservables() {
        viewModel.getChangeVideo().observe(this, change -> {
            if (change) {
                setupVideo(R.raw.condition);
            }
        });
    }

    private void setupVideo(@RawRes int video) {
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+video);
        videoView.setVideoURI(uri);
        videoView.start();
        if (video == R.raw.loop) {
            videoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
        } else {
            videoView.setOnCompletionListener(mediaPlayer -> setupVideo(R.raw.loop));
        }
    }

    private void keyboardListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftKeyboard(edtBarCode);
            }
        }, 300);

        edtBarCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void sendMessage() {
        Toast.makeText(this, "BATATA", Toast.LENGTH_LONG).show();
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }


}