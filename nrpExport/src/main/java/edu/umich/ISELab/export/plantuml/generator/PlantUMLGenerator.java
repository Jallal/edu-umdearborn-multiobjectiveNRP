package edu.umich.ISELab.export.plantuml.generator;

import edu.umich.ISELab.export.ExportGenerator;
import edu.umich.ISELab.export.plantuml.parser.ClassPlantUMLParser;

import java.util.List;

public class PlantUMLGenerator extends ExportGenerator {

    public PlantUMLGenerator(String exportFilename) {
        super(exportFilename);
    }

    @Override
    public String getContent(Object parent, ProjectObject project, ClassObject cls) {

        List<ClassObject> classes = project.getClasses();

        StringBuilder builder = new StringBuilder();

        builder.append("@startuml").append("\n");

        for (ClassObject c : classes) {
            builder.append(new ClassPlantUMLParser().parse(classes, c));
//			builder.append(new MethodsPlantUMLParser().parse(classes, cls));
//			builder.append(new AttributesPlantUMLParser().parse(classes, cls));
//			builder.append(new GeneralizationsPlantUMLParser().parse(classes, cls));
//			builder.append(new RealizationsPlantUMLParser().parse(classes, cls));
//			builder.append(new CompositionsPlantUMLParser().parse(classes, cls));
        }

        builder.append("@enduml").append("\n");

        return builder.toString();
    }

    @Override
    protected String getSuffix() {
        return ".plantuml";
    }

    @Override
    public String getContent(Object parent, ProjectObject project) {
        // TODO Auto-generated method stub
        return null;
    }
}
