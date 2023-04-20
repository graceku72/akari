package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.Controller;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;

public class MessageView implements FXComponent, ModelObserver {
    private Model model;
    private ClassicMvcController controller;
    public MessageView(Model model, ClassicMvcController controller) {
        this.model = model;
        this.controller = controller;
    }

    @Override
    public Parent render() {
        return null;
        // is it even necessary to have this class??
    }

    @Override
    public void update(Model model) {
        // dont need this
    }
}
