import core.*;


public class InitialBehavior extends Behavior {

	public InitialBehavior() {
		super("initial");
	}

	@Override
	public void condition(UserActionFactory UAF) {
		this.actionPerformed(null);
		
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		this.conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		CloudComment cl = CAF.createCloudComment("Greet me! (type hi/hello/chao)");
		
		cl.trigger();
	}
}
