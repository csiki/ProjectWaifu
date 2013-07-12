package behaviors;

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
			
			if (this.timePassed(time)) {
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
	
	private boolean timePassed(Time time) {
		
		Calendar cal = Calendar.getInstance();
    	int currY = cal.get(Calendar.YEAR);
    	int currMo = cal.get(Calendar.MONTH) + 1;
    	int currD = cal.get(Calendar.DAY_OF_MONTH);
    	int currH = cal.get(Calendar.HOUR_OF_DAY);
    	int currMi = cal.get(Calendar.MINUTE);
    	
    	int y = (time.getYear() == -1) ? currY : time.getYear();
    	int mo = (time.getMonth() == -1) ? currMo : time.getMonth();
    	int d = (time.getDay() == -1) ? currD : time.getDay();
    	int h = (time.getHour() == -1) ? currH : time.getHour();
    	int mi = (time.getMinute() == -1) ? currMi : time.getMinute();
    	
    	/*
    	 * Values: 	minute	1
    	 * 			hour	60
    	 * 			day		1440
    	 * 			month	44640
    	 * 			year	535680
    	 */
    	
    	int reached = (currY - y) * 535680 + (currMo - mo) * 44640 + (currD - d) * 1440 + (currH - h) * 60 + (currMi - mi);
    	
    	System.out.println("reached: " + reached);
    	
    	return reached >= 0;
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
			cl = CAF.createCloudComment("I forgot to tell you about " + this.chosenEventName + "! I'm so sorry..");
			ss = CAF.createSkinSwitch(Emotion.embarassed.code);
		}
		else {
			cl = CAF.createCloudComment("Hey! I should notify you about an event: " + this.chosenEventName + ".");
			ss = CAF.createSkinSwitch(Emotion.happy.code);
		}
		
		cl.trigger();
		ss.trigger();
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.chosenEventName = null;
		
		ss.setSkin(Emotion.neutral.code);
		cl.hide();
		ss.trigger();
	}

}
