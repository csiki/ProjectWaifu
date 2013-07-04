package main;


/**
 * Program starts execution here.
 * @author csiki
 *
 */
public class Main {
	public static void main(String args[]) {
		
		WaifuBuilder waifu = new WaifuBuilder();
		
		waifu.build();
		System.out.println("builded");
		waifu.start();
		System.out.println("started");
	}
}
