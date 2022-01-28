package sttsoft.com.br.video_player.Player.business;

import java.util.ArrayList;

public class MainBusinessModel {

    private static MainBusinessModel instance;
    private ArrayList<String> codes = new ArrayList<>();

    public static MainBusinessModel getInstance() {
        if (instance == null) {
            instance = new MainBusinessModel();
        }
        return instance;
    }

    public static void clearInstance() { instance = null; }

    // Regras de negocio
    public void setCodes() {
        codes.add("7896045505357");
        codes.add("78935495");
        codes.add("7896045523412");
        codes.add("7896045506064");
        codes.add("8712000025649");
        codes.add("7896045506040");
        codes.add("78936683");
        codes.add("78905498");
        codes.add("7896045505869");
        codes.add("7896045501038");
        codes.add("7896045505364");
        codes.add("7896045506057");
        codes.add("7896045505371");
        codes.add("7896045506071");
        codes.add("7896045506248");
        codes.add("7896045506217");
        codes.add("7896045502196");
        codes.add("7896045506347");
    }

    public boolean verifyCode(String code) {
        for (String c: codes) {
            if (c.equals(code)) {
                return true;
            }
        }
        return false;
    }
}
