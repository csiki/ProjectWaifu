package behaviors;

import core.Behavior;
import core.CloudComment;
import core.CounterActionFactory;
import core.KeyTyped;
import core.SkinSwitch;
import core.StringTyped;
import core.TextHighlighted;
import core.TimeReached;
import core.UserAction;
import core.UserActionFactory;

// csin�lj ugyanilyen classokat a behaviors csomagba, �s azokkal teszteld
// �ll�tds be h bin/behaviors-b�l t�lts�n be !!! (alapb�l nem onnan szedi) (AIOptionsba tudod ha elind�tod a cum�t)
// elej�n ezt irogasd �t, azt�ncsin�lj t�bb behaviorra egyszerre h reag�l
// nem kell m�sik csak ez fog m�k�dni!!!
// timereachedet pr�b�ld csak, t�bb valszeg szar ! meg a counter action�ket k�l�nb�z� kombin�ci�ban

public class BahaviorExample extends Behavior {

	public BahaviorExample(String name) {
		super(name);
		// ez a konstruktor, ide nem kell semmi
		System.out.println("construktor");
	}
	
	private TimeReached tr; // user action

	@Override
	public void notify(UserAction userAction) {
		
		// ez akkor fut le, ha egy a condition()-ba �ltalad l�trehozott UserAction sikeresen lezajlott
		// ekkor j�het a v�laszreakci� ( ha csak egy useraction van, egyel�re el�g is lesz annyi )
		this.conditionFulfilled();
		
		System.out.println("notify");
	}

	@Override
	public void condition(UserActionFactory UAF) {
		// egyszer� stringTyped
		this.tr = UAF.createTimeReached(); // a karakter kell
		
		// csal�s
		this.notify(this.tr);
		
		// �s aktiv�ljuk, �gy h ennek a behavior-nek sz�ljon ha sikeres
		//this.ht.activate(this); // csal�s miatt kinyomtam
		
		System.out.println("�gy teszteld h a r�sz lefutott e (ki�rja lenn a console-ra)");
	}

	@Override
	public void consequent(CounterActionFactory CAF) {
		// itt vmit csin�ljon a waifu
		
		// karakter v�lt�s
		SkinSwitch sw = CAF.createSkinSwitch(2); // 2es index�re v�lt, maradj 10en bel�l, de azt is tesztelheted ha rosszat �rsz (sokat, vagy minuszt)
		
		// comment
		CloudComment cc = CAF.createCloudComment("proba !"); // pr�b�ld ki sok sz�veggel is
		
		sw.trigger(); // els�tj�k a cum�t
		cc.trigger();
		System.out.println("conseqent");
		
		// itt �jraaktiv�lhatod a user actiont, ha azt akarod h �jra m�k�dj�n
		//this.tr.activate(this);
	}

}
