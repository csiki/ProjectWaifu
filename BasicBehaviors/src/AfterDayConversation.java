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
		System.out.println("zsíros on");
		this.notify(null);
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		CloudComment cl = CAF.createCloudComment("zsíros lesz ez haver !!!");
		InputBox ib = CAF.createInputBox();
		RadioBtn rb = CAF.createRadioBtn();
		CheckBox cb = CAF.createCheckBox();
		
		cl.trigger();
		rb.addOption("egyik");
		rb.addOption("masik");
		rb.addOption("harmadik");
		ib.trigger();
	}

}
