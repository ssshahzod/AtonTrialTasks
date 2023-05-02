package org.atonInternship.Server;

import static org.junit.jupiter.api.Assertions.*;

import org.atonInternship.Object.SimpleDBObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleDBTest {
    SimpleDB db;

    @BeforeEach
    void setUp() {
        db = new SimpleDB();
    }
    @Test
    void testInsertData() {
        SimpleDBObject object1 = new SimpleDBObject(1L, "Alice", 1000.0);
        SimpleDBObject object2 = new SimpleDBObject(2L, "Bob", 2000.0);
        SimpleDBObject object3 = new SimpleDBObject(3L, "Charlie", 3000.0);
        SimpleDBObject object4 = new SimpleDBObject(3L, "Char", 3400.0);

        db.insertData(object1);
        db.insertData(object1);
        Assertions.assertEquals(1, db.get(new SimpleDBObject(1L, null, null)).size());


        db.insertData(object2);
        db.insertData(object3);

        Assertions.assertTrue(db.get(new SimpleDBObject(1L, null, null)).contains(object1));
        Assertions.assertTrue(db.get(new SimpleDBObject(null, "Bob", null)).contains(object2));
        Assertions.assertTrue(db.get(new SimpleDBObject(null, null, 3000.0)).contains(object3));

        db.insertData(object4);
        Assertions.assertTrue(db.get(new SimpleDBObject(3L, null, null)).contains(object3));
        Assertions.assertTrue(db.get(new SimpleDBObject(3L, null, null)).contains(object4));
    }

    @Test
    void testRemove() {
        SimpleDBObject object1 = new SimpleDBObject(1L, "Alice", 1000.0);
        SimpleDBObject object2 = new SimpleDBObject(2L, "Bob", 2000.0);
        SimpleDBObject object3 = new SimpleDBObject(2L, "Charlie", 3000.0);

        db.insertData(object1);
        db.insertData(object2);
        db.insertData(object3);

        SimpleDBObject toRemove = new SimpleDBObject(2L, null, 2000.0);
        db.remove(toRemove);

        var result = db.get(toRemove);
        Assertions.assertNull(result);

        var tmp = db.get(new SimpleDBObject(2L, null, null));

        Assertions.assertEquals(1, tmp.size());
        Assertions.assertTrue(tmp.contains(object3));
    }

    @Test
    void testGet(){
        SimpleDBObject object1 = new SimpleDBObject(1L, "Alice", 1000.0);
        SimpleDBObject object2 = new SimpleDBObject(2L, "Bob", 2000.0);

        db.insertData(object1);
        db.insertData(object2);

        Assertions.assertTrue(db.get(new SimpleDBObject(1L, null, null)).contains(object1));
        Assertions.assertTrue(db.get(new SimpleDBObject(null, "Bob", null)).contains(object2));


        Assertions.assertNull(db.get(new SimpleDBObject(3L, null, null)));
    }

    @Test
    void testEdit(){
        SimpleDBObject object1 = new SimpleDBObject(1L, "Alice", 1000.0);
        SimpleDBObject object2 = new SimpleDBObject(2L, "Bob", 2000.0);

        db.insertData(object1);
        db.insertData(object2);

        Assertions.assertTrue(db.get(new SimpleDBObject(1L, null, null)).contains(object1));
        Assertions.assertTrue(db.get(new SimpleDBObject(null, "Bob", null)).contains(object2));

        SimpleDBObject object3 = db.get(new SimpleDBObject(1L, null, null)).get(0);
        db.remove(object3);

        object3.setValue(3000.0);
        db.insertData(object3);

        Assertions.assertTrue(db.get(new SimpleDBObject(null, null, 3000.0)).contains(object3));
    }
}