
package com.mathutopia.hadoop;

import java.util.Arrays;

public class PageRank {

	/**
	 * 计算原始矩阵S对应的特征向量
	 * @param S
	 * @return
	 */
	public static double[] calcPageRank(double[][] S) {
		int n = S.length;
		double a = 0.85;
		double[][] G = addTo(multiple(S, a), (1 - a) / n);
		double[] q0 = new double[n];
		Arrays.fill(q0, 1.0);
		double[] q1 = multiple(G, q0);
		while (distinct(q1, q0) > 0.001) {//精度控制为0.001
			q0 = q1;
			q1 = multiple(G, q0);
		}
		return q1;
	}

	/**
	 * 矩阵乘以向量，返回结果向量
	 * 
	 * @param A
	 * @param q
	 * @return
	 */
	public static double[] multiple(double[][] A, double[] q) {
		double[] q1 = new double[q.length];
		for (int i = 0; i < q1.length; i++) {
			double m = 0;
			for (int j = 0; j < A[i].length; j++) {
				m += A[i][j] * q[j];
			}
			q1[i] = m;
		}
		return q1;
	}

	/**
	 * 两个向量的距离
	 * 
	 * @param q1
	 * @param q2
	 * @return
	 */
	public static double distinct(double[] q1, double[] q2) {
		double d = 0;
		for (int i = 0; i < q1.length; i++) {
			d += Math.pow(q1[i] - q2[i], 2);
		}
		return Math.sqrt(d);
	}

	/**
	 * A(i,j)=A(i,j)+x
	 * 
	 * @param A
	 * @param x
	 * @return
	 */
	public static double[][] addTo(double[][] A, double x) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				A[i][j] += x;
			}
		}
		return A;
	}

	/**
	 * A(i,j)=A(i,j)+B(j,j)
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static double[][] addTo(double[][] A, double[][] B) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				A[i][j] += B[i][j];
			}
		}
		return A;
	}

	/**
	 * A(i,j)=A(i,j)*x
	 * 
	 * @param A
	 * @param x
	 * @return
	 */
	public static double[][] multiple(double[][] A, double x) {
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				A[i][j] *= x;
			}
		}
		return A;
	}

	public static void main(String[] args) {
		double[][] S = new double[][] { { 0, .5, .5, 0, .5 }, { .25, 0, 0, 0, 0 }, { .25, 0, 0, 1, .5 },
				{ .25, .5, .5, 0, 0 }, { .25, 0, 0, 0, 0 } };
		 print(calcPageRank(S));
	}

	private static void print(double[] d) {
		for (int i = 0; i < d.length; i++) {
			System.out.println(d[i]);
		}
	}

}
