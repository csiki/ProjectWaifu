package core;

//@ Project			: ProjectWaifu
//@ File Name		: Emotion.java
//@ Date			: 2013.07.02.
//@ Author			: csiki
//@ Copyright		: All rights reserved


public enum Emotion {
	
	neutral(0),
	talking(1),
	happy(2),
	sad(3),
	mad(4),
	embarassed(5),
	sleepy(6),
	suspicious(7),
	facingaway(8),
	extra1(9),
	extra2(10),
	extra3(11),
	extra4(12),
	extra5(13),
	extra6(14),
	extra7(15),
	extra8(16),
	extra9(17),
	extra10(18),
	extra11(19),
	extra12(20);
	
	public final int code;
	
	Emotion(int code) {
		this.code = code;
	}
}
