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

public class BehEx2 extends Behavior {

	public BehEx2(String name) {
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
		this.st = UAF.createStringTyped("hi");
		
		this.st.activate(this);
	}

	@Override
	public void consequent(CounterActionFactory CAF) {

		SkinSwitch sw = CAF.createSkinSwitch(2);
		
		CloudComment cc = CAF.createCloudComment("Wuzzuuuup?!");
		
		sw.trigger();
		cc.trigger();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cc.hide();
	}

}
