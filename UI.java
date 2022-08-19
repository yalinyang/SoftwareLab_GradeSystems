package GradeSystemProjcect;

public class UI {
	
	public GradeSystems aGradeSystem = null;
	
	/* method checkID
	 * 要 aGradeSystem做containsID(ID)看ID是否在aGradeSystem內
	 * 
	 * @param 	ID 	The specified ID
	 * @return	aGradeSystem有無ID的布林值
	 * 		 
	 * Time estimate: O(1)
	 * Example: 
	 * 		UI.checkID("123456789"); return false;
	 * 		UI.checkID("962001051"); return true;
	 * Pseudo code:
	 * 1. 要aGradeSystem 做containsID(ID) 看 ID 是否含在 aGradeSystem內
	 */
	private boolean checkID(String ID) {
		return aGradeSystem.containsID(ID);
	}
	
	/* method promptID
	 * get user ID or 結束使用指令
	 * 
	 * @return	使用者輸入的String
	 * 
	 * Time estimate: O(1)
	 * Example: 
	 * 		UI.promptID(); 
	 * 		螢幕顯示；
	 * 			輸入ID 或  Q(結束使用)?
	 * 		使用者輸入 ：123456789
	 * Pseudo code:
	 *  1. 提示使用者輸入ID或Q
	 */
	private String promptID() {
		System.out.println("輸入ID 或  Q(結束使用)?");
		return Main.scanner.next();
	}

	/* method showWelcomeMsg
	 * print welcome message
	 * 			 
	 * Time estimate: O(1)
	 * Example: 
	 * 		UI.showWelcomeMsg("962001051"); 
	 * 		螢幕顯示； Welcome, 李威廷
	 * Pseudo code:
	 *  1. 得到ID對應的使用者名稱
	 *  2. 螢幕顯示； Welcome, [使用者]
	 */
	public void showWelcomeMsg(String ID) {
		Grades tmp = aGradeSystem.aMap.get(ID);
		System.out.println("Welcome, " + tmp.name);
	}
	
	/* method promptCommand
	 * get user command
	 * 
	 * @return	使用者是否決定登出
	 * 			 
	 * Time estimate: O(n), n 是aGradeSystem內全班人數
	 * Example: 
	 * 		UI.promptCommand(); 
	 * 		螢幕顯示；
	 * 			輸入指令
	 * 			1) G 顯示成績 (Grade)
	 * 			2) R 顯示排名 (Rank)
	 * 			3) A 顯示平均 (Average)
	 * 			4) W 更新配分 (Weight)
	 * 			5) E 離開選單 (Exit)
	 * 		使用者輸入 ：R
	 * Pseudo code:
	 * 	1. prompt user for inputCommand
	 * 	2. if inputCommand is not G (Grade),R (Rank), W (Weights), or E (Exit),
	 * 		throws an object of NoSuchCommandException
	 * 	3. if inputCommand is E (Exit) then break
	 * 		else: G aGradeSystem.showGrade(ID), R showRank(ID), A showAverage(), W updateWeights() end if
	 */
	public boolean promptCommand(String inputID) throws NoSuchCommandExceptions {
		System.out.println("輸入指令\n" +
				"1) G 顯示成績 (Grade)\n" + 
				"2) R 顯示排名 (Rank)\n" + 
				"3) A 顯示平均 (Average)\n" + 
				"4) W 更新配分 (Weight)\n" + 
				"5) E 離開選單 (Exit)");
		String inputCmd =  Main.scanner.next();
		switch(inputCmd) {
		case "E":
			return false;
		case "G":
			aGradeSystem.showGrade(inputID);
			return true;
		case "R":
			aGradeSystem.showRank(inputID);
			return true;
		case "A":
			aGradeSystem.showAverage();
			return true;
		case "W":
			aGradeSystem.updateWeights();
			return true;
		default:
			throw new NoSuchCommandExceptions(inputCmd);
		}
	}

	/* method showFinishMsg
	 * print finish message
	 * 			 
	 * Time estimate: O(1)
	 * Example: 
	 * 		UI.showFinishMsg(); 
	 * 		螢幕顯示；
	 * 			再見...
	 * Pseudo code:
	 * 	1. 輸出 "再見..."
	 */
	private void showFinishMsg() {
		System.out.println("再見...");
	}
	
	/* constructor
	 * 建構  aGradeSystem
	 * 
	 * @throws NoSuchIDException -
	 * 			if the specified ID is not in aGradeSystem
	 * 
	 * @throws NoSuchCommandException - 
	 * 			if the specified ID is null
	 * 	 
	 * Time estimate: O(mn), n 是aGradeSystem內全班人數, m 是使用者指令總數目
	 * Example: 
	 * 		UI aUI = new UI();
	 * 
	 * Pseudo code:
	 * 	1.call GradeSystems() to建構 aGradeSystem
	 * 	2.loop1 until Q (Quit)
	 * 			1.	promptID() to get user ID  輸入ID或 Q (結束使用)？ 
	 * 			2.	checkID (ID) 看 ID 是否在 aGradeSystem內
	 * 			3.	showWelcomeMsg (ID)      e.g. Welcome李威廷
	 * 			4.	loop2 until E (Exit)
	 * 					promptCommand() to prompt for input command
	 * 				end loop2
	 *		end loop1
	 * 	3.showFinishMsg()           結束了
	 * 	end try
	 * 	finally {}
	 */
	public UI() throws NoSuchIDExceptions, NoSuchCommandExceptions {
		aGradeSystem = new GradeSystems();
		String inputID = null;
		while(true) {
			inputID = promptID();
			if(inputID.equals("Q")) 
				{
				this.showFinishMsg();
				break;
				}
			if(!checkID(inputID)) {
				throw new NoSuchIDExceptions();
			}
			this.showWelcomeMsg(inputID);
			while(true) {
				try {
					if(!this.promptCommand(inputID)) {
						
						break;
					}
				}catch(NoSuchCommandExceptions e) {
					throw new NoSuchCommandExceptions(e.getMessage());
				}
			}
		}
	}
}

