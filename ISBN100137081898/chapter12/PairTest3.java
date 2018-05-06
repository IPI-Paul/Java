package chapter12;  // pair3

import ipi.*;

/**
 * PairTest3 class Listing 12.3 <br />
 * PairAlg inner class <br />
 * Pair2 inner class <br />
 * Employee class  <br />
 * Manager class <br />
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class PairTest3 {
	private static final String MAIN_CLASS = "chapter12.Chapter12";
	private static String message = "";
	
	public static void main(String[] args) {
		Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
		Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
		Pair2<Manager, Double> buddies = new Pair2<>(ceo, cfo);
		printBuddies(buddies);
		
		ceo.setBonus(1000000);
		cfo.setBonus(500000);
		Manager[] managers = {ceo, cfo};
				
		Pair2<Employee, Double> result = new Pair2<>();
		minmaxBonus(managers, result);
		System.out.println("Min-Max");
		System.out.println("first: " + result.getFirst().getName() + " " + result.getfirstBonus() 
				+ ", second: " + result.getSecond().getName() + " " + result.getSecondBonus());
		maxminBonus(managers, result);
		System.out.println("Max-Min");
		System.out.println("first: " + result.getFirst().getName() + " " + result.getfirstBonus()  
				+ ", second: " + result.getSecond().getName() + " " + result.getSecondBonus());
		Views.openWindowOpener(MAIN_CLASS, message);
	}
	
	public static void printBuddies(Pair2<? extends Employee, ?> p) {
		Employee first = p.getFirst();
		Employee second = p.getSecond();
		System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
	}
	
	public static void minmaxBonus(Manager[] a, Pair2<? super Manager, ? super Double> result) {
		if (a == null || a.length == 0) return;
		Manager min = a[0];
		Manager max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (min.getBonus() > a[i].getBonus()) min = a[i];
			if (max.getBonus() < a[i].getBonus()) max = a[i];
		}
		result.SetFirst(min, min.getBonus());
		result.setSecond(max, max.getBonus());
	}
	
	public static void maxminBonus(Manager[] a, Pair2<? super Manager, ? super Double> result) {
		minmaxBonus(a, result);
		PairAlg.swapHelper(result); // OK -- swapHelper captures wildcard type
	}
}


class PairAlg {
	public static boolean hasNulls(Pair2<?, ?> p) {
		return p.getFirst() == null || p.getSecond() == null;
	}
	
	public static void swap(Pair2<?, ?> p) {swapHelper(p);}
	
	public static <T, U> void swapHelper(Pair2<T, U> p) {
		T t = p.getFirst();
		U tb = p.getfirstBonus();
		p.SetFirst(p.getSecond(), p.getSecondBonus());
		p.setSecond(t, tb);
	}
}


class Pair2<T, U> {
	private T first;
	private T second;
	private U firstBonus;
	private U secondBonus;
	
	public Pair2() { first = null; second = null; }
	public Pair2(T first, T second) { this.first = first; this.second = second; }
	
	public T getFirst() { return first; }
	public U getfirstBonus() {return firstBonus;}
	public T getSecond() { return second; }
	public U getSecondBonus() {return secondBonus;}
	
	public void SetFirst(T newValue, U bonus) { first = newValue; firstBonus = bonus;}
	public void setSecond(T newValue, U bonus) { second = newValue; secondBonus = bonus;}
}
