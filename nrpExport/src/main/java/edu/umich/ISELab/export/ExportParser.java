package edu.umich.ISELab.export;

import java.util.List;

public abstract class ExportParser {

    public abstract String parse(List<ClassObject> classes, ClassObject cls);
}
