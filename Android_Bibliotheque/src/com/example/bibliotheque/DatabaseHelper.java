package com.example.bibliotheque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	public static final String DBName="livre.db";
	public static final String Table_Name = "livre_table";
	public static final String column1="ID_Livre";
	public static final String column2="Nom_Livre";
	public static final String column3="Nom_Auteur";
	public static final String column4="Date_Apparition";
	
	
	public DatabaseHelper(Context context) {
		super(context, DBName, null, 1);
		SQLiteDatabase db = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub	
		db.execSQL("create table "+Table_Name+"(ID_Livre Integer Primary key autoincrement, Nom_Livre TEXT, Nom_Auteur Text, Date_Apparition Text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("Drop table if Exists "+Table_Name);
		onCreate(db);
	}
	
	public boolean insertData(String Nom_Livre, String Nom_Auteur, String Data_Apparition){
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues ct = new ContentValues();
		ct.put(column2, Nom_Livre);
		ct.put(column3, Nom_Auteur);
		ct.put(column4, Data_Apparition);
		
		long rst = db.insert(Table_Name, null, ct);
		if(rst==-1)
			return false;
		else
			return true;
	}
	
	public Cursor getALLData(){
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("select * from "+Table_Name, null);
		return cur;
	}

}
