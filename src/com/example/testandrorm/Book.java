package com.example.testandrorm;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.ForeignKeyField;

public class Book extends Model {

	public static final QuerySet<Book> objects(Context context) {
		return objects(context, Book.class);
	}

	protected CharField mTitle;
	protected ForeignKeyField<Author> mAuthor;

	public Book() {
		super();

		mTitle = new CharField(80);
		mAuthor = new ForeignKeyField<Author>(Author.class);
	}

	public void setName(String value) {
		mTitle.set(value);
	}

	public String getName() {
		return mTitle.get();
	}
}