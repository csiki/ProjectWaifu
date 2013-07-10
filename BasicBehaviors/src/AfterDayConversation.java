import core.*;

public class AfterDayConversation extends Behavior {

	public AfterDayConversation() {
		super("wda");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notify(UserAction userAction) {
		this.conditionFulfilled();
	}

	@Override
	public void condition(UserActionFactory UAF) {
		System.out.println("zs�ros on");
		this.notify(null);
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		CloudComment cl = CAF.createCloudComment("zs�ros lesz ez haver !!!");
		
		cl.trigger();
	}

}
