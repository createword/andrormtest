package com.example.testandrorm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Filter;
import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	student s;
	public String FILE_SAVE_DIR = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + File.separator + "MyDate";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		File f = new File(FILE_SAVE_DIR);
		if (!f.exists()) {
			f.mkdirs();
		}
		List<Class<? extends Model>> models = new ArrayList<Class<? extends Model>>();
		models.add(student.class);
		models.add(Book.class);
		models.add(Author.class);
		DatabaseAdapter.setDatabaseName(FILE_SAVE_DIR + "/name.db");
		DatabaseAdapter adapter = DatabaseAdapter
				.getInstance(getApplicationContext());
		adapter.setModels(models);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			s = new student();
			s.setName("Book name");
			s.save(this);

			break;
		case R.id.search:
			Filter filter = new Filter();
			// filter.contains("mName", "Bo");//包含的人容
			filter.is("mName", "Book name");// 指定要查询的内容
			QuerySet<student> st = student
					.objects(getApplicationContext()).filter(filter);
			for (student s : st) {
				Toast.makeText(getApplication(), s.getName(), 1).show();
			}
			break;
		case R.id.addbook_au:
			Author brown = new Author();
			brown.setName("Dan Brown");
		
			brown.save(this);
			 
			Author grisham = new Author();
			grisham.setName("John Grisham");
			grisham.save(this);
		
			
			Book b=new Book();
			b.setName("1");	
			b.save(this);
			brown.setBooks(b);
			break;
		case R.id.queryall:
			// Get the author with id = 1
			Author authors1 = Author.objects(this).get(1); // 查询一条

			// Querying for books
			QuerySet<Book> books = authors1.getBooks(this).all()
					.orderBy("-mTitle");

			for (Book book : books) {
				Toast.makeText(getApplication(), book.getId(), 1).show();
			}
			Toast.makeText(getApplication(), authors1.getBooks(getBaseContext())+"", 1).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
