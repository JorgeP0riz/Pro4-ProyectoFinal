package chatGlobal;

import javax.swing.*;

public class FrameChatG extends JFrame {

    public FrameChatG() {
        this.setSize(300,350);
        init();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void init() {
        ChatGlobal panelChat = new ChatGlobal();
        this.add(panelChat);
    }
}
