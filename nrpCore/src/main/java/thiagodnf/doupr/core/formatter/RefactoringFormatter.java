package thiagodnf.doupr.core.formatter;

import thiagodnf.doupr.core.refactoring.NrpBase;
import thiagodnf.doupr.core.util.StringUtils;

import java.util.Collections;

public class RefactoringFormatter extends Formatter {

    public RefactoringFormatter(Object object) {
        super(object);
    }

    @Override
    public String toString() {

        NrpBase refactoring = (NrpBase) object;

        StringBuilder builder = new StringBuilder();

        builder.append(refactoring.getClass().getSimpleName());
        builder.append("(");

        builder.append(refactoring.getClass1() == null ? "" : refactoring.getClass1());
        builder.append(";");

        builder.append(refactoring.getClass2() == null ? "" : refactoring.getClass2());
        builder.append(";");

//		builder.append(refactoring.getElement() == null ? "" : refactoring.getElement());
//		builder.append(";");

        Collections.sort(refactoring.getAttributes());
        Collections.sort(refactoring.getMethods());

        builder.append("[");
        builder.append(StringUtils.join(refactoring.getAttributes(), "|"));
        builder.append("]");
        builder.append(";");

        builder.append("[");
        builder.append(StringUtils.join(refactoring.getMethods(), "|"));
        builder.append("]");
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String toSimpleString() {

        NrpBase refactoring = (NrpBase) object;

        StringBuilder builder = new StringBuilder();

        builder.append(refactoring.getClass().getSimpleName());
        builder.append("(");

        builder.append(refactoring.getClass1() == null ? "" : StringUtils.getSimpleName(refactoring.getClass1()));
        builder.append(";");

        builder.append(refactoring.getClass2() == null ? "" : StringUtils.getSimpleName(refactoring.getClass2()));
        builder.append(";");

//		builder.append(refactoring.getElement() == null ? "" : refactoring.getElement());
//		builder.append(";");

        Collections.sort(refactoring.getAttributes());
        Collections.sort(refactoring.getMethods());

        builder.append("[");
        builder.append(StringUtils.join(refactoring.getAttributes(), "|"));
        builder.append("]");
        builder.append(";");

        builder.append("[");
        builder.append(StringUtils.join(refactoring.getMethods(), "|"));
        builder.append("]");
        builder.append(")");

        return builder.toString();
    }

}
