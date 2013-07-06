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

public class BahaviorExample extends Behavior {

	public BahaviorExample(String name) {
		super(name);
	}
	
	private TimeReached tr; // user action
	private KeyTyped kt;
	private StringTyped st;
	private TextHighlighted th;

	@Override
	public void notify(UserAction userAction) {
		this.conditionFulfilled();
	}

	@Override
	public void condition(UserActionFactory UAF) {
		this.kt = UAF.createKeyTyped('a');
		
		this.kt.activate(this);
	}

	@Override
	public void consequent(CounterActionFactory CAF) {

		SkinSwitch sw = CAF.createSkinSwitch(2);
		
		CloudComment cc = CAF.createCloudComment("proba !");
		
		sw.trigger();
		cc.trigger();
	}

}
