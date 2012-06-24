package com.zccicy.common.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zccicy.common.util.DBHelper;
import com.zccicy.common.util.StringUtil;

public class DefaultDao<T> {
	private String tableName;
	private Class<T> entityClass;

	private DBHelper dbhelper;
	// private String[] columnName;
	private String[] pKname;
	private Field[] entityFields;
	private String[] entityFieldsName;
	private Class[] entityFieldsType;
	private int entityFieldsLength;
	protected Context context;
	private Method[] getMethods;
	private Method[] setMethods;
	private Object a;

	public DefaultDao(String tableName, Class<T> entityC, Context appContext,
			String[] primaryKeyName) {
		super();
		try {
			this.tableName = tableName;
			this.entityClass = entityC;
			dbhelper = new DBHelper(appContext);

			pKname = primaryKeyName;
			entityFields = entityC.getDeclaredFields();
			entityFieldsLength = entityFields.length;
			entityFieldsName = new String[entityFieldsLength];
			getMethods = new Method[entityFieldsLength];
			setMethods = new Method[entityFieldsLength];
			entityFieldsType = new Class[entityFieldsLength];
			for (int i = 0; i < entityFieldsLength; i++) {
				entityFieldsName[i] = new String();
				entityFieldsName[i] = entityFields[i].getName();
				String stringLetter = entityFieldsName[i].substring(0, 1)
						.toUpperCase();
				String getName = "get" + stringLetter
						+ entityFieldsName[i].substring(1);
				String setName = "set" + stringLetter
						+ entityFieldsName[i].substring(1);

				getMethods[i] = entityClass.getMethod(getName, new Class[] {});
				setMethods[i] = entityClass.getMethod(setName,
						new Class[] { entityFields[i].getType() });

				entityFieldsType[i] = entityFields[i].getType();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 从String转换成model single

	public T fromStringToModel(String[] source) {
		T result = null;
		try {

			result = entityClass.newInstance();
			for (int i = 0; i < entityFieldsLength; i++) {
				Object o = adaptTypeFromString(entityFieldsType[i], source[i]);
				setMethods[i].invoke(result, o);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	// 从String转换成model List

	public List<T> fromStringListToModelList(List<String[]> source) {
		List<T> resultList = new ArrayList<T>();

		for (int i = 0; i < source.size(); i++) {
			resultList.add(fromStringToModel(source.get(i)));
		}
		return resultList;
	}

	// 插入数据
	public boolean insert(T entity) {
		SQLiteDatabase dbWriteable = dbhelper.getWritableDatabase();
		try {

			String[] entityValue = getEntityValue(entity);
			// String
			// sql="insert into "+tableName+" ("+entityFieldsNamesCommon+") values"+" ( "+getEntityValue(entity)+" ) ";
			ContentValues cv = new ContentValues();
			for (int i = 0; i < entityFieldsLength; i++) {
				if (entityValue[i] != null)
					cv.put(entityFieldsName[i], entityValue[i]);
			}

			if (-1 != dbWriteable.insert(tableName, null, cv)) {
				dbWriteable.close();
				return true;

			}
			dbWriteable.close();
		} catch (Exception e) {
			// TODO: handle exception
			dbWriteable.close();
			e.printStackTrace();
		}

		return false;
	}

	// 获得entity所有成员的值
	public String[] getEntityValue(T entity) {
		String[] result = new String[entityFieldsLength];
		try {
			for (int i = 0; i < entityFieldsLength; i++) {
				Method m = getMethods[i];

				Object obj = m.invoke(entity);

				if (null != obj) {
					result[i] = obj.toString();
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	public boolean delete(int pk)
	{
		return delete(new String[]{pk+""});
	}
	public boolean delete(String pk)
	{
		return delete(new String[]{pk});
	}
	
	// 以主键为索引删除
	public boolean delete(String[] pk) {
		SQLiteDatabase dbWriteable = dbhelper.getWritableDatabase();
		try {
			if (pk == null)
				return false;
			String whereParam = StringUtil.getWhereFromPk(pKname, pk);
			if (0 != dbWriteable.delete(tableName, whereParam, null)) {
				dbWriteable.close();
				return true;
			}

			else {
				dbWriteable.close();
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			dbWriteable.close();
			e.printStackTrace();
			return false;
		}
	}

	// 以where为索引删除
	public boolean delete(String whereClause, String[] whereArgs) {

		SQLiteDatabase dbWriteable = dbhelper.getWritableDatabase();
		try {
			if (0 != dbWriteable.delete(tableName, whereClause, whereArgs)) {
				dbWriteable.close();
				return true;

			} else {
				dbWriteable.close();
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			dbWriteable.close();
			e.printStackTrace();
			return false;
		}

	}

	// query简化版
	public List<T> query(String where) {
		return query(where, null, null, null, null);
	}

	// 用whereClause和whereArgs查找
	public List<T> query(String whereClause, String[] whereArgs,
			String groupBy, String having, String orderBy) {
		List<T> resultList = new ArrayList<T>();

		SQLiteDatabase dbReadable = dbhelper.getReadableDatabase();
		Cursor c = dbReadable.query(tableName, entityFieldsName, whereClause,
				whereArgs, groupBy, having, orderBy);

		T result = null;

		try {
			while (c.moveToNext()) {
				try {
					result = entityClass.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
				for (int i = 0; i < entityFieldsLength; i++) {
					Method m = setMethods[i];
					Object o = adaptTypeFromString(entityFieldsType[i],
							c.getString(i));

					m.invoke(result, o);
				}

				resultList.add(result);

			}
		} catch (Exception e) {
			// TODO: handle exception
			dbReadable.close();
			c.close();
			e.printStackTrace();
		}
		dbReadable.close();
		c.close();

		return resultList;
	}

	private Object adaptTypeFromString(Class class1, String string) {
		// TODO Auto-generated method stub

		if (null != string) {
			if (class1.getName().equals("java.lang.Integer")) {
				return Integer.parseInt(string);
			}
		}

		return string;
	}

	// 判断记录是否存在
	public boolean isExist(String[] pk) {

		T model = getModelByPk(pk);
		if (null != model)
			return true;
		else
			return false;
	}
	
	public T getModelByPk(int pk)
	{
		return getModelByPk(new String[]{pk+""});
	}
	public T getModelByPk(String pk)
	{
		return getModelByPk(new String[]{pk});
	}
	public List<T> getModelListByPk(String[] pk)
	{
		String whereParam = StringUtil.getWhereFromPk(pKname, pk);
		List<T> result = query(whereParam, null, null, null, null);
		
			return result;
	}
	//
	public T getModelByPk(String[] pk) {
		List<T> result=getModelListByPk(pk);
		if (null == result || 0 == result.size())
			return null;
		else
			return result.get(0);
		
	}
	 
	

	public boolean update(T entity, String[] pk, String whereClause,
			String[] whereArgs) {

		if (!isExist(pk))
			return insert(entity);
		else {
			String[] entityValue = getEntityValue(entity);
			ContentValues cv = new ContentValues();
			for (int i = 0; i < entityFieldsLength; i++) {
				cv.put(entityFieldsName[i], entityValue[i]);
			}
			SQLiteDatabase dbWriteable = dbhelper.getWritableDatabase();
			try {
				if (0 <= dbWriteable.update(tableName, cv, whereClause,
						whereArgs)) {
					dbWriteable.close();
					return true;
				}

				else {
					dbWriteable.close();
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				dbWriteable.close();
				return false;
			}
		}

	}

}
