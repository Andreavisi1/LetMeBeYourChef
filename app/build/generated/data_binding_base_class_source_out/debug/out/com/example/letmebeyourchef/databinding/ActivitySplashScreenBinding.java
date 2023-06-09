// Generated by data binding compiler. Do not edit!
package com.example.letmebeyourchef.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.letmebeyourchef.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivitySplashScreenBinding extends ViewDataBinding {
  @NonNull
  public final VideoView videoView;

  protected ActivitySplashScreenBinding(Object _bindingComponent, View _root, int _localFieldCount,
      VideoView videoView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.videoView = videoView;
  }

  @NonNull
  public static ActivitySplashScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_splash_screen, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySplashScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivitySplashScreenBinding>inflateInternal(inflater, R.layout.activity_splash_screen, root, attachToRoot, component);
  }

  @NonNull
  public static ActivitySplashScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_splash_screen, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySplashScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivitySplashScreenBinding>inflateInternal(inflater, R.layout.activity_splash_screen, null, false, component);
  }

  public static ActivitySplashScreenBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivitySplashScreenBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivitySplashScreenBinding)bind(component, view, R.layout.activity_splash_screen);
  }
}
