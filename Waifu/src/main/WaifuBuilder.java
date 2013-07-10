package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.UIManager;
import core.*;
import display.*;

public class WaifuBuilder {
	
	// sensors
	private KeyReader keyReader;
	private HighlightTracker highlightTracker;
	private TimeChecker timeChecker;
	Thread threadForHTracker;
	Thread threadForTChecker;
	
	// factories
	private CounterActionFactory counterActionFactory;
	private UserActionFactory userActionFactory;
	
	// dialogs
	private InputBoxDialog inputBoxDialog;
	private CheckBoxDialog checkBoxDialog;
	private RadioBtnDialog radioBtnDialog;
	private SettingsDialog settingsDialog;
	private SkinOptionsDialog skinOptionsDialog;
	private AIOptionsDialog aiOptionsDialog;
	
	// panels
	private CloudCommentPanel lowerCloudCommentPanel;
	private CloudCommentPanel upperCloudCommentPanel;
	private MenuPanel menuPanel;
	private SkinPanel skinPanel;
	
	// display elements
	private CloudCommentDisplay lowerCloudDisplay;
	private CloudCommentDisplay upperCloudDisplay;
	private SkinDisplay skinDisplay;
	
	// mainframe
	private MainFrame mainFrame;

	// others
	private AI ai;
	private BehaviorContainer behaviorContainer;
	private BehaviorLoader behaviorLoader;
	private Settings settings;
	private SkinContainer skinContainer;
	
	public void build() {

		this.setUIstyle();
		this.loadSettings();
		this.loadSkins();
		this.loadBehaviors();
		this.buildSensors();
		this.buildDisplays();
		this.buildMainFrame();
		
		this.buildDialogs();
		
		this.buildPanels();
		this.mainFrame.providePanels(skinPanel, lowerCloudCommentPanel, upperCloudCommentPanel, menuPanel);
		
		this.buildCounterActionDialogs();
		this.buildFactories();
		this.buildAI();
		this.provideAItoOthers();
		
	}
	
	public void start() {
		this.mainFrame.initialize();
		
		this.ai.newBehaviorsLoaded();
	}
	
	public void exit() {
		this.mainFrame.getFrame().dispose();
		
		// stop extra threads
		this.highlightTracker.turnOff();
		this.timeChecker.turnOff();
		this.keyReader.turnOff();
	}
	
	private void loadSettings() {
		this.settings = (Settings) Serializer.deserialize(null, "settings.waifu");
		
		if (this.settings == null) {
			this.settings = new Settings();
		}
	}
	
	private void loadSkins() {
		this.skinContainer = (SkinContainer) Serializer.deserialize(null, "skincontainer.waifu");
		
		if (this.skinContainer == null) {
			this.skinContainer = new SkinContainer();
			
			String pathToSkins = "skins" + java.io.File.separator + "default" + java.io.File.separator;
			for (Integer i = 0; i < 9; ++i) {
				this.skinContainer.addSkin(i, pathToSkins + i.toString() + ".png");
			}
		}
		else {
			this.skinContainer.init();
		}
	}
	
	private void loadBehaviors() {
		this.behaviorLoader = new BehaviorLoader(this.settings.getBehaviorsPath());
		this.behaviorContainer = this.behaviorLoader.loadBehaviors(null);
	}
	
	private void buildSensors() {
		this.keyReader = new KeyReader("keyreader");
		this.highlightTracker = new HighlightTracker("highlight");
		this.timeChecker = new TimeChecker("timechecker");
		
		this.threadForHTracker = new Thread(this.highlightTracker);
		this.threadForTChecker = new Thread(this.timeChecker);
		
		this.threadForHTracker.start();
		this.threadForTChecker.start();
	}
	
	private void buildDisplays() {
		
		// load image
		File upperCloudImgFile = new File("img" + java.io.File.separator + "speechbubble.png");
		File lowerCloudImgFile = new File("img" + java.io.File.separator + "chatbubble.png");
		BufferedImage upperCloudImg = null;
		BufferedImage lowerCloudImg = null;
		
		try {
			upperCloudImg = ImageIO.read(upperCloudImgFile);
			lowerCloudImg = ImageIO.read(lowerCloudImgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.lowerCloudDisplay = new CloudCommentDisplay(this.settings, lowerCloudImg);
		this.upperCloudDisplay = new CloudCommentDisplay(this.settings, upperCloudImg);
		// here setting the deafult sprite
		this.skinDisplay = new SkinDisplay(this.settings, this.skinContainer.loadSkin(0));
	}
	
	private void buildMainFrame() {
		this.mainFrame = new MainFrame(settings);
	}
	
	private void buildDialogs() {
		this.skinOptionsDialog = new SkinOptionsDialog(this.skinContainer, this.mainFrame.getFrame());
		this.aiOptionsDialog = new AIOptionsDialog(this.behaviorContainer, this.behaviorLoader, this.settings, this.mainFrame.getFrame());
		this.settingsDialog = new SettingsDialog(this.settings, this.mainFrame.getFrame());
	}
	
	private void buildPanels() {
		// load menu images
		String pathExit = "img" + java.io.File.separator + "cross.png";
		String pathWrench = "img" + java.io.File.separator + "edit.png";
		
		BufferedImage exitImg = null;
		BufferedImage wrenchImg = null;
		
		try {
			exitImg = ImageIO.read(new File(pathExit));
			wrenchImg = ImageIO.read(new File(pathWrench));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// load menu panel
		this.menuPanel = new MenuPanel(settings, wrenchImg, exitImg, this, skinOptionsDialog, aiOptionsDialog, settingsDialog);
		
		// skin panel
		this.skinPanel = new SkinPanel(this.settings, this.skinDisplay);
		
		// cloud panel
		this.lowerCloudCommentPanel = new CloudCommentPanel(this.settings, this.lowerCloudDisplay);
		this.upperCloudCommentPanel = new CloudCommentPanel(this.settings, this.upperCloudDisplay);
	}
	
	private void buildCounterActionDialogs() {
		this.checkBoxDialog = new CheckBoxDialog(this.settings, this.mainFrame.getFrame());
		this.inputBoxDialog = new InputBoxDialog(this.settings, this.mainFrame.getFrame());
		this.radioBtnDialog = new RadioBtnDialog(this.settings, this.mainFrame.getFrame());
	}
	
	private void buildFactories() {
		this.userActionFactory = new UserActionFactory(keyReader, timeChecker, highlightTracker);
		this.counterActionFactory = new CounterActionFactory(skinContainer, skinDisplay, skinPanel, lowerCloudDisplay, lowerCloudCommentPanel, upperCloudDisplay, upperCloudCommentPanel, inputBoxDialog, radioBtnDialog, checkBoxDialog);
	}
	
	private void buildAI() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		sensors.add(keyReader);
		sensors.add(timeChecker);
		sensors.add(highlightTracker);
		
		this.ai = new AI(sensors, behaviorContainer, userActionFactory, counterActionFactory);
	}
	
	private void provideAItoOthers() {
		
		// to behaviors
		for (int i = 0; i < this.behaviorContainer.getNumOfBehaviors(); ++i) {
			this.behaviorContainer.getBehavior(i).provideAI(this.ai);
		}
		
		// to AI options
		this.aiOptionsDialog.provideAI(this.ai);
	}
	
	private void setUIstyle() {
		UIManager.put("MenuItem.selectionBackground", new Color(47, 105, 202));
		UIManager.put("MenuItem.selectionForeground", Color.WHITE);
	}
}
