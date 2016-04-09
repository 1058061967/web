package cn.yijicai.app.util;

import com.whalin.MemCached.SockIOPool;

/**
 * Created by eshowtech on 2015/8/14.
 */
public class MemCacheInit{
    private String[] server;//服务器
    private Integer[] weights;//对应服务权重
    private Integer minConn;//初始化连接数
    private Integer maxConn;//最大连接数
    private Integer maxIdle;//最大链接时间
    private Integer mainSleep;//
    private Integer readTimeOut;//读取超时时间

    public Integer getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(Integer connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public Integer getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(Integer readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public Integer getMainSleep() {
        return mainSleep;
    }

    public void setMainSleep(Integer mainSleep) {
        this.mainSleep = mainSleep;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxConn() {
        return maxConn;
    }

    public void setMaxConn(Integer maxConn) {
        this.maxConn = maxConn;
    }

    public Integer getMinConn() {
        return minConn;
    }

    public void setMinConn(Integer minConn) {
        this.minConn = minConn;
    }

    public Integer[] getWeights() {
        return weights;
    }

    public void setWeights(Integer[] weights) {
        this.weights = weights;
    }

    public String[] getServer() {
        return server;
    }

    public void setServer(String[] server) {
        this.server = server;
    }

    private Integer connectTimeOut;//连接超时时间

    public void initPool(){
        SockIOPool pool=SockIOPool.getInstance();
        pool.setServers(this.server);
        pool.setWeights(this.weights);
        pool.setInitConn(this.minConn);
        pool.setMinConn(this.minConn);
        pool.setMaxConn(this.maxConn);
//        pool.setMaintSleep(this.mainSleep);
        pool.setMaxIdle(this.maxIdle);
        pool.setNagle(false);
        pool.setSocketTO(this.readTimeOut);
        pool.setSocketConnectTO(this.connectTimeOut);
        pool.initialize();
    }
}
