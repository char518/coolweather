package com.charr.CharWeather.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.charr.CharWeather.R;
import com.charr.CharWeather.db.CoolWeatherDB;
import com.charr.CharWeather.model.CityInfo;
import com.charr.CharWeather.util.HttpCallBackListener;
import com.charr.CharWeather.util.HttpUtil;
import com.charr.CharWeather.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
public class ChooseAreaActivity extends Activity {
    private EditText title;
    private ListView lv_cityList;
    private Button btn_Search;
    private List dataList = new ArrayList<String>();
    private ArrayAdapter<String> adapter = null;
    private CoolWeatherDB coolWeatherDb;
    private List<CityInfo> cityList = null;
    private CityInfo selectedCity;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_area);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        title = (EditText) findViewById(R.id.title_text);
        lv_cityList = (ListView) findViewById(R.id.list_view);
        btn_Search = (Button) findViewById(R.id.search);

        queryCities();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        lv_cityList.setAdapter(adapter);
        coolWeatherDb = CoolWeatherDB.getInstance(this);
        lv_cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCity = cityList.get(i);
            }
        });
    }

    private void queryCities() {
        cityList = coolWeatherDb.loadCityInfo();
        if(cityList.size() > 0){
            dataList.clear();

            for(CityInfo cityInfo:cityList){
                dataList.add(cityInfo.getCity_name());
            }
            adapter.notifyDataSetChanged();
            lv_cityList.setSelection(0);
            title.setText(selectedCity.getCity_name());
        }else {
            queryFromService();
        }
    }

    private void getCityInfo(){
        
    }

    private void queryFromService() {
        String address = "http://mobile.weather.com.cn/js/citylist.xml";
        showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpCallBackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                result = Util.handleCitiesResponse(coolWeatherDb, response);

                if(result){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialer();
                            queryCities();
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void closeProgressDialer() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }


}
