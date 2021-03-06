package com.spring.gear.context.support;

import com.spring.gear.beans.factroy.annotation.AutowiredAnnotationProcessor;
import com.spring.gear.beans.factroy.config.ConfigurableBeanFactory;
import com.spring.gear.beans.factroy.support.DefaultBeanFactory;
import com.spring.gear.beans.factroy.xml.XmlBeanDefinitionReader;
import com.spring.gear.context.ApplicationContext;
import com.spring.gear.core.io.Resource;
import com.spring.gear.utils.ClassUtil;

/**
 * 模板方法,在构造方法中先实现，不在各个子类中实现
 * @author hp
 *
 */
public abstract class AbstractApplicationContext implements ApplicationContext{

	private DefaultBeanFactory factory; 
	
	private ClassLoader classLoader;
	
	public AbstractApplicationContext(String configFile) {
		this(configFile,ClassUtil.getDefaultClassLoader());
	}
	
	public AbstractApplicationContext(String configFlie,ClassLoader cl) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = this.getResourceByPath(configFlie);
		reader.loadBeanDefinition(resource);
		factory.setClassLoader(cl);
		registerBeanPostProcessors(factory);
	}
	
	public Object getBean(String BeanId) {
		return this.factory.getBean(BeanId);
	}
	
	protected abstract Resource getResourceByPath(String configFlie);
	

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public ClassLoader getClassLoader() {
		return this.classLoader!=null ? this.classLoader : ClassUtil.getDefaultClassLoader();
	}
	
	protected void registerBeanPostProcessors(ConfigurableBeanFactory beanFactory) {
		AutowiredAnnotationProcessor postProcessor = new AutowiredAnnotationProcessor();
		postProcessor.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(postProcessor);
				
	}
}
