package thiagodnf.doupr.export.dot.parser;

import thiagodnf.doupr.core.base.CallObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.util.ClassObjectUtils;
import thiagodnf.doupr.export.ExportParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DependencyDotParser extends ExportParser {

    @Override
    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

        List<CallObject> calls = new ArrayList<>();

//		for (MethodObject m : cls.getMethods()) {
//			calls.addAll(m.getCalls());
//		}

        builder.append(convert(classes, calls));

        return builder.toString();
    }

    /**
     * This class converts a list of callIns and callOuts into a string
     *
     * @param classes list of callIns and callOuts
     * @return calls content
     */
    public String convert(List<ClassObject> classes, List<CallObject> calls) {

        StringBuilder builder = new StringBuilder();

        Map<String, Integer> counter = new HashMap<String, Integer>();

        // We first count the number of relationship
        for (CallObject call : calls) {

            String source = ClassObjectUtils.findByName(classes, call.getSourceClass()).getSimpleName();
            String destiny = ClassObjectUtils.findByName(classes, call.getTargetClass()).getSimpleName();

            String key = source + "___" + destiny;

            if (counter.containsKey(key)) {
                counter.put(key, counter.get(key) + 1);
            } else {
                counter.put(key, 1);
            }
        }

        // Now we add the information about the edge and the number of calls
        for (Entry<String, Integer> entry : counter.entrySet()) {

            String[] part = entry.getKey().split("___");

            builder.append(part[1]);
            builder.append(" -> ");
            builder.append(part[0]);
            builder.append("[label=\"");
            builder.append(entry.getValue());
            builder.append("\"]");
            builder.append("\n");
        }

        return builder.toString();
    }

}
