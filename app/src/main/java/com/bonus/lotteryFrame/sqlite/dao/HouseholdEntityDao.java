package com.bonus.lotteryFrame.sqlite.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bonus.lotteryFrame.entity.HouseholdEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tb_household".
*/
public class HouseholdEntityDao extends AbstractDao<HouseholdEntity, Long> {

    public static final String TABLENAME = "tb_household";

    /**
     * Properties of entity HouseholdEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "id");
        public final static Property Number = new Property(1, String.class, "number", false, "number");
        public final static Property Phone = new Property(2, String.class, "phone", false, "phone");
        public final static Property Name = new Property(3, String.class, "name", false, "name");
        public final static Property Water = new Property(4, String.class, "water", false, "water");
        public final static Property Electricity = new Property(5, String.class, "electricity", false, "electricity");
        public final static Property Address = new Property(6, String.class, "address", false, "address");
        public final static Property Time = new Property(7, String.class, "time", false, "time");
    }


    public HouseholdEntityDao(DaoConfig config) {
        super(config);
    }
    
    public HouseholdEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tb_household\" (" + //
                "\"id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"number\" TEXT," + // 1: number
                "\"phone\" TEXT," + // 2: phone
                "\"name\" TEXT," + // 3: name
                "\"water\" TEXT," + // 4: water
                "\"electricity\" TEXT," + // 5: electricity
                "\"address\" TEXT," + // 6: address
                "\"time\" TEXT);"); // 7: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tb_household\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HouseholdEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(2, number);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String water = entity.getWater();
        if (water != null) {
            stmt.bindString(5, water);
        }
 
        String electricity = entity.getElectricity();
        if (electricity != null) {
            stmt.bindString(6, electricity);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(7, address);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HouseholdEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(2, number);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String water = entity.getWater();
        if (water != null) {
            stmt.bindString(5, water);
        }
 
        String electricity = entity.getElectricity();
        if (electricity != null) {
            stmt.bindString(6, electricity);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(7, address);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HouseholdEntity readEntity(Cursor cursor, int offset) {
        HouseholdEntity entity = new HouseholdEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // number
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // phone
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // water
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // electricity
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // address
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HouseholdEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNumber(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhone(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setWater(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setElectricity(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAddress(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HouseholdEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HouseholdEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HouseholdEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
