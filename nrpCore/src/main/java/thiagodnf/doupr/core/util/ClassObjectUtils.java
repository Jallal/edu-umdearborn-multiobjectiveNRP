package thiagodnf.doupr.core.util;

import thiagodnf.doupr.core.base.ClassObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ClassObjectUtils {

    public static ClassObject findByName(List<ClassObject> classes, String className) {

        if (className == null || className.isEmpty()) {
            return null;
        }

        for (ClassObject cls : classes) {
            if (cls.getName().equalsIgnoreCase(className)) {
                return cls;
            }
        }

        return null;
    }

    public static List<ClassObject> removeByName(List<ClassObject> classes, String className) {

        checkNotNull(classes, "The 'classes' cannot be null");
        checkNotNull(className, "The 'className' cannot be null");
        checkArgument(!className.isEmpty(), "The 'className' cannot be empty");

        ClassObject clsToRemove = null;

        for (ClassObject cls : classes) {
            if (cls.getName().equalsIgnoreCase(className)) {
                clsToRemove = cls;
            }
        }

        if (clsToRemove == null) {
            throw new RuntimeException("The className was not found");
        }

        classes.remove(clsToRemove);

        return classes;
    }

    public static List<String> getSuperClasses(List<ClassObject> classes, ClassObject cls) {

        checkNotNull(classes, "The 'classes' cannot be null");
        checkNotNull(cls, "The 'cls' cannot be null");

        List<String> superClasses = new ArrayList<>();

        if (cls.hasSuperClass()) {

            ClassObject clsSuperClass = findByName(classes, cls.getSuperClass());

            superClasses.add(clsSuperClass.getName());

            superClasses.addAll(getSuperClasses(classes, clsSuperClass));
        }

        return superClasses;
    }

    public static List<String> getSubClasses(List<ClassObject> classes, ClassObject cls) {

        checkNotNull(classes, "The 'classes' cannot be null");
        checkNotNull(cls, "The 'cls' cannot be null");

        List<String> subClasses = new ArrayList<>();

        for (ClassObject tempCls : classes) {
            if (tempCls.hasSuperClass() && tempCls.getSuperClass().equalsIgnoreCase(cls.getName())) {
                subClasses.add(tempCls.getName());
                subClasses.addAll(getSubClasses(classes, tempCls));
            }
        }

        return subClasses;
    }

    public static List<ClassObject> copy(List<ClassObject> classes) {

        checkNotNull(classes, "The 'classes' cannot be null");

        List<ClassObject> copy = new ArrayList<>();

        for (ClassObject cls : classes) {
            copy.add(new ClassObject(cls));
        }

        for (ClassObject cls : copy) {
            cls.setSuperClasses(ClassObjectUtils.getSuperClasses(copy, cls));
            cls.setSubClasses(ClassObjectUtils.getSubClasses(copy, cls));
        }

        return copy;
    }

    public static List<String> getSimpleNames(List<ClassObject> classes) {
        return classes.stream().map(ClassObject::getSimpleName).collect(Collectors.toList());
    }

    public static List<String> getNames(List<ClassObject> classes) {
        return classes.stream().map(ClassObject::getName).collect(Collectors.toList());
    }
}
