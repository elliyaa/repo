package RocketLauncher;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Exceptions.IncorrectDataFormatException;

public class ConditionCalculator implements CalculatorInterface{
	
	private LinkedHashMap<String, List<String>> CSVData;
	private LinkedHashMap<String, List<String>> CSVCalculatedData = new LinkedHashMap<>();
	
	private List<List<String>> listOfData = new LinkedList<>();
	private List<List<String>> listOfDataInter = new LinkedList<>();
	//private List<List<String>> additionalDataList = new LinkedList<>();
	
	private List<Float> calculationsList = new LinkedList<>();
	private List<Integer> daysForLaunch = new LinkedList<>();
	//private String[] entryValueArray;
	//private String[] additionalDataArray;
	
	private float temperature;
	private float wind;
	private float humidity;
	private float precipitation;
	private String lightning;
	private String clouds;
	
	public ConditionCalculator(LinkedHashMap<String, List<String>> CSVData) throws IncorrectDataFormatException {
		this.CSVData = CSVData;
		//this.additionalDataList = additionalDataList;
		//int counter = 0;
		
		for(Map.Entry<String, List<String>> entry : CSVData.entrySet()) {
			
			String entryKey = entry.getKey();	
			List<String> entryValue = entry.getValue();
			
//			if(additionalDataList.size() > 0) {
//				for(int i = 0; i < additionalDataList.size(); i++) {
//					additionalDataArray[i] = additionalDataList.get(i).get(counter);
//				}
//				
//				for(int i = 0; i < entryValue.size(); i++) {
//					entryValueArray[i] = entryValue.get(i);
//				}
//				String[] finalArray = new String[entryValueArray.length + additionalDataArray.length];
//				for (int i = 0; i < entryValueArray.length; i++) {
//					finalArray[i] = entryValueArray[i];
//				}
//				for (int i = 0; i < additionalDataArray.length; i++) {
//					
//					finalArray[entryValueArray.length + i] = additionalDataArray[i];
//				}
//				listOfDataInter.add(Arrays.asList(finalArray));
//			} else {
//				listOfDataInter.add(entryValue);
//			}
			listOfDataInter.add(entryValue);
			//counter++;
			
			if (entryKey.trim().equals("Day/Parameter") || entryKey.trim().equals("Lightning") || entryKey.trim().equals("Clouds")) {
				CSVCalculatedData.put(entryKey.trim(), new LinkedList<>());
			} else {
				CSVCalculatedData.put(entryKey.trim(), Arrays.asList(this.calculateMin(entryValue), this.calculateMax(entryValue), this.calculateAverage(entryValue), this.calculateMedian(entryValue)));	
			}
		}
		
		for(int i = 0; i < listOfDataInter.get(0).size(); i++) {
			
			List<String> column = new LinkedList<>();
			for (int j = 0; j < listOfDataInter.size(); j++) {
				if (i < listOfDataInter.get(j).size()) {
					column.add(listOfDataInter.get(j).get(i));
				} else {
					throw new IncorrectDataFormatException();
				}
			}
			listOfData.add(column);
		}

	}
	
	@Override
	public String calculateAverage(List<String> data) {
		data.forEach(str -> calculationsList.add(Float.parseFloat(str)));
		float sum = 0;
		for (float num : calculationsList) {
		    sum += num;
		}
		float avg = sum / calculationsList.size();
		return "Average value: " + avg;
	}

	@Override
	public String calculateMax(List<String> data) {
		data.forEach(str -> calculationsList.add(Float.parseFloat(str)));
		float max = Collections.max(calculationsList);
		return "Max value: " + max;
	}

	@Override
	public String calculateMin(List<String> data) {
		data.forEach(str -> calculationsList.add(Float.parseFloat(str)));
		float min = Collections.min(calculationsList);
		return "Min value: " + min;
	}

	@Override
	public String calculateMedian(List<String> data) {
		data.forEach(str -> calculationsList.add(Float.parseFloat(str)));
		Collections.sort(calculationsList);
		
		int size = calculationsList.size();
		float median;
		if (size % 2 == 0) {
		    median = (calculationsList.get(size / 2 - 1) + calculationsList.get(size / 2)) / 2;
		} else {
		    median = calculationsList.get(size / 2);
		}
		return "Median value: " + median;
	}

	@Override // It is not clear which of the criteria (wind or humidity) is more important so the order is randomly chosen
	public String calculateLaunchDay() {
		if (daysForLaunch.size() > 1) {
			
			for(int i = 0; i < daysForLaunch.size() - 1; i++) {
				
				if (daysForLaunch.size() == 1) {
					break;
				}
				
				if (Float.parseFloat(listOfData.get(daysForLaunch.get(i) - 1).get(2)) > Float.parseFloat(listOfData.get(daysForLaunch.get(i + 1) - 1).get(2))) {
					daysForLaunch.remove(i + 1);
					break;
				} else if (Float.parseFloat(listOfData.get(daysForLaunch.get(i) - 1).get(2)) < Float.parseFloat(listOfData.get(daysForLaunch.get(i + 1) - 1).get(2))){
					daysForLaunch.remove(1);
					break;
				} else if (Float.parseFloat(listOfData.get(daysForLaunch.get(i) - 1).get(3)) > Float.parseFloat(listOfData.get(daysForLaunch.get(i + 1) - 1).get(3))) {	
					daysForLaunch.remove(i + 1);
					break;	
				} else if (Float.parseFloat(listOfData.get(daysForLaunch.get(i) - 1).get(3)) < Float.parseFloat(listOfData.get(daysForLaunch.get(i + 1) - 1).get(3))) {
					daysForLaunch.remove(i);
					break;
				} 
			}
			
		} else if (daysForLaunch.size() == 0) {
			return "There are no appropriate days for launching";
		} 		
		return "The best day for launch is: " + daysForLaunch.get(0);
	}
	
	public void findAllLaunchDays() {
		for (int i = 0; i < listOfData.size(); i++) {
			temperature  =  Float.parseFloat(listOfData.get(i).get(1));
			wind  =  Float.parseFloat(listOfData.get(i).get(2));
			humidity  =  Float.parseFloat(listOfData.get(i).get(3));
			precipitation  =  Float.parseFloat(listOfData.get(i).get(4));
			lightning = listOfData.get(i).get(5);
			clouds = listOfData.get(i).get(6);
			
			if ((temperature >= 2 && temperature <= 31) && (wind <= 10) && (humidity < 60) && (precipitation == 0) && (lightning).toLowerCase().trim().equals("no") && (!(clouds.toLowerCase().trim().equals("cumulus")) && !(clouds.toLowerCase().trim().equals("nimbus")))) {
				daysForLaunch.add(Integer.parseInt(listOfData.get(i).get(0)));
			}
		} 
	}
	
	public void addLaunchDay() {
		this.findAllLaunchDays();
		CSVCalculatedData.get("Day/Parameter").add(this.calculateLaunchDay());
		CSVCalculatedData.get("Lightning").add(this.calculateLaunchDay());
		CSVCalculatedData.get("Clouds").add(this.calculateLaunchDay());
	}
	
	public List<List<String>> getListOfData () {
		return this.listOfData;
	}
	
	public Map<String, List<String>> getCSVCalculatedData () {
		return this.CSVCalculatedData;
	}
	
	public ConditionCalculator () {
		
	}
}
