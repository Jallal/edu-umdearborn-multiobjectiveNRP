package test.java.test.thiagodnf.doupr.core.base;

import org.junit.Test;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ParameterObject;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestMethodObject {

    @Test
    public void shouldCloneAnObject() {
        ParameterObject p1 = new ParameterObject();
        ParameterObject p2 = new ParameterObject();

        p1.setType("String");
        p1.setTemplates(Arrays.asList("Double", "String"));

        p2.setType("MyClass");
        p2.setTemplates(Arrays.asList("Double"));

        MethodObject main = new MethodObject();
        main.setVisibility(Visibility.PROTECTED);
        main.setName("print");
        main.setReturnType("Void");
        main.getParameters().add(p1);
        main.getParameters().add(p2);

        MethodObject copy = new MethodObject(main);
        main.setVisibility(Visibility.PRIVATE);
        copy.setName("setName");
        copy.setReturnType("String");
        copy.getParameters().clear();

        assertFalse(main.getVisibility() == copy.getVisibility());
        assertFalse(main.getName().equalsIgnoreCase(copy.getName()));
        assertFalse(main.getReturnType().equalsIgnoreCase(copy.getReturnType()));
        assertFalse(main.getParameters().size() == copy.getParameters().size());
        assertFalse(main.getParameters().isEmpty());
        assertTrue(copy.getParameters().isEmpty());
    }
}
