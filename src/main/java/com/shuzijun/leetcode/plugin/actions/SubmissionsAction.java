package com.shuzijun.leetcode.plugin.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.shuzijun.leetcode.plugin.manager.SubmissionManager;
import com.shuzijun.leetcode.plugin.manager.ViewManager;
import com.shuzijun.leetcode.plugin.model.Config;
import com.shuzijun.leetcode.plugin.model.Question;
import com.shuzijun.leetcode.plugin.model.Submission;
import com.shuzijun.leetcode.plugin.utils.DataKeys;
import com.shuzijun.leetcode.plugin.window.SubmissionsPanel;
import com.shuzijun.leetcode.plugin.window.WindowFactory;

import javax.swing.*;
import java.util.List;

/**
 * @author shuzijun
 */
public class SubmissionsAction extends AbstractAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent, Config config) {
        JTree tree = WindowFactory.getDataContext(anActionEvent.getProject()).getData(DataKeys.LEETCODE_PROJECTS_TREE);
        Question question = ViewManager.getTreeQuestion(tree, anActionEvent.getProject());
        if(question == null){
            return;
        }
        List<Submission> submissionList = SubmissionManager.getSubmissionService(question,anActionEvent.getProject());
        if (submissionList == null || submissionList.isEmpty()) {
            return;
        }
        SubmissionsPanel.TableModel tableModel = new SubmissionsPanel.TableModel(submissionList);
        SubmissionsPanel dialog = new SubmissionsPanel(anActionEvent.getProject(), tableModel);
        dialog.setTitle(question.getFormTitle() + " Submissions");

        if (dialog.showAndGet()) {
            SubmissionManager.openSubmission(submissionList.get(dialog.getSelectedRow()), question, anActionEvent.getProject());
        }
    }

}
