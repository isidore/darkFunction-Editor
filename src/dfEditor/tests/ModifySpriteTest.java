package dfEditor.tests;

import java.io.File;

import javax.swing.JSpinner;

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
    AnimationController spriteSheet = openSpriteSheet("TestingResources/StarWarsImages/Llewellyn.anim");
    spriteSheet.spriteList.setSelectedIndex(0);
    JSpinner angleSpinner = spriteSheet.angleSpinner;
    angleSpinner.setValue(42);
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
