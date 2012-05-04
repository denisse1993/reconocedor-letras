package reconoceletras;

import java.util.*;

public class SampleData {
	private Map<String,ArrayList<Drawing>> labelToDrawing;
	private int width, height;
	private boolean firstDrawingAdded;
	
	public SampleData() {
		labelToDrawing = new LinkedHashMap<String,ArrayList<Drawing>>();
		firstDrawingAdded = false;
	}
	
	public int numDrawings() {
		int num = 0;
		for (String label: allLabels()) {
			num += numDrawingsFor(label);
		}
		return num;
	}
	
	public int numLabels() {
		return labelToDrawing.size();
	}
	
	public void addLabel(String label) {
		labelToDrawing.put(label, new ArrayList<Drawing>());
	}
	
	public int getDrawingWidth() {return width;}
	public int getDrawingHeight() {return height;}
	
	public void addDrawing(String label, Drawing d) {
                if (firstDrawingAdded) {
			if (d.getWidth() != width || d.getHeight() != height) {
				throw new IllegalArgumentException("size mismatch");
			}
		} else {
			width = d.getWidth();
			height = d.getHeight();
			firstDrawingAdded = true;
		}
		
		if (!hasLabel(label)) {
			addLabel(label);
		}
		labelToDrawing.get(label).add(d);
	}
	
	public boolean hasLabel(String label) {
		return labelToDrawing.containsKey(label);
	}
	
	public int numDrawingsFor(String label) {
		return hasLabel(label) ? labelToDrawing.get(label).size() : 0;
	}
	
	public Drawing getDrawing(String label, int index) {
		return labelToDrawing.get(label).get(index);
	}
	
	public Set<String> allLabels() {
		return labelToDrawing.keySet();
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (String label: allLabels()) {
			result.append(label + ":");
			for (int i = 0; i < numDrawingsFor(label); ++i) {
				result.append(getDrawing(label, i).toString());
				result.append(":");
			}
			result.deleteCharAt(result.length() - 1);
			result.append("\n");
		}
		return result.toString();
	}
	
	public static SampleData parseDataFrom(Scanner s) {
		SampleData result = new SampleData();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] tokens = line.split(":");
			String label = tokens[0];
			for (int i = 1; i < tokens.length; ++i) {
				result.addDrawing(label, new Drawing(tokens[i]));
			}
		}
		
		return result;
	}
}
