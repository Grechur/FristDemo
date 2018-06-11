package com.butterknife.complier;


import com.butterknife.annotation.BindView;
import com.butterknife.annotation.OnClick;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by zz on 2018/5/9.
 */
@AutoService(Processor.class)
public class ButterKnifeProcessor extends AbstractProcessor{

    private Filer mFiler;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    public SourceVersion getSupportSourceVersion(){
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation:getAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getAnnotations(){
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(BindView.class);
        annotations.add(OnClick.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        System.out.println("---------------->");
//        System.out.println("---------------->");
//        System.out.println("---------------->");
//        System.out.println("---------------->");
//        System.out.println("---------------->");
        //有注解就会进来
        //.，使activity与属性集合一一对应
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        Map<Element,List<Element>> elementMap = new LinkedHashMap<>();
        for (Element element : elements) {
            Element enclosingElement = element.getEnclosingElement();
            List<Element> viewBindElements = elementMap.get(enclosingElement);
            if(viewBindElements == null){
                viewBindElements = new ArrayList<>();
                elementMap.put(enclosingElement,viewBindElements);
            }
            viewBindElements.add(element);
        }




        //生成代码
        for (Map.Entry<Element,List<Element>> entry: elementMap.entrySet()) {
            Element enclosingElement = entry.getKey();//activity
            List<Element> viewBindElements = entry.getValue();//view集合


            String activityClassNameStr = enclosingElement.getSimpleName().toString();
            ClassName activityClassName = ClassName.bestGuess(activityClassNameStr);
            ClassName unbinderClassName = ClassName.get("com.butterknife","Unbinder");

            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(activityClassNameStr+"_ViewBinding")
                    .addModifiers(Modifier.FINAL,Modifier.PUBLIC)
                    .addSuperinterface(unbinderClassName)
                    .addField(activityClassName,"target",Modifier.PRIVATE);

            //实现unbind方法
            ClassName callSuperClassName = ClassName.get("android.support.annotation","CallSuper");
            MethodSpec.Builder unbindMethodBuilder = MethodSpec.methodBuilder("unbind")
                    .addAnnotation(Override.class)
                    .addAnnotation(callSuperClassName)
                    .addModifiers(Modifier.PUBLIC,Modifier.FINAL);

            unbindMethodBuilder.addStatement("$T target = this.target",enclosingElement);
            unbindMethodBuilder.addStatement("if (target == null) throw new IllegalStateException(\"Bindings already cleared\")");

            //添加构造函数
            MethodSpec.Builder constructMethod = MethodSpec.constructorBuilder()
                    .addParameter(activityClassName,"target")
                    .addModifiers(Modifier.PUBLIC);

            //添加代码this.target = target;
            constructMethod.addStatement("this.target = target");

            for (Element viewBindElement : viewBindElements) {
                String filedName = viewBindElement.getSimpleName().toString();
                ClassName utilsClassName = ClassName.get("com.butterknife","Utils");
                int viewId = viewBindElement.getAnnotation(BindView.class).value();
                constructMethod.addStatement("target.$L = $T.findViewById(target,$L)",filedName,utilsClassName,viewId);

                unbindMethodBuilder.addStatement("target.$L = null",filedName);
            }

            //把方法加入到类中
            classBuilder.addMethod(unbindMethodBuilder.build());
            classBuilder.addMethod(constructMethod.build());
            //生成类，看下效果
            try {
                String packageName = mElementUtils.getPackageOf(enclosingElement).getQualifiedName().toString();
                JavaFile.builder(packageName,classBuilder.build())
                        .addFileComment("butterKnife 自动生成")
                        .build().writeTo(mFiler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
