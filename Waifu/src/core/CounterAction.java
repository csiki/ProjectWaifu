package core;

//  @ Project		: ProjectWaifu
//  @ File Name		: CounterAction.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


/**
 * Base class of counter actions like InputBox, CloudComment etc.
 * @author csiki
 *
 */
public abstract class CounterAction {
	
	/**
	 * Triggers the counter action.
	 */
    public abstract void trigger();
}
