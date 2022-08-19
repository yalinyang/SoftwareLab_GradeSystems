package GradeSystemProjcect;

public class UI {
	
	public GradeSystems aGradeSystem = null;
	
	/* method checkID
	 * �n aGradeSystem��containsID(ID)��ID�O�_�baGradeSystem��
	 * 
	 * @param 	ID 	The specified ID
	 * @return	aGradeSystem���LID�����L��
	 * 		 
	 * Time estimate: O(1)
	 * Example: 
	 * 		UI.checkID("123456789"); return false;
	 * 		UI.checkID("962001051"); return true;
	 * Pseudo code:
	 * 1. �naGradeSystem ��containsID(ID) �� ID �O�_�t�b aGradeSystem��
	 */
	private boolean checkID(String ID) {
		return aGradeSystem.containsID(ID);
	}
	
	/* method promptID
	 * get user ID or �����ϥΫ��O
	 * 
	 * @return	�ϥΪ̿�J��String
	 * 
	 * Time estimate: O(1)
	 * Example: 
	 * 		UI.promptID(); 
	 * 		�ù���ܡF
	 * 			��JID ��  Q(�����ϥ�)?
	 * 		�ϥΪ̿�J �G123456789
	 * Pseudo code:
	 *  1. ���ܨϥΪ̿�JID��Q
	 */
	private String promptID() {
		System.out.println("��JID ��  Q(�����ϥ�)?");
		return Main.scanner.next();
	}

	/* method showWelcomeMsg
	 * print welcome message
	 * 			 
	 * Time estimate: O(1)
	 * Example: 
	 * 		UI.showWelcomeMsg("962001051"); 
	 * 		�ù���ܡF Welcome, ���§�
	 * Pseudo code:
	 *  1. �o��ID�������ϥΪ̦W��
	 *  2. �ù���ܡF Welcome, [�ϥΪ�]
	 */
	public void showWelcomeMsg(String ID) {
		Grades tmp = aGradeSystem.aMap.get(ID);
		System.out.println("Welcome, " + tmp.name);
	}
	
	/* method promptCommand
	 * get user command
	 * 
	 * @return	�ϥΪ̬O�_�M�w�n�X
	 * 			 
	 * Time estimate: O(n), n �OaGradeSystem�����Z�H��
	 * Example: 
	 * 		UI.promptCommand(); 
	 * 		�ù���ܡF
	 * 			��J���O
	 * 			1) G ��ܦ��Z (Grade)
	 * 			2) R ��ܱƦW (Rank)
	 * 			3) A ��ܥ��� (Average)
	 * 			4) W ��s�t�� (Weight)
	 * 			5) E ���}��� (Exit)
	 * 		�ϥΪ̿�J �GR
	 * Pseudo code:
	 * 	1. prompt user for inputCommand
	 * 	2. if inputCommand is not G (Grade),R (Rank), W (Weights), or E (Exit),
	 * 		throws an object of NoSuchCommandException
	 * 	3. if inputCommand is E (Exit) then break
	 * 		else: G aGradeSystem.showGrade(ID), R showRank(ID), A showAverage(), W updateWeights() end if
	 */
	public boolean promptCommand(String inputID) throws NoSuchCommandExceptions {
		System.out.println("��J���O\n" +
				"1) G ��ܦ��Z (Grade)\n" + 
				"2) R ��ܱƦW (Rank)\n" + 
				"3) A ��ܥ��� (Average)\n" + 
				"4) W ��s�t�� (Weight)\n" + 
				"5) E ���}��� (Exit)");
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
	 * 		�ù���ܡF
	 * 			�A��...
	 * Pseudo code:
	 * 	1. ��X "�A��..."
	 */
	private void showFinishMsg() {
		System.out.println("�A��...");
	}
	
	/* constructor
	 * �غc  aGradeSystem
	 * 
	 * @throws NoSuchIDException -
	 * 			if the specified ID is not in aGradeSystem
	 * 
	 * @throws NoSuchCommandException - 
	 * 			if the specified ID is null
	 * 	 
	 * Time estimate: O(mn), n �OaGradeSystem�����Z�H��, m �O�ϥΪ̫��O�`�ƥ�
	 * Example: 
	 * 		UI aUI = new UI();
	 * 
	 * Pseudo code:
	 * 	1.call GradeSystems() to�غc aGradeSystem
	 * 	2.loop1 until Q (Quit)
	 * 			1.	promptID() to get user ID  ��JID�� Q (�����ϥ�)�H 
	 * 			2.	checkID (ID) �� ID �O�_�b aGradeSystem��
	 * 			3.	showWelcomeMsg (ID)      e.g. Welcome���§�
	 * 			4.	loop2 until E (Exit)
	 * 					promptCommand() to prompt for input command
	 * 				end loop2
	 *		end loop1
	 * 	3.showFinishMsg()           �����F
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

