package org.zzgsc.com.bmobdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
public class MainActivity extends AppCompatActivity {
    private StudentBean s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBmob();

    }

    private void initBmob() {
        //eaee2f25ebd5b50e95c846ffb2adf5c0
        Bmob.initialize(this, "eaee2f25ebd5b50e95c846ffb2adf5c0");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }
    public void add(View v){
         s=new StudentBean();
        s.setAge(30);
        s.setName("gsc");
         s.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    Toast.makeText(getApplication(),"add sucesss!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplication(),"add Fail!"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void selectData(View v){
        BmobQuery<StudentBean> q=new BmobQuery<>();
        q.addWhereEqualTo("Name","fdy");
         q.findObjects(new FindListener<StudentBean>() {
         @Override
         public void done(List<StudentBean> list, BmobException e) {
             for(StudentBean s:list){
                 Log.i("gsc",""+s.getAge());
             }

         }
     });

    }
    public void UpdateData(View view){
        s=new StudentBean();
        s.setAge(90);
       s.update("43c105f1ac",new UpdateListener() {
        @Override
        public void done(BmobException e) {
            System.out.println("----------ok---"+e.getMessage());
        }
    });
    }
    public void Del(View v){
        s=new StudentBean();
        s.setObjectId("edea491b3a");
        s.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(getApplicationContext(),"OK"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"OK"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
