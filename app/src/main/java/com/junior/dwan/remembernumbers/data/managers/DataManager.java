package com.junior.dwan.remembernumbers.data.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Might on 04.11.2016.
 */

public class DataManager {

    private PreferencesManager mPreferencesManager;
    private static DataManager sDataManager;
    private Context mContext;

    private DataManager(Context context) {
        this.mContext=context;
        this.mPreferencesManager=new PreferencesManager();
    }

    public DataManager get(Context c){
        if(sDataManager==null){
            sDataManager=new DataManager(c.getApplicationContext());
        }
        return sDataManager;
    }

    public PreferencesManager getPreferencesManager(){
        return mPreferencesManager;
    }




}
