import java.util.Random;
import core.*; 

public class MemoryBehavior extends Behavior {

	public MemoryBehavior() {
		super("MemoryBehavior");
	}

	@Override
	public void condition(UserActionFactory UAF) {

		// will ask the question 1-2 minutes after waifu started
		Random generator = new Random();
		int waitInMinutes = generator.nextInt(2) + 1;
		
		// remember, we don't use Thread.sleep in condition,
		// because it blocks the whole working thread,
		// and doesn't let other behaviors to load
		// .. so use TimeReached
		TimeReached tr = UAF.createTimeReached();
		tr.nextMinutes(waitInMinutes);
		tr.activate(this);
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		// it only ask the question once, after the waifu started,
		// so we don't need to activate TimeReached again
		
		// just notify the AI
		conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		// create CloudComment and SkinSwitch (used anyway)
		CloudComment cc = CAF.createCloudComment("");
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.neutral.code);
		
		// load the previous answer if there is any
		// from a serialized file "answer.dat"
		String answer = (String) Serializer.deserialize(this, "answer.dat");
		
		// don't use primitives (like int if you saved an integer)
		// ..to load cast data (cast to Integer e.g)
		// that way you can check if it's null or not
		
		if (answer == null) {
			// if null, there was no file like answer.dat,
			// or null value was in the file
			
			// ask the question first time
			cc.setComment("What music do you listen to recently?");
			ss.setSkin(Emotion.talking.code);
		}
		else {
			// answer not null
			// ask the question nth time
			cc.setComment("I know you listening to " + answer +
					" recently, got anything new?");
			ss.setSkin(Emotion.happy.code);
		}
		
		InputBox musicRecentlyInput = CAF.createInputBox();
		
		// trigger counter actions
		cc.trigger();
		ss.trigger();
		musicRecentlyInput.trigger();
		
		// now we've got the answer from musicRecentlyInput
		if (musicRecentlyInput.getInput() == null) {
			// if null, cancel was pressed
			cc.setComment("Why don't you tell me?!");
			ss.setSkin(Emotion.sad.code);
		}
		else {
			// input text is not null
			// save the answer (only serializable objects can be saved
			// if you pass null as the first parameter,
			// answer.dat will be created in serialized/shared/
			// if you pass this behavior as 'this',
			// answer.dat will be created in serialized/<name of this behavior>/
			Serializer.serialize(this, musicRecentlyInput.getInput(), "answer.dat");
			
			cc.setComment("Thank you for telling me! I won't forget it!");
			ss.setSkin(Emotion.happy.code);
		}
		
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
