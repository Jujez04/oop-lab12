package it.unibo.es1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class LogicsImpl implements Logics {

	private final List<Integer> list;

	public LogicsImpl(int size) {
		this.list = new ArrayList<>(size);	
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public List<Integer> values() {
		return List.copyOf(this.list);
	}

	@Override
	public List<Boolean> enablings() {
		return this.list.stream()
			.map(i -> i < this.list.size())
			.collect(Collectors.toList());
	}

	@Override
	public int hit(int elem) {
		int n = this.list.get(elem);
		this.list.set(elem, ++n);
		return n;
	}

	@Override
	public String result() {
		return this.list.stream()
			.map(i -> i.toString())
			.reduce("", (a, b) -> a.concat("|" + b));
	}

	@Override
	public boolean toQuit() {
		return this.list.stream()
			.allMatch(i -> i.equals(this.list.size()));
	}
}