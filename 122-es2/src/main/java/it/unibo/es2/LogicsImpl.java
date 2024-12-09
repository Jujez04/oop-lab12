package it.unibo.es2;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LogicsImpl implements Logics {

    private final Map<Pair<Integer,Integer>, String> map;

    public LogicsImpl(final int size) {
        this.map = new HashMap<>();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.map.put(new Pair<Integer,Integer>(i, j), " ");
            }
        }
    }

    @Override
    public String hit(Pair<Integer, Integer> elem) {
        final String str = this.map.get(elem).equals("*") ? " " : "*";
        this.map.put(elem, str);
        return str;
    }

    @Override
    public boolean toQuit() {
        return getStreamValue(this.map)
            .allMatch(s -> s.equals("*"));
    }

    private static <E, O> Stream<O> getStreamValue(Map<Pair<E, E>, O> mapIn) {
        return mapIn.entrySet().stream()
            .map(e -> e.getValue());
    }
}
