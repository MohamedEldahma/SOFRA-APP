package com.example.pro.sofranewapp.helper;

import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class ValidationHelper {

    public static void error(String  messag){
        Log.e(TAG,messag );
        }
public  static void verbose(String messag){

    Log.e(TAG,messag);
}
}
