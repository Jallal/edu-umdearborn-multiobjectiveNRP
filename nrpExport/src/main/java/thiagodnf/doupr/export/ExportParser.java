package thiagodnf.doupr.export;

import thiagodnf.doupr.core.base.ClassObject;

import java.util.List;

public abstract class ExportParser {

    public abstract String parse(List<ClassObject> classes, ClassObject cls);
}
