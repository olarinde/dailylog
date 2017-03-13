package org.koweg.demo.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.internal.engine.DefaultParameterNameProvider;

public class RestAnnotationParameterNameProvider extends DefaultParameterNameProvider {

    @Override
    public List<String> getParameterNames(Method method) {
        Method iMethod = null;
        Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
        for (Class<?> interfaze : interfaces) {
            try {
                iMethod = interfaze.getMethod(method.getName(), method.getParameterTypes());
                break;
            } catch (NoSuchMethodException e) {
                // ignore it!
            }

        }

        Annotation[][] annotationsByParam = method.getParameterAnnotations();
        Annotation[][] iAnnotationsByParam = annotationsByParam;
        if (iMethod != null) {
            iAnnotationsByParam = iMethod.getParameterAnnotations();
        }
        List<String> names = new ArrayList<String>(annotationsByParam.length);
        for (int i = 0; i < annotationsByParam.length; i++) {
            Annotation[] annotations = annotationsByParam[i];
            Annotation[] iAnnotations = iAnnotationsByParam[i];
            String name = getParamName(annotations);
            if (name == null) {
                name = getParamName(iAnnotations);
                if (name == null) {
                    Class<?> parameterType = method.getParameterTypes()[i];
                    XmlType xmlType = parameterType.getAnnotation(XmlType.class);
                    if (xmlType != null && xmlType.name() != null) {
                        name = xmlType.name();
                    } else {
                        name = "arg" + (i + 1);
                    }
                }
            }

            names.add(name);
        }

        return names;
    }

    private static String getParamName(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == QueryParam.class) {
                return "queryParam." + QueryParam.class.cast(annotation).value();
            }
            else if (annotation.annotationType() == PathParam.class) {
                return "pathParam." + PathParam.class.cast(annotation).value();
            }
            else if (annotation.annotationType() == Valid.class) {
                return "payload";
            }

        }

        return null;
    }
}
