package behaviors;

import core.Behavior;
import core.CloudComment;
import core.CounterActionFactory;
import core.Emotion;
import core.KeyTyped;
import core.SkinSwitch;
import core.StringTyped;
import core.TextHighlighted;
import core.TimeReached;
import core.UserAction;
import core.UserActionFactory;

public class JyggaMyNigga extends Behavior {

	public JyggaMyNigga(String name) {
		super(name);
	}
	
	// user actions
	private TimeReached tr;
	private KeyTyped kt;
	private StringTyped st;
	private TextHighlighted th;

	@Override
	public void notify(UserAction userAction) {
		this.conditionFulfilled();
	}

	@Override
	public void condition(UserActionFactory UAF) {
		this.notify(null);
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		
		CloudComment ccshort = CAF.createCloudComment("Type \"hi\"!");
		CloudComment ccmedium = CAF.createCloudComment("Imma really confused on this one.. follow !");
		CloudComment cclong = CAF.createCloudComment("This is the ignorant shit you like.. nigga fuck shit ass bitch.. The first thing I'm gonna do is free Seagal.");
		SkinSwitch sw = CAF.createSkinSwitch(3);
		
		ccshort.trigger();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ccmedium.trigger();
		sw.trigger();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cclong.trigger();
		sw.setSkin(Emotion.angry.code);
		sw.trigger();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cclong.hide();
	}

}
