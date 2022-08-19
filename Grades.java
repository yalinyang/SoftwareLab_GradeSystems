package GradeSystemProjcect;

public class Grades {
	public String ID,name;
	public int lab1,lab2,lab3,midTerm,finalTerm,totalGrade;
	
	/* method calculateTotalGrade
	 * Grades()�٦�GradeSystems.updateWeights()�|�I�s��method�A�p���`���Z
	 * 
	 * @param weights �U�����Z�v��
	 * 
	 * @throws WeightsOutOfBoundException -
	 * 				if length of weights != 5
	 * 
	 * Time estimate: O(1)
	 * Example: 
	 * 		param: weights = {40, 10, 10, 30, 10}
	 * 		private var: lab1=80 lab2=50 lab3=70 midTerm=70 finalTerm=90
	 * 		Grades.calculateTotalGrade(weights); result: totalGrade = 74
	 * 
	 * Pseudo code:
	 * 1. totalGrade=�U������*���������Z
	 */
	public void calculateTotalGrade(int[] weights) {
		this.totalGrade = Math.round((float)(weights[0]*this.lab1 +
						  weights[1]*this.lab2 +
						  weights[2]*this.lab3 +
						  weights[3]*this.midTerm +
						  weights[4]*this.finalTerm)/100);
	}
	
	/* constructor */
	public Grades() {}
}
