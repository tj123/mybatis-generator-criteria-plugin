package com.github.tj123.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Iterator;
import java.util.List;


/**
 *  解决 org.mybatis.generator.plugins.RenameExampleClassPlugin 修改不彻底问题
 */
public class CriteriaPlugin extends PluginAdapter {
	
	public boolean validate(List<String> warnings) {
		return true;
	}
	
	/**
	 * 修改xml的id
	 * @param element
	 * @param id
	 */
	private void changeElementId(XmlElement element,String id){
		Iterator<Attribute> it = element.getAttributes().iterator();
		while (it.hasNext()) {
			if (it.next().getName().equals("id")) {
				it.remove();
				break;
			}
		}
		element.addAttribute(new Attribute("id",id));
	}
	
	@Override
	public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("countByCriteria");
		return true;
	}
	
	@Override
	public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("deleteByCriteria");
		return true;
	}
	
	@Override
	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("selectByCriteria");
		return true;
	}
	
	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("selectByCriteria");
		return true;
	}
	
	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		method.setName("selectByCriteria");
		return true;
	}
	
	@Override
	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		method.setName("selectByCriteria");
		return true;
	}
	
	@Override
	public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("updateByCriteria");
		return true;
	}
	
	@Override
	public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		method.setName("updateByCriteria");
		return true;
	}
	
	@Override
	public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("updateByCriteria");
		return true;
	}
	
	@Override
	public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		method.setName("updateByCriteria");
		return true;
	}
	
	@Override
	public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		method.setName("updateByCriteriaSelective");
		return true;
	}
	
	
	//mapper 文件
	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element,"countByCriteria");
		return true;
	}
	
	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element,"deleteByCriteria");
		return true;
	}
	
	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element,"selectByCriteria");
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element,"updateByCriteriaSelective");
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element,"updateByCriteria");
		return true;
	}
	
	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		changeElementId(element,"updateByCriteria");
		return true;
	}
}
