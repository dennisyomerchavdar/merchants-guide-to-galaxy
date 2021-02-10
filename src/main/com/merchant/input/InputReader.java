package com.merchant.input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.merchant.input.definition.IDefinition;
import com.merchant.input.definition.TranslationDefinition;
import com.merchant.input.definition.ValueDefinition;
import com.merchant.input.query.IQuery;
import com.merchant.input.query.IntergalacticUnitTranslationQuery;
import com.merchant.input.query.InvalidQuery;
import com.merchant.input.query.ItemValueQuery;
import com.merchant.roman.RomanCharacter;

public class InputReader {

	private List<IDefinition> definitions = new ArrayList<>();
	private List<IQuery> queries = new ArrayList<>();
	BufferedReader bufferedReader;
	String currentLine;
	public InputReader(String filename){
		try {
			FileInputStream fstream = new FileInputStream(filename);
			bufferedReader = new BufferedReader(new InputStreamReader(fstream));
			currentLine = bufferedReader.readLine();
			readDictionaryDefinitionLines();
			applyDictionaryDefinitions();
			readValueDefinitionLines();
			applyValueDefinitions();
			readQueries();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void applyDictionaryDefinitions(){
		definitions.stream().filter(iDefinition -> iDefinition instanceof TranslationDefinition).forEach(iDefinition -> iDefinition.applyDefinition());
	}
	private void applyValueDefinitions(){
		definitions.stream().filter(iDefinition -> iDefinition instanceof ValueDefinition).forEach(iDefinition -> iDefinition.applyDefinition());
	}

	//Handle Inputs that are dictionary definitions
	private void readDictionaryDefinitionLines() throws IOException {

		while(currentLine != null){
			Pattern regex = Pattern.compile("^(\\w+) is (\\w)$");
			Matcher matcher = regex.matcher(currentLine);
			if (matcher.matches()){
				definitions.add(new TranslationDefinition(matcher.group(1) , RomanCharacter.valueOf(matcher.group(2))));
				currentLine = bufferedReader.readLine();
			}

			else{
				return;
			}



		}

	}

	//Handle inputs that describe value
	private void readValueDefinitionLines() throws IOException {

		while(currentLine != null){
			Pattern regex = Pattern.compile("^((?:(?:\\w+) )+)(\\w+) is (\\d+[.]?\\d*) Credits$");
			Matcher matcher = regex.matcher(currentLine);
			if (matcher.matches()){
				String galacticRomanText = matcher.group(1).trim();
				definitions.add(new ValueDefinition(galacticRomanText, matcher.group(2), Float.valueOf(matcher.group(3))));
				currentLine= bufferedReader.readLine();
			}

			else{
				return;
			}



		}

	}

	public List<IQuery> getQueries() {
		return queries;
	}

	public void readQueries() throws IOException {
		while(currentLine != null){
			Pattern howMuchQuestionRegex = Pattern.compile("^how much is ((?:(?:\\w+) )+)\\?$");
			Pattern howManyQuestionRegex = Pattern.compile("^how many Credits is ((?:(?:\\w+) )+)(\\w+) \\?$");
			try{
				Matcher matcher = howMuchQuestionRegex.matcher(currentLine);
				if (matcher.matches()){
					String galacticRomanText = matcher.group(1).trim();
					queries.add(new IntergalacticUnitTranslationQuery(galacticRomanText));
					currentLine= bufferedReader.readLine();
					continue;
				}

				matcher = howManyQuestionRegex.matcher(currentLine);
				if (matcher.matches()){
					String galacticRomanText = matcher.group(1).trim();
					queries.add(new ItemValueQuery(galacticRomanText, matcher.group(2)));
					currentLine= bufferedReader.readLine();
					continue;
				}

				queries.add(new InvalidQuery());
				currentLine= bufferedReader.readLine();
			}

			catch (Exception e){
				queries.add(new InvalidQuery());
				currentLine= bufferedReader.readLine();
			}

		}
	}

}
