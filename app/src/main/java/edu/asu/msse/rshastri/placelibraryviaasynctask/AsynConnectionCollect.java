package edu.asu.msse.rshastri.placelibraryviaasynctask;

import android.os.AsyncTask;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by richashastri29 on 3/24/2017.
 */



//I give the full right to Dr lindquist and Arizona State University to build my project and evaluate it or the purpose of determining your grade and program assessment.
//
//Purpose: AsyncThread methods implementation
//
// Ser423 Mobile Applications
//see http://pooh.poly.asu.edu/Mobile
//@author Richa Shastri Richa.Shastri@asu.edu
//        Software Engineering, CIDSE, ASU Poly
//@version March 24, 2017

public class AsynConnectionCollect extends AsyncTask<MethodInfo,Integer,MethodInfo> {
    public AsynResponse delegate = null;

    @Override
    protected MethodInfo doInBackground(MethodInfo... aRequest) {


        android.util.Log.d(this.getClass().getSimpleName(),"in doInBackground on "+
                (Looper.myLooper() == Looper.getMainLooper()?"Main thread":"Async Thread"));
        try {
            JSONArray ja = new JSONArray(aRequest[0].params);
            android.util.Log.d(this.getClass().getSimpleName(),"params: "+ja.toString());
            String requestData = "{ \"jsonrpc\":\"2.0\", \"method\":\""+aRequest[0].method+"\", \"params\":"+ja.toString()+
                    ",\"id\":3}";
            android.util.Log.d(this.getClass().getSimpleName(),"requestData: "+requestData+" url: "+aRequest[0].urlString);
            JsonRPCRequestViaHttp conn = new JsonRPCRequestViaHttp((new URL(aRequest[0].urlString)), aRequest[0].parent);
            String resultStr = conn.call(requestData);
            aRequest[0].resultAsJson = resultStr;
        }catch (Exception ex){
            android.util.Log.d(this.getClass().getSimpleName(),"exception in remote call "+
                    ex.getMessage());
        }
        return aRequest[0];
    }

    @Override
    protected void onPreExecute(){
        android.util.Log.d(this.getClass().getSimpleName(),"in onPreExecute on "+
                (Looper.myLooper() == Looper.getMainLooper()?"Main thread":"Async Thread"));

    }

    @Override
    protected void onPostExecute(MethodInfo res){


        android.util.Log.d(this.getClass().getSimpleName(), "in onPostExecute on " +
                (Looper.myLooper() == Looper.getMainLooper() ? "Main thread" : "Async Thread"));
        android.util.Log.d(this.getClass().getSimpleName(), " resulting is: " + res.resultAsJson);
        try {
            if (res.method.equals("getNames")) {
                JSONObject jo = new JSONObject(res.resultAsJson);
                JSONArray ja = jo.getJSONArray("result");
                ArrayList<String> al = new ArrayList<String>();
                for (int i = 0; i < ja.length(); i++) {
                    al.add(ja.getString(i));
                }
                String[] names = al.toArray(new String[0]);
                if(res.parent instanceof MainActivity){

                    ((MainActivity)res.parent).adapter.clear();
                    for (int i = 0; i < names.length; i++) {

                        ((MainActivity)res.parent).adapter.add(names[i]);
                    }
                    ((MainActivity)res.parent).adapter.notifyDataSetChanged();
                }else if(res.parent instanceof Main2Activity){

                    ((Main2Activity)res.parent).aaA.clear();
                    for (int i = 0; i < names.length; i++) {

                        ((Main2Activity)res.parent).aaA.add(names[i]);
                    }
                    ((Main2Activity)res.parent).aaA.notifyDataSetChanged();
                }

            } else if (res.method.equals("get")) {

                if(res.params[0].toString() == Main2Activity.j){
                    JSONObject jo = new JSONObject(res.resultAsJson);
                    System.out.println(res.params[0].toString());
                    System.out.println(Main2Activity.j);
                    Main2Activity.firstselection = new PlaceDescription(jo.getJSONObject("result"));

                }else if (res.params[0].toString() == Main2Activity.selecteddest){
                    JSONObject jo = new JSONObject(res.resultAsJson);
                    android.util.Log.w(this.getClass().getSimpleName(),res.params[0].toString());
                    Main2Activity.destination = new PlaceDescription(jo.getJSONObject("result"));

                }


                delegate.processFinish(Main2Activity.firstselection);

            } else if (res.method.equals("add")){
                try{

                    // finished adding a student. refresh the list of students by going back to the server for names
                    MethodInfo mi = new MethodInfo(res.parent, res.urlString, "getNames", new Object[]{ });
                    AsynConnectionCollect ac = (AsynConnectionCollect) new AsynConnectionCollect().execute(mi);
                } catch (Exception ex){
                    android.util.Log.w(this.getClass().getSimpleName(),"Exception processing getNames: "+
                            ex.getMessage());
                }
            }
        }catch (Exception ex){
            android.util.Log.d(this.getClass().getSimpleName(),"Exception: "+ex.getMessage());
        }
    }


}


