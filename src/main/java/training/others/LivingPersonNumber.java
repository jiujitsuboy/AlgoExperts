package training.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LivingPersonNumber {

	public static void main(String[] args) {

		int[] birthdayYear = new int[] { 1986, 1982, 1990 };
		int[] deathYear = new int[] { 1995, 1996, 1992 };

		MaxYears maxYearsLiving = getMayYearsLiving(birthdayYear, deathYear);
				
		System.out.println(String.format("nro:%d", maxYearsLiving.getNumber()));
		
		Integer[] years = maxYearsLiving.getYears();
		
		for(int year=0;year<years.length;year++) {
			System.out.println(years[year]);
		}
	}

	public static LivingPersonNumber.MaxYears getMayYearsLiving(int[] birthdayYear, int[] deathYear) {

		Map<Integer, Integer> yearsCount = new HashMap<Integer, Integer>();
		List<Integer> years = new ArrayList<Integer>();
		int maxNumber = 0;

		for (int year = 0; year < birthdayYear.length; year++) {			
			int initialYear = birthdayYear[year];
			
			while(initialYear<=deathYear[year]) {
				int increment = 1;
				if (yearsCount.containsKey(initialYear)) {
					increment += yearsCount.get(initialYear);
				}
				yearsCount.put(initialYear, increment);
				initialYear++;
			}			
		}
		
		for(Entry<Integer, Integer> year: yearsCount.entrySet()) {
			if(year.getValue()>maxNumber) {
				years.clear();
				years.add(year.getKey());
				maxNumber = year.getValue(); 
			}
			else if(year.getValue()==maxNumber) {
				years.add(year.getKey());
			}
		}
		
		return new MaxYears(maxNumber, (Integer[])years.toArray(new Integer[years.size()]));
	}
	
	private static class MaxYears{
		private int number;
		private Integer[] years;
		
		public MaxYears(int number,Integer[] years) {
			this.number = number;
			this.years = years;
		}
		
		public int getNumber() {
			return number;
		}
		public Integer[] getYears() {
			return years;
		}
		
		
	}

}
