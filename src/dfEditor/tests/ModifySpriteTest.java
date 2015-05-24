package dfEditor.tests;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;

import org.approvaltests.Approvals;
import org.junit.Test;

import com.spun.util.ThreadUtils;

import dfEditor.dfEditorApp;
import dfEditor.dfEditorView;
import dfEditor.animation.AnimationController;

public class ModifySpriteTest
{
  @Test
  public void test() throws Exception
  {
    // Put a sprite on the canvas
    AnimationController spriteSheet = openSpriteSheet("TestingResources/StarWarsImages/Llewellyn.anim");
    // Change the angle
    spriteSheet.spriteList.setSelectedIndex(0);
    JSpinner angleSpinner = spriteSheet.angleSpinner;
    JFormattedTextField textField = ((DefaultEditor) angleSpinner.getEditor()).getTextField();
    while (!textField.isFocusOwner())
    {
      System.out.println("focus = " + textField.isFocusOwner());
      textField.requestFocus();
    }
    Robot r = new Robot();
    r.setAutoDelay(40);
    r.keyPress(KeyEvent.VK_4);
    r.keyPress(KeyEvent.VK_ENTER);
    ThreadUtils.sleep(3000);
    spriteSheet.spriteList.clearSelection();
    spriteSheet.spriteList.setSelectedIndex(1);
    spriteSheet.spriteList.setSelectedIndex(0);
    assertEquals(4, angleSpinner.getValue());
    Approvals.verify(spriteSheet);
  }
  private AnimationController openSpriteSheet(String file)
  {
    File selectedFile = new File(file);
    AnimationController load = (AnimationController) getDfEditor().load(selectedFile);
    return load;
  }
  public static dfEditorView getDfEditor()
  {
    dfEditorApp.main(new String[0]);
    dfEditorApp application = null;
    dfEditorView viewer = null;
    while (viewer == null)
    {
      application = dfEditorApp.getApplication();
      viewer = application.getViewer();
      ThreadUtils.sleep(100);
    }
    application.getMainFrame().toFront();
    return viewer;
  }
}
