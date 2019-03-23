package test.java.test.thiagodnf.doupr.core.refactoring;

import org.junit.Before;
import org.junit.Test;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.PullUpField;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.core.util.FileReaderUtils;
import thiagodnf.doupr.core.util.ProjectObjectUtils;
import thiagodnf.doupr.core.util.RefactoringUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestPullUpField {

    protected ProjectObject project;

    @Before
    public void init() throws IOException {
        this.project = FileReaderUtils.read(new File("src/test/resources/pull-up-field/project.blocks"));
    }

    @Test
    public void shouldRetornar() throws Exception {

        ProjectObject expected = FileReaderUtils.read(new File("src/test/resources/pull-up-field/test-1-expected.blocks"));

        Refactoring refactoring = new PullUpField("Class_B", "Class_A", "Attribute_2");

        ProjectObject copy = ProjectObjectUtils.copy(project);

        RefactoringUtils.apply(copy, refactoring);

        System.out.println(copy);

        assertEquals(1, 1);

    }
}
