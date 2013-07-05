package core;

import display.CheckBoxDialog;
import display.CloudCommentPanel;
import display.InputBoxDialog;
import display.RadioBtnDialog;
import display.SkinDisplay;
import display.CloudCommentDisplay;

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
    private InputBoxDialog inputBoxDialog;
    private RadioBtnDialog radioBtnDialog;
    private CheckBoxDialog checkBoxDialog;
    
    public CounterActionFactory(
    		SkinContainer skinContainer,
    		SkinDisplay skinDisplay,
    		CloudCommentDisplay cloudCommentDisplay,
    		CloudCommentPanel cloudCommentPanel,
    		InputBoxDialog inputBoxDialog,
    		RadioBtnDialog radioBtnDialog,
    		CheckBoxDialog checkBoxDialog)
    {
    	this.skinContainer = skinContainer;
    	this.skinDisplay = skinDisplay;
    	this.cloudCommentDisplay = cloudCommentDisplay;
    	this.cloudCommentPanel = cloudCommentPanel;
    	this.inputBoxDialog = inputBoxDialog;
    	this.radioBtnDialog = radioBtnDialog;
    	this.checkBoxDialog = checkBoxDialog;
    }
    
    public SkinSwitch createSkinSwitch(int skinIndex) {
    	return new SkinSwitch(skinIndex, skinDisplay, skinContainer);
    }
    
    public CloudComment createCloudComment(String comment) {
    	return new CloudComment(comment, cloudCommentDisplay, cloudCommentPanel);
    }
    
    public InputBox createInputBox() {
    	return new InputBox(this.inputBoxDialog);
    }
    
    public RadioBtn createRadioBtn() {
    	return new RadioBtn(this.radioBtnDialog);
    }
    
    public CheckBox createCheckBox() {
    	return new CheckBox(this.checkBoxDialog);
    }
}
