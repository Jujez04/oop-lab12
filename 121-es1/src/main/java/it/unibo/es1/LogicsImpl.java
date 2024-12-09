package it.unibo.es1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class LogicsImpl implements Logics {

	private final List<Integer> list;

	public LogicsImpl(final int size) {
		this.list = new ArrayList<>(Collections.nCopies(size, 0));
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public List<Integer> values() {
		return Collections.unmodifiableList(this.list);
	}

	@Override
	public List<Boolean> enablings() {
		return this.list.stream()
			.map(i -> i < this.list.size())
			.toList();
	}

	@Override
	public int hit(final int elem) {
		final int n = this.list.get(elem) + 1;
		this.list.set(elem, n);
		return n;
	}

	@Override
	public String result() {
		return this.list.stream()
			.map(i -> i.toString())
			.collect(Collectors.joining("|", "<<" , ">>"));
	}

	@Override
	public boolean toQuit() {
		return this.list.stream()
			.allMatch(i -> i.equals(this.list.get(0)));
	}
}