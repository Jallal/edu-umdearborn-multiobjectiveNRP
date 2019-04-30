/*
 * Copyright (c) 2018.
 * Author: Vahid Alizadeh
 * Email: alizadeh@umich.edu
 */

package thiagodnf.doupr.core.util;

import com.google.common.io.Files;
import org.apache.commons.io.Charsets;
import org.apache.log4j.Logger;
import thiagodnf.doupr.core.base.Block;
import thiagodnf.doupr.core.base.CallObject;
import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.callgraph.CallGraph;
import thiagodnf.doupr.core.factory.NrpFactory;
import thiagodnf.doupr.core.refactoring.NrpBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is a utility one responsible for managing the instance files. You
 * may, for instance, read a instance file and convert it to a {@link ProjectObject}.
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-09
 */
public class FileReaderUtils {

	/**
	 * This method reads a .blocks file and converts it for a {@link ProjectObject}
	 *
	 * @param filename the name (or directory) of instance that it should read
	 * @return a project instance
	 * @throws IOException if there is some problem with the reading
	 */

	protected static final Logger LOGGER = Logger.getLogger(FileReaderUtils.class);

	public static ProjectObject read(File filename) throws IOException {

		// All lines will be put into a block
		List<Block> blocks = new ArrayList<>();

		// We read the file and put the lines into an array
		List<String> lines = Files.readLines(filename, Charsets.UTF_8);

		Block block = new thiagodnf.doupr.core.base.Block();

		// Parse the lines of the file
		for (String line : lines) {

			if (line.startsWith("StartClass")) {
				block = new Block();
			} else if (line.startsWith("EndClass")) {
				blocks.add(block);
			} else {
				block.getLines().add(line);
			}
		}

		ProjectObject project = new ProjectObject();

		// Convert the block object into a class one

		List<thiagodnf.doupr.core.base.ClassObject> classes = new ArrayList<>();
		List<thiagodnf.doupr.core.base.CallObject> calls = new ArrayList<>();

		for (Block b : blocks) {
			ClassObject cls = b.getCls();
			List<CallObject> call = b.getCalls();

			if (cls != null) {
				classes.add(cls);
				calls.addAll(call);
			}
		}

		// Split the list of classes into a list of packages. While to that,
		// find all super and sub classes given a class

		Map<String, List<thiagodnf.doupr.core.base.ClassObject>> map = new HashMap<>();

		for (thiagodnf.doupr.core.base.ClassObject cls : classes) {

			// Find the super and sub classes
			cls.setSuperClasses(ClassObjectUtils.getSuperClasses(classes, cls));
			cls.setSubClasses(ClassObjectUtils.getSubClasses(classes, cls));

			// Find the correct package
			String packageName = StringUtils.getPackageName(cls.getName());

			if (!map.containsKey(packageName)) {
				map.put(packageName, new ArrayList<>());
			}

			map.get(packageName).add(cls);
		}

		CallGraph graph = project.getCallGraph();

		for (thiagodnf.doupr.core.base.CallObject call : calls) {

			ClassObject c1 = ClassObjectUtils.findByName(classes, call.getSourceClass());
			ClassObject c2 = ClassObjectUtils.findByName(classes, call.getTargetClass());

			ElementObject sourceMethod = MethodObjectUtils.findByName(c1, call.getSourceMethod());
			ElementObject targetElement = null;

			if (call.isCallForAttribute()) {
				targetElement = AttributeObjectUtils.findByName(c2, call.getTargetName());
			} else if (call.isCallForMethod()) {
				targetElement = MethodObjectUtils.findByName(c2, call.getTargetName());
			}

			if (targetElement == null) {
				continue;
			}
			try {
				if (call.isCallForMethod()) {
//                    LOGGER.info(call.toString());
					graph.addCall(sourceMethod.getIdentifier(), targetElement.getIdentifier());
				} else if (call.isCallForAttribute() && call.isRead()) {
					graph.addReadCall(sourceMethod.getIdentifier(), targetElement.getIdentifier());
				} else if (call.isCallForAttribute() && call.isWrite()) {
					graph.addWriteCall(sourceMethod.getIdentifier(), targetElement.getIdentifier());
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}

		}

		for (String key : map.keySet()) {
			project.getPackages().add(new thiagodnf.doupr.core.base.PackageObject(key, map.get(key)));
		}

		return project;
	}

	public static List<NrpBase> readRefactorings(File file) throws IOException {

		List<NrpBase> refactorings = new ArrayList<>();

		List<String> lines = Files.readLines(file, Charsets.UTF_8);

		// Use regex to find the pattern into a refactoring
		Pattern p = Pattern.compile("(.*)\\((.*);(.*);\\[(.*)\\];\\[(.*)\\]\\)");

		for (String line : lines) {

			String[] refactoringsStr = line.split(",");

			for (String refac : refactoringsStr) {

				Matcher m = p.matcher(refac);

				while (m.find()) {

					String name = m.group(1).trim();
					String class1 = m.group(2).equalsIgnoreCase("null") ? null : m.group(2).trim();
					String class2 = m.group(3).equalsIgnoreCase("null") ? null : m.group(3).trim();
					String[] attributes = m.group(4).isEmpty() ? new String[0] : m.group(4).split("\\|");
					String[] methods = m.group(5).isEmpty() ? new String[0] : m.group(5).split("\\|");

					NrpBase refactoring = NrpFactory.getNrpOptimization(name);

					refactoring.setClass1(class1);
					refactoring.setClass2(class2);
					refactoring.setAttributes(Arrays.asList(attributes));
					refactoring.setMethods(Arrays.asList(methods));
					refactoring.setMustDefineActors(false);

					refactorings.add(refactoring);
				}
			}
		}

		return refactorings;
	}

	public static List<double[]> readWeights(File file) throws IOException {

		List<String> lines = Files.readLines(file, Charsets.UTF_8);

		List<double[]> weights = new ArrayList<>();

		for (String line : lines) {
			weights.add(ConverterUtils.toDoubleArray(line.split("\t")));
		}

		return weights;
	}
}
