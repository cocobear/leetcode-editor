package com.shuzijun.leetcode.plugin.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.shuzijun.leetcode.plugin.model.LeetcodeEditor;
import com.shuzijun.leetcode.plugin.setting.ProjectConfig;

/**
 * @author shuzijun
 */
public class EditorMenuActionGroup extends DefaultActionGroup {

    @Override
    public void update(AnActionEvent e) {
        VirtualFile vf = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        LeetcodeEditor leetcodeEditor = ProjectConfig.getInstance(e.getProject()).getEditor(vf.getPath());
        boolean menuAllowed = false;
        if (leetcodeEditor != null) {
            menuAllowed = true;
        }
        e.getPresentation().setEnabledAndVisible(menuAllowed);
    }
}
