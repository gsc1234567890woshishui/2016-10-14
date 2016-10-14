package org.zzgsc.com.bmobdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
/**
 * Created by Administrator on 2016/10/13.
 */
public class Test extends AppCompatActivity {
   private PtrClassicFrameLayout pf;
    private RecyclerView rv;
    private List<String> strsData=new ArrayList<>();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
      pf = (PtrClassicFrameLayout) findViewById(R.id.pf);
        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
       final String  strs[]={"http://b.hiphotos.baidu.com/image/h%3D200/sign=7e9d1a99a9345982da8ae2923cf5310b/d009b3de9c82d15825ffd75c840a19d8bd3e42da.jpg","http://b.hiphotos.baidu.com/image/h%3D200/sign=234bd32e09d79123ffe093749d355917/0823dd54564e925838c205c89982d158ccbf4e26.jpg","http://h.hiphotos.baidu.com/image/pic/item/43a7d933c895d143b233160576f082025aaf074a.jpg","http://b.hiphotos.baidu.com/image/h%3D200/sign=9d3833093f292df588c3ab158c305ce2/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg","http://b.hiphotos.baidu.com/image/h%3D200/sign=9d3833093f292df588c3ab158c305ce2/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg","http://b.hiphotos.baidu.com/image/h%3D200/sign=9d3833093f292df588c3ab158c305ce2/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg",};
       for(int i=0;i<strs.length;i++){
          strsData.add(strs[i]);
       }
        //strsData = Arrays.asList(strs);
        myAdapter = new MyAdapter(this, (ArrayList<String>) strsData);
        rv.setAdapter(myAdapter);
        pf.setPtrHandler(new PtrDefaultHandler2() {
            //上拉加载
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                Log.i("gsc", "onLoadMoreBegin");
                pf.postDelayed(new Runnable() {//套路
                    @Override
                    public void run() {
                        String Str1="http://g.hiphotos.baidu.com/image/h%3D200/sign=011fe800b08f8c54fcd3c22f0a282dee/c2cec3fdfc03924578c6cfe18394a4c27c1e25e8.jpg";
                        myAdapter.add(0,Str1);
                        pf.refreshComplete();
                    }
                }, 2000);
            }
            //下拉刷新
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.i("gsc", "onRefreshBegin");
                pf.postDelayed(new Runnable() {//套路
                    @Override
                    public void run() {
                        String Str1="http://c.hiphotos.baidu.com/image/h%3D200/sign=540039b04fed2e73e3e9812cb701a16d/f7246b600c3387448982f948540fd9f9d72aa0bb.jpg";
                        myAdapter.add(1,Str1);
                        System.out.println("-----------dnagqian--"+Thread.currentThread().getName());
                        pf.refreshComplete();
                    }
                }, 2000);
            }
        });

    }

}
