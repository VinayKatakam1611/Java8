package com.vinay.java8.streams;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.vinay.java8.streams.Person.Sex;

public class CollectOfCollectorsOperationsOnPerson {
	public static void main(String[] args) {
		List<Person> roster = new ArrayList();
		roster.add(new Person("Fred", IsoChronology.INSTANCE.date(1980, 6, 20), Person.Sex.MALE, "fred@example.com"));
		roster.add(new Person("Jane", IsoChronology.INSTANCE.date(1990, 7, 15), Person.Sex.FEMALE, "jane@example.com"));
		roster.add(
				new Person("George", IsoChronology.INSTANCE.date(1991, 8, 13), Person.Sex.MALE, "george@example.com"));
		roster.add(new Person("Bob", IsoChronology.INSTANCE.date(2000, 9, 12), Person.Sex.MALE, "bob@example.com"));

		// Names of male members with collect operation

		List<String> collect = roster.stream().filter(p -> p.getGender() == Person.Sex.MALE).map(p -> p.getName())
				.collect(Collectors.toList());
		System.out.println(collect);
		System.out.println("----------------");

		// Group members by gender ::: by using groupingBy

		Map<Sex, List<Person>> collect2 = roster.stream().collect(Collectors.groupingBy(p -> p.getGender()));
		System.out.println(collect2);
		System.out.println("----------------");

		// Group "names" by gender ::: by using groupingBy({2-para})

		Map<Sex, List<String>> collect3 = roster.stream().collect(
				Collectors.groupingBy(p -> p.getGender(), Collectors.mapping(p -> p.getName(), Collectors.toList())));
		System.out.println(collect3);
		System.out.println("----------------");

		// Total age by gender ::: by using reducing
		// step 1 :identity, step 2 : mapper, step 3 : operation //
		Integer collect4 = roster.stream().collect(Collectors.reducing(0, p -> p.getAge(), Integer::sum));
		System.out.println(collect4);
		System.out.println("----------------");

		// Average age by gender

		Map<Sex, Double> collect5 = roster.stream()
				.collect(Collectors.groupingBy(p -> p.getGender(), Collectors.averagingInt(p -> p.getAge())));

		for (Map.Entry<Person.Sex, Double> map : collect5.entrySet()) {
			System.out.println(map.getKey() + "   " + map.getValue());
		}
		System.out.println("----------------");

		// Average age by specific gender
		Map<Sex, Double> collect6 = roster.stream().filter(p -> p.getGender() == Person.Sex.MALE)
				.collect(Collectors.groupingBy(p -> p.getGender(), Collectors.averagingInt(p -> p.getAge())));

		System.out.println(collect6);
	}

}
