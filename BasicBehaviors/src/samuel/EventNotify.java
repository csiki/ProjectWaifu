package samuel;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import core.*;

public class EventNotify extends Behavior {

	public EventNotify() {
		super("EventStore"); // use store serialized objects
	}
	
	HashMap<Time, String> events;
	TimeReached timer;
	String chosenEventName = null;
	Time chosenEventTime = null;

	@Override
	public void condition(UserActionFactory UAF) {
		timer = UAF.createTimeReached();
		timer.nextMinutes(1);
		timer.activate(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(UserAction userAction) {
		timer.nextMinutes(1);
		timer.activate(this);
		// load events data
		events = (HashMap<Time, String>) Serializer.deserialize(this, "events.dat");
		
		if (events == null) {
			events = new HashMap<Time, String>();
			return;
		}
		
		// find already reached times
		Iterator<Entry<Time, String>> it = events.entrySet().iterator();
		this.chosenEventName = null;
		this.chosenEventTime = null;
		while (it.hasNext()) {
			Entry<Time, String> one = it.next();
			Time time = one.getKey();
			
			if (time.isPassed()) {
				// notify user
				chosenEventName = one.getValue();
				this.chosenEventTime = one.getKey();
				events.remove(one.getKey());
				break;
			}
		}
		
		if (this.chosenEventName != null) {
			this.conditionFulfilled();
		}
	}
	
	@Override
	public void consequent(CounterActionFactory CAF) {
		
		// save event changes
		Serializer.serialize(this, this.events, "events.dat");
		
		// if it was yesterday (or earlier) we just mention it
		Calendar cal = Calendar.getInstance();
		CloudComment cl;
		SkinSwitch ss;
		if (cal.get(Calendar.DAY_OF_MONTH) > this.chosenEventTime.getDay()) {
			cl = CAF.createCloudComment("Forgot to tell you one of your shitty events: " + this.chosenEventName + ".");
			ss = CAF.createSkinSwitch(Emotion.embarassed.code);
		}
		else {
			cl = CAF.createCloudComment("There's stuff you should get to before lighting a joi.. a lightsaber: " + this.chosenEventName + ".");
			ss = CAF.createSkinSwitch(Emotion.talking.code);
		}
		
		cl.trigger();
		ss.trigger();
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.chosenEventName = null;
		
		ss.setSkin(Emotion.neutral.code);
		cl.hide();
		ss.trigger();
	}

}
