package dfEditor.tests;

import static org.junit.Assert.*;

import java.awt.Component;
import java.io.File;

import org.approvaltests.Approvals;
import org.jdesktop.application.Application;
import org.junit.Test;

import com.spun.util.ThreadUtils;

import dfEditor.dfEditorApp;
import dfEditor.dfEditorView;
import dfEditor.animation.AnimationController;
import dfEditor.command.CommandManager;
import dfEditor.io.AnimationSetReader;

public class ModifySpriteTest {

	@Test
	public void test() throws Exception {
		// Put a sprite on the canvas
		AnimationController spriteSheet = openSpriteSheet("TestingResources/StarWarsImages/Llewellyn.anim");
		// Change the angle
		spriteSheet.spriteList.setSelectedIndex(0);
		spriteSheet.angleSpinner.getEditor().()
		;
		spriteSheet.spriteList.setSelectedIndex(-1);
		// Select the canvas
		// Verify that the angle has reverted
		Approvals.verify(spriteSheet);
	}

	private AnimationController openSpriteSheet(String file) {
		dfEditorApp.main(new String[0]);
		File selectedFile = new File(file);
		dfEditorApp application = null;
		dfEditorView viewer = null;
		while (viewer == null) {
			application = dfEditorApp.getApplication();
			viewer = application.getViewer();
			System.out.println("viewer");
			ThreadUtils.sleep(100);
		}

		AnimationController load = (AnimationController) viewer.load(selectedFile);
		return load;
	}

}
