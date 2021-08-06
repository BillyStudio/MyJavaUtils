package cn.wsd.utils.binarytree;

import java.util.Scanner;
import java.util.Stack;


public class TestInfix2PostfixExpression {

	static boolean isOp(char c) {
		return c=='+' || c=='-' || c=='*' || c=='/';
	}

	static int getPriority(char c) {
		if (c=='*' || c=='/') {
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			StringBuilder output = new StringBuilder();
			Stack<Character> stack = new Stack<>();
			for (char c : input.toCharArray()) {
				if (isOp(c)) {
					while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)) {
						output.append(stack.pop());
					}
					stack.push(c);
				} else { // alphabet
					output.append(c);
				}
			}
			while (! stack.isEmpty()) {
				output.append(stack.pop());
			}
			System.out.println(output);
		}
	}
}
