package it.unibo.es2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics {

    private final List<List<String>> mat;

    public LogicsImpl(final int size) {
        this.mat = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.mat.add(new ArrayList<>(Collections.nCopies(size, " ")));
        }
    }

    @Override
    public String hit(Pair<Integer, Integer> elem) {
        final String str = this.mat.get(elem.getY()).get(elem.getX()).equals("*") ? " " : "*";
        this.mat.get(elem.getY()).set(elem.getX(), str);
        return str;
    }

    @Override
    public boolean toQuit() {
        return toQuitRow() || toQuitCol();
    }

    private boolean toQuitRow() {
        return IntStream.range(0, this.mat.size())
            .anyMatch(i -> IntStream.range(0, this.mat.size())
            .allMatch(j -> this.mat.get(j).get(i).equals("*")));
    }

    private boolean toQuitCol() {
        return this.mat.stream().anyMatch(l -> l.stream().allMatch(s -> s.equals("*")));
    }
}
