package core;
import display.CloudCommentPanel;
import display.SkinDisplay;
import display.CloudCommentDisplay;
import display.InputBoxPane;
import display.RadioBtnPane;
import display.CheckBoxPane;

//  @ Project		: ProjectWaifu
//  @ File Name		: CounterActionFactory.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class CounterActionFactory {
	
    private SkinContainer skinContainer;
    private SkinDisplay skinDisplay;
    private CloudCommentDisplay cloudCommentDisplay;
    private CloudCommentPanel cloudCommentPanel;
    private InputBoxPane inputBoxPane;
    private RadioBtnPane radioBtnPane;
    private CheckBoxPane checkBoxPane;
    
    public CounterActionFactory(
    		SkinContainer skinContainer,
    		SkinDisplay skinDisplay,
    		CloudCommentDisplay cloudCommentDisplay,
    		InputBoxPane inputBoxPane,
    		RadioBtnPane radioBtnPane,
    		CheckBoxPane checkBoxPane)
    {
    	this.skinContainer = skinContainer;
    	this.skinDisplay = skinDisplay;
    	this.cloudCommentDisplay = cloudCommentDisplay;
    	this.inputBoxPane = inputBoxPane;
    	this.radioBtnPane = radioBtnPane;
    	this.checkBoxPane = checkBoxPane;
    }
    
    public SkinSwitch createSkinSwitch(int skinIndex) {
    	return new SkinSwitch(skinIndex, skinDisplay, skinContainer);
    }
    
    public CloudComment createCloudComment(String comment) {
    	return new CloudComment(comment, cloudCommentDisplay, cloudCommentPanel);
    }
    
    public InputBox createInputBox() {
    	return new InputBox(this.inputBoxPane);
    }
    
    public RadioBtn createRadioBtn() {
    	return new RadioBtn(this.radioBtnPane);
    }
    
    public CheckBox createCheckBox() {
    	return new CheckBox(this.checkBoxPane);
    }
}
