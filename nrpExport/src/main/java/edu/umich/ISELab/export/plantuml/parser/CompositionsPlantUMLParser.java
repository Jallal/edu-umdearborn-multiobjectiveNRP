package edu.umich.ISELab.export.plantuml.parser;

import edu.umich.ISELab.export.ExportParser;

import java.util.List;

public class CompositionsPlantUMLParser extends ExportParser {

    @Override
    public String parse(List<ClassObject> classes, ClassObject cls) {

        StringBuilder builder = new StringBuilder();

//		List<String> compositionClasses = getCompositionClasses(classes, cls);
//
//		for (String c : compositionClasses) {
//			builder.append(cls.getName());
//			builder.append(" *-- ");
//			builder.append(c);
//			builder.append("\n");
//		}

        return builder.toString();
    }

//	public List<String> getCompositionClasses(List<ClassObject> classes, ClassObject cls) {
//
//		List<String> classNames = BlockUtils.getClassNames(classes);
//
//		List<String> compositionClass = new ArrayList<>();
//
//		for (AttributeObject attribute : cls.getAttributes()) {
//			for (String name : classNames) {
//				if (attribute.toString().contains(name) && !compositionClass.contains(name)) {
//					compositionClass.add(name);
//				}
//			}
//		}
//
//		return compositionClass;
//	}

}
