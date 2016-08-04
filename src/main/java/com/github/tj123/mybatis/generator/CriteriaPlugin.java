package com.github.tj123.mybatis.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Iterator;
import java.util.List;


/**
 * 解决 org.mybatis.generator.plugins.RenameExampleClassPlugin 修改不彻底问题
 */
public class CriteriaPlugin extends PluginAdapter {
	
	public boolean validate(List<String> warnings) {
		return true;
	}
	
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		return null;
	}
	
	/**
	 * 修改文件名称
	 * @param interfaze
	 */
	private void changeFileName(Interface interfaze) {
		System.out.println(interfaze.getType().getShortName());
		FullyQualifiedJavaType type = interfaze.getType();
		
	}
	
	/**
	 * 修改xml的id
	 *
	 * @param element
	 * @param id
	 */
	private void changeElementId(XmlElement element, String id) {
		Iterator<Attribute> it = element.getAttributes().iterator();
		while (it.hasNext()) {
			if (it.next().getName().equals("id")) {
				it.remove();
				break;
			}
		}
		element.addAttribute(new Attribute("id", id));
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
	 * @param name
	 */
	private void changeMethodParameterName(Method method, String name, int count) {
		List<Parameter> parameters = method.getParameters();
		if (parameters == null || parameters.isEmpty())
			return;
		count -= 1;
		Parameter parameter = parameters.get(count);
		if (parameter != null) {
			parameters.remove(count);
			parameters.add(count, changeParameterName(parameter, name));
		}
	}
	
	/**
	 * 默认修改第一个
	 *
	 * @param method
	 * @param name
	 */
	private void changeMethodParameterName(Method method, String name) {
		changeMethodParameterName(method, name, 1);
	}
	
	
	@Override
	public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("countByCriteria");
		changeMethodParameterName(method, "criteria");
		return true;
	}
	
	@Override
	public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("deleteByCriteria");
		changeMethodParameterName(method, "criteria");
		return true;
	}
	
	@Override
	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("selectByCriteria");
		changeMethodParameterName(method, "criteria");
		return true;
	}
	
	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("selectByCriteria");
		changeMethodParameterName(method, "criteria");
		return true;
	}
	
	
	@Override
	public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("updateByCriteria");
		changeMethodParameterName(method, "criteria", 2);
		return true;
	}
	

	@Override
	public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("updateByCriteria");
		changeMethodParameterName(method, "criteria", 2);
		return true;
	}
	
	@Override
	public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("updateByCriteriaSelective");
		changeMethodParameterName(method, "criteria", 2);
		return true;
	}
	
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		changeFileName(interfaze);
		return true;
	}
	
	//mapper 文件
	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element, "countByCriteria");
		return true;
	}
	
	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element, "deleteByCriteria");
		return true;
	}
	
	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element, "selectByCriteria");
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element, "updateByCriteriaSelective");
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element, "updateByCriteria");
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element, "updateByCriteria");
		return true;
	}
	
	
}
