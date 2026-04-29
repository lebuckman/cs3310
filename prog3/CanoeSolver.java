package prog3;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: 016916341                                        */
/* CS 3310, Spring 2026                                       */
/* Programming Assignment 3                                   */
/* CanoeSolver: Implements the bottom-up dynamic programming  */
/*              algorithm to compute minimum canoe rental     */
/*              costs between all pairs of trading posts and  */
/*              reconstruct the optimal rental sequence.      */
/**************************************************************/
public class CanoeSolver {
	// The direct cost matrix read from the input file
	private int[][] directCost;

	// The minimum cost to travel between any two posts
	private int[][] opt;

	// Track the next post to stop at for reconstructing the optimal path
	private int[][] next;

	// The number of trading posts
	private int numPosts;

	/**************************************************************/
	/* Constructor: CanoeSolver                                   */
	/* Purpose: Initialize the solver with the direct cost matrix */
	/*          and allocates the DP and path-tracking tables.    */
	/* Parameters:                                                */
	/*   int[][] costMatrix: direct rental costs between posts    */
	/*   int numPosts: total number of trading posts              */
	/**************************************************************/
	public CanoeSolver(int[][] costMatrix, int numPosts) {
		this.directCost = costMatrix;
		this.numPosts = numPosts;
		this.opt = new int[numPosts][numPosts];
		this.next = new int[numPosts][numPosts];
	}

	/**************************************************************/
	/* Method: solve                                              */
	/* Purpose: Runs the bottom-up DP algorithm to fill opt[][]   */
	/*          and next[][]                                      */
	/**************************************************************/
	public void solve() {
		// Initialize the base cases for adjacent posts
		for (int i = 0; i < numPosts - 1; i++) {
			opt[i][i + 1] = directCost[i][i + 1];
			next[i][i + 1] = -1; /* -1 = take the direct rental */
		}

		// Iterate over all possible gaps between posts
		for (int gap = 2; gap < numPosts; gap++) {
			for (int i = 0; i < numPosts - gap; i++) {
				int j = i + gap;

				// Start with the direct cost from i to j as the initial optimal cost
				opt[i][j] = directCost[i][j];
				next[i][j] = -1;

				// Check all possible intermediate stops between i and j
				for (int k = i + 1; k < j; k++) {
					int splitCost = opt[i][k] + opt[k][j];
					if (splitCost < opt[i][j]) {
						opt[i][j] = splitCost;
						// Record the intermediate post k for reconstructing the path
						next[i][j] = k; 
					}
				}
			}
		}
	}

		/**************************************************************/
		/* Method: getOptimalCost                                     */
		/* Purpose: Return the minimum cost to travel from post i     */
		/*          to post j.                                        */
		/* Parameters:                                                */
		/*   int i: starting post index                               */
		/*   int j: ending post index                                 */
		/* Returns: int - optimal cost from i to j                    */
		/**************************************************************/
    public int getOptimalCost(int i, int j) {
			return opt[i][j];
		}

}