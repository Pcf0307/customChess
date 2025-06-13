package com.pcf.base_model;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<Binding extends ViewDataBinding> extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected Binding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,this.getClass().getSimpleName() + " " + this.hashCode() + ": onCreate");
        Log.d(TAG,this.getClass().getSimpleName() + " " + "TaskId: " + this.getTaskId());
        createBinging();
        binding.setLifecycleOwner(this);
        setContentView(binding.getRoot());
        onCreateData();
    }

    @SuppressWarnings("unchecked")
    private void createBinging(){
        Class<Binding> vbClass = getVbClass();
        if (vbClass == null){
            return;
        }
        try {
            Method method = vbClass.getMethod("inflate", LayoutInflater.class);
            binding = (Binding) method.invoke(null,getLayoutInflater());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private Class<Binding> getVbClass(){
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        if (parameterizedType == null){
            return null;
        }
        return (Class<Binding>) parameterizedType.getActualTypeArguments()[0];
    }
    protected abstract void onCreateData();

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,this.getClass().getSimpleName() + ": onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,this.getClass().getSimpleName() + ": onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,this.getClass().getSimpleName() + ": onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,this.getClass().getSimpleName() + " " + this.hashCode() + ": onStop");
        Log.d(TAG,this.getClass().getSimpleName() + " " + "TaskId: " + this.getTaskId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,this.getClass().getSimpleName() + ": onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,this.getClass().getSimpleName() + " " + this.hashCode() + ": onDestroy");
        Log.d(TAG,this.getClass().getSimpleName() + " " + "TaskId: " + this.getTaskId());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,this.getClass().getSimpleName() + ": onNewIntent");
    }
}
