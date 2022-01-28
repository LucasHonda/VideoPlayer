package sttsoft.com.br.video_player.Player.business;

public class MainBusinessModel {

    private static MainBusinessModel instance;

    public static MainBusinessModel getInstance() {
        if (instance == null) {
            instance = new MainBusinessModel();
        }
        return instance;
    }

    public static void clearInstance() { instance = null; }

    // Regras de negocio


}
