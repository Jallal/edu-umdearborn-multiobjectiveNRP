package thiagodnf.doupr.export.json.generator;

import thiagodnf.doupr.export.ExportGenerator;

import java.util.List;
import java.util.stream.Collectors;

public abstract class JSONAbstractGenerator extends ExportGenerator {

    public JSONAbstractGenerator(String filename) {
        super(filename);
    }

    protected List<String> jsonify(List<String> list) {
        return list.stream().map(e -> "\"" + e + "\"").collect(Collectors.toList());
    }
}
