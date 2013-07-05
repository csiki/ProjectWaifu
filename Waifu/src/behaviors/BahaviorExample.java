package behaviors;

import core.Behavior;
import core.CloudComment;
import core.CounterActionFactory;
import core.SkinSwitch;
import core.StringTyped;
import core.UserAction;
import core.UserActionFactory;

// csinálj ugyanilyen classokat a behaviors csomagba, és azokkal teszteld
// állítds be h bin/behaviors-ból töltsön be !!! (alapból nem onnan szedi) (AIOptionsba tudod ha elindítod a cumót)
// elején ezt irogasd át, aztáncsinálj több behaviorra egyszerre h reagál
// nem kell másik csak ez fog mûködni!!!


public class BahaviorExample extends Behavior {

	public BahaviorExample(String name) {
		super(name);
		// ez a konstruktor, ide nem kell semmi
		System.out.println("construktor");
	}
	
	private StringTyped stringT; // user action

	@Override
	public void notify(UserAction userAction) {
		
		// ez akkor fut le, ha egy a condition()-ba általad létrehozott UserAction sikeresen lezajlott
		// ekkor jöhet a válaszreakció ( ha csak egy useraction van, egyelõre elég is lesz annyi )
		this.conditionFulfilled();
		
		System.out.println("notify");
	}

	@Override
	public void condition(UserActionFactory UAF) {
		// egyszerû stringTyped
		this.stringT = UAF.createStringTyped("hajaj"); // hajaj stringet várjuk h beüssék
		
		// és aktiváljuk, úgy h ennek a behavior-nek szóljon ha sikeres
		this.stringT.activate(this);
		
		System.out.println("így teszteld h a rész lefutott e (kiírja lenn a console-ra");
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		// itt vmit csináljon a waifu
		
		// karakter váltás
		SkinSwitch sw = CAF.createSkinSwitch(2); // 2es indexûre vált, maradj 10en belül, de azt is tesztelheted ha rosszat írsz (sokat, vagy minuszt)
		
		// comment
		CloudComment cc = CAF.createCloudComment("proba !"); // próbáld ki sok szöveggel is
		
		sw.trigger(); // elsütjük a cumót
		cc.trigger();
		System.out.println("conseqent");
	}

}
