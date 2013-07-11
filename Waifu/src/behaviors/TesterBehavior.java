package behaviors;

import core.*;

public class TesterBehavior extends Behavior {

	public TesterBehavior() {
		super("Tester");
	}

	@Override
	public void condition(UserActionFactory UAF) {
		//this.actionPerformed(null);
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		this.conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		RadioBtn extreme = CAF.createRadioBtn();
		CheckBox extreme2 = CAF.createCheckBox();
		
		for (Integer i = 0; i < 10; ++i) {
			extreme2.addOption("dawwdawdawd");
		}
		
		extreme2.trigger();
		
	}

}
