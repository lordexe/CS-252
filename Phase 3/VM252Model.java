import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import vm252GUI.java;

public class vm252Model {
    short accumulator;
    int programCounter;
    String objectFileName;
    final int MAX_LENGTH = 8192;
    byte [] memory = new byte[MAX_LENGTH];

    public vm252Model(){
    }
//Methods that implement debugger actions as listed in vm252dbg help such as:
// [aa,amb,amd,amdx,ap,ba,bl,cb,h,mb,md,mi,n,ob,od,oi,q,r,s,t,z]   
    //Alter contents of accumulator to IV
    public int alterACC(int ACC){
      return ACC;
    }
    //Alter contents of program counter to MA
    public int alterProgCounter(int MA){
      return MA;
    }
    //Set a breakpoint at address MA
    public int breakAddress(int MA){
      return MA;
    }
    //Set a breakpoint at source-line L
    public int breakLine(int MA){
      return MA;
    }
