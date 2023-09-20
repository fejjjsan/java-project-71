package hexlet.code;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Objects;

public class DiffBuilder {
    public static List<DiffAccumulator> build(Map<String, Object> before, Map<String, Object> after) {

        Map<String, Object> combined = new TreeMap<>();
        combined.putAll(before);
        combined.putAll(after);

        List<DiffAccumulator> diffs = new ArrayList<>();
        combined.keySet()
                .forEach(i -> {
                    DiffAccumulator diff = new DiffAccumulator();
                    if (!after.containsKey(i)) {
                        diff = new DiffAccumulator(i, "removed", before.get(i));
                    } else if (!before.containsKey(i)) {
                        diff = new DiffAccumulator(i, "added", after.get(i));
                    } else if (after.containsKey(i) && before.containsKey(i)) {
                        if (Objects.equals(after.get(i), before.get(i))) {
                            diff = new DiffAccumulator(i, "unchanged", before.get(i));
                        } else if (!Objects.equals(after.get(i), before.get(i))) {
                            diff = new DiffAccumulator(i, "updated", before.get(i), after.get(i));
                        }
                    }
                    diffs.add(diff);
                });
        return diffs;
    }
}
