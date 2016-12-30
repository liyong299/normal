package com.ly.java.thrift.anno;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({ "com.ly.java.thrift.anno.SAASServerMap" })
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MyProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		System.out.println("Test log in MyProcessor.process");
		System.out.println(roundEnv.toString());

		for (TypeElement typeElement : annotations) { // 遍历annotations获取annotation类型
			for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) { // 使用roundEnv.getElementsAnnotatedWith获取所有被某一类型注解标注的元素，依次遍历
				// 在元素上调用接口获取注解值
				String annoValue = element.getAnnotation(SAASServerMap.class).value();

				System.out.println("value = " + annoValue);
			}
		}
		return false;
	}

}