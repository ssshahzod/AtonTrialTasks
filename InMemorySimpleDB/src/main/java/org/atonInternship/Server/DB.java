package org.atonInternship.Server;

import org.atonInternship.Object.DBObject;

public interface DB <T extends DBObject>{
    void insertData(T data);
    T get();
    void remove();
}
