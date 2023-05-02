package org.atonInternship.Server;

import java.util.List;
import org.atonInternship.Object.DBObject;

public interface DB <T extends DBObject>{
    int insertData(T data);
    List<T> get(T data);
    int remove(T data);
}
