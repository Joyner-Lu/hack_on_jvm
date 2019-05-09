package test.client;

import test.rmi.server.IOperation;

import java.rmi.Naming;

/**
 * Created by wwt on 2016/9/15.
 */
public class Client {

    public static void main(String args[]) throws Exception{
        IOperation iOperation= (IOperation) Naming.lookup("rmi://127.0.0.1:1099/Operation");
        System.out.println(iOperation.add(1,1));
    }

}