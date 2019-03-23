package thiagodnf.doupr.core.util;

import org.apache.commons.lang3.StringUtils;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ParameterObject;

public class MethodObjectUtils {

    public static MethodObject findByName(ClassObject cls, String methodName) {

        if (cls == null) {
            return null;
        }

        for (MethodObject method : cls.getMethods()) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                return method;
            }
        }

        return null;
    }

    public static MethodObject createGetter(ClassObject parent, AttributeObject attribute) {

        MethodObject method = new MethodObject();

        method.setName("get" + StringUtils.capitalize(attribute.getName()));
        method.setReturnType(attribute.getType());

        return method;
    }

    public static MethodObject createSetter(ClassObject parent, AttributeObject attribute) {
        MethodObject method = new MethodObject();

        method.setName("set" + StringUtils.capitalize(attribute.getName()));
        method.setReturnType("void");
        method.getParameters().add(new ParameterObject(attribute.getType()));

        return method;
    }
}
