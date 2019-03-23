package thiagodnf.doupr.core.util;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject;
import thiagodnf.doupr.core.base.PackageObject;
import thiagodnf.doupr.core.base.ProjectObject;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProjectObjectUtils {

    public static ProjectObject copy(ProjectObject project) {

        checkNotNull(project, "The 'project' instance cannot be null");

        return new ProjectObject(project);
    }

    public static ClassObject findByName(ProjectObject project, String className) {

        for (PackageObject pkg : project.getPackages()) {
            for (ClassObject cls : pkg.getClasses()) {
                if (cls.getName().equalsIgnoreCase(className)) {
                    return cls;
                }
            }
        }

        return null;
    }

    public static Map<String, ElementObject> mapElement(ProjectObject project) {

        Map<String, ElementObject> map = new HashMap<>();

        for (PackageObject pkg : project.getPackages()) {

            for (ClassObject cls : pkg.getClasses()) {

                for (ElementObject el : cls.getAttributes()) {
                    map.put(el.getIdentifier(), el);
                }

                for (ElementObject el : cls.getMethods()) {
                    map.put(el.getIdentifier(), el);
                }
            }
        }

        return map;
    }

    public static Map<String, ClassObject> mapParent(ProjectObject project) {

        Map<String, ClassObject> map = new HashMap<>();

        for (PackageObject pkg : project.getPackages()) {

            for (ClassObject cls : pkg.getClasses()) {

                for (ElementObject el : cls.getAttributes()) {
                    map.put(el.getIdentifier(), cls);
                }

                for (ElementObject el : cls.getMethods()) {
                    map.put(el.getIdentifier(), cls);
                }
            }
        }

        return map;
    }

    public static Map<String, String> getClassNames(ProjectObject project) {

        Map<String, String> classNames = new HashMap<>();

        for (PackageObject pkg : project.getPackages()) {

            for (ClassObject cls : pkg.getClasses()) {
                classNames.put(cls.getName(), cls.getName());
            }
        }

        return classNames;
    }
}
