package prog_assign;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public ArrayList<SatProb> problems;
	public boolean verifyValSol = false;
	public static int[] truthTableRow;

	boolean DEBUG = false;

	/**
	 * @param stream
	 *            The InputStream where input will be read from (usually
	 *            System.in)
	 * @return ArrayList of Strings, where each element is a line of input
	 */
	public ArrayList<String> getInput(InputStream stream) {
		ArrayList<String> input = new ArrayList<String>();
		Scanner scanner = new Scanner(stream);

		while (true) {
			String nextLine = scanner.nextLine();
			if (nextLine.equals("")) {
				break;
			}
			input.add(nextLine);
		}
		scanner.close();

		return input;
	}

	public void parseInput(ArrayList<String> input) {
		this.problems = new ArrayList<SatProb>();
		int m = 0;
		int n = 0;
		for (int i = 0; i < input.size(); i = i + (2 + n)) {
			SatProb tempProb = new SatProb();
			m = 0;
			n = 0;

			// get name of problem
			tempProb.name = input.get(i);
			if (DEBUG)
				System.out.println(tempProb.name);

			// get number of variables and clauses
			String tmpStr = input.get(i + 1);
			String[] mn = tmpStr.split(" ");
			m = Integer.parseInt(mn[0]);
			n = Integer.parseInt(mn[1]);
			tempProb.numVariables = m;
			tempProb.numClauses = n;
			if (DEBUG)
				System.out
						.println("# of vars: " + m + "; num of clauses: " + n);

			// Get array of numbers
			tempProb.clauses = new ArrayList<ArrayList<Integer>>();
			for (int j = 0; j < n; j++) {
				String nums = input.get(i + 2 + j);
				String[] numArray = nums.split(" ");
				ArrayList<Integer> tempAL = new ArrayList<Integer>();

				for (int k = 0; k < numArray.length; k++) {
					tempAL.add(Integer.parseInt(numArray[k]));
				}

				tempProb.clauses.add(tempAL);
			}
			if (DEBUG)
				System.out.println(Arrays.deepToString(tempProb.clauses
						.toArray()));
			this.problems.add(tempProb);
		}
	}

	public void solve() {
		for (int i = 0; i < problems.size(); i++) {
			problems.get(i).initEvaluateResult();
		}
	}

	public static void main(String[] args) {
		ArrayList<String> input = new ArrayList<String>();
		Main prog = new Main();
		
		input = prog.getInput(System.in);
		prog.parseInput(input);
		prog.solve();
	}

	// public boolean evaluateBoolExpr(SatProb prob) {
	// String evalExpression = new String();
	//
	// if (DEBUG) {
	// System.out.println("Evaluating problem " + prob.name);
	// System.out.println(Arrays.deepToString(prob.clauses.toArray()));
	// }
	//
	// for (int i = 0; i < prob.clauses.size(); i++) {
	//
	// ArrayList<Integer> line = prob.clauses.get(i);
	// String evalLine = new String();
	//
	// for (int j = 0; j < line.size(); j++) {
	// if (DEBUG)
	// System.out.println("here j: " + j);
	// // if the number is negative, add the ! in front of it
	// if (line.get(j) < 0) {
	// evalLine += "!";
	// }
	//
	// if (prob.truths.get(line.get(j))) {
	// evalLine += "true";
	// } else {
	// evalLine += "false";
	// }
	//
	// // if not on last element, add the || symbol after the
	// // true/false
	// if (j != line.size() - 1) {
	// evalLine += "||";
	// }
	// if (DEBUG)
	// System.out.println("evalLine: " + evalLine);
	// }
	//
	// if (i > 0) {
	// evalExpression += " && ";
	// }
	//
	// evalExpression += "(" + evalLine + ")";
	// if (DEBUG)
	// System.out.println("evalExpression: " + evalExpression);
	// }
	//
	// if (DEBUG)
	// System.out.println(evalExpression);
	//
	// ScriptEngineManager mgr = new ScriptEngineManager();
	// ScriptEngine engine = mgr.getEngineByName("JavaScript");
	// boolean result = false;
	// try {
	// result = (boolean) engine.eval(evalExpression);
	// } catch (ScriptException e) {
	// e.printStackTrace();
	// }
	//
	// return result;
	// }

}
