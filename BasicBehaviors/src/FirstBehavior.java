import core.*;

public class FirstBehavior extends Behavior {

	public FirstBehavior() {
		super("firstBehavior"); // name the behavior whatever you want
	}
	
	StringTyped helloTyped;
	StringTyped hiTyped;
	StringTyped chaoTyped;

	@Override
	public void condition(UserActionFactory UAF) {
		// create StringTyped instances
		helloTyped = UAF.createStringTyped("hello");
		hiTyped = UAF.createStringTyped("hi");
		chaoTyped = UAF.createStringTyped("chao");
		
		// activate StringTypeds
		// this way the three StringTyped will listen to..
		// .. the hit keys at the same time,
		// if any of them typed, one of them is triggered,
		// and call actionPerformed() of this behavior
		helloTyped.activate(this); // pass this behavior as 'this'
		hiTyped.activate(this);
		chaoTyped.activate(this);
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		// create counter actions (waifu's reactions)
		CloudComment cloudComment = CAF.createCloudComment("Hi master!");
		SkinSwitch skinSwitch = CAF.createSkinSwitch(Emotion.happy.code);
		
		// trigger counter actions
		cloudComment.trigger();
		skinSwitch.trigger();
		
		// wait a 4 seconds
		// let the user recognize and read the comment
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// get waifu back to usual (neutral)
		skinSwitch.setSkin(Emotion.neutral.code);
		skinSwitch.trigger();
		cloudComment.trigger();
	}

}
