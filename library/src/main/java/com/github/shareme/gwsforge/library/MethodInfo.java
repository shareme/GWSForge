package com.github.shareme.gwsforge.library;

import com.github.shareme.gwsforge.library.widgets.Button;
import com.github.shareme.gwsforge.library.widgets.CheckBox;
import com.github.shareme.gwsforge.library.widgets.Spinner;
import com.github.shareme.gwsforge.library.widgets.Title;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodInfo {

  static final int VIEW_BUTTON = 1;
  static final int VIEW_CHECKBOX = 2;
  static final int VIEW_SPINNER = 3;

  static final int INVALID = -1;

  private final Method method;

  private int viewType = INVALID;
  private Object data;
  private String title;
  private Object instance;

  private MethodInfo(Method method, Object instance) {
    this.method = method;
    this.instance = instance;

    parseMethodAnnotations();
  }

  static MethodInfo newInstance(Method method, Object instance) {
    return new MethodInfo(method, instance);
  }

  private void parseMethodAnnotations() {
    Annotation[] annotations = method.getAnnotations();
    for (Annotation annotation : annotations) {
      Class<? extends Annotation> annotationType = annotation.annotationType();

      if (annotationType == Title.class) {
        title = ((Title) annotation).value();
        continue;
      }

      if (annotationType == Button.class) {
        viewType = VIEW_BUTTON;
        continue;
      }

      if (annotationType == CheckBox.class) {
        viewType = VIEW_CHECKBOX;
        continue;
      }

      if (annotationType == Spinner.class) {
        data = ((Spinner) annotation).value();
        viewType = VIEW_SPINNER;
      }
    }
  }

  Method getMethod() {
    return method;
  }

  int getViewType() {
    return viewType;
  }

  Object getData() {
    return data;
  }

  String getTitle() {
    return title;
  }

  Object getInstance() {
    return instance;
  }
}
