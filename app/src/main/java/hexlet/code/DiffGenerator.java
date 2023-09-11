package hexlet.code;

import hexlet.code.Utils.parser.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DiffGenerator {
    public static List<DiffAccumulator> generate(String file1, String file2, Parser parser) throws IOException {
        final Map<String, Object> before = parser.parse(file1);
        final Map<String, Object> after = parser.parse(file2);

        Map<String, Object> combined = new TreeMap<>();
        combined.putAll(before);
        combined.putAll(after);

        List<DiffAccumulator> diffs = new ArrayList<>();
        combined.keySet()
                .forEach(i -> {
                    DiffAccumulator diff;
                    if (!after.containsKey(i)) {
                        diff = new DiffAccumulator(i, "removed", before.get(i));
                        diffs.add(diff);
                    } else if (after.containsKey(i) && before.containsKey(i)) {
                        if (after.get(i) == before.get(i)) {
                            diff = new DiffAccumulator(i, "unchanged", before.get(i));
                            diffs.add(diff);
                        } else if (after.get(i) == null || before.get(i) == null) {
                            diff = new DiffAccumulator(i, "updated", before.get(i), after.get(i));
                            diffs.add(diff);
                        } else if (after.get(i).equals(before.get(i))) {
                            diff = new DiffAccumulator(i, "unchanged", before.get(i));
                            diffs.add(diff);
                        } else {
                            diff = new DiffAccumulator(i, "updated", before.get(i), after.get(i));
                            diffs.add(diff);
                        }
                    } else if (!before.containsKey(i)) {
                        diff = new DiffAccumulator(i, "added", after.get(i));
                        diffs.add(diff);
                    }
                });
        return diffs;
    }
}

