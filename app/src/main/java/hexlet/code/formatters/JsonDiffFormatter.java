package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffAccumulator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class JsonDiffFormatter implements DiffFormatter {
    public String format(List<DiffAccumulator> diffs) throws Exception {

        Map<String, Object> diffsMap = new TreeMap<>();

        diffs.forEach(o -> {
            if (o.getStatus().equals("removed") || o.getStatus().equals("added")) {
                diffsMap.put(o.getKey(), o.getValue());
            } else if (o.getStatus().equals("updated")) {
                List<Object> objList = new ArrayList<>();
                objList.add(o.getValueBefore());
                objList.add(o.getValueAfter());
                diffsMap.put(o.getKey(), objList);
            } else {
                diffsMap.put(o.getKey(), o.getValue());
            }
        });

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(diffsMap);
    }
}
