package com.example.testandrorm;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.ManyToManyField;

public class student extends Model {
	protected CharField mName;
	public static final QuerySet<student> objects(Context context) {
        return objects(context, student.class);
    }
	public student() {
		mName = new CharField();
	
	}

	public void setName(String name) {
		mName.set(name);
	}

	public String getName() {
		return mName.get();
	}
}