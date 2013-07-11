package behaviors;

import core.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class EventStore extends Behavior {

	public EventStore() {
		super("EventStore");
	}
	
	private TimeReached askTime;

	@Override
	public void condition(UserActionFactory UAF) {

		// rand time between 1-2 minutes
		Random generator = new Random();
		int waitMinute = generator.nextInt(1) + 1;
		
		// set a TimeReached to the next waitTime minute
		this.askTime = UAF.createTimeReached();
		this.askTime.nextMinutes(0); // TODO
		
		// this behavior is run only once a day, now we see if it has run already
		// by loading a serialized file, that contains only an Integer, the day of the last run
		Integer lastRun = (Integer) Serializer.deserialize(this, "lastrun.dat");
		if (lastRun != null) {
			if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) != lastRun) {
				// activate TimeReached
				// it will call actionPerformed() method of this behavior..
				// ..(when the given time reached)
				this.askTime.activate(this);
			}
		}
		else {
			// if lastRun == null, that means it couldn't run today (or have not run at all)
			this.askTime.activate(this);
		}
	}

	@Override
	public void actionPerformed(UserAction userAction) {

		// ask for running consequent
		this.conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		
		// save last run
		Serializer.serialize(this, new Integer(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)), "lastrun.dat");
		
		// now condition is fulfilled and AI has given the permission
		// ask for any events in the future
		// prepare a SkinSwitch to switch the waifu skin to suspicious
		SkinSwitch skinSwitch = CAF.createSkinSwitch(Emotion.suspicious.code);
		
		// prepare a cloud comment for asking the user
		CloudComment cloudComment = CAF.createCloudComment("Any important events for today?");
		
		// prepare a radio button to find out if the user has any events
		RadioBtn yesnoRadioBtn = CAF.createRadioBtn();
		// add two options (yes/no)
		yesnoRadioBtn.addOption("Yes");
		yesnoRadioBtn.addOption("No");
		
		// now trigger all of them (this is the point when the user see something happening)
		skinSwitch.trigger();
		cloudComment.trigger();
		// last we trigger the radioBtn, because it waits until the input is given
		yesnoRadioBtn.trigger();
		
		// now we have the input
		int selectedIndex = yesnoRadioBtn.getSelectedIndex();
		
		if (selectedIndex == -1) {
			// if selected index == -1 than cancel button has been hit
			// change the skin
			// we don't have to create a new SkinSwitch for that
			// but we should trigger again if we want to see the effect
			skinSwitch.setSkin(Emotion.mad.code);
			// change comment
			// same CloudComment instance will do it
			cloudComment.setComment("Ok, if you don't want to tell me!");
			
			// trigger both
			skinSwitch.trigger();
			cloudComment.trigger();
			
			// now we wait a bit for the user to able to read the cloud
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// now get back to neutral
			skinSwitch.setSkin(Emotion.neutral.code);
			
			skinSwitch.trigger();
			cloudComment.hide();
		}
		else if (selectedIndex == 0) {
			// the user has chosen "Yes" (that's the option with the 0. index, the first that was added)
			// now we ask what is that event, and ask again if the user has any more events
			boolean askFurther = true;
			boolean rejected = false;

			while (askFurther) {
				skinSwitch.setSkin(Emotion.talking.code);
				cloudComment.setComment("What's the name of the event?");
				// create an input box for asking the name of the event
				InputBox eventNameBox = CAF.createInputBox();
				
				cloudComment.trigger();
				skinSwitch.trigger();
				// notice, input box is triggered after cloudComment
				eventNameBox.trigger();
				
				String eventName = eventNameBox.getInput();
				if (eventName == null) {
					// if null, than cancel pressed, rejected
					rejected = true;
					break;
				}
				//else {
					
				// now we have the name
				// ask if the event is today
				cloudComment.setComment("Shall I notify you today about the event?");
				// remember we have already got a radio btn box with a yes and no options
				cloudComment.trigger();
				yesnoRadioBtn.trigger();
				
				selectedIndex = yesnoRadioBtn.getSelectedIndex();
				if (selectedIndex == -1) {
					// rejected
					rejected = true;
					break;
				}
				else if (selectedIndex == 0) {
					// it's for today
					// ask for hour
					cloudComment.setComment("When shall I notify you (hour 0-23)?");
					InputBox eventHour = CAF.createInputBox();
					
					cloudComment.trigger();
					eventHour.trigger();
					
					if (eventHour.getInput() == null) {
						// rejected
						rejected = true;
						break;
					}
					else {
						int hour = Integer.parseInt(eventHour.getInput());
						
						cloudComment.setComment("When shall I notify you (minute 0-59)?");
						InputBox eventMinute = CAF.createInputBox();
						
						cloudComment.trigger();
						eventMinute.trigger();
						
						if (eventMinute.getInput() == null) {
							// rejected
							rejected = true;
							break;
						}
						else {
							int minute = Integer.parseInt(eventMinute.getInput());
							
							// now we got the hour, minute, save the data
							// create time that we can serialize (save)
							Time time = new Time();
							time.setHour(hour);
							time.setMinute(minute);
							// year, month, day automatically filled
							
							// deserialize existing events
							HashMap<Time, String> events = (HashMap<Time, String>) Serializer.deserialize(this, "events.dat");
							if (events == null) {
								// no data exists
								events = new HashMap<Time, String>();
							}
							
							events.put(time, eventName);
							
							// save data
							Serializer.serialize(this, events, "events.dat");
							
							// say ok
							cloudComment.setComment("Ok, I won't forget " + eventName + "!");
							skinSwitch.setSkin(Emotion.happy.code);
							
							cloudComment.trigger();
							skinSwitch.trigger();
							
							try {
								Thread.sleep(3500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							skinSwitch.setSkin(Emotion.neutral.code);
							
							skinSwitch.trigger();
							cloudComment.hide();
						}
					}
				}
				else {
					// other day in the future
					// ask for month at first
					cloudComment.setComment("What month?");
					RadioBtn eventMonth = CAF.createRadioBtn();
					
					for (Integer i = 1; i <= 12; ++i) {
						eventMonth.addOption(i.toString());
					}
					
					cloudComment.trigger();
					eventMonth.trigger();
					
					if (eventMonth.getSelectedIndex() == -1) {
						// rejected
						rejected = true;
						break;
					}
					else {
						int month = eventMonth.getSelectedIndex() + 1;
						
						// day
						cloudComment.setComment("What day?");
						InputBox eventDay = CAF.createInputBox();
						
						cloudComment.trigger();
						eventDay.trigger();
						if (eventDay.getInput() == null) {
							// rejected
							rejected = true;
							break;
						}
						else {
							int day = Integer.parseInt(eventDay.getInput());
							
							// hour
							cloudComment.setComment("What hour?");
							InputBox eventHour = CAF.createInputBox();
							
							cloudComment.trigger();
							eventHour.trigger();
							if (eventHour.getInput() == null) {
								// rejected
								rejected = true;
								break;
							}
							else {
								int hour = Integer.parseInt(eventHour.getInput());
								
								// minute
								cloudComment.setComment("When exactly (minute)?");
								InputBox eventMinute = CAF.createInputBox();
								
								cloudComment.trigger();
								eventMinute.trigger();
								if (eventMinute.getInput() == null) {
									// rejected
									rejected = true;
									break;
								}
								else {
									int minute = Integer.parseInt(eventMinute.getInput());
									
									// we've got everything, now save data
									Time time = new Time();
									time.setMonth(month);
									time.setDay(day);
									time.setHour(hour);
									time.setMinute(minute);
									
									// deserialize existing events
									HashMap<Time, String> events = (HashMap<Time, String>) Serializer.deserialize(this, "events.dat");
									if (events == null) {
										// no data exists
										events = new HashMap<Time, String>();
									}
									
									events.put(time, eventName);
									
									// save data
									Serializer.serialize(this, events, "events.dat");
									
									// say ok
									cloudComment.setComment("Ok, I won't forget " + eventName + "!");
									skinSwitch.setSkin(Emotion.happy.code);
									
									cloudComment.trigger();
									skinSwitch.trigger();
									
									try {
										Thread.sleep(3500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
									skinSwitch.setSkin(Emotion.neutral.code);
									
									skinSwitch.trigger();
									cloudComment.hide();
								}
							}
						}
					}
				}
				
				// ask for any new event
				skinSwitch.setSkin(Emotion.talking.code);
				cloudComment.setComment("Any more events?");
				
				skinSwitch.trigger();
				cloudComment.trigger();
				yesnoRadioBtn.trigger();
				
				if (yesnoRadioBtn.getSelectedIndex() == -1) {
					// rejected
					rejected = true;
					break;
				}
				else if (yesnoRadioBtn.getSelectedIndex() == 0) {
					// yes
					askFurther = true;
				}
				else {
					// no
					askFurther = false;
					
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					skinSwitch.setSkin(Emotion.neutral.code);
					skinSwitch.trigger();
					cloudComment.hide();
				}
			}
			
			// if rejected, waifu gets mad
			if (rejected) {
				skinSwitch.setSkin(Emotion.mad.code);
				cloudComment.setComment("Why you always playing with me?!");
				
				skinSwitch.trigger();
				cloudComment.trigger();
				
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				skinSwitch.setSkin(Emotion.neutral.code);
				skinSwitch.trigger();
				cloudComment.hide();
			}
			
		}
		else {
			cloudComment.setComment("What a borint day it's gonna be..");
			skinSwitch.setSkin(Emotion.sleepy.code);
			
			cloudComment.trigger();
			skinSwitch.trigger();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			skinSwitch.setSkin(Emotion.neutral.code);
			skinSwitch.trigger();
			cloudComment.hide();
		}
	}

}
