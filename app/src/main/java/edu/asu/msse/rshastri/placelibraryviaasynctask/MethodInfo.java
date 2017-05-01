package edu.asu.msse.rshastri.placelibraryviaasynctask;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by richashastri29 on 3/24/2017.
 */

/**
 * Created by richashastri29 on 3/21/2017.
 */

//I give the full right to Dr lindquist and Arizona State University to build my project and evaluate it or the purpose of determining your grade and program assessment.
//
//Purpose: The MethodInformation to pass the arguements to asynctask
//
// Ser423 Mobile Applications
//see http://pooh.poly.asu.edu/Mobile
//@author Richa Shastri Richa.Shastri@asu.edu
//        Software Engineering, CIDSE, ASU Poly
//@version March 24, 2017
public class MethodInfo {
    public String method;
    public Object[] params;
    public AppCompatActivity parent;
    public String urlString;
    public String resultAsJson;

    MethodInfo( AppCompatActivity parent, String urlString, String method, Object[] params){
        this.method = method;
        this.parent = parent;
        this.urlString = urlString;
        this.params = params;
        this.resultAsJson = "{}";
    }
}
