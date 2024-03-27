package com.vinay.java8.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortingAndAveragingStreamOperationsOnEmployee2 {
	public static void main(String[] args) {

		List<Employee2> listEmp = new ArrayList<>();
		listEmp.add(new Employee2(1, "vinay", "M", 10000d));
		listEmp.add(new Employee2(2, "sharma", "M", 50000d));
		listEmp.add(new Employee2(3, "Niha", "F", 30000d));
		listEmp.add(new Employee2(4, "Harika", "F", 40000d));
		
		//get salary list >10000
		List<Double> collect5 = listEmp.stream().map(e -> e.getSalary()).filter(e -> e>10000).collect(Collectors.toList());
		System.out.println(collect5);
		
		// how many female and male employees in the organization
		Map<String, Long> collect = listEmp.stream()
				.collect(Collectors.groupingBy(emp -> emp.getGender(), Collectors.counting()));
		System.out.println(collect);

		// averaging salary of genders
		Map<String, Double> collect2 = listEmp.stream().collect(
				Collectors.groupingBy(emp -> emp.getGender(), Collectors.averagingDouble(emp -> emp.getSalary())));
		System.out.println(collect2);

		// sort based on salary -natural sort(ascending)
		List<Employee2> collect3 = listEmp.stream().sorted(Comparator.comparing(Employee2::getSalary))
				.collect(Collectors.toList());
		System.out.println(collect3);

		// sort based on salary -customised sort(descending)
		List<Employee2> collect4 = listEmp.stream().sorted(Comparator.comparing(Employee2::getSalary).reversed())
				.collect(Collectors.toList());
		System.out.println(collect4);

	}
}
