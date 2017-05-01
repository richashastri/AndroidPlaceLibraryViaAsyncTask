package edu.asu.msse.rshastri.placelibraryviaasynctask;

/**
 * Created by richashastri29 on 3/24/2017.
 */

//I give the full right to Dr lindquist and Arizona State University to build my project and evaluate it or the purpose of determining your grade and program assessment.
//
//Purpose: Interface to retur value from onPostExecute to main activity
//
// Ser423 Mobile Applications
//see http://pooh.poly.asu.edu/Mobile
//@author Richa Shastri Richa.Shastri@asu.edu
//        Software Engineering, CIDSE, ASU Poly
//@version March 24, 2017

public interface AsynResponse {
    void processFinish(PlaceDescription output);
}
