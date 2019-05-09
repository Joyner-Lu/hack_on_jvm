package test.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by wwt on 2016/9/14.
 */
public class OperationImpl extends UnicastRemoteObject implements IOperation{

    public OperationImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException{
        return a+b;
    }

}
