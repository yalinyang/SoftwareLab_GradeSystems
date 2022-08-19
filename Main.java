package GradeSystemProjcect;
import java.util.*;
public class Main extends Object{
	public static Scanner scanner = new Scanner(System.in);
	/* main
	 * 要 aGradeSystem做containsID(ID)看ID是否在aGradeSystem內
	 * 
	 * @throws NoSuchIDException -
	 * 			if the specified ID is not in aGradeSystem
	 * 
	 * @throws NoSuchCommandException - 
	 * 			if the specified ID is null
	 * 			 
	 * Time estimate: O(1)
	 * Example: 
	 * 		UI.checkID("123456789"); throws NoSuchIDException
	 * 		UI.checkID("962001051"); 
	 * 
	 * Pseudo Code
	 * 1. construct an UI Object aUI. (handle NoSuchIDExceptions and NoSuchCommandExceptions)
	 */
	public static void main(String args[]) {
		try {
			UI aUI=new UI();
		}catch(NoSuchIDExceptions nid){
			System.out.println(nid.getMessage());
		}catch(NoSuchCommandExceptions ncmd){
			System.out.println(ncmd.getMessage());
			
		}
	}
}
