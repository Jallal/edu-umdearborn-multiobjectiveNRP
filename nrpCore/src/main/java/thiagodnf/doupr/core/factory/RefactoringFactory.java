package thiagodnf.doupr.core.factory;

import thiagodnf.doupr.core.refactoring.DecreaseFieldSecurity;
import thiagodnf.doupr.core.refactoring.DecreaseMethodSecurity;
import thiagodnf.doupr.core.refactoring.EncapsulateField;
import thiagodnf.doupr.core.refactoring.ExtractClass;
import thiagodnf.doupr.core.refactoring.ExtractSubClass;
import thiagodnf.doupr.core.refactoring.ExtractSuperClass;
import thiagodnf.doupr.core.refactoring.IncreaseFieldSecurity;
import thiagodnf.doupr.core.refactoring.IncreaseMethodSecurity;
import thiagodnf.doupr.core.refactoring.MoveField;
import thiagodnf.doupr.core.refactoring.MoveMethod;
import thiagodnf.doupr.core.refactoring.PullUpMethod;
import thiagodnf.doupr.core.refactoring.PushDownField;
import thiagodnf.doupr.core.refactoring.PushDownMethod;
import thiagodnf.doupr.core.refactoring.Refactoring;

public class RefactoringFactory {

    public static Refactoring getRefactoring(String name) {

        if (name.equalsIgnoreCase("Move Method") || name.equalsIgnoreCase(MoveMethod.class.getSimpleName())) {
            return new MoveMethod();
        }
        if (name.equalsIgnoreCase("Move Field") || name.equalsIgnoreCase(MoveField.class.getSimpleName())) {
            return new MoveField();
        }
        if (name.equalsIgnoreCase("Extract Class") || name.equalsIgnoreCase(ExtractClass.class.getSimpleName())) {
            return new ExtractClass();
        }
        if (name.equalsIgnoreCase("Push Down Field") || name.equalsIgnoreCase(PushDownField.class.getSimpleName())) {
            return new PushDownField();
        }
        if (name.equalsIgnoreCase("Push Down Method") || name.equalsIgnoreCase(PushDownMethod.class.getSimpleName())) {
            return new PushDownMethod();
        }
        if (name.equalsIgnoreCase("Extract Sub Class") || name.equalsIgnoreCase(ExtractSubClass.class.getSimpleName())) {
            return new ExtractSubClass();
        }
        if (name.equalsIgnoreCase("Pull Up Field") || name.equalsIgnoreCase(thiagodnf.doupr.core.refactoring.PullUpField.class.getSimpleName())) {
            return new thiagodnf.doupr.core.refactoring.PullUpField();
        }
        if (name.equalsIgnoreCase("Pull Up Method") || name.equalsIgnoreCase(PullUpMethod.class.getSimpleName())) {
            return new PullUpMethod();
        }
        if (name.equalsIgnoreCase("Extract Super Class") || name.equalsIgnoreCase(ExtractSuperClass.class.getSimpleName())) {
            return new ExtractSuperClass();
        }
        if (name.equalsIgnoreCase("Encapsulate Field") || name.equalsIgnoreCase(EncapsulateField.class.getSimpleName())) {
            return new EncapsulateField();
        }

        if (name.equalsIgnoreCase("Increase Field Security") || name.equalsIgnoreCase(IncreaseFieldSecurity.class.getSimpleName())) {
            return new IncreaseFieldSecurity();
        }
        if (name.equalsIgnoreCase("Decrease Field Security") || name.equalsIgnoreCase(DecreaseFieldSecurity.class.getSimpleName())) {
            return new DecreaseFieldSecurity();
        }
        if (name.equalsIgnoreCase("Increase Method Security") || name.equalsIgnoreCase(IncreaseMethodSecurity.class.getSimpleName())) {
            return new IncreaseMethodSecurity();
        }
        if (name.equalsIgnoreCase("Decrease Method Security") || name.equalsIgnoreCase(DecreaseMethodSecurity.class.getSimpleName())) {
            return new DecreaseMethodSecurity();
        }

        return null;
    }
}
