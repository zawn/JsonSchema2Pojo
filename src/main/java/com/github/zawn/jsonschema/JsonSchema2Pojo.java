package com.github.zawn.jsonschema;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.ex.WindowManagerEx;
import com.intellij.openapi.wm.impl.IdeFrameImpl;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Idea Action.
 */
public class JsonSchema2Pojo extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }


    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        JSONOptions form = new JSONOptions(project);
        form.setTitle("JSON POJO Generator");
        form.pack();
        form.setLocationRelativeTo(getParentWindow(project));
        form.setResizable(true);
        form.setModal(true);
        form.setVisible(true);
    }

    private Window getParentWindow(Project project) {
        WindowManagerEx windowManager = (WindowManagerEx) WindowManager.getInstance();

        Window window = windowManager.suggestParentWindow(project);
        if (window == null) {
            Window focusedWindow = windowManager.getMostRecentFocusedWindow();
            if (focusedWindow instanceof IdeFrameImpl) {
                window = focusedWindow;
            }
        }
        return window;
    }
}
