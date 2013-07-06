package behaviors;

import core.Behavior;
import core.CloudComment;
import core.CounterActionFactory;
import core.KeyTyped;
import core.SkinSwitch;
import core.StringTyped;
import core.TextHighlighted;
import core.TimeReached;
import core.UserAction;
import core.UserActionFactory;

public class BehaviorExample extends Behavior {

	public BehaviorExample(String name) {
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
		
		ccshort.trigger();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ccmedium.trigger();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cclong.trigger();
	}

}
