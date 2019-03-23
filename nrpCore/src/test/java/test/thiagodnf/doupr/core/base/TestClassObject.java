package test.java.test.thiagodnf.doupr.core.base;

import org.junit.Test;
import thiagodnf.doupr.core.base.AttributeObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject.Visibility;
import thiagodnf.doupr.core.base.MethodObject;
import thiagodnf.doupr.core.base.ParameterObject;
import thiagodnf.doupr.core.util.ClassObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestClassObject {

    protected ParameterObject getParameter(String type) {
        ParameterObject parameter = new ParameterObject();

        parameter.setType(type);

        return parameter;
    }

    protected AttributeObject getAttribute(Visibility visibility, String type, String name, List<String> templates) {
        AttributeObject attribute = new AttributeObject();

        attribute.setName(name);
        attribute.setVisibility(visibility);
        attribute.setType(type);
        attribute.setTemplates(templates);

        return attribute;
    }

    protected MethodObject getMethod(String returnType, String name, List<ParameterObject> parameters) {
        MethodObject method = new MethodObject();

        method.setName(name);
        method.setReturnType(returnType);
        method.setParameters(parameters);

        return method;
    }

    @Test
    public void shouldRemoveAClassName() {
        List<ClassObject> classes = new ArrayList<>();

        classes.add(new ClassObject("Class1"));
        classes.add(new ClassObject("Class2"));
        classes.add(new ClassObject("Class3"));
        classes.add(new ClassObject("Class4"));
        classes.add(new ClassObject("Class5"));

        List<ClassObject> newClasses = ClassObjectUtils.removeByName(classes, "Class3");

        assertEquals(newClasses.size(), 4);
        assertTrue(newClasses.get(0).getName().equalsIgnoreCase("Class1"));
        assertTrue(newClasses.get(1).getName().equalsIgnoreCase("Class2"));
        assertTrue(newClasses.get(2).getName().equalsIgnoreCase("Class4"));
        assertTrue(newClasses.get(3).getName().equalsIgnoreCase("Class5"));
    }

    @Test
    public void shouldFindByNameAClass() {
        List<ClassObject> classes = new ArrayList<>();

        classes.add(new ClassObject("Class1"));
        classes.add(new ClassObject("Class2"));

        ClassObject cls = ClassObjectUtils.findByName(classes, "Class2");

        assertNotNull(cls);
        assertEquals(cls.getName(), "Class2");
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowAnExceptionCallingRemoveByNameWhenUnknownClassName() {
        List<ClassObject> classes = new ArrayList<>();

        classes.add(new ClassObject("Class1"));
        classes.add(new ClassObject("Class2"));

        ClassObjectUtils.removeByName(classes, "Class3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionCallingRemoveByNameWhenEmptyClassName() {
        List<ClassObject> classes = new ArrayList<>();
        ClassObjectUtils.removeByName(classes, "");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowAnExceptionCallingRemoveByNameWhenNullClassName() {
        List<ClassObject> classes = new ArrayList<>();
        ClassObjectUtils.removeByName(classes, null);
    }

    public void shouldReturnNullCallingFindByNameWhenUnknownClassName() {
        List<ClassObject> classes = new ArrayList<>();

        classes.add(new ClassObject("Class1"));

        assertNull(ClassObjectUtils.findByName(classes, "Class2"));
    }

//	@Test(expected = IllegalArgumentException.class)
//	public void shouldThrowAnExceptionCallingFindByNameWhenEmptyClassName() {
//		List<ClassObject> classes = new ArrayList<>();
//		ClassObjectUtils.findByName(classes, "");
//	}

//	@Test(expected = NullPointerException.class)
//	public void shouldThrowAnExceptionCallingFindByNameWhenNullClassName() {
//		List<ClassObject> classes = new ArrayList<>();
//		ClassObjectUtils.findByName(classes, null);
//	}

    @Test
    public void shouldCopyAListOfClass() {
        List<ClassObject> classes = new ArrayList<>();

        classes.add(new ClassObject("Class1"));
        classes.add(new ClassObject("Class2"));

        List<ClassObject> copy = ClassObjectUtils.copy(classes);

        assertNotNull(copy);
        assertEquals(copy.size(), 2);
        assertEquals(copy.get(0).getName(), "Class1");
        assertEquals(copy.get(1).getName(), "Class2");

        copy.clear();

        assertEquals(copy.size(), 0);
        assertEquals(classes.size(), 2);
        assertEquals(classes.get(0).getName(), "Class1");
        assertEquals(classes.get(1).getName(), "Class2");
    }

    @Test
    public void shouldCloneAnObject() {
        ClassObject main = new ClassObject();
        main.setName("Class1");

        main.getAttributes().add(getAttribute(Visibility.PUBLIC, "Void", "name", Arrays.asList()));

        ParameterObject p1 = getParameter("Double");
        ParameterObject p2 = getParameter("Integer");

        main.getMethods().add(getMethod("Void", "print", Arrays.asList(p1, p2)));

        ClassObject copy = new ClassObject(main);

        copy.setName("Class2");
        copy.setVisibility("Private");
        copy.setAbstract(true);
        copy.getMethods().clear();
        copy.getAttributes().clear();

        assertFalse(main.getVisibility().equalsIgnoreCase(copy.getVisibility()));
        assertFalse(main.getName().equalsIgnoreCase(copy.getName()));
        assertFalse(main.getMethods().isEmpty());
        assertFalse(main.getAttributes().isEmpty());
        assertFalse(main.isAbstract());
    }
}
