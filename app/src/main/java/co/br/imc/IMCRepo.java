package co.br.imc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class IMCRepo {
    SQLiteDatabase db;

    public IMCRepo(Context context){
        Conexao cn = new Conexao(context);
        db = cn.getWritableDatabase();
    }

    public void salvar(IMC imc){
        ContentValues valores = new ContentValues();
        valores.put("peso", imc.getPeso());
        valores.put("altura", imc.getAltura());
        db.insert("imc", null, valores);
    }
    public ArrayList<IMC> carregar(){
        ArrayList<IMC> imc = new ArrayList<IMC>();
        Cursor cur = db.rawQuery("SELECT * FROM imc", null);
        if(cur.moveToFirst()){
            do{
                IMC i = new IMC();
                i.setPeso(cur.getString(0));
                i.setAltura(cur.getString(1));
                imc.add(i);
            }while (cur.moveToNext());
        }
        return imc;
    }
}
