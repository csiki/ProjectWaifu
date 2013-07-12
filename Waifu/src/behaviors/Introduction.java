package behaviors;

import core.*;

public class Introduction extends Behavior {

	public Introduction() {
		super("intro");
	}

	@Override
	public void condition(UserActionFactory UAF) {
		this.actionPerformed(null);
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		// run immediately
		this.conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// preparation
		CloudComment cc = CAF.createCloudComment("");
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.happy.code);
		String wName = (String) Serializer.deserialize(null, "waifuname.dat");
		String mName = (String) Serializer.deserialize(null, "mastername.dat");
		Integer endOfDayHour = (Integer) Serializer.deserialize(null, "endofdayhour.dat");
		
		// get a name for the waifu
		if (wName == null) {
			cc.setComment("I believe we haven't seen each other! Hi! Please tell me how you want to call me!");
			ss.setSkin(Emotion.happy.code);
			InputBox waifuName = CAF.createInputBox();
			
			cc.trigger();
			ss.trigger();
			waifuName.trigger();
			
			while (waifuName.getInput() == null) {
				cc.setComment("Please give me a naaaaame!");
				ss.setSkin(Emotion.sad.code);
				
				cc.trigger();
				ss.trigger();
				waifuName.trigger();
			}
			
			// now we got the name
			Serializer.serialize(null, waifuName.getInput(), "waifuname.dat");
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// *** at your service!
			cc.setComment(waifuName.getInput() + ".. hmm thanks!");
			ss.setSkin(Emotion.happy.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// inform about waifu menu
			cc.setComment("From now on, whenever you type my name " + waifuName.getInput() + ", I'll be at your service!");
			cc.trigger();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ss.setSkin(Emotion.neutral.code);
			ss.trigger();
			cc.hide();
		}
		// get name for the master
		else if (mName == null) {
			cc.setComment("I was so rude! I haven't asked your name, so what should I call you?");
			ss.setSkin(Emotion.embarassed.code);
			InputBox masterName = CAF.createInputBox();
			
			cc.trigger();
			ss.trigger();
			masterName.trigger();
			
			while (masterName.getInput() == null) {
				cc.setComment("Please tell me your name!");
				ss.setSkin(Emotion.sad.code);
				
				cc.trigger();
				ss.trigger();
				masterName.trigger();
			}
			
			// now we got the master's name
			Serializer.serialize(null, masterName.getInput(), "mastername.dat");
			
			// say thanks
			cc.setComment(masterName.getInput() + ", ok! I'll get use to it!");
			ss.setSkin(Emotion.happy.code);
			
			cc.trigger();
			ss.trigger();
						
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ss.setSkin(Emotion.neutral.code);
			ss.trigger();
			cc.hide();
		}
		// get end of day hour
		else if (endOfDayHour == null) {
			RadioBtn rb = CAF.createRadioBtn();
			rb.addOption("Working");
			rb.addOption("Studying");
			rb.addOption("Chilling");
			cc.setComment("What are you doing during the weekdays " + mName + "?");
			ss.setSkin(Emotion.suspicious.code);
			
			cc.trigger();
			ss.trigger();
			rb.trigger();
			
			String question;
			if (rb.getSelectedIndex() == -1) {
				// rejected
				cc.setComment("Ok whatever..");
				ss.setSkin(Emotion.sad.code);
				
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				question = "What's the end of the day for you then? When you just hang before the screen?";
			}
			else if (rb.getSelectedIndex() == 0) {
				question = "What's the time when you finish working (hour 0-23)?";
			}
			else if (rb.getSelectedIndex() == 1) {
				question = "What's the time when you leave your school or university usually?";
			}
			else {
				ss.setSkin(Emotion.happy.code);
				ss.trigger();
				question = "Haha! Than what's the time when you just hang before the screen after the day?";
			}
			
			cc.setComment(question);
			InputBox endofday = CAF.createInputBox();
			
			cc.trigger();
			endofday.trigger();
			
			while (endofday.getInput() == null) {
				cc.setComment("Please tell meeeee!");
				ss.setSkin(Emotion.sad.code);
				
				cc.trigger();
				ss.trigger();
				endofday.trigger();
			}
			
			// now we got end of day, save it
			Integer eod = null;
			boolean isNumber = true;
			try {
				eod = Integer.parseInt(endofday.getInput());
			} catch (Exception e) {
				isNumber = false;
			}
			
			if (isNumber) {
				Serializer.serialize(null, eod, "endofdayhour.dat");
				
				// say thanks
				ss.setSkin(Emotion.happy.code);
				cc.setComment("Thank you! Now I can plan my day to be able to spend the most time with you!");
				
				ss.trigger();
				cc.trigger();
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				// not a number
				cc.setComment("That was not a number!");
				ss.setSkin(Emotion.mad.code);
				
				ss.trigger();
				cc.trigger();
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			ss.setSkin(Emotion.neutral.code);
			ss.trigger();
			cc.hide();
		}
	}
}
