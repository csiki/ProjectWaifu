package samuel;

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
			// save wName
			Serializer.serialize(null, "samuel", "waifuname.dat");
			
			cc.setComment("That's the first time I see your ass! Hola!");
			ss.setSkin(Emotion.happy.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			// inform about waifu menu
			cc.setComment("From now on, whenever you type one of my names: windu, bmf, or samuel, I'll be there to help yo ass!");
			cc.trigger();
			
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ss.setSkin(Emotion.neutral.code);
			ss.trigger();
			cc.hide();
		}
		// get name for the master
		else if (mName == null) {
			cc.setComment("Hey, peach of shit! What may I call you, instead of peace of shit?");
			ss.setSkin(Emotion.embarassed.code);
			InputBox masterName = CAF.createInputBox();
			
			cc.trigger();
			ss.trigger();
			masterName.trigger();
			
			while (masterName.getInput() == null) {
				cc.setComment("You tell me, and won't press that cancel button again!");
				ss.setSkin(Emotion.sad.code);
				
				cc.trigger();
				ss.trigger();
				masterName.trigger();
			}
			
			// now we got the master's name
			Serializer.serialize(null, masterName.getInput(), "mastername.dat");
			
			// say thanks
			cc.setComment(masterName.getInput() + ", aha.. that's a fucked up name, yo momma gotta hate on your ass.");
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
			rb.addOption("Jediing");
			rb.addOption("Fuck em up");
			rb.addOption("Smokin");
			cc.setComment("What ya doing when you're not jerking off in front of me " + mName + "?");
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
				
				question = "What's the end of the day for you then? When you just hang before the screen scratching yo ass?";
			}
			else if (rb.getSelectedIndex() == 0) {
				question = "What's the time when you put down your saber (hour 0-23)?";
			}
			else if (rb.getSelectedIndex() == 1) {
				question = "What's the time when you are finished with fuckin niggas up (hour 0-23)?";
			}
			else {
				ss.setSkin(Emotion.happy.code);
				ss.trigger();
				question = "That's sound great! When you finished with all them blunts (hour 0-23)?";
			}
			
			cc.setComment(question);
			InputBox endofday = CAF.createInputBox();
			
			cc.trigger();
			endofday.trigger();
			
			while (endofday.getInput() == null) {
				cc.setComment("You MUST tell me!");
				ss.setSkin(Emotion.extra2.code);
				
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
				cc.setComment("Shit's just got real.");
				
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
				cc.setComment("Does it look like a number?!");
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
