package GradeSystemProjcect;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class GradeSystems {
	private int[] weights = null;
	public int[] averages = null;
	public Map<String, Grades> aMap = null;	// 儲存全班的 <ID, 成績>

	/* method containsID
	 * 看 aGradeSystems是否含此ID
	 * 
	 * @param ID 	The specified ID
	 * @return aGradeSystem有否含此ID
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
	 * 	1. 回傳  aMap.containsKey(ID);
	 */
	public boolean containsID(String ID) {
		return this.aMap.containsKey(ID);
	}

	/* method showGrade
	 * 螢幕顯示ID對應到的學生之成績
	 * 
	 * @param ID 	The specified ID
	 * 			 
	 * Time estimate: O(1)
	 * Example: 
	 * 		aGradeSystem.showGrade("962001051");
	 * 		螢幕顯示： 
				李威廷成績：lab1：     81　
						lab2：     32*　註 低於60分需另外標記*
						lab3：     50*　註 低於60分需另外標記*
						midTerm :  90　註 依英文用字 mid及 term 用 “-“ 連結為一字
						final exam：93  註 依英文用字 final及 exam 為兩字 
						total grade : 81
	 * Pseudo code:
	 *  1. 找 這 ID 的  Grades
	 *  2. show Grades 裡的成績
	 */
	public void showGrade(String ID) {
		Grades tmp = this.aMap.get(ID);
		System.out.println(tmp.name + "成績：\n" +
				"lab1:\t\t" + tmp.lab1 + ((tmp.lab1 < 60)? "*\n": "\n") +
				"lab2:\t\t" + tmp.lab2 + ((tmp.lab2 < 60)? "*\n": "\n") +
				"lab3:\t\t" + tmp.lab3 + ((tmp.lab3 < 60)? "*\n": "\n") +
				"midTerm:\t" + tmp.midTerm + ((tmp.midTerm < 60)? "*\n": "\n") +
				"finalTerm:\t" + tmp.finalTerm + ((tmp.finalTerm < 60)? "*\n": "\n") +
				"totalGrade:\t" + tmp.totalGrade + ((tmp.totalGrade < 60)? "*": ""));
	}
	
	/* method showRank
	 * 螢幕顯示ID對應到的學生之班排名
	 * 
	 * @param ID 	The specified ID
	 * 			 
	 * Time estimate: O(n), n是全班人數
	 * Example: 
	 * 		aGradeSystem.showRank("962001051");
	 * 		螢幕顯示： 
				李威廷排名第63
	 * Pseudo code:
	 * 	1. 取得這 ID 的 theTotalGrade
	 * 	2. 令rank 為 1 
	 * 	3. loop aGrade in aMap if aTotalGrade > theTotalGrade then rank加1(退1名) end loop
	 * 	4. 印出 rank
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
		System.out.println(theGrade.name + "排名第" + rank);
	}
	
	/* method updateWeights
	 * 更新5個成績的權重
	 * 			 
	 * Time estimate: O(n), n是全班人數
	 * Example: 
	 * 		aGradeSystem.updateWeights();
	 * 		螢幕顯示： 
				舊配分 
					lab1           10%
	                lab2           10%
					lab3           10%
					midTerm       30%
					final exam     40%
			 	輸入新配分
				   	   lab1           20
				       lab2           20
				       lab3           20
				       midTerm       20
				       final exam     20
				請確認新配分
				       lab1           20%
				       lab2           20%
				       lab3           20%
				       midTerm       20%
				       final exam     20%
				  以上正確嗎? Y (Yes) 或 N (No)
				 使用者輸入：Y
	 * Pseudo code:
	 * 	1. showOldWeights() 
	 * 	2. getNewWeights() 
	 * 	3. setWeights(weights)
	 * 	4. if new weights is set, loop 
	 * 		1. aGrade in aMap to calculateTotalGrade(weights) 
	 * 	   end loop
	 */
	private void showOldWeights() {
		System.out.println("舊配分\r\n" +
				"lab1           " + (this.weights[0]) + "%\r\n" + 
				"lab2           " + (this.weights[1]) + "%\r\n" + 
				"lab3           " + (this.weights[2]) + "%\r\n" + 
				"midTerm       " + (this.weights[3]) + "%\r\n" + 
				"finalTerm     " + (this.weights[4]) + "%");
	}
	private int[] getNewWeights() {
		int[] weights = new int[5];
		System.out.println("輸入新配分\r\n");
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
		System.out.println("請確認新配分\r\n" +
				"lab1           " + weights[0] + "%\r\n" + 
				"lab2           " + weights[1] + "%\r\n" + 
				"lab3           " + weights[2] + "%\r\n" + 
				"midTerm       " + weights[3] + "%\r\n" + 
				"finalTerm     " + weights[4] + "%");
		System.out.println(" 以上正確嗎? Y (Yes) 或 N (No)");
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
	 * 計算全班學生5個成績分別的平均
	 * 			 
	 * Time estimate: O(n), n是全班人數
	 * Example: 
	 * 		aGradeSystem.calculateAverage();
	 * 		result: this.average 前5個元素分別為 82, 88, 87, 72, 74
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
	 * 計算全班學生總成績的平均
	 * 			 
	 * Time estimate: O(n), n是全班人數
	 * Example: 
	 * 		aGradeSystem.calculateTotalGradeAverage();
	 * 		result: this.average[5] = 81
	 * Pseudo code:
	 * 	1. loop1 各科平均分數的加權平均 end loop1
	 */
	private void calculateTotalGradeAverage() {
		this.averages[5] = 0;
		for(int i=0; i<5; i++) {
			this.averages[5] += this.averages[i] * this.weights[i];
		}
		this.averages[5] = Math.round((float)this.averages[5] / 100);
	}
	
	/* method showAverage
	 * 顯示全班學生5個成績和總成績分別的平均
	 * 			 
	 * Time estimate: O(1)
	 * Example: 
	 * 		aGradeSystem.showAverage();
	 * 		螢幕顯示： 
				平均 
					lab1           81
	                lab2           88
					lab3           87
					midTerm       72
					final exam     74
					total grade    81
	 * Pseudo code:
	 * 	1. 印出存在this.averages的6平均成績。
	 */
	public void showAverage() {
		System.out.println("平均 \r\n" + 
				"lab1           " + this.averages[0] + "\r\n" + 
				"lab2           " + this.averages[1] + "\r\n" + 
				"lab3           " + this.averages[2] + "\r\n" + 
				"midTerm       " + this.averages[3] + "\r\n" + 
				"finalTerm     " + this.averages[4] + "\r\n" + 
				"totalGrade    " + this.averages[5]);
	}
	
	/* constructor
	 *本Grade system 讓使用者(學生)取得他的總成績total grade 及排名 rank.
      Total grade 基於配分weights 來算 而 weights可以update. 
	  Rank 表示此 total grade 在全班學生的分數排序
	 * 			 
	 * Time estimate: O(n), n是全班人數
	 * 
	 * Pseudo code:
	 *	1. 開檔 input file 
	 * 	2. 用 Java Map 建構一個 empty HashMap of grades叫 aMap
	 * 	3. read line
	 * 	4. while not endOfFile
	 * 		1.call Grades() 建構aGrade
	 * 		2.用 Java Scanner 來 scan line 把各欄位存入aGrade
	 * 		3. aGrade.calculateTotalGrade(weights) 回傳aTotalGrade把它存入aGrade
	 * 		4. 把 aGrade 存入 aMap
	 * 		5. if endOfLine then read line end if
	 * 	   end while
	 * 	5. 計算5科成績平均
	 * 	6. 計算總成績平均
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
