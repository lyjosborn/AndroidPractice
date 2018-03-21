package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String Tag = "MainActivity";

    private List<Book> bookList = new ArrayList<>();

    private Button createDB;
    private Button addData;
    private Button updateData;
    private Button deleteData;
    private Button queryAllData;
    private Button queryFirstData;
    private Button queryLimitData;
    private Button queryOffsetData;
    private Button queryConditionData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBookList();
        createDB = (Button) findViewById(R.id.create_database);
        addData = (Button) findViewById(R.id.add_data);
        updateData = (Button) findViewById(R.id.update_data);
        deleteData = (Button) findViewById(R.id.delete_data);
        queryAllData = (Button) findViewById(R.id.query_all_data);
        queryFirstData = (Button) findViewById(R.id.query_first_data);
        queryLimitData = (Button) findViewById(R.id.query_limit_data);
        queryOffsetData = (Button) findViewById(R.id.query_offset_data);
        queryConditionData = (Button) findViewById(R.id.query_condition_data);
        createDB.setOnClickListener(this);
        addData.setOnClickListener(this);
        updateData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
        queryAllData.setOnClickListener(this);
        queryFirstData.setOnClickListener(this);
        queryLimitData.setOnClickListener(this);
        queryOffsetData.setOnClickListener(this);
        queryConditionData.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.d(Tag, "onClick");
        try {
            switch (view.getId()) {
                case R.id.create_database:
                    Log.d(Tag, "click create database button");
                    LitePal.getDatabase();
                    Log.d(Tag, "LitePal.getDatabase() end");
                    break;
                case R.id.add_data:
                    Log.d(Tag, "click add data button");
                    for (Book bo : bookList) {
                        Log.d(Tag, "save " + bo.getName());
                        bo.save();
                    }

                    break;
                case R.id.update_data:
                    Log.d(Tag, "click update data button");
                    Book book = new Book();
                    book.setPrice(14.95);
//                    book.setPress("Anchor");
                    book.setToDefault("pages");
                    book.updateAll("name = ? and author = ?", "The Da Vinci Code", "Dan Brown");
                    break;
                case R.id.delete_data:
                    Log.d(Tag, "click delete data button");
                    DataSupport.deleteAll(Book.class, "price < ?", "11");
                    break;
                case R.id.query_all_data:
                    Log.d(Tag, "click query all data button");
                    List<Book> bookList2 = DataSupport.findAll(Book.class);
                    Log.d(Tag, "bookList2 " + bookList2);
                    for (Book bo : bookList2) {
                        Log.d(Tag, "book name is " + bo.getName());
                        Log.d(Tag, "book price is " + bo.getPrice());
                        Log.d(Tag, "book author is " + bo.getAuthor());
                        Log.d(Tag, "book pages is " + bo.getPages());
//                        Log.d(Tag, "book press is " + bo.getPress());
                    }
                    break;
                case R.id.query_first_data:
                    Log.d(Tag, "click query first data button");
                    Book firstBook = DataSupport.findFirst(Book.class);
                    Log.d(Tag, "book name is " + firstBook.getName());
                    Log.d(Tag, "book price is " + firstBook.getPrice());
                    Log.d(Tag, "book author is " + firstBook.getAuthor());
                    Log.d(Tag, "book pages is " + firstBook.getPages());
//                    Log.d(Tag, "book press is " + firstBook.getPress());
                    break;
                case R.id.query_limit_data:
                    Log.d(Tag, "click query limit data button");
                    List<Book> limitBookList = DataSupport.limit(3).find(Book.class);
                    for (Book limitBo : limitBookList) {
                        Log.d(Tag, "book name is " + limitBo.getName());
                        Log.d(Tag, "book price is " + limitBo.getPrice());
                        Log.d(Tag, "book author is " + limitBo.getAuthor());
                        Log.d(Tag, "book pages is " + limitBo.getPages());
//                        Log.d(Tag, "book press is " + limitBo.getPress());
                    }
                    break;
                case R.id.query_offset_data:
                    Log.d(Tag, "click query offset data button");
                    List<Book> offsetBookList = DataSupport.limit(3).offset(1).find(Book.class);
                    for (Book offsetBo : offsetBookList) {
                        Log.d(Tag, "book name is " + offsetBo.getName());
                        Log.d(Tag, "book price is " + offsetBo.getPrice());
                        Log.d(Tag, "book author is " + offsetBo.getAuthor());
                        Log.d(Tag, "book pages is " + offsetBo.getPages());
//                        Log.d(Tag, "book press is " + offsetBo.getPress());
                    }
                    break;
                case R.id.query_condition_data:
                    Log.d(Tag, "click query condition data button");
                    List<Book> conditionBookList = DataSupport.select("name", "author", "pages").where("pages > ?", "400").order("pages").limit(10).offset(1).find(Book.class);
                    for (Book conditionBo : conditionBookList) {
                        Log.d(Tag, "book name is " + conditionBo.getName());
                        Log.d(Tag, "book price is " + conditionBo.getPrice());
                        Log.d(Tag, "book author is " + conditionBo.getAuthor());
                        Log.d(Tag, "book pages is " + conditionBo.getPages());
//                        Log.d(Tag, "book press is " + conditionBo.getPress());
                    }
                    break;
                default:
                    break;
            }
        } catch(Exception e){
            Log.e(Tag, e.getMessage());
        }
    }

    private void initBookList(){
        Book book1 = new Book();
        book1.setName("The Da Vinci Code");
        book1.setAuthor("Dan Brown");
        book1.setPages(454);
        book1.setPrice(16.96);
//        book1.setPress("Unknow");

        Book book2 = new Book();
        book2.setName("The Lost Symbol");
        book2.setAuthor("Dan Brown");
        book2.setPages(510);
        book2.setPrice(10.99);
//        book2.setPress("Unknow");

        Book book3 = new Book();
        book3.setName("The the the");
        book3.setAuthor("Dan Pom");
        book3.setPages(800);
        book3.setPrice(20.99);
//        book3.setPress("Osborn");

        Book book4 = new Book();
        book4.setName("The package");
        book4.setAuthor("Mary");
        book4.setPages(300);
        book4.setPrice(28.99);
//        book4.setPress("CSTC");

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
    }
}
