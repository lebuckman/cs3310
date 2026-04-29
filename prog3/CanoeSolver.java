package prog3;

/**************************************************************/
/* Liam Buckman                                               */
/* Login ID: lebuckman                                        */
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
}