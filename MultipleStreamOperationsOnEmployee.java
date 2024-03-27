package com.vinay.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vinay.java8.streams.test.A;

public class MultipleStreamOperationsOnEmployee extends A {

	public static void main(String[] args) {
		List<Employee> empList = new ArrayList<Employee>();

		empList.add(new Employee(1, "vinay", 29, 10000));
		empList.add(new Employee(2, "katakam", 28, 20000));
		empList.add(new Employee(3, "sharma", 24, 40000));
		empList.add(new Employee(4, "abhi", 30, 30000));

		// **** print list with have >= 40000
		empList.stream().filter(emp -> emp.getSal() >= 40000).forEach(emp -> System.out.println(emp));

		// **** print salary with have >= 40000

		empList.stream().filter(emp -> emp.getSal() >= 40000).map(emp -> emp.getSal())
				.forEach(sal -> System.out.println(sal));

		// ***** sum of salary ******\\
		Double collect = empList.stream().collect(Collectors.summingDouble(emp -> emp.getSal()));
		System.out.println(collect);

		// **** print highest salary

		Employee employeeHigh = empList.stream().max((num1, num2) -> num1.getSal() > num2.getSal() ? 1 : -1).get();
		System.out.println(employeeHigh);

		// **** print lowest salary
		Employee employeeLow = empList.stream().max((num1, num2) -> num1.getSal() < num2.getSal() ? 1 : -1).get();
		System.out.println(employeeLow);

		// basic list to set

		Set<Employee> toSet = empList.stream().collect(Collectors.toSet());
		System.out.println(toSet);

		// based on salary it will get and convert to set

		// Set<Double> toSetcollect = empList.stream().map(emp ->
		/* emp.getSal()).filter(sal -> sal>2000).collect(Collectors.toSet()); */
		// this or this login will works //
		Set<Double> toSetcollect = empList.stream().filter(sal -> sal.getSal() > 2000).map(emp -> emp.getSal())
				.collect(Collectors.toSet());

		System.out.println(toSetcollect);

		// convert list to map
		Map<Integer, String> toMapCollect = empList.stream()
				.collect(Collectors.toMap(eid -> eid.getId(), ename -> ename.getName()));

		System.out.println(toMapCollect);

		// Using METHOD REFERENCE

		List<Double> collect1 = empList.stream().filter(sal -> sal.getSal() > 30000).map(Employee::getSal)
				.collect(Collectors.toList());

		System.out.println(collect1);

		Map<String, Double> mapCollect = empList.stream().filter(sal -> sal.getSal() > 30000)
				.collect(Collectors.toMap(ename -> ename.getName(), esal -> esal.getSal()));

		System.out.println(mapCollect);

	}
}
