package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

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
	private CloudCommentPanel cloudCommentPanel;
	private MenuPanel menuPanel;
	private SkinPanel skinPanel;
	
	// display elements
	private CloudCommentDisplay cloudCommentDisplay;
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
		
		this.loadSettings();
		this.loadSkins();
		this.loadBehaviors();
		this.buildSensors();
		this.buildDisplays();
		this.buildMainFrame();
		
		this.buildDialogs();
		this.mainFrame.provideDialogs(skinOptionsDialog, aiOptionsDialog, settingsDialog);
		
		this.buildPanels();
		this.mainFrame.providePanels(skinPanel, cloudCommentPanel, menuPanel);
		
		this.buildCounterActionDialogs();
		this.buildFactories();
		this.buildAI();
		this.provideAItoBehaviors();
		
	}
	
	public void start() {
		this.mainFrame.initialize();
		
		// start behaviors // TODO ezt majd áttenni AIba !
		for (int i = 0; i < this.behaviorContainer.getNumOfBehaviors(); ++i) {
			this.behaviorContainer.getBehavior(i).condition(userActionFactory);
		}
	}
	
	public void exit() {
		this.mainFrame.getFrame().dispose(); // TODO
		
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
			for (Integer i = 1; i <= 10; ++i) {
				this.skinContainer.addSkin(i-1, pathToSkins + "seibah" + i.toString() + ".png");
			}
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
		File cloudImgFile = new File("img" + java.io.File.separator + "chatbubble.png");
		BufferedImage cloudImg = null;
		try {
			cloudImg = ImageIO.read(cloudImgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.cloudCommentDisplay = new CloudCommentDisplay(cloudImg);
		// here setting the deafult sprite
		this.skinDisplay = new SkinDisplay(this.settings, this.skinContainer.loadSkin(0));
	}
	
	private void buildMainFrame() {
		this.mainFrame = new MainFrame(settings);
	}
	
	private void buildDialogs() {
		this.skinOptionsDialog = new SkinOptionsDialog(this.skinContainer, this.mainFrame.getFrame());
		this.aiOptionsDialog = new AIOptionsDialog(this.behaviorContainer, this.behaviorLoader, this.settings);
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
		this.menuPanel = new MenuPanel(wrenchImg, exitImg, this, skinOptionsDialog, aiOptionsDialog, settingsDialog);
		
		// skin panel
		this.skinPanel = new SkinPanel(this.skinDisplay);
		
		// cloud panel
		this.cloudCommentPanel = new CloudCommentPanel(this.cloudCommentDisplay);
	}
	
	private void buildCounterActionDialogs() {
		this.checkBoxDialog = new CheckBoxDialog(this.mainFrame.getFrame());
		this.inputBoxDialog = new InputBoxDialog(this.mainFrame.getFrame());
		this.radioBtnDialog = new RadioBtnDialog(this.mainFrame.getFrame());
	}
	
	private void buildFactories() {
		this.userActionFactory = new UserActionFactory(keyReader, timeChecker, highlightTracker);
		this.counterActionFactory = new CounterActionFactory(skinContainer, skinDisplay, cloudCommentDisplay, inputBoxDialog, radioBtnDialog, checkBoxDialog);
	}
	
	private void buildAI() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		sensors.add(keyReader);
		sensors.add(timeChecker);
		sensors.add(highlightTracker);
		
		this.ai = new AI(sensors, behaviorContainer, userActionFactory, counterActionFactory);
	}
	
	private void provideAItoBehaviors() {
		
		for (int i = 0; i < this.behaviorContainer.getNumOfBehaviors(); ++i) {
			this.behaviorContainer.getBehavior(i).provideAI(this.ai);
		}
		
	}
}
