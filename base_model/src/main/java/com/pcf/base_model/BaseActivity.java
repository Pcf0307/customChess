package com.pcf.base_model;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<Binding extends ViewDataBinding> extends AppCompatActivity {
    protected Binding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
