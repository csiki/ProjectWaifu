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

		SkinSwitch sw = CAF.createSkinSwitch(5);
		
		CloudComment cc = CAF.createCloudComment("Type \"hi\"!");
		
		sw.trigger();
		cc.trigger();
	}

}
