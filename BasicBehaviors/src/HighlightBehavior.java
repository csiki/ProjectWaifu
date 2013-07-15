import core.*;
import de.ksquared.system.keyboard.KeyEvent;

public class HighlightBehavior extends Behavior {

	public HighlightBehavior() {
		super("HighlightBehavior");
	}
	
	TextHighlighted commentH;

	@Override
	public void condition(UserActionFactory UAF) {
		// create TextHighlighted, to be able to use..
		// ..it in method consequent
		commentH = UAF.createTextHighlighted();
		
		// wait for Enter to be pressed
		KeyTyped enterPressed = UAF.createKeyTyped(KeyEvent.VK_RETURN);
		enterPressed.activate(this);
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		// enter pressed, notify AI
		conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		CloudComment cc = CAF.createCloudComment("Highlight your comment!");
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.embarassed.code);
		
		cc.trigger();
		ss.trigger();
		
		// activate TextHighlighted
		// pass null, we don't want to call actionPerformed()
		this.commentH.activate(null);
		
		// wait till text is highlighted or 30 seconds
		int secondsPassed = 0;
		while (!this.commentH.isTextHighlighted() && secondsPassed < 30) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			++secondsPassed;
		}
		
		if (!this.commentH.isTextHighlighted()) {
			// still no text is highlighted
			cc.setComment("I can't see your comment!");
			ss.setSkin(Emotion.sad.code);
		}
		else {
			// text is highligted
			cc.setComment("Great comment!");
			ss.setSkin(Emotion.happy.code);
		}
		
		// trigger counter actions
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
