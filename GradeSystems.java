package GradeSystemProjcect;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class GradeSystems {
	private int[] weights = null;
	public int[] averages = null;
	public Map<String, Grades> aMap = null;	// �x�s���Z�� <ID, ���Z>

	/* method containsID
	 * �� aGradeSystems�O�_�t��ID
	 * 
	 * @param ID 	The specified ID
	 * @return aGradeSystem���_�t��ID
	 * 	
	 * @throws ClassCastException -
	 * 			if the specified ID is not a String Object
	 * 
	 * @throws NullPointerException - 
 	 * 			if the specified ID is null
     *
	 * Time estimate: O(1)
	 * 
	 * Example: 
	 * 		aGradeSystem.containsID("123456789"); return false
	 * 		aGradeSystem.containsID("962001051"); return true
	 * 
	 * Pseudo code:
	 * 	1. �^��  aMap.containsKey(ID);
	 */
	public boolean containsID(String ID) {
		return this.aMap.containsKey(ID);
	}

	/* method showGrade
	 * �ù����ID�����쪺�ǥͤ����Z
	 * 
	 * @param ID 	The specified ID
	 * 			 
	 * Time estimate: O(1)
	 * Example: 
	 * 		aGradeSystem.showGrade("962001051");
	 * 		�ù���ܡG 
				���§ʦ��Z�Glab1�G     81�@
						lab2�G     32*�@�� �C��60���ݥt�~�аO*
						lab3�G     50*�@�� �C��60���ݥt�~�аO*
						midTerm :  90�@�� �̭^��Φr mid�� term �� ��-�� �s�����@�r
						final exam�G93  �� �̭^��Φr final�� exam ����r 
						total grade : 81
	 * Pseudo code:
	 *  1. �� �o ID ��  Grades
	 *  2. show Grades �̪����Z
	 */
	public void showGrade(String ID) {
		Grades tmp = this.aMap.get(ID);
		System.out.println(tmp.name + "���Z�G\n" +
				"lab1:\t\t" + tmp.lab1 + ((tmp.lab1 < 60)? "*\n": "\n") +
				"lab2:\t\t" + tmp.lab2 + ((tmp.lab2 < 60)? "*\n": "\n") +
				"lab3:\t\t" + tmp.lab3 + ((tmp.lab3 < 60)? "*\n": "\n") +
				"midTerm:\t" + tmp.midTerm + ((tmp.midTerm < 60)? "*\n": "\n") +
				"finalTerm:\t" + tmp.finalTerm + ((tmp.finalTerm < 60)? "*\n": "\n") +
				"totalGrade:\t" + tmp.totalGrade + ((tmp.totalGrade < 60)? "*": ""));
	}
	
	/* method showRank
	 * �ù����ID�����쪺�ǥͤ��Z�ƦW
	 * 
	 * @param ID 	The specified ID
	 * 			 
	 * Time estimate: O(n), n�O���Z�H��
	 * Example: 
	 * 		aGradeSystem.showRank("962001051");
	 * 		�ù���ܡG 
				���§ʱƦW��63
	 * Pseudo code:
	 * 	1. ���o�o ID �� theTotalGrade
	 * 	2. �Orank �� 1 
	 * 	3. loop aGrade in aMap if aTotalGrade > theTotalGrade then rank�[1(�h1�W) end loop
	 * 	4. �L�X rank
	 */
	public void showRank(String ID) {
		Grades theGrade = this.aMap.get(ID);
		int theTotalGrade = theGrade.totalGrade;
		int rank = 1;
		for(Grades aGrade : this.aMap.values()) {
			if(aGrade.totalGrade > theTotalGrade) {
				rank = rank + 1;
			}
		}
		System.out.println(theGrade.name + "�ƦW��" + rank);
	}
	
	/* method updateWeights
	 * ��s5�Ӧ��Z���v��
	 * 			 
	 * Time estimate: O(n), n�O���Z�H��
	 * Example: 
	 * 		aGradeSystem.updateWeights();
	 * 		�ù���ܡG 
				�°t�� 
					lab1           10%
	                lab2           10%
					lab3           10%
					midTerm       30%
					final exam     40%
			 	��J�s�t��
				   	   lab1           20
				       lab2           20
				       lab3           20
				       midTerm       20
				       final exam     20
				�нT�{�s�t��
				       lab1           20%
				       lab2           20%
				       lab3           20%
				       midTerm       20%
				       final exam     20%
				  �H�W���T��? Y (Yes) �� N (No)
				 �ϥΪ̿�J�GY
	 * Pseudo code:
	 * 	1. showOldWeights() 
	 * 	2. getNewWeights() 
	 * 	3. setWeights(weights)
	 * 	4. if new weights is set, loop 
	 * 		1. aGrade in aMap to calculateTotalGrade(weights) 
	 * 	   end loop
	 */
	private void showOldWeights() {
		System.out.println("�°t��\r\n" +
				"lab1           " + (this.weights[0]) + "%\r\n" + 
				"lab2           " + (this.weights[1]) + "%\r\n" + 
				"lab3           " + (this.weights[2]) + "%\r\n" + 
				"midTerm       " + (this.weights[3]) + "%\r\n" + 
				"finalTerm     " + (this.weights[4]) + "%");
	}
	private int[] getNewWeights() {
		int[] weights = new int[5];
		System.out.println("��J�s�t��\r\n");
		System.out.println("lab1           ");
		weights[0] =  Main.scanner.nextInt();
		
		System.out.println("lab2           ");
		weights[1] =  Main.scanner.nextInt();
		
		System.out.println("lab3           ");
		weights[2] =  Main.scanner.nextInt();
		
		System.out.println("midTerm       ");
		weights[3] =  Main.scanner.nextInt();
		
		System.out.println("finalTerm     ");
		weights[4] =  Main.scanner.nextInt();
		return weights;
	}
	private boolean setWeights(int[] weights) {
		System.out.println("�нT�{�s�t��\r\n" +
				"lab1           " + weights[0] + "%\r\n" + 
				"lab2           " + weights[1] + "%\r\n" + 
				"lab3           " + weights[2] + "%\r\n" + 
				"midTerm       " + weights[3] + "%\r\n" + 
				"finalTerm     " + weights[4] + "%");
		System.out.println(" �H�W���T��? Y (Yes) �� N (No)");
		String inputCmd =  Main.scanner.next();
		if(inputCmd.charAt(0) == 'Y') {
			for(int i=0; i<5; i++) {
				this.weights[i] = weights[i];
			}
			return true;
		}
		return false;
	}
	public void updateWeights() {
		this.showOldWeights();
		int[] weights = this.getNewWeights();
		if( this.setWeights(weights) ) {
			for(Grades aGrade : this.aMap.values()) {
				aGrade.calculateTotalGrade(this.weights);
				this.calculateTotalGradeAverage();
			}
		}
	}

	/* method calculateAverage
	 * �p����Z�ǥ�5�Ӧ��Z���O������
	 * 			 
	 * Time estimate: O(n), n�O���Z�H��
	 * Example: 
	 * 		aGradeSystem.calculateAverage();
	 * 		result: this.average �e5�Ӥ������O�� 82, 88, 87, 72, 74
	 * Pseudo code:
	 * 	1. loop1 sum up aLab1, aLab2, aLab3, aMidTerm, and aFinalTerm of aGrade 
	 * 		in aMap respectively end loop1
	 * 	2. Divide each sum with the size of aMap.
	 */
	private void calculateAverage() {
		int count = this.aMap.size();
		for(int i=0; i<5; i++) {
			this.averages[i] = 0;
		}
		for(Grades aGrade : aMap.values()) {
			this.averages[0] += aGrade.lab1;
			this.averages[1] += aGrade.lab2;
			this.averages[2] += aGrade.lab3;
			this.averages[3] += aGrade.midTerm;
			this.averages[4] += aGrade.finalTerm;
		}
		for(int i=0; i<5; i++) {
			this.averages[i] = Math.round((float)this.averages[i] / (float)count);
		}
	}

	/* method calculateTotalGradeAverage
	 * �p����Z�ǥ��`���Z������
	 * 			 
	 * Time estimate: O(n), n�O���Z�H��
	 * Example: 
	 * 		aGradeSystem.calculateTotalGradeAverage();
	 * 		result: this.average[5] = 81
	 * Pseudo code:
	 * 	1. loop1 �U�쥭�����ƪ��[�v���� end loop1
	 */
	private void calculateTotalGradeAverage() {
		this.averages[5] = 0;
		for(int i=0; i<5; i++) {
			this.averages[5] += this.averages[i] * this.weights[i];
		}
		this.averages[5] = Math.round((float)this.averages[5] / 100);
	}
	
	/* method showAverage
	 * ��ܥ��Z�ǥ�5�Ӧ��Z�M�`���Z���O������
	 * 			 
	 * Time estimate: O(1)
	 * Example: 
	 * 		aGradeSystem.showAverage();
	 * 		�ù���ܡG 
				���� 
					lab1           81
	                lab2           88
					lab3           87
					midTerm       72
					final exam     74
					total grade    81
	 * Pseudo code:
	 * 	1. �L�X�s�bthis.averages��6�������Z�C
	 */
	public void showAverage() {
		System.out.println("���� \r\n" + 
				"lab1           " + this.averages[0] + "\r\n" + 
				"lab2           " + this.averages[1] + "\r\n" + 
				"lab3           " + this.averages[2] + "\r\n" + 
				"midTerm       " + this.averages[3] + "\r\n" + 
				"finalTerm     " + this.averages[4] + "\r\n" + 
				"totalGrade    " + this.averages[5]);
	}
	
	/* constructor
	 *��Grade system ���ϥΪ�(�ǥ�)���o�L���`���Ztotal grade �αƦW rank.
      Total grade ���t��weights �Ӻ� �� weights�i�Hupdate. 
	  Rank ��ܦ� total grade �b���Z�ǥͪ����ƱƧ�
	 * 			 
	 * Time estimate: O(n), n�O���Z�H��
	 * 
	 * Pseudo code:
	 *	1. �}�� input file 
	 * 	2. �� Java Map �غc�@�� empty HashMap of grades�s aMap
	 * 	3. read line
	 * 	4. while not endOfFile
	 * 		1.call Grades() �غcaGrade
	 * 		2.�� Java Scanner �� scan line ��U���s�JaGrade
	 * 		3. aGrade.calculateTotalGrade(weights) �^��aTotalGrade�⥦�s�JaGrade
	 * 		4. �� aGrade �s�J aMap
	 * 		5. if endOfLine then read line end if
	 * 	   end while
	 * 	5. �p��5�즨�Z����
	 * 	6. �p���`���Z����
	 */
	public GradeSystems() {
		Path path = Paths.get("./src/GradeSystemProjcect/gradeinput.txt");
		try(Scanner reader = new Scanner(path, "UTF-8")) {
			this.weights = new int[] {10, 10, 10, 30, 40};
			this.averages = new int[6];
			this.aMap = new HashMap<String, Grades>();
			char c = reader.findInLine(".").charAt(0);
			while(reader.hasNext()) {
				Grades aGrade = new Grades();
				aGrade.ID = reader.next();
				aGrade.name = reader.next();
				aGrade.lab1 = reader.nextInt();
				aGrade.lab2 = reader.nextInt();
				aGrade.lab3 = reader.nextInt();
				aGrade.midTerm = reader.nextInt();
				aGrade.finalTerm = reader.nextInt();
				aGrade.calculateTotalGrade(this.weights);
				this.aMap.put(aGrade.ID, aGrade);
			}
			this.calculateAverage();
			this.calculateTotalGradeAverage();
		}catch(IOException ioe) {
			System.out.print("Caught IOException:" + ioe.getMessage());
		}catch(IllegalArgumentException iae) {
			System.out.print("Caught IllegalArgumentException:" + iae.getMessage());
		}catch(InputMismatchException ime) {
			System.out.print("Caught InputMismatchException:" + ime.getMessage());
		}
	}
}
