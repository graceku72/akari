package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.Controller;
import javafx.scene.Parent;

public class MessageView implements FXComponent {
    private Controller controller;
    public MessageView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        return null;
    }
}
