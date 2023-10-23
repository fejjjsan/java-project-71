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
                .forEach(key -> {
                    DiffAccumulator diff = new DiffAccumulator();
                    if (!after.containsKey(key)) {
                        diff = new DiffAccumulator(key, "removed", before.get(key));
                    } else if (!before.containsKey(key)) {
                        diff = new DiffAccumulator(key, "added", after.get(key));
                    } else if (Objects.equals(after.get(key), before.get(key))) {
                        diff = new DiffAccumulator(key, "unchanged", before.get(key));
                    } else if (!Objects.equals(after.get(key), before.get(key))) {
                        diff = new DiffAccumulator(key, "updated", before.get(key), after.get(key));
                    }
                    diffs.add(diff);
                });
        return diffs;
    }
}
