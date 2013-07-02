package core;
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
    private SkinDisplay skinFrame;
    private CloudCommentDisplay cloudCommentFrame;
    private InputBoxPane inputBoxFrame;
    private RadioBtnPane radioBtnFrame;
    public CheckBoxPane checkBoxFrame;
    
    public SkinSwitch createSkinSwitch(int skinIndex) {
    	return null;
    }
    
    public CloudComment createCloudComment(String comment) {
    	return null;
    }
    
    public InputBox createInputBox() {
    	return null;
    }
    
    public RadioBtn createRadioBtn() {
    	return null;
    }
    
    public CheckBox createCheckBox() {
    	return null;
    }
}
