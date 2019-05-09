package test.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 服务端接口必须实现java.rmi.Remote
 * Created by wwt on 2016/9/14.
 */
public interface IOperation extends Remote {

    /**
     * 远程接口上的方法必须抛出RemoteException，因为网络通信是不稳定的，不能吃掉异常
     * @param a
     * @param b
     * @return
     */
    int add(int a, int b) throws RemoteException;

}
