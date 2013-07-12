package behaviors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Map.Entry;

import core.*;

public class WaifuMenuBehavior extends Behavior {

	public WaifuMenuBehavior() {
		super("EventStore"); // to be able to load events
	}
	
	StringTyped nameTyped = null;
	TextHighlighted commentHed = null;
	
	@Override
	public void condition(UserActionFactory UAF) {
		commentHed = UAF.createTextHighlighted();
		
		// retrieve waifu name
		String wName = (String) Serializer.deserialize(null, "waifuname.dat");
		
		if (wName != null) {
			nameTyped = UAF.createStringTyped(wName);
			nameTyped.activate(this);
		}
	}

	@Override
	public void actionPerformed(UserAction userAction) {
		nameTyped.activate(this);
		this.conditionFulfilled();
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		
		// retrieve master name
		String mName = (String) Serializer.deserialize(null, "mastername.dat");
		
		String question = "What can I help you with?";
		if (mName != null) {
			question = "What can I help you with "+ mName +"?";
		}
		
		// show menu
		CloudComment cc = CAF.createCloudComment(question);
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.happy.code);
		RadioBtn menu = CAF.createRadioBtn();
		menu.addOption("Would you read my comment?");
		menu.addOption("Tell me something positive!");
		menu.addOption("I'd like to talk with you!");
		menu.addOption("Events in mind?");
		
		cc.trigger();
		ss.trigger();
		menu.trigger();
		
		int si = menu.getSelectedIndex();
		if (si == -1) {
			// cancelled
			cc.setComment("Ohh.. ok.");
			ss.setSkin(Emotion.sleepy.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if (si == 0) {
			// read comment
			this.readComment(CAF);
		}
		else if (si == 1) {
			// something pos
			this.somethingPositive(CAF);
		}
		else if (si == 2) {
			// talking
			this.someTalking(CAF);
		}
		else {
			// events in mind
			this.eventsInMind(CAF);
		}
		
		// get back to neutral
		ss.setSkin(Emotion.neutral.code);
		ss.trigger();
		cc.hide();
	}
	
	private void readComment(CounterActionFactory CAF) {
		CloudComment cc = CAF.createCloudComment("Of course! Highlight your comment for me please!");
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.happy.code);
		
		cc.trigger();
		ss.trigger();
		
		commentHed.activate(null);
		
		// wait till highlight, max 30 seconds
		int seconds = 0;
		while (!commentHed.isTextHighlighted() && seconds < 30) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			++seconds;
		}
		
		if (!commentHed.isTextHighlighted()) {
			// haven't highlighted anything
			cc.setComment("Thank you for that nothing!");
			ss.setSkin(Emotion.facingaway.code);
			
			cc.trigger();
			ss.trigger();
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			// highlighted
			String text = commentHed.getHighlightedText();
			text = text.toLowerCase();
			
			// waifu mentioned
			String wName = (String) Serializer.deserialize(null, "waifuname.dat");
			
			if (wName != null) {
				if (text.contains(wName.toLowerCase())) {
					cc.setComment("You mentioned my name?? I'm so embarassed!");
					ss.setSkin(Emotion.embarassed.code);
					
					cc.trigger();
					ss.trigger();
					
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					return;
				}
			}
			
			// favourite movie mentioned
			String favMov = (String) Serializer.deserialize(null, "favmovie.dat");
			
			if (favMov != null) {
				if (text.contains(favMov.toLowerCase())) {
					cc.setComment("That's your favourite movie, " + favMov + ", you mentioned! Hell good it is!");
					ss.setSkin(Emotion.happy.code);
					
					cc.trigger();
					ss.trigger();
					
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					return;
				}
			}
			
			// favourite spot on earth
			String favSpot = (String) Serializer.deserialize(null, "favspot.dat");
			
			if (favSpot != null) {
				if (text.contains(favSpot.toLowerCase())) {
					cc.setComment("That's your favourite spot on earth you mentioned, " + favSpot + "! What a nice place!");
					ss.setSkin(Emotion.happy.code);
					
					cc.trigger();
					ss.trigger();
					
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					return;
				}
			}
			
			// music recently listening to
			String musicRecently = (String) Serializer.deserialize(null, "musicrecently.dat");
			
			if (musicRecently != null) {
				if (text.contains(musicRecently.toLowerCase())) {
					cc.setComment("The music you listening to nowadays, " + musicRecently + ", is mentioned! Good taste of music!");
					ss.setSkin(Emotion.happy.code);
					
					cc.trigger();
					ss.trigger();
					
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					return;
				}
			}
			
			// crush's name
			String crushName = (String) Serializer.deserialize(null, "crushname.dat");
			if (crushName != null) {
				if (text.contains(crushName.toLowerCase())) {
					cc.setComment("That's your crush you're talking to?? Go on!");
					ss.setSkin(Emotion.happy.code);
					
					cc.trigger();
					ss.trigger();
					
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					return;
				}
			}
			
			// nothing found
			String mName = (String) Serializer.deserialize(null, "mastername.dat");
			if (mName == null) {
				mName = "";
			}
			
			String[] reactions = {
					"I like that comment " + mName + "!",
					"You're so creative " + mName + "! Great comment!",
					"Ohh, I wonder what the others gonna react to that comment " + mName + "!",
					"Aha, it's ok.",
					"Anything more maybe " + mName + "?"};
			Random generator = new Random();
			int rIndex = generator.nextInt(reactions.length);
			
			// different skins
			if (rIndex < 3) {
				ss.setSkin(Emotion.happy.code);
			}
			else {
				ss.setSkin(Emotion.sleepy.code);
			}
			
			cc.setComment(reactions[rIndex]);
			
			ss.trigger();
			cc.trigger();
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void somethingPositive(CounterActionFactory CAF) {
		CloudComment cc = CAF.createCloudComment("");
		SkinSwitch ss = CAF.createSkinSwitch(Emotion.happy.code);
		
		String[] positiveTs= {
				"\"Be yourself; everyone else is already taken.\" - Oscar Wilde",
				"\"Be the change that you wish to see in the world.\" - Mahatma Gandhi",
				"\"Imperfection is beauty, madness is genius and it's better to be absolutely ridiculous than absolutely boring.\" - Marilyn Monroe",
				"\"There are only two ways to live your life. One is as though nothing is a miracle. The other is as though everything is a miracle.\" - Albert Einstein",
				"\"I have not failed. I've just found 10,000 ways that won't work.\" - Thomas A. Edison",
				"\"It is never too late to be what you might have been.\" - George Eliot",
				"\"Do what you can, with what you have, where you are.\" - Theodore Roosevelt",
				"\"Life isn't about finding yourself. Life is about creating yourself.\" - George Bernard Shaw",
				"\"If you don't like something, change it. If you can't change it, change your attitude. Don't complain.\" - Maya Angelou"};
		
		Random generator = new Random();
		int index = generator.nextInt(positiveTs.length);
		
		cc.setComment(positiveTs[index]);
		
		cc.trigger();
		ss.trigger();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void someTalking(CounterActionFactory CAF) {
		CloudComment cc = CAF.createCloudComment("");
		SkinSwitch ss = CAF.createSkinSwitch(0);
		InputBox answerBox = CAF.createInputBox();
		
		// retrieve serialized data
		String favMovie = (String) Serializer.deserialize(null, "favmovie.dat");
		String favSpot = (String) Serializer.deserialize(null, "favspot.dat");
		String musicRecently = (String) Serializer.deserialize(null, "musicrecently.dat");
		String crushName = (String) Serializer.deserialize(null, "crushname.dat");
		
		if (favMovie == null) {
			favMovie = "";
		}
		if (favSpot == null) {
			favSpot = "";
		}
		if (musicRecently == null) {
			musicRecently = "";
		}
		if (crushName == null) {
			crushName = "";
		}
		String[] favs = {favMovie, favSpot, musicRecently, crushName};
		String[] serializedFavs = {"favmovie.dat", "favspot.dat", "musicrecently.dat", "crushname.dat"};
		
		// ask about fav movie, fav spot, music recently listening to, crush's name
		String[] questions = {
				"What's your favourite movie lately? Mine is Pulp Fiction!",
				"What's your favourite spot on Earth?",
				"What music do you listen to recently?",
				"Do you have a crush on someone? Who is it?"};
		String[] questionsNTime = {
				"What's your favourite movie lately? I know it was " + favMovie + " last time I asked you! Something new?",
				"What's one of your favourite spot on Earth? I know " + favSpot + ", but that's all?",
				"I know you listening to " + musicRecently + " recently, got anything new?",
				"Last time we talked you was in love with " + crushName + ". Any updates? Maybe a new one?"};
		
		Random generator = new Random();
		int qIndex = generator.nextInt(4);
		
		if (favs[qIndex] == "") {
			// question taken first!
			cc.setComment(questions[qIndex]);
		}
		else {
			cc.setComment(questionsNTime[qIndex]);
		}
		ss.setSkin(Emotion.happy.code);
		
		ss.trigger();
		cc.trigger();
		answerBox.trigger();
		
		String answer = answerBox.getInput();
		if (answer == null) {
			// rejected
			ss.setSkin(Emotion.sad.code);
			cc.setComment("Why don't you tell me?!");
			
			ss.trigger();
			cc.trigger();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			// save data
			Serializer.serialize(null, answer, serializedFavs[qIndex]);
			
			ss.setSkin(Emotion.happy.code);
			cc.setComment("I appreciate you told me that! I won't forget it!");
			
			ss.trigger();
			cc.trigger();
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void eventsInMind(CounterActionFactory CAF) {
		CloudComment cc = CAF.createCloudComment("");
		SkinSwitch ss = CAF.createSkinSwitch(0);
		
		@SuppressWarnings("unchecked")
		HashMap<Time, String> events = (HashMap<Time, String>) Serializer.deserialize(this, "events.dat");
		
		if (events == null) {
			ss.setSkin(Emotion.sleepy.code);
			cc.setComment("There's no event I know about.");
			
			ss.trigger();
			cc.trigger();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			if (events.size() == 0) {
				ss.setSkin(Emotion.sleepy.code);
				cc.setComment("There's no event I know about.");
				
				ss.trigger();
				cc.trigger();
				
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				ss.setSkin(Emotion.facingaway.code);
				cc.setComment("Wait a bit, I found some heereee..");
			
				ss.trigger();
				cc.trigger();
				
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				ss.setSkin(Emotion.talking.code);
				ss.trigger();
				
				Iterator<Entry<Time, String>> it = events.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Time, String> one = it.next();
					Time evTime = one.getKey();
					String evName = one.getValue();
					
					cc.setComment(evName + " at " + evTime.toString());
					cc.trigger();
					
					try {
						Thread.sleep(3500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				cc.setComment("Aaaand that's all.");
				ss.setSkin(Emotion.sleepy.code);
				
				cc.trigger();
				ss.trigger();
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
