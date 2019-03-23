package test.java.test.thiagodnf.doupr.core.base;

import org.junit.Test;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;

public class TestAttributeObject {

    @Test
    public void shouldCloneAnObject() {
        AttributeObject main = new AttributeObject();

        main.setName("name");
        main.setType("Customer");
        main.setVisibility(Visibility.PROTECTED);
        main.setTemplates(Arrays.asList("Double"));

        AttributeObject copy = new AttributeObject(main);

        copy.setName("age");
        copy.setType("Order");
        copy.setVisibility(Visibility.PUBLIC);
        copy.setTemplates(Arrays.asList("String", "Integer"));
        copy.setStatic(true);
        copy.setFinal(true);

        assertFalse(main.getName().equalsIgnoreCase(copy.getName()));
        assertFalse(main.getType().equalsIgnoreCase(copy.getType()));
        assertFalse(main.getVisibility() == copy.getVisibility());
        assertFalse(main.getTemplates().size() == copy.getTemplates().size());
        assertFalse(main.isStatic());
        assertFalse(main.isFinal());
        assertFalse(copy.getTemplates().contains("Double"));
        assertFalse(main.getTemplates().contains("String"));
        assertFalse(main.getTemplates().contains("Integer"));
    }
}
