package behaviors;

import java.util.Calendar;

import core.*;

public class AfterWork extends Behavior {

	public AfterWork() {
		super("AfterWork");
	}

	@Override
	public void condition(UserActionFactory UAF) {
		
		// retrieve last run
		Calendar cal = Calendar.getInstance();
		Integer lastRunDay = (Integer) Serializer.deserialize(this, "lastrun.dat");
		boolean run = false;
		
		if (lastRunDay != null) {
			if (lastRunDay != cal.get(Calendar.DAY_OF_MONTH)) {
				// not today
				run = true;
			}
		}
		else {
			// last run is null
			run = true;
		}
		
		if (run) {
			// load end of day hour
			Integer endOfDayHour = (Integer) Serializer.deserialize(null, "endofdayhour.dat");
			if (endOfDayHour == null) {
				endOfDayHour = 17; // 5pm
			}
			
			TimeReached tr = UAF.createTimeReached();
			if (cal.get(Calendar.HOUR_OF_DAY) >= endOfDayHour) {
				tr.nextMinutes(0);
			}
			else {
				// set a time reached to alert when end of day occurs
				tr.setHour(endOfDayHour);
			}
			tr.activate(this);
		}
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		this.conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		
		// set and save last run
		Serializer.serialize(this, new Integer(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)), "lastrun.dat");
		
		// retreive master name
		String mName = (String) Serializer.deserialize(null, "mastername.dat");
		
		String question;
		if (mName != null) {
			question = "How was your day " + mName + "?";
		}
		else {
			question = "How was your day?";
		}
		
		// get data from a previous day
		Integer prevDaySI = (Integer) Serializer.deserialize(this, "howwayyourday.dat");
		
		String remember = "";
		if (prevDaySI != null) {
			if (prevDaySI == 1 || prevDaySI == 2) {
				// good or excellent
				remember = " Was it as good as yesterday?";
			}
			else if (prevDaySI == 3) {
				// best
				remember = " Was it as extraordinary good as yesterday?";
			}
			else {
				// not good
				remember = " I bet it's better than yesterday!";
			}
		}
		
		CloudComment cc = CAF.createCloudComment(question + remember);
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.happy.code);
		RadioBtn rb = CAF.createRadioBtn();
		rb.addOption("not that good..");
		rb.addOption("good.");
		rb.addOption("excellent!");
		rb.addOption("best of my life!");
		
		cc.trigger();
		ss.trigger();
		rb.trigger();
		
		int si = rb.getSelectedIndex();
		if (si == -1) { 
			// rejected
			cc.setComment("Nevermind.. if you don't want to tell me..");
			ss.setSkin(Emotion.facingaway.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if (si == 1 || si == 2) {
			// good or excellent
			// save
			Serializer.serialize(this, (Integer) si, "howwayyourday.dat");
			
			if (mName != null) {
				cc.setComment("Good to hear " + mName + "!");
			}
			else {
				cc.setComment("Good to hear!");
			}
			ss.setSkin(Emotion.happy.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if (si == 3) {
			// best
			// save data
			Serializer.serialize(this, (Integer) si, "howwayyourday.dat");
			
			cc.setComment("Really? I'm sooo happy about that!");
			ss.setSkin(Emotion.happy.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			// not that good..
			// save data
			Serializer.serialize(this, (Integer) si, "howwayyourday.dat");
			
			cc.setComment("Hmm, what a pity..");
			ss.setSkin(Emotion.sad.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			cc.setComment("Because I have a great day so far, you should get more positive!");
			ss.setSkin(Emotion.happy.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// get back to neutral
		ss.setSkin(Emotion.neutral.code);
		ss.trigger();
		cc.hide();
	}

}
