import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DecisionTree {
	private static List<String[]> chart;
	private static int[][] trainingSet;
	private static Map<String, Integer> choiceToNumber;
	private static Map<String, Integer> attrToNumber;
	private static Map<Integer, String> numberToChoice;
	private static Map<Integer, String> numberToAttr;
	private static Node root;
	
	public static void main(String[] args) {
		chart = new ArrayList<>();
 		chart.add(new String[] {"Sunny", "Hot", "High", "Weak", "0"});
 		chart.add(new String[] {"Sunny", "Hot", "High", "Strong", "0"});
 		chart.add(new String[] {"Overcast", "Hot", "High", "Weak", "1"});
 		chart.add(new String[] {"Rain", "Mild", "High", "Weak", "1"});
 		chart.add(new String[] {"Rain", "Cool", "Normal", "Weak", "1"});
 		chart.add(new String[] {"Rain", "Cool", "Normal", "Strong", "0"});
 		chart.add(new String[] {"Overcast", "Cool", "Normal", "Strong", "1"});
 		chart.add(new String[] {"Sunny", "Mild", "High", "Weak", "0"});
 		chart.add(new String[] {"Sunny", "Cool", "Normal", "Weak", "1"});
 		chart.add(new String[] {"Rain", "Mild", "Normal", "Weak", "1"});
 		chart.add(new String[] {"Sunny", "Mild", "Normal", "Strong", "1"});
 		chart.add(new String[] {"Overcast", "Mild", "High", "Strong", "1"});
 		chart.add(new String[] {"Overcast", "Hot", "Normal", "Weak", "1"});
 		chart.add(new String[] {"Rain", "Mild", "High", "Strong", "0"});
 		
 		attrToNumber = new HashMap<>();
 		attrToNumber.put("Outlook", 0);
 		attrToNumber.put("Temperature", 1);
 		attrToNumber.put("Humidity", 2);
 		attrToNumber.put("Wind", 3);
		
		choiceToNumber = new HashMap<>();
		trainingSet = ChartParser.parseChart(chart, choiceToNumber);
		
		numberToChoice = Helpers.reverseMap(choiceToNumber);
		numberToAttr = Helpers.reverseMap(attrToNumber);
		
		root = new Node(trainingSet, new HashSet<>());
		
		Digraph dg = new Digraph(root, numberToChoice, numberToAttr);
		
		System.out.println(dg);
	}
}
