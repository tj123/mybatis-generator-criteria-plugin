package com.github.tj123.mybatis.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Iterator;
import java.util.List;


/**
 * 解决 org.mybatis.generator.plugins.RenameExampleClassPlugin 修改不彻底问题
 */
public class CriteriaPlugin extends PluginAdapter {
	
	private static final String interfaceSearchString = "Mapper$";
	private static final String interfaceReplaceString = "Dao";
	
	private static final String methodSearchString = "Example";
	private static final String methodReplaceString = "Criteria";
	
	private static final String xmlSearchString = "Example";
	private static final String xmlReplaceString = "Criteria";
	
	private static final String parameterSearchString = "example";
	private static final String parameterReplaceString = "criteria";
	
	
	public boolean validate(List<String> warnings) {
		return true;
	}
	
	/**
	 * 修改文件名称
	 *
	 * @param interfaze
	 */
	private void changeClientFileName(Interface interfaze) {
		FullyQualifiedJavaType type = interfaze.getType();
		String fullyQualifiedName = type.getFullyQualifiedName();
		fullyQualifiedName = fullyQualifiedName.replaceAll(interfaceSearchString, interfaceReplaceString);
		FullyQualifiedJavaType newType = new FullyQualifiedJavaType(fullyQualifiedName);
		Class<? extends Interface> interfaceClass = interfaze.getClass();
		try {
			java.lang.reflect.Field typeField = interfaceClass.getDeclaredField("type");
			typeField.setAccessible(true);
			typeField.set(interfaze, newType);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改 namespace
	 * @param document
	 */
	private void changeSqlMapNamespace(Document document) {
		XmlElement rootElement = document.getRootElement();
		Iterator<Attribute> it = rootElement.getAttributes().iterator();
		String originalValue = null;
		while (it.hasNext()) {
			Attribute attribute = it.next();
			if (attribute.getName().equals("namespace")) {
				originalValue = attribute.getValue();
				it.remove();
				break;
			}
		}
		if (originalValue != null) {
			rootElement.addAttribute(new Attribute("namespace", originalValue.replaceAll(interfaceSearchString, interfaceReplaceString)));
		}
		
	}
	
	/**
	 * 修改xml的id
	 *
	 * @param element
	 */
	private void changeElementId(XmlElement element) {
		Iterator<Attribute> it = element.getAttributes().iterator();
		String originalVale = null;
		while (it.hasNext()) {
			Attribute attribute = it.next();
			if (attribute.getName().equals("id")) {
				originalVale = attribute.getValue();
				it.remove();
				break;
			}
		}
		if (originalVale != null) {
			element.addAttribute(new Attribute("id", originalVale.replaceAll(xmlSearchString,xmlReplaceString)));
		}
		
	}
	
	/**
	 * 修改参数名称
	 *
	 * @param parameter
	 * @param name
	 * @return
	 */
	private Parameter changeParameterName(Parameter parameter, String name) {
		Parameter rtn = new Parameter(parameter.getType(), name, parameter.isVarargs());
		for (String annotation : parameter.getAnnotations()) {
			rtn.addAnnotation(annotation);
		}
		return rtn;
	}
	
	/**
	 * 修改方法的参数名称
	 * 修改第 ｛count｝个参数
	 *
	 * @param method
	 */
	private void changeMethodParameterName(Method method, int count) {
		List<Parameter> parameters = method.getParameters();
		if (parameters == null || parameters.isEmpty())
			return;
		count -= 1;
		Parameter parameter = parameters.get(count);
		String newName = parameter.getName().replaceAll(parameterSearchString,parameterReplaceString);
		if (parameter != null) {
			parameters.remove(count);
			parameters.add(count, changeParameterName(parameter, newName));
		}
	}
	
	/**
	 * 默认修改第一个
	 *
	 * @param method
	 */
	private void changeMethodParameterName(Method method) {
		changeMethodParameterName(method, 1);
	}
	
	/**
	 * 修改方法名称
	 * @param method
	 */
	private void changeMethodName(Method method){
		method.setName(method.getName().replaceAll(methodSearchString,methodReplaceString));
	}
	
	@Override
	public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		changeMethodName(method);
		changeMethodParameterName(method);
		return true;
	}
	
	@Override
	public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		changeMethodName(method);
		changeMethodParameterName(method);
		return true;
	}
	
	@Override
	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		changeMethodName(method);
		changeMethodParameterName(method);
		return true;
	}
	
	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		changeMethodName(method);
		changeMethodParameterName(method);
		return true;
	}
	
	
	@Override
	public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		changeMethodName(method);
		changeMethodParameterName(method, 2);
		return true;
	}
	
	
	@Override
	public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		changeMethodName(method);
		changeMethodParameterName(method, 2);
		return true;
	}
	
	@Override
	public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		changeMethodName(method);
		changeMethodParameterName(method, 2);
		return true;
	}
	
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		changeClientFileName(interfaze);
		return true;
	}
	
	//mapper 文件
	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element);
		return true;
	}
	
	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element);
		return true;
	}
	
	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element);
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element);
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element);
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element);
		return true;
	}
	
	@Override
	public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return true;
	}
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		changeSqlMapNamespace(document);
		return true;
	}
}
