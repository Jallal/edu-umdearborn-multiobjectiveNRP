package edu.umich.ISELab.export.json.generator;

import edu.umich.ISELab.export.ExportGenerator;

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
