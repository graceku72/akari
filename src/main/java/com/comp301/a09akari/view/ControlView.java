package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.Controller;
import javafx.scene.Parent;

public class ControlView implements FXComponent {
    private Controller controller;
    public ControlView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        return null;
    }
}
