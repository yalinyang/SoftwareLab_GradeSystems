package GradeSystemProjcect;

public class NoSuchCommandExceptions extends Exception {
	public NoSuchCommandExceptions(String cmd) {
		super("���~�����O: " + cmd);
	}
}
