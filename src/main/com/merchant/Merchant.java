package com.merchant;

import com.merchant.input.InputReader;

public class Merchant {
	public static void main(String[] args){
		InputReader inputReader = new InputReader("input.txt");
		inputReader.getQueries().forEach(iQuery -> System.out.println(iQuery.getAnswerForQuery()));
	}
}
