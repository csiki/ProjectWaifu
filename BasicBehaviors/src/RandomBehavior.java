import java.util.Random;
import core.*; 

public class RandomBehavior extends Behavior {

	public RandomBehavior() {
		super("RandomBehavior");
	}
	
	TimeReached every5minutes;
	
	@Override
	public void condition(UserActionFactory UAF) {

		// create a TimeReached user action to notify every 5 minutes
		every5minutes = UAF.createTimeReached();
		
		// will call actionPeformed() after 5 minutes
		every5minutes.nextMinutes(5);
		every5minutes.activate(this);
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		// activate TimeReached again for the next 5 minutes
		every5minutes.nextMinutes(5);
		every5minutes.activate(this);
		
		// notify AI 50%
		Random generator = new Random();
		int chance = generator.nextInt(2);
		
		if (chance == 1) { // 50%
			conditionFulfilled();
		}
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		// create CloudComment and SkinSwitch
		CloudComment cc = CAF.createCloudComment("How are you doing?");
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.suspicious.code);
		
		// create RadioBtn: form with radio buttons
		RadioBtn radioForm = CAF.createRadioBtn();
		
		// add good and bad options
		radioForm.addOption("Good!");
		radioForm.addOption("Bad..");
		
		// at first trigger CloudComment and SkinSwitch
		// only after trigger RadioBtn, because it waits..
		// ..till the radio button form is filled
		cc.trigger();
		ss.trigger();
		radioForm.trigger();
		
		// now we have the input of radioForm
		// if the selected index is -1, than cancel button was pressed
		// if it's 0, "Good!" was selected if 1, "Bad.." was selected
		if (radioForm.getSelectedIndex() == -1) {
			// cancel pressed, be anxious
			// (the same CloudComment and SkinSwitch instances..
			// ..can be used again and again)
			cc.setComment("Why don't you tell me?!");
			ss.setSkin(Emotion.mad.code);
		}
		else if (radioForm.getSelectedIndex() == 0) {
			// "Good!" was selected
			cc.setComment("Yaaay! Me too!");
			ss.setSkin(Emotion.happy.code);
		}
		else {
			// "Bad.." was selected
			cc.setComment("I'm sorry to hear it!");
			ss.setSkin(Emotion.sad.code);
		}
		
		// trigger both CloudComment and SkinSwitch
		cc.trigger();
		ss.trigger();
		
		// wait 4 seconds
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// get back to normal
		ss.setSkin(Emotion.neutral.code);
		ss.trigger();
		cc.hide();
	}

}
