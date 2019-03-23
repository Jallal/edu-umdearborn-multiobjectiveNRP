package thiagodnf.doupr.core.util;

import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;

public class AttributeObjectUtils {

    public static AttributeObject findByName(ClassObject cls, String attributeName) {

        if (cls == null) {
            return null;
        }

        for (AttributeObject attribute : cls.getAttributes()) {
            if (attribute.getName().equalsIgnoreCase(attributeName)) {
                return attribute;
            }
        }

        return null;
    }
}
