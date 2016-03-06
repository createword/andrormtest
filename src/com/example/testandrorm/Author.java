package com.example.testandrorm;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.OneToManyField;

public class Author extends Model {

	public static final QuerySet<Author> objects(Context context) {
		return objects(context, Author.class);
	}

	protected CharField mName;
	protected OneToManyField<Author, Book> mBooks;

	public Author() {
		mBooks = new OneToManyField<Author, Book>(Author.class, Book.class);
		mName = new CharField();
	}

	public QuerySet<Book> getBooks(Context context) {
		return mBooks.get(context, this);
	}

	public void setBooks(Book b) {
		mBooks.add(b);
	}

	public void setName(String name) {
		mName.set(name);
	}

}
