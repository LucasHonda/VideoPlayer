package sttsoft.com.br.video_player.Player.presentation.ui;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        bindComponents();
        setupObservables();
        setupVideo(R.raw.loop);
        keyboardListener();
        setupVideo();
    }

    private void bindComponents() {
        edtBarCode = findViewById(R.id.edtBarCode);
        videoView = findViewById(R.id.videoView);
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
            videoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(false));
            videoView.setOnCompletionListener(mediaPlayer -> setupVideo(R.raw.loop));
        }
    }

    private void keyboardListener() {
        edtBarCode.setEnabled(true);
        new Handler().postDelayed(() -> showSoftKeyboard(edtBarCode), 300);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void setupVideo() {
        edtBarCode.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                viewModel.changeVideo(textView.getText().toString());
                textView.setText("");
                keyboardListener();
            }
            return false;
        });
    }
}