package net.tableschedule.jsf.bean.cache;

import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 03.05.17.
 */
@NoArgsConstructor
public class CacheCities {

    private static volatile CacheCities instance;
    private List<String> cache = new ArrayList<String>();

    public void updateCache(List<String> cities){
        cache = null;
        cache = cities;
    }

    public List<String> getCache(){
        return cache;
    }

    public static CacheCities getInstance() {
        CacheCities localInstance = instance;
        if (localInstance == null) {
            synchronized (CacheCities.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CacheCities();
                }
            }
        }
        return localInstance;
    }
}
