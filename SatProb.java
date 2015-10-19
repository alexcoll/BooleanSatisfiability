package prog_assign;

import java.util.ArrayList;

public class SatProb {
	public String name;
	public int numVariables;
	public int numClauses;
	public ArrayList<ArrayList<Integer>> clauses;
	public boolean isSatisfy;
	public boolean[] truthsBoolean;
	public int[] truthsBinary;

	public SatProb() {
		this.clauses = new ArrayList<ArrayList<Integer>>();
	}

	/**
	 * Prints the results of the program. See assignment sheet for more details
	 */
	public void printResults() {
		// Print name, num of variables, and num of clauses
		System.out.printf("%s: %d Variable(s) %d Clause(s)\n", this.name,
				this.numVariables, this.numClauses);
		// Print satisfy or not
		if (this.isSatisfy) {
			System.out.println("Satisfiable");

			// Print truth values of variables
			String truthString = new String();
			for (int j = 0; j < this.truthsBoolean.length; j++) {
				truthString += this.truthsBoolean[j] + " ";
			}
			System.out.println(truthString);
		} else {
			System.out.println("Unsatisfiable");
		}
	}

	public void initEvaluateResult() {
		truthsBinary = new int[this.numVariables];
		this.truthsBoolean = new boolean[this.numVariables];
		this.EvaluateResult(truthsBinary, 1);
		this.printResults();
	}

	public void EvaluateResult(int[] truthsBinary, int currentDepth) {
		if (isSatisfy) {
			return;
		}

		if (currentDepth == this.numVariables) {
			for (int i = 0; i < this.numClauses; i++) {
				int clauseTruthValue = 0;
				for (int j = 0; j < this.clauses.get(i).size(); j++) {
					int currentNumber = this.clauses.get(i).get(j);

					if (currentNumber < 0) {
						int k = (-1 * (currentNumber)) - 1;
						if (truthsBinary[k] == 0) {
							clauseTruthValue++;
							break;
						} else {
							;
						}
					} else {
						if (truthsBinary[currentNumber - 1] == 1) {
							clauseTruthValue = clauseTruthValue
									+ truthsBinary[currentNumber - 1];
							break;
						} else {
							clauseTruthValue = clauseTruthValue
									+ truthsBinary[currentNumber - 1];
						}
					}
				}

				if (clauseTruthValue == 0) {
					this.isSatisfy = false;
					break;
				} else if (clauseTruthValue > 0) {
					this.isSatisfy = true;
				}
			}

			// Print result
			if (this.isSatisfy) {
				// assert (truthTableRow.length == this.truths.length);
				for (int i = 0; i < truthsBinary.length; i++) {
					if (truthsBinary[i] == 1) {
						this.truthsBoolean[i] = true;
					} else if (truthsBinary[i] == 0) {
						this.truthsBoolean[i] = false;
					}
				}
			}
			return;
		}

		// Search trues
		truthsBinary[currentDepth] = 1;
		this.EvaluateResult(truthsBinary, currentDepth + 1);

		// Search falses
		truthsBinary[currentDepth] = 0;
		this.EvaluateResult(truthsBinary, currentDepth + 1);
	}
}
