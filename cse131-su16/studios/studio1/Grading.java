package studio1;

import cse131.ArgsProcessor;

public class Grading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		String name = ap.nextString("Name of person whose grade is being calculated:");
		double quiz = ap.nextInt("Number of quiz points received:");
		double studio = ap.nextInt("Number of studio points received:");
		double lab = ap.nextInt("Number of lab points received:");
		double extension = ap.nextInt("Number of extension points received:");
		double exam = ap.nextInt("Number of exam points received:");
		
		double quizzes = (quiz*.05);
		double studios = (studio*.1);
		double labs = (lab*.3);
		double extensions = (extension*.3);
		double exams = (exam*.25);
		double grade = (quizzes + studios + labs + extensions + exams);
		
		double rounded = Math.round(grade*100) / 100;
		int graded = (int)rounded;
		
		boolean plus;
		plus = (grade >= 77.0 && grade < 80);
		plus = plus || (grade >= 87.0 && grade < 90);
		
		boolean minus;
		minus = (grade >= 70.0 && grade < 73);
		minus = minus || (grade >= 80.0 && grade < 83);
		minus = minus || (grade >= 90.0 && grade < 93);
		
		System.out.println(name);
		System.out.println("Total score: " + rounded);
		System.out.println("Grade for this course: " + graded);
		System.out.println("Final grade has a...");
		System.out.println("Plus: " + plus);
		System.out.println("Minus: " + minus);
		
	}

}
