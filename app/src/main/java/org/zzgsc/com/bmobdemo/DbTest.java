package org.zzgsc.com.bmobdemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;
import me.itangqi.greendao.DaoMaster;
import me.itangqi.greendao.DaoSession;
import me.itangqi.greendao.Note;
import me.itangqi.greendao.NoteDao;

/**
 * Created by Administrator on 2016/10/14.
 */

public class DbTest extends AppCompatActivity {
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);
        initdb();

    }

    /**
     * 添加数据
     */
    public void add(View view) {
        Note note = new Note(null, "gsc", "Learn orm 框架", new Date());
        daoSession.getNoteDao().insert(note);
        Toast.makeText(getApplicationContext(), "insert Ok!", 1).show();
    }

    /**
     * @param view 查询数据
     */
    public void select(View view) {
        //条件查询
        //   Query<Note> gsc = daoSession.getNoteDao().queryBuilder().where(NoteDao.Properties.Text.eq("gsc")).build();
        Query<Note> build = daoSession.getNoteDao().queryBuilder().build();
        List<Note> list = build.list();
        for (Note N : list) {
            String comment = N.getComment();
            Date date = N.getDate();
            String text = N.getText();
            System.out.println("------------" + N.getId());
        }
    }

    public void Del(View view) {
        //删除所有
        // daoSession.getNoteDao().deleteAll();
        //根据key 主键删除
        daoSession.getNoteDao().deleteByKey(10L);
        System.out.println("删除成功！");
    }

    public void update(View view) {
//修改以前要查询
        Query<Note> gsc = daoSession.getNoteDao().queryBuilder().where(NoteDao.Properties.Text.eq("gsc")).build();
        List<Note> list = gsc.list();
        for (Note n : list) {
            n.setText("fdy");
            daoSession.getNoteDao().update(n);
            Toast.makeText(this, "修改成功！！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 初始化数据库
     */
    private void initdb() {
        String dbName = "stu";//数据库名字
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, dbName, null);
        SQLiteDatabase db = helper.getWritableDatabase();//获取数据库对象
        daoMaster = new DaoMaster(db);//获取数据库的管理者
        daoSession = daoMaster.newSession();
    }
}
