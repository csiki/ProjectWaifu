package core;

import display.CheckBoxDialog;
import display.CloudCommentPanel;
import display.InputBoxDialog;
import display.RadioBtnDialog;
import display.SkinDisplay;
import display.CloudCommentDisplay;
import display.SkinPanel;

//  @ Project		: ProjectWaifu
//  @ File Name		: CounterActionFactory.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class CounterActionFactory {
	
	/**
	 * SkinContainer instance for creating SkinSwitch instances.
	 */
    private SkinContainer skinContainer;
    
    /**
     * SkinDisplay instance for creating SkinSwitch instances.
     */
    private SkinDisplay skinDisplay;
    
    /**
     * SkinPanel instance for creating SkinSwitch instances.
     */
    private SkinPanel skinPanel;
    
    /**
     * CloudCommentDisplay instance for creating (lower) CloudComment instances.
     */
    private CloudCommentDisplay lowerCloudDisplay;
    
    /**
     * CloudCommentDisplay instance for creating (upper) CloudComment instances.
     */
    private CloudCommentDisplay upperCloudDisplay;
    
    /**
     * CloudCommentPanel instance for creating (lower) CloudComment instances.
     */
    private CloudCommentPanel lowerCloudPanel;
    
    /**
     * CloudCommentPanel instance for creating (upper) CloudComment instances.
     */
    private CloudCommentPanel upperCloudPanel;
    
    /**
     * InputBoxDialog instance for creating InputBox instances.
     */
    private InputBoxDialog inputBoxDialog;
    
    /**
     * RadioBtnDialog instance for creating RadioBtn instances.
     */
    private RadioBtnDialog radioBtnDialog;
    
    /**
     * CheckBoxDialog instance for creating CheckBox instances.
     */
    private CheckBoxDialog checkBoxDialog;
    
    /**
     * Constructor of CounterActionFactory.
     * @param skinContainer
     * @param skinDisplay
     * @param skinPanel
     * @param cloudCommentDisplay
     * @param cloudCommentPanel
     * @param inputBoxDialog
     * @param radioBtnDialog
     * @param checkBoxDialog
     */
    public CounterActionFactory(
    		SkinContainer skinContainer,
    		SkinDisplay skinDisplay,
    		SkinPanel skinPanel,
    		CloudCommentDisplay lowerCloudDisplay,
    		CloudCommentPanel lowerCloudPanel,
    		CloudCommentDisplay upperCloudDisplay,
    		CloudCommentPanel upperCloudPanel,
    		InputBoxDialog inputBoxDialog,
    		RadioBtnDialog radioBtnDialog,
    		CheckBoxDialog checkBoxDialog)
    {
    	this.skinContainer = skinContainer;
    	this.skinDisplay = skinDisplay;
    	this.skinPanel = skinPanel;
    	this.lowerCloudDisplay = lowerCloudDisplay;
    	this.upperCloudDisplay = upperCloudDisplay;
    	this.lowerCloudPanel = lowerCloudPanel;
    	this.upperCloudPanel = upperCloudPanel;
    	this.inputBoxDialog = inputBoxDialog;
    	this.radioBtnDialog = radioBtnDialog;
    	this.checkBoxDialog = checkBoxDialog;
    }
    
    /**
     * Creates a SkinSwitch instance.
     * @param skinIndex index of the skin to switch to (use Emotion.XY.code).
     * @return created SkinSwitch instance
     */
    public SkinSwitch createSkinSwitch(int skinIndex) {
    	return new SkinSwitch(skinIndex, skinDisplay, skinContainer, skinPanel);
    }
    
    /**
     * Creates a CloudComment instance.
     * @param comment the text to show in the cloud.
     * @return created CloudComment instance
     */
    public CloudComment createCloudComment(String comment) {
    	return new CloudComment(comment, upperCloudDisplay, upperCloudPanel);
    }
    
    /**
     * Creates an InputBox instance.
     * @return created InputBox instance.
     */
    public InputBox createInputBox() {
    	return new InputBox(this.inputBoxDialog, lowerCloudDisplay, lowerCloudPanel);
    }
    
    /**
     * Creates a RadioBtn instance.
     * @return created RadioBtn instance.
     */
    public RadioBtn createRadioBtn() {
    	return new RadioBtn(this.radioBtnDialog, lowerCloudDisplay, lowerCloudPanel);
    }
    
    /**
     * Creates a CheckBox instance.
     * created CheckBox instance.
     * @return new CheckBox instance
     */
    public CheckBox createCheckBox() {
    	return new CheckBox(this.checkBoxDialog, lowerCloudDisplay, lowerCloudPanel);
    }
}
