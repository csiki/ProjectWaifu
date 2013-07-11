import core.*;

public class ShowOffConversation extends Behavior {

	public ShowOffConversation() {
		super("showoff");
	}
	
	StringTyped st;
	StringTyped st2;
	StringTyped st3;
	
	@Override
	public void condition(UserActionFactory UAF) {
		st = UAF.createStringTyped("hi");
		st2 = UAF.createStringTyped("chao");
		st3 = UAF.createStringTyped("hello");
		
		st.activate(this);
		st2.activate(this);
		st3.activate(this);
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		st.deactivate();
		st2.deactivate();
		st3.deactivate();
		
		this.conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {

		CloudComment cl = CAF.createCloudComment("");
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.happy.code);
		
		String greeting;
		if (st.isStringTyped()) {
			greeting = "Hi!";
		}
		else if (st2.isStringTyped()) {
			greeting = "Chao!";
		}
		else {
			greeting = "Hello!";
		}
		
		ss.trigger();
		cl.setComment(greeting);
		cl.trigger();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String savedReason = (String) Serializer.deserialize(this, "reason.dat");
		
		if (savedReason != null) {
			cl.setComment("How was your day? I know yesterday was a good one because of " + savedReason + "!");
		}
		else {
			cl.setComment("How is your day?");
		}
		
		cl.trigger();
		
		RadioBtn rb = CAF.createRadioBtn();
		rb.addOption("not good");
		rb.addOption("good");
		rb.addOption("excellent");
		rb.addOption("best so far");
		rb.trigger();
		
		if (rb.getSelectedIndex() == 0) {
			ss.setSkin(Emotion.sad.code);
			cl.setComment("Ohh, I'm sorry to hear it!");
			
			ss.trigger();
			cl.trigger();
		}
		else if (rb.getSelectedIndex() > 0) {
			InputBox ib = CAF.createInputBox();
			ss.setSkin(Emotion.suspicious.code);
			cl.setComment("Hmm, what's the reason?");
			
			ss.trigger();
			cl.trigger();
			ib.trigger();
			
			String reason = ib.getInput();
			while (reason == null || reason == "") {
				cl.setComment("Please tell meeee!");
				ss.setSkin(Emotion.embarassed.code);
				
				cl.trigger();
				ss.trigger();
				ib.trigger();
				reason = ib.getInput();
			}
			
			Serializer.serialize(this, reason, "reason.dat");
			ss.setSkin(Emotion.happy.code);
			cl.setComment("I won't forget that " + reason + " is the reason that you're happy!");
			
			cl.trigger();
			ss.trigger();
		} else {
			cl.setComment("It's ok if you don't want to talk to me..");
			ss.setSkin(Emotion.sad.code);
			
			cl.trigger();
			ss.trigger();
		}
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ss.setSkin(Emotion.neutral.code);
		
		ss.trigger();
		cl.hide();
	}

}
