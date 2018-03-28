package com.sgo.depanalyze.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoTableMappingList {
    private Map<String, DaoTableMappingProperties> map = new HashMap<String, DaoTableMappingProperties>();

    public void addMapping(DaoTableMappingProperties props) {
        map.put(props.getDaoClassName(), props);
    }

    public DaoTableMappingProperties getMapping(String daoClassName) {
        return map.get(daoClassName);
    }

    public boolean isMappingExists(String daoClassName) {
        return map.containsKey(daoClassName);
    }

    public List<DaoTableMappingProperties> getMappingList() {
        return new ArrayList<DaoTableMappingProperties>(map.values());
    }
}
