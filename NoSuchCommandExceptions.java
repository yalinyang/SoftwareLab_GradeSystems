package GradeSystemProjcect;

public class NoSuchCommandExceptions extends Exception {
	public NoSuchCommandExceptions(String cmd) {
		super("¿ù»~ªº«ü¥O: " + cmd);
	}
}
