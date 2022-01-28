package sttsoft.com.br.video_player.Player.Utils.views;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BaseActivity extends AppCompatActivity {

    private ViewModelProvider viewModelProvider;

    public ViewModelProvider getViewModelProvider() {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(this);
        }
        return viewModelProvider;
    }

    @NonNull
    @MainThread
    public <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        return getViewModelProvider().get(modelClass);
    }

}
