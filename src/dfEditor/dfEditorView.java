/*
 * dfEditorView.java
 */

package dfEditor;

import com.DeskMetrics.DeskMetrics;
import dfEditor.animation.AnimationController;
import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import dfEditor.command.CommandManager;
import javax.swing.JFileChooser;
import dfEditor.io.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * The application's main frame.
 */
public class dfEditorView extends FrameView implements TaskChangeListener, org.jdesktop.application.Application.ExitListener
{
    private JFileChooser fileChooser;

    public dfEditorView(dfEditorApp app) {
        super(app);

        fileChooser = new JFileChooser();
        
        initComponents();
        
        //feedbackMenu.setVisible(false);

        checkRegistered();

        if (dfEditorApp.isFreeVersion)
        {
            //this.showAboutBox();
        }
        else
        {
            if (! dfEditorApp.getApplication().isRegistered())// && dfEditorApp.getApplication().getDaysRemaining() < 10)
            {
                this.showAboutBox();
            }
        }

        java.net.URL imgURL = this.getClass().getResource("resources/main_icons/Star.png");
        ImageIcon ii = new ImageIcon(imgURL);
        this.getFrame().setIconImage(ii.getImage());

        this.getFrame().addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                dfEditorApp.closeDeskMetrics();
                System.exit(0);
            }
        });
        
    }

    public void checkRegistered()
    {
        if (dfEditorApp.isFreeVersion)
        {
            helpLabel.setText("Please visit http://www.darkfunction.com for updates!");
        }
        else
        {
            if (dfEditorApp.getApplication().isRegistered())
            {
                helpLabel.setText("darkFunction Editor: Licensed version.");
            }
            else
            {
                long remainingDays = dfEditorApp.getApplication().getDaysRemaining();
                if (remainingDays > 0)
                {
                    helpLabel.setText("Unregistered trial: " + remainingDays + " days remaining. Licenses can be purchased at http://www.darkfunction.com");
                }
                else
                {
                    helpLabel.setText("Trial period expired. Licenses can be purchased at http://www.darkfunction.com");
                }
            }
        }
    }

    public void willExit(java.util.EventObject aObj)
    {

    }

    public boolean canExit(java.util.EventObject aObj)
    {
        for (int i=0; i<tabbedPane.getTabCount(); ++i)
        {
            dfEditorTask tab = (dfEditorTask)(tabbedPane.getComponentAt(i));

            if (!tab.hasBeenModified())
            {
                continue;
            }

            String[] choices = {" Save ", " Discard ", " Cancel "};

            String msg = "You have not saved " + tab.getName() + ". Would you like to save it now?";
            int choice = JOptionPane.showOptionDialog(
                               this.getFrame()                   // Center in window.
                             , msg                          // Message
                             , "Save changes?"                  // Title in titlebar
                             , JOptionPane.YES_NO_OPTION    // Option type
                             , JOptionPane.WARNING_MESSAGE    // messageType
                             , null                         // Icon (none)
                             , choices                      // Button text as above.
                             , " Save "      // Default button's label
                           );
            switch(choice)
            {
                case 0:
                    if (tab.save())
                        tabbedPane.remove(i);
                    else
                    {
                        return false;
                    }
                    break;
                case 1:
                    tabbedPane.remove(i);
                    i--;
                    break;
                case 2:
                    return false;
                default:
                    return false;
            }
        }
        return true;
    }


    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = dfEditorApp.getApplication().getMainFrame();
            if (dfEditorApp.isFreeVersion)
                aboutBox = new dfEditorAboutBoxFree(mainFrame);
            else
                aboutBox = new dfEditorAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        dfEditorApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        newSpritesheetItem = new javax.swing.JMenuItem();
        newAnimationItem = new javax.swing.JMenuItem();
        loadMenuItem = new javax.swing.JMenuItem();
        menuItemSave = new javax.swing.JMenuItem();
        menuItemSaveAs = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        feedbackMenu = new javax.swing.JMenu();
        feedbackMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        helpLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('F');
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(dfEditor.dfEditorApp.class).getContext().getResourceMap(dfEditorView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jMenu2.setMnemonic('N');
        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        newSpritesheetItem.setText(resourceMap.getString("newSpritesheetItem.text")); // NOI18N
        newSpritesheetItem.setName("newSpritesheetItem"); // NOI18N
        newSpritesheetItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSpritesheetItemActionPerformed(evt);
            }
        });
        jMenu2.add(newSpritesheetItem);

        newAnimationItem.setText(resourceMap.getString("newAnimationItem.text")); // NOI18N
        newAnimationItem.setName("newAnimationItem"); // NOI18N
        newAnimationItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAnimationItemActionPerformed(evt);
            }
        });
        jMenu2.add(newAnimationItem);

        fileMenu.add(jMenu2);

        loadMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadMenuItem.setMnemonic('O');
        loadMenuItem.setText(resourceMap.getString("loadMenuItem.text")); // NOI18N
        loadMenuItem.setName("loadMenuItem"); // NOI18N
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadMenuItem);

        menuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSave.setMnemonic('S');
        menuItemSave.setText(resourceMap.getString("menuItemSave.text")); // NOI18N
        menuItemSave.setEnabled(false);
        menuItemSave.setName("menuItemSave"); // NOI18N
        menuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemSave);

        menuItemSaveAs.setMnemonic('a');
        menuItemSaveAs.setText(resourceMap.getString("menuItemSaveAs.text")); // NOI18N
        menuItemSaveAs.setEnabled(false);
        menuItemSaveAs.setName("menuItemSaveAs"); // NOI18N
        menuItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveAsActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemSaveAs);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(dfEditor.dfEditorApp.class).getContext().getActionMap(dfEditorView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('E');
        editMenu.setText(resourceMap.getString("editMenu.text")); // NOI18N
        editMenu.setName("editMenu"); // NOI18N

        undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenuItem.setMnemonic('U');
        undoMenuItem.setText(resourceMap.getString("undoMenuItem.text")); // NOI18N
        undoMenuItem.setEnabled(false);
        undoMenuItem.setName("undoMenuItem"); // NOI18N
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(undoMenuItem);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setMnemonic('R');
        redoMenuItem.setText(resourceMap.getString("redoMenuItem.text")); // NOI18N
        redoMenuItem.setEnabled(false);
        redoMenuItem.setName("redoMenuItem"); // NOI18N
        redoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(redoMenuItem);

        menuBar.add(editMenu);

        helpMenu.setAction(actionMap.get("showAboutBox")); // NOI18N
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        feedbackMenu.setText(resourceMap.getString("feedbackMenu.text")); // NOI18N
        feedbackMenu.setName("feedbackMenu"); // NOI18N

        feedbackMenuItem.setText(resourceMap.getString("feedbackMenuItem.text")); // NOI18N
        feedbackMenuItem.setName("feedbackMenuItem"); // NOI18N
        feedbackMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feedbackMenuItemActionPerformed(evt);
            }
        });
        feedbackMenu.add(feedbackMenuItem);

        menuBar.add(feedbackMenu);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(800, 20));

        helpLabel.setText(resourceMap.getString("helpLabel.text")); // NOI18N
        helpLabel.setName("helpLabel"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        helpLabel.getAccessibleContext().setAccessibleName(resourceMap.getString("helpLabel.AccessibleContext.accessibleName")); // NOI18N

        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setMinimumSize(new java.awt.Dimension(5, 5));
        mainPanel.setName("mainPanel"); // NOI18N

        tabbedPane.setName("tabbedPane"); // NOI18N
        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    

   
    private void menuItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveAsActionPerformed
        dfEditorTask currentTab = (dfEditorTask)tabbedPane.getSelectedComponent();

        if (currentTab != null)
        {
            if (currentTab.saveAs())
            {
                java.io.File file = currentTab.getSavedFile();
                if (file != null && file.exists())
                {
                    tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), file.getName());   
                }
            }    
        }            
    }//GEN-LAST:event_menuItemSaveAsActionPerformed

    

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        ((dfEditorTask)tabbedPane.getSelectedComponent()).undo();
    }//GEN-LAST:event_undoMenuItemActionPerformed

    private void redoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuItemActionPerformed
        ((dfEditorTask)tabbedPane.getSelectedComponent()).redo();
    }//GEN-LAST:event_redoMenuItemActionPerformed

    private void addTab(java.awt.Component c)
    {
        tabbedPane.add(c);
        tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(c), new TabComponent(tabbedPane));
        tabbedPane.setSelectedComponent(c);
    }

    private void newSpritesheetItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSpritesheetItemActionPerformed
        //Custom button text
     
        JFrame frame = this.getFrame();
        SingleOrMultiDialog dialog = new SingleOrMultiDialog(frame, true);
        dialog.setLocationRelativeTo(frame);

        int result = dialog.showDialog();

        dfEditorPanel panel = null;
        switch (result)
        {
            case 0:
            {
                panel = new SpritesheetController(new CommandManager(undoMenuItem, redoMenuItem), true, helpLabel, this, fileChooser);
                break;
            }
            case 1:
            {
                panel = new SpriteImageController(new CommandManager(undoMenuItem, redoMenuItem), helpLabel, this, fileChooser);
                break;
            }
        }

        if (panel != null)
            addTab(panel);

}//GEN-LAST:event_newSpritesheetItemActionPerformed

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed
        load();
    }//GEN-LAST:event_loadMenuItemActionPerformed

    public void load()
    {
        JFileChooser chooser = fileChooser;

        CustomFilter filter = new CustomFilter();
        filter.addExtension(CustomFilter.EXT_ANIM);
        filter.addExtension(CustomFilter.EXT_SPRITE);
        chooser.resetChoosableFileFilters();
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Load a spritesheet / animation set");
        JFrame mainFrame = dfEditorApp.getApplication().getMainFrame();
        int returnVal = chooser.showOpenDialog(mainFrame);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            java.io.File selectedFile = chooser.getSelectedFile();

            if (selectedFile != null && selectedFile.exists())
            {
                java.awt.Component task = null;

                boolean bLoaded = false;
                if (Utils.getExtension(selectedFile).equals(CustomFilter.EXT_ANIM)) // meh
                {
                    AnimationController animController = new AnimationController(new CommandManager(undoMenuItem, redoMenuItem), false, helpLabel, this, fileChooser);
                    bLoaded = animController.load(new AnimationSetReader(selectedFile));                    
                    task = animController;
                    if (helpLabel != null)
                        helpLabel.setText("Loaded animations " + selectedFile.toString());
                }
                else if (Utils.getExtension(selectedFile).equals(CustomFilter.EXT_SPRITE))
                {
                    JFrame frame = this.getFrame();
                    SingleOrMultiDialog dialog = new SingleOrMultiDialog(frame, true);
                    dialog.setLocationRelativeTo(frame);
                                        
                    SpritesheetReader reader = new SpritesheetReader(selectedFile);
                    String imagePath = reader.getImagePath();
                    DefaultTreeModel model = reader.getTreeModel();         

                    int result = dialog.showDialog();
                    
                    switch (result)
                    {
                        case 0:
                        {
                            SpritesheetController spriteSheet = new SpritesheetController(new CommandManager(undoMenuItem, redoMenuItem), false, helpLabel, this, fileChooser);
                            bLoaded = spriteSheet.load(imagePath, model);
                            task = spriteSheet;
                            break;
                        }
                        case 1:
                        {
                            SpriteImageController spriteSheet = new SpriteImageController(new CommandManager(undoMenuItem, redoMenuItem), helpLabel, this, fileChooser);
                            bLoaded = spriteSheet.load(imagePath, model);
                            task = spriteSheet;
                            break;
                        }
                    }                              

                    if (helpLabel != null)
                        helpLabel.setText("Loaded spritesheet " + selectedFile.toString());
                }

                if (bLoaded && task != null)
                {
                    addTab(task);
                    ((dfEditorTask)task).setSavedFile(selectedFile);
                    tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), selectedFile.getName());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(
                   null,
                   "No such file exists",
                   "File not found",
                   JOptionPane.ERROR_MESSAGE);            
            }
        }
    }

    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        updateMenuBar();
    }//GEN-LAST:event_tabbedPaneStateChanged

    private void newAnimationItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAnimationItemActionPerformed
        AnimationController animationSet = new AnimationController(new CommandManager(undoMenuItem, redoMenuItem), true, this.helpLabel, this, fileChooser);
        addTab(animationSet);
    }//GEN-LAST:event_newAnimationItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        showAboutBox();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void menuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveActionPerformed
        dfEditorTask currentTab = (dfEditorTask)tabbedPane.getSelectedComponent();

        if (currentTab != null)
        {
            if (currentTab.save())
            {
                java.io.File file = currentTab.getSavedFile();
            
                if (file != null && file.exists())                            
                {                
                    String saveName = file.getName();
                    if (saveName != null && saveName.length() > 0)
                    {
                        tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), saveName);
                    }
                }
            }                
        }
    }//GEN-LAST:event_menuItemSaveActionPerformed

private void feedbackMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedbackMenuItemActionPerformed
    this.showFeedbackForm();
}//GEN-LAST:event_feedbackMenuItemActionPerformed

    public void showFeedbackForm() 
    {
        JFrame mainFrame = dfEditorApp.getApplication().getMainFrame();
        FeedbackDialog feedback = new FeedbackDialog(mainFrame, true);
        feedback.setLocationRelativeTo(mainFrame);

        dfEditorApp.getApplication().show(feedback);
    }

    private void updateMenuBar()
    {
        dfEditorTask selectedTask = (dfEditorTask)(tabbedPane.getSelectedComponent());

        if (selectedTask != null)
        {
            String savedName = null;
            if (selectedTask.getSavedFile() != null)
                savedName = selectedTask.getSavedFile().getName();
            
            if (selectedTask.hasBeenModified())
            {
                if (savedName != null)
                    tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(),  "*" + savedName);

                menuItemSaveAs.setEnabled(true);
                menuItemSave.setEnabled(true);
            }
            else
            {
                menuItemSaveAs.setEnabled(false);
                menuItemSave.setEnabled(false);
            }

            selectedTask.refreshCommandManagerButtons();
        }
    }

    public void taskChanged(dfEditorTask aTask)
    {
        // TODO: this ignores arg and uses current task
        updateMenuBar();
    }

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu feedbackMenu;
    private javax.swing.JMenuItem feedbackMenuItem;
    private javax.swing.JLabel helpLabel;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JMenuItem menuItemSaveAs;
    private javax.swing.JMenuItem newAnimationItem;
    private javax.swing.JMenuItem newSpritesheetItem;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JMenuItem undoMenuItem;
    // End of variables declaration//GEN-END:variables

    private JDialog aboutBox;



}



